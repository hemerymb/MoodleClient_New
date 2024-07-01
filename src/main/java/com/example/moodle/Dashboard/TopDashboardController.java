package com.example.moodle.Dashboard;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import com.example.moodle.HelloApplication;
import com.example.moodle.Login.HelloController;

import com.example.moodle.Privatefiles.PrivateFile;
import com.example.moodle.Teacher.entity.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.moodle.HelloApplication.stage;
import static com.example.moodle.moodleclient.Moodleclient.user;




public class TopDashboardController implements Initializable{

        @FXML
        private Circle connectionIndic;

        @FXML
        private CustomMenuItem editProfileMenu;

        @FXML
        private CustomMenuItem logOutMenu;

        @FXML
        private Label loginIndic;

        @FXML
        private HBox moodleLayout;

        @FXML
        private Label notifIndic;

        @FXML
        private MenuButton profileBtn;

        @FXML
        private ImageView profileImg;

        @FXML
        private ImageView profileImg_in_menu;

        @FXML
        private ProgressBar progressBar;

        @FXML
        private Button syncBtn;

        @FXML
        private ImageView syncImg;

        @FXML
        private CustomMenuItem syncMenu;

        @FXML
        private Label teacherLabel;

        @FXML
        private Tooltip tipIndic;

        @FXML
        private Label username;

    private static ObservableList<Course> courList = FXCollections.observableArrayList();


    static List<PrivateFile> privateFiles = new ArrayList<>();

    private static final String SERVER_ADDRESS = "http://localhost/";
    private static final String TOKEN = "70ee2f13a67b858438afd8ddb3525854";

    private static final String REQUEST_URL = "http://localhost/login/token.php?username=camrole&password=Camrole-5000&service=SMAS";



    @Override
        public void initialize(URL url, ResourceBundle rb) {
            if (!HelloController.isTeacher) teacherLabel.setText("Student");
            username.setText(user.getUsername());
        }

        @FXML
        void handleLogOutMenu(ActionEvent event) throws IOException {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/LOgOutDialog.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void handleProfileEdit(ActionEvent event) {
            try {
                // Load the FXML for the edit profile dialog
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/EditProfileDialog.fxml"));
                Parent root = loader.load();

                // Create a new stage for the dialog
                Stage stage = new Stage();
                stage.setTitle("Edit Profile");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void handleSyncBtn(ActionEvent event) {
            SyncroniserCour();

            // Logic for sync button
            System.out.println("Sync Clicked");
        }

    public static void readCoursessyncro() {
        String URL = "jdbc:mysql://localhost:3307/moodleclient";
        String user = "root";
        String password = "root";
        String query = "SELECT id,courseName,courseAbr,courseDescription,nbChapters,nbAssignments FROM Course";
        try (Connection connection = DriverManager.getConnection(URL,user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String courseName = resultSet.getString("courseName");
                String courseAbr = resultSet.getString("courseAbr");
                String courseDescription = resultSet.getString("courseDescription");
                int nbChapters = resultSet.getInt("nbChapters");
                int nbAssignments = resultSet.getInt("nbAssignments");

                System.out.println("ID: " + id + ", Course Name: " + courseName + ", Course Abbreviation: " + courseAbr +
                        ", Description: " + courseDescription + ", Chapters: " + nbChapters + ", Assignments: " + nbAssignments);
                courList.add(new Course( id, courseName, courseAbr,courseDescription,nbChapters,nbAssignments));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<PrivateFile> readPrivateFilessyncro() {
        String URL = "jdbc:mysql://localhost:3307/moodleclient";
        String user = "root";
        String password = "root";

        String query = "SELECT * FROM private_files";
        try (Connection connection = DriverManager.getConnection(URL,user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String fileName = resultSet.getString("fileName");
                long fileSize = resultSet.getLong("fileSize");
                String fileType = resultSet.getString("fileType");
                String filePath = resultSet.getString("filePath");
                privateFiles.add(new PrivateFile(id, fileName, fileSize, fileType, filePath));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return privateFiles;
    }

    private static String uploadFileToDraftArea(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            System.err.println("Cannot access file: " + filePath);
            return null;
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(SERVER_ADDRESS + "webservice/upload.php?token=" + TOKEN);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file_1", file, ContentType.APPLICATION_OCTET_STREAM, file.getName());
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);

            System.out.println("Upload response: " + responseString);

            if (responseString.startsWith("[")) {
                JSONArray jsonResponse = new JSONArray(responseString);
                JSONObject jsonObject = jsonResponse.getJSONObject(0);
                return String.valueOf(jsonObject.getLong("itemid"));
            } else if (responseString.startsWith("{")) {
                JSONObject jsonResponse = new JSONObject(responseString);
                return String.valueOf(jsonResponse.getLong("itemid"));
            } else {
                System.err.println("Unexpected response format: " + responseString);
                return null;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static void moveToUserPrivateFiles(String draftId) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(SERVER_ADDRESS + "webservice/rest/server.php?moodlewsrestformat=json");
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("draftid", draftId));
            urlParameters.add(new BasicNameValuePair("wsfunction", "core_user_add_user_private_files"));
            urlParameters.add(new BasicNameValuePair("wstoken", TOKEN));

            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = httpClient.execute(post);
            HttpEntity responseEntity = response.getEntity();
            String responseString = EntityUtils.toString(responseEntity);

            System.out.println("Move to private files response: " + responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SyncroniserCour() {
        readCoursessyncro();
        List<PrivateFile> privatesyncrofiles=readPrivateFilessyncro();
        for(PrivateFile prifiles:privatesyncrofiles){
            String draftId = uploadFileToDraftArea(prifiles.getFilePath());
            if (draftId != null) {
                moveToUserPrivateFiles(draftId);
            }
        }


        String moodleUrl = "http://localhost/webservice/rest/server.php";
        String token = "2a8037fc2be456239aba388221cfc1f7";
        String categoryid = "1";

        for (Course cour : courList) {
            System.out.println("ID: " + cour.getId() + ", Course Name: " + cour.getCourseAbr() + ", Course Abbreviation: " + cour.getCourseDescription());
            try {
                    URL url = new URL(moodleUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setDoOutput(true);

                    String params = "moodlewsrestformat=json&wsfunction=core_course_create_courses&wstoken=" + token +
                            "&courses[0][fullname]=" + cour.getCourseName() +
                            "&courses[0][shortname]=" + cour.getCourseAbr() +
                            "&courses[0][categoryid]=" + categoryid +
                            "&courses[0][summary]=" + cour.getCourseDescription();

                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = params.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                    }

                    int responseCode = conn.getResponseCode();
                    System.out.println("Response Code : " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {

                        System.out.println("Course synchronized: " + cour.getCourseName());
                    } else {
                        System.out.println("Failed to synchronize course: " + cour.getCourseName());
                    }

                    conn.disconnect();

                } catch (Exception e) {
                    e.printStackTrace();
                }

        }


    }


    public static String executeRequest() throws Exception {
        URL url = new URL(REQUEST_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Extract the token from the response
            String responseStr = response.toString();
            return extractTokenFromResponse(responseStr);
        } else {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
    }

    private static String extractTokenFromResponse(String response) {
        // Assuming the response is in JSON format: {"token":"yourtoken"}
        // You can use a JSON library like org.json to parse the response
        try {
            org.json.JSONObject jsonObject = new org.json.JSONObject(response);
            return jsonObject.getString("token");
        } catch (org.json.JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    }

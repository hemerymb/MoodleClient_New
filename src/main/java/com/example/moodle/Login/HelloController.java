package com.example.moodle.Login;

import static com.example.moodle.moodleclient.Moodleclient.root;
import static com.example.moodle.moodleclient.Moodleclient.user;

import com.example.moodle.DBConnection;
import com.example.moodle.HelloApplication;
import com.example.moodle.MainDry.Dry;
import com.example.moodle.moodleclient.Moodleclient;
import com.example.moodle.moodleclient.client_moodle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Label errmsg;

    @FXML
    private Label tryconnect;

    @FXML
    public ImageView image1;

    @FXML
    private JFXTextField username1;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXRadioButton teacher1;

    @FXML
    private JFXRadioButton student1;

    public static boolean isTeacher;
    static private String  REQUEST_URL;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new client_moodle();

        this.errmsg.setText("");
        this.errmsg.setVisible(false);
        this.tryconnect.setVisible(false);

    }

    private boolean checkCredentials(String userName, String pass, boolean isTeacher) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ? AND statut = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                statement.setString(2, pass);
                statement.setString(3, isTeacher ? "Teacher" : "Student");

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            errmsg.setText("Error: " + e.getMessage());
            errmsg.setVisible(true);
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) throws IOException {
        String userName = username1.getText();
        String pass = password1.getText();
        boolean isStudent = student1.isSelected();
        boolean isTeacher = teacher1.isSelected();

          REQUEST_URL = "http://localhost/login/token.php?username="+userName+"&password="+pass+"&service=SMAS";



        if (userName.isEmpty() || pass.isEmpty() || (!student1.isSelected() && !teacher1.isSelected())) {
            errmsg.setText("You must enter a username, password, and select a status!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);


        if (checkCredentials(userName, pass, isTeacher)) {
            tryconnect.setText("Connected successfully!");
            tryconnect.setVisible(true);

            user.setUsername(userName);

            if (student1.isSelected()) {

                user.is_Teacher(false);

                HelloController.isTeacher = false;
                root = new BorderPane();
                Dry.showDashboard(root, false);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            } else if (teacher1.isSelected()) {

                user.is_Teacher(true);

                HelloController.isTeacher = true;
                root = new BorderPane();
                Dry.showDashboard(root, true);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            }
        } else {
            try {
                String token = executeRequest();
                System.out.println("Token: " + token);
                if(token!= null){
                    insertUserss(userName,pass);
                    if (student1.isSelected()) {

                        user.is_Teacher(false);

                        HelloController.isTeacher = false;
                        root = new BorderPane();
                        Dry.showDashboard(root, false);
                        Scene scene = new Scene(root, 1180, 707);

                        HelloApplication.stage.setTitle("Moodle Client");
                        HelloApplication.stage.setScene(scene);
                        HelloApplication.stage.show();
                    } else if (teacher1.isSelected()) {

                        user.is_Teacher(true);

                        HelloController.isTeacher = true;
                        root = new BorderPane();
                        Dry.showDashboard(root, true);
                        Scene scene = new Scene(root, 1180, 707);

                        HelloApplication.stage.setTitle("Moodle Client");
                        HelloApplication.stage.setScene(scene);
                        HelloApplication.stage.show();
                    }

                }

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

    public static void insertUserss( String username, String password) {

         String JDBC_URL = "jdbc:mysql://localhost:3307/moodleclient";
         String USERNAME = "root";
         String PASSWORD = "juve5000";
        String query = "INSERT INTO Users ( username, password) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("Users inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

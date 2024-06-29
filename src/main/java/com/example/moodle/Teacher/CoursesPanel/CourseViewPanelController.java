package com.example.moodle.Teacher.CoursesPanel;

import com.example.moodle.MainDry.Dry;
import com.example.moodle.Teacher.Cards.ChapterCard;
import com.example.moodle.Teacher.Cards.CourseCard;
import com.example.moodle.Teacher.CoursesPanel.DialogWindows.CreateChapterDialogController;
import com.example.moodle.Teacher.CoursesPanel.DialogWindows.EditCourseDialogController;
import com.example.moodle.Teacher.entity.Chapter;
import com.example.moodle.Teacher.entity.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class CourseViewPanelController implements Initializable {

    @FXML
    private Button AssignmentsBtn;

    @FXML
    private VBox ChaptersVbox;

    @FXML
    private Button ChaptersBtn;

    @FXML
    private Button CourseFilesBtn;

    @FXML
    private Label EditCourse;

    @FXML
    private Button ParticipantsBtn;

    @FXML
    private TextArea courseDescription;

    @FXML
    private Label coursename;

    @FXML
    private Label deleteCourse;

    @FXML
    private GridPane gridpane;

    @FXML
    private Label returnArrow;

    @FXML
    private VBox leftbtnMenu;

    @FXML
    private Button newChapBtn;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private Text chaptersTitle;

    public Course course;

    public static List<ChapterCard> Chapterslist;

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/moodleclient";
        String user = "root";
        String password = "681503533";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Chapterslist = new ArrayList<>();
        ChaptersVbox.getChildren().clear();
        hideAll();
    }

    @FXML
    void handleAssignmentsBtn(ActionEvent event) {
        ChaptersVbox.setVisible(false);
        selectBtn(leftbtnMenu, AssignmentsBtn);
        ChaptersVbox.getChildren().clear();

    }

    @FXML
    void handleChaptersBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, ChaptersBtn);
        chaptersTitle.setVisible(true);
        ChaptersVbox.setVisible(true);
        newChapBtn.setVisible(true);
        newChapBtn.setDisable(false);
        ChaptersVbox.getChildren().clear();

        loadChaptersFromDatabase();

    }

    @FXML
    void handleCourseFilesBtn(ActionEvent event) {
        ChaptersVbox.setVisible(false);
        selectBtn(leftbtnMenu, CourseFilesBtn);
        ChaptersVbox.getChildren().clear();

    }

    @FXML
    void handleDeleteCourse(MouseEvent event) {

    }

    @FXML
    void handleEditCourse(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/EditCourseDialog.fxml"));
            Parent root = loader.load();

            EditCourseDialogController editcontroller = loader.getController();
            editcontroller.setCourse(getCourse());

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
    void handleParticipantsBtn(ActionEvent event) {
        ChaptersVbox.setVisible(false);
        selectBtn(leftbtnMenu, ParticipantsBtn);
        ChaptersVbox.getChildren().clear();

    }

    @FXML
    void handleReturn(MouseEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/CoursesPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleCreateChapter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateChapterDialog.fxml"));
            Parent root = loader.load();

            CreateChapterDialogController ccdc = loader.getController();
            ccdc.course = getCourse();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addChapterCard(ChapterCard chap){

        Chapterslist.add(chap);
        ChaptersVbox.getChildren().clear();

        for (ChapterCard ch : Chapterslist){
            ChaptersVbox.getChildren().add(ch);
        }
    }

    public Course getCourse(){
        String query = "SELECT * FROM Course WHERE courseName = '" + coursename.getText() +"'";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                course = new Course(
                        rs.getInt("id"),
                        rs.getString("courseName"),
                        rs.getString("courseAbr"),
                        rs.getString("courseDescription"),
                        rs.getInt("nbChapters"),
                        rs.getInt("nbAssignments")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public void loadChaptersFromDatabase() {
        String query = "SELECT * FROM chapters WHERE courseId = '" + course.getId() + "'";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                Chapter chap = new Chapter(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("num"),
                        rs.getString("content"),
                        rs.getInt("courseId")
                );

                ChaptersVbox.getChildren().clear();
                addChapterCard(new ChapterCard(chap));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void selectBtn(VBox VB, Button button) {
        button.getStyleClass().add("focused");
        for (Node node : VB.getChildren()) {
            if(node instanceof Button) {
                Button btn = (Button) node;
                if(!btn.equals(button)) {
                    btn.getStyleClass().remove("focused");
                }
            }
        }
    }

    public int getChaptersCount() {
        return Chapterslist.size();
    }

    void hideAll() {
        ChaptersVbox.setVisible(false);
        chaptersTitle.setVisible(false);
        newChapBtn.setVisible(false);
        newChapBtn.setDisable(true);
    }
}

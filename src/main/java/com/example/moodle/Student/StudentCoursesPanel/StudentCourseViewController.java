package com.example.moodle.Student.StudentCoursesPanel;

import com.example.moodle.MainDry.Dry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class StudentCourseViewController {
    @FXML
    private Button AssignmentsBtn;

    @FXML
    private Button ChaptersBtn;

    @FXML
    private Button CourseFilesBtn;

    @FXML
    private Button ParticipantsBtn;

    @FXML
    private TextArea courseDescription;

    @FXML
    private Label coursename;

    @FXML
    private GridPane gridpane;

    @FXML
    private VBox leftbtnMenu;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    void handleAssignmentsBtn(ActionEvent event) {

    }

    @FXML
    void handleChaptersBtn(ActionEvent event) {

    }

    @FXML
    void handleCourseFilesBtn(ActionEvent event) {

    }

    @FXML
    void handleParticipantsBtn(ActionEvent event) {

    }

    @FXML
    void handleReturn(MouseEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_CoursesPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void define(String name, String desc) {
        coursename.setText(name);
        courseDescription.setText(desc);
    }
}

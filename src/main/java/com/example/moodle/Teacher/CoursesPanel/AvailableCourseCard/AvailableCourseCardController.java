package com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.moodle.Teacher.CoursesPanel.CourseViewPanelController;
import com.example.moodle.Teacher.entity.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class AvailableCourseCardController implements Initializable{
    @FXML
    private Label ChaptersNumber;

    @FXML
    private Label courseDesc;

    @FXML
    private Label courseName;

    private Course cours;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void handleCourseView(MouseEvent event) throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(AvailableCourseCardController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
        AnchorPane content = contentLoader.load();

        CourseViewPanelController contentCtrl = contentLoader.getController();
        contentCtrl.course = cours;

        Label name = (Label) contentLoader.getNamespace().get("coursename");
        TextArea desc = (TextArea) contentLoader.getNamespace().get("courseDescription");

        name.setText(courseName.getText());
        desc.setText(courseDesc.getText());
        root.setCenter(content);
    }

    public void define(String name, String description, int nbchapters, Course cours) {
        this.courseName.setText(name);
        this.courseDesc.setText(description);
        this.courseDesc.setStyle("-fx-text-fill: black");
        this.ChaptersNumber.setText(nbchapters + " Chapitres");
        this.ChaptersNumber.setStyle("-fx-text-fill: black");
        this.cours = cours;
    }
}

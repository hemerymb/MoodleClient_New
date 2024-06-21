package com.example.moodle.Student.StudentCoursesPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class StudentAvailableCourseCardController implements Initializable{
    @FXML
    private Label ChaptersNumber;

    @FXML
    private Label courseDesc;

    @FXML
    private Label courseName;

    @FXML
    private Pane pane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void define(String courseName, String description, int nbChapters) {
        this.courseName.setText(courseName);
        this.courseDesc.setText(description);
        this.courseDesc.setStyle("-fx-text-fill: black");
        this.ChaptersNumber.setText(nbChapters + " Chapitres");
        this.ChaptersNumber.setStyle("-fx-text-fill: black");
    }

    @FXML
    void handleCourse(MouseEvent event) {
        System.out.println("Yo");
    }

}

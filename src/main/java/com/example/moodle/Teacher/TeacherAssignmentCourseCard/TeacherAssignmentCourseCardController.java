package com.example.moodle.Teacher.TeacherAssignmentCourseCard;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TeacherAssignmentCourseCardController implements Initializable {
    @FXML
    private Label assignName;
    @FXML
    private Label courseName;
    @FXML
    private Button detailsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleDetails() throws IOException {
        System.out.println("You clicked");

        AnchorPane detailsPane = FXMLLoader.load(getClass().getResource("/TeacherAssignmentDetailsController/TeacherAssignmentDetails.fxml"));
    }
}

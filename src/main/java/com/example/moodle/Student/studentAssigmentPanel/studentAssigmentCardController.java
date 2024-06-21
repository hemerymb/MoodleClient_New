package com.example.moodle.Student.studentAssigmentPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.Date;

public class studentAssigmentCardController {

    @FXML
    private Label ChaptersNumber;

    @FXML
    private Label deadLine;

    @FXML
    private VBox assigment;

    @FXML
    private Label AssigmentName;

    @FXML
    private Label courseName;

    @FXML
    private void initialize() {
    }

    public void define(String assigmentCourseName, String assigmentName, Date Deadline) {
        this.courseName.setText(assigmentCourseName);
        this.AssigmentName.setText(assigmentName);
        this.deadLine.setText(Deadline.toString());
    }

    @FXML
    void handleCourse(MouseEvent event) {
        System.out.println("Yo");
    }

}

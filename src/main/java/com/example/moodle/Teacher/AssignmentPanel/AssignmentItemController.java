package com.example.moodle.Teacher.AssignmentPanel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AssignmentItemController {

    @FXML
    private Label assignName;

    @FXML
    private Label courseName;

    @FXML
    private Button detailsBtn;

    private Assignment assignment;

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
        assignName.setText(assignment.getAssignmentName());
        courseName.setText(assignment.getCourseName());
    }

    @FXML
    private void handleDetails() {
        // Implement the details handling logic here
    }
}

package com.example.moodle.Teacher.AssignmentPanel;

import com.example.moodle.Teacher.entity.Assignment;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TAssignmentDetailsController implements Initializable {

    @FXML
    private Label modifyLabel;
    @FXML
    private Label deleteLabel;
    @FXML
    private Label assignName;
    @FXML
    private Label openDate;
    @FXML
    private Label dueDate;
    @FXML
    private Button viewSubmissionBtn;
    @FXML
    private Button gradleBtn;
    @FXML
    private ScrollPane submissionScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleModify() {

    }

    public void handleDelete() {

    }

    public void handleViewSubmission() {

    }

    public void handleGradle() {

    }

    public void setAssignDetails(Assignment assign) {
        this.assignName.setText(assign.getAssignName());
        this.openDate.setText(assign.getOpenDate().toString());
        this.dueDate.setText(assign.getDueDate().toString());
    }

}

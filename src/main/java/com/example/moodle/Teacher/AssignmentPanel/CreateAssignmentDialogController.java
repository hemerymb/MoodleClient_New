package com.example.moodle.Teacher.AssignmentPanel;

import com.example.moodle.dao.AssignmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;

public class CreateAssignmentDialogController {

    @FXML
    private TextField assignmentNameField;

    @FXML
    private DatePicker createdDatePicker;

    @FXML
    private DatePicker limitedDatePicker;

    @FXML
    private TextField courseNameField;

    @FXML
    private TextField statutField;

    @FXML
    private Button createButton;

    private boolean assignmentCreated = false;

    @FXML
    private void handleCreate() {
        String assignmentName = assignmentNameField.getText();
        Date createdDate = Date.valueOf(createdDatePicker.getValue());
        Date limitedDate = Date.valueOf(limitedDatePicker.getValue());
        String courseName = courseNameField.getText();
        String statut = statutField.getText();

        AssignmentDAO.insertAssignment(assignmentName, createdDate, limitedDate, courseName, statut);
        assignmentCreated = true;

        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) createButton.getScene().getWindow();
        stage.close();
    }

    public boolean isAssignmentCreated() {
        return assignmentCreated;
    }
}

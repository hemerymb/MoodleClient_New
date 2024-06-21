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
    private TextField assignNameTextField;

    @FXML
    private DatePicker openDatePicker;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private TextField courseNameTextfiel;

    @FXML
    private Button createBtn;

    @FXML
    private Button cancelBtn;

    private boolean assignmentCreated = false;

    public CreateAssignmentDialogController() {
        // Constructeur par défaut requis pour JavaFX
    }

    @FXML
    private void handleCreate() {
        try {
            String assignmentName = assignNameTextField.getText();
            Date createdDate = Date.valueOf(openDatePicker.getValue());
            Date limitedDate = Date.valueOf(dueDatePicker.getValue());
            String courseName = courseNameTextfiel.getText();

            System.out.println("Inserting assignment: " + assignmentName + ", " + createdDate + ", " + limitedDate + ", " + courseName);

            AssignmentDAO.insertAssignment(assignmentName, createdDate, limitedDate, courseName);
            assignmentCreated = true;

            Stage stage = (Stage) createBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println("Error in handleCreate");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public boolean isAssignmentCreated() {
        return assignmentCreated;
    }
}

package com.example.moodle.Teacher.AssignmentPanel;

import  com.example.moodle.Teacher.AssignmentPanel.Assignment;
import com.example.moodle.dao.AssignmentDAO;
import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import com.example.moodle.Teacher.CoursesPanel.CoursesPanelController;
import com.example.moodle.dao.AssignmentDAO;
import com.example.moodle.dao.CourseDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Date;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
//import java.util.Date;

import java.sql.Date;

import static com.example.moodle.moodleclient.Moodleclient.root;


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

    private TAssignmentPanelController assignPanelController;

    public void setAssignPanelController(TAssignmentPanelController controller) {
        this.assignPanelController = controller;
    }

    public void handleCreate() throws IOException {

        String assignName = assignNameTextField.getText();
        String courseName = courseNameTextfiel.getText();

        LocalDate openLocalDate = openDatePicker.getValue();
        Date openDate = Date.valueOf(openLocalDate.toString());
        LocalDate dueLocalDate = dueDatePicker.getValue();
        Date dueDate = Date.valueOf(dueLocalDate);

        if (assignName.isEmpty() || courseName.isEmpty() || openDate.equals(null) || dueDate.equals(null)) {
            System.out.println("All fields must be filled out!");
        }

        String statut = "in progress";
        if (dueLocalDate.equals(LocalDate.now())) {
            statut = "";
        }
        try {
            AssignmentDAO.insertAssignment(assignName, openDate, dueDate, courseName, statut);
            System.out.println("Assignment created successfully.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentCard.fxml"));
            AnchorPane assignCard = loader.load();

            TAssignmentCardController assignCardController = loader.getController();
            assignCardController.setAssignDetails(new Assignment(assignName, courseName, openLocalDate, dueLocalDate));

            assignPanelController.addAssignCardToPanel(assignCard);
           /* if (assignPanelController != null) {
                assignPanelController.addAssignCardToPanel(assignCard);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();
    }
        @FXML
        private void handleCancel() {
            Stage stage1 = (Stage) cancelBtn.getScene().getWindow();
            stage1.close();
        };

        /*
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane content = contentLoader.load();


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
*/}

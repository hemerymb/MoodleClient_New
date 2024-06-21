package com.example.moodle.Teacher.AssignmentPanel;

import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import com.example.moodle.Teacher.CoursesPanel.CoursesPanelController;
import com.example.moodle.Teacher.entity.Assignment;
import com.example.moodle.dao.AssignmentDAO;
import com.example.moodle.dao.CourseDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private TextField courseNameTextField;
    @FXML
    private DatePicker openDatePicker;
    @FXML
    private DatePicker dueDatePicker;

    private TAssignmentPanelController assignPanelController;

    public void setAssignPanelController(TAssignmentPanelController controller) {
        this.assignPanelController = controller;
    }

    public void handleCreate() throws IOException {

        String assignName = assignNameTextField.getText();
        String courseName = courseNameTextField.getText();

        LocalDate openLocalDate = openDatePicker.getValue();
        Date openDate = Date.valueOf(openLocalDate.toString());
        LocalDate dueLocalDate = dueDatePicker.getValue();
        Date dueDate = Date.valueOf(dueLocalDate);

        if(assignName.isEmpty() || courseName.isEmpty() || openDate.equals(null) || dueDate.equals(null)) {
            System.out.println("All fields must be filled out!");
        }

        String statut = "in progress";
        if(dueLocalDate.equals(LocalDate.now())) {
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

        /*
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane content = contentLoader.load();

        ScrollPane assignSrollPane = (ScrollPane) content.lookup("#assignScrollPane");
        FXMLLoader assignLoader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane assignCard = assignLoader.load();

        assignSrollPane.setContent(assignCard);

        root.setCenter(content);*/
    }

    public void handleCancel() {
        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();
    }

    public void handleCancelEvent() {
        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();
    }

}

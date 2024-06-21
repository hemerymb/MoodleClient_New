package com.example.moodle.Teacher.CoursesPanel;

import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import com.example.moodle.dao.CourseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateCourseDialogController implements Initializable {

    @FXML
    private TextArea descriptionfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField shortnamefield;

    private CoursesPanelController coursesPanelController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCoursesPanelController(CoursesPanelController controller) {
        this.coursesPanelController = controller;
    }

    @FXML
    void createCourse(ActionEvent event) {
        String courseName = namefield.getText();
        String courseAbr = shortnamefield.getText();
        String courseDescription = descriptionfield.getText();

        int nbChapters = 0;
        int nbAssignments = 0;

        if (courseName.isEmpty() || courseAbr.isEmpty() || courseDescription.isEmpty()) {
            System.out.println("All fields must be filled out!");
            return;
        }

        try {
            CourseDAO.insertCourse(courseName, courseAbr, courseDescription, nbChapters, nbAssignments);
            System.out.println("Course created successfully.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/AvailableCourseCard_updated.fxml"));
            Pane courseCard = loader.load();

            AvailableCourseCardController controller = loader.getController();
            controller.setCourseDetails(courseName, courseDescription, nbChapters);

            if (coursesPanelController != null) {
                coursesPanelController.addCourseCard(courseCard);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) namefield.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCancelAction(ActionEvent event) {
        Stage stage = (Stage) namefield.getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleCancelEvent(MouseEvent event) {
        Stage stage = (Stage) namefield.getScene().getWindow();
        stage.close();
    }

    public String getCourseName() {
        return namefield.getText();
    }
}

package com.example.moodle.Teacher.CoursesPanel.DialogWindows;

import com.example.moodle.Teacher.Cards.CourseCard;
import com.example.moodle.Teacher.entity.Course;
import com.example.moodle.Teacher.CoursesPanel.CoursesPanelController;
import com.example.moodle.dao.CourseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCourseDialogController implements Initializable {

    @FXML
    private TextArea descriptionfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField shortnamefield;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void createCourse(ActionEvent event) {

        int nbChapters = 0;
        int nbAssignments = 0;

        if (namefield.getText().isEmpty() || shortnamefield.getText().isEmpty() || descriptionfield.getText().isEmpty()) {
            System.out.println("All fields must be filled out!");
            return;
        }

        try {
            CourseDAO.insertCourse(namefield.getText(), shortnamefield.getText(), descriptionfield.getText(), nbChapters, nbAssignments);
            System.out.println("Course created successfully.");

            FXMLLoader coursesloader = new FXMLLoader(CreateCourseDialogController.class.getResource("/com/example/moodle/FXML/CoursesPanel_updated.fxml"));
            coursesloader.load();
            CoursesPanelController CourseCtrler = coursesloader.getController();
            CourseCtrler.addCourseCard(new CourseCard(new Course(
                    namefield.getText(),
                    descriptionfield.getText(),
                    0
            )));



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

package com.example.moodle.Teacher.CoursesPanel.DialogWindows;

import com.example.moodle.Teacher.Cards.CourseCard;
import com.example.moodle.Teacher.CoursesPanel.CourseViewPanelController;
import com.example.moodle.Teacher.CoursesPanel.CoursesPanelController;
import com.example.moodle.Teacher.entity.Course;
import com.example.moodle.dao.CourseDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class EditCourseDialogController implements Initializable {

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
    void editCourse(ActionEvent event) {

        int nbChapters = 0;
        int nbAssignments = 0;

        if (namefield.getText().isEmpty() || shortnamefield.getText().isEmpty() || descriptionfield.getText().isEmpty()) {
            System.out.println("All fields must be filled out!");
            return;
        }

        try {

            FXMLLoader courseviewloader = new FXMLLoader(CreateCourseDialogController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
            AnchorPane courseview = courseviewloader.load();
            CourseViewPanelController CVController = courseviewloader.getController();

            Label nameView = (Label) courseviewloader.getNamespace().get("coursename");
            TextArea descView = (TextArea) courseviewloader.getNamespace().get("courseDescription");

            nameView.setText(namefield.getText()); descView.setText(descriptionfield.getText());

            CourseDAO.updateCourse(CVController.getCourse().getId(), namefield.getText(), shortnamefield.getText(), descriptionfield.getText(), CVController.getCourse().getNbChapters(), CVController.getCourse().getNbAssignments());
            System.out.println("Course created successfully.");

            FXMLLoader coursesloader = new FXMLLoader(CreateCourseDialogController.class.getResource("/com/example/moodle/FXML/CoursesPanel_updated.fxml"));
            AnchorPane courses = coursesloader.load();

            CoursesPanelController CourseCtrler = coursesloader.getController();
            CourseCtrler.addCourseCard(new CourseCard(new Course(
                    CVController.getCourse().getId(),
                    namefield.getText(),
                    CVController.getCourse().getCourseAbr(),
                    descriptionfield.getText(),
                    CVController.getCourse().getNbChapters(),
                    CVController.getCourse().getNbAssignments()
            )));

            root.setCenter(courseview);

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
}

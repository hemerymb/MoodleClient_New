package com.example.moodle.Teacher.CoursesPanel.DialogWindows;

import com.example.moodle.Teacher.Cards.ChapterCard;
import com.example.moodle.Teacher.Cards.CourseCard;
import com.example.moodle.Teacher.CoursesPanel.CourseViewPanelController;
import com.example.moodle.Teacher.CoursesPanel.CoursesPanelController;
import com.example.moodle.Teacher.entity.Chapter;
import com.example.moodle.Teacher.entity.Course;
import com.example.moodle.dao.ChaptersDAO;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.currentCourse;
import static com.example.moodle.moodleclient.Moodleclient.root;

public class CreateChapterDialogController implements Initializable {


    @FXML
    private TextField namefield;

    @FXML
    private TextField numfield;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void createChapter(ActionEvent event) {

        if (namefield.getText().isEmpty()) {
            System.out.println("New chapter must have a name !");
            return;
        }

        try {
            ChaptersDAO.insertChapter(namefield.getText(), Integer.parseInt(numfield.getText()), "", currentCourse.getId());
            currentCourse.setNbChapters(currentCourse.getNbChapters() + 1);
            CourseDAO.updateCourse(currentCourse.getId(),currentCourse.getCourseName(), currentCourse.getCourseAbr(), currentCourse.getCourseDescription(), currentCourse.getNbChapters(), currentCourse.getNbAssignments());
            System.out.println("Course created successfully.");

            FXMLLoader coursVloader = new FXMLLoader(CreateChapterDialogController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
            AnchorPane coursV = coursVloader.load();

            root.setCenter(coursV);

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

    public String getChapName() {
        return namefield.getText();
    }
}

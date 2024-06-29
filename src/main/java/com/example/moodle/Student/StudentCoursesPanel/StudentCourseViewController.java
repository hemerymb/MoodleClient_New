package com.example.moodle.Student.StudentCoursesPanel;

import com.example.moodle.MainDry.Dry;
import com.example.moodle.Student.Cards.ChapTile;
import com.example.moodle.Student.Entities.Section;
import com.example.moodle.Student.Entities.Course;
import com.example.moodle.dao.SectionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class StudentCourseViewController {
    @FXML
    private Button AssignmentsBtn;

    @FXML
    private Button ChaptersBtn;

    @FXML
    private Button CourseFilesBtn;

    @FXML
    private Button ParticipantsBtn;

    @FXML
    private TextArea courseDescription;

    @FXML
    private Label coursename;

    @FXML
    private GridPane gridpane;

    @FXML
    private VBox leftbtnMenu;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private VBox vboxChapters;

    private Course course;

    @FXML
    void handleAssignmentsBtn(ActionEvent event) {
        vboxChapters.getChildren().clear();
    }

    @FXML
    void handleChaptersBtn(ActionEvent event) {
        if(vboxChapters.getChildren().isEmpty()) {
            ArrayList<Section> sections = SectionDAO.getSections((int) course.getCourseid());
            for(Section section : sections) {
                ChapTile chapTile = new ChapTile(section);
                vboxChapters.getChildren().add(chapTile);
            }
        }
    }

    @FXML
    void handleCourseFilesBtn(ActionEvent event) {
        vboxChapters.getChildren().clear();
    }

    @FXML
    void handleParticipantsBtn(ActionEvent event) {
        vboxChapters.getChildren().clear();
    }

    @FXML
    void handleReturn(MouseEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_CoursesPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void define(String name, String desc) {
        coursename.setText(name);
        courseDescription.setText(desc);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

package com.example.moodle.Student.Cards;

import com.example.moodle.Student.StudentCoursesPanel.Course;
import com.example.moodle.Student.StudentCoursesPanel.StudentAvailableCourseCardController;
import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CourseCard extends Pane {
    private Course course;
    private Pane pane;

    public CourseCard(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(CourseCard.class.getResource("/com/example/moodle/FXML/student_AvailableCourseCard_updated.fxml"));
            this.pane = loader.load();
            StudentAvailableCourseCardController cardController = loader.getController();
            cardController.define(course.getCourseName(), course.getDescription(), course.getNbChapters());
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

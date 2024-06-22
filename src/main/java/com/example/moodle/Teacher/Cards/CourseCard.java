package com.example.moodle.Teacher.Cards;

import com.example.moodle.Teacher.entity.Course;
import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CourseCard extends Pane {
    private Course course;
    private Pane pane;

    public CourseCard(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(CourseCard.class.getResource("/com/example/moodle/FXML/AvailableCourseCard_updated.fxml"));
            this.pane = loader.load();
            AvailableCourseCardController cardController = loader.getController();
            cardController.define(course.getCourseName(), course.getCourseDescription(), course.getNbChapters());
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

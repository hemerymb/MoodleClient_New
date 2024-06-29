package com.example.moodle.Student.StudentCoursesPanel;

import com.example.moodle.Student.Cards.CourseCard;
import com.example.moodle.Student.Entities.Course;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StudentCourseView extends Pane {
    private Pane pane;
    private Course course;

    public StudentCourseView(Course course) {
        try {
            FXMLLoader loader = new FXMLLoader(StudentCourseView.class.getResource("/com/example/moodle/FXML/student_CourseViewPanel_updated.fxml"));
            this.pane = loader.load();
            StudentCourseViewController controller = loader.getController();
            controller.define(course.getFullname(), course.getSummary());
            controller.setCourse(course);
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.moodle.Student.Cards;

import com.example.moodle.Student.StudentCoursesPanel.Assigment;
import com.example.moodle.Student.StudentCoursesPanel.StudentAvailableCourseCardController;
import com.example.moodle.Student.studentAssigmentPanel.studentAssigmentCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class AssigmentCard extends Pane {
    private Assigment assigment;
    private Pane pane;
    public AssigmentCard(Assigment assigment) {
        try {
            FXMLLoader loader = new FXMLLoader(CourseCard.class.getResource("/com/example/moodle/FXML/student_assigmentCard.fxml"));
            this.pane = loader.load();
            studentAssigmentCardController cardController = loader.getController();
            cardController.define(assigment.getAssigmentCourseName(), assigment.getAssigmentName(),assigment.getDeadline());
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

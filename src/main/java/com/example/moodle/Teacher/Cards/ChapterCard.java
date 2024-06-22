package com.example.moodle.Teacher.Cards;

import com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard.AvailableCourseCardController;
import com.example.moodle.Teacher.CoursesPanel.ChapterCard.ChapterCardController;
import com.example.moodle.Teacher.entity.Chapter;
import com.example.moodle.Teacher.entity.Course;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChapterCard extends AnchorPane {
    private Chapter chap;
    private AnchorPane pane;

    public ChapterCard(Chapter chap) {
        try {
            FXMLLoader loader = new FXMLLoader(ChapterCard.class.getResource("/com/example/moodle/FXML/ChapterCard.fxml"));
            this.pane = loader.load();
            ChapterCardController cardController = loader.getController();
            cardController.define(chap.getTitle(), chap.getNum()+"", 0);
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

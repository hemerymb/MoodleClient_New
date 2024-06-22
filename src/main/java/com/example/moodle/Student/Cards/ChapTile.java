package com.example.moodle.Student.Cards;

import com.example.moodle.Student.Entities.Chapter;
import com.example.moodle.Student.StudentCoursesPanel.ChapTileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ChapTile extends Pane {
    private Pane pane;
    private Chapter chapter;

    public ChapTile(Chapter chapter) {
        try {
            FXMLLoader loader = new FXMLLoader(ChapTile.class.getResource("/com/example/moodle/FXML/ChapTile.fxml"));
            this.pane = loader.load();
            // this.pane.setStyle("-fx-margin: 10");
            ChapTileController controller = loader.getController();
            controller.setChapter(chapter);
            controller.define(chapter.getNum(), chapter.getTitle());
            getChildren().add(this.pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.moodle.Teacher.CoursesPanel.ChapterCard;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ChapterCardController implements Initializable {

    @FXML
    private Label FilesNumber;

    @FXML
    private VBox FilesVbox;

    @FXML
    private Label chapterName;

    @FXML
    private Label chapterNum;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void define(String name, String num, int nbfichiers) {
        this.chapterName.setText(name);
        this.chapterNum.setText(num);
        this.chapterNum.setStyle("-fx-text-fill: black");
        this.FilesNumber.setText(nbfichiers+"");
        this.FilesNumber.setStyle("-fx-text-fill: black");
    }
}

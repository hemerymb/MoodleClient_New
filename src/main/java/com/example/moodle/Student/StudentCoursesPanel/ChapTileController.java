package com.example.moodle.Student.StudentCoursesPanel;

import com.example.moodle.Student.Entities.Section;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ChapTileController {
    private Section section;

    @FXML
    private AnchorPane chapContainer;

    @FXML
    private Text numChap;

    @FXML
    private Text titleChap;

    @FXML
    void handleChap(MouseEvent event) {
//        if(this.section.getContent() != null) {
//            try (FileReader reader = new FileReader(this.section.getContent());
//                 BufferedReader br = new BufferedReader(reader)) {
//                String line;
//                while((line = br.readLine()) != null) {
//                    System.out.println(line);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }
    public void define(int num, String title) {
        numChap.setText(num + ".");
        titleChap.setText(title);
    }

    public Section getChapter() {
        return section;
    }

    public void setChapter(Section section) {
        this.section = section;
    }
}

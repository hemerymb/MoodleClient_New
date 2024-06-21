package com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class AvailableCourseCardController implements Initializable{

    @FXML
    private Label courseDesc;

    @FXML
    private Label courseName;

    @FXML
    private Label ChapterNumber;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleCourseView(MouseEvent event) throws IOException {

        FXMLLoader contentLoader = new FXMLLoader(AvailableCourseCardController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);

    }

}

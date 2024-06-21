package com.example.moodle.Teacher.CoursesPanel.AvailableCourseCard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AvailableCourseCardController implements Initializable{

    @FXML
    private Label courseDesc;

    @FXML
    private Label courseName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleCourseView(MouseEvent event) throws IOException {
        FXMLLoader contentLoader = new FXMLLoader(AvailableCourseCardController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);
    }

    public void setCourseDetails(String name, String description, int chapters) {
        this.courseName.setText(name);
        this.courseDesc.setText(description);
        this.ChaptersNumber.setText(String.valueOf(chapters) + " Chapitres");
    }
}

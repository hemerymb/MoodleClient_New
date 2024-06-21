package com.example.moodle.Student.StudentCoursesPanel;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class StudentCoursesPanelController implements Initializable{

    @FXML
    private Label labelNewCourse;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleNewCourse(MouseEvent event) {

    }

}

package com.example.moodle.Teacher.TeacherAssignmentList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;


public class TeacherAssignmentListController implements Initializable {

    @FXML
    private HBox addAsignBox;

    @FXML private ScrollPane assignScrollPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void handleAddAsign() {
        System.out.println("You clicked to add an esignment");
    }
}

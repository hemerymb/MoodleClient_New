package com.example.moodle.Teacher.TeacherAssignmentList;

import java.util.ResourceBundle;

@FXML
private HBox addAsignBox;

@FXML private SrollPaneScrollPane assignScrollPane;

public class TeacherAssignmentListController {

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void handleAddAsign() {
        System.out.println("You clicked to add an esignment");
    }
}

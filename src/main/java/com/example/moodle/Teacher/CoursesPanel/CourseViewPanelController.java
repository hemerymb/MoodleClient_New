package com.example.moodle.Teacher.CoursesPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CourseViewPanelController {

    @FXML
    private Button AssignmentsBtn;

    @FXML
    private Button ChaptersBtn;

    @FXML
    private Button CourseFilesBtn;

    @FXML
    private Label EditCourse;

    @FXML
    private Button ParticipantsBtn;

    @FXML
    private TextArea courseDescription;

    @FXML
    private Label coursename;

    @FXML
    private Label deleteCourse;

    @FXML
    private GridPane gridpane;

    @FXML
    private Label returnArrow;

    @FXML
    private VBox leftbtnMenu;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    void handleAssignmentsBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, AssignmentsBtn);

    }

    @FXML
    void handleChaptersBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, ChaptersBtn);

    }

    @FXML
    void handleCourseFilesBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, CourseFilesBtn);

    }

    @FXML
    void handleDeleteCourse(MouseEvent event) {

    }

    @FXML
    void handleEditCourse(MouseEvent event) {

    }

    @FXML
    void handleParticipantsBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, ParticipantsBtn);

    }

    void selectBtn(VBox VB, Button button) {
        button.getStyleClass().add("focused");
        for (Node node : VB.getChildren()) {
            if(node instanceof Button) {
                Button btn = (Button) node;
                if(!btn.equals(button)) {
                    btn.getStyleClass().remove("focused");
                }
            }
        }
    }

}

package com.example.moodle.Teacher.AssignmentPanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class CreateAssignmentDialogController {
    @FXML
    private TextField assignNameTextField;
    @FXML
    private TextField courseNameTextField;
    @FXML
    private DatePicker openDatePicker;
    @FXML
    private DatePicker dueDatePicker;

    public void handleCreate() throws IOException {
        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();

        String assignName = assignNameTextField.getText();
        //String courseName = courseNameTextField.getText();
        /*
        FXMLLoader contentLoader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane content = contentLoader.load();

        ScrollPane assignSrollPane = (ScrollPane) content.lookup("#assignScrollPane");
        FXMLLoader assignLoader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane assignCard = assignLoader.load();

        assignSrollPane.setContent(assignCard);

        root.setCenter(content);*/
    }

    public void handleCancel() {
        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();
    }

    public void handleCancelEvent() {
        Stage stage = (Stage) assignNameTextField.getScene().getWindow();
        stage.close();
    }

}

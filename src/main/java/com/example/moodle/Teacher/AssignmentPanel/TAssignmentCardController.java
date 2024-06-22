package com.example.moodle.Teacher.AssignmentPanel;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import static com.example.moodle.moodleclient.Moodleclient.root;

public class TAssignmentCardController implements Initializable {
    @FXML
    private Label assignNameLabel;
    @FXML
    private Label courseNameLabel;
    @FXML
    private Button detailsBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void handleDetails() throws IOException {
        System.out.println("You clicked");

        FXMLLoader detailsPaneLoader = new FXMLLoader(getClass().getResource( "/com/example/moodle/FXML/TeacherAssignmentDetails.fxml"));
        AnchorPane detailsPane = detailsPaneLoader.load();

       /*TAssignmentDetailsController detailsController = detailsPaneLoader.getController();
        detailsController.setAssignDetails(assignNameLabel.getText());*/

        root.setCenter(detailsPane);
    }

    public void setAssignDetails(com.example.moodle.Teacher.AssignmentPanel.Assignment assign) {
        this.assignNameLabel.setText(assign.getAssignName());
        this.courseNameLabel.setText(assign.getCourseName());
    }


}

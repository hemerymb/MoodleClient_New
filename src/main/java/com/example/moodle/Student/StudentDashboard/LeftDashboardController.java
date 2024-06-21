package com.example.moodle.Student.StudentDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.root;
import com.example.moodle.MainDry.Dry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class LeftDashboardController implements Initializable{

    @FXML
    private Button Assignmentsbtn;

    @FXML
    private Button Coursesbtn;

    @FXML
    private Button Noveltiesbtn;

    @FXML
    private Button PrivatesFilesbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleAssignmentsbtn(ActionEvent event) {

    }

    @FXML
    void handleCoursesbtn(ActionEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_CoursesPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleExbandLeftMenu(ActionEvent event) {

    }

    @FXML
    void handleNoveltiesbtn(ActionEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_Novelties_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handlePrivatesFilesbtn(ActionEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_PrivatesFiles_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.example.moodle.Student.StudentDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.root;
import com.example.moodle.MainDry.Dry;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class LeftDashboardController implements Initializable{

    @FXML
    private AnchorPane leftmenu;

    @FXML
    private Button Assignmentsbtn;

    @FXML
    private Button Coursesbtn;

    @FXML
    private Button Noveltiesbtn;

    @FXML
    private Button PrivatesFilesbtn;

    @FXML
    private VBox vbox;

    private boolean isMenuExpanded = true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    void handleAssignmentsbtn(ActionEvent event) {
        try {
            selectBtn(vbox, Assignmentsbtn);
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_assigmentPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            HelloController.root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleCoursesbtn(ActionEvent event) {
        try {
            selectBtn(vbox, Coursesbtn);
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_CoursesPanel_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleExbandLeftMenu(ActionEvent event) {
        Timeline time = new Timeline();

        if(!isMenuExpanded){
            for(int i = (int) leftmenu.getMaxWidth(); i >= (int) leftmenu.getMinWidth(); i--){
                KeyFrame keyFrameMin = new KeyFrame(Duration.millis(300),
                        new KeyValue(leftmenu.prefWidthProperty(), i, Interpolator.EASE_BOTH));
                time.getKeyFrames().add(keyFrameMin);
            }
            time.play();
        }else{
            for(int i = (int) leftmenu.getMinWidth(); i <= (int) leftmenu.getMaxWidth(); i++){
                KeyFrame keyFrameMax = new KeyFrame(Duration.millis(300),
                        new KeyValue(leftmenu.prefWidthProperty(), i, Interpolator.EASE_BOTH));
                time.getKeyFrames().add(keyFrameMax);
            }
            time.play();
        }
        isMenuExpanded = !isMenuExpanded;
    }

    @FXML
    void handleNoveltiesbtn(ActionEvent event) {
        try {
            selectBtn(vbox, Noveltiesbtn);
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
            selectBtn(vbox, PrivatesFilesbtn);
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/student_PrivatesFiles_updated.fxml"));
            AnchorPane content = contentLoader.load();
            root.setCenter(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

package com.example.moodle.Teacher.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.moodle.MainDry.Dry;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class LeftDashboardController implements Initializable{

    @FXML
    private Button Assignmentsbtn;

    @FXML
    private Button Calendarbtn;

    @FXML
    private Button Coursesbtn;

    @FXML
    private Button PrivateFolesbtn;

    @FXML
    private AnchorPane leftmenu;

    @FXML
    private VBox vbox;

    private boolean isMenuExpanded = true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectBtn(vbox, Coursesbtn);
    }

    @FXML
    void handleAssignmentsbtn(ActionEvent event) throws IOException {
        selectBtn(vbox, Assignmentsbtn);
        FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/TeacherAssignmentPanel.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);


    }

    @FXML
    void handleCalendarbtn(ActionEvent event) throws IOException {
        selectBtn(vbox, Calendarbtn);

        FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/calendar.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);

    }

    @FXML
    void handleCoursesbtn(ActionEvent event) throws IOException {
        selectBtn(vbox, Coursesbtn);

        FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/CoursePanel_updated.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);

    }

    @FXML
    void handleExpandLeftMenu(ActionEvent event) {
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
    void handlePrivateFilesbtn(ActionEvent event) throws IOException {
        selectBtn(vbox, PrivateFolesbtn);
        FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/privatefiles.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);

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

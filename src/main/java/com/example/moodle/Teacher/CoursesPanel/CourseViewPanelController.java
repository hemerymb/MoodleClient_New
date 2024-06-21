package com.example.moodle.Teacher.CoursesPanel;

import com.example.moodle.MainDry.Dry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.root;

public class CourseViewPanelController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectBtn(leftbtnMenu, ChaptersBtn);

    }

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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/EditCourseDialog.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleParticipantsBtn(ActionEvent event) {
        selectBtn(leftbtnMenu, ParticipantsBtn);

    }

    @FXML
    void handleReturn(MouseEvent event) {
        try {
            FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/CoursesPanel_updated.fxml"));
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

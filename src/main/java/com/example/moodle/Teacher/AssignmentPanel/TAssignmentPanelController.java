package com.example.moodle.Teacher.AssignmentPanel;

import com.example.moodle.Teacher.AssignmentPanel.Assignment;
import com.example.moodle.dao.AssignmentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TAssignmentPanelController {

    @FXML
    private Label newAssignLabel;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane assignScrollPane;

    @FXML
    private GridPane gridpane;

    @FXML
    private void initialize() {
        loadAssignments();
    }

    @FXML
    private void handleNewAssign(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateAssignmentDialog.fxml"));
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


    private void loadAssignments() {
        List<Assignment> assignments = AssignmentDAO.readAssignments();
        gridpane.getChildren().clear();

        for (int i = 0; i < assignments.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/TeacherAssignmentCard.fxml"));
                Parent row = loader.load();

                AssignmentItemController controller = loader.getController();
                controller.setAssignment(assignments.get(i));

                gridpane.add(row, 0, i);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

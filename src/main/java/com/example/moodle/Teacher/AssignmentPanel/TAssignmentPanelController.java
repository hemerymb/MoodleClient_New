package com.example.moodle.Teacher.AssignmentPanel;

//import com.example.moodle.Assignment;
import com.example.moodle.dao.AssignmentDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
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
    private ListView<Assignment> assignmentListView;

    //@Override
    public void initialize(URL url, ResourceBundle rb) {
        loadAssignmentsFromDatabase();
    }

    private void loadAssignmentsFromDatabase() {
        assignmentListView.getItems().clear();
        List<Assignment> assignments = AssignmentDAO.readAssignments();
        assignmentListView.getItems().addAll(assignments);
    }

    @FXML
    void handleNewAssign(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateAssignmentDialog.fxml"));
            Parent root = loader.load();

            CreateAssignmentDialogController controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            // Rafraîchir la liste des assignments après l'ajout
            if (controller.isAssignmentCreated()) {
                loadAssignmentsFromDatabase();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

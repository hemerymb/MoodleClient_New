package com.example.moodle.Dashboard;

import com.example.moodle.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.moodle.HelloApplication.stage;

public class LogOutDialogController implements Initializable {

    @FXML
    private Button logOutbnt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void handleCancelAction(ActionEvent event) {
        Stage stage = (Stage) logOutbnt.getScene().getWindow();
        stage.close();

    }

    @FXML
    void handleCancelEvent(MouseEvent event) {
        Stage stage = (Stage) logOutbnt.getScene().getWindow();
        stage.close();

    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/moodle/FXML/hello-view.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1180, 707);
        stage.setTitle("Moodle Client");
        stage.setScene(scene);
        stage.show();

        Stage postage = (Stage) logOutbnt.getScene().getWindow();
        postage.close();
    }
}

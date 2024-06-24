package com.example.moodle.Login;

import static com.example.moodle.moodleclient.Moodleclient.root;
import static com.example.moodle.moodleclient.Moodleclient.user;

import com.example.moodle.DBConnection;
import com.example.moodle.HelloApplication;
import com.example.moodle.MainDry.Dry;
import com.example.moodle.moodleclient.Moodleclient;
import com.example.moodle.moodleclient.client_moodle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {


    @FXML
    private Label errmsg;

    @FXML
    private Label tryconnect;

    @FXML
    public ImageView image1;

    @FXML
    private JFXTextField username1;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXRadioButton teacher1;

    @FXML
    private JFXRadioButton student1;

    public static boolean isTeacher;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user = new client_moodle();

        this.errmsg.setText("");
        this.errmsg.setVisible(false);
        this.tryconnect.setVisible(false);

    }

    private boolean checkCredentials(String userName, String pass, boolean isTeacher) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) FROM Users WHERE username = ? AND password = ? AND statut = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, userName);
                statement.setString(2, pass);
                statement.setString(3, isTeacher ? "Teacher" : "Student");

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt(1) > 0;
                    }
                }
            }
        } catch (SQLException e) {
            errmsg.setText("Error: " + e.getMessage());
            errmsg.setVisible(true);
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleLoginBtn(ActionEvent event) throws IOException {
        String userName = username1.getText();
        String pass = password1.getText();
        boolean isStudent = student1.isSelected();
        boolean isTeacher = teacher1.isSelected();

        if (userName.isEmpty() || pass.isEmpty() || (!student1.isSelected() && !teacher1.isSelected())) {
            errmsg.setText("You must enter a username, password, and select a status!");
            errmsg.setVisible(true);
            return;
        }

        errmsg.setVisible(false);

        if (checkCredentials(userName, pass, isTeacher)) {
            tryconnect.setText("Connected successfully!");
            tryconnect.setVisible(true);

            user.setUsername(userName);

            if (student1.isSelected()) {

                user.is_Teacher(false);

                HelloController.isTeacher = false;
                root = new BorderPane();
                Dry.showDashboard(root, false);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            } else if (teacher1.isSelected()) {

                user.is_Teacher(true);

                HelloController.isTeacher = true;
                root = new BorderPane();
                Dry.showDashboard(root, true);
                Scene scene = new Scene(root, 1180, 707);

                HelloApplication.stage.setTitle("Moodle Client");
                HelloApplication.stage.setScene(scene);
                HelloApplication.stage.show();
            }
        } else {
            errmsg.setText("User does not exist!");
            errmsg.setVisible(true);
        }
    }
}

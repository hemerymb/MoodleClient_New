package com.example.moodle.Dashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.moodle.Login.HelloController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;


    public class TopDashboardController implements Initializable {

        @FXML
        private Circle connectionIndic;

        @FXML
        private CustomMenuItem editProfileMenu;

        @FXML
        private CustomMenuItem logOutMenu;

        @FXML
        private Label loginIndic;

        @FXML
        private HBox moodleLayout;

        @FXML
        private Label notifIndic;

        @FXML
        private MenuButton profileBtn;

        @FXML
        private ImageView profileImg;

        @FXML
        private ImageView profileImg_in_menu;

        @FXML
        private ProgressBar progressBar;

        @FXML
        private Button syncBtn;

        @FXML
        private ImageView syncImg;

        @FXML
        private CustomMenuItem syncMenu;

        @FXML
        private Label teacherLabel;

        @FXML
        private Tooltip tipIndic;

        @FXML
        private Label username;


        @Override
        public void initialize(URL url, ResourceBundle rb) {
            if (!HelloController.isTeacher) teacherLabel.setText("Student");

        }

        @FXML
        void handleLogOutMenu(ActionEvent event) {
            // Logic for logging out the user
            System.out.println("Log Out Clicked");
        }

        @FXML
        void handleProfileEdit(ActionEvent event) {
            try {
                // Load the FXML for the edit profile dialog
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/EditProfileDialog.fxml"));
                Parent root = loader.load();

                // Create a new stage for the dialog
                Stage stage = new Stage();
                stage.setTitle("Edit Profile");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @FXML
        void handleSyncBtn(ActionEvent event) {
            // Logic for sync button
            System.out.println("Sync Clicked");
        }
    }

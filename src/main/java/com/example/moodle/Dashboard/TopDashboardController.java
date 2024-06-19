package com.example.moodle.Dashboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class TopDashboardController implements Initializable{

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
        
    }

    @FXML
    void handleLogOutMenu(ActionEvent event) {

    }

    @FXML
    void handleProfileEdit(ActionEvent event) {

    }

    @FXML
    void handleSyncBtn(ActionEvent event) {

    }

}

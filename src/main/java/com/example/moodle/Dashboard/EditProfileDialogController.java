package com.example.moodle.Dashboard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class EditProfileDialogController {

    @FXML
    private TextField usernameField;

    @FXML
    private ImageView profileImageView;

    @FXML
    private void handleChangeImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profileImageView.setImage(image);
        }
    }

    @FXML
    private void handleSave() {
        // Logic to save the profile changes
        System.out.println("Save Clicked");
        // Close the dialog
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        // Logic to cancel the changes
        System.out.println("Cancel Clicked");
        // Close the dialog
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}

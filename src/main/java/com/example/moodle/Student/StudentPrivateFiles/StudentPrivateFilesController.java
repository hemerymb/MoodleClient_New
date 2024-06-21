package com.example.moodle.Student.StudentPrivateFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class StudentPrivateFilesController {

    @FXML
    private GridPane gridpane;

    @FXML
    private Label labelNewFile;

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private ListView<filesdetails> fileListView;

    @FXML
    private void initialize() {
        fileListView.setOnMouseClicked(event -> handleFileClick(event));
    }



    @FXML
    void handleNewFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("Fichiers Vidéo", ".mp4", ".avi", "*.mkv");
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Fichiers Image", ".png", ".jpg", ".jpeg", ".gif");
        FileChooser.ExtensionFilter documentFilter = new FileChooser.ExtensionFilter("Fichiers Document", ".pdf", ".doc", ".docx", ".xls", "*.xlsx");

        fileChooser.getExtensionFilters().addAll(videoFilter, imageFilter, documentFilter);

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(((Node) event.getSource()).getScene().getWindow());

        if (selectedFiles != null) {
            for (File file : selectedFiles) {
                String fileName = file.getName();
                long fileSize = file.length();
                String filePath = file.getAbsolutePath();
                String fileType = determineFileType(fileChooser, file);

                filesdetails fileItem = new filesdetails(fileName, fileSize, fileType, filePath);
                fileListView.getItems().add(fileItem);
            }
        }
    }
    private String determineFileType(FileChooser fileChooser, File file) {
        String fileName = file.getName().toLowerCase();
        for (FileChooser.ExtensionFilter filter : fileChooser.getExtensionFilters()) {
            for (String extension : filter.getExtensions()) {
                if (fileName.endsWith(extension.replace("*", "").toLowerCase())) {
                    return filter.getDescription();
                }
            }
        }
        return "Unknown";
    }

    @FXML
    private void handleFileClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            filesdetails selectedFileItem = fileListView.getSelectionModel().getSelectedItem();
            if (selectedFileItem != null) {
                openFile(selectedFileItem.getPath());
            }
        }
    }

    private void openFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

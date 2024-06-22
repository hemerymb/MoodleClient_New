package com.example.moodle.Student.StudentPrivateFiles;

import com.example.moodle.Student.StudentPrivateFiles.PrivateFile1;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.scene.control.ListView;
import javafx.scene.Node;
import java.text.DecimalFormat;
import javafx.util.Callback;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.example.moodle.dao.PrivateFilesDAO;


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
        fileListView.setCellFactory(new Callback<>() {
            @Override
            public ListCell<com.example.moodle.Student.StudentPrivateFiles.filesdetails> call(ListView<com.example.moodle.Student.StudentPrivateFiles.filesdetails> param) {
                return new FileItemCell();
            }
        });

        fileListView.setOnMouseClicked(this::handleFileClick);
        loadPrivateFilesFromDatabase(); // Load private files from database when the application starts
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
                String readableFileSize = readableFileSize(fileSize);
                String filePath = file.getAbsolutePath();
                String fileType = determineFileType(fileChooser, file);

                PrivateFilesDAO.insertPrivateFile(fileName, fileSize, fileType, filePath);

                fileListView.getItems().add(new com.example.moodle.Student.StudentPrivateFiles.filesdetails(fileName, fileType, filePath, readableFileSize));
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

    private String readableFileSize(long size) {
        if (size <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    @FXML
    private void handleFileClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            filesdetails selectedFileItem = fileListView.getSelectionModel().getSelectedItem();
            if (selectedFileItem != null) {
                openFile(selectedFileItem.getfilePath());
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

    private void loadPrivateFilesFromDatabase() {
        List<PrivateFile1> privateFiles = PrivateFilesDAO.readPrivateFiles1();
        for (PrivateFile1 privateFile : privateFiles) {
            String readableFileSize = readableFileSize(privateFile.getFileSize());
            fileListView.getItems().add(new com.example.moodle.Student.StudentPrivateFiles.filesdetails(
                    privateFile.getFileName(),
                    readableFileSize,
                    privateFile.getFileType(),
                    privateFile.getFilePath()
            ));
        }
    }


}

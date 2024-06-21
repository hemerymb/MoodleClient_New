//package com.example.moodle.Privatefiles;
//
//import javafx.fxml.FXML;
//import javafx.stage.FileChooser;
//import javafx.event.ActionEvent;
//import javafx.scene.control.Button;
//import javafx.scene.control.ListCell;
//import javafx.scene.control.ListView;
//import javafx.scene.input.MouseEvent;
//import javafx.util.Callback;
//
//import java.awt.Desktop;
//import java.io.File;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.List;
//import com.example.moodle.dao.PrivateFilesDAO;
//
//
//public class privatefilesController {
//
//    @FXML
//    private Button uploadButton;
//
//    @FXML
//    private ListView<filesdetails> fileListView;
//
//    @FXML
//    private void initialize() {
//        fileListView.setCellFactory(new Callback<>() {
//            @Override
//            public ListCell<filesdetails> call(ListView<filesdetails> param) {
//                return new FileItemCell();
//            }
//        });
//
//        fileListView.setOnMouseClicked(this::handleFileClick);
//        loadPrivateFilesFromDatabase(); // Load private files from database when the application starts
//    }
//
//    @FXML
//    private void onButtonClicked(ActionEvent event) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Choisir des fichiers");
//
//        fileChooser.getExtensionFilters().addAll(
//                new FileChooser.ExtensionFilter("Fichiers Vidéo", "*.mp4", "*.avi", "*.mkv"),
//                new FileChooser.ExtensionFilter("Fichiers Image", "*.png", "*.jpg", "*.jpeg", "*.gif"),
//                new FileChooser.ExtensionFilter("Fichiers Document", "*.pdf", "*.doc", "*.docx", "*.xls", "*.xlsx")
//        );
//
//        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(uploadButton.getScene().getWindow());
//
//        if (selectedFiles != null) {
//            for (File file : selectedFiles) {
//                String fileName = file.getName();
//                long fileSize = file.length();
//                String readableFileSize = readableFileSize(fileSize);
//                String fileType = getFileType(file);
//                String filePath = file.getAbsolutePath();
//
//                // Insert the file into the database
//                PrivateFilesDAO.insertPrivateFile(fileName, fileSize, fileType, filePath);
//
//                // Add the file to the ListView
//                fileListView.getItems().add(new filesdetails(fileName, readableFileSize, fileType, filePath));
//            }
//        }
//    }
//
//    private String getFileType(File file) {
//        String fileName = file.getName().toLowerCase();
//        if (fileName.endsWith(".mp4") || fileName.endsWith(".avi") || fileName.endsWith(".mkv")) {
//            return "Video";
//        } else if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".gif")) {
//            return "Image";
//        } else if (fileName.endsWith(".pdf") || fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
//            return "Document";
//        } else {
//            return "Other";
//        }
//    }
//
//    private String readableFileSize(long size) {
//        if (size <= 0) return "0 B";
//        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
//        int digitGroups = (int) (Math.log10(size) / Math.log(1024));
//        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
//    }
//
//    @FXML
//    private void handleFileClick(MouseEvent event) {
//        if (event.getClickCount() == 2) {
//            filesdetails selectedFileItem = fileListView.getSelectionModel().getSelectedItem();
//            if (selectedFileItem != null) {
//                openFile(selectedFileItem.getFilePath());
//            }
//        }
//    }
//
//    private void openFile(String filePath) {
//        File file = new File(filePath);
//        if (file.exists()) {
//            try {
//                Desktop.getDesktop().open(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void loadPrivateFilesFromDatabase() {
//        List<PrivateFile> privateFiles = PrivateFilesDAO.readPrivateFiles();
//        for (PrivateFile privateFile : privateFiles) {
//            String readableFileSize = readableFileSize(privateFile.getFileSize());
//            fileListView.getItems().add(new filesdetails(
//                    privateFile.getFileName(),
//                    readableFileSize,
//                    privateFile.getFileType(),
//                    privateFile.getFilePath()
//            ));
//        }
//    }
//}

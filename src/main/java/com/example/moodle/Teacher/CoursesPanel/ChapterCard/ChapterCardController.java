package com.example.moodle.Teacher.CoursesPanel.ChapterCard;

import com.example.moodle.Teacher.CoursesPanel.CourseViewPanelController;
import com.example.moodle.Teacher.CoursesPanel.DialogWindows.CreateChapterDialogController;
import com.example.moodle.Teacher.entity.Chapter;
import com.example.moodle.Teacher.entity.DocumentFile;
import com.example.moodle.dao.CourseDAO;
import com.example.moodle.dao.DocumentsFilesDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import javafx.scene.paint.Color;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.moodle.HelloApplication.stage;
import static com.example.moodle.moodleclient.Moodleclient.currentCourse;
import static com.example.moodle.moodleclient.Moodleclient.root;


public class ChapterCardController implements Initializable {

    @FXML
    private Label FilesNumber;

    @FXML
    private VBox FilesVbox;

    @FXML
    private Text chapterName;

    @FXML
    private Label chapterNum;

    public Chapter chapter;

    private int NumFiles;

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/moodleclient";
        String user = "root";
        String password = "681503533";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumFiles = 0;
        FilesVbox.getChildren().clear();

    }

    @FXML
    void addCoursefiles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose files");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All doc files", "*.pdf", "*.PDF", "*.doc", "*.docx", "*.odt", "*.ppt", "*.pptx", "*.xls", "*.xlsx"),
                new FileChooser.ExtensionFilter("PDF files", "*.pdf", "*.PDF"),
                new FileChooser.ExtensionFilter("Word files", "*.doc", "*.docx", "*.odt"),
                new FileChooser.ExtensionFilter("Presentation files", "*.ppt", "*.pptx"),
                new FileChooser.ExtensionFilter("Sheets files", "*.xls", "*.xlsx")
        );

        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(chapterNum.getScene().getWindow());

        if (selectedFiles != null) {

            for (File file : selectedFiles) {
                String fileName = file.getName();
                long fileSize = file.length();
                String readableFileSize = readableFileSize(fileSize);
                String fileType = getFileType(file);
                String filePath = file.getAbsolutePath();

                // Insert the file into the database
                DocumentsFilesDAO.insertDocumentFile(fileName, fileSize, fileType, filePath, chapter.getId());

                NumFiles++;
                FilesVbox.getChildren().add(docLine(fileName, readableFileSize, fileType, filePath));
            }
            FilesNumber.setText(NumFiles+"");
        }
    }

    @FXML
    void deleteChapter(MouseEvent event) throws IOException {

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM documents_files WHERE chapterId = ?")) {
            statement.setInt(1, chapter.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM chapters WHERE id = ?")) {
            pstmt.setInt(1, chapter.getId());
            pstmt.executeUpdate();
            System.out.println("Chapter deleted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        currentCourse.setNbChapters(currentCourse.getNbChapters() - 1);
        CourseDAO.updateCourse(currentCourse.getId(), currentCourse.getCourseName(), currentCourse.getCourseAbr(), currentCourse.getCourseDescription(), currentCourse.getNbChapters(), currentCourse.getNbAssignments());

        FXMLLoader chapL = new FXMLLoader(CreateChapterDialogController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_ChaptersSection.fxml"));
        AnchorPane chapV = chapL.load();

        FXMLLoader coursVloader = new FXMLLoader(CreateChapterDialogController.class.getResource("/com/example/moodle/FXML/CourseViewPanel_updated.fxml"));
        AnchorPane coursV = coursVloader.load();

        CourseViewPanelController coursVPC = coursVloader.getController();

        root.setCenter(coursV);
        coursVPC.setOnCenter(chapV);

    }


    private void loadDocumentsFilesFromDatabase() {
        NumFiles = 0;
        FilesVbox.getChildren().clear();

        String query = "SELECT * FROM documents_files WHERE chapterId = '" + chapter.getId() + "'";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                DocumentFile doc = new DocumentFile(
                    rs.getInt("id"),
                    rs.getString("fileName"),
                    rs.getLong("fileSize"),
                    rs.getString("fileType"),
                    rs.getString("filePath"),
                    rs.getInt("chapterId")
                );

                NumFiles++;
                FilesNumber.setText(NumFiles+"");
                FilesVbox.getChildren().add(docLine(doc.getFileName(), readableFileSize(doc.getFileSize()), doc.getFileType(), doc.getFilePath()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getFileType(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".pdf")) {
            return "PDF Document";
        } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
            return "Word Document";
        } else if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
            return "Presentation Doc";
        } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
            return "Sheet Document";
        } else {
            return "Other";
        }
    }

    private String readableFileSize(long size) {
        if (size <= 0) return "0 B";

        final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
        int unitIndex = 0;

        while (size >= 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }

        return String.format("%.1f %s", (double) size, units[unitIndex]);
    }


    public void define(String name, String num, int nbfichiers, Chapter chap) {
        this.chapterName.setText(name);
        this.chapterNum.setText(num);
        this.chapterNum.setStyle("-fx-text-fill: black");
        this.FilesNumber.setText(nbfichiers+"");
        this.FilesNumber.setStyle("-fx-text-fill: black");
        this.chapter = chap;

        loadDocumentsFilesFromDatabase();
    }

    public HBox docLine(String fileName, String readableFileSize, String fileType, String filePath){
        HBox line = new HBox();
        Region region = new Region();
        Label path = new Label(filePath);
        Text name = new Text(fileName);

        Label type = new Label(fileType);
        type.setStyle("-fx-background-color:#fff0; -fx-text-fill:#000000;");
        type.setPrefWidth(120);

        Label deldoc = new Label("❌");
        deldoc.setStyle("-fx-background-color:#fff0; -fx-text-fill:#ff5e5e;");


        line.setPrefWidth(FilesVbox.getWidth());
        line.setPrefHeight(HBox.USE_COMPUTED_SIZE);
        line.setSpacing(20);

        HBox.setHgrow(region, Priority.ALWAYS);
        region.setPrefHeight(Region.USE_COMPUTED_SIZE);
        path.setMaxWidth(50);
        path.setVisible(false);

        line.getChildren().addAll(name, path, region, new Text(readableFileSize), type, deldoc);

        line.setOnMouseClicked(event -> handleDocClick(line, event));
        deldoc.setOnMouseClicked(event -> deleteDocFile(name, event));

        return line;
    }

    private void deleteDocFile(Text fileName, MouseEvent event) {
        String query = "DELETE FROM documents_files WHERE fileName = ?";
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, fileName.getText());
            pstmt.executeUpdate();
            System.out.println("Document deleted successfully.");

            loadDocumentsFilesFromDatabase();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void handleDocClick(HBox hbox, MouseEvent event) {
        if (event.getClickCount() == 2) {
            Optional<Node> path = hbox.getChildren().stream()
                    .filter(node -> node instanceof Label)
                    .findFirst();
            if(path.isPresent()) {
                Label label = (Label) path.get();
                openFile(label.getText());
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

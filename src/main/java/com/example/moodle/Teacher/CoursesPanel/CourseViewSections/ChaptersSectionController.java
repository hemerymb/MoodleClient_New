package com.example.moodle.Teacher.CoursesPanel.CourseViewSections;

import com.example.moodle.Teacher.Cards.ChapterCard;
import com.example.moodle.Teacher.entity.Chapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.moodle.moodleclient.Moodleclient.currentCourse;

public class ChaptersSectionController implements Initializable {

    @FXML
    private VBox ChaptersVbox;

    @FXML
    private Text chaptersTitle;

    @FXML
    private Button newChapBtn;

    @FXML
    private ScrollPane scrollpane;

    public static List<ChapterCard> Chapterslist;

    private Connection connect() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/moodleclient";
        String user = "root";
        String password = "681503533";
        return DriverManager.getConnection(url, user, password);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Chapterslist = new ArrayList<>();
        ChaptersVbox.getChildren().clear();

        loadChaptersFromDatabase();
    }


    @FXML
    void handleCreateChapter(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/moodle/FXML/CreateChapterDialog.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addChapterCard(ChapterCard chap){

        Chapterslist.add(chap);
        ChaptersVbox.getChildren().clear();

        for (ChapterCard ch : Chapterslist){
            ChaptersVbox.getChildren().add(ch);
        }
    }

    public void loadChaptersFromDatabase() {
        String query = "SELECT * FROM chapters WHERE courseId = '" + currentCourse.getId() + "'";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {

                Chapter chap = new Chapter(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("num"),
                        rs.getString("content"),
                        rs.getInt("courseId")
                );

                ChaptersVbox.getChildren().clear();
                addChapterCard(new ChapterCard(chap));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getChaptersCount() {
        return Chapterslist.size();
    }
}

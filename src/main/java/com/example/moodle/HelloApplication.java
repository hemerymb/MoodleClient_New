package com.example.moodle;

import com.example.moodle.MainDry.Dry;
import static com.example.moodle.moodleclient.Moodleclient.root;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {
    public static Stage stage;
    @Override
    public void start(Stage primarystage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/moodle/FXML/hello-view.fxml"));
        StackPane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1180, 707);
        primarystage.setTitle("Moodle Client");
        primarystage.setScene(scene);
        stage = primarystage;
        primarystage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

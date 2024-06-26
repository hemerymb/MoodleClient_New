package com.example.moodle;

import static com.example.moodle.moodleclient.Moodleclient.root;
import com.example.moodle.MainDry.Dry;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primarystage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/moodle/hello-view.fxml"));
        //StackPane root = fxmlLoader.load();
        root = new BorderPane();
        Dry.showDashboard(root);
        Scene scene = new Scene(root, 1180, 707);
        primarystage.setTitle("Moodle Client");
        primarystage.setScene(scene);
        primarystage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

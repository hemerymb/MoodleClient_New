package com.example.moodle.MainDry;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Dry {
    public Dry(){
        
    }
    
    public static void showDashboard(BorderPane root) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/topDashboard_updated.fxml"));
        
        AnchorPane topMenu = loader.load();
        
        Label username = (Label) loader.getNamespace().get("username");
        //username.setText(moodleclient.Moodleclient.user.getUsername());
        
        root.setTop(topMenu);


        FXMLLoader leftLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/leftDashboard_updated.fxml"));
        AnchorPane leftMenu = leftLoader.load();
        root.setLeft(leftMenu);
        // AnchorPane rightMenu =  (AnchorPane)FXMLLoader.load(Dry.class.getResource("/SDashboard/rightDashboard.fxml"));
        // root.setRight(rightMenu);
        
        //Le leftMenu se charge de mettre à jour le contenu central donc, plus besoin de ceci.
        FXMLLoader contentLoader = new FXMLLoader(Dry.class.getResource("/com/example/moodle/FXML/CoursesPanel_updated.fxml"));
        AnchorPane content = contentLoader.load();
        root.setCenter(content);
    }

}

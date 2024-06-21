package com.example.moodle.Student.StudentCoursesPanel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.moodle.Student.Cards.CourseCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class StudentCoursesPanelController implements Initializable{

    @FXML
    private Label returnArrow;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private GridPane gridpane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // gridpane.setAlignment(Pos.CENTER);
        gridpane.setPadding(new Insets(10));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Architecture des ordinateurs", "Venez découvrir le concept de microcontrôleur et d'électronique", 3));
        courses.add(new Course("Réseaux mobiles et intelligents", "Ne voulez-vous pas savoir comment les réseaux de téléponie fonctionnent", 11));
        courses.add(new Course("Management", "Apprendre à se gérer et gérer les autres", 5));
        courses.add(new Course("Systèmes multi-agents", "La nouvelle évolution de l'IA", 6));
        courses.add(new Course("Analyse numérique", "Parce qu'on ne sait pas encore quand on aura besoin de résoudre des équations", 2));
        courses.add(new Course("Systèmes formels", "Avant de construire des systèmes experts", 3));

        int count = 0;
        for(int i = 0; i < (int)Math.ceil((courses.size() / 4.0)); i++) {
            for(int j = 0; j < 4; j++) {
                gridpane.add(new CourseCard(courses.get(count)), j, i);
                count++;
                if(count == courses.size()) return;
            }
        }
    }

}

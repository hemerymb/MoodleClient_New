import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import main.java.com.example.moodle.Teacher.TeacherAssignmentDetails.TeacherAssignmentDetailsController;

public class TeacherAssignmentCourseCardController {
    @FXML
    private Label assignName;
    @FXML
    private Label courseName;
    @FXML
    private Button detailsBtn;

    public void handleDetails() {
        System.out.println("You clicked");

        AnchorPane detailsPane = FXMLLoader.load(getClass().getResource("/TeacherAssignmentDetailsController/TeacherAssignmentDetails.fxml")) 
    }
}

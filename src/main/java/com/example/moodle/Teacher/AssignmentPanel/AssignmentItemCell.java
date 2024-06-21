package com.example.moodle.Teacher.AssignmentPanel;

//import com.example.moodle.Assignment;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class AssignmentItemCell extends ListCell<Assignment> {

    private HBox content;
    private Text assignmentName;
    private Text createdDate;
    private Text limitedDate;
    private Text courseName;
    private Text statut;

    public AssignmentItemCell() {
        super();
        assignmentName = new Text();
        createdDate = new Text();
        limitedDate = new Text();
        courseName = new Text();
        statut = new Text();
        content = new HBox(10, assignmentName, createdDate, limitedDate, courseName, statut);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Assignment item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            assignmentName.setText(item.getAssignmentName());
            createdDate.setText(item.getCreatedDate().toString());
            limitedDate.setText(item.getLimitedDate().toString());
            courseName.setText(item.getCourseName());
            statut.setText(item.getStatut());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}

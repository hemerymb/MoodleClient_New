package com.example.moodle.Student.StudentPrivateFiles;

import com.example.moodle.Student.StudentPrivateFiles.filesdetails;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FileItemCell extends ListCell<com.example.moodle.Student.StudentPrivateFiles.filesdetails> {

    private HBox content;
    private Text fileName;
    private Text fileSize;
    private Text fileType;

    public FileItemCell() {
        super();
        fileName = new Text();
        fileSize = new Text();
        fileType = new Text();
        content = new HBox(10, fileName, fileSize, fileType);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(filesdetails item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            fileName.setText(item.getfileName());
            fileSize.setText(item.getfileSize());
            fileType.setText(item.getfileType());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}


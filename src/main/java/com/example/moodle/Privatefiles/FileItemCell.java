package com.example.moodle.Privatefiles;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FileItemCell extends ListCell<filesdetails> {

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
            fileName.setText(item.getFileName());
            fileSize.setText(item.getFileSize());
            fileType.setText(item.getFileType());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}

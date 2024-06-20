package com.example.moodle.Privatefiles;

import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class FileItemCell extends ListCell<filesdetails> {

    private HBox content;
    private Text name;
    private Text size;
    private Text type;

    public FileItemCell() {
        super();
        name = new Text();
        size = new Text();
        type = new Text();
        content = new HBox(10, name, size, type);
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(filesdetails item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            name.setText(item.getName());
            size.setText(item.getSize());
            type.setText(item.getType());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }
}

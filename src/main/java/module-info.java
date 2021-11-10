module de.rabitem.evluationtool {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.fasterxml.jackson.databind;

    opens de.rabitem.evaluationtool to javafx.fxml;
    exports de.rabitem.evaluationtool;
    exports de.rabitem.evaluationtool.entity;
}
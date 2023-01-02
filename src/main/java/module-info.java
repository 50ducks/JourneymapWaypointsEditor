module com.jwe.app.journeymapwaypointseditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires org.apache.commons.io;


    opens com.jwe.ui to javafx.fxml;
    exports com.jwe.ui;
}
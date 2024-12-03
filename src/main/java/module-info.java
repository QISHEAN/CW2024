module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.media;

    opens com.example.demo.controller to javafx.fxml;  // Open to javafx.fxml for FXML access
    exports com.example.demo.controller;
    exports com.example.demo.level;
    exports com.example.demo.levelview;
    exports com.example.demo.actor;
    exports com.example.demo.projectiles;
    exports com.example.demo.managers;
    exports com.example.demo.ui;  // Add this line to export the controller package to javafx.graphics
}

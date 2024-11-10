module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo.controller to javafx.fxml;  // Open to javafx.fxml for FXML access
    exports com.example.demo;  // Export your main application package
    exports com.example.demo.controller;  // Add this line to export the controller package to javafx.graphics
}

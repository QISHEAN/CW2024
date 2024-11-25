package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.util.logging.*;

public class MenuController {

    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

    static {
        // Remove default handlers to prevent duplicate logs
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        // Create a new ConsoleHandler with the custom formatter
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter());
        handler.setLevel(Level.ALL); // Set desired level

        // Add the handler to the root logger
        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.ALL); // Set global logging level
    }

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    // Called when Start Game button is clicked
    @FXML
    private void handleStartButton() {
        LOGGER.info("Start Game button clicked");
        try {
            Controller gameController = new Controller(stage);
            gameController.launchGame();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while launching the game", e);
        }
    }

    // Called when Exit button is clicked
    @FXML
    private void handleExitButton() {
        LOGGER.info("Exit button clicked");
        System.exit(0);
    }
}

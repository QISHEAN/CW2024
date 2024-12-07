package com.example.demo.controller;

import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Label titleLabel;
    public Button startButton;
    public Button endlessModeButton;
    public Button leaderboardButton;
    public Button soundSettingsButton;
    public Button exitButton;

    private Stage stage;
    private SoundManager soundManager;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    // Called when Start Game button is clicked
    @FXML
    private void handleStartButton() {
        LOGGER.info("Start Game button clicked");
        try {
            Controller gameController = new Controller(stage, soundManager);
            gameController.launchGame();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while launching the game", e);
        }
    }

    // Called when Endless Mode button is clicked
    @FXML
    private void startEndlessMode() {
        LOGGER.info("Endless Mode button clicked");
        try {
            EndlessController endlessController = new EndlessController(stage, soundManager);
            endlessController.startEndlessMode();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while starting endless mode", e);
        }
    }

    // Called when Leaderboard button is clicked
    @FXML
    private void showLeaderboard() {
        LOGGER.info("Leaderboard button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaderboard.fxml"));
            Parent root = loader.load();

            LeaderboardController controller = loader.getController();
            controller.setSoundManager(soundManager); // If needed
            Scene scene = new Scene(root, 1300, 750);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading Leaderboard.fxml", e);
            // Optionally, show an alert to the user
        }
    }

    // Called when Sound Settings button is clicked
    @FXML
    private void openSoundSettings() {
        LOGGER.info("Sound Settings button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SoundSettings.fxml"));
            Parent root = loader.load();

            SoundSettingsController controller = loader.getController();
            controller.setSoundManager(soundManager);

            Stage settingsStage = new Stage();
            settingsStage.setTitle("Sound Settings");
            settingsStage.setScene(new Scene(root));
            settingsStage.initOwner(stage); // Sets the main stage as the owner
            settingsStage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading SoundSettings.fxml", e);
            // Optionally, show an alert to the user
        }
    }

    // Called when Exit button is clicked
    @FXML
    private void handleExitButton() {
        LOGGER.info("Exit button clicked");
        Platform.exit(); // Closes all stages and ends the JavaFX application.
    }
}

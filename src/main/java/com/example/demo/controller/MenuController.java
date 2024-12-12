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

/**
 * Controller for the main menu screen.
 * Handles user interactions with menu buttons and navigation to other parts of the application.
 */
public class MenuController {

    //Logger for debugging and tracking events in the MenuController.
    private static final Logger LOGGER = Logger.getLogger(MenuController.class.getName());

    static {
        // Configure logging: remove default handlers and add a custom ConsoleHandler.
        Logger rootLogger = Logger.getLogger("");
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomFormatter()); // Use a custom formatter for logs.
        handler.setLevel(Level.ALL); // Enable all log levels.

        rootLogger.addHandler(handler);
        rootLogger.setLevel(Level.ALL); // Set global logging level.
    }

    //FXML-bound UI elements
    public Label titleLabel;
    public Button startButton;
    public Button endlessModeButton;
    public Button leaderboardButton;
    public Button soundSettingsButton;
    public Button exitButton;

    //Stage and sound manager references
    private Stage stage;
    private SoundManager soundManager;

    /**
     * Sets the primary stage for this controller.
     *
     * @param stage The primary stage of the application.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the SoundManager for managing audio in the application.
     *
     * @param soundManager The SoundManager instance.
     */
    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    /**
     * Handles the "Start Game" button click.
     * Launches the game by initializing the main game controller.
     */
    @FXML
    protected void handleStartButton() {
        LOGGER.info("Start Game button clicked");
        try {
            Controller gameController = new Controller(stage, soundManager);
            gameController.launchGame();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while launching the game", e);
        }
    }

    /**
     * Handles the "Endless Mode" button click.
     * Transitions to endless mode gameplay using the EndlessController.
     */
    @FXML
    protected void startEndlessMode() {
        LOGGER.info("Endless Mode button clicked");
        try {
            EndlessController endlessController = new EndlessController(stage, soundManager);
            endlessController.startEndlessMode();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An exception occurred while starting endless mode", e);
        }
    }

    /**
     * Handles the "Leaderboard" button click.
     * Loads and displays the leaderboard screen.
     */
    @FXML
    protected void showLeaderboard() {
        LOGGER.info("Leaderboard button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaderboard.fxml"));
            Parent root = loader.load();

            LeaderboardController controller = loader.getController();
            controller.setSoundManager(soundManager); //Pass the sound manager if required.
            Scene scene = new Scene(root, 1300, 750);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading Leaderboard.fxml", e);
            //Optionally, show an alert to the user.
        }
    }

    /**
     * Handles the "Sound Settings" button click.
     * Opens the sound settings screen in a new stage.
     */
    @FXML
    protected void openSoundSettings() {
        LOGGER.info("Sound Settings button clicked");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SoundSettings.fxml"));
            Parent root = loader.load();

            SoundSettingsController controller = loader.getController();
            controller.setSoundManager(soundManager);

            Stage settingsStage = new Stage();
            settingsStage.setTitle("Sound Settings");
            settingsStage.setScene(new Scene(root));
            settingsStage.initOwner(stage); // Set the main stage as the owner for the settings stage.
            settingsStage.show();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error loading SoundSettings.fxml", e);
            //Optionally, show an alert to the user.
        }
    }

    /**
     * Handles the "Exit" button click.
     * Exits the application by stopping all JavaFX stages.
     */
    @FXML
    protected void handleExitButton() {
        LOGGER.info("Exit button clicked");
        Platform.exit(); //Exits the JavaFX application.
    }
}

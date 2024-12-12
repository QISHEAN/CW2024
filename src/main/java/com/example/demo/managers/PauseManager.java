package com.example.demo.managers;

import com.example.demo.controller.MenuController;
import com.example.demo.controller.PauseMenuController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the pause functionality of the game, including the pause menu and related actions.
 * Handles pausing, resuming, restarting, and navigating back to the main menu.
 */
public class PauseManager {
    private static final Logger logger = Logger.getLogger(PauseManager.class.getName());

    private final Scene scene; // Scene to attach the pause key handler.
    private final Group root; // Root group to manage game elements.
    private final Stage stage; // Main game stage.
    private final SoundManager soundManager; // Manager for handling sound.
    private final Runnable pauseAction; // Action to execute when pausing the game.
    private final Runnable resumeAction; // Action to execute when resuming the game.
    private final Runnable restartAction; // Action to execute when restarting the game.
    private PauseMenuController pauseMenuController; // Controller for the pause menu.
    private boolean isPaused = false; // Tracks the pause state of the game.

    /**
     * Constructor to initialize the `PauseManager`.
     *
     * @param scene          The game scene to attach input handling.
     * @param root           The root group for managing game elements.
     * @param stage          The main stage of the game.
     * @param soundManager   The sound manager for controlling background music and sound effects.
     * @param pauseAction    Action to execute when pausing the game.
     * @param resumeAction   Action to execute when resuming the game.
     * @param restartAction  Action to execute when restarting the game.
     */
    public PauseManager(Scene scene, Group root, Stage stage, SoundManager soundManager,
                        Runnable pauseAction, Runnable resumeAction, Runnable restartAction) {
        this.scene = scene;
        this.root = root;
        this.stage = stage;
        this.soundManager = soundManager;
        this.pauseAction = pauseAction;
        this.resumeAction = resumeAction;
        this.restartAction = restartAction;

        initializePauseMenu();
        initializePauseHandler();
    }

    /**
     * Initializes the pause menu by loading it from an FXML file and setting its actions.
     */
    private void initializePauseMenu() {
        try {
            URL resource = getClass().getResource("/PauseMenu.fxml");
            if (resource == null) {
                logger.log(Level.SEVERE, "PauseMenu.fxml not found!");
                return;
            }
            FXMLLoader loader = new FXMLLoader(resource);
            Parent pauseRoot = loader.load();
            pauseMenuController = loader.getController();
            pauseMenuController.setActions(
                    this::resumeGame, // Resume action.
                    this::restart, // Restart action.
                    this::exitToMainMenu // Exit to main menu action.
            );
            pauseMenuController.setPauseRoot(pauseRoot);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize Pause Menu", e);
        }
    }

    /**
     * Attaches the pause handler to the scene, allowing the ESC key to toggle the pause menu.
     */
    public void initializePauseHandler() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler); // Attach the pause handler.
    }

    /**
     * Toggles the game's pause state when the ESC key is pressed.
     */
    private final EventHandler<KeyEvent> pauseHandler = e -> {
        if (e.getCode() == KeyCode.ESCAPE) {
            e.consume(); // Prevent the event from propagating.
            if (isPaused) {
                resumeGame(); // Resume the game if currently paused.
            } else {
                pauseGame(); // Pause the game if not currently paused.
            }
        }
    };

    /**
     * Pauses the game by executing the pause action and displaying the pause menu.
     */
    private void pauseGame() {
        pauseAction.run(); // Execute the custom pause action.
        if (pauseMenuController != null) {
            root.getChildren().add(pauseMenuController.getPauseRoot()); // Display the pause menu.
        }
        isPaused = true;
        logger.info("Game paused.");
    }

    /**
     * Resumes the game by executing the resume action and removing the pause menu.
     */
    private void resumeGame() {
        resumeAction.run(); // Execute the custom resume action.
        removePauseMenu(); // Remove the pause menu from the scene.
        isPaused = false;
        root.requestFocus(); // Return focus to the game.
        logger.info("Game resumed.");
    }

    /**
     * Removes the pause menu from the root group if it is present.
     */
    private void removePauseMenu() {
        if (pauseMenuController != null && pauseMenuController.getPauseRoot() != null) {
            root.getChildren().remove(pauseMenuController.getPauseRoot()); // Remove the pause menu.
        }
    }

    /**
     * Exits the game to the main menu.
     * Stops the game, switches to the main menu scene, and cleans up the pause menu.
     */
    public void exitToMainMenu() {
        Platform.runLater(() -> {
            try {
                pauseAction.run(); // Stop the game.
                soundManager.stopBackgroundMusic(); // Stop the background music.

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
                Parent menuRoot = loader.load();
                MenuController menuController = loader.getController();
                menuController.setStage(stage);
                menuController.setSoundManager(soundManager);

                Scene menuScene = new Scene(menuRoot, 1300, 750);
                stage.setScene(menuScene);

                removePauseMenu(); // Clean up the pause menu.
                isPaused = false;
                logger.info("Exited to Main Menu.");
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to exit to main menu", e);
            }
        });
    }

    /**
     * Checks if the game is currently paused.
     *
     * @return True if the game is paused, false otherwise.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Restarts the game by executing the restart action and removing the pause menu.
     */
    protected void restart() {
        logger.info("Restarting the game via PauseMenu.");
        if (restartAction != null) {
            restartAction.run(); // Execute the custom restart action.
        }
        removePauseMenu(); // Remove the pause menu after restarting.
        isPaused = false;
    }
}

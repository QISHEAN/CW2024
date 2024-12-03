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

public class PauseManager {
    private static final Logger logger = Logger.getLogger(PauseManager.class.getName());

    private final Scene scene;
    private final Group root;
    private final Stage stage;
    private final SoundManager soundManager;
    private final Runnable pauseAction;
    private final Runnable resumeAction;
    private final Runnable restartAction;
    private PauseMenuController pauseMenuController;
    private boolean isPaused = false;

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

    private void initializePauseMenu() {
        try {
            URL resource = getClass().getResource("/PauseMenu.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            Parent pauseRoot = loader.load();
            pauseMenuController = loader.getController();
            pauseMenuController.setActions(
                    this::resumeGame,
                    restartAction,
                    this::exitToMainMenu // Use method from PauseManager
            );
            pauseMenuController.setPauseRoot(pauseRoot);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to initialize Pause Menu", e);
        }
    }


    public void initializePauseHandler() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, pauseHandler);
    }

    private final EventHandler<KeyEvent> pauseHandler = e -> {
        if (e.getCode() == KeyCode.ESCAPE) {
            e.consume(); // Prevent the event from propagating
            if (isPaused) {
                resumeGame();
            } else {
                pauseGame();
            }
        }
    };

    private void pauseGame() {
        pauseAction.run(); // Call the pause action (e.g., stop timelines)
        if (pauseMenuController != null) {
            root.getChildren().add(pauseMenuController.getPauseRoot());
        }
        isPaused = true;
    }

    private void resumeGame() {
        resumeAction.run(); // Call the resume action (e.g., restart timelines)
        if (pauseMenuController != null) {
            root.getChildren().remove(pauseMenuController.getPauseRoot());
        }
        isPaused = false;
        root.requestFocus(); // Ensure the scene regains focus
    }

    public void exitToMainMenu() {
        Platform.runLater(() -> {
            try {
                // Stop the game
                pauseAction.run(); // Stop timelines, etc.

                // Load the main menu
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
                Parent root = loader.load();
                MenuController menuController = loader.getController();
                menuController.setStage(stage);
                menuController.setSoundManager(soundManager); // Pass SoundManager

                // Set the new scene
                Scene scene = new Scene(root, 1300, 750);
                stage.setScene(scene);

                // Clean up pause menu
                isPaused = false;
                if (pauseMenuController != null) {
                    this.root.getChildren().remove(pauseMenuController.getPauseRoot());
                }
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to exit to main menu", e);
            }
        });
    }

    public boolean isPaused() {
        return isPaused;
    }
}

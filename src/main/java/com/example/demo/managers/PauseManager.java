package com.example.demo.managers;

import com.example.demo.controller.PauseMenuController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PauseManager {
    private static final Logger logger = Logger.getLogger(PauseManager.class.getName());

    private final Scene scene;
    private boolean isPaused = false;
    private final Runnable pauseAction;
    private final Runnable resumeAction;
    private final Group root;
    private PauseMenuController pauseMenuController;

    public PauseManager(Scene scene, Group root, Runnable pauseAction, Runnable resumeAction,
                        Runnable mainMenuAction, Runnable restartAction) {
        this.scene = scene;
        this.root = root;
        this.pauseAction = pauseAction;
        this.resumeAction = resumeAction;

        initializePauseMenu(restartAction, mainMenuAction);
        initializePauseHandler();
    }

    private void initializePauseMenu(Runnable restartAction, Runnable mainMenuAction) {
        try {
            URL resource = getClass().getResource("/PauseMenu.fxml");
            FXMLLoader loader = new FXMLLoader(resource);
            loader.load();
            pauseMenuController = loader.getController();
            pauseMenuController.setActions(
                    this::resumeGame,
                    restartAction,
                    mainMenuAction
            );
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

    public boolean isPaused() {
        return isPaused;
    }
}

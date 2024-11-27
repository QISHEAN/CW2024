package com.example.demo.managers;

import com.example.demo.controller.PauseMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

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
            URL resource = PauseManager.class.getResource("/PauseMenu.fxml");
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
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ESCAPE) {
                if (isPaused) {
                    resumeGame();
                } else {
                    pauseGame();
                }
            }
        });
    }

    private void pauseGame() {
        pauseAction.run();
        if (pauseMenuController != null) {
            root.getChildren().add(pauseMenuController.getPauseRoot());
        }
        isPaused = true;
    }

    private void resumeGame() {
        resumeAction.run();
        if (pauseMenuController != null) {
            root.getChildren().remove(pauseMenuController.getPauseRoot());
        }
        isPaused = false;
    }

    public boolean isPaused() {
        return isPaused;
    }
}

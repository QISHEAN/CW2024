package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;

public class PauseMenuController {

    @FXML
    private Parent pauseRoot; // The root node of the pause menu FXML

    private Runnable resumeAction;
    private Runnable restartAction;
    private Runnable mainMenuAction;

    public void setActions(Runnable resumeAction, Runnable restartAction, Runnable mainMenuAction) {
        this.resumeAction = resumeAction;
        this.restartAction = restartAction;
        this.mainMenuAction = mainMenuAction;
    }

    public Parent getPauseRoot() {
        return pauseRoot;
    }

    @FXML
    private void onResumeClicked() {
        if (resumeAction != null) {
            resumeAction.run();
        }
    }

    @FXML
    private void onRestartClicked() {
        if (restartAction != null) {
            restartAction.run();
        }
    }

    @FXML
    private void onExitToMainMenuClicked() {
        if (mainMenuAction != null) {
            mainMenuAction.run();
        }
    }
}
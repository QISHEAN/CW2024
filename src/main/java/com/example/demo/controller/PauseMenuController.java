package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Controller for the Pause Menu screen.
 * Handles interactions within the pause menu, such as resuming, restarting the game, or exiting to the main menu.
 */
public class PauseMenuController {

    //UI elements bound to the FXML file.
    public Label pauseTitleLabel;
    public Button resumeButton;
    public Button restartButton;
    public Button exitToMainMenuButton;

    //The root node of the pause menu FXML.
    @FXML
    private Parent pauseRoot;

    //Actions to be executed for each button's functionality.
    private Runnable resumeAction;
    private Runnable restartAction;
    private Runnable mainMenuAction;

    /**
     * Sets the actions to be executed when the buttons are clicked.
     *
     * @param resumeAction   The action to execute when the "Resume" button is clicked.
     * @param restartAction  The action to execute when the "Restart" button is clicked.
     * @param mainMenuAction The action to execute when the "Exit to Main Menu" button is clicked.
     */
    public void setActions(Runnable resumeAction, Runnable restartAction, Runnable mainMenuAction) {
        this.resumeAction = resumeAction;
        this.restartAction = restartAction;
        this.mainMenuAction = mainMenuAction;
    }

    /**
     * Sets the root node of the pause menu FXML.
     *
     * @param pauseRoot The root node of the pause menu.
     */
    public void setPauseRoot(Parent pauseRoot) {
        this.pauseRoot = pauseRoot;
    }

    /**
     * Retrieves the root node of the pause menu FXML.
     *
     * @return The root node of the pause menu.
     */
    public Parent getPauseRoot() {
        return pauseRoot;
    }

    /**
     * Called when the "Resume" button is clicked.
     * Executes the resume action if it is defined.
     */
    @FXML
    protected void onResumeClicked() {
        if (resumeAction != null) {
            resumeAction.run();
        }
    }

    /**
     * Called when the "Restart" button is clicked.
     * Executes the restart action if it is defined.
     */
    @FXML
    protected void onRestartClicked() {
        if (restartAction != null) {
            restartAction.run();
        }
    }

    /**
     * Called when the "Exit to Main Menu" button is clicked.
     * Executes the main menu action if it is defined.
     */
    @FXML
    protected void onExitToMainMenuClicked() {
        if (mainMenuAction != null) {
            mainMenuAction.run();
        }
    }
}

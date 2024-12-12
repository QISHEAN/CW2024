package com.example.demo.controller;

import com.example.demo.level.EndlessMode;
import com.example.demo.managers.SoundManager;
import javafx.stage.Stage;

/**
 * Controller for managing the Endless Mode of the game.
 * Responsible for initializing and transitioning to the endless gameplay mode.
 */
public class EndlessController {

    //The primary stage for the application.
    private final Stage stage;

    //Manages sound effects and background music during the game.
    private final SoundManager soundManager;

    /**
     * Constructor to initialize the EndlessController.
     *
     * @param stage        The primary stage of the application where the scene is displayed.
     * @param soundManager The SoundManager for controlling game audio.
     */
    public EndlessController(Stage stage, SoundManager soundManager) {
        this.stage = stage;
        this.soundManager = soundManager;
    }

    /**
     * Initializes and starts the endless mode game.
     * Creates an instance of EndlessMode, sets up the scene, and begins gameplay.
     */
    public void startEndlessMode() {
        //Create an instance of EndlessMode with the provided sound manager and stage.
        EndlessMode endlessMode = new EndlessMode(soundManager, stage);

        //Set the stage to display the endless mode scene.
        stage.setScene(endlessMode.initializeScene());

        //Show the stage to make the game visible to the player.
        stage.show();

        //Start the endless mode gameplay.
        endlessMode.startGame();
    }
}

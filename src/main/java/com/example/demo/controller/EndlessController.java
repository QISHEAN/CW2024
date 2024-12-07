package com.example.demo.controller;

import com.example.demo.level.EndlessMode;
import com.example.demo.managers.SoundManager;
import javafx.stage.Stage;

/**
 * Controller for the endless mode.
 */
public class EndlessController {

    private final Stage stage;
    private final SoundManager soundManager;

    public EndlessController(Stage stage, SoundManager soundManager) {
        this.stage = stage;
        this.soundManager = soundManager;
    }

    /**
     * Initializes and starts the endless mode game.
     */
    public void startEndlessMode() {
        EndlessMode endlessMode = new EndlessMode(soundManager, stage);
        stage.setScene(endlessMode.initializeScene());
        stage.show();
        endlessMode.startGame();
    }
}

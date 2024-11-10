package com.example.demo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {


    private Stage stage;
    @FXML
    private Button startButton;

    @FXML
    private Button exitButton;

    public void setStage(Stage stage){this.stage=stage;}
    // Called when Start Game button is clicked
    @FXML
    private void handleStartButton() {
        try{
            System.out.println("Start Game button clicked");
            Controller gameController = new Controller(stage);
            gameController.launchGame();
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

    // Called when Exit button is clicked
    @FXML
    private void handleExitButton() {
        System.out.println("Exit button clicked");
        System.exit(0);
    }
}

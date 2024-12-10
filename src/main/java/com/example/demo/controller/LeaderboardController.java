package com.example.demo.controller;

import com.example.demo.managers.LeaderboardManager;
import com.example.demo.managers.SoundManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class LeaderboardController {

    public Button backButton;
    @FXML
    protected ListView<String> leaderboardListView;

    private SoundManager soundManager;

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    /**
     * Initializes the leaderboard by loading the top scores.
     */
    @FXML
    public void initialize() {
        loadTopScores();
    }

    /**
     * Loads the top scores into the ListView.
     */
    private void loadTopScores() {
        List<Integer> topScores = LeaderboardManager.getTopScores(5); // Get top 5 scores
        leaderboardListView.getItems().clear();

        if (topScores.isEmpty()) {
            leaderboardListView.getItems().add("No scores yet.");
            return;
        }

        int rank = 1;
        for (int score : topScores) {
            leaderboardListView.getItems().add("Rank " + rank + ": " + score);
            rank++;
        }
    }

    @FXML
    protected void backToMenu() {
        try {
            URL menuFxml = getClass().getResource("/MenuScreen.fxml");
            if (menuFxml == null) {
                System.err.println("Could not find MenuScreen.fxml");
            }

            FXMLLoader loader = new FXMLLoader(menuFxml);
            Parent root = loader.load();
            MenuController menuController = loader.getController();

            Stage currentStage = (Stage) leaderboardListView.getScene().getWindow();
            menuController.setStage(currentStage);
            menuController.setSoundManager(soundManager);

            Scene scene = new Scene(root, 1300, 750);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating back to the menu. Please try again.");
            alert.showAndWait();
        }
    }
}

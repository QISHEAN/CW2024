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

/**
 * Controller for managing the leaderboard screen.
 * Handles displaying top scores and navigation back to the main menu.
 */
public class LeaderboardController {

    //Button for navigating back to the main menu.
    public Button backButton;

    //ListView for displaying leaderboard scores.
    @FXML
    protected ListView<String> leaderboardListView;

    //Reference to the sound manager for managing audio effects and music.
    private SoundManager soundManager;

    /**
     * Sets the SoundManager instance for managing game audio.
     *
     * @param soundManager The SoundManager instance.
     */
    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
    }

    /**
     * Initializes the leaderboard screen by loading the top scores into the ListView.
     * This method is automatically invoked by JavaFX when the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        loadTopScores();
    }

    /**
     * Loads the top scores from the LeaderboardManager and displays them in the ListView.
     * If there are no scores, a placeholder message is displayed.
     */
    private void loadTopScores() {
        //Retrieve the top 5 scores from the leaderboard.
        List<Integer> topScores = LeaderboardManager.getTopScores(5);

        //Clear the ListView before adding new items.
        leaderboardListView.getItems().clear();

        //Display a placeholder if no scores are available.
        if (topScores.isEmpty()) {
            leaderboardListView.getItems().add("No scores yet.");
            return;
        }

        //Add the scores to the ListView with ranking.
        int rank = 1;
        for (int score : topScores) {
            leaderboardListView.getItems().add("Rank " + rank + ": " + score);
            rank++;
        }
    }

    /**
     * Handles the back button action to navigate back to the main menu.
     * Loads the main menu FXML file and switches the scene.
     */
    @FXML
    protected void backToMenu() {
        try {
            //Load the main menu FXML file.
            URL menuFxml = getClass().getResource("/MenuScreen.fxml");
            if (menuFxml == null) {
                System.err.println("Could not find MenuScreen.fxml");
                return;
            }

            FXMLLoader loader = new FXMLLoader(menuFxml);
            Parent root = loader.load();

            //Get the MenuController instance and configure it.
            MenuController menuController = loader.getController();
            Stage currentStage = (Stage) leaderboardListView.getScene().getWindow();
            menuController.setStage(currentStage);
            menuController.setSoundManager(soundManager);

            //Switch the scene to the main menu.
            Scene scene = new Scene(root, 1300, 750);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            //Show an alert if there is an error navigating back to the main menu.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Navigation Error");
            alert.setContentText("An error occurred while navigating back to the menu. Please try again.");
            alert.showAndWait();
        }
    }
}

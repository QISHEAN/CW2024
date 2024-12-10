package com.example.demo.controller;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.LeaderboardManager;
import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LeaderboardControllerTest extends JavaFXInitializer {

    private Stage testStage;
    private LeaderboardController leaderboardController;
    private SoundManager mockSoundManager;
    private MockedStatic<LeaderboardManager> mockedLeaderboardManager;

    @BeforeEach
    void setUp() throws Exception {
        mockedLeaderboardManager = Mockito.mockStatic(LeaderboardManager.class);
        Platform.runLater(() -> {
            testStage = new Stage();
            mockSoundManager = mock(SoundManager.class);
        });
        waitForFXEvents();
    }

    @AfterEach
    void tearDown() {
        mockedLeaderboardManager.close();
    }

    @Test
    void testInitializeWithScores() throws Exception {
        mockedLeaderboardManager.when(() -> LeaderboardManager.getTopScores(5))
                .thenReturn(Arrays.asList(100, 90, 80, 70, 60));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaderboard.fxml"));
        assertNotNull(loader.getLocation(), "FXML file path is null");
        Parent root = loader.load();
        leaderboardController = loader.getController();
        assertNotNull(leaderboardController, "Controller is null");
        leaderboardController.setSoundManager(mockSoundManager);

        Platform.runLater(() -> {
            testStage.setScene(new Scene(root));
            testStage.show();
        });
        waitForFXEvents();

        Platform.runLater(() -> {
            assertNotNull(leaderboardController.leaderboardListView, "Leaderboard ListView should not be null");
            assertEquals(5, leaderboardController.leaderboardListView.getItems().size(), "ListView should contain 5 scores");
            assertEquals("Rank 1: 100", leaderboardController.leaderboardListView.getItems().getFirst(), "First score should be Rank 1: 100");
        });
        waitForFXEvents();
    }

    @Test
    void testInitializeWithNoScores() throws Exception {
        mockedLeaderboardManager.when(() -> LeaderboardManager.getTopScores(5))
                .thenReturn(Collections.emptyList());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaderboard.fxml"));
        Parent root = loader.load();
        leaderboardController = loader.getController();
        leaderboardController.setSoundManager(mockSoundManager);

        Platform.runLater(() -> {
            testStage.setScene(new Scene(root));
            testStage.show();
        });
        waitForFXEvents();

        Platform.runLater(() -> {
            assertNotNull(leaderboardController.leaderboardListView, "Leaderboard ListView should not be null");
            assertEquals(1, leaderboardController.leaderboardListView.getItems().size(), "ListView should contain 1 item");
            assertEquals("No scores yet.", leaderboardController.leaderboardListView.getItems().getFirst(), "The item should indicate no scores");
        });
        waitForFXEvents();
    }

    @Test
    void testBackToMenu() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/leaderboard.fxml"));
        Parent root = loader.load();
        leaderboardController = loader.getController();

        Platform.runLater(() -> {
            testStage.setScene(new Scene(root));
            testStage.show();
            leaderboardController.backToMenu();
            assertNull(testStage.getScene(), "Scene should be null after navigating back to menu");
        });
        waitForFXEvents();
    }

    private void waitForFXEvents() throws InterruptedException {
        Thread.sleep(100); // Allow JavaFX events to process
    }
}
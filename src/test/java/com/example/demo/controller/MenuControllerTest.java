package com.example.demo.controller;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest extends JavaFXInitializer {

    private Stage testStage;
    private SoundManager soundManager;
    private MenuController menuController;


    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                // Initialize the test stage
                testStage = new Stage();

                // Mock dependencies
                soundManager = Mockito.mock(SoundManager.class);

                // Load the FXML and get the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
                Parent root = loader.load();
                menuController = loader.getController();

                // Set dependencies
                menuController.setStage(testStage);
                menuController.setSoundManager(soundManager);

                // Set the scene and show the stage
                Scene scene = new Scene(root, 1300, 750);
                testStage.setScene(scene);
                testStage.setTitle("Sky Battle");
                testStage.setResizable(false);
                testStage.show();

                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
                latch.countDown();
                fail("Exception during setup: " + e.getMessage());
            }
        });

        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("Timeout waiting for JavaFX setup");
        }
    }

    /**
     * Tests that the MenuController initializes correctly and all UI components are injected.
     */
    @Test
    void testInitialization() {
        assertNotNull(menuController, "MenuController should be initialized");
        assertNotNull(menuController.titleLabel, "titleLabel should be injected");
        assertNotNull(menuController.startButton, "startButton should be injected");
        assertNotNull(menuController.endlessModeButton, "endlessModeButton should be injected");
        assertNotNull(menuController.leaderboardButton, "leaderboardButton should be injected");
        assertNotNull(menuController.soundSettingsButton, "soundSettingsButton should be injected");
        assertNotNull(menuController.exitButton, "exitButton should be injected");
    }

    /**
     * Tests the handleStartButton method to ensure it executes without throwing exceptions.
     */
    @Test
    void testHandleStartButton() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                menuController.handleStartButton();
                // Optionally, verify interactions or state changes here
            } catch (Exception e) {
                fail("Exception thrown during handleStartButton: " + e.getMessage());
            }
            latch.countDown();
        });

        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new RuntimeException("Timeout waiting for handleStartButton()");
        }

        // Since handleStartButton internally creates new Controller instances,
        // we cannot directly verify method calls without modifying the controller for dependency injection.
        // Therefore, this test ensures that no exceptions are thrown during execution.
    }
}

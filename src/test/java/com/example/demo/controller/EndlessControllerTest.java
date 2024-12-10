package com.example.demo.controller;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EndlessControllerTest extends JavaFXInitializer {

    private Stage testStage;
    private SoundManager soundManager;
    private EndlessController endlessController;

    @BeforeEach
    void setUp() throws InterruptedException {
        Platform.runLater(() -> {
            testStage = new Stage();
            soundManager = Mockito.mock(SoundManager.class);
            endlessController = new EndlessController(testStage, soundManager);
        });
        waitForFXEvents();
    }

    /**
     * Tests that the endless mode game can be launched without throwing exceptions.
     */
    @Test
    void testStartEndlessMode() throws InterruptedException {
        Platform.runLater(() -> {
            endlessController.startEndlessMode();

            // Verify the stage is visible
            assertTrue(testStage.isShowing(), "Stage should be visible after starting endless mode");

            // Verify the scene is set
            Scene scene = testStage.getScene();
            assertNotNull(scene, "Scene should not be null after starting endless mode");

            // Verify the game-specific elements in the scene
            Pane rootPane = (Pane) scene.getRoot();
            assertNotNull(rootPane, "Root pane should not be null");
            ImageView background = (ImageView) rootPane.getChildren().getFirst();
            assertNotNull(background, "Background ImageView should not be null");
            assertTrue(background.getImage().getUrl().endsWith("blank.jpg"), "Background image should be blank.jpg");
        });
        waitForFXEvents();
    }

    /**
     * Helper method to wait for JavaFX events to be processed.
     */
    private void waitForFXEvents() throws InterruptedException {
        Thread.sleep(100); // Adjust the duration if necessary
    }
}

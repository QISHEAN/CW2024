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

class ControllerTest extends JavaFXInitializer {

    private Stage testStage;
    private SoundManager soundManager;
    private Controller controller;

    @BeforeEach
    void setUp() throws InterruptedException {
        Platform.runLater(() -> {
            testStage = new Stage();
            soundManager = Mockito.mock(SoundManager.class);
            controller = new Controller(testStage, soundManager);
        });
        waitForFXEvents();
    }

    /**
     * Tests that the game can be launched without throwing exceptions.
     */
    @Test
    void testLaunchGame() throws InterruptedException {
        Platform.runLater(() -> {
            controller.launchGame();

            // Verify the stage is visible
            assertTrue(testStage.isShowing(), "Stage should be visible after launching the game");

            // Verify the scene is set
            Scene scene = testStage.getScene();
            assertNotNull(scene, "Scene should not be null after launching the game");

            // Verify the background image is set
            ImageView background = (ImageView) ((Pane) scene.getRoot()).getChildren().getFirst();
            assertNotNull(background, "Background ImageView should not be null");
            assertTrue(background.getImage().getUrl().endsWith("blank.jpg"), "Background image should be blank.jpg");
        });
        waitForFXEvents();
    }

    /**
     * Tests that exiting to the main menu doesn't throw an exception and sets the scene properly.
     */
    @Test
    void testExitToMainMenu() throws InterruptedException {
        Platform.runLater(() -> controller.launchGame());
        waitForFXEvents();

        Platform.runLater(() -> {
            controller.exitToMainMenu();

            // Verify the scene is set for the main menu
            Scene scene = testStage.getScene();
            assertNotNull(scene, "Scene should not be null after exiting to the main menu");

            // Verify the background image is set
            ImageView background = (ImageView) ((Pane) scene.getRoot()).getChildren().getFirst();
            assertNotNull(background, "Background ImageView should not be null");
            assertTrue(background.getImage().getUrl().endsWith("blank.jpg"), "Background image should be blank.jpg");
        });
        waitForFXEvents();
    }

    /**
     * Tests that restarting the level doesn't throw exceptions.
     */
    @Test
    void testRestartLevel() throws InterruptedException {
        Platform.runLater(() -> controller.launchGame());
        waitForFXEvents();

        Platform.runLater(() -> {
            controller.restartLevel();

            // Verify the scene remains set after restarting the level
            Scene scene = testStage.getScene();
            assertNotNull(scene, "Scene should not be null after restarting the level");

            // Verify the background image is set
            ImageView background = (ImageView) ((Pane) scene.getRoot()).getChildren().getFirst();
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

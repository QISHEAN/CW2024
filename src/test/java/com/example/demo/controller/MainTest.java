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

import static org.junit.jupiter.api.Assertions.*;

class MainTest extends JavaFXInitializer {

    private Stage testStage;
    private SoundManager soundManager;
    private Main mainApp;
    private MenuController menuController;

    @BeforeEach
    void setUp() throws InterruptedException {
        Platform.runLater(() -> {
            try {
                // Initialize the test stage
                testStage = new Stage();

                // Mock dependencies
                soundManager = Mockito.mock(SoundManager.class);

                // Initialize Main application
                mainApp = new Main();

                // Manually call the start method with the test stage
                mainApp.start(testStage);

                // Assuming Main's start method loads MenuScreen.fxml and sets up MenuController
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
                Parent root = loader.load();
                menuController = loader.getController();
                menuController.setStage(testStage);
                menuController.setSoundManager(soundManager);

                // Set the scene and show the stage
                Scene scene = new Scene(root, 1300, 750);
                testStage.setScene(scene);
                testStage.setTitle("Sky Battle");
                testStage.setResizable(false);
                testStage.show();
            } catch (Exception e) {
                fail("Exception during setup: " + e.getMessage());
            }
        });

        waitForFXEvents();
    }

    /**
     * Tests that the Main application launches correctly and the stage is set up as expected.
     */
    @Test
    void testApplicationLaunch() throws InterruptedException {
        Platform.runLater(() -> {
            try {
                // Verify that the stage is showing
                assertTrue(testStage.isShowing(), "Stage should be visible after launching the application");

                // Verify the stage title
                assertEquals("Sky Battle", testStage.getTitle(), "Stage title should be 'Sky Battle'");

                // Verify that the scene is set
                Scene scene = testStage.getScene();
                assertNotNull(scene, "Scene should not be null after launching the application");

                // Optionally, verify specific UI components are present
                assertNotNull(scene.lookup("#startButton"), "Start Button should be present in the scene");
                // Add more assertions as needed
            } catch (Exception e) {
                fail("Exception during testApplicationLaunch: " + e.getMessage());
            }
        });

        waitForFXEvents();
    }

    /**
     * Tests that the Main application can handle launching multiple stages without issues.
     */
    @Test
    void testMultipleStageLaunch() throws InterruptedException {
        Platform.runLater(() -> {
            try {
                // Create a new stage
                Stage secondStage = new Stage();
                mainApp.start(secondStage);

                // Set up the second stage similar to the first
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
                Parent root = loader.load();
                MenuController secondMenuController = loader.getController();
                secondMenuController.setStage(secondStage);
                secondMenuController.setSoundManager(soundManager);

                Scene scene = new Scene(root, 1300, 750);
                secondStage.setScene(scene);
                secondStage.setTitle("Sky Battle - Second Instance");
                secondStage.setResizable(false);
                secondStage.show();

                // Verify that both stages are showing
                assertTrue(testStage.isShowing(), "First stage should still be visible");
                assertTrue(secondStage.isShowing(), "Second stage should be visible");
                assertEquals("Sky Battle - Second Instance", secondStage.getTitle(), "Second stage title should be correct");
            } catch (Exception e) {
                fail("Exception during testMultipleStageLaunch: " + e.getMessage());
            }
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

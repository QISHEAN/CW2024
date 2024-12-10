package com.example.demo.managers;

import com.example.demo.JavaFXInitializer;
import com.example.demo.controller.PauseMenuController;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PauseManagerTest extends JavaFXInitializer {

    private PauseManager pauseManager;
    private Scene scene;
    private Group root;
    private Stage stage;
    private Runnable pauseAction;
    private Runnable resumeAction;
    private Runnable restartAction;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize JavaFX components on the JavaFX thread
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            stage = new Stage();
            root = new Group();
            scene = new Scene(root, 800, 600);
            latch.countDown();
        });
        latch.await();

        // Mock dependencies
        SoundManager soundManager = mock(SoundManager.class);
        pauseAction = mock(Runnable.class);
        resumeAction = mock(Runnable.class);
        restartAction = mock(Runnable.class);

        // Create the PauseManager instance
        pauseManager = new PauseManager(scene, root, stage, soundManager, pauseAction, resumeAction, restartAction);
    }

    @Test
    void testPauseGame() {
        // Simulate pressing the ESC key to pause the game
        Platform.runLater(() -> scene.getRoot().fireEvent(
                new javafx.scene.input.KeyEvent(
                        javafx.scene.input.KeyEvent.KEY_PRESSED, "", "", javafx.scene.input.KeyCode.ESCAPE,
                        false, false, false, false
                )
        ));

        // Allow JavaFX events to propagate
        waitForPlatform();

        assertTrue(pauseManager.isPaused(), "Game should be paused.");
        verify(pauseAction, times(1)).run();
        assertEquals(1, root.getChildren().size(), "Pause menu should be added to the root.");
    }

    @Test
    void testResumeGame() {
        // Pause the game first
        Platform.runLater(() -> scene.getRoot().fireEvent(
                new javafx.scene.input.KeyEvent(
                        javafx.scene.input.KeyEvent.KEY_PRESSED, "", "", javafx.scene.input.KeyCode.ESCAPE,
                        false, false, false, false
                )
        ));

        // Allow JavaFX events to propagate
        waitForPlatform();

        // Resume the game by pressing ESC again
        Platform.runLater(() -> scene.getRoot().fireEvent(
                new javafx.scene.input.KeyEvent(
                        javafx.scene.input.KeyEvent.KEY_PRESSED, "", "", javafx.scene.input.KeyCode.ESCAPE,
                        false, false, false, false
                )
        ));

        // Allow JavaFX events to propagate
        waitForPlatform();

        assertFalse(pauseManager.isPaused(), "Game should be resumed.");
        verify(resumeAction, times(1)).run();
        assertTrue(root.getChildren().isEmpty(), "Pause menu should be removed from the root.");
    }

    @Test
    void testRestartGame() {
        // Mock PauseMenuController and inject it
        PauseMenuController mockPauseMenuController = mock(PauseMenuController.class);
        Mockito.doNothing().when(mockPauseMenuController).setActions(any(), any(), any());
        pauseManager.initializePauseHandler();

        // Simulate the restart action
        Platform.runLater(() -> pauseManager.restart());

        // Allow JavaFX events to propagate
        waitForPlatform();

        verify(restartAction, times(1)).run();
        assertFalse(pauseManager.isPaused(), "Game should not be paused after restart.");
    }

    private void waitForPlatform() {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            Platform.runLater(latch::countDown);
            latch.await();
        } catch (InterruptedException e) {
            fail("Interrupted while waiting for JavaFX thread.");
        }
    }
}

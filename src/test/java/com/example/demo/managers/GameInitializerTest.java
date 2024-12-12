package com.example.demo.managers;

import com.example.demo.JavaFXInitializer;
import com.example.demo.actor.TestUserPlane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameInitializerTest extends JavaFXInitializer {

    private GameInitializer gameInitializer;
    private Group root;
    private ImageView background;
    private TestUserPlane userPlane;
    private Timeline timeline;
    private int millisecondDelay;

    @BeforeEach
    void setUp() {
        // Initialize components on the JavaFX thread
        runAndWait(() -> {
            root = new Group();
            background = new ImageView();
            userPlane = new TestUserPlane( "blank.jpg", 50);
            timeline = new Timeline();
            millisecondDelay = 16; // Approximately 60 FPS

            gameInitializer = new GameInitializer(root, background, userPlane, timeline, millisecondDelay);
        });
    }

    @AfterEach
    void tearDown() {
        // Optional: Clear the root Group after each test
        runAndWait(() -> root.getChildren().clear());
    }

    /**
     * Helper method to run tasks on the JavaFX Application Thread and wait for their completion.
     *
     * @param action The task to run.
     */
    private void runAndWait(Runnable action) {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        try {
            boolean completed = latch.await(5, TimeUnit.SECONDS);
            if (!completed) {
                fail("Timeout waiting for JavaFX tasks to complete.");
            }
        } catch (InterruptedException e) {
            fail("Interrupted while waiting for JavaFX tasks to complete.");
        }
    }

    /**
     * Tests that initializeBackground() sets the background as focus traversable and adds it as the first child.
     */
    @Test
    void testInitializeBackground() {
        // Act
        runAndWait(() -> gameInitializer.initializeBackground());

        // Assert
        assertTrue(background.isFocusTraversable(), "Background should be focus traversable.");
        assertEquals(background, root.getChildren().getFirst(), "Background should be the first child in root.");
    }

    /**
     * Tests that initializeTimeline() sets up the timeline correctly and the updateScene runnable is invoked.
     */
    @Test
    void testInitializeTimeline() {
        // Arrange
        Runnable mockUpdateScene = mock(Runnable.class);
        gameInitializer.initializeTimeline(mockUpdateScene);

        // Assert timeline setup
        assertEquals(Timeline.INDEFINITE, timeline.getCycleCount(), "Timeline should cycle indefinitely.");
        assertEquals(1, timeline.getKeyFrames().size(), "Timeline should have one KeyFrame.");
        KeyFrame keyFrame = timeline.getKeyFrames().getFirst();
        assertEquals(Duration.millis(millisecondDelay), keyFrame.getTime(), "KeyFrame duration should match millisecondDelay.");

        // Act: Simulate the KeyFrame firing
        keyFrame.getOnFinished().handle(null);

        // Assert that updateScene.run() was called
        verify(mockUpdateScene, times(1)).run();
    }
}

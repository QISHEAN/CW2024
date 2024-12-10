package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import javafx.application.Platform;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ActiveActor.
 */
class ActiveActorTest extends JavaFXInitializer {

    private TestActiveActor testActor;

    /**
     * Sets up the TestActiveActor instance before each test.
     */
    @BeforeEach
    void setUp() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Initialize TestActiveActor with test parameters
            testActor = new TestActiveActor("blank.jpg", 100, 200.0, 300.0);
            assertNotNull(testActor, "TestActiveActor should be initialized properly.");
            latch.countDown();
        });
        // Wait for the JavaFX thread to finish initialization
        assertTrue(latch.await(2, TimeUnit.SECONDS), "JavaFX thread initialization timed out.");
    }

    /**
     * Tests the constructor of ActiveActor.
     */
    @Test
    void testConstructor() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            // Verify image is loaded correctly
            Image image = testActor.getImage();
            assertNotNull(image, "Image should be loaded and not null.");
            assertFalse(image.isError(), "Image should load without errors.");

            // Verify initial positions
            assertEquals(200.0, testActor.getLayoutX(), 0.001, "Initial X position should be 200.0");
            assertEquals(300.0, testActor.getLayoutY(), 0.001, "Initial Y position should be 300.0");

            // Verify image properties
            assertEquals(100, testActor.getFitHeight(), "Fit height should be 100.");
            assertTrue(testActor.isPreserveRatio(), "Preserve ratio should be true.");

            latch.countDown();
        });
        assertTrue(latch.await(2, TimeUnit.SECONDS), "JavaFX thread timed out during constructor test.");
    }

    /**
     * Tests the moveHorizontally method.
     */
    @Test
    void testMoveHorizontally() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            double initialTranslateX = testActor.getTranslateX();
            testActor.moveHorizontally(15.0);
            double updatedTranslateX = testActor.getTranslateX();
            assertEquals(initialTranslateX + 15.0, updatedTranslateX, 0.001, "TranslateX should increase by 15.0");
            latch.countDown();
        });
        assertTrue(latch.await(2, TimeUnit.SECONDS), "JavaFX thread timed out during moveHorizontally test.");
    }

    /**
     * Tests the moveVertically method.
     */
    @Test
    void testMoveVertically() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            double initialTranslateY = testActor.getTranslateY();
            testActor.moveVertically(-10.0);
            double updatedTranslateY = testActor.getTranslateY();
            assertEquals(initialTranslateY - 10.0, updatedTranslateY, 0.001, "TranslateY should decrease by 10.0");
            latch.countDown();
        });
        assertTrue(latch.await(2, TimeUnit.SECONDS), "JavaFX thread timed out during moveVertically test.");
    }

    /**
     * Tests the updatePosition method.
     */
    @Test
    void testUpdatePosition() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            double initialTranslateX = testActor.getTranslateX();
            double initialTranslateY = testActor.getTranslateY();
            testActor.updatePosition();
            double updatedTranslateX = testActor.getTranslateX();
            double updatedTranslateY = testActor.getTranslateY();

            // Verify that translateX increased by 10 and translateY increased by 5
            assertEquals(initialTranslateX + 10.0, updatedTranslateX, 0.001, "TranslateX should increase by 10.0");
            assertEquals(initialTranslateY + 5.0, updatedTranslateY, 0.001, "TranslateY should increase by 5.0");
            latch.countDown();
        });
        assertTrue(latch.await(2, TimeUnit.SECONDS), "JavaFX thread timed out during updatePosition test.");
    }
}

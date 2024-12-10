package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import com.example.demo.projectiles.UserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for UserPlane.
 */
class UserPlaneTest extends JavaFXInitializer {

    private TestUserPlane userPlane;

    /**
     * Sets up the TestUserPlane instance before each test.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @BeforeEach
    void setUp() throws InterruptedException {
        // Initialize the TestUserPlane with "blank.jpg" and an initial health value of 3
        Platform.runLater(() -> userPlane = new TestUserPlane("blank.jpg", 3));
        // Wait briefly to ensure JavaFX components are initialized
        waitForFXEvents();
    }

    /**
     * Tests the initial position of the UserPlane.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testInitialPosition() throws InterruptedException {
        // Run the assertions on the JavaFX Application Thread
        Platform.runLater(() -> {
            assertEquals(5.0, userPlane.getLayoutX(), 0.001, "Initial X position should be 5.0");
            assertEquals(300.0, userPlane.getLayoutY(), 0.001, "Initial Y position should be 300.0");
        });
        waitForFXEvents();
    }

    /**
     * Tests moving the UserPlane upwards.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testMoveUp() throws InterruptedException {
        // Move the UserPlane up
        Platform.runLater(() -> {
            userPlane.moveUp();
            userPlane.updatePosition();
        });
        waitForFXEvents();

        // Verify the new position
        Platform.runLater(() -> {
            double expectedY = 300.0 - 12.0; // Initial Y position minus VERTICAL_VELOCITY
            double actualY = userPlane.getLayoutY() + userPlane.getTranslateY();
            assertEquals(expectedY, actualY, 0.001, "UserPlane should move up by 12 units");
        });
        waitForFXEvents();
    }

    /**
     * Tests moving the UserPlane downwards.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testMoveDown() throws InterruptedException {
        // Move the UserPlane down
        Platform.runLater(() -> {
            userPlane.moveDown();
            userPlane.updatePosition();
        });
        waitForFXEvents();

        // Verify the new position
        Platform.runLater(() -> {
            double expectedY = 300.0 + 12.0; // Initial Y position plus VERTICAL_VELOCITY
            double actualY = userPlane.getLayoutY() + userPlane.getTranslateY();
            assertEquals(expectedY, actualY, 0.001, "UserPlane should move down by 12 units");
        });
        waitForFXEvents();
    }

    /**
     * Tests moving the UserPlane to the left when near the boundary.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testMoveLeftBoundary() throws InterruptedException {
        // Move the UserPlane left
        Platform.runLater(() -> {
            userPlane.moveLeft();
            userPlane.updatePosition();
        });
        waitForFXEvents();

        // Verify the position remains unchanged due to boundary check
        Platform.runLater(() -> {
            double expectedX = 5.0; // Movement should be reverted
            double actualX = userPlane.getLayoutX() + userPlane.getTranslateX();
            assertEquals(expectedX, actualX, 0.001, "UserPlane should not move left beyond the boundary");
        });
        waitForFXEvents();
    }

    /**
     * Tests moving the UserPlane to the left within safe boundaries.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testMoveLeftWithinBoundaries() throws InterruptedException {
        // Initialize UserPlane at X = 20.0 for safe movement
        Platform.runLater(() -> {
            userPlane.setLayoutX(20.0);
            userPlane.setTranslateX(0.0);
        });
        waitForFXEvents();

        // Move the UserPlane left
        Platform.runLater(() -> {
            userPlane.moveLeft();
            userPlane.updatePosition();
        });
        waitForFXEvents();

        // Verify the new position
        Platform.runLater(() -> {
            double expectedX = 20.0 - 12.0; // 8.0
            double actualX = userPlane.getLayoutX() + userPlane.getTranslateX();
            assertEquals(expectedX, actualX, 0.001, "UserPlane should move left by 12 units");
        });
        waitForFXEvents();
    }

    /**
     * Tests moving the UserPlane to the right.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testMoveRight() throws InterruptedException {
        // Move the UserPlane right
        Platform.runLater(() -> {
            userPlane.moveRight();
            userPlane.updatePosition();
        });
        waitForFXEvents();

        // Verify the new position
        Platform.runLater(() -> {
            double expectedX = 5.0 + 12.0; // Initial X position plus HORIZONTAL_VELOCITY
            double actualX = userPlane.getLayoutX() + userPlane.getTranslateX();
            assertEquals(expectedX, actualX, 0.001, "UserPlane should move right by 12 units");
        });
        waitForFXEvents();
    }

    /**
     * Tests firing a projectile from the UserPlane.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testFireProjectile() throws InterruptedException {
        Platform.runLater(() -> {
            // Fire a projectile
            ActiveActorDestructible projectile = userPlane.fireProjectile();

            // Verify the projectile is correctly instantiated
            assertNotNull(projectile, "Projectile should not be null");
            assertInstanceOf(UserProjectile.class, projectile, "Projectile should be an instance of UserProjectile");

            // Verify the projectile's initial position
            double expectedX = userPlane.getProjectileXPosition(50);
            double expectedY = userPlane.getProjectileYPosition(25);
            assertEquals(expectedX, projectile.getLayoutX(), 0.001, "Projectile X position incorrect");
            assertEquals(expectedY, projectile.getLayoutY(), 0.001, "Projectile Y position incorrect");
        });
        waitForFXEvents();
    }

    /**
     * Tests resetting the UserPlane.
     *
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    @Test
    void testReset() throws InterruptedException {
        Platform.runLater(() -> {
            // Simulate some movements and kills
            userPlane.moveUp();
            userPlane.updatePosition();
            userPlane.incrementKillCount(5);
            userPlane.reset();

            // Verify reset state
            assertEquals(3, userPlane.getHealth(), "Health should reset to initial health");
            assertEquals(0, userPlane.getTranslateX(), "TranslateX should reset to 0");
            assertEquals(0, userPlane.getTranslateY(), "TranslateY should reset to 0");
            assertEquals(0, userPlane.getKillCount(), "Kill count should reset to 0");
            assertFalse(userPlane.isMovingVertically(), "Should not be moving vertically after reset");
            assertFalse(userPlane.isMovingHorizontally(), "Should not be moving horizontally after reset");
        });
        waitForFXEvents();
    }

    /**
     * Helper method to wait for JavaFX events to be processed.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    private void waitForFXEvents() throws InterruptedException {
        // In a real-world scenario, consider using CountDownLatch or similar mechanisms
        Thread.sleep(100); // Adjust the sleep duration as necessary
    }
}

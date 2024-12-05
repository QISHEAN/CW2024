package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FighterPlaneTest extends JavaFXInitializer{

    private TestFighterPlane fighterPlane;

    @BeforeEach
    void setUp() {
        // Initialize a new TestFighterPlane before each test
        Platform.runLater(() -> fighterPlane = new TestFighterPlane("blank.jpg", 100, 50.0, 100.0, 3));
        waitForFXEvents();
    }

    @Test
    void testConstructor() {
        // Verify that health is initialized correctly
        Platform.runLater(() -> assertEquals(3, fighterPlane.getHealth(), "Health should be initialized to 3"));
        waitForFXEvents();
    }

    @Test
    void testConstructorNegativeHealth() {
        Platform.runLater(() -> {
            // Create a fighter plane with negative health
            TestFighterPlane planeWithNegativeHealth = new TestFighterPlane("blank.jpg", 100, 50.0, 100.0, -5);
            assertEquals(0, planeWithNegativeHealth.getHealth(), "Health should be set to 0 if initialized with negative value");
        });
        waitForFXEvents();
    }

    @Test
    void testTakeDamage() {
        Platform.runLater(() -> {
            fighterPlane.takeDamage();
            assertEquals(2, fighterPlane.getHealth(), "Health should decrease by 1 after taking damage");
            assertFalse(fighterPlane.isDestroyed(), "Plane should not be destroyed yet");

            fighterPlane.takeDamage();
            assertEquals(1, fighterPlane.getHealth(), "Health should decrease to 1");

            fighterPlane.takeDamage();
            assertEquals(0, fighterPlane.getHealth(), "Health should decrease to 0");
            assertTrue(fighterPlane.isDestroyed(), "Plane should be destroyed when health reaches zero");
        });
        waitForFXEvents();
    }

    @Test
    void testTakeDamageWhenHealthZero() {
        Platform.runLater(() -> {
            // Reduce health to zero
            fighterPlane.takeDamage();
            fighterPlane.takeDamage();
            fighterPlane.takeDamage();

            // Try taking damage when health is already zero
            fighterPlane.takeDamage();
            assertEquals(0, fighterPlane.getHealth(), "Health should not go below 0");
            assertTrue(fighterPlane.isDestroyed(), "Plane should remain destroyed");
        });
        waitForFXEvents();
    }

    @Test
    void testGetProjectilePosition() {
        Platform.runLater(() -> {
            // Set known positions
            fighterPlane.setLayoutX(50.0);
            fighterPlane.setLayoutY(100.0);
            fighterPlane.setTranslateX(10.0);
            fighterPlane.setTranslateY(20.0);

            double xOffset = 5.0;
            double yOffset = 10.0;

            double expectedX = 50.0 + 10.0 + xOffset;
            double expectedY = 100.0 + 20.0 + yOffset;

            // Since methods are protected, we'll call them via the subclass
            double projectileX = fighterPlane.getProjectileXPosition(xOffset);
            double projectileY = fighterPlane.getProjectileYPosition(yOffset);

            assertEquals(expectedX, projectileX, 0.001, "Projectile X position should be calculated correctly");
            assertEquals(expectedY, projectileY, 0.001, "Projectile Y position should be calculated correctly");
        });
        waitForFXEvents();
    }

    // Helper method to wait for JavaFX events to be processed
    private void waitForFXEvents() {
        try {
            Thread.sleep(100); // Adjust if necessary
        } catch (InterruptedException e) {
            System.out.println("error");
        }
    }
}

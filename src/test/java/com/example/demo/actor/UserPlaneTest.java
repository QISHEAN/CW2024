package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import com.example.demo.projectiles.UserProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserPlaneTest extends JavaFXInitializer {

    private UserPlane userPlane;


    @BeforeEach
    void setUp() throws InterruptedException {
        // Initialize the UserPlane with an initial health value, e.g., 3
        Platform.runLater(() -> userPlane = new UserPlane(3));
        // Wait briefly to ensure JavaFX components are initialized
        Thread.sleep(100);
    }

    @Test
    void testInitialPosition() throws InterruptedException {
        // Run the assertions on the JavaFX Application Thread
        Platform.runLater(() -> {
            assertEquals(5.0, userPlane.getLayoutX(), 0.001, "Initial X position should be 5.0");
            assertEquals(300.0, userPlane.getLayoutY(), 0.001, "Initial Y position should be 300.0");
        });
        Thread.sleep(100);
    }

    @Test
    void testMoveUp() throws InterruptedException {
        // Move the UserPlane up
        Platform.runLater(() -> {
            userPlane.moveUp();
            userPlane.updatePosition();
        });
        Thread.sleep(100);

        // Verify the new position
        Platform.runLater(() -> {
            double expectedY = userPlane.getLayoutY() - 8;
            double actualY = userPlane.getLayoutY() + userPlane.getTranslateY();
            assertEquals(expectedY, actualY, 0.001, "UserPlane should move up by 8 units");
        });
        Thread.sleep(100);
    }

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
        Thread.sleep(100);
    }
}

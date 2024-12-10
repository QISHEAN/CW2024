package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import com.example.demo.projectiles.EnemyProjectile;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for EnemyPlane.
 */
class EnemyPlaneTest extends JavaFXInitializer {

    private TestEnemyPlane enemyPlane;


    /**
     * Sets up the TestEnemyPlane instance before each test.
     */
    @BeforeEach
    void setUp() throws InterruptedException {
        Platform.runLater(() -> {
            enemyPlane = new TestEnemyPlane(100.0, 200.0, 3);
            assertNotNull(enemyPlane, "EnemyPlane should be initialized properly.");
        });
        Thread.sleep(200); // Wait for JavaFX thread to complete initialization
    }

    /**
     * Tests the constructor of EnemyPlane.
     */
    @Test
    void testConstructor() throws InterruptedException {
        Platform.runLater(() -> {
            assertNotNull(enemyPlane.getImage(), "Image should not be null.");
            assertEquals(100.0, enemyPlane.getLayoutX(), 0.001, "Initial X position should be 100.0");
            assertEquals(200.0, enemyPlane.getLayoutY(), 0.001, "Initial Y position should be 200.0");
            assertEquals(3, enemyPlane.getHealth(), "Initial health should be 3");
        });
        Thread.sleep(200);
    }

    /**
     * Tests updating the position of the EnemyPlane.
     */
    @Test
    void testUpdatePosition() throws InterruptedException {
        Platform.runLater(() -> {
            double initialTranslateX = enemyPlane.getTranslateX();
            enemyPlane.updatePosition();
            double updatedTranslateX = enemyPlane.getTranslateX();
            assertEquals(initialTranslateX + enemyPlane.horizontalVelocity, updatedTranslateX, 0.001, "EnemyPlane translateX should decrease by horizontalVelocity (-6).");
        });
        Thread.sleep(200);
    }

    /**
     * Tests firing a projectile from the EnemyPlane.
     */
    @Test
    void testFireProjectile() throws InterruptedException {
        Platform.runLater(() -> {
            ActiveActorDestructible projectile = enemyPlane.fireProjectile();
            assertNotNull(projectile, "Projectile should not be null.");
            assertInstanceOf(EnemyProjectile.class, projectile, "Projectile should be of type EnemyProjectile.");
        });
        Thread.sleep(200);
    }
}

package com.example.demo.actor;

import com.example.demo.projectiles.EnemyProjectile;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Test subclass of EnemyPlane to expose protected methods and override behaviors for testing purposes.
 */
public class TestEnemyPlane extends EnemyPlane {

    private static final double TEST_PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double TEST_PROJECTILE_Y_POSITION_OFFSET = 50.0;

    /**
     * Constructor for TestEnemyPlane.
     *
     * @param initialXPos Initial X position of the EnemyPlane.
     * @param initialYPos Initial Y position of the EnemyPlane.
     * @param health      Initial health of the EnemyPlane.
     */
    public TestEnemyPlane(double initialXPos, double initialYPos, int health) {
        super(initialXPos, initialYPos, health);

        // Ensure image is loaded properly
        Image testImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/demo/images/blank.jpg")));
        if (testImage.isError()) {
            throw new IllegalArgumentException("Failed to load blank.jpg for testing.");
        }
        setImage(testImage);
    }

    /**
     * Overrides fireProjectile to always return a new EnemyProjectile for deterministic testing.
     *
     * @return A new EnemyProjectile instance.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        double projectileXPosition = getProjectileXPosition(TEST_PROJECTILE_X_POSITION_OFFSET);
        double projectileYPosition = getProjectileYPosition(TEST_PROJECTILE_Y_POSITION_OFFSET);
        return new EnemyProjectile(projectileXPosition, projectileYPosition);
    }

}

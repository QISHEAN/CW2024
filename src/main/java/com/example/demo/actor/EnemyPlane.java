package com.example.demo.actor;

import com.example.demo.projectiles.EnemyProjectile;
import javafx.geometry.Bounds;

/**
 * The EnemyPlane class represents an enemy aircraft in the game.
 * It extends FighterPlane and includes functionality for movement,
 * firing projectiles, and collision detection.
 */
public class EnemyPlane extends FighterPlane {

	//Constants defining the enemy plane's image, dimensions, and behavior.
	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 120;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final double FIRE_RATE = 0.01;

	//Horizontal velocity of the enemy plane.
	protected double horizontalVelocity;

	/**
	 * Constructor to initialize the enemy plane with a specific position and health.
	 *
	 * @param initialXPos the initial X position of the enemy plane.
	 * @param initialYPos the initial Y position of the enemy plane.
	 * @param health      the health value of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos, int health) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, health);
		this.horizontalVelocity = -6;
	}

	/**
	 * Updates the position of the enemy plane by moving it horizontally.
	 * The movement is determined by the current horizontal velocity.
	 */
	@Override
	public void updatePosition() {
			moveHorizontally(horizontalVelocity);
	}

	/**
	 * Fires a projectile from the enemy plane if a random chance meets the fire rate.
	 *
	 * @return an EnemyProjectile instance if the plane fires; otherwise, null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the enemy plane's behavior. Currently, it updates its position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Updates the bounding box of the enemy plane for collision detection.
	 * The bounding box's dimensions and offsets are adjusted for accuracy.
	 */
	@Override
	public void updateBoundingBox() {
		double offsetX = 5;    // Adjust as needed
		double offsetY = 35;   // Adjust as needed
		double scaleWidth = 0.9;
		double scaleHeight = 0.4;

		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX() + offsetX);
		boundingBox.setY(bounds.getMinY() + offsetY);
		boundingBox.setWidth(bounds.getWidth() * scaleWidth);
		boundingBox.setHeight(bounds.getHeight() * scaleHeight);
	}

	/**
	 * Sets the speed of the enemy plane.
	 * A negative speed is applied to maintain leftward movement.
	 *
	 * @param speed the new speed value.
	 */
	public void setSpeed(double speed) {
		this.horizontalVelocity = -speed; // Negative for leftward movement
	}

	/**
	 * Gets the current speed of the enemy plane.
	 *
	 * @return the positive value of the speed.
	 */
	public double getSpeed() {
		return -this.horizontalVelocity; //Return positive speed value
	}
}

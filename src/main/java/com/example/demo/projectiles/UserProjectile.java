package com.example.demo.projectiles;

import javafx.geometry.Bounds;

/**
 * Represents a projectile fired by the user's plane in the game.
 * Extends the `Projectile` class and defines specific behavior for user projectiles.
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png"; // Image file representing the user's projectile.
	private static final int IMAGE_HEIGHT = 125; // Height of the projectile image.
	private static final int HORIZONTAL_VELOCITY = 20; // Horizontal speed of the projectile.

	/**
	 * Constructor to initialize a `UserProjectile` at the specified X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos); // Call the parent constructor to initialize the projectile.
	}

	/**
	 * Updates the position of the projectile.
	 * Moves the projectile horizontally to simulate its forward motion.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the projectile to the right.
	}

	/**
	 * Updates the actor's state during the game loop.
	 * Specifically, updates the position of the projectile.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Call the method to update the position of the projectile.
	}

	/**
	 * Updates the bounding box of the projectile to reflect its current position.
	 * Adjusts the bounding box dimensions to align with the visual representation of the projectile.
	 */
	public void updateBoundingBox() {
		// Apply offsets and scaling factors specific to UserPlane
		double offsetX = 60; // Offset to fine-tune the X position.
		double offsetY = 50; // Offset to fine-tune the Y position.
		double scaleWidth = 0.3; // Scale factor for the width.
		double scaleHeight = 0.3; // Scale factor for the height.

		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX() + offsetX); // Adjust the X position of the bounding box.
		boundingBox.setY(bounds.getMinY() + offsetY); // Adjust the Y position of the bounding box.
		boundingBox.setWidth(bounds.getWidth() * scaleWidth); // Scale the width of the bounding box.
		boundingBox.setHeight(bounds.getHeight() * scaleHeight); // Scale the height of the bounding box.
	}
}

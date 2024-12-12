package com.example.demo.projectiles;

/**
 * Represents a projectile fired by the boss in the game.
 * Extends the `Projectile` class and defines specific behavior for the boss's projectile.
 */
public class BossProjectile extends Projectile {

	private static final String IMAGE_NAME = "fireball.png"; // Image file representing the projectile.
	private static final int IMAGE_HEIGHT = 50; // Height of the projectile image.
	private static final int HORIZONTAL_VELOCITY = -15; // Horizontal movement speed of the projectile.
	private static final int INITIAL_X_POSITION = 950; // Initial X position of the projectile.

	/**
	 * Constructor to initialize a `BossProjectile` at the specified Y position.
	 *
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos); // Call the parent constructor to set up the projectile.
	}

	/**
	 * Updates the position of the projectile.
	 * Moves the projectile horizontally based on the defined velocity.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); // Move the projectile to the left.
	}

	/**
	 * Updates the actor's state, which includes updating its position.
	 * This method is called during each game update cycle.
	 */
	@Override
	public void updateActor() {
		updatePosition(); // Call the method to update the position of the projectile.
	}
}

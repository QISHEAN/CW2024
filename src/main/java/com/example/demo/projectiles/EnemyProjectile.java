package com.example.demo.projectiles;

/**
 * Represents a projectile fired by an enemy unit in the game.
 * Extends the `Projectile` class and defines specific behavior for enemy projectiles.
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "enemyFire.png"; //Image file representing the enemy's projectile.
	private static final int IMAGE_HEIGHT = 30; //Height of the projectile image.
	private static final int HORIZONTAL_VELOCITY = -10; //Horizontal speed of the projectile.

	/**
	 * Constructor to initialize an `EnemyProjectile` at the specified X and Y positions.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos); //Call the parent constructor to initialize the projectile.
	}

	/**
	 * Updates the position of the projectile.
	 * Moves the projectile horizontally to simulate its flight.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY); //Move the projectile to the left.
	}

	/**
	 * Updates the actor's state during the game loop.
	 * Updates the position and bounding box of the projectile to reflect its current state.
	 */
	@Override
	public void updateActor() {
		updatePosition(); //Update the position of the projectile.
		updateBoundingBox(); //Update the bounding box to match the current position.
	}
}

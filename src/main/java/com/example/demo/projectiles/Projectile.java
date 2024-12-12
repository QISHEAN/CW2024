package com.example.demo.projectiles;

import com.example.demo.actor.ActiveActorDestructible;

/**
 * Represents the base class for all projectiles in the game.
 * Defines common behavior and properties for projectiles, such as initialization and handling damage.
 * Extends `ActiveActorDestructible` to allow projectiles to interact with other actors.
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor to initialize a `Projectile`.
	 *
	 * @param imageName     The name of the image file representing the projectile.
	 * @param imageHeight   The height of the projectile image.
	 * @param initialXPos   The initial X position of the projectile.
	 * @param initialYPos   The initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos); // Call the parent constructor to initialize the projectile.
	}

	/**
	 * Handles damage taken by the projectile.
	 * When a projectile takes damage, it is destroyed immediately.
	 */
	@Override
	public void takeDamage() {
		this.destroy(); // Mark the projectile as destroyed.
	}

	/**
	 * Abstract method to update the position of the projectile.
	 * This method must be implemented by subclasses to define specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();
}

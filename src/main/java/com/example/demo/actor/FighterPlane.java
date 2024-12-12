package com.example.demo.actor;

/**
 * Abstract class representing a fighter plane in the game.
 * It extends ActiveActorDestructible and provides functionality for health management
 * and firing projectiles.
 */
public abstract class FighterPlane extends ActiveActorDestructible {

	//The current health of the fighter plane.
	private int health;

	/**
	 * Constructor to initialize the fighter plane with an image, size, position, and health.
	 *
	 * @param imageName    the name of the image file to load for this plane.
	 * @param imageHeight  the height of the plane's image (aspect ratio preserved).
	 * @param initialXPos  the initial X position of the plane.
	 * @param initialYPos  the initial Y position of the plane.
	 * @param health       the initial health of the plane (non-negative).
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = Math.max(0,health);
	}

	/**
	 * Abstract method for firing projectiles. Subclasses must define how projectiles are created
	 * and returned when the plane fires.
	 *
	 * @return an ActiveActorDestructible instance representing the fired projectile.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Handles the fighter plane taking damage. Decreases health by 1 if health is above 0.
	 * If health reaches zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		if (health > 0) {
			health--;
			if (healthAtZero()) {
				this.destroy(); //Trigger destruction logic when health reaches zero.
			}
		}
	}

	/**
	 * Calculates the X-coordinate for a projectile based on an offset.
	 *
	 * @param xPositionOffset the offset to adjust the X-coordinate of the projectile.
	 * @return the calculated X-coordinate for the projectile's spawn point.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y-coordinate for a projectile based on an offset.
	 *
	 * @param yPositionOffset the offset to adjust the Y-coordinate of the projectile.
	 * @return the calculated Y-coordinate for the projectile's spawn point.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the plane's health is zero.
	 *
	 * @return true if health is zero, false otherwise.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Gets the current health of the fighter plane.
	 *
	 * @return the current health value.
	 */
	public int getHealth() {
		return health;
	}
		
}

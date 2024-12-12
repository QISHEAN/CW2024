package com.example.demo.actor;

import com.example.demo.projectiles.UserProjectile;
import javafx.geometry.Bounds;

/**
 * Class representing the User's Plane in the game.
 * This plane can move in all directions, fire projectiles, and has health and kill count tracking.
 */
public class UserPlane extends FighterPlane {

	//Constants defining the UserPlane's properties and movement boundaries.
	private static final String DEFAULT_IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double X_LEFT_BOUND = 0;
	private static final double X_RIGHT_BOUND = 800.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 15;
	private static final int HORIZONTAL_VELOCITY = 15;

	// Variables for controlling movement and tracking game state.
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;
	private int killCount;
	private final int initialHealth;

	/**
	 * Default constructor using the default image "userplane.png".
	 *
	 * @param initialHealth The initial health of the UserPlane.
	 */
	public UserPlane(int initialHealth) {
		this(DEFAULT_IMAGE_NAME, initialHealth);
	}

	/**
	 * Constructor allowing a custom image name. Primarily used for testing or customization.
	 *
	 * @param imageName     The name or path of the image representing the UserPlane.
	 * @param initialHealth The initial health of the UserPlane.
	 */
	public UserPlane(String imageName, int initialHealth) {
		super(imageName, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		this.initialHealth = initialHealth;
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		killCount = 0;
	}

	/**
	 * Updates the position of the UserPlane based on its current movement state.
	 * Ensures the plane stays within defined movement boundaries.
	 */
	@Override
	public void updatePosition() {
		//Handle vertical movement
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}

		//Handle horizontal movement
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the actor's state, including position and bounding box.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateBoundingBox();
	}

	/**
	 * Resets the UserPlane to its initial state, including position, health, and kill count.
	 */
	public void reset() {
		setHealth(initialHealth);
		setTranslateX(0);
		setTranslateY(0);
		resetKillCount();
		stopVerticalMovement();
		stopHorizontalMovement();
	}

	/**
	 * Resets the kill count to zero.
	 */
	public void resetKillCount() {
		this.killCount = 0;
	}

	/**
	 * Fires a projectile from the UserPlane.
	 *
	 * @return A new UserProjectile instance.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(50), getProjectileYPosition(25));
	}

	/**
	 * Checks if the plane is currently moving vertically.
	 *
	 * @return True if moving vertically, false otherwise.
	 */
	protected boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	/**
	 * Checks if the plane is currently moving horizontally.
	 *
	 * @return True if moving horizontally, false otherwise.
	 */
	protected boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * Initiates upward movement for the plane.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Initiates downward movement for the plane.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Initiates leftward movement for the plane.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Initiates rightward movement for the plane.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops all vertical movement for the plane.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops all horizontal movement for the plane.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the bounding box for the plane, adjusting for offsets and scaling.
	 * Used for accurate collision detection.
	 */
	@Override
	public void updateBoundingBox() {

		double offsetX = 10;
		double offsetY = 50;
		double scaleWidth = 0.9;
		double scaleHeight = 0.3;

		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX() + offsetX);
		boundingBox.setY(bounds.getMinY() + offsetY);
		boundingBox.setWidth(bounds.getWidth() * scaleWidth);
		boundingBox.setHeight(bounds.getHeight() * scaleHeight);
	}

	/**
	 * Increments the kill count by a specified value.
	 *
	 * @param count The number of kills to add to the current count.
	 */
	public void incrementKillCount(int count) {
		this.killCount += count;
	}

	/**
	 * Retrieves the current kill count of the UserPlane.
	 *
	 * @return The current kill count.
	 */
	public int getKillCount() {
		return this.killCount;
	}
}

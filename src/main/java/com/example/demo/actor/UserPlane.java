package com.example.demo.actor;

import com.example.demo.projectiles.UserProjectile;
import javafx.geometry.Bounds;

/**
 * Class representing the User's Plane in the game.
 */
public class UserPlane extends FighterPlane {

	private static final String DEFAULT_IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double X_LEFT_BOUND = 0;
	private static final double X_RIGHT_BOUND = 800.0;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 12;
	private static final int HORIZONTAL_VELOCITY = 12;
	private int verticalVelocityMultiplier;
	private int horizontalVelocityMultiplier;

	private int killCount;
	private final int initialHealth;

	/**
	 * Default constructor using "userplane.png" as the image.
	 *
	 * @param initialHealth Initial health of the UserPlane.
	 */
	public UserPlane(int initialHealth) {
		this(DEFAULT_IMAGE_NAME, initialHealth);
	}

	/**
	 * Constructor allowing custom image names. Primarily used for testing.
	 *
	 * @param imageName     The name/path of the image to represent the UserPlane.
	 * @param initialHealth Initial health of the UserPlane.
	 */
	public UserPlane(String imageName, int initialHealth) {
		super(imageName, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		this.initialHealth = initialHealth;
		verticalVelocityMultiplier = 0;
		horizontalVelocityMultiplier = 0;
		killCount = 0;
	}

	@Override
	public void updatePosition() {
		if (isMovingVertically()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}

		// Update horizontal position
		if (isMovingHorizontally()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX < X_LEFT_BOUND || newPositionX > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	@Override
	public void updateActor() {
		updatePosition();
		updateBoundingBox();
	}

	/**
	 * Resets the UserPlane to its initial state.
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

	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(50), getProjectileYPosition(25));
	}

	protected boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	protected boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	/**
	 * Initiates upward movement.
	 */
	public void moveUp() {
		verticalVelocityMultiplier = -1;
	}

	/**
	 * Initiates downward movement.
	 */
	public void moveDown() {
		verticalVelocityMultiplier = 1;
	}

	/**
	 * Initiates leftward movement.
	 */
	public void moveLeft() {
		horizontalVelocityMultiplier = -1;
	}

	/**
	 * Initiates rightward movement.
	 */
	public void moveRight() {
		horizontalVelocityMultiplier = 1;
	}

	/**
	 * Stops vertical movement.
	 */
	public void stopVerticalMovement() {
		verticalVelocityMultiplier = 0;
	}

	/**
	 * Stops horizontal movement.
	 */
	public void stopHorizontalMovement() {
		horizontalVelocityMultiplier = 0;
	}

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
	 * Increments the kill count by the specified amount.
	 *
	 * @param count The number of kills to add.
	 */
	public void incrementKillCount(int count) {
		this.killCount += count;
	}

	/**
	 * Retrieves the current kill count.
	 *
	 * @return The kill count.
	 */
	public int getKillCount() {
		return this.killCount;
	}
}

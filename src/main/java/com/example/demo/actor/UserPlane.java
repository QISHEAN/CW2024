package com.example.demo.actor;

import com.example.demo.projectiles.UserProjectile;
import javafx.geometry.Bounds;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
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

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
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

	public void reset() {
		setHealth(initialHealth);
		setTranslateX(0);
		setTranslateY(0);
		resetKillCount();
		stopVerticalMovement();
		stopHorizontalMovement();
	}

	public void resetKillCount() {
		this.killCount = 0;
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(50), getProjectileYPosition(25));
	}

	private boolean isMovingVertically() {
		return verticalVelocityMultiplier != 0;
	}

	private boolean isMovingHorizontally() {
		return horizontalVelocityMultiplier != 0;
	}

	public void moveUp() { verticalVelocityMultiplier = -1; }
	public void moveDown() { verticalVelocityMultiplier = 1; }
	public void moveLeft() { horizontalVelocityMultiplier = -1; }
	public void moveRight() { horizontalVelocityMultiplier = 1; }

	public void stopVerticalMovement() { verticalVelocityMultiplier = 0; }
	public void stopHorizontalMovement() { horizontalVelocityMultiplier = 0; }

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

	public void incrementKillCount(int count) {
		this.killCount += count;
	}

	public int getKillCount() {
		return this.killCount;
	}
}

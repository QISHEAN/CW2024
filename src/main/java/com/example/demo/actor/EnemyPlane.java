package com.example.demo.actor;

import com.example.demo.projectiles.EnemyProjectile;
import javafx.geometry.Bounds;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "enemyplane.png";
	private static final int IMAGE_HEIGHT = 150;
	private double horizontalVelocity;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos, int health) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, health);
		this.horizontalVelocity = -6;
	}

	@Override
	public void updatePosition() {
		moveHorizontally(horizontalVelocity);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public void updateBoundingBox() {
		double offsetX = 5;    // Adjust as needed
		double offsetY = 45;   // Adjust as needed
		double scaleWidth = 0.9;
		double scaleHeight = 0.4;

		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX() + offsetX);
		boundingBox.setY(bounds.getMinY() + offsetY);
		boundingBox.setWidth(bounds.getWidth() * scaleWidth);
		boundingBox.setHeight(bounds.getHeight() * scaleHeight);
	}

	public void setSpeed(double speed) {
		this.horizontalVelocity = -speed; // Negative for leftward movement
	}

	public double getSpeed() {
		return -this.horizontalVelocity; // Return positive speed value
	}
}

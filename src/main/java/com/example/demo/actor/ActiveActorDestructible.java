package com.example.demo.actor;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;
	protected Rectangle boundingBox;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;

		// Initialize the bounding box
		boundingBox = new Rectangle();
		boundingBox.setStroke(Color.RED); // For visibility during debugging
		boundingBox.setFill(Color.TRANSPARENT);
		updateBoundingBox();
	}

	@Override
	public abstract void updatePosition();

	public abstract void updateActor();

	@Override
	public abstract void takeDamage();

	@Override
	public void destroy() {
		setDestroyed();
	}

	protected void setDestroyed() {
		this.isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	// Method to update the bounding box
	public void updateBoundingBox() {
		// Get the bounds of the image
		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX());
		boundingBox.setY(bounds.getMinY());
		boundingBox.setWidth(bounds.getWidth());
		boundingBox.setHeight(bounds.getHeight());
	}

	// Getter for the bounding box
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
}

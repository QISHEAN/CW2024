package com.example.demo.actor;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Abstract class representing a destructible active actor in the game.
 * This class extends ActiveActor and implements the Destructible interface,
 * adding health management and collision detection capabilities.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	//Flag indicating whether the actor is destroyed.
	private boolean isDestroyed;

	//The current health of the actor.
	protected int health;

	//A rectangle representing the actor's bounding box for collision detection.
	protected Rectangle boundingBox;

	/**
	 * Constructor to initialize a destructible active actor with an image, size, and position.
	 *
	 * @param imageName    the name of the image file to load for this actor
	 * @param imageHeight  the height of the image (aspect ratio will be preserved)
	 * @param initialXPos  the initial X position of the actor
	 * @param initialYPos  the initial Y position of the actor
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		isDestroyed = false;

		//Initialize the bounding box for collision detection.
		boundingBox = new Rectangle();
		boundingBox.setStroke(Color.RED); // For visibility during debugging
		boundingBox.setFill(Color.TRANSPARENT);
		updateBoundingBox();
	}

	/**
	 * Abstract method to update the position of the actor.
	 * Subclasses must implement specific movement behavior.
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Abstract method to update the actor's state.
	 * Subclasses must define the logic for how the actor behaves in each frame.
	 */
	public abstract void updateActor();

	/**
	 * Abstract method to handle damage taken by the actor.
	 * Subclasses must define how damage affects the actor.
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Marks the actor as destroyed and performs any necessary cleanup operations.
	 */
	@Override
	public void destroy() {
		setDestroyed();
	}

	/**
	 * Sets the actor's destroyed state to true.
	 */
	protected void setDestroyed() {
		this.isDestroyed = true;
	}

	/**
	 * Checks whether the actor is destroyed.
	 *
	 * @return true if the actor is destroyed, false otherwise
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}

	/**
	 * Updates the bounding box to match the actor's current position and size.
	 * This is essential for accurate collision detection.
	 */
	public void updateBoundingBox() {
		//Get the current bounds of the actor in the parent container.
		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX());
		boundingBox.setY(bounds.getMinY());
		boundingBox.setWidth(bounds.getWidth());
		boundingBox.setHeight(bounds.getHeight());
	}

	/**
	 * Retrieves the bounding box of the actor for collision detection.
	 *
	 * @return the Rectangle representing the actor's bounding box
	 */
	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	/**
	 * Removes the actor and its bounding box from the scene graph.
	 * Marks the actor as destroyed and performs any additional cleanup if needed.
	 *
	 * @param root the Group representing the root of the scene graph
	 */
	public void removePlane(Group root) {
		root.getChildren().remove(this); // Remove the actor's graphical representation.
		root.getChildren().remove(this.getBoundingBox()); // Remove the bounding box.
		setDestroyed(); // Mark the actor as destroyed.
	}

	/**
	 * Sets the actor's health.
	 *
	 * @param health the new health value for the actor
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Retrieves the actor's current health.
	 *
	 * @return the current health of the actor
	 */
	public int getHealth() {
		return this.health;
	}
}

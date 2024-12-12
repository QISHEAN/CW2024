package com.example.demo.actor;

import javafx.scene.image.*;

import java.util.Objects;

/**
 * Abstract class representing an active actor in the game. This class extends ImageView to represent graphical elements.
 */
public abstract class ActiveActor extends ImageView {

	//Path to the folder containing images for active actors.
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructor to initialize an ActiveActor with a specific image, size, and position.
	 *
	 * @param imageName    the name of the image file to load for this actor
	 * @param imageHeight  the height of the image (aspect ratio will be preserved)
	 * @param initialXPos  the initial X position of the actor
	 * @param initialYPos  the initial Y position of the actor
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		//Load the image for the actor, ensuring the file exists.
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));

		//Set the initial position of the actor.
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);

		//Set the image height and preserve its aspect ratio.
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method to update the position of the actor. This method must be implemented
	 * by subclasses to define the actor's movement behavior.
	 */
	public abstract void updatePosition();

	/**
	 * Moves the actor horizontally by the specified amount.
	 *
	 * @param horizontalMove the amount to move horizontally (positive for right, negative for left)
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by the specified amount.
	 *
	 * @param verticalMove the amount to move vertically (positive for down, negative for up)
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}

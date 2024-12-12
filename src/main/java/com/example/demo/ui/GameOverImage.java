package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents the "Game Over" image displayed when the player loses the game.
 * Extends `ImageView` to display an image with predefined properties.
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/gameover.png"; // Path to the "Game Over" image file.
	private static final double DEFAULT_WIDTH = 500; // Default width for resizing the image.

	/**
	 * Constructor to initialize the `GameOverImage` at a specified position.
	 *
	 * @param xPosition The X coordinate where the image should be placed.
	 * @param yPosition The Y coordinate where the image should be placed.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		// Load and set the "Game Over" image.
		setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));

		// Set the layout position of the image.
		setLayoutX(xPosition);
		setLayoutY(yPosition);

		// Preserve the aspect ratio of the image during resizing.
		setPreserveRatio(true);

		// Set a default fit width for the image.
		setFitWidth(DEFAULT_WIDTH); // Adjust width as necessary.
	}
}

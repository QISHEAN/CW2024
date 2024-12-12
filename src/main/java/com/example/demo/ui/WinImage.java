package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents a "You Win" image displayed when the player wins the game.
 * Extends `ImageView` to visually represent the victory screen.
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/youwin.png"; // Path to the "You Win" image file.
	private static final int HEIGHT = 500; // Height of the "You Win" image.
	private static final int WIDTH = 600; // Width of the "You Win" image.

	/**
	 * Constructor to initialize the `WinImage` at a specified position.
	 * Sets up the image properties such as dimensions, visibility, and layout position.
	 *
	 * @param xPosition The X coordinate where the image should be displayed.
	 * @param yPosition The Y coordinate where the image should be displayed.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm())); // Load the "You Win" image.
		this.setVisible(false); // Initially, the image is not visible.
		this.setFitHeight(HEIGHT); // Set the height of the image.
		this.setFitWidth(WIDTH); // Set the width of the image.
		this.setLayoutX(xPosition); // Set the X position of the image.
		this.setLayoutY(yPosition); // Set the Y position of the image.
	}

	/**
	 * Makes the "You Win" image visible on the screen.
	 * Used to indicate the player's victory.
	 */
	public void showWinImage() {
		this.setVisible(true); // Display the "You Win" image.
	}
}

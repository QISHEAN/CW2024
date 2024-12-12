package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * Represents a shield image displayed when the boss activates its shield.
 * Extends `ImageView` to visually represent the shield.
 */
public class ShieldImage extends ImageView {

	private static final int SHIELD_SIZE = 150; // Default size for the shield image.

	/**
	 * Constructor to initialize the `ShieldImage`.
	 * Sets the shield's size, position, image, and visibility properties.
	 */
	public ShieldImage() {
		this.setLayoutX(1150); // Initial X position of the shield.
		this.setLayoutY(5); // Initial Y position of the shield.
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/shield.png")).toExternalForm())); // Load the shield image.
		this.setVisible(false); // Initially, the shield is not visible.
		this.setFitHeight(SHIELD_SIZE); // Set the height of the shield image.
		this.setFitWidth(SHIELD_SIZE); // Set the width of the shield image.
	}

	/**
	 * Makes the shield image visible on the screen.
	 * Used when the shield is activated.
	 */
	public void showShield() {
		this.setVisible(true); // Display the shield image.
	}

	/**
	 * Hides the shield image from the screen.
	 * Used when the shield is deactivated.
	 */
	public void hideShield() {
		this.setVisible(false); // Hide the shield image.
	}
}

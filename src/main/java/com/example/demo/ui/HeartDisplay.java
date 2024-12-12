package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * Represents a display of hearts used to show the player's remaining health.
 * The hearts are displayed in a horizontal layout.
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/heart.png"; // Path to the heart image file.
	private static final int HEART_HEIGHT = 50; // Height of each heart image.
	private static final int INDEX_OF_FIRST_ITEM = 0; // Index of the first heart in the container.
	private HBox container; // Container to hold the heart images.
	private final double containerXPosition; // X position of the container.
	private final double containerYPosition; // Y position of the container.
	private final int numberOfHeartsToDisplay; // Number of hearts to display initially.

	/**
	 * Constructor to initialize the `HeartDisplay` at a specified position and number of hearts.
	 *
	 * @param xPosition       The X coordinate of the heart display container.
	 * @param yPosition       The Y coordinate of the heart display container.
	 * @param heartsToDisplay The number of hearts to display.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer(); // Initialize the container for the hearts.
		initializeHearts(); // Populate the container with hearts.
	}

	/**
	 * Initializes the container (an `HBox`) for holding the heart images.
	 * Sets the container's layout position.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Populates the container with the specified number of heart images.
	 * Each heart is an `ImageView` initialized with the heart image.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));
			heart.setFitHeight(HEART_HEIGHT); // Set the height of the heart.
			heart.setPreserveRatio(true); // Preserve the aspect ratio of the image.
			container.getChildren().add(heart); // Add the heart to the container.
		}
	}

	/**
	 * Removes one heart from the container, representing a loss of health.
	 * Removes the first heart if the container is not empty.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty()) {
			container.getChildren().remove(INDEX_OF_FIRST_ITEM); // Remove the first heart.
		}
	}

	/**
	 * Retrieves the container holding the heart images.
	 *
	 * @return The `HBox` container of heart images.
	 */
	public HBox getContainer() {
		return container;
	}
}

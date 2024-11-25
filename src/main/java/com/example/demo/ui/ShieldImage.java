package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {

	private static final int SHIELD_SIZE = 150;

	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(1150);
		this.setLayoutY(5);
		//this.setImage(new Image(IMAGE_NAME));
		this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	public void showShield() {
		this.setVisible(true);
	}

	public void hideShield() {
		this.setVisible(false);
	}

}
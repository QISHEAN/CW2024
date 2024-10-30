package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
	
	private static final String IMAGE_NAME = "/images/shield.png";
	private static final int SHIELD_SIZE = 200;
	
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
<<<<<<< HEAD
		//this.setImage(new Image(IMAGE_NAME));this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.png").toExternalForm()));
=======
		//this.setImage(new Image(IMAGE_NAME));this.setImage(new Image(getClass().getResource("/com/example/demo/images/shield.jpg").toExternalForm()));
>>>>>>> 2eb84223281f72f29507b470a5e3ed0338b568b7
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

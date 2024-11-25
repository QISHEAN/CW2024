package com.example.demo.levelview;

import com.example.demo.ui.ShieldImage;
import javafx.scene.Group;

public class LevelViewLevelTwo extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;

	public LevelViewLevelTwo(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay);
		this.root = root;
		this.shieldImage = new ShieldImage();
		addImagesToRoot();
	}

	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	public void showShield() {
		shieldImage.showShield();
	}

	public void hideShield() {
		shieldImage.hideShield();
	}

}

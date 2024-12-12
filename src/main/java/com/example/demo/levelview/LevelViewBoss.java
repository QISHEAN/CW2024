package com.example.demo.levelview;

import com.example.demo.ui.ShieldImage;
import javafx.scene.Group;

/**
 * Class representing the level view for levels with a boss.
 * Extends LevelView to include boss-specific UI components, such as the shield image.
 */
public class LevelViewBoss extends LevelView {

	private final Group root;
	private final ShieldImage shieldImage;

	/**
	 * Constructor to initialize the boss level view.
	 *
	 * @param root            The root group for UI components.
	 * @param heartsToDisplay The initial number of hearts to display for the player.
	 */
	public LevelViewBoss(Group root, int heartsToDisplay) {
		super(root, heartsToDisplay,false,null);
		this.root = root;
		this.shieldImage = new ShieldImage();
		addImagesToRoot();
	}

	/**
	 * Adds boss-specific images, such as the shield, to the root group.
	 * Ensures the shield image is part of the visual elements managed by the root.
	 */
	private void addImagesToRoot() {
		root.getChildren().addAll(shieldImage);
	}

	/**
	 * Displays the boss's shield image on the screen.
	 * Ensures the shield is visible during gameplay when activated.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the boss's shield image from the screen.
	 * Ensures the shield is not visible when deactivated.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	} //Hide the shield image.

}

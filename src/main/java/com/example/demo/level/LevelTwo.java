package com.example.demo.level;

import com.example.demo.actor.Boss;
import com.example.demo.levelview.LevelView;
import com.example.demo.levelview.LevelViewLevelTwo;
import com.example.demo.ui.ShieldImage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.util.Duration;
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/level2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;

	private final ShieldImage shieldImage;

	private boolean isBlinking;
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
		this.shieldImage = new ShieldImage();
	}
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene(); // Call the parent method
		getLevelView().showBossHealthDisplay(); // Add this line
		return scene;
	}
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
		getRoot().getChildren().add(getUser().getBoundingBox());
		addEnemyUnit(getBoss());
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (getBoss().isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(getBoss());
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
	}
	protected void updateLevelView() {
		super.updateLevelView();
		if (boss.getIsShield()) {
			if (!shieldImage.isVisible() && !isBlinking) {
				startShieldBlinkAnimation();
			} else {
				updateShieldPosition();
			}
		} else {
			shieldImage.hideShield();
		}
	}
	private void updateShieldPosition() {
		// Position the shield slightly in front and above the boss
		double shieldOffsetX = -10; // Horizontal offset
		double shieldOffsetY = -10; // Vertical offset

		shieldImage.setLayoutX(boss.getLayoutX() + boss.getTranslateX() + shieldOffsetX);
		shieldImage.setLayoutY(boss.getLayoutY() + boss.getTranslateY() + shieldOffsetY);
	}

	private void startShieldBlinkAnimation() {
		isBlinking = true;

		// Create a timeline for blinking animation
		Timeline blinkTimeline = new Timeline(
				new KeyFrame(Duration.millis(300), e -> {
					if (shieldImage.isVisible()) {
						shieldImage.setVisible(false);
					} else {
						shieldImage.setVisible(true);
						shieldImage.setOpacity(0.5); // Semi-transparent while blinking
					}
				})
		);
		blinkTimeline.setCycleCount(8); // Blink 4 times (on and off)
		blinkTimeline.setOnFinished(e -> {
			shieldImage.setVisible(true); // Ensure the shield is visible after blinking
			shieldImage.setOpacity(1.0); // Fully opaque after blinking
			isBlinking = false; // Reset blinking state
		});
		blinkTimeline.play();
	}
}

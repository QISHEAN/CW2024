package com.example.demo.level;

import com.example.demo.actor.Boss;
import com.example.demo.levelview.LevelView;
import com.example.demo.levelview.LevelViewBoss;
import com.example.demo.managers.SoundManager;
import com.example.demo.ui.ShieldImage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represents the Boss level in the game.
 * Extends LevelParent and includes functionality for managing the Boss, shield animations, and game state transitions.
 */
public class BossLevel extends LevelParent {

	//Path to the background image for the level.
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";

	//Initial health for the player.
	private static final int PLAYER_INITIAL_HEALTH = 5;

	//Boss character for this level.
	private final Boss boss;

	//Shield image displayed when the Boss's shield is active.
	private final ShieldImage shieldImage;

	//Tracks whether the shield is blinking during activation.
	private boolean isBlinking;

	/**
	 * Constructor to initialize the BossLevel.
	 *
	 * @param screenWidth  The width of the game screen.
	 * @param screenHeight The height of the game screen.
	 * @param soundManager The SoundManager for managing audio.
	 * @param stage        The primary stage for the application.
	 */
	public BossLevel(double screenWidth, double screenHeight, SoundManager soundManager, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH, soundManager, stage);
		boss = new Boss(); // Instantiate the Boss character.
		this.shieldImage = new ShieldImage(); // Instantiate the shield image.
	}

	/**
	 * Initializes the scene for the Boss level.
	 *
	 * @return The Scene object for this level.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene(); // Call the parent method to set up the scene.
		getLevelView().showBossHealthDisplay(); // Add the Boss's health display to the UI.
		return scene;
	}

	/**
	 * Initializes friendly units for the level.
	 * This method is intentionally left empty for the Boss level.
	 */
	@Override
	protected void initializeFriendlyUnits() {
	}

	/**
	 * Starts the Boss level gameplay.
	 * Focuses the background, plays the timeline, and starts Boss-specific music.
	 */
	@Override
	public void startGame() {
		background.requestFocus(); //Focus on the background for keyboard events.
		timeline.play(); //Start the game timeline.
		soundManager.playBackgroundMusic("/sounds/boss.background.mp3"); //Play Boss-specific music.
	}

	/**
	 * Checks if the game is over by evaluating whether the player or the Boss is destroyed.
	 * If the player is destroyed, the player loses.
	 * If the Boss is destroyed, the player wins.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (getBoss().isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Spawns enemy units in the level. Ensures only the Boss is added as the enemy.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(getBoss()); //Add the Boss as the only enemy.
		}
	}

	/**
	 * Instantiates the level view for the Boss level.
	 *
	 * @return A LevelViewBoss object customized for this level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelViewBoss(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Updates the level view with information about the Boss and its shield.
	 */
	protected void updateLevelView() {
		super.updateLevelView(); //Call the parent method to handle general updates.

		if (boss.getIsShield()) {
			if (!shieldImage.isVisible() && !isBlinking) {
				startShieldBlinkAnimation(); //Start the shield blink animation if not already blinking.
			} else {
				updateShieldPosition(); //Update the shield's position to follow the Boss.
			}
		} else {
			shieldImage.hideShield(); //Hide the shield when it's not active.
		}
	}

	/**
	 * Updates the shield's position to align with the Boss.
	 */
	private void updateShieldPosition() {
		//Position the shield slightly in front and above the Boss.
		double shieldOffsetX = -10; //Horizontal offset.
		double shieldOffsetY = -10; //Vertical offset.

		shieldImage.setLayoutX(boss.getLayoutX() + boss.getTranslateX() + shieldOffsetX);
		shieldImage.setLayoutY(boss.getLayoutY() + boss.getTranslateY() + shieldOffsetY);
	}

	/**
	 * Starts a blinking animation for the shield to indicate activation.
	 */
	private void startShieldBlinkAnimation() {
		isBlinking = true;

		//Create a timeline for the blinking animation.
		Timeline blinkTimeline = new Timeline(
				new KeyFrame(Duration.millis(300), e -> {
					if (shieldImage.isVisible()) {
						shieldImage.setVisible(false); //Hide the shield on every other frame.
					} else {
						shieldImage.setVisible(true); //Show the shield.
						shieldImage.setOpacity(0.5); //Semi-transparent while blinking.
					}
				})
		);
		blinkTimeline.setCycleCount(8); //Blink 4 times (on and off).
		blinkTimeline.setOnFinished(e -> {
			shieldImage.setVisible(true); //Ensure the shield is visible after blinking.
			shieldImage.setOpacity(1.0); //Set to fully opaque after blinking.
			isBlinking = false; //Reset the blinking state.
		});
		blinkTimeline.play(); //Start the animation.
	}
}

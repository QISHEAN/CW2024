package com.example.demo.levelview;

import com.example.demo.ui.GameOverImage;
import com.example.demo.ui.HeartDisplay;
import com.example.demo.ui.ShieldImage;
import com.example.demo.ui.WinImage;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class representing the visual elements of a level.
 * Manages UI components such as health, kill counts, shields, and boss health displays.
 */
public class LevelView {

	//Constants for UI component positioning.
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = 355;
	private static final int LOSS_SCREEN_Y_POSITION = 175;

	//Root group for all visual elements.
	protected final Group root;

	//UI components.
	private final HeartDisplay heartDisplay;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final ShieldImage shieldImage;
	private final Text bossHealthText;
	private final Pane bossHealthPane;
	private final Text killCountText;

	private boolean killCountDisplayVisible;
	private Label warningLabel; //Optional warning label for specific levels

	/**
	 * Constructor for LevelView.
	 *
	 * @param root            The root group for UI components.
	 * @param heartsToDisplay The initial number of hearts to display.
	 * @param hasWarningLabel Whether a warning label should be displayed.
	 * @param warningMessage  The message for the warning label (optional).
	 */
	public LevelView(Group root, int heartsToDisplay, boolean hasWarningLabel, String warningMessage) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.shieldImage = new ShieldImage();

		//Initialize boss health text.
		this.bossHealthText = new Text();
		this.bossHealthText.setFill(Color.WHITE);
		this.bossHealthText.setX(1050);
		this.bossHealthText.setY(50);
		this.bossHealthText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.bossHealthPane = new Pane();
		this.bossHealthPane.getChildren().add(bossHealthText);

		//Initialize kill count text.
		this.killCountText = new Text();
		this.killCountText.setFill(Color.WHITE);
		this.killCountText.setX(1050);
		this.killCountText.setY(50);
		this.killCountText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.killCountText.setText(""); // Initially empty
		this.killCountDisplayVisible = false;

		if (hasWarningLabel) {
			initializeWarningLabel(warningMessage);
		}
	}

	/**
	 * Initializes the warning label with the given message and styles it.
	 *
	 * @param warningMessage The message to display on the warning label.
	 */
	private void initializeWarningLabel(String warningMessage) {
		warningLabel = new Label(warningMessage);
		warningLabel.setFont(new Font("Arial", 20));
		warningLabel.setTextFill(Color.RED);
		warningLabel.setLayoutX(300);
		warningLabel.setLayoutY(20);
		root.getChildren().add(warningLabel);
	}

	/**
	 * Displays the player's heart (health) icons on the screen.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image when the player wins the level.
	 * Ensures the win image is only added once.
	 */
	public void showWinImage() {
		if (!root.getChildren().contains(winImage)) { //Ensure no duplicates
			root.getChildren().add(winImage); //Add the win image to the root
		}
		winImage.showWinImage(); //Make the image visible
	}


	/**
	 * Displays the game-over image when the player loses the level.
	 * Ensures the game-over image is only added once.
	 */
	public void showGameOverImage() {
		if (!root.getChildren().contains(gameOverImage)) {
			root.getChildren().add(gameOverImage);
		}
	}

	/**
	 * Removes hearts from the health display based on the player's remaining health.
	 *
	 * @param heartsRemaining The number of hearts the player still has.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	/**
	 * Displays the kill count text on the screen.
	 * Ensures the display is only shown once.
	 */
	public void showKillCountDisplay() {
		if (!killCountDisplayVisible) {
			root.getChildren().add(killCountText);
			killCountDisplayVisible = true;
		}
	}

	/**
	 * Updates the kill count text with the player's current progress.
	 * Optionally updates the warning label dynamically.
	 *
	 * @param currentKills     The current number of kills the player has achieved.
	 * @param totalKillsToWin  The total kills required to win the level.
	 */
	public void updateKillCountDisplay(int currentKills, int totalKillsToWin) {
		killCountText.setText("Kills: " + currentKills + "/" + totalKillsToWin);

		// Update warning message dynamically if applicable
		if (warningLabel != null && currentKills > totalKillsToWin / 2) {
			warningLabel.setText("You're halfway there! Keep going!");
		}
	}

	/**
	 * Displays the shield image on the screen.
	 */
	public void showShield() {
		shieldImage.showShield();
	}

	/**
	 * Hides the shield image from the screen.
	 */
	public void hideShield() {
		shieldImage.hideShield();
	}

	/**
	 * Updates the boss health text with the boss's current health.
	 *
	 * @param bossHealth The current health of the boss.
	 */
	public void updateBossHealthText(int bossHealth) {
		bossHealthText.setText("Boss HP: " + bossHealth);
	}

	/**
	 * Displays the boss health text on the screen.
	 */
	public void showBossHealthDisplay() {
		root.getChildren().add(bossHealthPane);
	}
}

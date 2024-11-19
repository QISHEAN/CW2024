package com.example.demo;

import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;


public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = 355;
	private static final int LOSS_SCREEN_Y_POSISITION = 175;
	private static final int SHIELD_X_POSITION = 1150;
	private static final int SHIELD_Y_POSITION = 500;
	private final ShieldImage shieldImage;
	protected final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	private final Text bossHealthText;
	private final Pane bossHealthPane;

	private final Text killCountText;
	private boolean killCountDisplayVisible;

	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSISITION);
		this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);


		this.bossHealthText = new Text();
		this.bossHealthText.setFill(Color.WHITE);
		this.bossHealthText.setX(1050);
		this.bossHealthText.setY(50); // Display slightly above the shield
		this.bossHealthText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.bossHealthPane = new Pane();
		this.bossHealthPane.getChildren().add(bossHealthText);
		this.killCountText = new Text();
		this.killCountText.setFill(Color.WHITE);
		this.killCountText.setX(1050); // Adjust position as needed
		this.killCountText.setY(50); // Adjust position as needed
		this.killCountText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.killCountText.setText(""); // Initially empty
		this.killCountDisplayVisible = false; // Initially not visible

	}

	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	public void showGameOverImage() {
		root.getChildren().add(gameOverImage);
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	// Method to show the kill count display
	public void showKillCountDisplay() {
		if (!killCountDisplayVisible) {
			root.getChildren().add(killCountText);
			killCountDisplayVisible = true;
		}
	}

	// Method to update the kill count display
	public void updateKillCountDisplay(int currentKills, int totalKills) {
		killCountText.setText("Kills: " + currentKills + "/" + totalKills);
	}

	public void showShield() {
		shieldImage.showShield();
	}
	public void hideShield() {
		shieldImage.hideShield();
	}
	public void updateBossHealthText(int bossHealth) {
		bossHealthText.setText("Boss HP: " + bossHealth);
	}

	// Method to add the boss health display pane to the root
	public void showBossHealthDisplay() {
		root.getChildren().add(bossHealthPane);
	}

}

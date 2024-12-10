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

public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 355;
	private static final int WIN_IMAGE_Y_POSITION = 175;
	private static final int LOSS_SCREEN_X_POSITION = 355;
	private static final int LOSS_SCREEN_Y_POSITION = 175;

	protected final Group root;
	private final HeartDisplay heartDisplay;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final ShieldImage shieldImage;
	private final Text bossHealthText;
	private final Pane bossHealthPane;
	private final Text killCountText;

	private boolean killCountDisplayVisible;
	private Label warningLabel; // Optional warning label for specific levels

	public LevelView(Group root, int heartsToDisplay, boolean hasWarningLabel, String warningMessage) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.shieldImage = new ShieldImage();

		this.bossHealthText = new Text();
		this.bossHealthText.setFill(Color.WHITE);
		this.bossHealthText.setX(1050);
		this.bossHealthText.setY(50); // Display slightly above the shield
		this.bossHealthText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.bossHealthPane = new Pane();
		this.bossHealthPane.getChildren().add(bossHealthText);

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

	private void initializeWarningLabel(String warningMessage) {
		warningLabel = new Label(warningMessage);
		warningLabel.setFont(new Font("Arial", 20));
		warningLabel.setTextFill(Color.RED);
		warningLabel.setLayoutX(300);
		warningLabel.setLayoutY(20);
		root.getChildren().add(warningLabel);
	}

	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		if (!root.getChildren().contains(winImage)) { // Ensure no duplicates
			root.getChildren().add(winImage); // Add the win image to the root
		}
		winImage.showWinImage(); // Make the image visible
	}


	public void showGameOverImage() {
		if (!root.getChildren().contains(gameOverImage)) {
			root.getChildren().add(gameOverImage);
		}
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

	public void showKillCountDisplay() {
		if (!killCountDisplayVisible) {
			root.getChildren().add(killCountText);
			killCountDisplayVisible = true;
		}
	}

	public void updateKillCountDisplay(int currentKills, int totalKillsToWin) {
		killCountText.setText("Kills: " + currentKills + "/" + totalKillsToWin);

		// Update warning message dynamically if applicable
		if (warningLabel != null && currentKills > totalKillsToWin / 2) {
			warningLabel.setText("You're halfway there! Keep going!");
		}
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

	public void showBossHealthDisplay() {
		root.getChildren().add(bossHealthPane);
	}
}

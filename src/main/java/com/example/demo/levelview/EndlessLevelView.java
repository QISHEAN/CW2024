package com.example.demo.levelview;

import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Class representing the level view for endless mode.
 */
public class EndlessLevelView extends LevelView {

    private static final Logger logger = Logger.getLogger(EndlessLevelView.class.getName());

    private final Text killCountText;

    public EndlessLevelView(Group root, int playerInitialHealth) {
        super(root, playerInitialHealth);
        this.killCountText = new Text();
        initializeKillCountDisplay();
    }

    /**
     * Initializes and displays the kill count on the screen.
     */
    private void initializeKillCountDisplay() {
        killCountText.setText("Score: 0");
        // Adjust the position as needed
        killCountText.setLayoutX(1100);
        killCountText.setLayoutY(50);
        killCountText.setStyle("-fx-font-size: 24px; -fx-fill: yellow;");
        root.getChildren().add(killCountText);
        killCountText.toFront(); // Ensure it's on top
    }

    /**
     * Updates the kill count display with the current kill count.
     *
     * @param killCount the current kill count.
     */
    public void updateKillCount(int killCount) {
        killCountText.setText("Score: " + killCount);
        killCountText.toFront(); // Ensure it's on top after update
    }

    @Override
    public void showHeartDisplay() {
        super.showHeartDisplay();
        // Additional UI setup if needed
    }

    /**
     * Shows the game over image and navigates to leaderboard after a delay.
     */
    public void showGameOverImage() {
        try {
            Image gameOverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/gameover.png")).toExternalForm());
            ImageView gameOverView = new ImageView(gameOverImage);
            gameOverView.setPreserveRatio(true);
            gameOverView.setFitWidth(400); // Adjust size as needed

            // Center the image
            StackPane overlay = new StackPane(gameOverView);
            overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Semi-transparent background
            overlay.setPrefSize(root.getScene().getWidth(), root.getScene().getHeight());

            root.getChildren().add(overlay);
            overlay.toFront(); // Ensure it's on top

            // Delay before navigating to leaderboard
            PauseTransition pause = new PauseTransition(Duration.seconds(3)); // 3-second delay
            pause.setOnFinished(event -> navigateToLeaderboard());
            pause.play();
        } catch (NullPointerException e) {
            logger.severe("Game over image not found.");
            // Navigate immediately if image not found
            navigateToLeaderboard();
        }
    }

    /**
     * Navigates to the Leaderboard screen.
     */
    private void navigateToLeaderboard() {
        logger.info("Navigating to Leaderboard...");
        // Implement navigation logic here, e.g., changing the scene or calling a navigation manager
    }

    /**
     * Resets the kill count display.
     */
    public void resetKillCountDisplay() {
        killCountText.setText("Score: 0");
        killCountText.toFront(); // Ensure it's on top after reset
    }
}

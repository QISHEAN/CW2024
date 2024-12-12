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
 * Manages the display of UI elements such as the kill count and game-over screen.
 */
public class EndlessLevelView extends LevelView {

    private static final Logger logger = Logger.getLogger(EndlessLevelView.class.getName());

    private final Text killCountText;

    /**
     * Constructor to initialize the endless mode level view.
     *
     * @param root                The root group for the level's UI components.
     * @param playerInitialHealth The initial health of the player.
     */
    public EndlessLevelView(Group root, int playerInitialHealth) {
        super(root, playerInitialHealth,false,null);
        this.killCountText = new Text();
        initializeKillCountDisplay();
    }

    /**
     * Initializes and displays the kill count on the screen.
     * Sets the initial score to zero and positions the text element.
     */
    private void initializeKillCountDisplay() {
        killCountText.setText("Score: 0"); // Set initial score text.
        killCountText.setLayoutX(1100); // Position X coordinate.
        killCountText.setLayoutY(50); // Position Y coordinate.
        killCountText.setStyle("-fx-font-size: 24px; -fx-fill: yellow;"); // Style the text.
        root.getChildren().add(killCountText); // Add the text to the root group.
        killCountText.toFront(); // Ensure the text is displayed on top.
    }

    /**
     * Updates the kill count display with the current score.
     * Ensures the text element stays on top after the update.
     *
     * @param killCount The current kill count to display.
     */
    public void updateKillCount(int killCount) {
        killCountText.setText("Score: " + killCount);
        killCountText.toFront(); //Ensure it's on top after update
    }

    /**
     * Displays the player's heart/health display.
     * Overrides the parent method to customize the heart display for endless mode.
     */
    @Override
    public void showHeartDisplay() {
        super.showHeartDisplay();//Call the parent method to handle heart display.
    }

    /**
     * Displays a game-over screen with an overlay image.
     * After a delay, navigates to the leaderboard screen.
     * If the image cannot be loaded, navigates to the leaderboard immediately.
     */
    public void showGameOverImage() {
        try {
            Image gameOverImage = new Image(Objects.requireNonNull(getClass().getResource("/com/example/demo/images/gameover.png")).toExternalForm());
            ImageView gameOverView = new ImageView(gameOverImage);
            gameOverView.setPreserveRatio(true);
            gameOverView.setFitWidth(400); //Adjust size as needed

            // Center the image
            StackPane overlay = new StackPane(gameOverView);
            overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);"); // Semi-transparent background
            overlay.setPrefSize(root.getScene().getWidth(), root.getScene().getHeight());

            root.getChildren().add(overlay);
            overlay.toFront(); //Ensure it's on top

            //Delay before navigating to leaderboard
            PauseTransition pause = new PauseTransition(Duration.seconds(3)); // 3-second delay
            pause.setOnFinished(event -> navigateToLeaderboard());
            pause.play();
        } catch (NullPointerException e) {
            logger.severe("Game over image not found.");
            //Navigate immediately if image not found
            navigateToLeaderboard();
        }
    }

    /**
     * Navigates to the leaderboard screen.
     * Placeholder for actual navigation logic to be implemented later.
     */
    private void navigateToLeaderboard() {
        logger.info("Navigating to Leaderboard...");
        //Implement navigation logic here, e.g., changing the scene or calling a navigation manager
    }

    /**
     * Resets the kill count display to zero.
     * Ensures the text element stays on top after the reset.
     */
    public void resetKillCountDisplay() {
        killCountText.setText("Score: 0");
        killCountText.toFront(); //Ensure it's on top after reset
    }
}

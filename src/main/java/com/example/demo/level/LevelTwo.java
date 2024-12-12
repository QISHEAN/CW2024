package com.example.demo.level;

import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.LevelView; // Use the refactored LevelView class
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Represents Level Two of the game.
 * Features increased difficulty with more enemies, higher health, and faster spawn rates.
 */
public class LevelTwo extends LevelParent {

    // Level-specific constants.
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.level.BossLevel";
    private static final int TOTAL_ENEMIES = 7; // Total number of enemies allowed on screen.
    private static final double ENEMY_SPAWN_PROBABILITY = 0.25; // Probability of spawning new enemies.
    private static final int PLAYER_INITIAL_HEALTH = 5; // Player's initial health.
    private static final int TOTAL_KILLS_TO_WIN = 10; // Kill count target to progress to the next level.

    // Tracks the player's current kill count.
    private int currentKills = 0;

    /**
     * Constructor for Level Two.
     *
     * @param screenWidth  The width of the game screen.
     * @param screenHeight The height of the game screen.
     * @param soundManager The SoundManager instance for handling audio.
     * @param stage        The primary stage for the application.
     */
    public LevelTwo(double screenWidth, double screenHeight, SoundManager soundManager, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH, soundManager, stage);
    }

    /**
     * Checks if the game is over by evaluating player status or kill target.
     * If the player is destroyed, the game ends.
     * If the player reaches the kill target, the next level is loaded.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame(); // Trigger game-over logic.
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL); // Progress to the next level.
        }
    }

    /**
     * Initializes friendly units for Level Two.
     * This method is intentionally left empty but can be extended for additional allies.
     */
    @Override
    protected void initializeFriendlyUnits() {
        // No friendly units initialized in Level Two.
    }

    /**
     * Increments the player's kill count and updates the on-screen display.
     *
     * @param count The number of kills to add.
     */
    @Override
    public void incrementKillCount(int count) {
        currentKills += count;
        getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN); // Update kill count UI.
    }

    /**
     * Initializes the scene for Level Two by setting up UI elements like the kill count display.
     *
     * @return The initialized Scene object.
     */
    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene(); // Call the parent method for common setup.
        getLevelView().showKillCountDisplay(); // Display the kill count UI.
        getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN); // Set the initial kill count.
        return scene;
    }

    /**
     * Spawns enemy units based on the current number of enemies and spawn probability.
     * Adds faster and stronger enemies to increase difficulty.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies(); // Check current enemies on screen.
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition(); // Random Y position.
                EnemyPlane newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 2); // Enemy with higher health.

                newEnemy.setHealth(5); // Set the health of the enemy.
                newEnemy.setSpeed(newEnemy.getSpeed() + 2); // Increase speed for difficulty.

                addEnemyUnit(newEnemy); // Add the enemy to the game.
            }
        }
    }

    /**
     * Creates the LevelView specific to Level Two with customized UI elements and warnings.
     *
     * @return A new LevelView instance for Level Two.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(
                getRoot(),
                PLAYER_INITIAL_HEALTH,
                true, // Enable warning messages.
                "Enemies are stronger! Stay alert!" // Custom warning for Level Two.
        );
    }

    /**
     * Checks if the player has reached the required kill target to win the level.
     *
     * @return True if the player has achieved the target kill count, false otherwise.
     */
    private boolean userHasReachedKillTarget() {
        return currentKills >= TOTAL_KILLS_TO_WIN;
    }
}

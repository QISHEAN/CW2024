package com.example.demo.level;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.EndlessLevelView;
import com.example.demo.levelview.LevelView;
import com.example.demo.managers.LeaderboardManager;
import com.example.demo.managers.SoundManager;
import javafx.stage.Stage;
import java.util.logging.Logger;

/**
 * Class representing the Endless Mode in the game.
 * In this mode, enemies spawn continuously, and difficulty increases over time.
 */
public class EndlessMode extends LevelParent {

    // Logger for debugging and tracking events.
    private static final Logger logger = Logger.getLogger(EndlessMode.class.getName());

    // Configuration constants for the Endless Mode.
    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
    private static final int INITIAL_ENEMIES = 5;
    private static final int MAXIMUM_ENEMIES = 10;
    private static final double INITIAL_SPAWN_RATE = 0.3;
    private static final double ENEMY_Y_UPPER_BOUND = 30;
    private static final double SPAWN_RATE_INCREMENT = 0.02;
    private static final double SPAWN_RATE_CAP = 0.9;

    // Variables for tracking game state.
    private int totalEnemies;  // Total number of enemies to spawn.
    private double spawnRate;  // Probability of spawning a new enemy.
    private int currentWave;   // Current wave of enemies.

    /**
     * Constructor to initialize the Endless Mode.
     *
     * @param soundManager The SoundManager for managing game audio.
     * @param stage        The primary stage for the application.
     */
    public EndlessMode(SoundManager soundManager, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, 1300, 750, 5, soundManager, stage);
        this.totalEnemies = INITIAL_ENEMIES;
        this.spawnRate = INITIAL_SPAWN_RATE;
        this.currentWave = 1;
    }

    /**
     * Instantiates the LevelView for Endless Mode.
     *
     * @return A new instance of EndlessLevelView.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new EndlessLevelView(root, 5);
    }

    /**
     * Checks if the game is over.
     * Saves the score and ends the game if the player's plane is destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            saveScore(getUserKillCount());
            loseGame();
            logger.info("Game over detected. Score saved.");
        }
    }

    /**
     * Increases the difficulty of the game by adjusting the number of enemies and spawn rate.
     * Called after certain milestones, such as every 10 kills.
     */
    private void increaseDifficulty() {
        currentWave++;
        totalEnemies += 2; // Increase the total number of enemies for the wave.
        spawnRate = Math.min(spawnRate + SPAWN_RATE_INCREMENT, SPAWN_RATE_CAP); // Cap the spawn rate.
    }

    /**
     * Spawns enemy units for the Endless Mode.
     * Ensures the number of enemies on screen does not exceed the maximum allowed.
     */
    @Override
    protected void spawnEnemyUnits() {
        int currentEnemies = getCurrentNumberOfEnemies();
        int maxEnemies = Math.min(totalEnemies, MAXIMUM_ENEMIES);

        // Spawn new enemies up to the limit.
        for (int i = 0; i < maxEnemies - currentEnemies; i++) {
            if (Math.random() < spawnRate) {
                double yPos = ENEMY_Y_UPPER_BOUND + Math.random() * getEnemyMaximumYPosition();
                double xPos = getScreenWidth();
                ActiveActorDestructible enemy = createEnemyPlaneOne(xPos, yPos);
                addEnemyUnit(enemy);
            }
        }

        // Increase difficulty after every 10 kills.
        if (getUserKillCount() >= currentWave * 10) {
            increaseDifficulty();
        }
    }

    /**
     * Creates a new EnemyPlane with specified position and default health.
     *
     * @param x The X position of the enemy.
     * @param y The Y position of the enemy.
     * @return A new EnemyPlane instance.
     */
    private ActiveActorDestructible createEnemyPlaneOne(double x, double y) {
        return new EnemyPlane(x, y, 1); // Default health set to 1.
    }

    /**
     * Increments the player's kill count and updates the UI display.
     *
     * @param count The number of kills to add.
     */
    @Override
    protected void incrementKillCount(int count) {
        super.incrementKillCount(count);

        // Update the kill count display.
        if (levelView instanceof EndlessLevelView) {
            ((EndlessLevelView) levelView).updateKillCount(getUserKillCount());
        }
    }

    /**
     * Saves the player's score to the leaderboard.
     *
     * @param score The player's final score.
     */
    private void saveScore(int score) {
        LeaderboardManager.addScore(score);
    }

    /**
     * Cleans up the game state before restarting.
     * Resets variables, clears entities, and resets the player's plane and UI elements.
     */
    private void cleanupGameState() {
        stopGame(); // Stop all animations and timelines.

        // Reset game variables to their initial states.
        totalEnemies = INITIAL_ENEMIES;
        spawnRate = INITIAL_SPAWN_RATE;
        currentWave = 1;

        // Reset the player's plane.
        getUser().reset();

        // Clear all enemies and projectiles.
        entityManager.clearEnemies();
        entityManager.clearAllProjectiles();

        // Reset UI elements for EndlessLevelView.
        if (levelView instanceof EndlessLevelView) {
            ((EndlessLevelView) levelView).updateKillCount(0);
            ((EndlessLevelView) levelView).resetKillCountDisplay();
        }
    }

    /**
     * Restarts the Endless Mode by cleaning up the current state and creating a new instance.
     */
    @Override
    public void restartLevel() {
        logger.info("Restarting the Endless Mode...");

        cleanupGameState(); // Clean up the current state.

        // Create and start a new Endless Mode instance.
        EndlessMode newGame = new EndlessMode(soundManager, stage);
        stage.setScene(newGame.initializeScene());
        newGame.startGame();

        logger.info("New EndlessMode instance started after cleanup.");
    }
}

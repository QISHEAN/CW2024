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
 * Class representing the endless mode in the game.
 */
public class EndlessMode extends LevelParent {
    private static final Logger logger = Logger.getLogger(EndlessMode.class.getName());

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
    private static final int INITIAL_ENEMIES = 5;
    private static final int MAXIMUM_ENEMIES = 10;
    private static final double INITIAL_SPAWN_RATE = 0.3;
    private static final double ENEMY_Y_UPPER_BOUND = 30;
    private static final double SPAWN_RATE_INCREMENT = 0.02;
    private static final double SPAWN_RATE_CAP = 0.9;

    private int totalEnemies;
    private double spawnRate;
    private int currentWave;

    public EndlessMode(SoundManager soundManager, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, 1300, 750, 5, soundManager, stage);
        this.totalEnemies = INITIAL_ENEMIES;
        this.spawnRate = INITIAL_SPAWN_RATE;
        this.currentWave = 1;
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new EndlessLevelView(root, 5);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            // Save score before ending game
            saveScore(getUserKillCount());
            loseGame();
            logger.info("Game over detected. Score saved.");
        }
    }

    private void increaseDifficulty() {
        currentWave++;
        totalEnemies += 2; // Increase total enemies per wave
        spawnRate = Math.min(spawnRate + SPAWN_RATE_INCREMENT, SPAWN_RATE_CAP);
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentEnemies = getCurrentNumberOfEnemies();
        int maxEnemies = Math.min(totalEnemies, MAXIMUM_ENEMIES);

        for (int i = 0; i < maxEnemies - currentEnemies; i++) {
            if (Math.random() < spawnRate) {
                double yPos = ENEMY_Y_UPPER_BOUND + Math.random() * getEnemyMaximumYPosition();
                double xPos = getScreenWidth();

                // Create an enemy with specified position
                ActiveActorDestructible enemy = createEnemyPlaneOne(xPos, yPos);
                addEnemyUnit(enemy);
            }
        }

        // Increase difficulty after every 10 kills
        if (getUserKillCount() >= currentWave * 10) {
            increaseDifficulty();
        }
    }

    private ActiveActorDestructible createEnemyPlaneOne(double x, double y) {
        return new EnemyPlane(x, y, 1); // Set enemy health to 1 for testing
    }

    @Override
    protected void incrementKillCount(int count) {
        super.incrementKillCount(count);
        // Update the kill count display via levelView
        if (levelView instanceof EndlessLevelView) {
            ((EndlessLevelView) levelView).updateKillCount(getUserKillCount());
        }
    }

    private void saveScore(int score) {
        LeaderboardManager.addScore(score);
    }

    /**
     * Cleans up the current game state. This method is called before creating a new EndlessMode instance.
     * It ensures that no old UI elements, animations, or data remain from the previous session.
     */
    private void cleanupGameState() {
        // Stop all timelines and animations
        stopGame();

        // Reset game variables
        totalEnemies = INITIAL_ENEMIES;
        spawnRate = INITIAL_SPAWN_RATE;
        currentWave = 1;

        // Reset the user plane
        getUser().reset();

        // Clear all enemies and projectiles
        entityManager.clearEnemies();
        entityManager.clearAllProjectiles();

        // Reset UI elements (kill count, etc.)
        if (levelView instanceof EndlessLevelView) {
            ((EndlessLevelView) levelView).updateKillCount(0);
            ((EndlessLevelView) levelView).resetKillCountDisplay();
        }
    }

    @Override
    public void restartLevel() {
        logger.info("Restarting the Endless Mode...");

        // Clean up the current game state before starting a new instance
        cleanupGameState();

        // Create a fresh new instance of EndlessMode
        EndlessMode newGame = new EndlessMode(soundManager, stage);
        stage.setScene(newGame.initializeScene());
        newGame.startGame();

        logger.info("New EndlessMode instance started after cleanup.");
    }
}

package com.example.demo.level;

import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.LevelView; // Use the refactored LevelView class
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.level.BossLevel";
    private static final int TOTAL_ENEMIES = 7; // Increased total enemies
    private static final double ENEMY_SPAWN_PROBABILITY = 0.25; // Faster spawn probability
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private static final int TOTAL_KILLS_TO_WIN = 10; // Higher kill count target

    private int currentKills = 0; // Track current kill count

    public LevelTwo(double screenWidth, double screenHeight, SoundManager soundManager, Stage stage) {
        super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH, soundManager, stage);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        // Initialize any friendly units if needed
    }

    public void incrementKillCount(int count) {
        currentKills += count;
        getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN);
    }

    @Override
    public Scene initializeScene() {
        Scene scene = super.initializeScene();
        getLevelView().showKillCountDisplay();
        getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN);
        return scene;
    }

    @Override
    protected void spawnEnemyUnits() {
        int currentNumberOfEnemies = getCurrentNumberOfEnemies();
        for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                EnemyPlane newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 2); // Increased health

                newEnemy.setHealth(5);
                // Increase enemy speed
                newEnemy.setSpeed(newEnemy.getSpeed() + 2);

                addEnemyUnit(newEnemy);
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        // Use the refactored LevelView with custom parameters for Level Two
        return new LevelView(
                getRoot(),
                PLAYER_INITIAL_HEALTH,
                true, // Enable warning label for this level
                "Enemies are stronger! Stay alert!" // Warning message specific to Level Two
        );
    }

    private boolean userHasReachedKillTarget() {
        return currentKills >= TOTAL_KILLS_TO_WIN;
    }
}

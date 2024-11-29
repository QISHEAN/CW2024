package com.example.demo.level;

import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.LevelViewLevelTwo;
import com.example.demo.levelview.LevelView; // Import LevelView
import javafx.scene.Scene;

public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/level2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.level.BossLevel";
    private static final int TOTAL_ENEMIES = 7; // Increased total enemies
    private static final double ENEMY_SPAWN_PROBABILITY = .25; // Faster spawn probability
    private static final int PLAYER_INITIAL_HEALTH = 5;

    private static final int TOTAL_KILLS_TO_WIN = 15; // Higher kill count target
    private int currentKills = 0; // Track current kill count

    // Duplicate declaration removed

    public LevelTwo(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
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
        // Add the user plane to the scene
        getRoot().getChildren().add(getUser());

        // Add the user plane's bounding box to the scene
        getRoot().getChildren().add(getUser().getBoundingBox());
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
        // Use a specialized LevelTwo view (if needed)
        return new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return currentKills >= TOTAL_KILLS_TO_WIN;
    }
}

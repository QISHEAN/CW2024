package com.example.demo.level;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.EnemyPlane;
import com.example.demo.level.LevelParent;
import com.example.demo.levelview.LevelView;
import javafx.scene.Scene;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/level1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private static final int TOTAL_KILLS_TO_WIN = 10;
	private int currentKills = 0; // Track current kill count

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			winGame();
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void initializeFriendlyUnits() {
		// Add the user plane to the scene
		getRoot().getChildren().add(getUser());

		// Add the user plane's bounding box to the scene
		getRoot().getChildren().add(getUser().getBoundingBox()); // Add bounding box
	}


	public void incrementKillCount(int count) {
		currentKills += count;
		getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN);
	}


	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene();
		getLevelView().showKillCountDisplay(); // Show the kill count display
		getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN); // Initialize display
		return scene;
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	private boolean userHasReachedKillTarget() {
		return currentKills >= TOTAL_KILLS_TO_WIN;
	}


}
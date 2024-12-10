package com.example.demo.level;
import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.LevelView;
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";
	private static final int TOTAL_ENEMIES = 5;
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private static final int TOTAL_KILLS_TO_WIN = 5;
	private int currentKills = 0; // Track current kill count

	public LevelOne(double screenWidth, double screenHeight, SoundManager soundManager, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH,soundManager,stage);
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
	}

	@Override
	protected void incrementKillCount(int count) {
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
				EnemyPlane newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 1); // Enemy health is 1
				addEnemyUnit(newEnemy);
			}
		}
	}

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH,false,null);
	}

	protected boolean userHasReachedKillTarget() {
		return currentKills >= TOTAL_KILLS_TO_WIN;
	}
}

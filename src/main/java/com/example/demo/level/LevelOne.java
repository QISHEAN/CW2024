package com.example.demo.level;

import com.example.demo.actor.EnemyPlane;
import com.example.demo.levelview.LevelView;
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class representing Level One in the game.
 * This level requires the player to reach a specific kill count to proceed to the next level.
 */
public class LevelOne extends LevelParent {

	//Constants for the level configuration.
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.level.LevelTwo";
	private static final int TOTAL_ENEMIES = 5; //Maximum number of enemies on screen.
	private static final double ENEMY_SPAWN_PROBABILITY = 0.20; //Probability of spawning an enemy.
	private static final int PLAYER_INITIAL_HEALTH = 5; //Player's initial health.
	private static final int TOTAL_KILLS_TO_WIN = 5; //Number of kills required to win.

	//Tracks the current number of kills made by the player.
	private int currentKills = 0;

	/**
	 * Constructor for Level One.
	 *
	 * @param screenWidth  The width of the game screen.
	 * @param screenHeight The height of the game screen.
	 * @param soundManager The SoundManager for managing game audio.
	 * @param stage        The primary stage for the application.
	 */
	public LevelOne(double screenWidth, double screenHeight, SoundManager soundManager, Stage stage) {
		super(BACKGROUND_IMAGE_NAME, screenWidth, screenHeight, PLAYER_INITIAL_HEALTH, soundManager, stage);
	}

	/**
	 * Checks if the game is over by evaluating the player's status.
	 * If the player is destroyed, the game ends with a loss.
	 * If the player reaches the kill target, the level transitions to the next one.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		} else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL); // Transition to the next level.
		}
	}

	/**
	 * Initializes friendly units for the level.
	 * This method is intentionally left empty for Level One.
	 */
	@Override
	protected void initializeFriendlyUnits() {
	}

	/**
	 * Increments the player's kill count and updates the kill count display.
	 *
	 * @param count The number of kills to add.
	 */
	@Override
	protected void incrementKillCount(int count) {
		currentKills += count;
		getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN); //Update the UI.
	}

	/**
	 * Initializes the scene for Level One.
	 * Sets up the kill count display for the player.
	 *
	 * @return The Scene object for this level.
	 */
	@Override
	public Scene initializeScene() {
		Scene scene = super.initializeScene(); // Call the parent method to set up the scene.
		getLevelView().showKillCountDisplay(); // Show the kill count display.
		getLevelView().updateKillCountDisplay(currentKills, TOTAL_KILLS_TO_WIN); //Initialize the display.
		return scene;
	}

	/**
	 * Spawns enemy units in the level.
	 * Ensures the number of enemies on screen does not exceed the maximum allowed.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				EnemyPlane newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition, 1); //Enemy health is set to 1.
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Instantiates the LevelView for Level One.
	 *
	 * @return A new instance of LevelView for Level One.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH, false, null);
	}

	/**
	 * Checks if the player has reached the kill target to win the level.
	 *
	 * @return True if the player has reached the required kill count, false otherwise.
	 */
	protected boolean userHasReachedKillTarget() {
		return currentKills >= TOTAL_KILLS_TO_WIN;
	}
}

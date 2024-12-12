package com.example.demo.level;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.Boss;
import com.example.demo.actor.FighterPlane;
import com.example.demo.actor.UserPlane;
import com.example.demo.levelview.EndlessLevelView;
import com.example.demo.levelview.LevelView;
import com.example.demo.managers.*;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.demo.managers.SoundManager;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Abstract base class for all levels in the game.
 * Manages core functionalities such as player setup, enemy handling, input management, and game progression.
 */
public abstract class LevelParent {
	protected final Stage stage;

	//Constants for screen adjustments and animation timing.
	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;

	//Core properties of the level.
	protected final double screenWidth;
	protected final double screenHeight;
	private final double enemyMaximumYPosition;

	//Game components.
	protected final Group root;
	protected final Timeline timeline;
	protected final UserPlane user;
	private final Scene scene;
	protected final ImageView background;

	protected final LevelView levelView;
	private final Boss boss;

	//Managers for handling various game subsystems.
	private final CollisionManager collisionManager;
	protected final EntityManager entityManager;

	private final GameInitializer gameInitializer;
	private final InputManager inputManager;
	protected final NavigationManager navigationManager;

	private final PauseManager pauseManager;

	protected final SoundManager soundManager;

	//Logger for debugging and tracking game events.
	private static final Logger logger = Logger.getLogger(LevelParent.class.getName());

	/**
	 * Constructor to initialize a level with the specified properties and managers.
	 *
	 * @param backgroundImageName Path to the background image for the level.
	 * @param screenWidth         Width of the game screen.
	 * @param screenHeight        Height of the game screen.
	 * @param playerInitialHealth Initial health of the player's plane.
	 * @param soundManager        Manager for handling sound effects and music.
	 * @param stage               The primary application stage.
	 */
	public LevelParent(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth, SoundManager soundManager, Stage stage) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.soundManager = soundManager;
		this.stage = stage;

		//Load background image and calculate enemy spawn bounds.
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;

		//Initialize level view and optional boss.
		this.levelView = instantiateLevelView();
		this.boss = new Boss();

		//Initialize managers.
		this.pauseManager = new PauseManager(scene, root, stage, soundManager, this::onPause, this::onResume, this::restartLevel);
		this.collisionManager = new CollisionManager();
		this.entityManager = new EntityManager(root);
		this.navigationManager = new NavigationManager();
		this.gameInitializer = new GameInitializer(root, background, user, timeline, MILLISECOND_DELAY);
		this.inputManager = new InputManager(user, scene, pauseManager, this::fireProjectile);

		initializeGame();//Set up the game.
	}
	protected void initializeFriendlyUnits() {
		gameInitializer.initializeFriendlyUnits();
	}

	/**
	 * Initializes the game by setting up background, units, input handling, and the game timeline.
	 */
	private void initializeGame() {
		gameInitializer.initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		gameInitializer.initializeTimeline(this::updateScene);
		inputManager.initializeInputHandling();
		pauseManager.initializePauseHandler();
		entityManager.addFriendlyUnit(user);
	}

	public void addLevelListener(LevelListener listener) {
		navigationManager.addLevelListener(listener);
	}

	public void removeLevelListener(LevelListener listener) {
		navigationManager.removeLevelListener(listener);
	}

	protected void notifyLevelChange(String nextLevelClassName) {
		navigationManager.notifyLevelChange(nextLevelClassName);
	}

	protected LevelView getLevelView() {
		return levelView;
	}

	/**
	 * Abstract method for checking if the game is over.
	 * Subclasses must implement this to handle win/lose conditions.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method for spawning enemy units.
	 * Subclasses must implement this to define enemy behavior.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Abstract method to instantiate the level's UI view.
	 *
	 * @return The instantiated LevelView object.
	 */
	protected abstract LevelView instantiateLevelView();


	public Boss getBoss() {
		return boss;
	}

	public Scene initializeScene() {
		return scene;
	}

	/**
	 * Starts the game by playing the timeline and background music.
	 */
	public void startGame() {
		background.requestFocus();
		timeline.play();
		soundManager.playBackgroundMusic("/sounds/background.mp3");
		logger.info("Game started.");
	}

	public void goToNextLevel(String levelName) {
		notifyLevelChange(levelName);
	}

	//Gameplay logic methods.

	/**
	 * Updates the game scene, including spawning enemies, handling collisions, and checking game status.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		entityManager.updateEntities();
		generateEnemyFire();
		collisionManager.handleEnemyPenetration(entityManager.getEnemyUnits(), user, root, screenWidth);
		collisionManager.handleUserProjectileCollisions(entityManager.getUserProjectiles(), entityManager.getEnemyUnits());
		collisionManager.handleEnemyProjectileCollisions(entityManager.getEnemyProjectiles(), entityManager.getFriendlyUnits());
		collisionManager.handlePlaneCollisions(entityManager.getFriendlyUnits(), entityManager.getEnemyUnits());
		int enemiesDestroyed = entityManager.removeAllDestroyedActors();
		if (enemiesDestroyed > 0) {
			incrementKillCount(enemiesDestroyed);
			soundManager.playSoundEffect("explosion");
		}
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Increments the kill count for the user and updates the level view.
	 *
	 * @param count The number of kills to add.
	 */
	protected void incrementKillCount(int count) {
		user.incrementKillCount(count);
		updateLevelView();
	}

	protected int getUserKillCount() {
		return user.getKillCount();
	}

	/**
	 * Pauses the game by stopping the timeline.
	 */
	private void onPause() {
		timeline.pause();
		logger.info("Game paused.");
	}

	/**
	 * Resumes the game by restarting the timeline and refocusing the background.
	 */
	private void onResume() {
		timeline.play();
		background.requestFocus();
		logger.info("Game resumed.");
	}

	/**
	 * Handles restarting the current level.
	 * Subclasses should override this to implement specific restart logic.
	 */
	public void restartLevel() {
		navigationManager.notifyRestartLevel();
		logger.info("RestartLevel called. Subclass should handle the restart.");
	}

	protected void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		entityManager.addUserProjectile(projectile);
		soundManager.playSoundEffect("shoot");
	}

	/**
	 * Spawns projectiles fired by enemy units.
	 */
	private void generateEnemyFire() {
		for (ActiveActorDestructible enemy : entityManager.getEnemyUnits()) {
			ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
			if (projectile != null) {
				entityManager.addEnemyProjectile(projectile);
			}
		}
	}

	/**
	 * Updates the level view based on the player's health, kills, and boss state.
	 */
	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		if (levelView instanceof EndlessLevelView) {
			((EndlessLevelView) levelView).updateKillCount(getUserKillCount());
		}
		levelView.showShield();
		if (boss != null) {
			levelView.updateBossHealthText(boss.getHealth());
			if (boss.getIsShield()) {
				levelView.showShield();
			} else {
				levelView.hideShield();
			}
		}
	}

	private void updateKillCount() {
		//Placeholder method for updating the kill count.
	}

	protected void winGame() {
		timeline.stop();  //Stops the game timeline and displays the win screen.
		levelView.showWinImage();
		soundManager.stopBackgroundMusic();
		soundManager.playSoundEffect("win");
		logger.info("Player won the game.");
	}

	protected void loseGame() {
		timeline.stop();    //Stops the game timeline and displays the game-over screen.
		levelView.showGameOverImage();
		soundManager.stopBackgroundMusic();
		soundManager.playSoundEffect("game_over");
	}

	public void stopGame() {
		if (timeline != null) {
			timeline.stop();
		}
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		//Returns the current number of enemy units in the game.
		return entityManager.getEnemyUnits().size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		//Adds an enemy unit to the game.
		entityManager.addEnemyUnit(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		//Returns the maximum Y-coordinate where enemies can spawn.
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		//Returns the width of the game screen.
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		//Checks if the user's plane has been destroyed.
		return user.isDestroyed();
	}

	protected UserPlane getUser() {
		//Returns the user's plane.
		return user;
	}
}

package com.example.demo.level;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.Boss;
import com.example.demo.actor.FighterPlane;
import com.example.demo.actor.UserPlane;
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

public abstract class LevelParent{
	protected final Stage stage;

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	protected final double screenWidth;
	protected final double screenHeight;
	private final double enemyMaximumYPosition;

	protected final Group root;
	protected final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	protected final ImageView background;

	private final LevelView levelView;

	private final Boss boss;

	private final CollisionManager collisionManager;
	private final EntityManager entityManager;

	private final GameInitializer gameInitializer;
	private final InputManager inputManager;
	private final NavigationManager navigationManager;

	private final PauseManager pauseManager;

	protected final SoundManager soundManager;

	public LevelParent(String backgroundImageName, double screenWidth, double screenHeight, int playerInitialHealth, SoundManager soundManager, Stage stage) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.soundManager = soundManager;
		this.stage = stage;

		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.boss = new Boss();

		this.pauseManager = new PauseManager(scene, root, stage, soundManager, this::onPause, this::onResume, this::restartLevel);
		this.collisionManager = new CollisionManager();
		this.entityManager = new EntityManager(root);
		this.navigationManager = new NavigationManager();


		this.gameInitializer = new GameInitializer(root, background, user, timeline, MILLISECOND_DELAY);
		this.inputManager = new InputManager(user, scene, pauseManager, this::fireProjectile);

		initializeGame();
	}

	protected void initializeFriendlyUnits() {
		gameInitializer.initializeFriendlyUnits();
	}

	private void initializeGame() {
		gameInitializer.initializeBackground();
		initializeFriendlyUnits(); // Call the method that can be overridden
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

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Boss getBoss() {
		return boss;
	}

	public Scene initializeScene() {
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
		soundManager.playBackgroundMusic("/sounds/background.mp3");
	}

	public void goToNextLevel(String levelName) {
		notifyLevelChange(levelName);
	}

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

	protected void incrementKillCount(int count) {
		// Default implementation does nothing
	}

	private void onPause() {
		timeline.pause();
	}

	private void onResume() {
		timeline.play();
		background.requestFocus();
	}

	public void restartLevel() {
		navigationManager.notifyRestartLevel();
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		entityManager.addUserProjectile(projectile);
		soundManager.playSoundEffect("shoot");
	}

	private void generateEnemyFire() {
		for (ActiveActorDestructible enemy : entityManager.getEnemyUnits()) {
			ActiveActorDestructible projectile = ((FighterPlane) enemy).fireProjectile();
			if (projectile != null) {
				entityManager.addEnemyProjectile(projectile);
			}
		}
	}

	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
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
		// Implement kill count update logic if needed
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		soundManager.stopBackgroundMusic();
		soundManager.playSoundEffect("win");
	}

	protected void loseGame() {
		timeline.stop();
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
		return entityManager.getEnemyUnits().size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		entityManager.addEnemyUnit(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
}

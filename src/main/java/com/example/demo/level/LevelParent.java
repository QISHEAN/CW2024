package com.example.demo.level;

import com.example.demo.actor.ActiveActorDestructible;
import com.example.demo.actor.Boss;
import com.example.demo.actor.FighterPlane;
import com.example.demo.actor.UserPlane;
import com.example.demo.levelview.LevelView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LevelParent{

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;

	private final List<ActiveActorDestructible> friendlyUnits;
	private final List<ActiveActorDestructible> enemyUnits;
	private final List<ActiveActorDestructible> userProjectiles;
	private final List<ActiveActorDestructible> enemyProjectiles;

	private int currentNumberOfEnemies;
	private final LevelView levelView;

	private final Boss boss;

	private final List<LevelListener> levelListeners = new CopyOnWriteArrayList<>();

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.friendlyUnits = new ArrayList<>();
		this.enemyUnits = new ArrayList<>();
		this.userProjectiles = new ArrayList<>();
		this.enemyProjectiles = new ArrayList<>();

		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		this.boss = new Boss();
		initializeTimeline();
		friendlyUnits.add(user);
	}

	public void addLevelListener(LevelListener listener) {
		levelListeners.add(listener);
	}

	public void removeLevelListener(LevelListener listener) {
		levelListeners.remove(listener);
	}

	protected void notifyLevelChange(String nextLevelClassName) {
		List<LevelListener> listenersCopy = new ArrayList<>(levelListeners); // Create a copy
		for (LevelListener listener : listenersCopy) { // Iterate over the copy
			listener.onLevelChange(nextLevelClassName);
		}
	}

	protected LevelView getLevelView() {
		return levelView;
	}

	protected void initializeFriendlyUnits() {
		root.getChildren().add(user);
		root.getChildren().add(user.getBoundingBox()); // Add bounding box
	}


	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	// In your LevelParent subclass (e.g., LevelThree)

	public Boss getBoss() {
		return boss;
	}

	public Scene initializeScene() {
		initializeBackground();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	public void goToNextLevel(String levelName) {
		notifyLevelChange(levelName);
	}

	private void updateScene() {
		spawnEnemyUnits();
		updateActors();
		generateEnemyFire();
		updateNumberOfEnemies();
		handleEnemyPenetration();
		handleUserProjectileCollisions();
		handleEnemyProjectileCollisions();
		handlePlaneCollisions();
		removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> {
			KeyCode kc = e.getCode();

			// Check for directional keys to move the user
			if (kc == KeyCode.UP) {
				user.moveUp();
			} else if (kc == KeyCode.DOWN) {
				user.moveDown();
			} else if (kc == KeyCode.RIGHT) {
				user.moveRight();
			} else if (kc == KeyCode.LEFT) {
				user.moveLeft();
			}

			// Check for space key to fire a projectile
			if (kc == KeyCode.SPACE) {
				fireProjectile();
			}
		});

		background.setOnKeyReleased(e -> {
			KeyCode kc = e.getCode();
			if (kc == KeyCode.UP || kc == KeyCode.DOWN) user.stop();
		});
		root.getChildren().add(0, background);
	}

	private void fireProjectile() {
		ActiveActorDestructible projectile = user.fireProjectile();
		root.getChildren().add(projectile);
		userProjectiles.add(projectile);
		root.getChildren().add(projectile.getBoundingBox()); // Add bounding box
	}

	private void generateEnemyFire() {
		enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile()));
	}

	private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
		if (projectile != null) {
			root.getChildren().add(projectile);
			enemyProjectiles.add(projectile);
			root.getChildren().add(projectile.getBoundingBox()); // Add bounding box
		}
	}

	private void updateActors() {
		friendlyUnits.forEach(actor -> {
			actor.updateActor();
			actor.updateBoundingBox(); // Update bounding box
		});

		enemyUnits.forEach(actor -> {
			actor.updateActor();
			actor.updateBoundingBox(); // Update bounding box
		});

		userProjectiles.forEach(actor -> {
			actor.updateActor();
			actor.updateBoundingBox(); // Update bounding box
		});

		enemyProjectiles.forEach(actor -> {
			actor.updateActor();
			actor.updateBoundingBox(); // Update bounding box
		});
	}
	private void removeAllDestroyedActors() {
		List<ActiveActorDestructible> destroyedEnemies = enemyUnits.stream()
				.filter(ActiveActorDestructible::isDestroyed)
				.toList();

		int enemiesDestroyed = destroyedEnemies.size();
		if (enemiesDestroyed > 0 && this instanceof LevelOne) {
			((LevelOne) this).incrementKillCount(enemiesDestroyed);
		}

		removeDestroyedActors(friendlyUnits);
		removeDestroyedActors(enemyUnits);
		removeDestroyedActors(userProjectiles);
		removeDestroyedActors(enemyProjectiles);
	}

	private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
		List<ActiveActorDestructible> destroyedActors = actors.stream()
				.filter(ActiveActorDestructible::isDestroyed)
				.toList();

		for (ActiveActorDestructible actor : destroyedActors) {
			root.getChildren().remove(actor);
			root.getChildren().remove(actor.getBoundingBox()); // Remove bounding box
		}
		actors.removeAll(destroyedActors);
	}

	private void handlePlaneCollisions() {
		for (ActiveActorDestructible friendly : friendlyUnits) {
			for (ActiveActorDestructible enemy : enemyUnits) {
				if (friendly.getBoundingBox().getBoundsInParent().intersects(enemy.getBoundingBox().getBoundsInParent())) {
					friendly.takeDamage();
					enemy.takeDamage();
				}
			}
		}
	}


	private void handleUserProjectileCollisions() {
		handleCollisions(userProjectiles, enemyUnits);
	}

	private void handleEnemyProjectileCollisions() {
		handleCollisions(enemyProjectiles, friendlyUnits);
	}

	private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
		for (ActiveActorDestructible actor1 : actors1) {
			for (ActiveActorDestructible actor2 : actors2) {
				if (actor1.getBoundingBox().getBoundsInParent().intersects(actor2.getBoundingBox().getBoundsInParent())) {
					actor1.takeDamage();
					actor2.takeDamage();
				}
			}
		}
	}

	private void handleEnemyPenetration() {
		List<ActiveActorDestructible> enemiesToRemove = new ArrayList<>();

		for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
			if (enemyHasPenetratedDefenses(enemy)) {
				user.takeDamage();
				enemy.removePlane(getRoot());
				enemiesToRemove.add(enemy);
			}
		}

		enemyUnits.removeAll(enemiesToRemove);
	}

	protected void updateLevelView() {
		levelView.removeHearts(user.getHealth());
		levelView.showShield();
		if (boss != null) {
			levelView.updateBossHealthText(boss.getHealth()); // Update the health on the screen
			if (boss.getIsShield()) {
				levelView.showShield(); // Implement this method in LevelView
			} else {
				levelView.hideShield(); // Implement this method in LevelView
			}
		}
	}

	private void updateKillCount() {
		for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
			user.incrementKillCount();
		}
	}

	private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
		return Math.abs(enemy.getTranslateX()) > screenWidth;
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}



	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
		return enemyUnits.size();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		enemyUnits.add(enemy);
		root.getChildren().add(enemy);
		root.getChildren().add(enemy.getBoundingBox()); // Add bounding box
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

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = enemyUnits.size();
	}

}

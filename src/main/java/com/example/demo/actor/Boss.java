package com.example.demo.actor;

import com.example.demo.projectiles.BossProjectile;
import javafx.geometry.Bounds;

import java.util.*;

/**
 * The Boss class represents a boss character in the game. It extends FighterPlane
 * and includes unique features like shield activation, randomized movement patterns, and projectile firing.
 */
public class Boss extends FighterPlane {

	// Constants defining the boss's behavior, appearance, and gameplay attributes.
	private static final String IMAGE_NAME = "bossplane.png";
	private static final double INITIAL_X_POSITION = 1000.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = 0.05;
	private static final int IMAGE_HEIGHT = 300;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 20;
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int ZERO = 0;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = -100;
	private static final int Y_POSITION_LOWER_BOUND = 475;
	private static final int MAX_FRAMES_WITH_SHIELD = 100;
	private final List<Integer> movePattern;
	private boolean isShielded;
	private int consecutiveMovesInSameDirection;
	private int indexOfCurrentMove;
	private int framesWithShieldActivated;

	private int currentHealth;

	/**
	 * Constructor to initialize the boss with its default attributes and behaviors.
	 */
	public Boss() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		currentHealth = HEALTH;
		movePattern = new ArrayList<>();
		consecutiveMovesInSameDirection = 0;
		indexOfCurrentMove = 0;
		framesWithShieldActivated = 0;
		isShielded = false;
		initializeMovePattern();
	}

	/**
	 * Returns the current health of the boss.
	 *
	 * @return the current health value.
	 */
	public int getHealth() {
		return currentHealth;
	}

	/**
	 * Updates the position of the boss based on the current move in its pattern.
	 * Prevents the boss from moving out of defined bounds.
	 */
	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss's state, including position and shield status.
	 */
	@Override
	public void updateActor() {
		updatePosition();
		updateShield();
	}

	/**
	 * Fires a projectile if the boss decides to fire in the current frame.
	 *
	 * @return a new BossProjectile instance if firing, otherwise null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Handles the boss taking damage. If the shield is active, no damage is applied.
	 * If the boss's health reaches zero, it is destroyed.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
			currentHealth--; // Decrease health when damage is taken
			if (currentHealth <= 0) {
				currentHealth = 0;
				destroy(); // Handle destruction or death logic
			}
		}
	}

	/**
	 * Initializes the boss's movement pattern with a mix of vertical and stationary moves.
	 * The pattern is shuffled for unpredictability.
	 */
	private void initializeMovePattern() {
		for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
			movePattern.add(VERTICAL_VELOCITY);
			movePattern.add(-VERTICAL_VELOCITY);
			movePattern.add(ZERO);
		}
		Collections.shuffle(movePattern);
	}

	/**
	 * Updates the shield's status. Activates or deactivates the shield based on probability and duration.
	 */
	protected void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			if (shieldExhausted()) {
				deactivateShield();
			}
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}
	}

	/**
	 * Determines the next move in the boss's movement pattern.
	 * Ensures moves do not repeat excessively before switching.
	 *
	 * @return the next move value.
	 */
	private int getNextMove() {
		int currentMove = movePattern.get(indexOfCurrentMove);
		consecutiveMovesInSameDirection++;
		if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
			consecutiveMovesInSameDirection = 0;
			indexOfCurrentMove++;
			if (indexOfCurrentMove >= movePattern.size()) {
				indexOfCurrentMove = 0;
				Collections.shuffle(movePattern); // Shuffle after completing a cycle
			}
		}
		return currentMove;
	}

	/**
	 * Determines whether the boss fires a projectile in the current frame based on a probability.
	 *
	 * @return true if the boss fires, false otherwise.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Calculates the initial position for the boss's projectiles.
	 *
	 * @return the Y-coordinate for the projectile's spawn point.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Checks whether the boss's shield is active.
	 *
	 * @return true if the shield is active, false otherwise.
	 */
	public boolean getIsShield(){
		return isShielded;
	}

	/**
	 * Determines whether the shield should be activated based on a probability.
	 *
	 * @return true if the shield should be activated, false otherwise.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Checks if the shield duration has been exhausted.
	 *
	 * @return true if the shield duration is complete, false otherwise.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates the boss's shield, making it immune to damage.
	 */
	protected void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates the boss's shield, allowing it to take damage again.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	/**
	 * Updates the bounding box of the boss for collision detection.
	 * Adjusts the bounding box dimensions and offsets for better accuracy.
	 */
	@Override
	public void updateBoundingBox() {
		double offsetX = 50;  // Horizontal offset for bounding box.
		double offsetY = 100; // Vertical offset for bounding box.
		double scaleWidth = 0.9;  // Scaling factor for width.
		double scaleHeight = 0.3; // Scaling factor for height.

		Bounds bounds = this.getBoundsInParent();
		boundingBox.setX(bounds.getMinX() + offsetX);
		boundingBox.setY(bounds.getMinY() + offsetY);
		boundingBox.setWidth(bounds.getWidth() * scaleWidth);
		boundingBox.setHeight(bounds.getHeight() * scaleHeight);
	}
}

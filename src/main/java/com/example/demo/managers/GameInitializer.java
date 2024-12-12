package com.example.demo.managers;

import com.example.demo.actor.UserPlane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class responsible for initializing the game environment and its components.
 * Handles setup for the background, player (user), and timeline for game updates.
 */
public class GameInitializer {

    private final Group root; // The root group containing all game elements.
    private final ImageView background; // Background image for the game.
    private final UserPlane user; // The player's plane.
    private final Timeline timeline; // Timeline for handling game updates.
    private final int millisecondDelay; // Delay in milliseconds for each game loop frame.

    /**
     * Constructor to initialize the game components.
     *
     * @param root            The root group for game elements.
     * @param background      The background image for the game.
     * @param user            The player's plane.
     * @param timeline        The timeline for game updates.
     * @param millisecondDelay The delay in milliseconds for each game loop frame.
     */
    public GameInitializer(Group root, ImageView background, UserPlane user, Timeline timeline, int millisecondDelay) {
        this.root = root;
        this.background = background;
        this.user = user;
        this.timeline = timeline;
        this.millisecondDelay = millisecondDelay;
    }

    /**
     * Initializes the background for the game.
     * Adds the background to the root group and ensures it can receive focus for input handling.
     */
    public void initializeBackground() {
        background.setFocusTraversable(true); // Allow the background to handle keyboard focus.
        root.getChildren().addFirst(background); // Add the background as the first element in the root group.
    }

    /**
     * Initializes friendly units, specifically the player's plane.
     * Adds the user's plane to the root group for rendering and interaction.
     */
    public void initializeFriendlyUnits() {
        root.getChildren().add(user); // Add the player's plane to the root group.
    }

    /**
     * Initializes the timeline for the game.
     * Sets up an indefinite game loop with a specified delay between frames, calling the provided update logic.
     *
     * @param updateScene A runnable that contains the logic to update the game scene each frame.
     */
    public void initializeTimeline(Runnable updateScene) {
        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the timeline indefinitely.
        KeyFrame gameLoop = new KeyFrame(Duration.millis(millisecondDelay), e -> updateScene.run()); // Define the game loop frame.
        timeline.getKeyFrames().add(gameLoop); // Add the game loop frame to the timeline.
    }
}

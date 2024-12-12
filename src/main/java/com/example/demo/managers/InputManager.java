package com.example.demo.managers;

import com.example.demo.actor.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Manages user input for controlling the player's plane and other game actions.
 * Handles key press and release events and maps them to player actions.
 */
public class InputManager {

    private final UserPlane user; // The player's plane to control.
    private final Scene scene; // The scene to capture input events.
    private final PauseManager pauseManager; // Manager to handle pause state.
    private final Runnable fireProjectileCallback; // Callback to trigger when firing projectiles.

    private final EventHandler<KeyEvent> keyPressedHandler; // Handles key press events.
    private final EventHandler<KeyEvent> keyReleasedHandler; // Handles key release events.

    /**
     * Constructor to initialize the input manager.
     *
     * @param user                  The player's plane to control.
     * @param scene                 The scene to capture input events.
     * @param pauseManager          The manager to handle pause state (optional).
     * @param fireProjectileCallback Callback to trigger firing projectiles (optional).
     */
    public InputManager(UserPlane user, Scene scene, PauseManager pauseManager, Runnable fireProjectileCallback) {
        this.user = user;
        this.scene = scene;
        this.pauseManager = pauseManager;
        this.fireProjectileCallback = fireProjectileCallback;

        // Initialize event handlers after assigning variables.
        this.keyPressedHandler = createKeyPressedHandler();
        this.keyReleasedHandler = createKeyReleasedHandler();
    }

    /**
     * Initializes input handling by adding key press and release event handlers to the scene.
     */
    public void initializeInputHandling() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedHandler); // Add key press handler.
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleasedHandler); // Add key release handler.
    }

    /**
     * Creates the handler for key press events.
     * Maps specific keys to player actions like movement or firing projectiles.
     *
     * @return The key press event handler.
     */
    private EventHandler<KeyEvent> createKeyPressedHandler() {
        return e -> {
            // Ignore input if the game is paused.
            if (pauseManager != null && pauseManager.isPaused()) {
                return;
            }

            KeyCode kc = e.getCode(); // Get the key code of the pressed key.

            // Map key presses to actions.
            switch (kc) {
                case UP:
                case W: // Move up.
                    user.moveUp();
                    break;
                case DOWN:
                case S: // Move down.
                    user.moveDown();
                    break;
                case LEFT:
                case A: // Move left.
                    user.moveLeft();
                    break;
                case RIGHT:
                case D: // Move right.
                    user.moveRight();
                    break;
                case SPACE: // Fire a projectile.
                    if (fireProjectileCallback != null) {
                        fireProjectileCallback.run();
                    }
                    break;
                default:
                    break; // Ignore unhandled keys.
            }
        };
    }

    /**
     * Creates the handler for key release events.
     * Stops player movement when corresponding keys are released.
     *
     * @return The key release event handler.
     */
    private EventHandler<KeyEvent> createKeyReleasedHandler() {
        return e -> {
            // Ignore input if the game is paused.
            if (pauseManager != null && pauseManager.isPaused()) {
                return;
            }

            KeyCode kc = e.getCode(); // Get the key code of the released key.

            // Map key releases to actions.
            switch (kc) {
                case UP:
                case W:
                case DOWN:
                case S: // Stop vertical movement.
                    user.stopVerticalMovement();
                    break;
                case LEFT:
                case A:
                case RIGHT:
                case D: // Stop horizontal movement.
                    user.stopHorizontalMovement();
                    break;
                default:
                    break; // Ignore unhandled keys.
            }
        };
    }
}

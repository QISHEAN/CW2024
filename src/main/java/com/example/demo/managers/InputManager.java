package com.example.demo.managers;

import com.example.demo.actor.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputManager {

    private final UserPlane user;
    private final Scene scene;
    private final PauseManager pauseManager;
    private final Runnable fireProjectileCallback;

    private final EventHandler<KeyEvent> keyPressedHandler;
    private final EventHandler<KeyEvent> keyReleasedHandler;

    public InputManager(UserPlane user, Scene scene, PauseManager pauseManager, Runnable fireProjectileCallback) {
        this.user = user;
        this.scene = scene;
        this.pauseManager = pauseManager;
        this.fireProjectileCallback = fireProjectileCallback;

        // Initialize event handlers after variables are assigned
        this.keyPressedHandler = createKeyPressedHandler();
        this.keyReleasedHandler = createKeyReleasedHandler();
    }

    public void initializeInputHandling() {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyPressedHandler);
        scene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleasedHandler);
    }

    private EventHandler<KeyEvent> createKeyPressedHandler() {
        return e -> {
            if (pauseManager != null && pauseManager.isPaused()) {
                return; // Ignore input if paused
            }

            KeyCode kc = e.getCode();

            switch (kc) {
                case UP:
                case W:
                    user.moveUp();
                    break;
                case DOWN:
                case S:
                    user.moveDown();
                    break;
                case LEFT:
                case A:
                    user.moveLeft();
                    break;
                case RIGHT:
                case D:
                    user.moveRight();
                    break;
                case SPACE:
                    if (fireProjectileCallback != null) {
                        fireProjectileCallback.run();
                    }
                    break;
                default:
                    break;
            }
        };
    }

    private EventHandler<KeyEvent> createKeyReleasedHandler() {
        return e -> {
            if (pauseManager != null && pauseManager.isPaused()) {
                return; // Ignore input if paused
            }

            KeyCode kc = e.getCode();

            switch (kc) {
                case UP:
                case W:
                case DOWN:
                case S:
                    user.stopVerticalMovement();
                    break;
                case LEFT:
                case A:
                case RIGHT:
                case D:
                    user.stopHorizontalMovement();
                    break;
                default:
                    break;
            }
        };
    }
}

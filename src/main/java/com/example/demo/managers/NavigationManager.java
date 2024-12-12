package com.example.demo.managers;

import com.example.demo.level.LevelListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages navigation between levels and other game states by notifying registered listeners.
 * Handles events such as level changes, exiting to the main menu, and restarting levels.
 */
public class NavigationManager {

    private final List<LevelListener> levelListeners = new CopyOnWriteArrayList<>(); // Thread-safe list of level listeners.

    /**
     * Adds a `LevelListener` to the list of listeners.
     *
     * @param listener The `LevelListener` to add.
     */
    public void addLevelListener(LevelListener listener) {
        levelListeners.add(listener); // Add the listener to the list.
    }

    /**
     * Removes a `LevelListener` from the list of listeners.
     *
     * @param listener The `LevelListener` to remove.
     */
    public void removeLevelListener(LevelListener listener) {
        levelListeners.remove(listener); // Remove the listener from the list.
    }

    /**
     * Notifies all registered listeners of a level change.
     * Calls the `onLevelChange` method of each listener with the name of the next level.
     *
     * @param nextLevelClassName The name of the next level's class.
     */
    public void notifyLevelChange(String nextLevelClassName) {
        // Create a copy of the listeners to prevent concurrent modification issues.
        List<LevelListener> listenersCopy = new ArrayList<>(levelListeners);
        for (LevelListener listener : listenersCopy) {
            listener.onLevelChange(nextLevelClassName); // Notify each listener of the level change.
        }
    }

    /**
     * Notifies all registered listeners to exit to the main menu.
     * Calls the `exitToMainMenu` method of each listener.
     */
    public void notifyExitToMainMenu() {
        for (LevelListener listener : levelListeners) {
            listener.exitToMainMenu(); // Notify each listener to exit to the main menu.
        }
    }

    /**
     * Notifies all registered listeners to restart the current level.
     * Calls the `restartLevel` method of each listener.
     */
    public void notifyRestartLevel() {
        for (LevelListener listener : levelListeners) {
            listener.restartLevel(); // Notify each listener to restart the level.
        }
    }
}

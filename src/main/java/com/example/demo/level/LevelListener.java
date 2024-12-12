//LevelListener.java
package com.example.demo.level;

/**
 * Interface for listening to level-related events in the game.
 * Implementing classes will handle transitions between levels, exiting to the main menu, and restarting the current level.
 */
public interface LevelListener {

    /**
     * Triggered when a level change is requested.
     *
     * @param nextLevelClassName The fully qualified name of the next level's class.
     */
    void onLevelChange(String nextLevelClassName);

    /**
     * Triggered when the user exits to the main menu.
     * Implementing classes should handle the transition to the main menu screen.
     */
    void exitToMainMenu();

    /**
     * Triggered when the user requests to restart the current level.
     * Implementing classes should reset the level and start it afresh.
     */
    void restartLevel();
}

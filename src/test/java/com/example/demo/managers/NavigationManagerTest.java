package com.example.demo.managers;

import com.example.demo.level.LevelListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

/**
 * Test suite for NavigationManager.
 */
class NavigationManagerTest {

    private NavigationManager navigationManager;
    private LevelListener listener1;
    private LevelListener listener2;

    @BeforeEach
    void setUp() {
        navigationManager = new NavigationManager();
        listener1 = Mockito.mock(LevelListener.class);
        listener2 = Mockito.mock(LevelListener.class);
    }

    /**
     * Tests adding listeners to the NavigationManager.
     */
    @Test
    void testAddLevelListener() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        // Internally, levelListeners is a CopyOnWriteArrayList, but since it's private,
        // we can't access it directly. Instead, we'll trigger an event and verify listeners are called.

        navigationManager.notifyLevelChange("NextLevelClassName");

        verify(listener1, times(1)).onLevelChange("NextLevelClassName");
        verify(listener2, times(1)).onLevelChange("NextLevelClassName");
    }

    /**
     * Tests removing a listener from the NavigationManager.
     */
    @Test
    void testRemoveLevelListener() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        // Remove listener1
        navigationManager.removeLevelListener(listener1);

        // Trigger an event
        navigationManager.notifyExitToMainMenu();

        // listener1 should not receive the notification
        verify(listener1, never()).exitToMainMenu();
        // listener2 should receive the notification
        verify(listener2, times(1)).exitToMainMenu();
    }

    /**
     * Tests notifying listeners of a level change.
     */
    @Test
    void testNotifyLevelChange() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        String nextLevel = "Level2";

        navigationManager.notifyLevelChange(nextLevel);

        verify(listener1, times(1)).onLevelChange(nextLevel);
        verify(listener2, times(1)).onLevelChange(nextLevel);
    }

    /**
     * Tests notifying listeners to exit to the main menu.
     */
    @Test
    void testNotifyExitToMainMenu() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        navigationManager.notifyExitToMainMenu();

        verify(listener1, times(1)).exitToMainMenu();
        verify(listener2, times(1)).exitToMainMenu();
    }

    /**
     * Tests notifying listeners to restart the level.
     */
    @Test
    void testNotifyRestartLevel() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        navigationManager.notifyRestartLevel();

        verify(listener1, times(1)).restartLevel();
        verify(listener2, times(1)).restartLevel();
    }

    /**
     * Tests that removing a listener works correctly.
     */
    @Test
    void testRemoveListenerEffect() {
        navigationManager.addLevelListener(listener1);
        navigationManager.addLevelListener(listener2);

        // Remove listener2
        navigationManager.removeLevelListener(listener2);

        // Trigger an event
        navigationManager.notifyRestartLevel();

        // listener1 should receive the notification
        verify(listener1, times(1)).restartLevel();
        // listener2 should not receive the notification
        verify(listener2, never()).restartLevel();
    }

    /**
     * Tests that notifying with no listeners does not cause any errors.
     */
    @Test
    void testNotifyWithNoListeners() {
        // No listeners added
        // This should not throw any exceptions
        assertDoesNotThrow(() -> {
            navigationManager.notifyLevelChange("Level3");
            navigationManager.notifyExitToMainMenu();
            navigationManager.notifyRestartLevel();
        });
    }
}

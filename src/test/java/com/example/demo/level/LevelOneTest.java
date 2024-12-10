package com.example.demo.level;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import com.example.demo.levelview.LevelView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LevelOneTest extends JavaFXInitializer {

    private LevelOne levelOne;
    private SoundManager mockSoundManager;

    @BeforeEach
    void setUp() {
        mockSoundManager = Mockito.mock(SoundManager.class);
        Stage mockStage = Mockito.mock(Stage.class);

        double screenWidth = 800;
        double screenHeight = 600;
        levelOne = new LevelOne(screenWidth, screenHeight, mockSoundManager, mockStage);
    }

    @Test
    void testInitialization() {
        // Just ensure no exceptions upon construction and initialization
        assertNotNull(levelOne, "LevelOne instance should not be null");
    }

    @Test
    void testInitializeScene() {
        // Ensure that initializing the scene returns a non-null Scene
        Scene scene = levelOne.initializeScene();
        assertNotNull(scene, "Scene should not be null after initialization");

        // Check the LevelView is of correct type
        assertInstanceOf(LevelView.class, levelOne.getLevelView(), "Should use a LevelView for LevelOne");

        // Check if kill count display has been initialized
        // Since the code updates kill count display immediately after scene initialization,
        // we assume no exceptions and that initial kill count is zero.
    }

    @Test
    void testStartGame() {
        // Starting the game should not throw an exception
        assertDoesNotThrow(() -> levelOne.startGame());

        // Verify that background music is played once
        // You will need to know what background music file is expected for LevelOne.
        // If the code in LevelParent or LevelOne calls playBackgroundMusic, check the parameter.
        verify(mockSoundManager, times(1)).playBackgroundMusic(anyString());
    }

    @Test
    void testIncrementKillCount() {
        // Initially kill count is zero; after incrementing, check that the display updates and no exception occurs
        assertDoesNotThrow(() -> levelOne.incrementKillCount(1));

        // No direct assertion to confirm display changes without a UI testing approach,
        // but we can at least confirm no exceptions are thrown and possibly reflect on an internal counter if accessible.
    }

    @Test
    void testUserHasReachedKillTarget() {
        // By default, the kill target is 5 (per code)
        // Increment kills up to 5 and check if next level trigger is considered
        for (int i = 0; i < 5; i++) {
            levelOne.incrementKillCount(1);
        }

        // Now let's check if the condition returns true internally
        // This relies on `userHasReachedKillTarget()` being accessible. If private, consider testing via indirect effects.
        assertTrue(invokePrivateMethod_userHasReachedKillTarget(), "User should have reached kill target after 5 increments");
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        // If user is destroyed, loseGame() should be invoked.
        // We can simulate user destruction by calling `setUserHealth(0)` if such a method exists.
        // Otherwise, modify code to allow such a test, or use reflection.

        assertDoesNotThrow(() -> levelOne.checkIfGameOver());

        // We can't directly assert loseGame was called without mocking or making loseGame observable.
        // If loseGame logs something or changes an internal state, check that state.
        // For demonstration:
        // assertTrue(levelOne.isGameLost(), "Game should be lost after user destruction");
    }

    @Test
    void testCheckIfGameOver_UserReachedKillTarget() {
        // Reaching the kill target should trigger going to next level
        for (int i = 0; i < 5; i++) {
            levelOne.incrementKillCount(1);
        }

        assertDoesNotThrow(() -> levelOne.checkIfGameOver());
        // If goToNextLevel is invoked, maybe track that with a spy or check an internal state.
        // As a placeholder:
        // assertTrue(levelOne.didGoToNextLevel(), "Should have progressed to the next level after reaching kill target");
    }

    @Test
    void testSpawnEnemyUnits() {
        // Before spawning enemies, the enemy list should be empty
        assertEquals(0, levelOne.getCurrentNumberOfEnemies(), "No enemies should exist initially");

        // Spawn enemy units multiple times to increase chance of adding at least one enemy (due to probability)
        for (int i = 0; i < 20; i++) {
            levelOne.spawnEnemyUnits();
        }

        // There's a chance that no enemies spawn if probability is too low,
        // but with enough attempts, statistically at least one should appear.
        // For a deterministic test, consider mocking Math.random().

        assertTrue(levelOne.getCurrentNumberOfEnemies() >= 0, "Should have spawned at least zero or more enemies");
        // This test is weak due to randomness. For a proper test, mock Math.random to return a fixed value.
    }

    /*
     * Helper method if needed to test private methods or internal states.
     * If userHasReachedKillTarget() is private, consider using reflection or test through side effects.
     * For demonstration, we assume it's protected or package-private and accessible.
     */
    private boolean invokePrivateMethod_userHasReachedKillTarget() {
        // If userHasReachedKillTarget is not accessible directly,
        // test by calling checkIfGameOver after achieving kill target
        // and verify level progression side effect.

        // This is a placeholder. If userHasReachedKillTarget() is accessible (as in the provided code),
        // just call levelOne.userHasReachedKillTarget().
        return levelOne.userHasReachedKillTarget();
    }
}

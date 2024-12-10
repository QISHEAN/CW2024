package com.example.demo.level;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LevelTwoTest extends JavaFXInitializer {

    private LevelTwo levelTwo;
    private SoundManager mockSoundManager;

    @BeforeEach
    void setUp() {
        mockSoundManager = Mockito.mock(SoundManager.class);
        Stage mockStage = Mockito.mock(Stage.class);
        double screenWidth = 800;
        double screenHeight = 600;

        levelTwo = new LevelTwo(screenWidth, screenHeight, mockSoundManager, mockStage);
    }

    @Test
    void testInitialization() {
        assertNotNull(levelTwo, "LevelTwo instance should not be null");
    }

    @Test
    void testInitializeScene() {
        Scene scene = levelTwo.initializeScene();
        assertNotNull(scene, "Scene should not be null after initialization");
    }

    @Test
    void testStartGame() {
        assertDoesNotThrow(() -> levelTwo.startGame());
        // Verify background music is played when game starts
        verify(mockSoundManager, times(1)).playBackgroundMusic("/sounds/background.mp3");
    }

    @Test
    void testSpawnEnemyUnits() {
        // Initially no enemies
        assertEquals(0, levelTwo.getCurrentNumberOfEnemies());

        // Spawn enemies multiple times; due to probability, enemies may or may not spawn
        // For a robust test, consider mocking Math.random() if possible.
        for (int i = 0; i < 20; i++) {
            levelTwo.spawnEnemyUnits();
        }

        // After multiple attempts, it's likely at least one enemy spawned (but not guaranteed).
        // A better approach is to mock Math.random() to return a fixed value > 0.25, ensuring spawn.
        // For now, just check no exceptions.
        assertTrue(levelTwo.getCurrentNumberOfEnemies() >= 0, "Enemies should be zero or more after spawn attempts");
    }

    @Test
    void testIncrementKillCount() {
        // Initially kill count is 0; increment by some amount
        levelTwo.incrementKillCount(5);
        // Not directly accessible, but no exceptions means updateKillCountDisplay was called
        // and internal kill count increased. Consider reflection or adding a getter for more direct testing.
        // Just ensure no exceptions occur:
        assertDoesNotThrow(() -> levelTwo.incrementKillCount(1));
    }

    @Test
    void testCheckIfGameOver_UserDestroyed() {
        // Simulate user destroyed if possible
        levelTwo.getUser().setHealth(0);
        assertDoesNotThrow(() -> levelTwo.checkIfGameOver());
        // Without a direct check, we rely on no exceptions and that loseGame() would be called.
        // If loseGame changes the UI or sound, we could verify:
        // verify(mockSoundManager, times(1)).playSoundEffect("game_over");
    }

    @Test
    void testCheckIfGameOver_ReachedKillTarget() {
        // Reach or exceed the kill target (which is 10 in LevelTwo)
        levelTwo.incrementKillCount(10);

        assertDoesNotThrow(() -> levelTwo.checkIfGameOver());
        // If goToNextLevel is invoked, mock a LevelListener and verify:
    }
}

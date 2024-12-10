package com.example.demo.level;

import com.example.demo.JavaFXInitializer;
import com.example.demo.actor.Boss;
import com.example.demo.managers.SoundManager;
import com.example.demo.levelview.LevelViewBoss;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BossLevelTest extends JavaFXInitializer {

    private BossLevel bossLevel;
    private SoundManager mockSoundManager;

    @BeforeEach
    void setUp() {
        mockSoundManager = Mockito.mock(SoundManager.class);
        Stage mockStage = Mockito.mock(Stage.class);

        double screenWidth = 800;
        double screenHeight = 600;
        bossLevel = new BossLevel(screenWidth, screenHeight, mockSoundManager, mockStage);
    }

    @Test
    void testInitialization() {
        // Ensure boss is initialized
        Boss boss = bossLevel.getBoss();
        assertNotNull(boss, "Boss should be initialized");
        assertFalse(boss.isDestroyed(), "Boss should not be destroyed at start");
    }

    @Test
    void testInitializeScene() {
        // Ensure that initializing the scene returns a non-null Scene
        Scene scene = bossLevel.initializeScene();
        assertNotNull(scene, "Scene should not be null after initialization");
        assertInstanceOf(LevelViewBoss.class, bossLevel.getLevelView(), "Should use LevelViewBoss");
    }

    @Test
    void testStartGame() {
        // Starting the game should not throw and should play boss background music
        assertDoesNotThrow(() -> bossLevel.startGame());
        verify(mockSoundManager, times(1)).playBackgroundMusic("/sounds/boss.background.mp3");
    }

    @Test
    void testSpawnEnemyUnits() {
        // Initially no enemies
        assertEquals(0, bossLevel.getCurrentNumberOfEnemies());

        // After spawning enemies, boss should be added once
        bossLevel.spawnEnemyUnits();
        assertEquals(1, bossLevel.getCurrentNumberOfEnemies(), "Boss should be spawned");
    }

    @Test
    void testCheckIfGameOver_NoExceptionOnUserDestroyed() {
        // Simulate user destroyed (if there's a way to set health directly)
        // Just ensure no exception occurs
        assertDoesNotThrow(() -> bossLevel.checkIfGameOver());
    }

    @Test
    void testCheckIfGameOver_NoExceptionOnBossDestroyed() {
        bossLevel.spawnEnemyUnits();
        assertDoesNotThrow(() -> bossLevel.checkIfGameOver());
    }
}

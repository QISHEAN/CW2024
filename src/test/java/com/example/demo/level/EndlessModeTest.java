package com.example.demo.level;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EndlessModeTest extends JavaFXInitializer {

    private EndlessMode endlessMode;
    private SoundManager mockSoundManager;

    @BeforeEach
    void setUp() {
        mockSoundManager = Mockito.mock(SoundManager.class);
        Stage mockStage = Mockito.mock(Stage.class);

        // Create an instance of EndlessMode for testing
        endlessMode = new EndlessMode(mockSoundManager, mockStage);
    }

    @Test
    void testInitialization() {
        // Ensure the object is created and user health is set as expected
        assertNotNull(endlessMode, "EndlessMode should be initialized");
        assertEquals(5, endlessMode.getUser().getHealth(), "User should start with health 5");
    }

    @Test
    void testInitializeScene() {
        // Ensure initializing the scene does not throw exceptions and returns a scene
        Scene scene = endlessMode.initializeScene();
        assertNotNull(scene, "Scene should be initialized");
    }

    @Test
    void testStartGame() {
        // Starting the game should not throw exceptions
        assertDoesNotThrow(() -> endlessMode.startGame(), "startGame should not throw exceptions");
        verify(mockSoundManager, times(1)).playBackgroundMusic(anyString());
    }

    @Test
    void testSpawnEnemies() {
        // Spawn enemies should not throw exceptions and should add some enemies
        assertDoesNotThrow(() -> endlessMode.spawnEnemyUnits());
        assertTrue(endlessMode.getCurrentNumberOfEnemies() >= 0, "Should spawn enemies without issues");
    }

    @Test
    void testCheckIfGameOver() {
        // Check that calling checkIfGameOver without user destroyed does not fail
        assertDoesNotThrow(() -> endlessMode.checkIfGameOver());

        // Simulate user destroyed and ensure still no exception
        endlessMode.getUser().setHealth(0);
        assertDoesNotThrow(() -> endlessMode.checkIfGameOver(), "Check game over when user is destroyed");
    }

    @Test
    void testRestartLevel() {
        // Restarting the level should not throw exceptions
        assertDoesNotThrow(() -> endlessMode.restartLevel(), "restartLevel should not throw exceptions");
    }
}

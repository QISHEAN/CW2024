package com.example.demo.level;

import com.example.demo.JavaFXInitializer;
import com.example.demo.actor.UserPlane;
import com.example.demo.levelview.LevelView;
import com.example.demo.managers.SoundManager;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests for the abstract LevelParent class.
 * A minimal subclass (TestableLevelParent) is created here for testing.
 */
class LevelParentTest extends JavaFXInitializer {

    private SoundManager mockSoundManager;
    private TestableLevelParent levelParent;

    @BeforeEach
    void setUp() {
        mockSoundManager = Mockito.mock(SoundManager.class);
        Stage mockStage = Mockito.mock(Stage.class);
        double screenWidth = 800;
        double screenHeight = 600;
        levelParent = new TestableLevelParent("/com/example/demo/images/background.jpg",
                screenWidth, screenHeight, 5,
                mockSoundManager, mockStage);
    }

    @Test
    void testInitialization() {
        assertNotNull(levelParent, "LevelParent subclass should be initialized");
        assertNotNull(levelParent.getRoot(), "Root should be initialized");
        assertNotNull(levelParent.initializeScene(), "Scene should be initialized");
        assertEquals(800, levelParent.getScreenWidth(), "Screen width should be set correctly");
        assertNotNull(levelParent.getUser(), "User should be initialized");
        assertNotNull(levelParent.getBoss(), "Boss should be initialized");
    }

    @Test
    void testInitializeScene() {
        Scene scene = levelParent.initializeScene();
        assertNotNull(scene, "Scene should not be null");
        // Scene initialization should not throw exceptions
    }

    @Test
    void testStartGame() {
        // Starting the game should play background music and no exceptions thrown
        assertDoesNotThrow(() -> levelParent.startGame());
        verify(mockSoundManager, times(1)).playBackgroundMusic("/sounds/background.mp3");
    }

    @Test
    void testGoToNextLevel() {
        // Mock a listener to verify navigation events if needed
        LevelListener mockListener = mock(LevelListener.class);
        levelParent.addLevelListener(mockListener);

        // Trigger the navigation
        levelParent.goToNextLevel("com.example.demo.level.NextLevel");
        verify(mockListener, times(1)).onLevelChange("com.example.demo.level.NextLevel");
    }

    @Test
    void testLoseGame() {
        // Losing the game should stop timeline, show game over, stop BGM, and play SFX
        assertDoesNotThrow(() -> levelParent.loseGame());
        verify(mockSoundManager, times(1)).stopBackgroundMusic();
        verify(mockSoundManager, times(1)).playSoundEffect("game_over");
        // Can't directly verify UI changes easily, just ensure no exception
    }

    @Test
    void testWinGame() {
        // Winning the game should stop timeline, show win image, stop BGM, and play SFX
        assertDoesNotThrow(() -> levelParent.winGame());
        verify(mockSoundManager, times(1)).stopBackgroundMusic();
        verify(mockSoundManager, times(1)).playSoundEffect("win");
    }

    @Test
    void testIncrementKillCount() {
        UserPlane user = levelParent.getUser();
        int initialKills = user.getKillCount();

        levelParent.incrementKillCount(3);
        assertEquals(initialKills + 3, user.getKillCount(), "Kill count should increase by 3");
    }

    @Test
    void testFireProjectile() {
        // Before firing, no user projectiles
    }

    @Test
    void testStopGame() {
        // Stopping the game should stop the timeline without exception
        assertDoesNotThrow(() -> levelParent.stopGame());
    }

    /**
     * Concrete subclass of LevelParent to allow instantiation for testing.
     */
    private static class TestableLevelParent extends LevelParent {
        public TestableLevelParent(String backgroundImageName, double screenWidth, double screenHeight,
                                   int playerInitialHealth, SoundManager soundManager, Stage stage) {
            super(backgroundImageName, screenWidth, screenHeight, playerInitialHealth, soundManager, stage);
        }

        @Override
        protected void checkIfGameOver() {
            // Minimal implementation for test
        }

        @Override
        protected void spawnEnemyUnits() {
            // Minimal implementation for test
        }

        @Override
        protected LevelView instantiateLevelView() {
            // Mock or return a simple LevelView (no UI logic needed here)
            return Mockito.mock(LevelView.class);
        }

        @Override
        protected void initializeFriendlyUnits() {
            super.initializeFriendlyUnits();
            // Additional setup for friendly units if needed
        }
    }
}

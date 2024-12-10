package com.example.demo.managers;

import com.example.demo.JavaFXInitializer;
import com.example.demo.actor.UserPlane;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class InputManagerTest extends JavaFXInitializer {

    @Mock
    private UserPlane userPlane;

    @Mock
    private PauseManager pauseManager;

    @Mock
    private Runnable fireProjectileCallback;

    @Mock
    private Scene scene;

    @Captor
    private ArgumentCaptor<EventHandler<KeyEvent>> keyPressedHandlerCaptor;

    @Captor
    private ArgumentCaptor<EventHandler<KeyEvent>> keyReleasedHandlerCaptor;

    private EventHandler<KeyEvent> keyPressedHandler;
    private EventHandler<KeyEvent> keyReleasedHandler;

    @BeforeEach
    void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);

        // Instantiate InputManager with mocked dependencies
        InputManager inputManager = new InputManager(userPlane, scene, pauseManager, fireProjectileCallback);

        // Initialize input handling, which adds event handlers to the scene
        inputManager.initializeInputHandling();

        // Capture the event handlers added to the scene
        verify(scene).addEventHandler(eq(KeyEvent.KEY_PRESSED), keyPressedHandlerCaptor.capture());
        verify(scene).addEventHandler(eq(KeyEvent.KEY_RELEASED), keyReleasedHandlerCaptor.capture());

        keyPressedHandler = keyPressedHandlerCaptor.getValue();
        keyReleasedHandler = keyReleasedHandlerCaptor.getValue();
    }

    @AfterEach
    void tearDown() {
        // No specific teardown required as mocks are reset automatically
    }

    /**
     * Helper method to simulate a key pressed event.
     *
     * @param keyCode The KeyCode to simulate.
     */
    private void simulateKeyPressed(KeyCode keyCode) {
        KeyEvent keyEvent = new KeyEvent(
                KeyEvent.KEY_PRESSED,
                "",
                "",
                keyCode,
                false,
                false,
                false,
                false
        );
        keyPressedHandler.handle(keyEvent);
    }

    /**
     * Helper method to simulate a key released event.
     *
     * @param keyCode The KeyCode to simulate.
     */
    private void simulateKeyReleased(KeyCode keyCode) {
        KeyEvent keyEvent = new KeyEvent(
                KeyEvent.KEY_RELEASED,
                "",
                "",
                keyCode,
                false,
                false,
                false,
                false
        );
        keyReleasedHandler.handle(keyEvent);
    }

    /**
     * Tests that pressing the UP arrow key triggers userPlane.moveUp().
     */
    @Test
    void testKeyPressed_UP() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.UP);

        // Assert
        verify(userPlane, times(1)).moveUp();
    }

    /**
     * Tests that pressing the W key triggers userPlane.moveUp().
     */
    @Test
    void testKeyPressed_W() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.W);

        // Assert
        verify(userPlane, times(1)).moveUp();
    }

    /**
     * Tests that pressing the DOWN arrow key triggers userPlane.moveDown().
     */
    @Test
    void testKeyPressed_DOWN() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.DOWN);

        // Assert
        verify(userPlane, times(1)).moveDown();
    }

    /**
     * Tests that pressing the S key triggers userPlane.moveDown().
     */
    @Test
    void testKeyPressed_S() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.S);

        // Assert
        verify(userPlane, times(1)).moveDown();
    }

    /**
     * Tests that pressing the LEFT arrow key triggers userPlane.moveLeft().
     */
    @Test
    void testKeyPressed_LEFT() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.LEFT);

        // Assert
        verify(userPlane, times(1)).moveLeft();
    }

    /**
     * Tests that pressing the A key triggers userPlane.moveLeft().
     */
    @Test
    void testKeyPressed_A() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.A);

        // Assert
        verify(userPlane, times(1)).moveLeft();
    }

    /**
     * Tests that pressing the RIGHT arrow key triggers userPlane.moveRight().
     */
    @Test
    void testKeyPressed_RIGHT() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.RIGHT);

        // Assert
        verify(userPlane, times(1)).moveRight();
    }

    /**
     * Tests that pressing the D key triggers userPlane.moveRight().
     */
    @Test
    void testKeyPressed_D() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.D);

        // Assert
        verify(userPlane, times(1)).moveRight();
    }

    /**
     * Tests that pressing the SPACE key triggers fireProjectileCallback.run().
     */
    @Test
    void testKeyPressed_SPACE() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyPressed(KeyCode.SPACE);

        // Assert
        verify(fireProjectileCallback, times(1)).run();
    }

    /**
     * Tests that releasing the UP arrow key triggers userPlane.stopVerticalMovement().
     */
    @Test
    void testKeyReleased_UP() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.UP);

        // Assert
        verify(userPlane, times(1)).stopVerticalMovement();
    }

    /**
     * Tests that releasing the W key triggers userPlane.stopVerticalMovement().
     */
    @Test
    void testKeyReleased_W() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.W);

        // Assert
        verify(userPlane, times(1)).stopVerticalMovement();
    }

    /**
     * Tests that releasing the DOWN arrow key triggers userPlane.stopVerticalMovement().
     */
    @Test
    void testKeyReleased_DOWN() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.DOWN);

        // Assert
        verify(userPlane, times(1)).stopVerticalMovement();
    }

    /**
     * Tests that releasing the S key triggers userPlane.stopVerticalMovement().
     */
    @Test
    void testKeyReleased_S() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.S);

        // Assert
        verify(userPlane, times(1)).stopVerticalMovement();
    }

    /**
     * Tests that releasing the LEFT arrow key triggers userPlane.stopHorizontalMovement().
     */
    @Test
    void testKeyReleased_LEFT() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.LEFT);

        // Assert
        verify(userPlane, times(1)).stopHorizontalMovement();
    }

    /**
     * Tests that releasing the A key triggers userPlane.stopHorizontalMovement().
     */
    @Test
    void testKeyReleased_A() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.A);

        // Assert
        verify(userPlane, times(1)).stopHorizontalMovement();
    }

    /**
     * Tests that releasing the RIGHT arrow key triggers userPlane.stopHorizontalMovement().
     */
    @Test
    void testKeyReleased_RIGHT() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.RIGHT);

        // Assert
        verify(userPlane, times(1)).stopHorizontalMovement();
    }

    /**
     * Tests that releasing the D key triggers userPlane.stopHorizontalMovement().
     */
    @Test
    void testKeyReleased_D() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(false);

        // Act
        simulateKeyReleased(KeyCode.D);

        // Assert
        verify(userPlane, times(1)).stopHorizontalMovement();
    }

    /**
     * Tests that when the game is paused, pressing any key does not trigger any actions.
     */
    @Test
    void testInputIgnoredWhenPaused_KeyPressed() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(true);

        // Act
        simulateKeyPressed(KeyCode.UP);
        simulateKeyPressed(KeyCode.SPACE);

        // Assert
        verify(userPlane, never()).moveUp();
        verify(fireProjectileCallback, never()).run();
    }

    /**
     * Tests that when the game is paused, releasing any key does not trigger any actions.
     */
    @Test
    void testInputIgnoredWhenPaused_KeyReleased() {
        // Arrange
        when(pauseManager.isPaused()).thenReturn(true);

        // Act
        simulateKeyReleased(KeyCode.UP);
        simulateKeyReleased(KeyCode.D);

        // Assert
        verify(userPlane, never()).stopVerticalMovement();
        verify(userPlane, never()).stopHorizontalMovement();
    }
}

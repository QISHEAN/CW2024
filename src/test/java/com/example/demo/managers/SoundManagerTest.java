package com.example.demo.managers;

import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class SoundManagerTest {

    @Mock
    private MediaPlayer mockShootPlayer;

    @Mock
    private MediaPlayer mockExplosionPlayer;

    @Mock
    private MediaPlayer mockWinPlayer;

    @Mock
    private MediaPlayer mockGameOverPlayer;

    @Mock
    private MediaPlayer mockBackgroundMusicPlayer;

    @InjectMocks
    private SoundManager soundManager;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Mock the Media objects to prevent actual media loading
        when(mockShootPlayer.getVolume()).thenReturn(1.0);
        when(mockExplosionPlayer.getVolume()).thenReturn(1.0);
        when(mockWinPlayer.getVolume()).thenReturn(1.0);
        when(mockGameOverPlayer.getVolume()).thenReturn(1.0);
        when(mockBackgroundMusicPlayer.getVolume()).thenReturn(1.0);

        // Use reflection to replace the MediaPlayer instances in the soundEffects map
        Field soundEffectsField = SoundManager.class.getDeclaredField("soundEffects");
        soundEffectsField.setAccessible(true);
        Map<String, MediaPlayer> soundEffects = (Map<String, MediaPlayer>) soundEffectsField.get(soundManager);
        soundEffects.put("shoot.mp3", mockShootPlayer);
        soundEffects.put("explosion.mp3", mockExplosionPlayer);
        soundEffects.put("win.mp3", mockWinPlayer);
        soundEffects.put("game_over.mp3", mockGameOverPlayer);

        // Replace backgroundMusicPlayer with mock
        Field backgroundMusicPlayerField = SoundManager.class.getDeclaredField("backgroundMusicPlayer");
        backgroundMusicPlayerField.setAccessible(true);
        backgroundMusicPlayerField.set(soundManager, mockBackgroundMusicPlayer);
    }

    @Test
    @DisplayName("Test playing a valid sound effect")
    void testPlaySoundEffect_ValidKey() {
        // Act
        soundManager.playSoundEffect("shoot.mp3");

        // Assert
        verify(mockShootPlayer, times(1)).stop();
        verify(mockShootPlayer, times(1)).seek(any());
        verify(mockShootPlayer, times(1)).play();
    }

    @Test
    @DisplayName("Test playing an invalid sound effect")
    void testPlaySoundEffect_InvalidKey() {
        // Act & Assert
        assertDoesNotThrow(() -> soundManager.playSoundEffect("invalid_key"));
        // No interactions should occur
        verifyNoInteractions(mockShootPlayer, mockExplosionPlayer, mockWinPlayer, mockGameOverPlayer);
    }

    @Test
    @DisplayName("Test setting sound effects volume")
    void testSetSoundEffectsVolume() throws Exception {
        // Act
        soundManager.setSoundEffectsVolume(0.5);

        // Assert
        verify(mockShootPlayer, times(1)).setVolume(0.5);
        verify(mockExplosionPlayer, times(1)).setVolume(0.5);
        verify(mockWinPlayer, times(1)).setVolume(0.5);
        verify(mockGameOverPlayer, times(1)).setVolume(0.5);

        // Verify internal state
        Field soundEffectsVolumeField = SoundManager.class.getDeclaredField("soundEffectsVolume");
        soundEffectsVolumeField.setAccessible(true);
        double volume = soundEffectsVolumeField.getDouble(soundManager);
        assertEquals(0.5, volume, 0.001, "Sound effects volume should be updated to 0.5");
    }

    @Test
    @DisplayName("Test setting background music volume")
    void testSetBackgroundMusicVolume() throws Exception {
        // Act
        soundManager.setBackgroundMusicVolume(0.7);

        // Assert
        verify(mockBackgroundMusicPlayer, times(1)).setVolume(0.7);

        // Verify internal state
        Field backgroundMusicVolumeField = SoundManager.class.getDeclaredField("backgroundMusicVolume");
        backgroundMusicVolumeField.setAccessible(true);
        double volume = backgroundMusicVolumeField.getDouble(soundManager);
        assertEquals(0.7, volume, 0.001, "Background music volume should be updated to 0.7");
    }

    @Test
    @DisplayName("Test stopping background music")
    void testStopBackgroundMusic() {
        // Act
        soundManager.stopBackgroundMusic();

        // Assert
        verify(mockBackgroundMusicPlayer, times(1)).stop();
    }
}


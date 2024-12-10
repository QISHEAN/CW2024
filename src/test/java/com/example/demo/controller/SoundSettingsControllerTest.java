package com.example.demo.controller;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SoundSettingsControllerTest extends JavaFXInitializer {

    private SoundSettingsController controller;
    private SoundManager mockSoundManager;

    private Slider musicVolumeSlider;
    private Slider effectsVolumeSlider;
    private Label musicVolumeValue;
    private Label effectsVolumeValue;

    @BeforeEach
    void setUp() {
        // Create controller
        controller = new SoundSettingsController();

        // Mock SoundManager
        mockSoundManager = Mockito.mock(SoundManager.class);

        // Create UI components
        musicVolumeSlider = new Slider(0.0, 1.0, 0.5);
        effectsVolumeSlider = new Slider(0.0, 1.0, 0.5);
        musicVolumeValue = new Label();
        effectsVolumeValue = new Label();

        // Use reflection or direct field assignment to simulate FXML injection
        setField(controller, "musicVolumeSlider", musicVolumeSlider);
        setField(controller, "effectsVolumeSlider", effectsVolumeSlider);
        setField(controller, "musicVolumeValue", musicVolumeValue);
        setField(controller, "effectsVolumeValue", effectsVolumeValue);
    }

    // Simple utility method to set fields via reflection if needed
    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set field " + fieldName + ": " + e.getMessage());
        }
    }

    @Test
    void testSetSoundManager_InitialValuesAreSet() {
        // Mock initial volumes
        when(mockSoundManager.getBackgroundMusicVolume()).thenReturn(0.8);
        when(mockSoundManager.getSoundEffectsVolume()).thenReturn(0.3);

        // Set the sound manager
        controller.setSoundManager(mockSoundManager);

        // Verify sliders are initialized
        assertEquals(0.8, musicVolumeSlider.getValue(), 0.0001, "Music slider should be set to manager's music volume");
        assertEquals(0.3, effectsVolumeSlider.getValue(), 0.0001, "Effects slider should be set to manager's effects volume");

        // Verify labels are updated
        assertEquals("80%", musicVolumeValue.getText());
        assertEquals("30%", effectsVolumeValue.getText());
    }

    @Test
    void testMusicVolumeSliderChangeUpdatesSoundManager() {
        when(mockSoundManager.getBackgroundMusicVolume()).thenReturn(0.5);
        when(mockSoundManager.getSoundEffectsVolume()).thenReturn(0.5);

        // Set sound manager to initialize listeners
        controller.setSoundManager(mockSoundManager);

        // Change the music volume slider value
        musicVolumeSlider.setValue(0.9);

        // Verify soundManager was updated
        verify(mockSoundManager, times(1)).setBackgroundMusicVolume(0.9);

        // Check label update
        assertEquals("90%", musicVolumeValue.getText());
    }

    @Test
    void testEffectsVolumeSliderChangeUpdatesSoundManager() {
        when(mockSoundManager.getBackgroundMusicVolume()).thenReturn(0.5);
        when(mockSoundManager.getSoundEffectsVolume()).thenReturn(0.5);

        controller.setSoundManager(mockSoundManager);

        // Change the effects volume slider
        effectsVolumeSlider.setValue(0.25);

        // Verify soundManager was updated
        verify(mockSoundManager, times(1)).setSoundEffectsVolume(0.25);

        // Check label update
        assertEquals("25%", effectsVolumeValue.getText());
    }

    @Test
    void testApplyVolumeSettings() {
        when(mockSoundManager.getBackgroundMusicVolume()).thenReturn(0.5);
        when(mockSoundManager.getSoundEffectsVolume()).thenReturn(0.5);

        controller.setSoundManager(mockSoundManager);

        // Adjust sliders
        musicVolumeSlider.setValue(0.6); // Triggers setBackgroundMusicVolume(0.6)
        effectsVolumeSlider.setValue(0.4); // Triggers setSoundEffectsVolume(0.4)

        // Apply volume settings
        controller.applyVolumeSettings(); // Triggers setBackgroundMusicVolume(0.6) and setSoundEffectsVolume(0.4)

        // Check if sound manager receives final values
        verify(mockSoundManager, times(2)).setBackgroundMusicVolume(0.6);
        verify(mockSoundManager, times(2)).setSoundEffectsVolume(0.4);
    }
}

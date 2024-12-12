package com.example.demo.controller;

import com.example.demo.managers.SoundManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * Controller for the Sound Settings screen.
 * Allows users to adjust background music and sound effects volume using sliders.
 */
public class SoundSettingsController {

    // FXML-bound UI components for volume sliders and their labels.
    @FXML
    private Slider musicVolumeSlider;

    @FXML
    private Slider effectsVolumeSlider;

    @FXML
    private Label musicVolumeValue;

    @FXML
    private Label effectsVolumeValue;

    //Reference to the SoundManager for controlling audio settings.
    private SoundManager soundManager;

    /**
     * Initializes the controller. Called automatically by JavaFX when the FXML file is loaded.
     * Used for initial setup of components if necessary.
     */
    public void initialize() {
        //Optionally perform additional initialization if required.
    }

    /**
     * Sets the SoundManager instance for managing audio.
     * Initializes the sliders and adds listeners to handle user interactions.
     *
     * @param soundManager The SoundManager instance.
     */
    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
        initializeSliders(); // Set initial slider values based on the current sound manager settings.
        addSliderListeners(); // Add listeners to sliders to handle volume adjustments.
    }

    /**
     * Initializes the sliders with the current volume values from the SoundManager.
     * Updates the volume labels to reflect the initial values.
     */
    private void initializeSliders() {
        if (soundManager != null) {
            //Retrieve current volumes from the sound manager.
            double musicVolume = soundManager.getBackgroundMusicVolume();
            double effectsVolume = soundManager.getSoundEffectsVolume();

            //Set the sliders to the current volume levels.
            musicVolumeSlider.setValue(musicVolume);
            effectsVolumeSlider.setValue(effectsVolume);

            //Update the volume labels to show the current percentages.
            updateMusicVolumeLabel(musicVolume);
            updateEffectsVolumeLabel(effectsVolume);
        }
    }

    /**
     * Adds listeners to the sliders to dynamically update the SoundManager and labels
     * when the user adjusts the slider values.
     */
    private void addSliderListeners() {
        //Listener for the music volume slider.
        musicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (soundManager != null) {
                double volume = newValue.doubleValue();
                soundManager.setBackgroundMusicVolume(volume); // Update the music volume in the SoundManager.
                updateMusicVolumeLabel(volume); // Update the label to reflect the new volume.
            }
        });

        //Listener for the sound effects volume slider.
        effectsVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (soundManager != null) {
                double volume = newValue.doubleValue();
                soundManager.setSoundEffectsVolume(volume); // Update the sound effects volume in the SoundManager.
                updateEffectsVolumeLabel(volume); // Update the label to reflect the new volume.
            }
        });
    }

    /**
     * Updates the music volume label to display the current volume as a percentage.
     *
     * @param volume The current music volume (0.0 to 1.0).
     */
    private void updateMusicVolumeLabel(double volume) {
        int percentage = (int) Math.round(volume * 100);
        musicVolumeValue.setText(percentage + "%");
    }

    /**
     * Updates the sound effects volume label to display the current volume as a percentage.
     *
     * @param volume The current sound effects volume (0.0 to 1.0).
     */
    private void updateEffectsVolumeLabel(double volume) {
        int percentage = (int) Math.round(volume * 100);
        effectsVolumeValue.setText(percentage + "%");
    }

    /**
     * Applies the current slider values as the new volume settings in the SoundManager.
     * Triggered when the user confirms the settings.
     */
    @FXML
    protected void applyVolumeSettings() {
        if (soundManager != null) {
            //Retrieve the current slider values.
            double musicVolume = musicVolumeSlider.getValue();
            double effectsVolume = effectsVolumeSlider.getValue();

            //Update the SoundManager with the new volume levels.
            soundManager.setBackgroundMusicVolume(musicVolume);
            soundManager.setSoundEffectsVolume(effectsVolume);
        }
    }
}

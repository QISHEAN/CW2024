package com.example.demo.controller;

import com.example.demo.managers.SoundManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SoundSettingsController {

    @FXML
    private Slider musicVolumeSlider;

    @FXML
    private Slider effectsVolumeSlider;

    @FXML
    private Label musicVolumeValue;

    @FXML
    private Label effectsVolumeValue;

    private SoundManager soundManager;

    public void initialize() {
        // Initialize sliders or other components if needed
    }

    public void setSoundManager(SoundManager soundManager) {
        this.soundManager = soundManager;
        initializeSliders();
        addSliderListeners();
    }

    private void initializeSliders() {
        if (soundManager != null) {
            double musicVolume = soundManager.getBackgroundMusicVolume();
            double effectsVolume = soundManager.getSoundEffectsVolume();

            musicVolumeSlider.setValue(musicVolume);
            effectsVolumeSlider.setValue(effectsVolume);

            // Set initial volume values in labels
            updateMusicVolumeLabel(musicVolume);
            updateEffectsVolumeLabel(effectsVolume);
        }
    }

    private void addSliderListeners() {
        musicVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (soundManager != null) {
                double volume = newValue.doubleValue();
                soundManager.setBackgroundMusicVolume(volume);
                updateMusicVolumeLabel(volume);
            }
        });

        effectsVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (soundManager != null) {
                double volume = newValue.doubleValue();
                soundManager.setSoundEffectsVolume(volume);
                updateEffectsVolumeLabel(volume);
            }
        });
    }

    private void updateMusicVolumeLabel(double volume) {
        int percentage = (int) Math.round(volume * 100);
        musicVolumeValue.setText(percentage + "%");
    }

    private void updateEffectsVolumeLabel(double volume) {
        int percentage = (int) Math.round(volume * 100);
        effectsVolumeValue.setText(percentage + "%");
    }

    @FXML
    protected void applyVolumeSettings() {
        if (soundManager != null) {
            double musicVolume = musicVolumeSlider.getValue();
            double effectsVolume = effectsVolumeSlider.getValue();

            soundManager.setBackgroundMusicVolume(musicVolume);
            soundManager.setSoundEffectsVolume(effectsVolume);
        }
    }

}
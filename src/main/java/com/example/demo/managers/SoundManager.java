package com.example.demo.managers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SoundManager {
    private static final Logger LOGGER = Logger.getLogger(SoundManager.class.getName());

    private final Map<String, MediaPlayer> soundEffects = new HashMap<>();
    private MediaPlayer backgroundMusicPlayer;

    private double backgroundMusicVolume = 1.0;
    private double soundEffectsVolume = 1.0;

    public SoundManager() {
        // Load all sound effects during initialization
        loadSoundEffect("shoot", "/sounds/shoot.mp3");
        loadSoundEffect("explosion", "/sounds/explosion.mp3");
        loadSoundEffect("win", "/sounds/win.mp3");
        loadSoundEffect("game_over", "/sounds/game_over.mp3");
        // Add more sound effects as needed
    }

    private void loadSoundEffect(String key, String resourcePath) {
        try {
            URL soundResource = getClass().getResource(resourcePath);
            if (soundResource == null) {
                throw new IllegalArgumentException("Sound resource not found: " + resourcePath);
            }
            Media sound = new Media(soundResource.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(soundEffectsVolume); // Use the current volume
            soundEffects.put(key, mediaPlayer);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load sound effect: " + resourcePath, e);
        }
    }

    public void playSoundEffect(String key) {
        MediaPlayer mediaPlayer = soundEffects.get(key);
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Stop if currently playing
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }

    public void playBackgroundMusic(String resourcePath) {
        try {
            if (backgroundMusicPlayer != null) {
                backgroundMusicPlayer.stop();
            }
            URL musicResource = getClass().getResource(resourcePath);
            if (musicResource == null) {
                throw new IllegalArgumentException("Music resource not found: " + resourcePath);
            }
            Media music = new Media(musicResource.toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(music);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
            backgroundMusicPlayer.setVolume(backgroundMusicVolume); // Use the current volume
            backgroundMusicPlayer.play();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to play background music: " + resourcePath, e);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    public void setSoundEffectsVolume(double volume) {
        this.soundEffectsVolume = volume; // Update the internal field
        for (MediaPlayer mediaPlayer : soundEffects.values()) {
            mediaPlayer.setVolume(volume);
        }
    }

    public void setBackgroundMusicVolume(double volume) {
        this.backgroundMusicVolume = volume; // Update the internal field
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    public double getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    public double getSoundEffectsVolume() {
        return soundEffectsVolume;
    }
}


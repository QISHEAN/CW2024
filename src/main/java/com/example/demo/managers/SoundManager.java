package com.example.demo.managers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the sound effects and background music for the game.
 * Handles loading, playing, stopping, and volume adjustments for audio resources.
 */
public class SoundManager {
    private static final Logger LOGGER = Logger.getLogger(SoundManager.class.getName());

    private final Map<String, MediaPlayer> soundEffects = new HashMap<>(); // Stores sound effects with their keys.
    private MediaPlayer backgroundMusicPlayer; // MediaPlayer for background music.

    private double backgroundMusicVolume = 1.0; // Volume for background music.
    private double soundEffectsVolume = 1.0; // Volume for sound effects.

    /**
     * Constructor to initialize the `SoundManager`.
     * Preloads common sound effects used in the game.
     */
    public SoundManager() {
        loadSoundEffect("shoot", "/sounds/shoot.mp3");
        loadSoundEffect("explosion", "/sounds/explosion.mp3");
        loadSoundEffect("win", "/sounds/win.mp3");
        loadSoundEffect("game_over", "/sounds/game_over.mp3");
    }

    /**
     * Loads a sound effect into memory and associates it with a key.
     *
     * @param key           The key to identify the sound effect.
     * @param resourcePath  The file path of the sound resource.
     */
    private void loadSoundEffect(String key, String resourcePath) {
        try {
            URL soundResource = getClass().getResource(resourcePath);
            if (soundResource == null) {
                throw new IllegalArgumentException("Sound resource not found: " + resourcePath);
            }
            Media sound = new Media(soundResource.toExternalForm());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(soundEffectsVolume); // Set initial volume.
            soundEffects.put(key, mediaPlayer); // Store the sound effect in the map.
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load sound effect: " + resourcePath, e);
        }
    }

    /**
     * Plays a sound effect by its key.
     * Restarts the sound effect if it is already playing.
     *
     * @param key The key identifying the sound effect.
     */
    public void playSoundEffect(String key) {
        MediaPlayer mediaPlayer = soundEffects.get(key);
        if (mediaPlayer != null) {
            mediaPlayer.stop(); // Stop any existing playback.
            mediaPlayer.seek(Duration.ZERO); // Reset to the start.
            mediaPlayer.play(); // Play the sound effect.
        }
    }

    /**
     * Plays background music from the specified resource path.
     * Stops any currently playing background music and loops the new music indefinitely.
     *
     * @param resourcePath The file path of the background music resource.
     */
    public void playBackgroundMusic(String resourcePath) {
        try {
            if (backgroundMusicPlayer != null) {
                backgroundMusicPlayer.stop(); // Stop the existing music.
            }
            URL musicResource = getClass().getResource(resourcePath);
            if (musicResource == null) {
                throw new IllegalArgumentException("Music resource not found: " + resourcePath);
            }
            Media music = new Media(musicResource.toExternalForm());
            backgroundMusicPlayer = new MediaPlayer(music);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music.
            backgroundMusicPlayer.setVolume(backgroundMusicVolume); // Set initial volume.
            backgroundMusicPlayer.play(); // Play the music.
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to play background music: " + resourcePath, e);
        }
    }

    /**
     * Stops the currently playing background music, if any.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop(); // Stop the music playback.
        }
    }

    /**
     * Sets the volume for all sound effects.
     * Updates the volume for each sound effect in the `soundEffects` map.
     *
     * @param volume The volume level (0.0 to 1.0).
     */
    public void setSoundEffectsVolume(double volume) {
        this.soundEffectsVolume = volume; // Update the volume field.
        for (MediaPlayer mediaPlayer : soundEffects.values()) {
            mediaPlayer.setVolume(volume); // Set volume for each sound effect.
        }
    }

    /**
     * Sets the volume for background music.
     * Applies the volume change to the currently playing background music.
     *
     * @param volume The volume level (0.0 to 1.0).
     */
    public void setBackgroundMusicVolume(double volume) {
        this.backgroundMusicVolume = volume; // Update the volume field.
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume); // Set volume for the background music.
        }
    }

    /**
     * Retrieves the current volume level for background music.
     *
     * @return The background music volume (0.0 to 1.0).
     */
    public double getBackgroundMusicVolume() {
        return backgroundMusicVolume;
    }

    /**
     * Retrieves the current volume level for sound effects.
     *
     * @return The sound effects volume (0.0 to 1.0).
     */
    public double getSoundEffectsVolume() {
        return soundEffectsVolume;
    }
}

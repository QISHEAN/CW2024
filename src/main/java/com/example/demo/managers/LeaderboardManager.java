package com.example.demo.managers;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the leaderboard by storing and retrieving player scores.
 * Handles reading and writing scores to a file, maintaining a sorted list of top scores.
 */
public class LeaderboardManager {

    private static final List<Integer> scores = new ArrayList<>(); // List to store scores.
    private static final String LEADERBOARD_FILE = "leaderboard.txt"; // File to persist leaderboard data.
    private static final int MAX_SCORES = 5; // Maximum number of scores to keep in the leaderboard.

    static {
        // Load existing scores from the file when the class is loaded.
        loadScores();
    }

    /**
     * Adds a new score to the leaderboard.
     * The leaderboard is updated to include the score, sorted in descending order,
     * and only the top `MAX_SCORES` are retained.
     * The updated leaderboard is then saved to the file.
     *
     * @param score the score to add.
     */
    public static void addScore(int score) {
        scores.add(score); // Add the new score to the list.
        scores.sort(Collections.reverseOrder()); // Sort the scores in descending order.
        if (scores.size() > MAX_SCORES) {
            scores.subList(MAX_SCORES, scores.size()).clear(); // Retain only the top `MAX_SCORES`.
        }
        saveScores(); // Save the updated scores to the file.
    }

    /**
     * Retrieves the top `N` scores from the leaderboard.
     * Returns a sublist of the scores list containing the requested number of top scores.
     *
     * @param topN the number of top scores to retrieve.
     * @return a list of top scores, up to `topN` entries.
     */
    public static List<Integer> getTopScores(int topN) {
        return new ArrayList<>(scores.subList(0, Math.min(topN, scores.size()))); // Return a copy of the top scores.
    }

    /**
     * Loads scores from the leaderboard file into the `scores` list.
     * If the file exists, it reads each line, parses it as an integer, and adds it to the list.
     * Invalid scores are ignored, and any errors during reading are logged.
     * The list is sorted in descending order, and only the top `MAX_SCORES` are retained.
     */
    private static void loadScores() {
        Path path = Paths.get(LEADERBOARD_FILE);
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        int score = Integer.parseInt(line.trim()); // Parse the score.
                        scores.add(score); // Add the score to the list.
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score in leaderboard file: " + line); // Log invalid scores.
                    }
                }
                scores.sort(Collections.reverseOrder()); // Sort the scores in descending order.
                if (scores.size() > MAX_SCORES) {
                    scores.subList(MAX_SCORES, scores.size()).clear(); // Retain only the top `MAX_SCORES`.
                }
            } catch (IOException e) {
                System.err.println("Error reading leaderboard file: " + e.getMessage()); // Log errors during file reading.
            }
        } else {
            System.out.println("Leaderboard file does not exist. Starting with an empty leaderboard.");
        }
    }

    /**
     * Saves the current `scores` list to the leaderboard file.
     * Writes each score to a new line in the file. If the file does not exist, it is created.
     * If there are errors during writing, they are logged.
     */
    private static void saveScores() {
        Path path = Paths.get(LEADERBOARD_FILE);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int score : scores) {
                writer.write(String.valueOf(score)); // Write the score to the file.
                writer.newLine(); // Move to the next line.
            }
        } catch (IOException e) {
            System.err.println("Error writing leaderboard file: " + e.getMessage()); // Log errors during file writing.
        }
    }
}

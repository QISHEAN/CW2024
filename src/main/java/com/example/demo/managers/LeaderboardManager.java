package com.example.demo.managers;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the leaderboard by storing and retrieving player scores.
 */
public class LeaderboardManager {

    private static final List<Integer> scores = new ArrayList<>();
    private static final String LEADERBOARD_FILE = "leaderboard.txt";
    private static final int MAX_SCORES = 5;

    static {
        // Load existing scores from the file when the class is loaded
        loadScores();
    }

    /**
     * Adds a score to the leaderboard and persists it.
     *
     * @param score the score to add.
     */
    public static void addScore(int score) {
        scores.add(score);
        // Sort the scores in descending order
        scores.sort(Collections.reverseOrder());
        // Keep only top MAX_SCORES
        if (scores.size() > MAX_SCORES) {
            scores.subList(MAX_SCORES, scores.size()).clear();
        }
        // Persist the updated scores to the file
        saveScores();
    }

    /**
     * Retrieves the top N scores from the leaderboard.
     *
     * @param topN the number of top scores to retrieve.
     * @return a list of top scores.
     */
    public static List<Integer> getTopScores(int topN) {
        return new ArrayList<>(scores.subList(0, Math.min(topN, scores.size())));
    }

    /**
     * Loads scores from the leaderboard file into the scores list.
     */
    private static void loadScores() {
        Path path = Paths.get(LEADERBOARD_FILE);
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        int score = Integer.parseInt(line.trim());
                        scores.add(score);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score in leaderboard file: " + line);
                    }
                }
                // Sort the scores in descending order
                scores.sort(Collections.reverseOrder());
                // Keep only top MAX_SCORES
                if (scores.size() > MAX_SCORES) {
                    scores.subList(MAX_SCORES, scores.size()).clear();
                }
            } catch (IOException e) {
                System.err.println("Error reading leaderboard file: " + e.getMessage());
            }
        } else {
            System.out.println("Leaderboard file does not exist. Starting with an empty leaderboard.");
        }
    }

    /**
     * Saves the current scores list to the leaderboard file.
     */
    private static void saveScores() {
        Path path = Paths.get(LEADERBOARD_FILE);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (int score : scores) {
                writer.write(String.valueOf(score));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing leaderboard file: " + e.getMessage());
        }
    }
}

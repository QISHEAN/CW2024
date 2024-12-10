package com.example.demo.managers;

import com.example.demo.JavaFXInitializer;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeaderboardManagerTest extends JavaFXInitializer {

    private static final String TEMP_LEADERBOARD_FILE = "leaderboard.txt";


    @AfterEach
    void tearDown() throws IOException {
        // Clean up the temporary leaderboard file
        Files.deleteIfExists(Paths.get(TEMP_LEADERBOARD_FILE));
    }



    @Test
    void testMaxScoresLimit() {
        LeaderboardManager.addScore(100);
        LeaderboardManager.addScore(90);
        LeaderboardManager.addScore(80);
        LeaderboardManager.addScore(70);
        LeaderboardManager.addScore(60);
        LeaderboardManager.addScore(50); // This should not appear in the top scores

        List<Integer> topScores = LeaderboardManager.getTopScores(5);
        assertEquals(5, topScores.size(), "Top scores list should contain a maximum of 5 entries");
        assertEquals(List.of(100, 90, 80, 70, 60), topScores, "Scores should be limited to the top 5");
    }
}

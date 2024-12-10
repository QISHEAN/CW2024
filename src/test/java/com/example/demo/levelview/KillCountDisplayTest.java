package com.example.demo.levelview;

import com.example.demo.JavaFXInitializer;
import javafx.scene.text.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KillCountDisplayTest extends JavaFXInitializer {


    @Test
    void testInitialState() {
        int initialKills = 0;
        double xPosition = 100;
        double yPosition = 50;
        KillCountDisplay killCountDisplay = new KillCountDisplay(xPosition, yPosition, initialKills);

        // Check that the initial text is correct
        Text displayText = killCountDisplay.displayText;
        assertEquals("Score: 0", displayText.getText(), "Initial score display should be 'Score: 0'");

        // Check layout positions
        assertEquals(xPosition, displayText.getLayoutX(), 0.001, "X position should match the constructor input");
        assertEquals(yPosition, displayText.getLayoutY(), 0.001, "Y position should match the constructor input");

        // Check style
        String style = displayText.getStyle();
        assertTrue(style.contains("-fx-font-size: 24px"), "Font size should be 24px");
        assertTrue(style.contains("-fx-fill: white"), "Text fill color should be white");
    }

    @Test
    void testGetDisplayText() {
        int initialKills = 10;
        KillCountDisplay killCountDisplay = new KillCountDisplay(100, 50, initialKills);

        String expected = "Score: 10";
        assertEquals(expected, killCountDisplay.getDisplayText(), "getDisplayText should return 'Score: 10' for currentKills=10");
    }

    // If you later add methods to increment the kill count or update the display,
    // you can add tests to verify those behaviors as well.
}

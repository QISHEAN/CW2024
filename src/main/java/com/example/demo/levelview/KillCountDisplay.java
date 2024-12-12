package com.example.demo.levelview;

import javafx.scene.text.Text;

/**
 * Class representing the kill count display in the game.
 * Manages the display of the player's current score on the screen.
 */
public class KillCountDisplay {

    protected int currentKills;
    protected Text displayText;

    /**
     * Constructor to initialize the kill count display.
     *
     * @param xPosition   The X-coordinate for positioning the text on the screen.
     * @param yPosition   The Y-coordinate for positioning the text on the screen.
     * @param initialKills The initial number of kills to display.
     */
    public KillCountDisplay(double xPosition, double yPosition, int initialKills) {
        this.currentKills = initialKills;
        this.displayText = new Text();
        displayText.setLayoutX(xPosition);
        displayText.setLayoutY(yPosition);
        displayText.setStyle("-fx-font-size: 24px; -fx-fill: white;");

        updateDisplayText();
    }

    /**
     * Retrieves the formatted text to display the current kill count.
     *
     * @return A formatted string representing the current kill count.
     */
    protected String getDisplayText() {
        return "Score: " + currentKills;
    }

    /**
     * Updates the displayed text with the current kill count.
     * Calls `getDisplayText()` to format the text before updating the `Text` node.
     */
    private void updateDisplayText() {
        displayText.setText(getDisplayText());//Update the text element with the current kill count.
    }

}

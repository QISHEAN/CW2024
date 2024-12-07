package com.example.demo.levelview;

import javafx.scene.text.Text;

public class KillCountDisplay {

    protected int currentKills;
    protected Text displayText;

    public KillCountDisplay(double xPosition, double yPosition, int initialKills) {
        this.currentKills = initialKills;
        this.displayText = new Text();
        displayText.setLayoutX(xPosition);
        displayText.setLayoutY(yPosition);
        displayText.setStyle("-fx-font-size: 24px; -fx-fill: white;");

        updateDisplayText();
    }

    protected String getDisplayText() {
        return "Score: " + currentKills;
    }

    private void updateDisplayText() {
        displayText.setText(getDisplayText());
    }

}

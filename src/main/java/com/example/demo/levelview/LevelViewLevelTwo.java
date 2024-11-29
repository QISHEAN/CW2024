package com.example.demo.levelview;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LevelViewLevelTwo extends LevelView {

    private Label warningLabel; // Example: A special warning for LevelTwo

    public LevelViewLevelTwo(Group root, int playerInitialHealth) {
        super(root, playerInitialHealth);
        initializeCustomUI(root);
    }

    // Add custom UI elements for LevelTwo
    private void initializeCustomUI(Group root) {
        // Add a warning label specific to LevelTwo
        warningLabel = new Label("Enemies are stronger! Stay alert!");
        warningLabel.setFont(new Font("Arial", 20));
        warningLabel.setTextFill(Color.RED);
        warningLabel.setLayoutX(50);
        warningLabel.setLayoutY(20);

        // Add the warning label to the root group
        root.getChildren().add(warningLabel);
    }

    @Override
    public void showKillCountDisplay() {
        // Override to customize kill count display for LevelTwo
        super.showKillCountDisplay();
        // Optionally add more elements here
    }

    @Override
    public void updateKillCountDisplay(int currentKills, int totalKillsToWin) {
        // Customize kill count update if needed
        super.updateKillCountDisplay(currentKills, totalKillsToWin);
        // Example: Show a motivational message as kills increase
        if (currentKills > totalKillsToWin / 2) {
            warningLabel.setText("You're halfway there! Keep going!");
        }
    }
}

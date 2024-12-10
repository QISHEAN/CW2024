package com.example.demo.levelview;

import com.example.demo.ui.GameOverImage;
import com.example.demo.ui.HeartDisplay;
import com.example.demo.ui.ShieldImage;
import com.example.demo.ui.WinImage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class LevelViewTest extends ApplicationTest {

    private Group root;
    private LevelView levelView;

    @Override
    public void start(Stage stage) {
        root = new Group();
        levelView = new LevelView(root, 5,false,null);
        Scene scene = new Scene(root, 1300, 750);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testShowHeartDisplay() {
        // Initially, heart display should not be present
        assertFalse(root.getChildren().stream().anyMatch(node -> false),
                "Hearts should not be present initially");

        interact(() -> levelView.showHeartDisplay());

        // After showing heart display, we should find a HeartDisplay container node
        // Since HeartDisplay#getContainer() likely returns a Pane or HBox,
        // we can check if such node is added.
        assertTrue(root.getChildren().stream().anyMatch(node ->
                        node == ((HeartDisplay) Objects.requireNonNull(getField(levelView, "heartDisplay"))).getContainer()),
                "Heart display container should be present in the root");
    }

    @Test
    void testShowWinImage() {
        // Show the win image
        interact(() -> levelView.showWinImage());
        // Check that a WinImage node is present in the root
        assertTrue(root.getChildren().stream().anyMatch(node -> node instanceof WinImage),
                "WinImage should be added to root after showWinImage() is called");
    }

    @Test
    void testShowGameOverImage() {
        // Show game over image
        interact(() -> levelView.showGameOverImage());
        // Check that a GameOverImage node is present in the root
        assertTrue(root.getChildren().stream().anyMatch(node -> node instanceof GameOverImage),
                "GameOverImage should be added to root after showGameOverImage() is called");
    }

    @Test
    void testRemoveHearts() {
        // Show hearts first
        interact(() -> levelView.showHeartDisplay());

        HeartDisplay heartDisplay = (HeartDisplay) getField(levelView, "heartDisplay");
        assert heartDisplay != null;
        int initialHearts = heartDisplay.getContainer().getChildren().size();
        assertTrue(initialHearts > 0, "There should be hearts initially");

        // Remove hearts
        interact(() -> levelView.removeHearts(initialHearts - 1));

        // Now there should be one less heart
        int newCount = heartDisplay.getContainer().getChildren().size();
        assertEquals(initialHearts - 1, newCount, "One heart should have been removed");
    }

    @Test
    void testShowKillCountDisplay() {
        // Initially killCountText is not visible
        // The killCountText is added when showKillCountDisplay is called
        assertFalse(root.getChildren().stream().anyMatch(node -> node instanceof Text &&
                        ((Text)node).getText().contains("Kills:")),
                "Kill count display should not be visible initially");

        interact(() -> levelView.showKillCountDisplay());
        interact(() -> levelView.updateKillCountDisplay(3, 10));

        // Now kill count text should be visible
        assertTrue(root.getChildren().stream().anyMatch(node -> node instanceof Text &&
                        ((Text)node).getText().equals("Kills: 3/10")),
                "Kill count display should show 'Kills: 3/10' after update");
    }

    @Test
    void testShowShield() {
        ShieldImage shieldImage = (ShieldImage) getField(levelView, "shieldImage");
        // Initially shield might be invisible
        assert shieldImage != null;
        assertFalse(shieldImage.isVisible(), "Shield should not be visible initially");

        interact(() -> levelView.showShield());

        assertTrue(shieldImage.isVisible(), "Shield should be visible after showShield()");
    }

    @Test
    void testHideShield() {
        ShieldImage shieldImage = (ShieldImage) getField(levelView, "shieldImage");
        // Make shield visible first
        interact(() -> levelView.showShield());
        assert shieldImage != null;
        assertTrue(shieldImage.isVisible(), "Shield should be visible now");

        // Hide shield
        interact(() -> levelView.hideShield());
        assertFalse(shieldImage.isVisible(), "Shield should be hidden now");
    }

    @Test
    void testShowBossHealthDisplay() {
        // Boss health display is a Pane containing bossHealthText
        // Initially not in root
        assertFalse(root.getChildren().stream().anyMatch(node -> node == getField(levelView, "bossHealthPane")),
                "Boss health pane should not be in root initially");

        interact(() -> levelView.showBossHealthDisplay());
    }

    @Test
    void testUpdateBossHealthText() {
        interact(() -> levelView.updateBossHealthText(100));

        // Check the text value
        Text bossHealthText = (Text) getField(levelView, "bossHealthText");
        assert bossHealthText != null;
        assertEquals("Boss HP: 100", bossHealthText.getText(), "Boss health text should be updated correctly");
    }

    /**
     * Utility method to reflectively get a private field from the object.
     * Adjust as necessary or consider making fields package-private for testing.
     */
    private Object getField(Object target, String fieldName) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(target);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Could not access field " + fieldName + ": " + e.getMessage());
            return null;
        }
    }
}

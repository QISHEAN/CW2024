package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class ActiveActorDestructibleTest extends JavaFXInitializer {

    private Stage testStage;
    private SoundManager soundManager;
    private TestActiveActorDestructible actor;

    /**
     * Concrete subclass of ActiveActorDestructible for testing purposes.
     */
    private static class TestActiveActorDestructible extends ActiveActorDestructible {

        public TestActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
            super(imageName, imageHeight, initialXPos, initialYPos);
            this.health = 100; // Default health
        }

        @Override
        public void updatePosition() {
            // Implement minimal behavior for testing
            // For example, move the actor by a fixed amount
            setX(getX() + 5);
            setY(getY() + 5);
            updateBoundingBox();
        }

        @Override
        public void updateActor() {
            // Implement minimal behavior for testing
            // For example, change color or perform a simple update
            boundingBox.setFill(Color.BLUE);
        }

        @Override
        public void takeDamage() {
            // Implement minimal behavior for testing
            this.health -= 10;
            if (this.health <= 0) {
                destroy();
            }
            updateBoundingBox();
        }
    }

    @BeforeEach
    void setUp() throws InterruptedException {
        Platform.runLater(() -> {
            testStage = new Stage();
            soundManager = Mockito.mock(SoundManager.class);
            actor = new TestActiveActorDestructible("blank.jpg", 50, 100.0, 150.0);
            // Optionally, add the actor to a scene graph
            Pane root = new Pane();
            root.getChildren().add(actor);
            root.getChildren().add(actor.getBoundingBox());
            Scene scene = new Scene(root, 800, 600);
            testStage.setScene(scene);
            testStage.show();
            actor.updateBoundingBox();
        });
        waitForFXEvents();
    }


    /**
     * Tests the updatePosition method.
     */
    @Test
    void testUpdatePosition() throws InterruptedException {
        Platform.runLater(() -> {
            double initialX = actor.getBoundingBox().getX();
            double initialY = actor.getBoundingBox().getY();
            actor.updatePosition();
            // Assuming updatePosition moves the actor by +5 on both axes
            assertEquals(initialX + 5, actor.getBoundingBox().getX(), 0.001, "Bounding box X should increase by 5");
            assertEquals(initialY + 5, actor.getBoundingBox().getY(), 0.001, "Bounding box Y should increase by 5");
        });
        waitForFXEvents();
    }

    /**
     * Tests the updateBoundingBox method.
     */
    @Test
    void testUpdateBoundingBox() throws InterruptedException {
        Platform.runLater(() -> {
            // Move the actor
            actor.setX(200.0);
            actor.setY(250.0);
            actor.updateBoundingBox();
            // Verify that bounding box reflects the new position
            assertEquals(200.0, actor.getBoundingBox().getX(), 0.001, "Bounding box X should match actor's X");
            assertEquals(250.0, actor.getBoundingBox().getY(), 0.001, "Bounding box Y should match actor's Y");
        });
        waitForFXEvents();
    }

    /**
     * Tests the removePlane method.
     */
    @Test
    void testRemovePlane() throws InterruptedException {
        Platform.runLater(() -> {
            Group root = (Group) testStage.getScene().getRoot();
            actor.removePlane(root);
            assertTrue(actor.isDestroyed(), "Actor should be marked as destroyed after removePlane()");
            assertFalse(root.getChildren().contains(actor), "Actor should be removed from the scene graph");
            assertFalse(root.getChildren().contains(actor.getBoundingBox()), "Bounding box should be removed from the scene graph");
        });
        waitForFXEvents();
    }

    /**
     * Tests the setHealth and getHealth methods.
     */
    @Test
    void testSetAndGetHealth() throws InterruptedException {
        Platform.runLater(() -> {
            actor.setHealth(80);
            assertEquals(80, actor.getHealth(), "Health should be set to 80");
        });
        waitForFXEvents();
    }

    /**
     * Helper method to wait for JavaFX events to be processed.
     */
    private void waitForFXEvents() throws InterruptedException {
        Thread.sleep(100); // Adjust the duration if necessary
    }
}

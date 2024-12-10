package com.example.demo.managers;

import com.example.demo.JavaFXInitializer;
import com.example.demo.actor.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EntityManagerTest extends JavaFXInitializer {

    private EntityManager entityManager;
    private Group root;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize the Group and EntityManager on the JavaFX thread
        runAndWait(() -> {
            root = new Group();
            entityManager = new EntityManager(root);
        });
    }

    @AfterEach
    void tearDown() {
        // Optional: Clear the root Group after each test
        try {
            runAndWait(() -> root.getChildren().clear());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to run tasks on the JavaFX Application Thread and wait for their completion.
     *
     * @param action The task to run.
     */
    private void runAndWait(Runnable action) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        boolean completed = latch.await(5, TimeUnit.SECONDS);
        if (!completed) {
            fail("Timeout waiting for JavaFX tasks to complete.");
        }
    }

    /**
     * Creates a mocked ActiveActorDestructible with a specified bounding box.
     *
     * @param x      The x-coordinate of the bounding box.
     * @param y      The y-coordinate of the bounding box.
     * @param width  The width of the bounding box.
     * @param height The height of the bounding box.
     * @return A mocked ActiveActorDestructible.
     */
    private ActiveActorDestructible createMockedActor(double x, double y, double width, double height) {
        // Mock ActiveActorDestructible
        ActiveActorDestructible actor = mock(ActiveActorDestructible.class);

        // Mock Rectangle as boundingBox
        Rectangle boundingBox = mock(Rectangle.class);

        // Mock Bounds
        Bounds bounds = mock(Bounds.class);

        // Define the bounding box properties
        when(bounds.getMinX()).thenReturn(x);
        when(bounds.getMinY()).thenReturn(y);
        when(bounds.getMaxX()).thenReturn(x + width);
        when(bounds.getMaxY()).thenReturn(y + height);

        // Define intersects behavior
        when(bounds.intersects(any(Bounds.class))).thenAnswer(invocation -> {
            Bounds other = invocation.getArgument(0);
            return !(bounds.getMaxX() < other.getMinX() ||
                    bounds.getMinX() > other.getMaxX() ||
                    bounds.getMaxY() < other.getMinY() ||
                    bounds.getMinY() > other.getMaxY());
        });

        // Mock getBoundsInParent() to return the mocked bounds
        when(boundingBox.getBoundsInParent()).thenReturn(bounds);

        // Mock getBoundingBox() to return the mocked Rectangle
        when(actor.getBoundingBox()).thenReturn(boundingBox);

        // Optional: Mock other methods as needed
        // e.g., actor.isDestroyed(), actor.updateActor(), actor.updateBoundingBox()

        return actor;
    }

    /**
     * Tests adding a friendly unit to the EntityManager.
     */
    @Test
    void testAddFriendlyUnit() throws InterruptedException {
        // Arrange
        ActiveActorDestructible friendly = createMockedActor(50, 50, 100, 100);

        // Act
        runAndWait(() -> entityManager.addFriendlyUnit(friendly));

        // Assert
        assertTrue(entityManager.getFriendlyUnits().contains(friendly), "Friendly unit should be in friendlyUnits list.");
        assertTrue(root.getChildren().contains(friendly), "Friendly unit should be added to root children.");
        assertTrue(root.getChildren().contains(friendly.getBoundingBox()), "Friendly unit's bounding box should be added to root children.");
    }

    /**
     * Tests adding an enemy unit to the EntityManager.
     */
    @Test
    void testAddEnemyUnit() throws InterruptedException {
        // Arrange
        ActiveActorDestructible enemy = createMockedActor(150, 150, 100, 100);

        // Act
        runAndWait(() -> entityManager.addEnemyUnit(enemy));

        // Assert
        assertTrue(entityManager.getEnemyUnits().contains(enemy), "Enemy unit should be in enemyUnits list.");
        assertTrue(root.getChildren().contains(enemy), "Enemy unit should be added to root children.");
        assertTrue(root.getChildren().contains(enemy.getBoundingBox()), "Enemy unit's bounding box should be added to root children.");
    }

    /**
     * Tests adding a user projectile to the EntityManager.
     */
    @Test
    void testAddUserProjectile() throws InterruptedException {
        // Arrange
        ActiveActorDestructible projectile = createMockedActor(250, 250, 10, 10);

        // Act
        runAndWait(() -> entityManager.addUserProjectile(projectile));

        // Assert
        assertTrue(entityManager.getUserProjectiles().contains(projectile), "User projectile should be in userProjectiles list.");
        assertTrue(root.getChildren().contains(projectile), "User projectile should be added to root children.");
        assertTrue(root.getChildren().contains(projectile.getBoundingBox()), "User projectile's bounding box should be added to root children.");
    }

    /**
     * Tests adding an enemy projectile to the EntityManager.
     */
    @Test
    void testAddEnemyProjectile() throws InterruptedException {
        // Arrange
        ActiveActorDestructible enemyProjectile = createMockedActor(350, 350, 10, 10);

        // Act
        runAndWait(() -> entityManager.addEnemyProjectile(enemyProjectile));

        // Assert
        assertTrue(entityManager.getEnemyProjectiles().contains(enemyProjectile), "Enemy projectile should be in enemyProjectiles list.");
        assertTrue(root.getChildren().contains(enemyProjectile), "Enemy projectile should be added to root children.");
        assertTrue(root.getChildren().contains(enemyProjectile.getBoundingBox()), "Enemy projectile's bounding box should be added to root children.");
    }

    /**
     * Tests that updateEntities() calls updateActor() and updateBoundingBox() on all entities.
     */
    @Test
    void testUpdateEntities() throws InterruptedException {
        // Arrange
        ActiveActorDestructible friendly = createMockedActor(50, 50, 100, 100);
        ActiveActorDestructible enemy = createMockedActor(150, 150, 100, 100);
        ActiveActorDestructible userProjectile = createMockedActor(250, 250, 10, 10);
        ActiveActorDestructible enemyProjectile = createMockedActor(350, 350, 10, 10);

        // Add entities to EntityManager
        runAndWait(() -> {
            entityManager.addFriendlyUnit(friendly);
            entityManager.addEnemyUnit(enemy);
            entityManager.addUserProjectile(userProjectile);
            entityManager.addEnemyProjectile(enemyProjectile);
        });

        // Act
        runAndWait(() -> entityManager.updateEntities());

        // Assert
        verify(friendly, times(1)).updateActor();
        verify(friendly, times(1)).updateBoundingBox();
        verify(enemy, times(1)).updateActor();
        verify(enemy, times(1)).updateBoundingBox();
        verify(userProjectile, times(1)).updateActor();
        verify(userProjectile, times(1)).updateBoundingBox();
        verify(enemyProjectile, times(1)).updateActor();
        verify(enemyProjectile, times(1)).updateBoundingBox();
    }

    /**
     * Tests that removeAllDestroyedActors() removes destroyed enemy units and returns the correct count.
     */
    @Test
    void testRemoveAllDestroyedActors() throws InterruptedException {
        // Arrange
        ActiveActorDestructible enemy1 = createMockedActor(150, 150, 100, 100);
        ActiveActorDestructible enemy2 = createMockedActor(160, 160, 100, 100);
        ActiveActorDestructible friendly1 = createMockedActor(50, 50, 100, 100);
        ActiveActorDestructible userProjectile1 = createMockedActor(250, 250, 10, 10);
        ActiveActorDestructible enemyProjectile1 = createMockedActor(350, 350, 10, 10);

        // Mock isDestroyed()
        when(enemy1.isDestroyed()).thenReturn(true);
        when(enemy2.isDestroyed()).thenReturn(false);
        when(friendly1.isDestroyed()).thenReturn(true);
        when(userProjectile1.isDestroyed()).thenReturn(false);
        when(enemyProjectile1.isDestroyed()).thenReturn(true);

        // Add entities to EntityManager
        runAndWait(() -> {
            entityManager.addEnemyUnit(enemy1);
            entityManager.addEnemyUnit(enemy2);
            entityManager.addFriendlyUnit(friendly1);
            entityManager.addUserProjectile(userProjectile1);
            entityManager.addEnemyProjectile(enemyProjectile1);
        });

        // Act
        int enemiesDestroyed = runAndWaitAndReturn(() -> entityManager.removeAllDestroyedActors());

        // Assert
        assertEquals(1, enemiesDestroyed, "One enemy should be destroyed.");
        assertFalse(entityManager.getEnemyUnits().contains(enemy1), "Destroyed enemy1 should be removed from enemyUnits.");
        assertTrue(entityManager.getEnemyUnits().contains(enemy2), "Enemy2 should remain in enemyUnits.");
        assertFalse(entityManager.getFriendlyUnits().contains(friendly1), "Destroyed friendly1 should be removed from friendlyUnits.");
        assertFalse(root.getChildren().contains(enemy1), "Destroyed enemy1 should be removed from root.");
        assertFalse(root.getChildren().contains(friendly1), "Destroyed friendly1 should be removed from root.");
        assertFalse(root.getChildren().contains(enemyProjectile1), "Destroyed enemyProjectile1 should be removed from root.");
        assertTrue(root.getChildren().contains(enemy2), "Enemy2 should remain in root.");
        assertTrue(root.getChildren().contains(userProjectile1), "UserProjectile1 should remain in root.");
    }

    /**
     * Helper method to run tasks on the JavaFX Application Thread, wait for completion, and return a value.
     *
     * @param action The task to run, which returns a value.
     * @param <T>    The type of the return value.
     * @return The result of the action.
     */
    private <T> T runAndWaitAndReturn(java.util.concurrent.Callable<T> action) throws InterruptedException {
        final Object[] result = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                result[0] = action.call();
            } catch (Exception e) {
                fail("Exception during runAndWaitAndReturn: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
        boolean completed = latch.await(5, TimeUnit.SECONDS);
        if (!completed) {
            fail("Timeout waiting for JavaFX tasks to complete.");
        }
        @SuppressWarnings("unchecked")
        T returnValue = (T) result[0];
        return returnValue;
    }

    /**
     * Tests that removeAllDestroyedActors() removes destroyed friendly units and projectiles without affecting enemies.
     */
    @Test
    void testRemoveAllDestroyedActors_MultipleEntities() throws InterruptedException {
        // Arrange
        ActiveActorDestructible enemy1 = createMockedActor(150, 150, 100, 100);
        ActiveActorDestructible enemy2 = createMockedActor(160, 160, 100, 100);
        ActiveActorDestructible friendly1 = createMockedActor(50, 50, 100, 100);
        ActiveActorDestructible friendly2 = createMockedActor(60, 60, 100, 100);
        ActiveActorDestructible userProjectile1 = createMockedActor(250, 250, 10, 10);
        ActiveActorDestructible enemyProjectile1 = createMockedActor(350, 350, 10, 10);

        // Mock isDestroyed()
        when(enemy1.isDestroyed()).thenReturn(true);
        when(enemy2.isDestroyed()).thenReturn(false);
        when(friendly1.isDestroyed()).thenReturn(true);
        when(friendly2.isDestroyed()).thenReturn(false);
        when(userProjectile1.isDestroyed()).thenReturn(true);
        when(enemyProjectile1.isDestroyed()).thenReturn(true);

        // Add entities to EntityManager
        runAndWait(() -> {
            entityManager.addEnemyUnit(enemy1);
            entityManager.addEnemyUnit(enemy2);
            entityManager.addFriendlyUnit(friendly1);
            entityManager.addFriendlyUnit(friendly2);
            entityManager.addUserProjectile(userProjectile1);
            entityManager.addEnemyProjectile(enemyProjectile1);
        });

        // Act
        int enemiesDestroyed = runAndWaitAndReturn(() -> entityManager.removeAllDestroyedActors());

        // Assert
        assertEquals(1, enemiesDestroyed, "One enemy should be destroyed.");
        assertFalse(entityManager.getEnemyUnits().contains(enemy1), "Destroyed enemy1 should be removed from enemyUnits.");
        assertTrue(entityManager.getEnemyUnits().contains(enemy2), "Enemy2 should remain in enemyUnits.");
        assertFalse(entityManager.getFriendlyUnits().contains(friendly1), "Destroyed friendly1 should be removed from friendlyUnits.");
        assertTrue(entityManager.getFriendlyUnits().contains(friendly2), "Friendly2 should remain in friendlyUnits.");
        assertFalse(root.getChildren().contains(enemy1), "Destroyed enemy1 should be removed from root.");
        assertTrue(root.getChildren().contains(enemy2), "Enemy2 should remain in root.");
        assertFalse(root.getChildren().contains(friendly1), "Destroyed friendly1 should be removed from root.");
        assertTrue(root.getChildren().contains(friendly2), "Friendly2 should remain in root.");
        assertFalse(root.getChildren().contains(userProjectile1), "Destroyed userProjectile1 should be removed from root.");
        assertFalse(root.getChildren().contains(enemyProjectile1), "Destroyed enemyProjectile1 should be removed from root.");
    }

    /**
     * Tests that clearEnemies() removes all enemy units from the EntityManager and the root.
     */
    @Test
    void testClearEnemies() throws InterruptedException {
        // Arrange
        ActiveActorDestructible enemy1 = createMockedActor(150, 150, 100, 100);
        ActiveActorDestructible enemy2 = createMockedActor(160, 160, 100, 100);
        ActiveActorDestructible friendly1 = createMockedActor(50, 50, 100, 100);

        // Add entities to EntityManager
        runAndWait(() -> {
            entityManager.addEnemyUnit(enemy1);
            entityManager.addEnemyUnit(enemy2);
            entityManager.addFriendlyUnit(friendly1);
        });

        // Act
        runAndWait(() -> entityManager.clearEnemies());

        // Assert
        assertTrue(entityManager.getEnemyUnits().isEmpty(), "Enemy units list should be empty after clearing.");
        assertFalse(root.getChildren().contains(enemy1), "Enemy1 should be removed from root after clearing.");
        assertFalse(root.getChildren().contains(enemy2), "Enemy2 should be removed from root after clearing.");
        assertTrue(entityManager.getFriendlyUnits().contains(friendly1), "Friendly1 should remain after clearing enemies.");
        assertTrue(root.getChildren().contains(friendly1), "Friendly1 should remain in root after clearing enemies.");
    }

    /**
     * Tests that clearAllProjectiles() removes all user and enemy projectiles from the EntityManager and the root.
     */
    @Test
    void testClearAllProjectiles() throws InterruptedException {
        // Arrange
        ActiveActorDestructible userProjectile1 = createMockedActor(250, 250, 10, 10);
        ActiveActorDestructible userProjectile2 = createMockedActor(260, 260, 10, 10);
        ActiveActorDestructible enemyProjectile1 = createMockedActor(350, 350, 10, 10);
        ActiveActorDestructible enemyProjectile2 = createMockedActor(360, 360, 10, 10);
        ActiveActorDestructible enemy1 = createMockedActor(150, 150, 100, 100);

        // Add entities to EntityManager
        runAndWait(() -> {
            entityManager.addUserProjectile(userProjectile1);
            entityManager.addUserProjectile(userProjectile2);
            entityManager.addEnemyProjectile(enemyProjectile1);
            entityManager.addEnemyProjectile(enemyProjectile2);
            entityManager.addEnemyUnit(enemy1);
        });

        // Act
        runAndWait(() -> entityManager.clearAllProjectiles());

        // Assert
        assertTrue(entityManager.getUserProjectiles().isEmpty(), "User projectiles list should be empty after clearing.");
        assertTrue(entityManager.getEnemyProjectiles().isEmpty(), "Enemy projectiles list should be empty after clearing.");
        assertFalse(root.getChildren().contains(userProjectile1), "UserProjectile1 should be removed from root after clearing.");
        assertFalse(root.getChildren().contains(userProjectile2), "UserProjectile2 should be removed from root after clearing.");
        assertFalse(root.getChildren().contains(enemyProjectile1), "EnemyProjectile1 should be removed from root after clearing.");
        assertFalse(root.getChildren().contains(enemyProjectile2), "EnemyProjectile2 should be removed from root after clearing.");
        assertTrue(root.getChildren().contains(enemy1), "Enemy1 should remain after clearing projectiles.");
    }
}

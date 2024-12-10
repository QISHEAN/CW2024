package com.example.demo.managers;

import com.example.demo.actor.ActiveActorDestructible;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CollisionManagerTest {

    private CollisionManager collisionManager;

    @BeforeEach
    void setUp() {
        collisionManager = new CollisionManager();
    }

    @Test
    void testHandlePlaneCollisions() {
        ActiveActorDestructible friendly = mockActor(100, 100, 50, 50);
        ActiveActorDestructible enemy = mockActor(120, 120, 50, 50);

        List<ActiveActorDestructible> friendlyUnits = new ArrayList<>();
        friendlyUnits.add(friendly);
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(enemy);

        collisionManager.handlePlaneCollisions(friendlyUnits, enemyUnits);

        verify(friendly).takeDamage();
        verify(enemy).takeDamage();
    }

    @Test
    void testHandleUserProjectileCollisions() {
        ActiveActorDestructible userProjectile = mockActor(100, 100, 10, 10);
        ActiveActorDestructible enemy = mockActor(105, 105, 50, 50);

        List<ActiveActorDestructible> userProjectiles = new ArrayList<>();
        userProjectiles.add(userProjectile);
        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(enemy);

        collisionManager.handleUserProjectileCollisions(userProjectiles, enemyUnits);

        verify(userProjectile).takeDamage();
        verify(enemy).takeDamage();
    }

    @Test
    void testHandleEnemyProjectileCollisions() {
        ActiveActorDestructible enemyProjectile = mockActor(200, 200, 10, 10);
        ActiveActorDestructible friendly = mockActor(210, 210, 50, 50);

        List<ActiveActorDestructible> enemyProjectiles = new ArrayList<>();
        enemyProjectiles.add(enemyProjectile);
        List<ActiveActorDestructible> friendlyUnits = new ArrayList<>();
        friendlyUnits.add(friendly);

        collisionManager.handleEnemyProjectileCollisions(enemyProjectiles, friendlyUnits);

        verify(enemyProjectile).takeDamage();
        verify(friendly).takeDamage();
    }

    @Test
    void testHandleEnemyPenetration() {
        ActiveActorDestructible enemy = mockActor(800, 100, 50, 50);
        ActiveActorDestructible user = mock(ActiveActorDestructible.class);

        List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
        enemyUnits.add(enemy);

        Group root = mock(Group.class);
        double screenWidth = 600;

        collisionManager.handleEnemyPenetration(enemyUnits, user, root, screenWidth);

        verify(user).takeDamage();
        verify(enemy).removePlane(root);
        assert enemyUnits.isEmpty();
    }

    private ActiveActorDestructible mockActor(double x, double y, double width, double height) {
        ActiveActorDestructible actor = mock(ActiveActorDestructible.class);
        Rectangle boundingBox = new Rectangle(x, y, width, height);
        when(actor.getBoundingBox()).thenReturn(boundingBox);
        when(actor.getTranslateX()).thenReturn(x);
        return actor;
    }
}

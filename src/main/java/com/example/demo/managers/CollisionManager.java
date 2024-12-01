package com.example.demo.managers;

import com.example.demo.actor.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {

    public void handlePlaneCollisions(List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits) {
        for (ActiveActorDestructible friendly : friendlyUnits) {
            for (ActiveActorDestructible enemy : enemyUnits) {
                if (friendly.getBoundingBox().getBoundsInParent().intersects(enemy.getBoundingBox().getBoundsInParent())) {
                    friendly.takeDamage();
                    enemy.takeDamage();
                }
            }
        }
    }

    public void handleUserProjectileCollisions(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits);
    }

    public void handleEnemyProjectileCollisions(List<ActiveActorDestructible> enemyProjectiles, List<ActiveActorDestructible> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor1 : actors1) {
            for (ActiveActorDestructible actor2 : actors2) {
                if (actor1.getBoundingBox().getBoundsInParent().intersects(actor2.getBoundingBox().getBoundsInParent())) {
                    actor1.takeDamage();
                    actor2.takeDamage();
                }
            }
        }
    }

    public void handleEnemyPenetration(List<ActiveActorDestructible> enemyUnits, ActiveActorDestructible user, Group root, double screenWidth) {
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                user.takeDamage();
                enemy.removePlane(root);
                enemyUnits.remove(enemy);
            }
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }
}

package com.example.demo.managers;

import com.example.demo.actor.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Manager class responsible for handling collisions between different game entities.
 * Detects and resolves collisions between planes, projectiles, and other actors.
 */
public class CollisionManager {

    /**
     * Handles collisions between friendly units and enemy units.
     * When a collision is detected, both the friendly and enemy units take damage.
     *
     * @param friendlyUnits List of friendly units in the game.
     * @param enemyUnits    List of enemy units in the game.
     */
    public void handlePlaneCollisions(List<ActiveActorDestructible> friendlyUnits, List<ActiveActorDestructible> enemyUnits) {
        for (ActiveActorDestructible friendly : friendlyUnits) {
            for (ActiveActorDestructible enemy : enemyUnits) {
                if (friendly.getBoundingBox().getBoundsInParent().intersects(enemy.getBoundingBox().getBoundsInParent())) {
                    friendly.takeDamage(); // Apply damage to the friendly unit.
                    enemy.takeDamage(); // Apply damage to the enemy unit.
                }
            }
        }
    }

    /**
     * Handles collisions between the user's projectiles and enemy units.
     * Both the projectile and the enemy take damage upon collision.
     *
     * @param userProjectiles List of projectiles fired by the user.
     * @param enemyUnits      List of enemy units in the game.
     */
    public void handleUserProjectileCollisions(List<ActiveActorDestructible> userProjectiles, List<ActiveActorDestructible> enemyUnits) {
        handleCollisions(userProjectiles, enemyUnits); // Delegate to the generic collision handler.
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     * Both the projectile and the friendly unit take damage upon collision.
     *
     * @param enemyProjectiles List of projectiles fired by enemies.
     * @param friendlyUnits    List of friendly units in the game.
     */
    public void handleEnemyProjectileCollisions(List<ActiveActorDestructible> enemyProjectiles, List<ActiveActorDestructible> friendlyUnits) {
        handleCollisions(enemyProjectiles, friendlyUnits); // Delegate to the generic collision handler.
    }

    /**
     * Generic method to handle collisions between two lists of actors.
     * Detects collisions and applies damage to both actors involved.
     *
     * @param actors1 The first list of actors.
     * @param actors2 The second list of actors.
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor1 : actors1) {
            for (ActiveActorDestructible actor2 : actors2) {
                if (actor1.getBoundingBox().getBoundsInParent().intersects(actor2.getBoundingBox().getBoundsInParent())) {
                    actor1.takeDamage(); // Apply damage to the first actor.
                    actor2.takeDamage(); // Apply damage to the second actor.
                }
            }
        }
    }

    /**
     * Handles enemy units that penetrate the user's defenses.
     * Removes the enemy from the game and applies damage to the user.
     *
     * @param enemyUnits List of enemy units in the game.
     * @param user       The user's plane.
     * @param root       The root group containing all game elements.
     * @param screenWidth The width of the screen, used to detect penetration.
     */
    public void handleEnemyPenetration(List<ActiveActorDestructible> enemyUnits, ActiveActorDestructible user, Group root, double screenWidth) {
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) { // Iterate over a copy to avoid modification issues.
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                user.takeDamage(); // Apply damage to the user.
                enemy.removePlane(root); // Remove the enemy plane from the game.
                enemyUnits.remove(enemy); // Remove the enemy from the list.
            }
        }
    }

    /**
     * Checks if an enemy has penetrated the user's defenses by moving off-screen.
     *
     * @param enemy       The enemy actor to check.
     * @param screenWidth The width of the screen.
     * @return True if the enemy has moved beyond the screen bounds, false otherwise.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth; // Check if the enemy is off-screen.
    }
}

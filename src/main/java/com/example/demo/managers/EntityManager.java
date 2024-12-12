package com.example.demo.managers;

import com.example.demo.actor.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for managing entities in the game.
 * Handles adding, updating, and removing entities such as friendly units, enemy units, and projectiles.
 */
public class EntityManager {

    private final Group root; // The root group containing all visual elements.

    // Lists to track different types of entities in the game.
    private final List<ActiveActorDestructible> friendlyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> userProjectiles = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyProjectiles = new ArrayList<>();

    /**
     * Constructor to initialize the entity manager.
     *
     * @param root The root group for adding and removing entities.
     */
    public EntityManager(Group root) {
        this.root = root;
    }

    /**
     * Adds a friendly unit to the game.
     *
     * @param friendlyUnit The friendly unit to add.
     */
    public void addFriendlyUnit(ActiveActorDestructible friendlyUnit) {
        friendlyUnits.add(friendlyUnit);
        if (!root.getChildren().contains(friendlyUnit)) { // Avoid duplicates.
            root.getChildren().add(friendlyUnit);
        }
    }

    /**
     * Adds an enemy unit to the game.
     *
     * @param unit The enemy unit to add.
     */
    public void addEnemyUnit(ActiveActorDestructible unit) {
        enemyUnits.add(unit);
        root.getChildren().add(unit);
    }

    /**
     * Adds a projectile fired by the user to the game.
     *
     * @param projectile The user projectile to add.
     */
    public void addUserProjectile(ActiveActorDestructible projectile) {
        userProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    /**
     * Adds a projectile fired by an enemy to the game.
     *
     * @param projectile The enemy projectile to add.
     */
    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        enemyProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    /**
     * Retrieves the list of friendly units.
     *
     * @return List of friendly units.
     */
    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    /**
     * Retrieves the list of enemy units.
     *
     * @return List of enemy units.
     */
    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    /**
     * Retrieves the list of projectiles fired by the user.
     *
     * @return List of user projectiles.
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /**
     * Retrieves the list of projectiles fired by enemies.
     *
     * @return List of enemy projectiles.
     */
    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    /**
     * Updates all entities by calling their `updateActor` and `updateBoundingBox` methods.
     */
    public void updateEntities() {
        updateActors(friendlyUnits);
        updateActors(enemyUnits);
        updateActors(userProjectiles);
        updateActors(enemyProjectiles);
    }

    /**
     * Updates a list of actors by calling their update methods.
     *
     * @param actors The list of actors to update.
     */
    private void updateActors(List<ActiveActorDestructible> actors) {
        for (ActiveActorDestructible actor : actors) {
            actor.updateActor(); // Update actor position or state.
            actor.updateBoundingBox(); // Update actor's bounding box for collision detection.
        }
    }

    /**
     * Removes all destroyed entities from the game.
     *
     * @return The number of enemies destroyed.
     */
    public int removeAllDestroyedActors() {
        int enemiesDestroyed = removeDestroyedActors(enemyUnits); // Count destroyed enemies.
        removeDestroyedActors(friendlyUnits); // Remove destroyed friendly units.
        removeDestroyedActors(userProjectiles); // Remove destroyed user projectiles.
        removeDestroyedActors(enemyProjectiles); // Remove destroyed enemy projectiles.
        return enemiesDestroyed;
    }

    /**
     * Removes all destroyed actors from a given list.
     *
     * @param actors The list of actors to check for destruction.
     * @return The number of destroyed actors removed.
     */
    private int removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed) // Find destroyed actors.
                .toList();

        for (ActiveActorDestructible actor : destroyedActors) {
            root.getChildren().remove(actor); // Remove the actor from the root group.
            root.getChildren().remove(actor.getBoundingBox()); // Remove the actor's bounding box.
        }
        actors.removeAll(destroyedActors); // Remove actors from the list.
        return destroyedActors.size();
    }

    /**
     * Clears all enemy units from the game.
     */
    public void clearEnemies() {
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
            root.getChildren().remove(enemy); // Remove enemy from root group.
            root.getChildren().remove(enemy.getBoundingBox()); // Remove bounding box.
        }
        enemyUnits.clear(); // Clear the enemy list.
    }

    /**
     * Clears all projectiles (both user and enemy) from the game.
     */
    public void clearAllProjectiles() {
        // Remove all user projectiles.
        for (ActiveActorDestructible projectile : new ArrayList<>(userProjectiles)) {
            root.getChildren().remove(projectile);
            root.getChildren().remove(projectile.getBoundingBox());
        }
        userProjectiles.clear();

        // Remove all enemy projectiles.
        for (ActiveActorDestructible projectile : new ArrayList<>(enemyProjectiles)) {
            root.getChildren().remove(projectile);
            root.getChildren().remove(projectile.getBoundingBox());
        }
        enemyProjectiles.clear();
    }
}

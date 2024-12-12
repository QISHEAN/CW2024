package com.example.demo.managers;

import com.example.demo.actor.ActiveActorDestructible;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private final Group root;

    private final List<ActiveActorDestructible> friendlyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyUnits = new ArrayList<>();
    private final List<ActiveActorDestructible> userProjectiles = new ArrayList<>();
    private final List<ActiveActorDestructible> enemyProjectiles = new ArrayList<>();

    public EntityManager(Group root) {
        this.root = root;
    }

    public void addFriendlyUnit(ActiveActorDestructible friendlyUnit) {
        friendlyUnits.add(friendlyUnit);
        if (!root.getChildren().contains(friendlyUnit)) {
            root.getChildren().add(friendlyUnit);
        }
    }

    public void addEnemyUnit(ActiveActorDestructible unit) {
        enemyUnits.add(unit);
        root.getChildren().add(unit);
    }

    public void addUserProjectile(ActiveActorDestructible projectile) {
        userProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    public void addEnemyProjectile(ActiveActorDestructible projectile) {
        enemyProjectiles.add(projectile);
        root.getChildren().add(projectile);
    }

    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public void updateEntities() {
        updateActors(friendlyUnits);
        updateActors(enemyUnits);
        updateActors(userProjectiles);
        updateActors(enemyProjectiles);
    }

    private void updateActors(List<ActiveActorDestructible> actors) {
        for (ActiveActorDestructible actor : actors) {
            actor.updateActor();
            actor.updateBoundingBox();
        }
    }

    public int removeAllDestroyedActors() {
        int enemiesDestroyed = removeDestroyedActors(enemyUnits);
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
        return enemiesDestroyed;
    }

    private int removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .toList();

        for (ActiveActorDestructible actor : destroyedActors) {
            root.getChildren().remove(actor);
            root.getChildren().remove(actor.getBoundingBox());
        }
        actors.removeAll(destroyedActors);
        return destroyedActors.size();
    }

    public void clearEnemies() {
        for (ActiveActorDestructible enemy : new ArrayList<>(enemyUnits)) {
            root.getChildren().remove(enemy);
            root.getChildren().remove(enemy.getBoundingBox());
        }
        enemyUnits.clear();
    }

    public void clearAllProjectiles() {
        for (ActiveActorDestructible projectile : new ArrayList<>(userProjectiles)) {
            root.getChildren().remove(projectile);
            root.getChildren().remove(projectile.getBoundingBox());
        }
        userProjectiles.clear();

        for (ActiveActorDestructible projectile : new ArrayList<>(enemyProjectiles)) {
            root.getChildren().remove(projectile);
            root.getChildren().remove(projectile.getBoundingBox());
        }
        enemyProjectiles.clear();
    }
}

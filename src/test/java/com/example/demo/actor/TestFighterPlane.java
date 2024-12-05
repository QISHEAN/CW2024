package com.example.demo.actor;

import com.example.demo.projectiles.UserProjectile;
import org.mockito.Mockito;

public class TestFighterPlane extends FighterPlane {

    public TestFighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos, health);
    }

    @Override
    public UserProjectile fireProjectile() {
        // Return a mock UserProjectile for testing purposes
        return Mockito.mock(UserProjectile.class);
    }

    @Override
    public void updateActor() {
        // Implement a simple update or leave empty for testing
    }

    @Override
    public void updateBoundingBox() {
        // Implement as needed for testing
    }

    @Override
    public void updatePosition() {
        // No-operation implementation for testing
    }
}

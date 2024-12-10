package com.example.demo.actor;

/**
 * Test subclass of UserPlane to expose protected methods for testing purposes.
 */
public class TestUserPlane extends UserPlane {

    /**
     * Constructor for TestUserPlane.
     *
     * @param imageName     The name/path of the image to represent the UserPlane.
     * @param initialHealth The initial health of the UserPlane.
     */
    public TestUserPlane(String imageName, int initialHealth) {
        super(imageName, initialHealth);
    }

    /**
     * Exposes the protected method getProjectileXPosition for testing.
     *
     * @param offset The X offset for the projectile.
     * @return The calculated X position for the projectile.
     */
    @Override
    public double getProjectileXPosition(double offset) {
        return super.getProjectileXPosition(offset);
    }

    /**
     * Exposes the protected method getProjectileYPosition for testing.
     *
     * @param offset The Y offset for the projectile.
     * @return The calculated Y position for the projectile.
     */
    @Override
    public double getProjectileYPosition(double offset) {
        return super.getProjectileYPosition(offset);
    }
}

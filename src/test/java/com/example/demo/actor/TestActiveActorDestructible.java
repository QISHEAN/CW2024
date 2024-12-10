package com.example.demo.actor;

/**
 * Concrete subclass of ActiveActorDestructible for testing purposes.
 */
public class TestActiveActorDestructible extends ActiveActorDestructible {

    /**
     * Constructor for TestActiveActorDestructible.
     *
     * @param imageName    Name of the image file located in /com/example/demo/images/
     * @param imageHeight  Desired height for the image
     * @param initialXPos  Initial X position
     * @param initialYPos  Initial Y position
     * @param initialHealth Initial health value
     */
    public TestActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos, int initialHealth) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = initialHealth;
    }

    /**
     * Implementation of the abstract updatePosition method.
     * For testing, we'll move the actor horizontally by -5 units and vertically by +5 units.
     */
    @Override
    public void updatePosition() {
        // Move horizontally by -5 units (leftward movement)
        moveHorizontally(-5.0);
        // Move vertically by +5 units (downward movement)
        moveVertically(5.0);
    }

    /**
     * Implementation of the abstract updateActor method.
     * For testing, this method simply calls updatePosition.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }

    /**
     * Implementation of the abstract takeDamage method.
     * Reduces health by 1 and destroys the actor if health <= 0.
     */
    @Override
    public void takeDamage() {
        if (!isDestroyed()) {
            health -= 1;
            if (health <= 0) {
                destroy();
            }
        }
    }
}

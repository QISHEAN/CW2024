package com.example.demo.actor;

/**
 * Concrete subclass of ActiveActor for testing purposes.
 */
public class TestActiveActor extends ActiveActor {

    /**
     * Constructor for TestActiveActor.
     *
     * @param imageName   Name of the image file located in /com/example/demo/images/
     * @param imageHeight Desired height for the image
     * @param initialXPos Initial X position
     * @param initialYPos Initial Y position
     */
    public TestActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(imageName, imageHeight, initialXPos, initialYPos);
    }

    /**
     * Simple implementation of updatePosition().
     * For testing, we'll just move the actor by fixed amounts.
     */
    @Override
    public void updatePosition() {
        // For testing, move horizontally by +10 and vertically by +5
        moveHorizontally(10);
        moveVertically(5);
    }
}

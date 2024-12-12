package com.example.demo.actor;

/**
 * Interface representing destructible entities in the game.
 * Any class implementing this interface must provide behavior
 * for taking damage and being destroyed.
 */
public interface Destructible {

	/**
	 * Method to apply damage to the object. Implementing classes
	 * should define how the object reacts to taking damage.
	 */
	void takeDamage();

	/**
	 * Method to destroy the object. Implementing classes should define
	 * the behavior for when the object is destroyed, such as cleanup or removal from the game.
	 */
	void destroy();
	
}

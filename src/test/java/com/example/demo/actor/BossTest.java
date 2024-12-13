package com.example.demo.actor;

import com.example.demo.JavaFXInitializer;
import javafx.scene.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BossTest extends JavaFXInitializer {

    private Boss boss;


    @BeforeEach
    void setUp() {
        boss = new Boss();
        Group root = new Group();
        root.getChildren().add(boss);
        root.getChildren().add(boss.getBoundingBox());
    }

    @Test
    void testInitialState() {
        assertEquals(20, boss.getHealth(), "Boss should have initial health of 10");
        assertFalse(boss.getIsShield(), "Boss should not be shielded initially");
    }

    @Test
    void testUpdatePosition() {
        // The boss moves according to a pattern. Calling updatePosition should not throw.
        assertDoesNotThrow(() -> boss.updatePosition());
        // Since it depends on random moves (shuffled pattern),
        // we just ensure no exceptions and that consecutive calls do not cause issues.
        for (int i = 0; i < 50; i++) {
            assertDoesNotThrow(() -> boss.updatePosition());
        }
    }

    @Test
    void testTakeDamage() {
        int initialHealth = boss.getHealth();
        boss.takeDamage();
        assertEquals(initialHealth - 1, boss.getHealth(), "Boss health should decrease by 1 when taking damage");

        // Reduce health to zero
        for (int i = 0; i < 20; i++) {
            boss.takeDamage();
        }
        assertEquals(0, boss.getHealth(), "Boss health should not go below 0");
        assertTrue(boss.isDestroyed(), "Boss should be destroyed after health reaches 0");
    }

    @Test
    void testTakeDamageWithShield() {
        // Activate shield using reflection or directly call activateShield if accessible
        // For testing, we can call boss.activateShield() if it is protected you can consider reflection,
        // or temporarily make it package-private for testing.
        activateShieldForTest();

        int healthBefore = boss.getHealth();
        boss.takeDamage();
        // No damage should be taken because shield is active
        assertEquals(healthBefore, boss.getHealth(), "Health should remain the same when shielded");

        deactivateShieldForTest();
        boss.takeDamage();
        assertEquals(healthBefore - 1, boss.getHealth(), "Health should decrease after shield is removed");
    }

    @Test
    void testShieldActivationAndDeactivation() {
        // The shield activation is probabilistic. We'll force it using reflection for a deterministic test.
        activateShieldForTest();
        assertTrue(boss.getIsShield(), "Shield should be activated");

        // Simulate frames to exhaust shield
        for (int i = 0; i < 100; i++) {
            boss.updateActor(); // This calls updateShield internally
        }
        assertFalse(boss.getIsShield(), "Shield should deactivate after maximum frames");
    }

    @Test
    void testFireProjectile() {
        // Firing is random. For deterministic test, we can run it multiple times and allow
        // that sometimes no projectile is returned. Just ensure no exception:
        assertDoesNotThrow(() -> boss.fireProjectile());

        // If you want to guarantee a projectile, mock Math.random or modify the code for testing.
        // Without mocking, we just ensure no exceptions.
    }

    @Test
    void testUpdateBoundingBox() {
        // Just ensure no exceptions:
        assertDoesNotThrow(() -> boss.updateBoundingBox());
    }

    @Test
    void testUpdateActor() {
        // updateActor calls updatePosition and updateShield
        // Just ensure no exceptions and boss remains valid
        assertDoesNotThrow(() -> boss.updateActor());
        assertFalse(boss.isDestroyed(), "Boss should not be destroyed by just updating");
    }

    // --- Helper methods to manipulate shield state for testing ---

    private void activateShieldForTest() {
        // The boss has activateShield() as protected, we can use reflection to invoke it:
        try {
            var method = Boss.class.getDeclaredMethod("activateShield");
            method.setAccessible(true);
            method.invoke(boss);
        } catch (Exception e) {
            fail("Failed to activate shield via reflection: " + e.getMessage());
        }
    }

    private void deactivateShieldForTest() {
        // Similarly for deactivateShield():
        try {
            var method = Boss.class.getDeclaredMethod("deactivateShield");
            method.setAccessible(true);
            method.invoke(boss);
        } catch (Exception e) {
            fail("Failed to deactivate shield via reflection: " + e.getMessage());
        }
    }
}

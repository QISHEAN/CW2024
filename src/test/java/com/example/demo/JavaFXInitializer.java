package com.example.demo; // Adjust the package as needed

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Base class to initialize JavaFX once for all tests.
 */
public abstract class JavaFXInitializer {

    private static boolean initialized = false;

    /**
     * Initializes the JavaFX toolkit before any tests are run.
     */
    @BeforeAll
    public static synchronized void initJavaFX() throws InterruptedException {
        if (!initialized) {
            CountDownLatch latch = new CountDownLatch(1);
            try {
                // No action needed; just initialize JavaFX
                Platform.startup(latch::countDown);
            } catch (IllegalStateException e) {
                // JavaFX already initialized
                latch.countDown();
            }
            if (!latch.await(5, TimeUnit.SECONDS)) {
                throw new IllegalStateException("JavaFX platform did not start.");
            }
            initialized = true;
        }
    }
}

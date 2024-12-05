package com.example.demo;// src/test/java/com/example/demo/util/JavaFXInitializer.java

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

import java.util.concurrent.CountDownLatch;

public class JavaFXInitializer {
    private static boolean initialized = false;


    @BeforeAll
    public static synchronized void initJavaFX() throws InterruptedException {
        if (!initialized) {
            final CountDownLatch latch = new CountDownLatch(1);
            // No need to do anything here
            Platform.startup(latch::countDown);
            latch.await();
            initialized = true;
        }
    }
}

package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PauseMenuControllerTest {

    private PauseMenuController controller;

    @BeforeEach
    void setUp() {
        controller = new PauseMenuController();
    }

    @Test
    void testOnResumeClicked_NoActionSet() {
        // No actions set
        // Calling onResumeClicked should not throw or do anything
        assertDoesNotThrow(() -> controller.onResumeClicked());
    }

    @Test
    void testOnRestartClicked_NoActionSet() {
        assertDoesNotThrow(() -> controller.onRestartClicked());
    }

    @Test
    void testOnExitToMainMenuClicked_NoActionSet() {
        assertDoesNotThrow(() -> controller.onExitToMainMenuClicked());
    }

    @Test
    void testOnResumeClicked_ActionSet() {
        // Create a flag to check if runnable is run
        final boolean[] wasRun = {false};

        // Set the resume action
        controller.setActions(
                () -> wasRun[0] = true,
                null,
                null
        );

        controller.onResumeClicked();
        assertTrue(wasRun[0], "Resume action should have been run");
    }

    @Test
    void testOnRestartClicked_ActionSet() {
        final boolean[] wasRun = {false};

        controller.setActions(
                null,
                () -> wasRun[0] = true,
                null
        );

        controller.onRestartClicked();
        assertTrue(wasRun[0], "Restart action should have been run");
    }

    @Test
    void testOnExitToMainMenuClicked_ActionSet() {
        final boolean[] wasRun = {false};

        controller.setActions(
                null,
                null,
                () -> wasRun[0] = true
        );

        controller.onExitToMainMenuClicked();
        assertTrue(wasRun[0], "Main menu action should have been run");
    }
}

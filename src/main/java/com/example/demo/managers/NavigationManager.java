package com.example.demo.managers;

import com.example.demo.level.LevelListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NavigationManager {

    private final List<LevelListener> levelListeners = new CopyOnWriteArrayList<>();

    public void addLevelListener(LevelListener listener) {
        levelListeners.add(listener);
    }

    public void removeLevelListener(LevelListener listener) {
        levelListeners.remove(listener);
    }

    public void notifyLevelChange(String nextLevelClassName) {
        List<LevelListener> listenersCopy = new ArrayList<>(levelListeners);
        for (LevelListener listener : listenersCopy) {
            listener.onLevelChange(nextLevelClassName);
        }
    }

    public void notifyExitToMainMenu() {
        for (LevelListener listener : levelListeners) {
            listener.exitToMainMenu();
        }
    }

    public void notifyRestartLevel() {
        for (LevelListener listener : levelListeners) {
            listener.restartLevel();
        }
    }
}

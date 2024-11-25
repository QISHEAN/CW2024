package com.example.demo.controller;

import com.example.demo.level.LevelListener;
import com.example.demo.level.LevelParent;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller implements LevelListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	private static final Logger logger = Logger.getLogger(Controller.class.getName());

	static {
		// Remove default handlers
		Logger rootLogger = Logger.getLogger("");
		java.util.logging.Handler[] handlers = rootLogger.getHandlers();
		for (java.util.logging.Handler handler : handlers) {
			rootLogger.removeHandler(handler);
		}

		// Set up a new ConsoleHandler with the custom formatter
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new CustomFormatter());
		logger.addHandler(consoleHandler);

		// Set the logging level
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
	}
	private final Stage stage;
	private LevelParent currentLevel;  // Reference to the current level

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() {
		stage.show();
		try {
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			showErrorAlert(e);
		}
	}

	private void goToLevel(String className) throws Exception {
		removeLevelListener();
		Class<?> myClass = Class.forName(className);
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());
		currentLevel = myLevel;
		myLevel.addLevelListener(this); // Add this controller as a listener
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);
		myLevel.startGame();
	}

	private void removeLevelListener() {
		if (currentLevel != null) {
			currentLevel.removeLevelListener(this); // Remove this controller as a listener
			stage.setScene(null);  // Remove the current scene
			currentLevel = null;
		}
	}

	@Override
	public void onLevelChange(String nextLevelClassName) {
		//Use Platform.runLater to schedule the level change after current events are processed
		Platform.runLater(() -> {
			try {
				goToLevel(nextLevelClassName);
			} catch (Exception e) {
				showErrorAlert(e);
			}
		});
	}

	private void showErrorAlert(Exception e) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(e.getMessage());
		alert.show();
		// Log the exception
		logger.log(Level.SEVERE, "An exception occurred", e);
	}

}

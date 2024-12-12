package com.example.demo.controller;

import com.example.demo.level.LevelListener;
import com.example.demo.level.LevelParent;
import com.example.demo.managers.SoundManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller class that manages the game's levels and main menu.
 * Implements the LevelListener interface to respond to level changes.
 */
public class Controller implements LevelListener {

	private static final String LEVEL_ONE_CLASS_NAME = "com.example.demo.level.LevelOne";
	private static final Logger logger = Logger.getLogger(Controller.class.getName());

	static {
		//Remove default handlers
		Logger rootLogger = Logger.getLogger("");
		java.util.logging.Handler[] handlers = rootLogger.getHandlers();
		for (java.util.logging.Handler handler : handlers) {
			rootLogger.removeHandler(handler);
		}

		//Set up a new ConsoleHandler with a custom formatter
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new CustomFormatter());
		logger.addHandler(consoleHandler);

		//Set the logging level
		logger.setLevel(Level.ALL);
		consoleHandler.setLevel(Level.ALL);
	}

	private final Stage stage;
	private final SoundManager soundManager;
	private LevelParent currentLevel; // Reference to the current level

	/**
	 * Constructor to initialize the Controller.
	 *
	 * @param stage        The primary stage for the application.
	 * @param soundManager Manages the game's sound effects and music.
	 */
	public Controller(Stage stage, SoundManager soundManager) {
		this.stage = stage;
		this.soundManager = soundManager;
	}

	/**
	 * Launches the game by showing the stage and loading the first level.
	 */
	public void launchGame() {
		stage.show();
		try {
			goToLevel(LEVEL_ONE_CLASS_NAME);
		} catch (Exception e) {
			showErrorAlert(e);
		}
	}

	/**
	 * Loads and transitions to the specified level.
	 *
	 * @param className The fully qualified name of the level's class.
	 */
	private void goToLevel(String className) {
		Platform.runLater(() -> {
			try {
				removeLevelListener();
				Class<?> myClass = Class.forName(className);
				Constructor<?> constructor = myClass.getConstructor(double.class, double.class, SoundManager.class, Stage.class);
				LevelParent myLevel = (LevelParent) constructor.newInstance(1300, 750, soundManager, stage);
				currentLevel = myLevel;
				myLevel.addLevelListener(this); // Add this controller as a listener
				Scene scene = myLevel.initializeScene();
				stage.setScene(scene);
				myLevel.startGame();
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error loading level: " + className, e);
				showErrorAlert(e);
			}
		});
	}

	/**
	 * Removes the listener from the current level and stops the level.
	 * Clears the scene and sets the current level to null.
	 */
	private void removeLevelListener() {
		if (currentLevel != null) {
			currentLevel.removeLevelListener(this);
			currentLevel.stopGame(); // Ensure the level stops.
			stage.setScene(null);
			currentLevel = null;
		}
	}

	/**
	 * Handles level change requests by transitioning to the specified level.
	 *
	 * @param nextLevelClassName The fully qualified name of the next level's class.
	 */
	@Override
	public void onLevelChange(String nextLevelClassName) {
		Platform.runLater(() -> {
			try {
				goToLevel(nextLevelClassName);
			} catch (Exception e) {
				showErrorAlert(e);
			}
		});
	}

	/**
	 * Returns to the main menu by loading the menu screen.
	 */
	public void exitToMainMenu() {
		Platform.runLater(() -> {
			try {
				loadMainMenu();
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error exiting to main menu", e);
				showErrorAlert(e);
			}
		});
	}

	/**
	 * Restarts the current level by reloading it.
	 */
	@Override
	public void restartLevel() {
		if (currentLevel != null) {
			String currentLevelClassName = currentLevel.getClass().getName();
			goToLevel(currentLevelClassName);
		}
	}

	/**
	 * Loads the main menu FXML file and sets the scene to the stage.
	 *
	 * @throws Exception If there is an issue loading the FXML file.
	 */
	private void loadMainMenu() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuScreen.fxml"));
		Parent root = loader.load();
		MenuController menuController = loader.getController();
		menuController.setStage(stage);
		Scene scene = new Scene(root, 1300, 750);
		stage.setScene(scene);
	}

	/**
	 * Displays an error alert with the exception message.
	 *
	 * @param e The exception that occurred.
	 */
	private void showErrorAlert(Exception e) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("An error occurred");
		alert.setContentText(e.getMessage());
		alert.showAndWait();

		//Log the exception
		logger.log(Level.SEVERE, "An exception occurred", e);
	}
}

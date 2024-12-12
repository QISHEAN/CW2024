package com.example.demo.controller;

import com.example.demo.managers.SoundManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.*;

/**
 * Entry point for the "Sky Battle" application.
 * This class initializes the application, sets up logging, and loads the main menu.
 */
public class Main extends Application {

	//Constants for screen dimensions and application title.
	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";

	/**
	 * Starts the application by setting up the main stage and displaying the menu screen.
	 *
	 * @param stage The primary stage for the application.
	 * @throws SecurityException       If there is an issue with security policies.
	 * @throws IllegalArgumentException If invalid arguments are encountered.
	 */
	@Override
	public void start(Stage stage) throws SecurityException, IllegalArgumentException {
		//Initialize the sound manager for handling game audio.
		SoundManager soundManager = new SoundManager();

		try {
			//Load the main menu FXML file.
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuScreen.fxml"));
			Parent root = loader.load();

			//Get the controller for the menu and pass the stage and sound manager.
			MenuController menuController = loader.getController();
			menuController.setStage(stage);
			menuController.setSoundManager(soundManager);

			//Create a scene with the specified dimensions and set it on the stage.
			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			stage.setScene(scene);
			stage.setTitle(TITLE);
			stage.setResizable(false); // Prevent resizing for consistent UI layout.
			stage.show();
		} catch (Exception e) {
			//Print an error message if the FXML file fails to load.
			System.err.println("Error loading FXML file: " + e.getMessage());
		}
	}

	/**
	 * The main method, entry point of the application.
	 * Configures logging and launches the JavaFX application.
	 *
	 * @param args Command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		configureLogging();
		launch(args); // Launch the JavaFX application.
	}

	/**
	 * Configures logging for the application by removing default handlers and
	 * adding a custom console handler with a specific log level and formatter.
	 */
	private static void configureLogging() {
		//Remove default handlers to avoid duplicate logs.
		Logger rootLogger = Logger.getLogger("");
		for (Handler handler : rootLogger.getHandlers()) {
			rootLogger.removeHandler(handler);
		}

		//Create a new ConsoleHandler with the custom formatter.
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new CustomFormatter()); // Use the custom log formatter.
		handler.setLevel(Level.ALL); // Log all levels.

		//Add the new handler to the root logger.
		rootLogger.addHandler(handler);
		rootLogger.setLevel(Level.WARNING); // Set the log level to WARNING to suppress verbose logs.

		//Suppress unnecessary logs from JavaFX framework components.
		Logger.getLogger("javafx").setLevel(Level.WARNING);
		Logger.getLogger("javafx.scene").setLevel(Level.WARNING);
		Logger.getLogger("javafx.scene.input").setLevel(Level.WARNING);
		Logger.getLogger("javafx.scene.focus").setLevel(Level.WARNING);
		Logger.getLogger("javafx.css").setLevel(Level.WARNING);
	}
}

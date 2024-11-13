package com.example.demo.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";



    @Override
	public void start(Stage stage) throws SecurityException,
            IllegalArgumentException {

		try {
			// Load the FXML file
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MenuScreen.fxml"));
			Parent root = loader.load();
			MenuController menuController = loader.getController();
			menuController.setStage(stage);

			// Create the Scene with FXML root and set it on the stage
			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			stage.setScene(scene);
			stage.setTitle(TITLE);
			stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
			System.err.println("Error loading FXML file: " + e.getMessage());
		}


	}

	public static void main(String[] args) {
		launch(args);
	}
}
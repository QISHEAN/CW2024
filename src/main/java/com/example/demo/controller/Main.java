package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static final int SCREEN_WIDTH = 1300;
	private static final int SCREEN_HEIGHT = 750;
	private static final String TITLE = "Sky Battle";



    @Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

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
//			stage.setHeight(SCREEN_HEIGHT);
//			stage.setWidth(SCREEN_WIDTH);
			stage.show();
//        Controller myController = new Controller(stage);
//		myController.launchGame();
		} catch (Exception e) {
			System.err.println("Error loading FXML file: " + e.getMessage());
		}


	}

	public static void main(String[] args) {
		launch(args);
	}
}
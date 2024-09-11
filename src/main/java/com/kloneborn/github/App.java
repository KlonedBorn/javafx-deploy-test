package com.kloneborn.github;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("First JavaFX Application");
        stage.setScene(FXMLLoader.load(App.class.getResource("primary.fxml")));
        stage.centerOnScreen();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
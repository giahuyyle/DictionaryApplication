package com.huylegia.dictionaryapplication;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static com.huylegia.dictionaryapplication.DictionaryController.dictionaryManagement;

public class DictionaryApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("app.fxml"));
        Scene scene = new Scene(root, 600,400);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                dictionaryManagement.saveData();
            }
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
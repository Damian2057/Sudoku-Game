package com.example.gui;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                    .getResource("MainMenu.fxml")));
            Scene scene = new Scene(root,500,530);
            stage.setResizable(false);
            stage.setTitle("SudokuMenu");
            stage.setScene(scene);
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}

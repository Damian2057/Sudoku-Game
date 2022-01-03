package com.example.gui;

import javafx.application.Application;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        try {
            MainMenu m = new MainMenu();
            m.menuShow();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

}

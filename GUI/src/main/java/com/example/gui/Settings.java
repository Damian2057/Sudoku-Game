package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.io.IOException;

public class Settings {

    public MenuButton langButton;
    public Button cancel;
    public Button apply;

    public Settings() {
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }


    public void applySettings(ActionEvent actionEvent) {
    }

    public void cancelSettings(ActionEvent actionEvent) {
    }

    public void englishSet(ActionEvent actionEvent) {
    }

    public void polskiSet(ActionEvent actionEvent) {
    }
}

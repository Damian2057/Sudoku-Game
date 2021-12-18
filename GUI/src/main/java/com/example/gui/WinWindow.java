package com.example.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WinWindow {

    public WinWindow() {

    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("WinStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 220, 150);
        Stage stage = new Stage();
        stage.setTitle("WYGRALES");
        stage.setScene(scene);
        stage.show();
    }
}

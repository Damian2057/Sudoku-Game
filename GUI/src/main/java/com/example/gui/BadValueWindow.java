package com.example.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class BadValueWindow {

    public Button closeButton;
    public TextField test;

    public BadValueWindow() {
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoaderb = new FXMLLoader();
        fxmlLoaderb.setLocation(getClass().getResource("BadValueStage.fxml"));
        Scene scene = new Scene(fxmlLoaderb.load(), 220, 150);
        Stage stage = new Stage();
        stage.setTitle("BAD");
        stage.setScene(scene);
        stage.show();
    }


    public void closeApp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

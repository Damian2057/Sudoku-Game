package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LostWindow {

    public Button tryAgainButton;

    public LostWindow() {
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("LostStage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 220, 150);
        Stage stage = new Stage();
        stage.setTitle("Przegrales");
        stage.setScene(scene);
        stage.show();
    }

    public void tryAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) tryAgainButton.getScene().getWindow();
        stage.close();
    }
}

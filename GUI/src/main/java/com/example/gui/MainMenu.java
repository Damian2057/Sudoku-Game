package com.example.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainMenu {

    public void gameStart(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();

        Game game = loader.getController();
        game.StartGame();

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("SUDOKUGAME");
        stage.show();
    }
}

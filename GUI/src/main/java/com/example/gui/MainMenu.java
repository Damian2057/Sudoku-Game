package com.example.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sudoku.level.*;


public class MainMenu {


    private Level level;

    @FXML
    public MenuButton levelBar;

    public void gameStart(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();

        if(level == null) {
            level = new VeryEasy();
        }
        Game game = loader.getController();
        game.StartGame(level);

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("SUDOKUGAME");
        stage.show();
    }

    public void veryEasy(ActionEvent actionEvent) {
        level = new VeryEasy();
        levelBar.setText("VeryEasy");
    }

    public void Easy(ActionEvent actionEvent) {
        level = new Easy();
        levelBar.setText("Easy");
    }

    public void Medium(ActionEvent actionEvent) {
        level = new Medium();
        levelBar.setText("Medium");
    }

    public void Hard(ActionEvent actionEvent) {
        level = new Hard();
        levelBar.setText("Hard");
    }
}

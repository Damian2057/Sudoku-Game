package com.example.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import sudoku.level.Easy;
import sudoku.level.Hard;
import sudoku.level.Level;
import sudoku.level.Medium;
import sudoku.level.VeryEasy;

public class MainMenu {
    private Level level;

    @FXML
    public MenuButton levelBar;

    public void gameStart(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        if (level == null) {
            level = new VeryEasy();
        }
        Parent root = loader.load();
        Game game = loader.getController();
        game.startGame(level);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Stage stage = new Stage();
        stage.setScene(new Scene(root,525,650));
        stage.setResizable(false);
        stage.setTitle("SUDOKUGAME");
        stage.show();
    }

    public void veryEasy(ActionEvent actionEvent) {
        level = new VeryEasy();
        levelBar.setText("VeryEasy");
    }

    public void easy(ActionEvent actionEvent) {
        level = new Easy();
        levelBar.setText("Easy");
    }

    public void medium(ActionEvent actionEvent) {
        level = new Medium();
        levelBar.setText("Medium");
    }

    public void hard(ActionEvent actionEvent) {
        level = new Hard();
        levelBar.setText("Hard");
    }
}

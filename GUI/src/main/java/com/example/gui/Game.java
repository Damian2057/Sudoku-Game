package com.example.gui;

import impl.org.controlsfx.spreadsheet.GridCellEditor;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuField;
import sudoku.level.Level;

public class Game {
    @FXML
    public Label gameLevel;
    @FXML
    private GridPane plansza;
    @FXML
    private javafx.scene.control.Button closeButton;

    private static SudokuBoard sudokuBoard;

    public void StartGame(Level level){
        gameLevel.setText(level.toString());
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        level.removeFields(sudokuBoard);
        for(int i = 0; i<9;i++) {
            for(int j = 0; j <9;j++){
                if(sudokuBoard.getSudokuField(i,j).getFieldValue() == 0) {
                    TextField textField = new TextField("");
                    textField.setMaxSize(100,100);
                    textField.setAlignment(Pos.CENTER);
                    plansza.add(textField, i, j);
                } else {
                    TextField textField = new TextField(String.valueOf(sudokuBoard.getSudokuField(i,j).getFieldValue()));
                    textField.setAlignment(Pos.CENTER);
                    textField.setMaxSize(100,100);
                    textField.setEditable(false);
                    plansza.add(textField, i, j);
                }
            }
        }
    }

    public void closeApp(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

package com.example.gui;

import impl.org.controlsfx.spreadsheet.GridCellEditor;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuField;

public class Game {
    @FXML
    public GridPane plansza;
    @FXML
    private javafx.scene.control.Button closeButton;

    private static SudokuBoard sudokuBoard;

    public void StartGame(){
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        for(int i = 0; i<9;i++) {
            for(int j = 0; j <9;j++){
                TextField textField = new TextField(String.valueOf(sudokuBoard.getSudokuField(i,j).getFieldValue()));
                textField.setMaxSize(100,100);
                plansza.add(textField, i, j);

            }
        }
    }

    public void closeApp(ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

package com.example.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sudoku.BacktrackingSudokuSolver;
import sudoku.FieldVerify;
import sudoku.SudokuBoard;
import sudoku.level.Level;


public class Game {
    @FXML
    public Label gameLevel;
    @FXML
    private GridPane plansza;
    @FXML
    private javafx.scene.control.Button closeButton;

    private static SudokuBoard sudokuBoard;

    public void startGame(Level level) throws IOException {
        initBoard(level);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudokuBoard.getSudokuField(i,j).getFieldValue() == 0) {
                    fullTextField(i,j);
                } else {
                    patchyTextField(i,j);
                }
            }
        }
    }

    private void setBoard(int x, int y, int value) {
        sudokuBoard.set(y,x,value);
    }


    private void fullTextField(int i, int j) {
        TextField textField = new TextField("");
        textField.setMaxSize(100,100);
        textField.setAlignment(Pos.CENTER);
        textField.getStyleClass().add("custom");

        plansza.add(textField, i, j);
        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    setBoard(GridPane.getRowIndex(textField),
                            GridPane.getColumnIndex(textField), fieldVerification(textField));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int fieldVerification(TextField textField) throws IOException {
        FieldVerify fieldVerify = new FieldVerify();
        int num = fieldVerify.verifyTextField(textField.getText());
        if (num == -1) {
            textField.setText("");
            BadValueWindow badValueWindow = new BadValueWindow();
            badValueWindow.show();
        }
        return num;
    }

    private void patchyTextField(int i, int j) {
        TextField textField = new TextField(String.valueOf(sudokuBoard
                .getSudokuField(i,j).getFieldValue()));
        textField.setAlignment(Pos.CENTER);
        textField.setMaxSize(100,100);
        textField.setEditable(false);
        plansza.add(textField, i, j);
    }

    private void initBoard(Level level) {
        plansza.setHgap(3);
        plansza.setVgap(3);
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        gameLevel.setText(level.toString());
        level.removeFields(sudokuBoard);
    }

    public void closeApp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void checkBoard(ActionEvent actionEvent) throws IOException {
        if (sudokuBoard.checkvalid()) {
            WinWindow w = new WinWindow();
            w.show();
        } else {
            System.out.println("pozniej bedzie zly uklad/przegrana?");
        }
    }
}

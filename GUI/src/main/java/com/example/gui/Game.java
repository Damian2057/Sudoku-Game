package com.example.gui;

import factories.FileSudokuBoardDao;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import sudoku.BacktrackingSudokuSolver;
import sudoku.FieldVerify;
import sudoku.SudokuBoard;
import sudoku.SudokuField;
import sudoku.level.Level;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ResourceBundle;


public class Game {
    @FXML
    public Label gameLevel;
    public Button checker;
    public Pane loadButton;
    public Pane startConf;
    public Pane saveButton;
    public Pane startOldConf;
    public Label Levell;

    @FXML
    private GridPane plansza;
    @FXML
    private Button closeButton;

    private static SudokuBoard sudokuBoardActual;
    private static SudokuBoard sudokuBoardStart;
    private ResourceBundle bundle;

    public void startGame(Level level, ResourceBundle bundle) {
        initBoard(level);
        this.bundle = bundle;
        setNames(bundle);

        putValues(sudokuBoardActual);
        sudokuBoardStart = sudokuBoardActual.clone();
        Tooltip.install(loadButton, new Tooltip(bundle.getString("load")));
        Tooltip.install(startConf, new Tooltip(bundle.getString("startconf")));
        Tooltip.install(saveButton, new Tooltip(bundle.getString("save")));
        Tooltip.install(startOldConf, new Tooltip(bundle.getString("startoldconf")));
    }

    private void putValues(SudokuBoard board) {
        decideLevel(bundle);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.getSudokuField(i, j).isEditable()) {
                    fullTextField(board,i,j);
                } else {
                    patchyTextField(board,i,j);
                }
            }
        }
    }

    private void Verify(SudokuBoard board, int x, int y, int value) {

        board.getSudokuField(y,x).setFieldValue(value);

    }


    private void fullTextField(SudokuBoard board, int i, int j) {
        TextField textField = new TextField();
        textField.getStyleClass().add("custom");
        Bindings.bindBidirectional(textField.textProperty(), board.getSudokuField(i,j).fieldProperty());

        if(board.getSudokuField(i,j).getFieldValue() != 0) {
            textField.getStyleClass().remove("custom");
            textField.getStyleClass().add("done");
        } else {
            textField.setText("");
        }

        textField.setMaxSize(100,100);
        textField.setAlignment(Pos.CENTER);
        plansza.add(textField, i, j);

        textField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Verify(board, GridPane.getRowIndex(textField),
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
            textField.getStyleClass().remove("done");
            textField.getStyleClass().add("custom");
        } else {
            textField.getStyleClass().remove("done");
            textField.getStyleClass().remove("custom");
            textField.getStyleClass().add("done");
        }
        return num;
    }

    private void patchyTextField(SudokuBoard board, int i, int j) {
        TextField textField = new TextField();
        Bindings.bindBidirectional(textField.textProperty(), board.getSudokuField(i,j).fieldProperty());

        textField.setAlignment(Pos.CENTER);
        textField.setMaxSize(100,100);
        textField.setEditable(false);
        plansza.add(textField, i, j);
    }

    private void initBoard(Level level) {
        plansza.setHgap(3);
        plansza.setVgap(3);
        sudokuBoardActual = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoardActual.solveGame();
        level.removeFields(sudokuBoardActual);
    }

    public void closeApp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void checkBoard(ActionEvent actionEvent) throws IOException {
        if (sudokuBoardActual.checkvalid()) {
            WinWindow w = new WinWindow();
            w.show();
        } else {
            LostWindow l = new LostWindow();
            l.show();
        }
    }

    public void loadBoard(MouseEvent mouseEvent) throws IOException {
        try {
            FileSudokuBoardDao<SudokuBoard> loader = new FileSudokuBoardDao<>("@../../saves/save1.txt");
            sudokuBoardActual = loader.read();
            putValues(sudokuBoardActual);
        } catch (Exception ignored) {
        }
    }

    public void saveBoard(MouseEvent mouseEvent) throws IOException, FileNotFoundException {
            FileSudokuBoardDao<SudokuBoard> saver = new FileSudokuBoardDao<>("@../../saves/save1.txt");
            saver.write(sudokuBoardActual);
            saver.setFileName("@../../saves/save2.txt");
            saver.write(sudokuBoardStart);
    }

    public void startConf(MouseEvent mouseEvent) {
        sudokuBoardActual = sudokuBoardStart.clone();
        putValues(sudokuBoardActual);
    }

    public void startOldConf(MouseEvent mouseEvent) {
        System.out.println("loadOldConf");
        try {
            FileSudokuBoardDao<SudokuBoard> loader = new FileSudokuBoardDao<>("@../../saves/save2.txt");
            sudokuBoardActual = loader.read();
            putValues(sudokuBoardActual);
        } catch (Exception ignored) {
        }
    }

    private void setNames(ResourceBundle bundle) {
        checker.setText(bundle.getString("check"));
        closeButton.setText(bundle.getString("exit"));
        Levell.setText(bundle.getString("level"));
    }

    private void decideLevel(ResourceBundle bundle) {
        int n = sudokuBoardActual.getNumberOfEditable();
        if(n == 55) {
            gameLevel.setText(bundle.getString("hard"));
        } else if (n == 50) {
            gameLevel.setText(bundle.getString("medium"));
        } else if(n == 40) {
            gameLevel.setText(bundle.getString("easy"));
        } else {
            gameLevel.setText(bundle.getString("veryeasy"));
        }
    }


}

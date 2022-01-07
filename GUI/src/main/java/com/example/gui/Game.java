package com.example.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import sudoku.BacktrackingSudokuSolver;
import sudoku.FieldVerify;
import sudoku.SudokuBoard;
import sudoku.factories.FileSudokuBoardDao;
import sudoku.level.Level;


public class Game implements Initializable {


    private static Stage stageBAD;
    private static Stage stageWin;
    private static Stage stageLost;
    private static Stage game;

    @FXML
    public Label gameLevel;
    public Button checker;
    public Pane loadButton;
    public Pane startConf;
    public Pane saveButton;
    public Pane startOldConf;
    public Label levell;

    @FXML
    private GridPane plansza;
    @FXML
    private Button closeButton;

    private static SudokuBoard sudokuBoardActual;
    private static SudokuBoard sudokuBoardStart;
    private ResourceBundle bundle;

    public void startGame(Level level, ResourceBundle bundle) throws NoSuchMethodException {
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

    private void putValues(SudokuBoard board) throws NoSuchMethodException {
        decideLevel(bundle);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();

                    JavaBeanIntegerPropertyBuilder builder =
                            JavaBeanIntegerPropertyBuilder.create();
                    JavaBeanIntegerProperty test =  builder.bean(board.getSudokuField(i,j))
                            .name("FieldValue").build();
                    StringConverter<Number> converter = new NumberStringConverter();

                textField.setTextFormatter(new TextFormatter<>(c -> {
                    if (c.isContentChange()) {
                        if (c.getControlNewText().length() == 0) {
                            return c;
                        }

                        try {
                            Integer.parseInt(c.getControlNewText());
                            if (Integer.parseInt(c.getControlNewText()) > 9
                                    || Integer.parseInt(c.getControlNewText()) == 0) {
                                return null;
                            }

                            return c;
                        } catch (NumberFormatException ignored) {
                            ignored.getMessage();
                        }
                        return null;
                    }
                    return c;
                }));
                    Bindings.bindBidirectional(textField.textProperty(), test, converter);

                if (board.getSudokuField(i, j).isEditable()) {
                    fullTextField(textField,board,i,j);
                } else {
                    patchyTextField(textField,i,j);
                }
            }
        }
    }

    private void fullTextField(TextField textField, SudokuBoard board, int i, int j)
            throws NoSuchMethodException {
        textField.getStyleClass().add("custom");

        if (board.getSudokuField(i,j).getFieldValue() != 0) {
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
                    fieldVerification(textField);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void patchyTextField(TextField textField, int i, int j)
            throws NoSuchMethodException {
        textField.setAlignment(Pos.CENTER);
        textField.setMaxSize(100,100);
        textField.setEditable(false);
        plansza.add(textField, i, j);
    }

    private int fieldVerification(TextField textField) throws IOException {
        close();
        FieldVerify fieldVerify = new FieldVerify();
        int num = fieldVerify.verifyTextField(textField.getText());
        if (num == -1) {
            textField.setText("");
            textField.getStyleClass().remove("done");
            textField.getStyleClass().add("custom");
        } else {
            textField.getStyleClass().remove("done");
            textField.getStyleClass().remove("custom");
            textField.getStyleClass().add("done");
        }
        return num;
    }

    private void initBoard(Level level) {
        plansza.setHgap(3);
        plansza.setVgap(3);
        sudokuBoardActual = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoardActual.solveGame();
        level.removeFields(sudokuBoardActual);
    }

    public void closeApp(ActionEvent actionEvent) throws IOException {
        close();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void checkBoard(ActionEvent actionEvent) throws IOException {
        close();
        if (sudokuBoardActual.checkvalid()) {
            winValue();
        } else {
            lostValue();
        }
    }

    public void loadBoard(MouseEvent mouseEvent) throws IOException {
        try {
            FileSudokuBoardDao<SudokuBoard> loader =
                    new FileSudokuBoardDao<>("@../../saves/save1.txt");
            sudokuBoardActual = loader.read();
            putValues(sudokuBoardActual);
        } catch (Exception ignored) {
            System.out.println("Read/write Error");
        }
    }

    public void saveBoard(MouseEvent mouseEvent) throws IOException, FileNotFoundException {
            FileSudokuBoardDao<SudokuBoard> saver =
                    new FileSudokuBoardDao<>("@../../saves/save1.txt");
            saver.write(sudokuBoardActual);
        FileSudokuBoardDao<SudokuBoard> saver2 =
                new FileSudokuBoardDao<>("@../../saves/save2.txt");
            saver2.write(sudokuBoardStart);
    }

    public void startConf(MouseEvent mouseEvent) throws NoSuchMethodException {
        sudokuBoardActual = sudokuBoardStart.clone();
        putValues(sudokuBoardActual);
    }

    public void startOldConf(MouseEvent mouseEvent) {
        try {
            FileSudokuBoardDao<SudokuBoard> loader =
                    new FileSudokuBoardDao<>("@../../saves/save2.txt");
            sudokuBoardActual = loader.read();
            putValues(sudokuBoardActual);
        } catch (Exception ignored) {
            System.out.println("Read/write Error");
        }
    }

    private void setNames(ResourceBundle bundle) {
        checker.setText(bundle.getString("check"));
        closeButton.setText(bundle.getString("exit"));
        levell.setText(bundle.getString("level"));
    }

    private void decideLevel(ResourceBundle bundle) {
        int n = sudokuBoardActual.getNumberOfEditable();
        if (n == 55) {
            gameLevel.setText(bundle.getString("hard"));
        } else if (n == 50) {
            gameLevel.setText(bundle.getString("medium"));
        } else if (n == 40) {
            gameLevel.setText(bundle.getString("easy"));
        } else {
            gameLevel.setText(bundle.getString("veryeasy"));
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    private void winValue() throws IOException {
        if (stageWin == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("WinStage.fxml"));
            Parent root = loader.load();
            WinWindow win = loader.getController();
            win.send(bundle);
            stageWin = new Stage();
            stageWin.setScene(new Scene(root));
            stageWin.setAlwaysOnTop(true);
            stageWin.setTitle("Win");
            stageWin.show();
            setDisable(true);
            stageWin.setOnHidden(we -> {
                initNewGame();
                stageWin = null;
                setDisable(false);
            });
        } else {
            stageWin.toFront();
        }

    }

    private void lostValue() throws IOException {
        if (stageLost == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LostStage.fxml"));
            Parent root = loader.load();
            LostWindow lostWindow = loader.getController();
            lostWindow.send(bundle);
            stageLost = new Stage();
            stageLost.setScene(new Scene(root));
            stageLost.setTitle("Last");
            stageLost.setAlwaysOnTop(true);
            stageLost.show();
            setDisable(true);
            stageLost.setOnHidden(we -> {
                initNewGame();
                stageLost = null;
                setDisable(false);
            });
        } else {
            stageLost.toFront();
        }

    }

    private void close() {
        if (stageWin != null) {
            stageWin.close();
        }
        if (stageBAD != null) {
            stageBAD.close();
        }
        if (stageLost != null) {
            stageLost.close();
        }
    }

    public void send(Stage menuStage) {
        game = menuStage;
    }

    private void initNewGame() {
        game.close();
        MainMenu m = new MainMenu();
        try {
            m.menuShow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDisable(boolean t) {
        checker.setDisable(t);
        loadButton.setDisable(t);
        saveButton.setDisable(t);
        startConf.setDisable(t);
        startOldConf.setDisable(t);
        plansza.setDisable(t);
    }

}

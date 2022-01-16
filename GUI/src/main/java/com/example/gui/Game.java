package com.example.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.BacktrackingSudokuSolver;
import sudoku.FieldVerify;
import sudoku.SudokuBoard;
import sudoku.level.Level;


public class Game implements Initializable {

    private static Stage saveGame;
    private static Stage loadGame;
    private static Stage stageWin;
    private static Stage stageLost;
    private static Stage game;

    @FXML
    public Label gameLevel;
    public Button checker;
    public Pane loadButton;
    public Pane saveButton;
    public Label levell;

    @FXML
    private GridPane plansza;
    @FXML
    private Button closeButton;

    private static SudokuBoard sudokuBoardActual;
    public static SudokuBoard sudokuBoardStart;
    private ResourceBundle bundle;
    public static final String databaseURL
            = "jdbc:derby:SudokuBase";
    private JavaBeanIntegerPropertyBuilder builder =
            JavaBeanIntegerPropertyBuilder.create();

    public void startGame(Level level, ResourceBundle bundle) throws NoSuchMethodException {
        initBoard(level);
        this.bundle = bundle;
        setNames(bundle);

        putValues(sudokuBoardActual);
        sudokuBoardStart = sudokuBoardActual.clone();
        Tooltip.install(loadButton, new Tooltip(bundle.getString("load")));
        Tooltip.install(saveButton, new Tooltip(bundle.getString("save")));
    }

    public void putValues(SudokuBoard board) throws NoSuchMethodException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        decideLevel(bundle);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();

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
                            logger.error("Bad Value: " + c.getControlNewText());
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

        textField.setMaxSize(1000,100);
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
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Aplication close");
    }

    public void checkBoard(ActionEvent actionEvent) throws IOException, NoSuchMethodException {
        close();
        if (sudokuBoardActual.checkvalid()) {
            winValue();
        } else {
            lostValue();
        }
    }

    public void loadBoard(MouseEvent mouseEvent) throws IOException, SQLException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Load saved sudoku");
        if (loadGame == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadScene.fxml"));
            Parent root = loader.load();
            LoadScene load = loader.getController();

            load.send(bundle, sudokuBoardActual, game, databaseURL, builder);
            loadGame = new Stage();
            loadGame.setScene(new Scene(root));
            loadGame.setAlwaysOnTop(true);
            loadGame.setTitle("Load");
            loadGame.show();
            setDisable(true);

            loadGame.setOnHidden(windowEvent -> {
                loadGame = null;
                setDisable(false);
            });

        } else {
            loadGame.toFront();
        }
    }

    public void saveBoard(MouseEvent mouseEvent) throws IOException, FileNotFoundException {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("saved sudoku");

        if (saveGame == null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SaveScene.fxml"));
            Parent root = loader.load();
            SaveScene save = loader.getController();

            save.send(bundle, sudokuBoardActual, databaseURL);
            saveGame = new Stage();
            saveGame.setScene(new Scene(root));
            saveGame.setAlwaysOnTop(true);
            saveGame.setTitle("save");
            saveGame.show();
            setDisable(true);

            saveGame.setOnHidden(windowEvent -> {
                saveGame = null;
                setDisable(false);
            });

        } else {
            saveGame.toFront();
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
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Player has solved sudoku correctly");
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
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Player has solved sudoku incorrectly");
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
        if (stageLost != null) {
            stageLost.close();
        }
    }

    public void send(Stage gameStage) throws NoSuchMethodException {
        game = gameStage;
    }

    public void sendB(Stage gameStage, SudokuBoard board, ResourceBundle bundle,
                      JavaBeanIntegerPropertyBuilder builder)
            throws NoSuchMethodException {
        this.game = gameStage;
        this.sudokuBoardActual = board.clone();
        this.bundle = bundle;
        this.builder = builder;
        putValues(sudokuBoardActual);
        setNames(bundle);
    }

    private void initNewGame() {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Initialize Game");
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
        plansza.setDisable(t);
    }


}

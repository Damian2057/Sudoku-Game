package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadScene implements Initializable {
    public ChoiceBox LevelLoader = new ChoiceBox();
    public Text LoadText = new Text();
    public Button applyLoad = new Button();
    public Button cancelLoad = new Button();
    private SudokuBoard board;
    private ResourceBundle bundle;
    private Stage stageGameCopy;

    public void applyLoad(ActionEvent actionEvent) throws IOException, NoSuchMethodException {
        reloadGame();
        Stage stage = (Stage) applyLoad.getScene().getWindow();
        stage.close();
    }


    public void cancelLoad(ActionEvent actionEvent) throws IOException, NoSuchMethodException {
        reloadGame();
        Stage stage = (Stage) cancelLoad.getScene().getWindow();
        stage.close();
    }

    public void send(ResourceBundle bundle, SudokuBoard board, Stage stage) {
        this.board = board;
        this.bundle = bundle;
        this.stageGameCopy = stage;
        setNames();
    }

    private void setNames() {
        applyLoad.setText(bundle.getString("apply"));
        cancelLoad.setText(bundle.getString("exit"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void reloadGame() throws IOException, NoSuchMethodException {
        stageGameCopy.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root,525,650));
        Game game = loader.getController();
        SudokuBoard board3 = new SudokuBoard(new BacktrackingSudokuSolver());
        board3.solveGame();
        board3.getSudokuField(0,0).setEditable(true);
        game.sendB(stage2, board3, bundle);
        stage2.setResizable(false);
        stage2.setTitle("SudokuMenu");
        stage2.show();

    }
}

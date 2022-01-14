package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sudoku.SudokuBoard;

import java.net.URL;
import java.util.ResourceBundle;

public class SaveScene implements Initializable {

    private SudokuBoard board;
    private ResourceBundle bundle;

    public TextField saveTextField = new TextField();
    public Button cancelButton = new Button();
    public Button saveSaveButton = new Button();

    public void onCancelBoard(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onSaveBoard(ActionEvent actionEvent) {
        Stage stage = (Stage) saveSaveButton.getScene().getWindow();
        stage.close();

    }

    public void send(ResourceBundle bundle, SudokuBoard board) {
        this.board = board;
        this.bundle = bundle;
        setNames();
    }

    private void setNames() {
        saveSaveButton.setText(bundle.getString("saveBoard"));
        cancelButton.setText(bundle.getString("cancelSave"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

package com.example.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudoku.factories.SudokuBoardDaoFactory;

public class SaveScene implements Initializable {

    private SudokuBoard board;
    private ResourceBundle bundle;

    public TextField saveTextField = new TextField();
    public Button cancelButton = new Button();
    public Button saveSaveButton = new Button();
    private String url;

    public void onCancelBoard(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void onSaveBoard(ActionEvent actionEvent) {
        if (saveTextField.getText() != ""  && saveTextField.getText() != " ") {
            org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

            try (var JDBCdao =
                         SudokuBoardDaoFactory.getJdbcDao(saveTextField.getText(), url)) {
                JDBCdao.write(board);
            } catch (Exception e) {
                logger.error("Something goes wrong during loading board", e);
            }


            Stage stage = (Stage) saveSaveButton.getScene().getWindow();
            stage.close();
        }
    }

    public void send(ResourceBundle bundle, SudokuBoard board, String url) {
        this.board = board;
        this.bundle = bundle;
        this.url = url;
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

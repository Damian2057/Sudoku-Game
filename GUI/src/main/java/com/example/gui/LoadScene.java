package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import sudoku.SudokuBoard;
import sudoku.factories.SudokuBoardDaoFactory;

public class LoadScene implements Initializable {
    public ChoiceBox LevelLoader = new ChoiceBox();
    public Text LoadText = new Text();
    public Button applyLoad = new Button();
    public Button cancelLoad = new Button();
    private SudokuBoard board;
    private ResourceBundle bundle;
    private Stage stageGameCopy;
    private String url;

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

    public void send(ResourceBundle bundle, SudokuBoard board, Stage stage, String URL) throws SQLException {
        this.board = board;
        this.bundle = bundle;
        this.stageGameCopy = stage;
        this.url = URL;
        setNames();
        loadBoards();
    }

    private void setNames() {
        applyLoad.setText(bundle.getString("apply"));
        cancelLoad.setText(bundle.getString("exit"));
        LoadText.setText(bundle.getString("loader"));
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
        game.sendB(stage2, board, bundle);
        stage2.setResizable(false);
        stage2.setTitle("SudokuMenu");
        stage2.show();
    }

    private void loadBoards() throws SQLException {
        var listOfAvailableBoards = SudokuBoardDaoFactory
                .getJdbcDao("loader",url).getAllBoardsInDataBase();

        for(int i = 0; i< listOfAvailableBoards.size(); i++) {
            LevelLoader.getItems().add(listOfAvailableBoards.get(i));
        }
        LevelLoader.setOnAction((event) -> {
            String selectedItem = LevelLoader.getSelectionModel().getSelectedItem().toString();
            try {
                var jdbcDao = SudokuBoardDaoFactory.getJdbcDao(selectedItem, url);
                board = jdbcDao.read();
            } catch (Exception e) {

            }
        });

    }
}

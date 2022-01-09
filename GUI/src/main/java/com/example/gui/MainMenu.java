package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.LoggerFactory;
import sudoku.level.Easy;
import sudoku.level.Hard;
import sudoku.level.Level;
import sudoku.level.Medium;
import sudoku.level.VeryEasy;

public class MainMenu implements Initializable {

    @FXML
    public Pane setPane;
    public Button startButton;
    public MenuItem veryEasy;
    public MenuItem menuEasy;
    public MenuItem menuMedium;
    public MenuItem menuHard;
    private Level level;
    private ResourceBundle bundle;

    @FXML
    public MenuButton levelBar;

    private static Stage gameStage;
    private static Stage menuStage;

    public void menuShow() throws IOException {

        menuStage = new Stage();
        menuStage.setResizable(false);
        menuStage.setTitle("SudokuMenu");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass()
                .getResource("MainMenu.fxml")));
        Scene scene = new Scene(root,500,530);
        menuStage.setScene(scene);
        menuStage.show();
    }

    public void gameStart(ActionEvent actionEvent) throws IOException, NoSuchMethodException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        if (level == null) {
            level = new VeryEasy();
        }
        Parent root = loader.load();
        gameStage = new Stage();
        gameStage.setScene(new Scene(root,525,650));
        Game game = loader.getController();
        nullBundleSecure();
        game.startGame(level, bundle);
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        menuStage.close();
        gameStage.setResizable(false);
        gameStage.setTitle("SudokuGame");
        game.send(gameStage);
        gameStage.show();
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Starting Game with Level set to " + level.toString());
    }

    private void nullBundleSecure() {
        if (bundle == null) {
            this.bundle = ResourceBundle.getBundle("bundle", new Locale("eng", "ENG"));
        }
    }

    public void veryEasy(ActionEvent actionEvent) throws IOException {
        level = new VeryEasy();
        nullBundleSecure();
        levelBar.setText(bundle.getString("veryeasy"));
    }

    public void easy(ActionEvent actionEvent) throws IOException {
        level = new Easy();
        nullBundleSecure();
        levelBar.setText(bundle.getString("easy"));
    }

    public void medium(ActionEvent actionEvent) throws IOException {
        level = new Medium();
        nullBundleSecure();
        levelBar.setText(bundle.getString("medium"));
    }

    public void hard(ActionEvent actionEvent) throws IOException {
        level = new Hard();
        nullBundleSecure();
        levelBar.setText(bundle.getString("hard"));
    }

    public void setSettings(MouseEvent mouseEvent) throws IOException {
        org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.debug("Load Options");
        Stage stage = (Stage) setPane.getScene().getWindow();
        stage.close();
        Settings s = new Settings();
        s.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send(ResourceBundle bundle) {
        this.bundle = bundle;
        setNames(bundle);
    }

    private void setNames(ResourceBundle bundle) {
        startButton.setText(bundle.getString("start"));
        levelBar.setText(bundle.getString("select"));
        veryEasy.setText(bundle.getString("veryeasy"));
        menuEasy.setText(bundle.getString("easy"));
        menuMedium.setText(bundle.getString("medium"));
        menuHard.setText(bundle.getString("hard"));
    }
}

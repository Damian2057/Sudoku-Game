package com.example.gui;

import com.example.gui.resorc.ResourcesEng;
import com.example.gui.resorc.ResourcesPl;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Settings implements Initializable  {

    @FXML
    public MenuButton langButton;
    public Button apply;
    public Text settingstext;
    public Text authors;
    public Text a1;
    public Text a2;
    public Button exit;

    private Locale locale;
    private ResourceBundle bundle;


    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }


    public void applySettings(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.setScene(new Scene(root));
        MainMenu menu = loader.getController();
        checkNull();
        menu.send(bundle);
        stage.setTitle("SudokuMenu");
        stage.show();


        if (locale == null) {
            locale = new Locale("eng", "ENG");
        }
        setNames(locale);
    }

    private void checkNull() {
        if (bundle == null) {
            locale = new Locale("eng", "ENG");
            bundle = ResourceBundle.getBundle("bundle", locale);
        }
    }

    private void setNames(Locale locale) {
        bundle = ResourceBundle.getBundle("bundle", locale);
        exit.setText(bundle.getString("exit"));
        apply.setText(bundle.getString("apply"));
        settingstext.setText(bundle.getString("settings"));

    }

    public void englishSet(ActionEvent actionEvent) {
        locale = new Locale("eng", "ENG");
        ResourcesEng list = new ResourcesEng();
        authors.textProperty().set((String) list.getContents()[0][1]);
        a1.textProperty().set((String) list.getContents()[1][1]);
        a2.textProperty().set((String) list.getContents()[2][1]);
        langButton.setText("English");
        setNames(locale);
    }

    public void polskiSet(ActionEvent actionEvent) {
        locale = new Locale("pl", "PL");
        ResourcesPl list = new ResourcesPl();
        authors.textProperty().set((String) list.getContents()[0][1]);
        a1.textProperty().set((String) list.getContents()[1][1]);
        a2.textProperty().set((String) list.getContents()[2][1]);
        langButton.setText("Polski");
        setNames(locale);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void exitAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("SudokuMenu");
        stage.show();
    }
}

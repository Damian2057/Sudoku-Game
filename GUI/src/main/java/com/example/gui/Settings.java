package com.example.gui;

import com.example.gui.resorc.ResourcesEng;
import com.example.gui.resorc.ResourcesPl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Settings {

    @FXML
    public MenuButton langButton;
    public Button cancel;
    public Button apply;
    public Text settingstext;
    public Text authors;
    public Text a1;
    public Text a2;

    private Locale locale;


    public Settings() {
    }

    public void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Settings.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }


    public void applySettings(ActionEvent actionEvent) {
        if (locale == null) {
            locale = new Locale("eng", "ENG");
        }
        setNames(locale);
    }

    private void setNames(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", locale);
        cancel.setText(bundle.getString("cancel"));
        apply.setText(bundle.getString("apply"));
        settingstext.setText(bundle.getString("settings"));

    }

    public void cancelSettings(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
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
}

package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinWindow implements Initializable {


    public Text text;

    private void setNames(ResourceBundle bundle) {
//          closeButton.setText(bundle.getString("ok"));
            text.setText(bundle.getString("win"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send(ResourceBundle bundle) {
        setNames(bundle);
    }
}

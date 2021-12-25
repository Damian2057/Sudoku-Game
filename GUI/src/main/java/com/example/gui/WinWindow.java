package com.example.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WinWindow implements Initializable {


    public Text text;
    public Button winBut;

    private void setNames(ResourceBundle bundle) {
            winBut.setText(bundle.getString("winb"));
            text.setText(bundle.getString("win"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send(ResourceBundle bundle) {
        setNames(bundle);
    }

    public void winAction(ActionEvent actionEvent) {
        Stage stage = (Stage) winBut.getScene().getWindow();
        stage.close();
    }
}

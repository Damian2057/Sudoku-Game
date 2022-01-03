package com.example.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BadValueWindow implements Initializable {

    @FXML
    public Button closeButton = new Button();
    public Text text = new Text();

    public void closeApp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    private void setNames(ResourceBundle bundle) {
        closeButton.setText(bundle.getString("ok"));
        text.setText(bundle.getString("badvalue"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send(ResourceBundle bundle) {
        setNames(bundle);
    }
}

package com.example.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LostWindow implements Initializable {

    public Button tryAgainButton;
    public Text tryAgainText;

    private void setNames(ResourceBundle bundle) {
        tryAgainText.setText(bundle.getString("losttext"));
        tryAgainButton.setText(bundle.getString("lostbut"));
        tryAgainButton.setAlignment(Pos.BASELINE_CENTER);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void send(ResourceBundle bundle) {
        setNames(bundle);
    }

    public void tryAgain(ActionEvent actionEvent) {
        Stage stage = (Stage) tryAgainButton.getScene().getWindow();
        stage.close();
    }
}

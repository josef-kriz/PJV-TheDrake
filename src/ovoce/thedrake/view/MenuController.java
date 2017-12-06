package ovoce.thedrake.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController implements Initializable {
    @FXML private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }

    public void showMultiplayer(MouseEvent event) {
        setLabel("Hra dvou hráčů");
    }

    public void showComputer(MouseEvent event) {
        setLabel("Hra proti počítači");
    }

    public void showInternet(MouseEvent event) {
        setLabel("Hra na internetu");
    }

    public void showExit(MouseEvent event) {
        setLabel("Konec");
    }

    public void hideLabel(MouseEvent event) {
        setLabel("");
    }

    private void setLabel(String text) {
        label.setText(text);
    }
}

package ovoce.thedrake.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class MenuController implements Initializable {
//    @FXML private Label label;
//    @FXML private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        label.setText("Ahoj");
//        listView.getItems().add("Petr");
//        listView.getItems().add("Pavel");
//        listView.getItems().add("Ivan");
//        listView.getItems().add("Jana");
//        listView.getItems().add("Eva");
//
//        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                label.setText(newValue);
//            }
//        });
    }

    public void exit(ActionEvent event) {
        System.exit(0);
    }
}

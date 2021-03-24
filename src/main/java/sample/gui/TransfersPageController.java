package sample.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TransfersPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView transferIcon;

    @FXML
    private ImageView creditCardIcon;

    @FXML
    private ImageView profileIcon;

    @FXML
    private ImageView logoutIcon;

    @FXML
    private TableView<?> transactionTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> accountColumn;

    @FXML
    private TableColumn<?, ?> ammountColumn;

    @FXML
    private TableColumn<?, ?> wOrDColumn;

    @FXML
    void onCardIconClicked(MouseEvent event) {

    }

    @FXML
    void onLogoutClicked(MouseEvent event) {

    }

    @FXML
    void onProfileClicked(MouseEvent event) {

    }

    @FXML
    void onTransferIconClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert transferIcon != null : "fx:id=\"transferIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert creditCardIcon != null : "fx:id=\"creditCardIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert profileIcon != null : "fx:id=\"profileIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert logoutIcon != null : "fx:id=\"logoutIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert transactionTable != null : "fx:id=\"transactionTable\" was not injected: check your FXML file 'transfers.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert accountColumn != null : "fx:id=\"accountColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert ammountColumn != null : "fx:id=\"ammountColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert wOrDColumn != null : "fx:id=\"wOrDColumn\" was not injected: check your FXML file 'transfers.fxml'.";

    }
}

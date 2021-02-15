/**
 * Sample Skeleton for 'loginpage.fxml' Controller Class
 */

package sample.gui;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;

public class LoginPageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="loginTextLabel"
    private Button loginTextLabel; // Value injected by FXMLLoader

    @FXML // fx:id="forgotPasswordLabel"
    private Text forgotPasswordLabel; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    /**
     *
     */
    private boolean registerPageOpened = false;


    /**
     * When the login button is hovered over by a mouse it will change to a different color
     * @param event
     */
    @FXML
    void onLoginHovered(MouseEvent event) {
        loginTextLabel.setStyle("-fx-background-color: #9d2929;");
    }

    public void onLoginHoveredExitied(MouseEvent mouseEvent) {
        loginTextLabel.setStyle("-fx-background-color: #212121;");
    }

    public void onRegisterHovered(MouseEvent mouseEvent) {
        registerButton.setStyle("-fx-background-color: #9d2929;");
    }

    public void onRegisterExitied(MouseEvent mouseEvent) {
        registerButton.setStyle("-fx-background-color: #212121;");
    }

    public void onRegisterButtonClick(ActionEvent actionEvent) {
        if (!registerPageOpened) {
            try {
               Main.setRoot("gui/registerpage", 700, 835, false, StageStyle.UTILITY, 0);
                registerPageOpened = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

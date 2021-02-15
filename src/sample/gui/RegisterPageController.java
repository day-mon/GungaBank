/**
 * Sample Skeleton for 'registerpage.fxml' Controller Class
 */

package sample.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterPageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="firstNameTextField"
    private TextField firstNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="lastNameTextField"
    private TextField lastNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="emailTextField"
    private TextField emailTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="dobTextField"
    private TextField dobTextField; // Value injected by FXMLLoader

    @FXML // fx:id="phoneNumberTextField"
    private TextField phoneNumberTextField; // Value injected by FXMLLoader

    @FXML // fx:id="ssnTextField"
    private TextField ssnTextField; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML
    void clearButtonClicked(ActionEvent event) {
        System.out.println("button click!");
    }

    @FXML
    void registerButtonClicked(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert firstNameTextField != null : "fx:id=\"firstNameTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert lastNameTextField != null : "fx:id=\"lastNameTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert dobTextField != null : "fx:id=\"dobTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert phoneNumberTextField != null : "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert ssnTextField != null : "fx:id=\"ssnTextField\" was not injected: check your FXML file 'registerpage.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'registerpage.fxml'.";

    }
}
// 700 x 835
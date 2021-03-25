/**
 * Sample Skeleton for 'loginpage.fxml' Controller Class
 */

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import sample.Main;
import sample.core.objects.BankAccount;
import sample.core.objects.User;
import sample.util.ArrayList;
import sample.util.operations.AlertOperations;
import sample.util.operations.StringOperations;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.ResourceBundle;

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

    /**
     *
     */
    private ArrayList<Alert> alerts =  new ArrayList<>();


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {

    }
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

            try
            {
               Main.open("/registerpage", 700, 835, StageStyle.UTILITY);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

    }


    public void onLoginClick(ActionEvent actionEvent)
    {

        String login = usernameTextField.getText();
        String password = StringOperations.hashPassword(passwordField.getText());

        if (login.length() < 0)
        {
            AlertOperations.AlertShortner("bad", "Missing text!", "Login field is empty!");
            return;
        }

        if (!Main.users.containsKey(login))
        {
            AlertOperations.AlertShortner("bad", "Incorrect login!", "Your password or email may be incorrect!");
            return;
        }

        User userToLogin = Main.users.get(login);

        if (!userToLogin.gethashedPass().equals(password) && userToLogin.getEmail().equals(login))
        {
            AlertOperations.AlertShortner("bad", "Incorrect login!", "Your password or email may be incorrect!");
            return;
        }


        try
        {
            Main.userLoggedIn = userToLogin;
            System.out.println("Main.user: " + Main.userLoggedIn.getFirstName());
            System.out.println("UserToLogin: " + userToLogin.getFirstName());

            if (userToLogin.getBankAccounts().size() <= 0)
            {
                userToLogin.getBankAccounts().add(new BankAccount(userToLogin, BankAccount.AccountTypes.CHECKING));
            }

            if (Main.forms.containsKey("/dashboard"))
                Main.forms.remove("/dashboard");

            Main.open("/dashboard", 1200, 800, StageStyle.UTILITY);
            String sucess = String.format("Welcome %s! \nYour last login was %s", userToLogin.getFirstName(),  userToLogin.getLastLogin() == null ?  "Never!" : userToLogin.getLastLogin());
            AlertOperations.AlertShortner("good", "Login success!",sucess);
        }
        catch (Exception e)
        {
            System.err.printf("Error occured: %s", e.getLocalizedMessage());
            e.printStackTrace();
        }



    }
}

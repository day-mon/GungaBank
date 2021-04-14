/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.GungaBank;
import sample.Main;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;
import sample.util.structures.ArrayList;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static sample.GungaBank.getStageHandler;

public class LoginPageController
{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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

    @GungaObject
    private ArrayList<Button> buttons;

    @GungaObject
    private OnButtonHovered hovered;

    @GungaObject
    private OnButtonExited exited;

    @GungaObject
    private GungaBank gungaBank;

    private Parent root;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        buttons = new ArrayList<>();
        buttons.add(registerButton);
        buttons.add(loginTextLabel);
        hovered = new OnButtonHovered(buttons);
        exited = new OnButtonExited(buttons);

    }

    public void onRegisterButtonClick(ActionEvent actionEvent)
    {

        try
        {
            Main.open("/register", "Register Page", 700, 835, StageStyle.UTILITY);
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
        Alert alert;

        if (login.length() == 0)
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Missing text!");
            alert.setContentText("Login field is empty!");
            alert.show();
            return;
        }

        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Incorrect login!");
        alert.setContentText("Your password or email may be incorrect");

        if (!Main.users.containsKey(login))
        {
            alert.show();
            return;
        }

        User userToLogin = Main.users.get(login);

        if (!userToLogin.gethashedPass().equals(password) && userToLogin.getEmail().equals(login))
        {
            alert.show();
            return;
        }

        root = preLoadData(userToLogin);
        getStageHandler().switchToStage("dashboard", root);
        String success = String.format("Welcome %s! \nYour last login was %s", userToLogin.getFirstName(), userToLogin.getLastLogin() == null ? "Never!" : userToLogin.getLastLogin());

        userToLogin.setLastLogin(new Date());

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Login success!");
        alert.setContentText(success);
        alert.show();
        FileOperations.writeToFile(FileOperations.users, Main.users);


    }

    private Parent preLoadData(User userToLogin)
    {
        try
        {
            FXMLLoader loader = getStageHandler().getLoader("/dashboard");
            root = loader.load();
            DashboardPageController controller = loader.getController();
            if (userToLogin.getBankAccounts().size() <= 0)
            {
                userToLogin.getBankAccounts().add(new BankAccount(userToLogin, BankAccount.AccountTypes.CHECKING));
            }
            controller.initData(userToLogin);
            return root;
        }
        catch (Exception e)
        {
            LOGGER.error("Error occurred: {}", e.getCause(), e);
        }
        return null;
    }
}
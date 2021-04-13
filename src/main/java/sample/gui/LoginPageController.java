/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import sample.GungaBank;
import sample.Main;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.util.operations.AlertOperations;
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dashboard.fxml"));
            root = loader.load();

            DashboardPageController controller = loader.getController();
            if (userToLogin.getBankAccounts().size() <= 0)
            {
                userToLogin.getBankAccounts().add(new BankAccount(userToLogin, BankAccount.AccountTypes.CHECKING));
            }
            controller.initData(userToLogin);


            if (Main.forms.containsKey("/dashboard"))
                Main.forms.remove("/dashboard");


            getStageHandler().switchToStage("dashboard");
            String success = String.format("Welcome %s! \nYour last login was %s", userToLogin.getFirstName(), userToLogin.getLastLogin() == null ? "Never!" : userToLogin.getLastLogin());

            userToLogin.setLastLogin(new Date());
            FileOperations.writeToFile(FileOperations.users, Main.users);

        }
        catch (Exception e)
        {
            System.err.printf("Error occurred: %s", e.getLocalizedMessage());
            e.printStackTrace();
        }


    }
}

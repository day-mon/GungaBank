package sample.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.operations.StringOperations;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

//import sample.actions.OnButtonClicked;

public class LoginPageController implements Controller
{

    @GungaObject
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="usernameTextField"
    public TextField usernameTextField; // Value injected by FXMLLoader

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
    private FileHandler fileHandler;

    @GungaObject
    private StageHandler stageHandler;


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
        LOGGER.info("Login scene loaded successfully");

    }

    public void onRegisterButtonClick()
    {
        stageHandler.switchToStage(null, "register");
    }


    public void onLoginClick()
    {
        HashDictionary<String, User> users = fileHandler.getUsers();
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

        if (!users.containsKey(login))
        {
            alert.show();
            return;
        }

        User userToLogin = users.get(login);


        if (!userToLogin.gethashedPass().equals(password) && userToLogin.getEmail().equals(login))
        {
            alert.show();
            return;
        }
        if (userToLogin.getBankAccounts().size() <= 0)
        {
            userToLogin.getBankAccounts().add(new BankAccount(userToLogin, BankAccount.AccountTypes.CHECKING));
        }

        //root = preLoadData(userToLogin);
        stageHandler.switchToStage(userToLogin, "dashboard");
        String success = String.format("Welcome %s! \nYour last login was %s", userToLogin.getFirstName(), userToLogin.getLastLogin() == null ? "Never!" : userToLogin.getLastLogin());

        userToLogin.setLastLogin(new Date());

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Login success!");
        alert.setContentText(success);
        alert.show();


    }

    private Parent preLoadData(User userToLogin)
    {
        try
        {
            FXMLLoader loader = stageHandler.getLoader("/dashboard");
            root = loader.load();
            DashboardPageController controller = loader.getController();
            if (userToLogin.getBankAccounts().size() <= 0)
            {
                userToLogin.getBankAccounts().add(new BankAccount(userToLogin, BankAccount.AccountTypes.CHECKING));
            }
            controller.initData(userToLogin, stageHandler, fileHandler);
            return root;
        }
        catch (Exception e)
        {
            LOGGER.error("Error occurred: {}", e.getCause(), e);
        }
        return null;
    }


    @Override
    public void initData(User user, StageHandler stageHandler, FileHandler fileHandler)
    {
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;
    }


    @Override
    public User getUser()
    {
        return null;
    }


    public TextField getUsernameTextField()
    {
        return usernameTextField;
    }
}
package sample.actions;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.User;
import sample.gui.DashboardPageController;
import sample.gui.RegisterPageController;
import sample.util.Checks;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.util.Iterator;
import java.util.Optional;

import static sample.GungaBank.getStageHandler;

public class OnButtonClicked implements EventHandler<ActionEvent>
{
    private ArrayList<Button> buttons;
    private Controller controller;

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public OnButtonClicked(ArrayList<Button> buttons, Controller controller)
    {
        this.controller = controller;
        for (Button button : buttons)
        {
            button.addEventHandler(ActionEvent.ACTION, this);
            this.buttons.add(button);
        }
    }


    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(ActionEvent event)
    {
        Button buttonClicked = (Button) event.getSource();
        String buttonClickedName = buttonClicked.getId();
        String controllerName = controller.getClass().getName();
        switch (controllerName)
        {
            case "RegisterPageController":
                RegisterPageController c = (RegisterPageController) controller;
                switch (buttonClickedName)
                {
                    case "registerButton":
                        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();
                        int currentErrors = 0;
                        for (TextField textField : c.getTextFields())
                        {
                            switch (textField.getId())
                            {
                                case "firstNameTextField":
                                    if (textField.equals(""))
                                    {
                                        errorReasons.put(currentErrors++, "Your name field is empty!");

                                    }
                                    else
                                    {
                                        if (textField.getText().length() < 2)
                                        {
                                            errorReasons.put(currentErrors++, "Your name is less then 2 characters");
                                        }

                                        if (textField.getText().length() >= 35)
                                        {
                                            errorReasons.put(currentErrors++, "Your name is too long? \n(UK Government data suggest that this is a unreasonable length for a first name) \n Source: https://webarchive.nationalarchives.gov.uk/20100407173424/http://www.cabinetoffice.gov.uk/govtalk/schemasstandards/e-gif/datastandards.aspx");

                                        }

                                        if (!textField.getText().chars().allMatch(Character::isLetter))
                                        {
                                            errorReasons.put(currentErrors++, "Your first name field contains non-alphabetic characters");

                                        }
                                        continue;
                                        case "lastNameTextField":
                                            if (textField.equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your last name field is empty!");
                                            }
                                            else if (textField.getText().length() < 2)
                                            {
                                                errorReasons.put(currentErrors++, "Your last name field is less than 2 characters");
                                            }

                                            if (textField.getText().length() >= 45)
                                            {
                                                errorReasons.put(currentErrors++, "Your last name is too long!");
                                            }
                                            else if (textField.getText().chars().anyMatch(Character::isDigit))
                                            {
                                                errorReasons.put(currentErrors++, "Your last name contains numbers! ");
                                            }
                                            continue;
                                        case "emailTextField":
                                            if (textField.getText().equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your email field is empty!");
                                            }
                                            else if (!Checks.emailVaildaotr(textField.getText()))
                                            {
                                                errorReasons.put(currentErrors++, "Your email field is wrong!");
                                            }

                                            if (Checks.emailVaildaotr(textField.getText()))
                                            {
                                                Alert s = new Alert(Alert.AlertType.WARNING, "User found\nWould you like to goto the login page",
                                                        new ButtonType("Back"),
                                                        new ButtonType("Continue"));

                                                Optional<ButtonType> a = s.showAndWait();

                                                if (a.get().getText().equals("Back"))
                                                {
                                                    getStageHandler().switchToStage("login");
                                                    return;
                                                }
                                                return;
                                            }

                                            continue;
                                        case "passwordField":
                                            if (textField.getText().equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your Password field is empty!");
                                            }
                                            else if (!Checks.paswordValidator(textField.getText()))
                                            {
                                                errorReasons.put(currentErrors++, "Password field must be contain a digit (4-20), a number, special char, upper and lower case letter at least once!");
                                            }
                                            continue;
                                        case "dobTextField":
                                            if (textField.getText().equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your Date field is empty!");
                                            }
                                            else if (!Checks.dateValidator(textField.getText()))
                                            {
                                                errorReasons.put(currentErrors++, "Date is invalid must be (mm/dd/yyyy)!");
                                            }

                                            continue;
                                        case "phoneNumberTextField":
                                            if (textField.getText().equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your number field is empty!");
                                            }
                                            else if (!Checks.phoneValidator(textField.getText()))
                                            {
                                                errorReasons.put(currentErrors++, "Phone number is wrong!");
                                            }
                                            continue;
                                        case "ssnTextField":
                                            if (textField.getText().equals(""))
                                            {
                                                errorReasons.put(currentErrors++, "Your SSN field is empty!");
                                            }
                                            else if (textField.getText().length() != 9 || textField.getText().contains("[a-zA-Z]+"))
                                            {
                                                errorReasons.put(currentErrors++, "Not a valid SSN!");
                                            }
                                            break;
                                    }
                                    evalulateFields(errorReasons);

                            }
                            break;
                            case "clearButton":
                                clearTextFields(c.getTextFields());
                                break;
                            case "backButton":
                                getStageHandler().switchToStage("/dashboard");
                                break;
                        }
                        break;
                    case "LoginPageController":

                        break;
                }
        }

        private Parent preLoadData (User userToLogin)
        {
            try
            {
                FXMLLoader loader = getStageHandler().getLoader("/dashboard");
                Parent root = loader.load();
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
        private void clearTextFields (ArrayList < TextField > fields)
        {
            fields.forEach(t -> t.setText(""));
        }

        private void evalulateFields (HashDictionary < Integer, String > errorReasons)
        {
            //Creates map of error messages to throw when a field is wrong
            Iterator<Integer> keys = errorReasons.keys();
            Alert alert = new Alert(Alert.AlertType.WARNING);


            if (keys.hasNext())
            {


                StringBuilder errors = new StringBuilder();
                int size = 0;


                while (keys.hasNext())
                {
                    int element = keys.next();
                    /*
                      Could use a stringbuilder but meh.
                     */
                    errors.append(errorReasons.get(element)).append("\n");
                    size++;
                }

                alert.setHeaderText("You have " + size + " errors!");
                alert.setContentText(errors.toString());
                alert.show();
                return;
            }

            alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "You have successfully registered \nSending you back to the login page");
        }
    }

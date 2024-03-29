package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.Checks;
import sample.util.operations.StringOperations;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Optional;

//mport sample.actions.OnButtonClicked;


public class RegisterPageController implements Controller
{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField dobTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField ssnTextField;

    @FXML
    private Button clearButton;

    @FXML
    private Button registerButton;

    @FXML
    private Button backButton;

    @FXML
    private ArrayList<TextField> textFields;

    @FXML
    private ArrayList<Button> buttons;

    @GungaObject
    private OnButtonHovered hoverEvent;

    @GungaObject
    private OnButtonExited exitEvent;

    @GungaObject
    private StageHandler stageHandler;

    @GungaObject
    private FileHandler fileHandler;


    public TextField getDobTextField() {
        return dobTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public TextField getFirstNameTextField()
    {
        return firstNameTextField;
    }

    public TextField getLastNameTextField()
    {
        return lastNameTextField;
    }

    public TextField getPhoneNumberTextField()
    {
        return phoneNumberTextField;
    }

    public TextField getSsnTextField()
    {
        return ssnTextField;
    }

    public ArrayList<Button> getButtons()
    {
        return buttons;
    }

    public ArrayList<TextField> getTextFields()
    {
        return textFields;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        textFields = new ArrayList<>();
        buttons = new ArrayList<>();
        textFields.add(firstNameTextField);
        textFields.add(lastNameTextField);
        textFields.add(emailTextField);
        textFields.add(passwordField);
        textFields.add(dobTextField);
        textFields.add(phoneNumberTextField);
        textFields.add(ssnTextField);
        buttons.add(clearButton);
        buttons.add(backButton);
        buttons.add(registerButton);
        hoverEvent = new OnButtonHovered(buttons);
        exitEvent = new OnButtonExited(buttons);

        LOGGER.info("Register scene successfully loaded!");


    }


    @FXML
    void clearButtonClicked(ActionEvent event)
    {
        clearTextFields();
    }

    @FXML
    void registerButtonClicked(ActionEvent event) throws IOException, ParseException
    {
        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();
        int currentErrors = 0;
        for (int i = 0; i < textFields.size(); i++)
        {
            // Jesus this is horrible :(
            TextField currentField;


            switch (i)
            {

                // error checks First name field
                case 0:
                    currentField = textFields.get(i);
                    // Names could be 2 chars, usually asian names tho (Ki, Li, etc)
                    if (currentField.equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your name field is empty!");
                    }
                    else if (currentField.getText().length() < 2)
                    {
                        errorReasons.put(currentErrors++, "Your name is less then 2 characters");
                    }
                    if (currentField.getText().length() >= 35)
                    {
                        errorReasons.put(currentErrors++, "Your name is too long? \n(UK Government data suggest that this is a unreasonable length for a first name) \n Source: https://webarchive.nationalarchives.gov.uk/20100407173424/http://www.cabinetoffice.gov.uk/govtalk/schemasstandards/e-gif/datastandards.aspx");
                    }
                    /*
                      This checks if everything in the text file is a letter
                     */
                    if (!textFields.get(i).getText().chars().allMatch(Character::isLetter))
                    {
                        errorReasons.put(currentErrors++, "Your first name field contains non-alphabetic characters");
                    }
                    continue;

                    // error checks Last name field
                case 1:
                    currentField = textFields.get(i);


                    if (currentField.equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your last name field is empty!");
                    }
                    else if (currentField.getText().length() < 2)
                    {
                        errorReasons.put(currentErrors++, "Your last name field is less than 2 characters");
                    }

                    if (currentField.getText().length() >= 45)
                    {
                        errorReasons.put(currentErrors++, "Your last name is too long!");
                    }
                    else if (currentField.getText().chars().anyMatch(Character::isDigit))
                    {
                        errorReasons.put(currentErrors++, "Your last name contains numbers! ");
                    }
                    continue;

                    // error checks email field
                case 2:
                    currentField = textFields.get(i);
                    /*
                      For emails we will do a regex pattern checker
                     */
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your email field is empty!");
                    }
                    else if (!Checks.emailValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Your email field is wrong!");
                    }

                    if (checkEmail(currentField.getText()))
                    {
                        Alert s = new Alert(Alert.AlertType.WARNING, "User found\nWould you like to goto the login page",
                                new ButtonType("Back"),
                                new ButtonType("Continue"));

                        Optional<ButtonType> a = s.showAndWait();

                        if (a.get().getText().equals("Back"))
                        {
                            stageHandler.switchToStage("login");
                            return;
                        }
                        return;
                    }

                    continue;


                    // error checks Password field
                case 3:
                    currentField = textFields.get(i);

                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your Password field is empty!");
                    }
                    else if (!Checks.passwordValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Password field must be contain a digit (4-20), a number, special char, upper and lower case letter at least once!");
                    }

                    continue;

                    // error checks Date field
                case 4:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your Date field is empty!");
                    }
                    else if (!Checks.dateValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Date is invalid must be (mm/dd/yyyy)!");
                    }


                    continue;

                    // error checks Number field
                case 5:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your number field is empty!");
                    }
                    else if (!Checks.phoneValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Phone number is wrong!");
                    }
                    continue;

                    // error checks SSN field
                case 6:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your SSN field is empty!");
                    }
                    else if (currentField.getText().length() != 9 || currentField.getText().contains("[a-zA-Z]+"))
                    {
                        errorReasons.put(currentErrors++, "Not a valid SSN!");
                    }
                    break;


            }

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

                    errors.append(errorReasons.get(element)).append("\n");
                    size++;
                }

                alert.setHeaderText("You have " + size + " errors!");
                alert.setContentText(errors.toString());
                alert.show();
                return;
            }

            fileHandler.putUser(
                    new User(
                            firstNameTextField.getText(),
                            lastNameTextField.getText(),
                            emailTextField.getText(),
                            LocalDate.parse(dobTextField.getText(), DateTimeFormatter.ofPattern("MM/dd/yyyy").withLocale(Locale.ENGLISH)).atStartOfDay(),
                            phoneNumberTextField.getText(),
                            ssnTextField.getText(),
                            StringOperations.hashPassword(passwordField.getText())
                    )
            );

            clearTextFields();

            stageHandler.switchToStage("login");
        }
    }


    @FXML
    void backButtonPressed(ActionEvent event)
    {
        try
        {
            stageHandler.switchToStage("login");
            clearTextFields();
        }
        catch (Exception e)
        {
            LOGGER.error("Error occurred: {}", e.getCause(), e);
        }
    }









    private void clearTextFields()
    {
        textFields.forEach(textField -> textField.setText(""));
    }

    private boolean checkEmail(String email)
    {
        return fileHandler.getUsers().containsKey(email);
    }


    @Override
    public void initData(User user, StageHandler stageHandler, FileHandler fileHandler) {
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;

    }

    @Override
    public User getUser()
    {
        return null;
    }
}

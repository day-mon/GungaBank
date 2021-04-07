/**
 * Sample Skeleton for 'register.fxml' Controller Class
 */
package sample.gui;

import actions.onButtonHovered;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.GungaBankConstants;
import sample.Main;
import sample.core.objects.User;
import sample.core.other.GungaObject;
import sample.util.Checks;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

public class RegisterPageController
{

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
    private onButtonHovered hoverEvent;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize()
    {
        textFields = new ArrayList<>();
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
        hoverEvent = new onButtonHovered(buttons);
    }


    @FXML
    void clearButtonClicked(ActionEvent event)
    {
        clearTextFields();
    }

    @FXML
    void registerButtonClicked(ActionEvent event) throws IOException, InterruptedException, ParseException
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
                    else if (!Checks.emailVaildaotr(currentField.getText()))
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
                            Main.open("/loginpage");
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
                    else if (!Checks.paswordValidator(currentField.getText()))
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


            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");
            Date date = format.parse(dobTextField.getText());


            Main.users.put(emailTextField.getText(),
                    new User(
                            firstNameTextField.getText(),
                            lastNameTextField.getText(),
                            emailTextField.getText(),
                            date,
                            phoneNumberTextField.getText(),
                            ssnTextField.getText(),
                            StringOperations.hashPassword(passwordField.getText())
                    ));


            FileOperations.writeToFile(FileOperations.users, Main.users);

            clearTextFields();

            Main.open("/loginpage");
        }
    }


    @FXML
    void backButtonPressed(ActionEvent event)
    {
        try
        {
            Main.open("/loginpage");
            clearTextFields();
        }
        catch (Exception e)
        {
            System.err.printf("Error occured: %s ", e.getLocalizedMessage());
        }
    }

    @FXML
    void onBackExited(MouseEvent event)
    {
        backButton.setStyle("-fx-background-color: #313131;");
    }

    @FXML
    void onBackHovered(MouseEvent event)
    {
        backButton.setStyle(GungaBankConstants.BUTTON_HOVER_COLOR_STYLE);
    }

    public void onRegisterExited(MouseEvent mouseEvent)
    {
        registerButton.setStyle("-fx-background-color: #313131;");
    }

    public void onRegisterHovered(MouseEvent mouseEvent)
    {
        registerButton.setStyle(GungaBankConstants.BUTTON_HOVER_COLOR_STYLE);
    }

    public void onClearButtonExited(MouseEvent mouseEvent)
    {
        clearButton.setStyle("-fx-background-color: #313131;");
    }


    public void onClearButtonHovered(MouseEvent mouseEvent)
    {
        clearButton.setOnAction(onButtonHovered);
        // doesnt work
    }



    private void clearTextFields()
    {
        textFields.forEach(textField -> textField.setText(""));
    }

    private boolean checkEmail(String email) throws IOException
    {
        return Main.users.containsKey(email);
    }


}

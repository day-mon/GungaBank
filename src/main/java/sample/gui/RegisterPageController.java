/**
 * Sample Skeleton for 'registerpage.fxml' Controller Class
 */
package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import sample.Main;
import sample.core.objects.User;
import sample.util.ArrayList;
import sample.util.ArraySet;
import sample.util.HashDictionary;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPageController {

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

    // TODO: Something wrong with our arraylist?? we need to fix epic poggers :O
    private ArrayList<TextField> textFields;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        textFields = new ArrayList<TextField>();
        textFields.add(firstNameTextField);
        textFields.add(lastNameTextField);
        textFields.add(emailTextField);
        textFields.add(passwordField) ;
        textFields.add(dobTextField);
        textFields.add(phoneNumberTextField);
        textFields.add(ssnTextField);
    }


    @FXML
    void clearButtonClicked(ActionEvent event) {

        for (TextField fieldsToClear : textFields) {
            if (!fieldsToClear.getText().equals("")) {
                fieldsToClear.setText("");
            }
        }

    }

    @FXML
    void registerButtonClicked(ActionEvent event) throws IOException, InterruptedException, ParseException {
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
                    if (currentField.equals("")) {
                        errorReasons.put(currentErrors++, "Your name field is empty!");
                    } else if (currentField.getText().length() < 2) {
                        errorReasons.put(currentErrors++, "Your name is less then 2 characters");
                    }
                    if (currentField.getText().length() >= 35) {
                        errorReasons.put(currentErrors++, "Your name is too long? \n(UK Government data suggest that this is a unreasonable length for a first name) \n Source: https://webarchive.nationalarchives.gov.uk/20100407173424/http://www.cabinetoffice.gov.uk/govtalk/schemasstandards/e-gif/datastandards.aspx");
                    }
                    /**
                     * This checks if everything in the text file is a letter
                     */
                    if (!textFields.get(i).getText().chars().allMatch(Character::isLetter)) {
                        errorReasons.put(currentErrors++, "Your first name field contains non-alphabetic characters");
                    }
                    continue;

                    // error checks Last name field
                case 1:
                    currentField = textFields.get(i);


                    if (currentField.equals("")) {
                        errorReasons.put(currentErrors++, "Your last name field is empty!");
                    } else if (currentField.getText().length() < 2) {
                        errorReasons.put(currentErrors++, "Your last name field is less than 2 characters");
                    }

                    if (currentField.getText().length() >= 45) {
                        errorReasons.put(currentErrors++, "Your last name is too long!");
                    } else if (currentField.getText().chars().anyMatch(Character::isDigit)) {
                        errorReasons.put(currentErrors++, "Your last name contains numbers! ");
                    }
                    continue;

                    // error checks email field
                case 2:
                    currentField = textFields.get(i);
                    /**
                     * For emails we will do a regex pattern checker
                     */
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your email field is empty!");
                    }
                    else if (!emailVaildaotr(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Your email field is wrong!");
                    }

                    if (checkEmail(currentField.getText()))
                    {
                        Alert s =  new Alert(Alert.AlertType.WARNING, "User found\nWould you like to goto the login page",
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
                    else if(!paswordValidator(currentField.getText()))
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
                    else if(!DateValidator(currentField.getText()))
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
                    else if(!phoneValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Phone number is wrong!");
                    }
                    continue;

                // error checks SSN field
                case 6:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals("")) {
                        errorReasons.put(currentErrors++, "Your SSN field is empty!");
                    } else if(currentField.getText().length() != 9 || currentField.getText().contains("[a-zA-Z]+") ){
                        errorReasons.put(currentErrors++, "Not a valid SSN!");
                    }
                     break;


            }

                    //Creates map of error messages to throw when a field is wrong
                   ArraySet<Integer> keySet = errorReasons.keySet();
                   Alert alert = new Alert(Alert.AlertType.WARNING);

                    if (keySet.size() > 0)
                    {

                        int errorAmount = keySet.size();
                        alert.setHeaderText("You have " + errorAmount + " errors!");

                        String errors = "";

                        Iterator<Integer> s = keySet.iterator();

                        while (s.hasNext())
                        {
                            /**
                             * Could use a stringbuilder but meh.
                             */
                            errors += (errorReasons.get(s.next()) + "\n");
                        }


                        alert.setContentText(errors);
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

                        for (TextField tf : textFields)
                        {
                            tf.clear();
                        }

                        Main.open("/loginpage");
                    }
            }





    @FXML
    void backButtonPressed(ActionEvent event)
    {
        try
        {
            Main.open("/loginpage");
        }
        catch (Exception e)
        {
            System.err.printf("Error occured: %s ", e.getLocalizedMessage());
        }
    }

    @FXML
    void onBackExitied(MouseEvent event)
    {
        backButton.setStyle("-fx-background-color: #313131;");
    }

    @FXML
    void onBackHovered(MouseEvent event)
    {
        backButton.setStyle("-fx-background-color: #9d2929;");
    }

    public void onRegisterExitied (MouseEvent mouseEvent){
        registerButton.setStyle("-fx-background-color: #313131;");
    }

    public void onRegisterHovered (MouseEvent mouseEvent){
        registerButton.setStyle("-fx-background-color: #9d2929;");
    }

    public void onClearButtonExitied (MouseEvent mouseEvent){
        clearButton.setStyle("-fx-background-color: #313131;");
    }

    public void onClearButtonHovered (MouseEvent mouseEvent){
        clearButton.setStyle("-fx-background-color: #9d2929;");
    }

    //all Validate Regex checks for field entries
    private boolean emailVaildaotr (String email){
        // Regex pattern for emails
        final String EMAIL_REGEX = "^(.+)@(.+)$";
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        Matcher match = pat.matcher(email);
        return match.matches();
    }

    private boolean paswordValidator(String pass) {
        final String PASS_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,20}$";
        Pattern pt = Pattern.compile(PASS_REGEX);
        Matcher match = pt.matcher(pass);
        return match.matches();
    }
    private boolean DateValidator(String date) {
        final String DATE_REGEX = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pt = Pattern.compile(DATE_REGEX);
        Matcher match = pt.matcher(date);
        return match.matches();
    }
    private boolean phoneValidator(String phone) {
        final String PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pt = Pattern.compile(PHONE_REGEX);
        Matcher match = pt.matcher(phone);
        return match.matches();
    }
    private boolean checkEmail(String email) throws IOException {
       return Main.users.containsKey(email);
    }

}

// 700 x 835
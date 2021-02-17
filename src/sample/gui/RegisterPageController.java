/**
 * Sample Skeleton for 'registerpage.fxml' Controller Class
 */

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.core.objects.User;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPageController {

    public PasswordField passwordField;
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

    // TODO: Something wrong with our arraylist?? we need to fix epic poggers :O
    private java.util.ArrayList<TextField> textFields;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        textFields = new java.util.ArrayList<TextField>();
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        java.util.HashMap<Integer, String> errorReasons = new java.util.HashMap<>();
        Date date = null;
        int currentErrors = 0;
        for (int i = 0; i < textFields.size(); i++) {
            // Jesus this is horrible :(
            TextField currentField;
            switch (i) {

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
                    if (currentField.getText().equals("")) {
                        errorReasons.put(currentErrors++, "Your email field is empty!");
                    } else if(!emailVaildaotr(currentField.getText())){
                            errorReasons.put(currentErrors++, "Your email field is wrong!");
                    }
                    continue;

                // error checks Password field
                case 3:
                    currentField = textFields.get(i);

                    if (currentField.getText().equals("")) {
                        errorReasons.put(currentErrors++, "Your Password field is empty!");
                    } else if(!paswordValidator(currentField.getText())){
                     errorReasons.put(currentErrors++, "Password field must be contain a digit (4-20), a number, special char, upper and lower case letter at least once!");

                     }

                    continue;

                // error checks Date field
                case 4:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals("")) {
                        errorReasons.put(currentErrors++, "Your Date field is empty!");
                    } else if(!DateValidator(currentField.getText())){
                        errorReasons.put(currentErrors++, "Date is invalid must be (mm/dd/yyyy)!");
                    }


                    continue;

                // error checks Number field
                case 5:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals("")) {
                        errorReasons.put(currentErrors++, "Your number field is empty!");
                    } else if(!phoneValidator(currentField.getText())){
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
                  Set<Integer> keySet = errorReasons.keySet();
                    if (keySet.size() > 0) {
                        System.out.println(keySet.size());
                        int errorAmount = keySet.size();
                        alert.setHeaderText("You have " + errorAmount + " errors!");
                        StringBuilder errors = new StringBuilder("");
                        for (int erroReasonsInts : keySet) {
                            errors.append(errorReasons.get(erroReasonsInts) + "\n");
                        }

                        alert.setContentText(errors.toString());
                        alert.show();
                        return;
                    } else {
                        alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("You have successfully registered \n Sending you back to the login page");
                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyy");
                        date = format.parse(dobTextField.getText());



                        Main.users.put(emailTextField.getText().toString(),
                                new User(firstNameTextField.getText(), lastNameTextField.getText(), emailTextField.getText(), date, phoneNumberTextField.getText(), ssnTextField.getText(), StringOperations.hashPassword(passwordField.getText())));


                        FileOperations.writeToFile(FileOperations.users, Main.users);

                        // CREATES users
                        Main.setRoot("gui/loginpage");
                    }
            }
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


}

// 700 x 835
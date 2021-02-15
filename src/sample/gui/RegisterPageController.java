/**
 * Sample Skeleton for 'registerpage.fxml' Controller Class
 */

package sample.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Main;
import sample.util.ArrayList;

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

    // TODO: Something wrong with our arraylist?? we need to fix epic poggers :O
    private  java.util.ArrayList<TextField> textFields;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        textFields = new java.util.ArrayList<TextField>();
        textFields.add(firstNameTextField);
        textFields.add(lastNameTextField);
        textFields.add(emailTextField);
        textFields.add(passwordTextField);
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
    void registerButtonClicked(ActionEvent event) throws IOException, InterruptedException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        java.util.HashMap<Integer, String> errorReasons = new java.util.HashMap<>();
        for (int i = 0; i < textFields.size(); i++) {
            // Jesus this is horrible :(
            TextField currentField;
            switch (i) {

                // TODO: fix some stuff man these else if statements
              case 0:
                  currentField = textFields.get(i);
                  // Names could be 2 chars, usually asian names tho (Ki, Li, etc)
                  if (currentField.equals("")) {
                        errorReasons.put(0, "Your name field is empty!");
                  } else if (currentField.getText().length() < 2) {
                      errorReasons.put(1, "Your name is less then 2 characters");
                  }
                  if (currentField.getText().length() >= 35) {
                      errorReasons.put(2, "Your name is too long? \n(UK Government data suggest that this is a unreasonable length for a first name) \n Source: https://webarchive.nationalarchives.gov.uk/20100407173424/http://www.cabinetoffice.gov.uk/govtalk/schemasstandards/e-gif/datastandards.aspx");
                  }
                  /**
                   * This checks if everything in the text file is a letter
                   */
                  if (!textFields.get(i).getText().chars().allMatch(Character::isLetter)) {
                      errorReasons.put(3, "Your first name field contains non-alphabetic characters");
                  }
                  break;
              case 1:
                  currentField = textFields.get(i);

                  if (currentField.equals("")) {
                      errorReasons.put(4, "Your last name field is empty!");
                  } else if (currentField.getText().length() < 2) {
                      errorReasons.put(5, "Your last name field is less than 2 characters");
                  }

                  if (currentField.getText().length() >= 45) {
                      errorReasons.put(6, "Your last name is too long!");
                  }

                  if (currentField.getText().chars().anyMatch(Character::isDigit)) {
                      errorReasons.put(7, "Your last name contains numbers! ");
                  }
            break;
                case 2:
                    currentField = textFields.get(i);
                    /**
                     * For emails we will do a regex pattern checker
                     */
                    if (currentField.getText().equals("")) {
                        errorReasons.put(8, "Your email field is empty!");
                    } else if (currentField.getText().length() < 16) {
                        /**
                         * https://www.freshaddress.com/blog/long-email-addresses/
                         * Says 21, but mine is 16. So I will go with that basis.
                         */
                        errorReasons.put(9, "Your email is too short!");
                    }

                    if (currentField.getText().length() > 49) {
                        errorReasons.put(10, "Your email is too long!");
                    }


          }

            Set<Integer> keySet = errorReasons.keySet();

          if (keySet.size() > 0) {
              System.out.println(keySet.size());
              int errorAmount = keySet.size();
              alert.setHeaderText("You have " + errorAmount + " errors!");
              for (int erroReasonsInts : keySet) {
                  alert.setContentText(errorReasons.get(erroReasonsInts) + "\n");

              }
              alert.show();
              return;
          } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("You have successfully registered \n Sending you back to the login page");


            // CREATES users
            Main.setRoot("gui/loginpage");
          }
        }
    }



    public void onRegisterExitied(MouseEvent mouseEvent) {
        registerButton.setStyle("-fx-background-color: #313131;");
    }

    public void onRegisterHovered(MouseEvent mouseEvent) {
        registerButton.setStyle("-fx-background-color: #9d2929;");
    }

    public void onClearButtonExitied(MouseEvent mouseEvent) {
        clearButton.setStyle("-fx-background-color: #313131;");
    }

    public void onClearButtonHovered(MouseEvent mouseEvent) {
        clearButton.setStyle("-fx-background-color: #9d2929;");
    }

    private boolean emailVaildaotr(String email) {
        // Regex pattern for emails
        final String EMAIL_REGEX = "^[a-zA-Z][a-zA-Z0-9._-]*\\\\@\\\\w+(\\\\.)*\\\\w+\\\\.\\\\w+$";
        Pattern pat = Pattern.compile(EMAIL_REGEX);
        Matcher match = pat.matcher(email);

        return match.matches();
    }


}
// 700 x 835
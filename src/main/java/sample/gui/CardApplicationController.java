package sample.gui;

import java.lang.reflect.Array;
import java.net.URL;

import javafx.scene.control.Alert;
import sample.Main;
import sample.util.Checks;
import sample.util.structures.ArrayList;

import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.util.structures.HashDictionary;

public class CardApplicationController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ANNUAL_INCOME;

    @FXML
    private TextField TOTAL_ASSETS;

    @FXML
    private TextField PHONE_NUMBER;

    @FXML
    private Button clearButton;

    @FXML
    private Button APPLY_BUTTON;

    private ArrayList<TextField> textFields;

    @FXML
    void initialize()
    {
        textFields = new ArrayList<TextField>();
        textFields.add(ANNUAL_INCOME);
        textFields.add(TOTAL_ASSETS);
        textFields.add(PHONE_NUMBER);
    }

    @FXML
    void clearButtonClicked(ActionEvent event)
    {
        textFields.forEach(textField -> textField.setText(""));
    }

    @FXML
    void onApplyClicked(ActionEvent event)
    {
        int currentErrors = 0;
        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();

        for (int i = 0; i < textFields.size(); i++)
        {
            TextField currentField;
            switch (i)
            {
                case 0:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text = currentField.getText();

                    if (text.length() < 12)
                    {
                        errorReasons.put(currentErrors++, "There is no public human in our domain with an income than 1 Trillion +");
                    }

                    if (!Checks.number(text))
                    {
                        errorReasons.put(currentErrors++, "The entry in the 'Annual Income' field is not a number!");
                    }
                    continue;
                case 1:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text1 = currentField.getText();

                    if (text1.length() < 12)
                    {
                        errorReasons.put(currentErrors++, "There is no public human in our domain with a net-worth larger than 1 Trillion +");
                    }

                    if (!Checks.number(text1))
                    {
                        errorReasons.put(currentErrors++, "The entry in the 'Annual Income' field is not a number!");
                    }
                case 2:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Your number field is empty!");
                    }
                    else if (!Checks.phoneValidator(currentField.getText()))
                    {
                        errorReasons.put(currentErrors++, "Phone number is wrong!");
                    }
                    break;
            }


            long assets = Long.parseLong(TOTAL_ASSETS.getText());

            if (assets > 0 && assets < 10000)
            {
                // just give base credit card /shrug
            }
            else if (assets >= 10000 && assets < 50000)
            {
                if (!(assets / 5 < Main.userLoggedIn.getBankAccounts().get(0).getBalance()))
                {

                }
            }




            Iterator<Integer> keys = errorReasons.keys();
            Alert alert = new Alert(Alert.AlertType.WARNING);


            if (keys.hasNext())
            {


                String errors = "";
                int size = 0;


                while (keys.hasNext())
                {
                    int element = keys.next();
                    /**
                     * Could use a stringbuilder but meh.
                     */
                    errors += (errorReasons.get(element) + "\n");
                    size++;
                }

                alert.setHeaderText("You have " + size + " errors!");
                alert.setContentText(errors);
                alert.show();
                return;
            }







        }
    }

    @FXML
    void onApplyHovered(MouseEvent event)
    {
        APPLY_BUTTON.setStyle("-fx-background-color: #9d2929;");
    }

    @FXML
    void onClearButtonExitied(MouseEvent event)
    {
        clearButton.setStyle("-fx-background-color: #212121;");
    }

    @FXML
    void onClearButtonHovered(MouseEvent event)
    {
        clearButton.setStyle("-fx-background-color: #9d2929;");
    }

    @FXML
    void onRegisterExitied(MouseEvent event)
    {
        APPLY_BUTTON.setStyle("-fx-background-color: #212121;");
    }


}

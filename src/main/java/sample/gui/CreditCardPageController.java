package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import sample.Main;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.objects.bank.Card;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.util.operations.FileOperations;
import sample.util.operations.StageOperations;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreditCardPageController
{


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView homeIcon;

    @FXML
    private ImageView transferIcon;

    @FXML
    private ImageView creditCardIcon;

    @FXML
    private ImageView profileIcon;

    @FXML
    private ImageView logoutIcon;

    @FXML
    private Text FULL_NAME_IN_CARD;

    @FXML
    private Text CARD_NUMBER_IN_CARD;

    @FXML
    private Text CREDIT_CARD_TYPE;

    @FXML
    private Text CREDIT_CARD_NUMBER;

    @FXML
    private Text CVV_NUMBER;

    @FXML
    private Text CARD_ISSUED_DATE;

    @FXML
    private Text CARD_EXPIRY_DATE;

    @FXML
    private Text APR;

    @FXML
    private Text CREDIT_CARD_BALANCE;

    @FXML
    public Text EXPR_DATE;

    @FXML
    public Text CARD_ENABLED;

    @FXML
    public Text CREDIT_CARD_LIMIT;

    @FXML
    private PasswordField PIN_TEXT_FIELD;

    @FXML
    private PasswordField CONFIRM_PIN_TEXT_FIELD;

    @FXML
    private Button PIN_CONFIRM;

    @FXML
    private Button DISABLE_CARD;

    @FXML
    private Button GENERATE_CARD_NUMBER;

    @FXML
    private Button CARD_UPGRADE;

    @FXML
    private Button LIMIT_INCREASE;

    @GungaObject
    private User user = Main.userLoggedIn;

    @GungaObject
    private Card card = user.getCards().get(0);

    @GungaObject
    private ArrayList<TextField> pinPasswordFields;

    @GungaObject
    private ArrayList<Button> buttons;

    @GungaObject
    private OnButtonExited onButtonExited;

    @GungaObject
    private OnButtonHovered onButtonHovered;


    @FXML
    void initialize()
    {
        buttons = new ArrayList<>();
        pinPasswordFields = new ArrayList<>();
        buttons.add(PIN_CONFIRM);
        buttons.add(DISABLE_CARD);
        buttons.add(CARD_UPGRADE);
        buttons.add(LIMIT_INCREASE);
        pinPasswordFields.add(PIN_TEXT_FIELD);
        pinPasswordFields.add(CONFIRM_PIN_TEXT_FIELD);

        onButtonExited = new OnButtonExited(buttons);
        onButtonHovered = new OnButtonHovered(buttons);


        FULL_NAME_IN_CARD.setText(user.getFirstName().toUpperCase() + " " + user.getLastName().toUpperCase());
        CARD_NUMBER_IN_CARD.setText(user.getCards().get(0).getCardNumber());
        CREDIT_CARD_NUMBER.setText(card.getCardNumber());
        APR.setText(card.getApr());
        CREDIT_CARD_BALANCE.setText(card.getBalance().toString());
        CREDIT_CARD_TYPE.setText(card.getCardType().toString());
        CVV_NUMBER.setText(card.getCID());
        CREDIT_CARD_TYPE.setText(card.getCardType().toString());
        CARD_ISSUED_DATE.setText(card.getDateIssued().format(DateTimeFormatter.ofPattern("MM/yyyy")));
        CARD_EXPIRY_DATE.setText(card.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/yyyy")));
        EXPR_DATE.setText(card.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/yyyy")));
        CARD_ENABLED.setText(card.isDisabled() ? "No" : "Yes");
        DISABLE_CARD.setText(card.isDisabled() ? "Enable Card" : DISABLE_CARD.getText());
        CREDIT_CARD_LIMIT.setText(card.getLimit().toPlainString());

    }



    // ===================================== ON CLICKS (BUTTON) =====================================

    @FXML
    void onGenerateCardClick(ActionEvent event)
    {

        Alert genNewCard = new Alert(Alert.AlertType.INFORMATION, "Would you like to generate your card?",
                new ButtonType("Yes"),
                new ButtonType("No"));
        Optional<ButtonType> def = genNewCard.showAndWait();

        if (def.get().getText().equals("Yes") && !card.isDisabled())
        {
            card.generateNewCard();
            CARD_NUMBER_IN_CARD.setText(card.getCardNumber());
            CREDIT_CARD_NUMBER.setText(card.getCardNumber());
            CVV_NUMBER.setText(card.getCID());
            CREDIT_CARD_TYPE.setText(card.getCardType().toString());
            CARD_ISSUED_DATE.setText(card.getDateIssued().format(DateTimeFormatter.ofPattern("MM/yyyy")));
            CARD_EXPIRY_DATE.setText(card.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/yyyy")));
            EXPR_DATE.setText(card.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/yyyy")));
            FileOperations.writeToFile(FileOperations.users, Main.users);
        }

        if (card.isDisabled())
        {
            genNewCard.setContentText("You cannot make edits to a card that is disabled!");
            genNewCard.show();
        }
        return;

    }


    @FXML
    void onDisableCardClick(ActionEvent event)
    {
        Alert cardDisOrEn;
        if (!card.isDisabled())
        {
            cardDisOrEn = new Alert(Alert.AlertType.INFORMATION, "Would you like to enable your card?",
                                    new ButtonType("Yes"),
                                    new ButtonType("No"));
            Optional<ButtonType> s = cardDisOrEn.showAndWait();

            if (s.get().getText().equals("Yes"))
            {
                card.setDisabled(true);
                CARD_ENABLED.setText("Yes");
                DISABLE_CARD.setText("Disable Card");
            }
        }
        else
        {
            cardDisOrEn = new Alert(Alert.AlertType.INFORMATION, "Would you like to disable your card?",
                    new ButtonType("Yes"),
                    new ButtonType("No"));
            Optional<ButtonType> s = cardDisOrEn.showAndWait();

            if (s.get().getText().equals("Yes"))
            {
                card.setDisabled(false);
                CARD_ENABLED.setText("No");
                DISABLE_CARD.setText("Enable Card");
            }
        }
        FileOperations.writeToFile(FileOperations.users, Main.users);
    }

    @FXML
    void onLimitIncreaseClick(ActionEvent event)
    {

    }

    @FXML
    void onPinConfirmedClick(ActionEvent event)
    {
        int currentErrors = 0;
        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();
        for (int i = 0; i < pinPasswordFields.size(); i++)
        {
            TextField currentField;
            switch (i)
            {
                case 0:
                    currentField = pinPasswordFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Pin Field is empty!");
                    }

                    if (!currentField.getText().chars().allMatch(Character::isDigit))
                    {
                        errorReasons.put(currentErrors++, "Your pin can only contain numbers!");
                    }

                    if (currentField.getText().length() > 4)
                    {
                        errorReasons.put(currentErrors++, "Your pin cannot be longer than 4 numbers!");
                    }
                    continue;
                case 1:
                    currentField = pinPasswordFields.get(i);
                    String otherTextFielData = pinPasswordFields.get(i-1).getText();
                    if (!(currentField.getText().equals(otherTextFielData)))
                    {
                        errorReasons.put(currentErrors++, "Your pins dont match");
                    }
                    break;
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

            ButtonType exitButton = new ButtonType("Okay!", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert successfullPin = new Alert(Alert.AlertType.NONE,
                    "Pin changed!", exitButton);
            successfullPin.show();
            card.setPin(PIN_CONFIRM.getText());

            FileOperations.writeToFile(FileOperations.users, Main.users);

            pinPasswordFields.forEach(field -> field.setText(""));
            return;


        }
    }

    // ===================================== ON CLICKS (SWITCH SCENES) =====================================

    @FXML
    public void onCardIconClicked(MouseEvent event)
    {
        return;
    }

    @FXML
    void onDashboardClicked(MouseEvent event)
    {
        StageOperations.switchToDashboardScene();
    }


    @FXML
    void onProfileClicked(MouseEvent event)
    {
        StageOperations.switchToProfileScene();
    }

    @FXML
    void onLogoutClicked(MouseEvent event)
    {
        StageOperations.initLogoutSequence();
    }

    @FXML
    void onTransferIconClicked(MouseEvent event)
    {
        StageOperations.switchToTransfersScene();
    }


    public void onApplyCardUpgradeClick(ActionEvent actionEvent) {
    }
}

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.actions.OnIconClicked;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.Card;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;

public class CreditCardPageController implements Controller
{

    @GungaObject
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    private User userLoggedIn;

    @GungaObject
    private Card card;

    @GungaObject
    private ArrayList<TextField> pinPasswordFields;

    @GungaObject
    private ArrayList<Button> buttons;

    @GungaObject
    private ArrayList<ImageView> icons;

    @GungaObject
    private OnButtonExited onButtonExited;

    @GungaObject
    private OnButtonHovered onButtonHovered;

    @GungaObject
    private OnIconClicked onIconClicked;

    @GungaObject
    private StageHandler stageHandler;

    @GungaObject
    private FileHandler fileHandler;


    /**
     * @param user
     *      User Logged in
     * @param fileHandler
     *      Main File Handler
     * @param stageHandler
     *      Main Stage Handler
     */
    @Override
    public void initData(User user, StageHandler stageHandler, FileHandler fileHandler)
    {
        userLoggedIn = user;
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;
        icons = new ArrayList<>();
        icons.addAll(homeIcon, transferIcon, creditCardIcon, profileIcon, logoutIcon);
        onIconClicked = new OnIconClicked(icons, userLoggedIn, stageHandler, fileHandler);
        preloadTextFields(user);
    }

    @Override
    public User getUser()
    {
        return userLoggedIn;
    }

    @FXML
    void initialize()
    {
        buttons = new ArrayList<>();
        pinPasswordFields = new ArrayList<>();

        buttons.addAll(PIN_CONFIRM, DISABLE_CARD, CARD_UPGRADE);
        pinPasswordFields.addAll(PIN_TEXT_FIELD, CONFIRM_PIN_TEXT_FIELD);


        onButtonExited = new OnButtonExited(buttons);
        onButtonHovered = new OnButtonHovered(buttons);

        PIN_TEXT_FIELD.textProperty().addListener((observableValue, s, s2) ->
        {
            String t = PIN_TEXT_FIELD.getText();
            if (t.length() > 4) {
                PIN_TEXT_FIELD.setText(t.substring(0, 4));
            }
        });

        CONFIRM_PIN_TEXT_FIELD.textProperty().addListener((observableValue, s, s2) ->
        {
            String t = CONFIRM_PIN_TEXT_FIELD.getText();
            if (t.length() > 4) {
                CONFIRM_PIN_TEXT_FIELD.setText(t.substring(0, 4));
            }
        });

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
        }

        if (card.isDisabled())
        {
            genNewCard.setContentText("You cannot make edits to a card that is disabled!");
            genNewCard.show();
        }


    }



    @FXML
    void onDisableCardClick(ActionEvent event)
    {
        Alert cardDisOrEn;

        if (!card.isDisabled())
        {
            cardDisOrEn = new Alert(Alert.AlertType.INFORMATION, "Would you like to disable your card?",
                    new ButtonType("Yes"),
                    new ButtonType("No"));
            Optional<ButtonType> delay = cardDisOrEn.showAndWait();

            // IntelliJ being weird.. delay will always be present
            if (delay.get().getText().equals("Yes"))
            {
                card.setDisabled(true);
                CARD_ENABLED.setText("No");
                DISABLE_CARD.setText("Enable Card");
            }
        }
        else
        {
            cardDisOrEn = new Alert(Alert.AlertType.INFORMATION, "Would you like to enable your card?",
                    new ButtonType("Yes"),
                    new ButtonType("No"));
            Optional<ButtonType> s = cardDisOrEn.showAndWait();

            if (s.get().getText().equals("Yes"))
            {
                card.setDisabled(false);
                CARD_ENABLED.setText("Yes");
                DISABLE_CARD.setText("Disable Card");
            }
        }
    }


    @FXML
    void onPinConfirmedClick(ActionEvent event)
    {

        if (card.isDisabled())
        {
            ButtonType exitButton = new ButtonType("Okay!", ButtonBar.ButtonData.CANCEL_CLOSE);

            Alert cardDisabled = new Alert(Alert.AlertType.ERROR,
                    "Card is disabled!", exitButton);
            cardDisabled.show();
            return;
        }
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
                    String otherTextFieldData = pinPasswordFields.get(i - 1).getText();
                    if (!(currentField.getText().equals(otherTextFieldData)))
                    {
                        errorReasons.put(currentErrors++, "Your pins don't match");
                    }
                    break;
            }

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

            ButtonType exitButton = new ButtonType("Okay!", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert successfulPin = new Alert(Alert.AlertType.NONE,
                    "Pin changed!", exitButton);
            successfulPin.show();
            card.setPin(PIN_CONFIRM.getText());


            pinPasswordFields.forEach(field -> field.setText(""));
            return;


        }
    }



    public void onApplyCardUpgradeClick()
    {

      stageHandler.openNewScene("credit_card_application", userLoggedIn);

    }

    private void preloadTextFields(User user)
    {
        card = user.getCards().get(0);

        FULL_NAME_IN_CARD.setText(user.getFirstName().toUpperCase() + " " + userLoggedIn.getLastName().toUpperCase());
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
        CREDIT_CARD_LIMIT.setText((card.getLimit().toBigInteger().intValue() != -1) ? card.getLimit().toPlainString() : "No Limit");
    }
}

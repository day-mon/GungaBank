package sample.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

    @FXML
    void onApplyCardExited(MouseEvent event)
    {

    }

    @FXML
    void onApplyCardHovered(MouseEvent event)
    {

    }

    @FXML
    void onApplyCardUpgradeClick(ActionEvent event)
    {

    }

    @FXML
    void onCardIconClicked(MouseEvent event)
    {

    }

    @FXML
    void onConfirmedButtonExitied(MouseEvent event)
    {

    }

    @FXML
    void onConfirmedButtonHovered(MouseEvent event)
    {

    }

    @FXML
    void onDisableCardClick(ActionEvent event)
    {

    }

    @FXML
    void onDisabledCardExited(MouseEvent event)
    {

    }

    @FXML
    void onDisabledCardHovered(MouseEvent event)
    {

    }

    @FXML
    void onGenerateCardClick(ActionEvent event)
    {

    }

    @FXML
    void onGenerateCardExited(MouseEvent event)
    {

    }

    @FXML
    void onGenerateCardHovered(MouseEvent event)
    {

    }

    @FXML
    void onLimitIncreaseClick(ActionEvent event)
    {

    }

    @FXML
    void onLimitIncreaseExited(MouseEvent event)
    {

    }

    @FXML
    void onLimitIncreaseHovered(MouseEvent event)
    {

    }

    @FXML
    void onLogoutClicked(MouseEvent event)
    {

    }

    @FXML
    void onPinConfirmedClick(ActionEvent event)
    {

    }

    @FXML
    void onProfileClicked(MouseEvent event)
    {

    }

    @FXML
    void onTransferIconClicked(MouseEvent event)
    {

    }

    @FXML
    void initialize()
    {
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert transferIcon != null : "fx:id=\"transferIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert creditCardIcon != null : "fx:id=\"creditCardIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert profileIcon != null : "fx:id=\"profileIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert logoutIcon != null : "fx:id=\"logoutIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert FULL_NAME_IN_CARD != null : "fx:id=\"FULL_NAME_IN_CARD\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CARD_NUMBER_IN_CARD != null : "fx:id=\"CARD_NUMBER_IN_CARD\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CREDIT_CARD_TYPE != null : "fx:id=\"CREDIT_CARD_TYPE\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CREDIT_CARD_NUMBER != null : "fx:id=\"CREDIT_CARD_NUMBER\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CVV_NUMBER != null : "fx:id=\"CVV_NUMBER\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CARD_ISSUED_DATE != null : "fx:id=\"CARD_ISSUED_DATE\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CARD_EXPIRY_DATE != null : "fx:id=\"CARD_EXPIRY_DATE\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert APR != null : "fx:id=\"APR\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CREDIT_CARD_BALANCE != null : "fx:id=\"CREDIT_CARD_BALANCE\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert PIN_TEXT_FIELD != null : "fx:id=\"PIN_TEXT_FIELD\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CONFIRM_PIN_TEXT_FIELD != null : "fx:id=\"CONFIRM_PIN_TEXT_FIELD\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert PIN_CONFIRM != null : "fx:id=\"PIN_CONFIRM\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert DISABLE_CARD != null : "fx:id=\"DISABLE_CARD\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert GENERATE_CARD_NUMBER != null : "fx:id=\"GENERATE_CARD_NUMBER\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert CARD_UPGRADE != null : "fx:id=\"CARD_UPGRADE\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert LIMIT_INCREASE != null : "fx:id=\"LIMIT_INCREASE\" was not injected: check your FXML file 'dashboard.fxml'.";

    }
}

package sample.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.Main;
import sample.core.objects.BankAccount;
import sample.core.objects.User;
import sample.core.other.GungaObject;
import sample.util.operations.StageOperations;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController
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
    private Text FIRST_NAME;

    @FXML
    private Text FULL_NAME;

    @FXML
    private Text LAST_NAME;

    @FXML
    private Text LOGIN_ID;

    @FXML
    private Text DATE_OF_BIRTH;

    @FXML
    private Text NUMBER_OF_ACCOUNTS;

    @FXML
    private Text NUMBER_OF_CARDS;

    @FXML
    private Text ACCOUNT_NUMBER;

    @GungaObject
    private User user = Main.userLoggedIn;

    @GungaObject
    private BankAccount bankAccount = Main.userLoggedIn.getBankAccounts().get(0);


    @FXML
    void onCardIconClicked(MouseEvent event)
    {
        return;
    }

    @FXML
    void onLogoutClicked(MouseEvent event)
    {
        StageOperations.initLogoutSequence();
    }

    @FXML
    void onProfileClicked(MouseEvent event)
    {
        return;
    }

    @FXML
    void onTransferIconClicked(MouseEvent event)
    {
        StageOperations.switchToTransfersScene();
    }

    @FXML
    void onDashboardClicked(MouseEvent event)
    {
        StageOperations.switchToDashboardScene();
    }

    @FXML
    void initialize()
    {
        FULL_NAME.setText(user.getLastName() + ", " + user.getFirstName());
        FULL_NAME.setTextAlignment(TextAlignment.CENTER);
        FIRST_NAME.setText(user.getFirstName());
        LAST_NAME.setText(user.getLastName());
        LOGIN_ID.setText(user.getEmail());
        DATE_OF_BIRTH.setText(user.getDateOfBirth().toString());
        NUMBER_OF_ACCOUNTS.setText(Integer.toString(user.getBankAccounts().size()));
        NUMBER_OF_CARDS.setText(Integer.toString(user.getCards().size()));
        ACCOUNT_NUMBER.setText(Long.toString(bankAccount.getAccountNumber()));
    }


}

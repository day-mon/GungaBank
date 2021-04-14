package sample.gui;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.actions.OnIconClicked;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.util.structures.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageController implements Controller
{

    private Controller prevoiusController;

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
    private User userLoggedIn;

    @GungaObject
    private BankAccount bankAccount;

    @GungaObject
    private ArrayList<ImageView> icons;

    @GungaObject
    private OnIconClicked onIconClicked;


    @FXML
    void initialize()
    {

    }


    /**
     * @param user
     */
    @Override
    public void initData(User user)
    {
        userLoggedIn = user;
        bankAccount = user.getBankAccounts().get(0);
        FULL_NAME.setText(user.getLastName() + ", " + user.getFirstName());
        FULL_NAME.setTextAlignment(TextAlignment.CENTER);
        FIRST_NAME.setText(user.getFirstName());
        System.out.println(user.getFirstName());
        LAST_NAME.setText(user.getLastName());
        LOGIN_ID.setText(user.getEmail());
        DATE_OF_BIRTH.setText(user.getDateOfBirth().toString());
        NUMBER_OF_ACCOUNTS.setText(Integer.toString(user.getBankAccounts().size()));
        NUMBER_OF_CARDS.setText(Integer.toString(user.getCards().size()));
        ACCOUNT_NUMBER.setText(Long.toString(bankAccount.getAccountNumber()));
        icons = new ArrayList<>();
        icons.addAll(homeIcon, transferIcon, creditCardIcon, logoutIcon, profileIcon);
        onIconClicked = new OnIconClicked(icons, user);
    }

    @Override
    public User getUser()
    {
        return userLoggedIn;
    }


}

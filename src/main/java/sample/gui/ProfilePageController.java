package sample.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.Main;

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
    private Text fNameFill;

    @FXML
    private Text fullNameFill;

    @FXML
    private Text lNameFill;

    @FXML
    private Text LIDFill;

    @FXML
    private Text DOBFill;

    @FXML
    private Text numAccFill;

    @FXML
    private Text numCardsFill;

    @FXML
    void onCardIconClicked(MouseEvent event)
    {

    }

    @FXML
    void onLogoutClicked(MouseEvent event)
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
        fullNameFill.setText(Main.userLoggedIn.getLastName() + ", " + Main.userLoggedIn.getFirstName());
        fNameFill.setTextAlignment(TextAlignment.CENTER);
        fNameFill.setText(Main.userLoggedIn.getFirstName());
        lNameFill.setText(Main.userLoggedIn.getLastName());
        LIDFill.setText(Main.userLoggedIn.getEmail());
        DOBFill.setText(Main.userLoggedIn.getDateOfBirth().toString());
        numAccFill.setText(Integer.toString(Main.userLoggedIn.getBankAccounts().size()));
        numCardsFill.setText(Integer.toString(Main.userLoggedIn.getCards().size()));
    }
}

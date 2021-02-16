/**
 * Sample Skeleton for 'dashboard.fxml' Controller Class
 */

package sample.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class DashboardPageController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="homeIcon"
    private ImageView homeIcon; // Value injected by FXMLLoader

    @FXML // fx:id="transferIcon"
    private ImageView transferIcon; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardIcon"
    private ImageView creditCardIcon; // Value injected by FXMLLoader

    @FXML // fx:id="profileIcon"
    private ImageView profileIcon; // Value injected by FXMLLoader

    @FXML // fx:id="logoutIcon"
    private ImageView logoutIcon; // Value injected by FXMLLoader

    @FXML // fx:id="nameText"
    private Text nameText; // Value injected by FXMLLoader

    @FXML // fx:id="bankAccountBalance"
    private Text bankAccountBalance; // Value injected by FXMLLoader

    @FXML // fx:id="bankAccountScollable"
    private ComboBox<?> bankAccountScollable; // Value injected by FXMLLoader

    @FXML // fx:id="transactionsTable"
    private TreeTableView<?> transactionsTable; // Value injected by FXMLLoader

    @FXML // fx:id="dateColumn"
    private TreeTableColumn<?, ?> dateColumn; // Value injected by FXMLLoader

    @FXML // fx:id="accountNumberColumn"
    private TreeTableColumn<?, ?> accountNumberColumn; // Value injected by FXMLLoader

    @FXML // fx:id="transactionAmountColumn"
    private TreeTableColumn<?, ?> transactionAmountColumn; // Value injected by FXMLLoader

    @FXML // fx:id="debitOrWithdrawColumn"
    private TreeTableColumn<?, ?> debitOrWithdrawColumn; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardBalance"
    private Text creditCardBalance; // Value injected by FXMLLoader

    @FXML // fx:id="creditCardScrollable"
    private ComboBox<?> creditCardScrollable; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert transferIcon != null : "fx:id=\"transferIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert creditCardIcon != null : "fx:id=\"creditCardIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert profileIcon != null : "fx:id=\"profileIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert logoutIcon != null : "fx:id=\"logoutIcon\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert nameText != null : "fx:id=\"nameText\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert bankAccountBalance != null : "fx:id=\"bankAccountBalance\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert bankAccountScollable != null : "fx:id=\"bankAccountScollable\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert transactionsTable != null : "fx:id=\"transactionsTable\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert accountNumberColumn != null : "fx:id=\"accountNumberColumn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert transactionAmountColumn != null : "fx:id=\"transactionAmountColumn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert debitOrWithdrawColumn != null : "fx:id=\"debitOrWithdrawColumn\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert creditCardBalance != null : "fx:id=\"creditCardBalance\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert creditCardScrollable != null : "fx:id=\"creditCardScrollable\" was not injected: check your FXML file 'dashboard.fxml'.";

    }
}

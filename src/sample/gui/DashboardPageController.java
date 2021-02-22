/**
 * Sample Skeleton for 'dashboard.fxml' Controller Class
 */

package sample.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.Main;
import sample.core.objects.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

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
        /*if (Main.userLoggedIn.getBankAccounts().size() <= 0) {

        }*/
        //TODO: Figure out how to add stuff to treetablecolumns so we can load each transactionn on the dashboard
        /*for (Transaction t : Main.userLoggedIn.getBankAccounts().get(0).getTransactions()) {

        }*/

        String replaced = nameText.getText().replace("%{name}", Main.userLoggedIn.getFirstName());
        nameText.setText(replaced);
        nameText.setTextAlignment(TextAlignment.CENTER);
    }
}

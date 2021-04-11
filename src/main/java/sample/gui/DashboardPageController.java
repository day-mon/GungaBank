package sample.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sample.Main;
import sample.actions.OnIconClicked;
import sample.core.objects.BankAccount;
import sample.core.objects.Transaction;
import sample.core.objects.User;
import sample.util.structures.ArrayList;
import sample.core.other.GungaObject;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardPageController
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
    private Text bankAccountBalance;

    @FXML
    private ComboBox<?> bankAccountScollable;

    @FXML
    private Text creditCardBalance;

    @FXML
    private ComboBox<?> creditCardScrollable;

    @FXML
    private TableView<Transaction> transactionTable;

    @FXML
    private TableColumn<Transaction, String> dateColumn;

    @FXML
    private TableColumn<Transaction, String> accountColumn;

    @FXML
    private TableColumn<Transaction, String> ammountColumn;

    @FXML
    private TableColumn<Transaction, String> transactionColumn;

    @FXML
    private Text nameText;

    @GungaObject
    private User user = Main.userLoggedIn;

    @GungaObject
    private ArrayList<ImageView> icons;

    @GungaObject
    private OnIconClicked onIconClicked;






    @FXML
    void initialize()
    {
        BankAccount bankAccount = Main.userLoggedIn.getBankAccounts().get(0);
        User user = Main.userLoggedIn;

        icons = new ArrayList<>();
        icons.addAll(homeIcon, transferIcon, creditCardIcon, logoutIcon, profileIcon);
        onIconClicked = new OnIconClicked(icons);




        String replaced = nameText.getText().replace("%{name}", user.getFirstName());

        nameText.setText(replaced);
        nameText.setTextAlignment(TextAlignment.CENTER);
        creditCardBalance.setText(user.getCards().size() <= 0  ? "N/A" : user.getCards().get(0).getBalance().doubleValue() +"");



        ObservableList<Transaction> columns = FXCollections.observableArrayList();

        bankAccount.getTransactions().forEach(columns::add);


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionType"));
        transactionTable.setItems(columns);


        bankAccountBalance.setText(Main.userLoggedIn.getBankAccounts().get(0).getBalance() + "");
    }



}




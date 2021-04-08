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
import sample.GungaBank;
import sample.Main;
import sample.actions.OnIconClicked;
import sample.core.objects.BankAccount;
import sample.core.objects.Transaction;
import sample.core.objects.User;
import sample.core.other.GungaObject;

import java.net.URL;
import java.util.ArrayList;
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
    private ArrayList<ImageView> imageViewArrayList;

    @GungaObject
    private OnIconClicked onIconClicked;



    @FXML
    void initialize()
    {
        imageViewArrayList = new ArrayList<>();
        imageViewArrayList.add(homeIcon);
        imageViewArrayList.add(transferIcon);
        imageViewArrayList.add(creditCardIcon);
        imageViewArrayList.add(profileIcon);
        imageViewArrayList.add(logoutIcon);
        onIconClicked = new OnIconClicked(imageViewArrayList);
        BankAccount bankAccount = Main.userLoggedIn.getBankAccounts().get(0);
        User user = Main.userLoggedIn;

        String replaced = nameText.getText().replace("%{name}", user.getFirstName());

        nameText.setText(replaced);
        nameText.setTextAlignment(TextAlignment.CENTER);
        creditCardBalance.setText(user.getCards().size() <= 0  ? "N/A" : user.getCards().get(0).getBalance().doubleValue() +"");



        ObservableList<Transaction> s = FXCollections.observableArrayList();

        bankAccount.getTransactions().forEach(s::add);


        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionType"));
        transactionTable.setItems(s);


        bankAccountBalance.setText(Main.userLoggedIn.getBankAccounts().get(0).getBalance() + "");
    }



}




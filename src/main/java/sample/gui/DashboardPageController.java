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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.actions.OnIconClicked;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.BankAccount;
import sample.core.objects.bank.Transaction;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.structures.ArrayList;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardPageController implements Controller
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
    private User userLoggedIn;

    @GungaObject
    private ArrayList<ImageView> icons;

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
    public void initData(User user, StageHandler stageHandler, FileHandler fileHandler) {
        userLoggedIn = user;
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;
        BankAccount bankAccount = user.getBankAccounts().get(0);
        icons = new ArrayList<>();
        icons.addAll(homeIcon, transferIcon, creditCardIcon, logoutIcon, profileIcon);
        onIconClicked = new OnIconClicked(icons, userLoggedIn, stageHandler, fileHandler);

        String replaced = nameText.getText().replace("%{name}", user.getFirstName());
        nameText.setText(replaced);
        creditCardBalance.setText(user.getCards().size() <= 0 ? "N/A" : user.getCards().get(0).getBalance().doubleValue() + "");
        ObservableList<Transaction> columns = FXCollections.observableArrayList();
        bankAccount.getTransactions().forEach(columns::add);

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionType"));
        transactionTable.setItems(columns);

        bankAccountBalance.setText(user.getBankAccounts().get(0).getBalance() + "");

        icons = new ArrayList<>();


    }

    @Override
    public User getUser()
    {
        return userLoggedIn;
    }


    @FXML
    void initialize()
    {
        LOGGER.info("Dashboard scene loaded successfully!");
    }


}

package sample.gui;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import sample.Main;
import sample.core.objects.BankAccount;
import sample.core.objects.Transaction;
import sample.core.objects.User;
import sample.core.other.GungaObject;
import sample.util.operations.StageOperations;

import java.net.URL;
import java.util.Optional;
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


    @FXML
    void initialize()
    {
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


    public void onTransferIconClicked(MouseEvent mouseEvent)
    {
        StageOperations.switchToTransfersScene();
    }

    public void onCardIconClicked(MouseEvent mouseEvent)
    {
        StageOperations.switchToCardPage(user);
    }

    public void onProfileClicked(MouseEvent mouseEvent)
    {
        StageOperations.switchToProfileScene();
    }

    public void onLogoutClicked(MouseEvent mouseEvent)
    {

        Alert alert = new Alert(Alert.AlertType.WARNING,
                "You have 5 seconds! To respond or you will be logged out!",
                new ButtonType("Abort!", ButtonBar.ButtonData.CANCEL_CLOSE),
                new ButtonType("Okay!", ButtonBar.ButtonData.OK_DONE));

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get().getText().equals("Abort!"))
        {
            return;
        }


        PauseTransition delay = new PauseTransition(Duration.seconds(5.0));
        delay.setOnFinished(event ->
        {
            try
            {
                Main.userLoggedIn = null;
                Main.open("/loginpage");
            }
            catch (Exception e)
            {
                System.out.printf("Error occured: %s", e.getMessage());
            }
        });
        delay.play();
    }
}




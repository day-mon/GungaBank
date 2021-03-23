package sample.gui;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

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
import sample.core.objects.Transaction;

public class DashboardPageController {

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
    private TableColumn<Transaction, String> wOrDColumn;

    @FXML
    private Text nameText;

    @FXML
    void initialize() {
        String replaced = nameText.getText().replace("%{name}", Main.userLoggedIn.getFirstName());
        nameText.setText(replaced);
        nameText.setTextAlignment(TextAlignment.CENTER);


        ObservableList<Transaction> s = FXCollections.observableArrayList();
        Transaction trans1 = new Transaction(new BigDecimal(".32"), 232323, new Date(), "+");
        Transaction trans2 = new Transaction(new BigDecimal(".3"), 232311123, new Date(), "+");
        Transaction trans3 = new Transaction(new BigDecimal(".2"), 222, new Date(), "+");
        s.addAll(trans1, trans2, trans3);

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Account #"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Amount"));
        wOrDColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("+/-"));
        transactionTable.setItems(s);



    }
}




package sample.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.core.objects.BankAccount;
import sample.core.objects.Transaction;
import sample.core.objects.User;
import sample.core.other.GungaObject;
import sample.util.Checks;
import sample.util.operations.AlertOperations;
import sample.util.operations.FileOperations;
import sample.util.operations.StageOperations;
import sample.util.operations.StringOperations;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class TransfersPageController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ObservableList<Transaction> s = FXCollections.observableArrayList();

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
    private TextField amountToSend;

    @FXML
    private TextField accountNumberTF;

    @GungaObject
    private BankAccount bankAccount = Main.userLoggedIn.getBankAccounts().get(0);

    @GungaObject
    private User user = Main.userLoggedIn;

    @FXML
    void onCardIconClicked(MouseEvent event)
    {
        StageOperations.switchToCardPage(user);
    }

    @FXML
    void onLogoutClicked(MouseEvent event)
    {
        StageOperations.initLogoutSequence();
    }

    @FXML
    void onProfileClicked(MouseEvent event)
    {
        StageOperations.switchToProfileScene();
    }

    @FXML
    void onTransferIconClicked(MouseEvent event)
    {
        StageOperations.switchToTransfersScene();
    }

    @FXML
    void onHomeButtonClicked(MouseEvent event)
    {
        StageOperations.switchToDashboardScene();
    }




    @FXML
    void initialize()
    {

        List<Transaction> transactionsToDisplay = bankAccount
                .getTransactions()
                .stream()
                .filter(transaction -> transaction.getTransactionType().getTransactionType().equals("TRANSFER"))
                .collect(Collectors.toList());

        s.addAll(transactionsToDisplay);

        dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
        ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
        transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionType"));
        transactionTable.setItems(s);

    }

    public void transferMoneyButtonClicked(ActionEvent actionEvent)
    {
        String amountTosend = amountToSend.getText();
        String accountNumber = accountNumberTF.getText();

        if (!Checks.number(amountTosend) && !Checks.number(accountNumber))
        {
            AlertOperations.AlertShortner("bad", "Invalid Number!", "Please only insert numbers in the sending field!");
            return;
        }


        Stage primaryStage = new Stage();
        Button btn = new Button();
        btn.setStyle("-fx-background-color: #313131");
        btn.setText("Confirm");
        btn.setTextFill(Color.WHITE);

        Text text = new Text("Enter your password");

        text.setFont(Font.font("Berlin Sans FB Demi Bold"));
        text.setStyle("-fx-text-inner-color: #FFFF");

        PasswordField passwordField = new PasswordField();
        passwordField.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FFFF; -fx-text-fill: #FFFF");
        passwordField.setPromptText("Password");

        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);


        VBox dialogVbox = new VBox(20);
        dialogVbox.setStyle("-fx-background-color: #212121");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setFillWidth(false);
        dialogVbox.getChildren().addAll(text, passwordField, btn);


        double amt = Double.parseDouble(amountTosend);
        long accNumber = Long.parseLong(accountNumber);
        if (Main.userLoggedIn.getBankAccounts().get(0).getBalance() < amt)
        {
            AlertOperations.AlertShortner("bad", "Not enough funds", "You do not have enough funds in your account to complete this transaction");
            return;
        }

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        if (!dialog.isShowing())
        {
            dialog.setScene(dialogScene);
            dialog.show();
            btn.setOnAction(
                    new EventHandler<ActionEvent>()
                    {
                        @Override
                        public void handle(ActionEvent event)
                        {
                            if (Objects.equals(Main.userLoggedIn.gethashedPass(), StringOperations.hashPassword(passwordField.getText())))
                            {
                                Transaction transaction = new Transaction(new BigDecimal(amt + ""), accNumber, new Date(), Transaction.TransactionType.TRANSFER);
                                bankAccount.getTransactions().add(transaction);

                                dialog.close();

                                s.add(transaction);
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("Date"));
                                accountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("accountNumber"));
                                ammountColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("amount"));
                                transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction, String>("TransactionType"));
                                transactionTable.setItems(s);


                                bankAccount.removeToBalance(transaction.getAmount());

                                Iterator<User> it = Main.users.elements();
                                while(it.hasNext())
                                {
                                    User u = it.next();
                                    System.out.println(u.toString());
                                    if (u.getBankAccounts().size() > 0)
                                    {
                                        System.out.println("First if statement is okay!");
                                        if (accNumber == u.getBankAccounts().get(0).getAccountNumber())
                                        {
                                            System.out.println(u.getFirstName() + " has the money!");
                                            System.out.println(u.getFirstName() + " balance is " + u.getBankAccounts().get(0).getBalance());
                                            u.getBankAccounts().get(0).addToBalance(transaction.getAmount());
                                            u.getBankAccounts().get(0).getTransactions().add(transaction);
                                            System.out.println("Activated");
                                            System.out.println(u.getFirstName() + " balance is " + u.getBankAccounts().get(0).getBalance());


                                        }
                                    }

                                }
                                FileOperations.writeToFile(FileOperations.users, Main.users);


                            }
                            else
                            {
                                passwordField.setStyle("-fx-background-color: transparent; -fx-border-width: 0px 0px 2px 0px; -fx-border-color: #FF0000; -fx-text-fill: #FFFF");
                            }
                        }
                    }
            );
        }
    }



}

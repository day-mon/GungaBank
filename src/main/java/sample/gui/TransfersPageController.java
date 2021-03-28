package sample.gui;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Main;
import sample.util.operations.StageOperations;
import sample.util.operations.StringOperations;

public class TransfersPageController
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
    private TableView<?> transactionTable;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private TableColumn<?, ?> accountColumn;

    @FXML
    private TableColumn<?, ?> ammountColumn;

    @FXML
    private TableColumn<?, ?> wOrDColumn;

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
        Main.open("/dashboard");
    }


    @FXML
    void initialize()
    {
        assert homeIcon != null : "fx:id=\"homeIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert transferIcon != null : "fx:id=\"transferIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert creditCardIcon != null : "fx:id=\"creditCardIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert profileIcon != null : "fx:id=\"profileIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert logoutIcon != null : "fx:id=\"logoutIcon\" was not injected: check your FXML file 'transfers.fxml'.";
        assert transactionTable != null : "fx:id=\"transactionTable\" was not injected: check your FXML file 'transfers.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert accountColumn != null : "fx:id=\"accountColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert ammountColumn != null : "fx:id=\"ammountColumn\" was not injected: check your FXML file 'transfers.fxml'.";
        assert wOrDColumn != null : "fx:id=\"wOrDColumn\" was not injected: check your FXML file 'transfers.fxml'.";

    }

    public void transferMoneyButtonClicked(ActionEvent actionEvent)
    {

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
                                dialog.close();
                            }
                        }
                    }
            );
        }
    }
}

package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.GungaBankConstants;
import sample.Main;
import sample.core.objects.Card;
import sample.core.objects.User;
import sample.core.other.GungaObject;
import sample.util.Checks;
import sample.util.operations.FileOperations;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

public class CardApplicationController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ANNUAL_INCOME;

    @FXML
    private TextField TOTAL_ASSETS;

    @FXML
    private Button clearButton;

    @FXML
    private Button APPLY_BUTTON;

    @GungaObject
    private User user = Main.userLoggedIn;

    @GungaObject
    private ArrayList<TextField> textFields;

    @FXML
    void initialize()
    {
        textFields = new ArrayList<TextField>();
        textFields.add(ANNUAL_INCOME);
        textFields.add(TOTAL_ASSETS);
    }

    @FXML
    void clearButtonClicked(ActionEvent event)
    {
        textFields.forEach(textField -> textField.setText(""));
    }

    @FXML
    void onApplyClicked(ActionEvent event)
    {
        int currentErrors = 0;
        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();

        for (int i = 0; i < textFields.size(); i++)
        {
            TextField currentField;
            switch (i)
            {
                case 0:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text = currentField.getText();

                    if (text.length() > 12)
                    {
                        errorReasons.put(currentErrors++, "There is no public human in our domain with an income than 1 Trillion +");
                    }

                    if (!Checks.number(text))
                    {
                        errorReasons.put(currentErrors++, "The entry in the 'Annual Income' field is not a number!");
                    }
                    continue;
                case 1:
                    currentField = textFields.get(i);
                    if (currentField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text1 = currentField.getText();

                    if (text1.length() > 12)
                    {
                        errorReasons.put(currentErrors++, "There is no public human in our domain with a net-worth larger than 1 Trillion +");
                    }

                    if (!Checks.number(text1))
                    {
                        errorReasons.put(currentErrors++, "The entry in the 'Annual Income' field is not a number!");
                    }
                break;
            }


            double as = Double.parseDouble(TOTAL_ASSETS.getText());
            double in = Double.parseDouble(ANNUAL_INCOME.getText());



            if (in <= 20000)
            {
                if (as >= in * 2)
                {
                    addCard("18.99", Card.CardType.GOLD, String.valueOf(getLimit(Card.CardType.GOLD)), "0");
                }
                else if (as < in * 2 && as > in / 2)
                {
                    addCard("20.99", Card.CardType.SILVER, String.valueOf(getLimit(Card.CardType.SILVER)), "0");
                }
                else
                {
                    addCard("22.99", Card.CardType.BRONZE, String.valueOf(getLimit(Card.CardType.BRONZE)), "0");
                }
            }
            if (in > 20000 && in <= 150000)
            {
                if (as >= in * 4)
                {
                    addCard("15.99", Card.CardType.PLATINUM, String.valueOf(getLimit(Card.CardType.PLATINUM)), "0");
                }
                else if (as < in * 3 && as > in / 3)
                {
                    addCard("18.99", Card.CardType.GOLD, String.valueOf(getLimit(Card.CardType.GOLD)), "0");
                }
                else
                {
                    addCard("29.99", Card.CardType.SILVER, String.valueOf(getLimit(Card.CardType.SILVER)), "0");
                }
            }
            if (in > 150000)
            {
                if (as >= in * 6)
                {
                    addCard("13.99", Card.CardType.GUNGA, "No Limit", "0");
                }
                else if (as < in * 4 && as > in / 4)
                {
                    addCard("15.99", Card.CardType.PLATINUM, String.valueOf(getLimit(Card.CardType.PLATINUM)), "0");
                }
                else
                {
                    addCard("17.99", Card.CardType.GOLD, String.valueOf(getLimit(Card.CardType.GOLD)), "0");
                }
            }

            //big assets low income = big spend, bad with money
            //small assets big income = low spend, good with money
            //mid assets mid income = mid with money, decent but not great


            FileOperations.writeToFile(FileOperations.users, Main.users);


            Iterator<Integer> keys = errorReasons.keys();
            Alert alert = new Alert(Alert.AlertType.WARNING);


            if (keys.hasNext())
            {
                StringBuilder errors = new StringBuilder();
                int size = 0;
                while (keys.hasNext())
                {
                    int element = keys.next();
                    /**
                     * Could use a stringbuilder but meh.
                     */
                    errors.append(errorReasons.get(element)).append("\n");
                    size++;
                }

                alert.setHeaderText("You have " + size + " errors!");
                alert.setContentText(errors.toString());
                alert.show();
                return;
            }

            Card cardApprovedFor = Main.userLoggedIn.getCards().get(0);

            Alert approved = new Alert(Alert.AlertType.INFORMATION);
            approved.setHeaderText("Credit Card Approved!");
            approved.setContentText("You have been approved! " +
                                "Card Type: " + cardApprovedFor.getCardType().getCardName() +
                                "Limit: " + cardApprovedFor.getLimit());

            Optional<ButtonType> await = approved.showAndWait();

            if (await.get().getText().equals("OK"))
            {
            }
        }
    }

    private void addCard(String apr, Card.CardType type, String limit, String bal) //too many lines to type
    {
        java.util.Random r = new java.util.Random();
        int num = r.nextInt(999) + 1000;
        Main.userLoggedIn.getCards().add(new Card(Main.userLoggedIn, apr, String.valueOf(num), type, new BigDecimal(limit), new BigDecimal(bal)));
    }

    private int getLimit(Card.CardType cardType)
    {
        int lowerBound = Integer.parseInt(cardType.getUpperLimit());
        int upperBound = Integer.parseInt(cardType.getLowerLimit());
        int limit = new Random().nextInt((lowerBound - upperBound) + 1) + upperBound;
        limit = (limit / 10) * 10;
        System.out.println(limit);
        return limit;
    }


    @FXML
    void onApplyHovered(MouseEvent event)
    {
        APPLY_BUTTON.setStyle(GungaBankConstants.BUTTON_HOVER_COLOR_STYLE);
    }

    @FXML
    void onClearButtonExited(MouseEvent event)
    {
        clearButton.setStyle(GungaBankConstants.BUTTON_COLOR_STYLE);
    }

    @FXML
    void onClearButtonHovered(MouseEvent event)
    {
        clearButton.setStyle(GungaBankConstants.BUTTON_HOVER_COLOR_STYLE);
    }

    @FXML
    void onRegisterExited(MouseEvent event)
    {
        APPLY_BUTTON.setStyle(GungaBankConstants.BUTTON_COLOR_STYLE);
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }
}

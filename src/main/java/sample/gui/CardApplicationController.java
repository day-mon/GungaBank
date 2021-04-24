package sample.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.actions.OnButtonExited;
import sample.actions.OnButtonHovered;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.Card;
import sample.core.objects.bank.User;
import sample.core.other.GungaObject;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.Checks;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class CardApplicationController implements Controller
{

    @GungaObject
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
    private User userLoggedIn;

    @GungaObject
    private ArrayList<TextField> textFields;

    @GungaObject
    private ArrayList<Button> buttons;

    @GungaObject
    private OnButtonHovered onButtonHovered;

    @GungaObject
    private OnButtonExited onButtonExited;

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
    public void initData(User user, StageHandler stageHandler, FileHandler fileHandler)
    {
        userLoggedIn = user;
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;
    }

    @FXML
    void initialize()
    {
        buttons = new ArrayList<>();
        textFields = new ArrayList<>();
        buttons.add(clearButton);
        buttons.add(APPLY_BUTTON);
        textFields.add(ANNUAL_INCOME);
        textFields.add(TOTAL_ASSETS);


        onButtonExited = new OnButtonExited(buttons);
        onButtonHovered = new OnButtonHovered(buttons);
    }

    @FXML
    void clearButtonClicked(ActionEvent event)
    {
        textFields.forEach(textField -> textField.setText(""));
    }

    @FXML
    void onApplyClicked(ActionEvent event)
    {

        Button buttonClicked = (Button) event.getSource();
        int currentErrors = 0;
        HashDictionary<Integer, String> errorReasons = new HashDictionary<>();

        for (TextField textField : textFields)
        {
            switch (textField.getId())
            {
                case "ANNUAL_INCOME":
                    if (textField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text = textField.getText();

                    if (text.length() > 12)
                    {
                        errorReasons.put(currentErrors++, "There is no public human in our domain with an income than 1 Trillion +");
                    }

                    if (!Checks.number(text))
                    {
                        errorReasons.put(currentErrors++, "The entry in the 'Annual Income' field is not a number!");
                    }
                    continue;
                case "TOTAL_ASSETS":
                    if (textField.getText().equals(""))
                    {
                        errorReasons.put(currentErrors++, "Annual Income Field is empty!");
                    }

                    String text1 = textField.getText();

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
        }
        // Evals users card

        boolean update = userLoggedIn.getCards().size() > 0;
        Card usersCard = eval();

        if (update) {
            userLoggedIn.getCards().set(0, usersCard);
        } else {
            userLoggedIn.getCards().add(usersCard);
        }
        fileHandler.writeToFile();


        Iterator<Integer> keys = errorReasons.keys();
        Alert alert = new Alert(Alert.AlertType.WARNING);


        if (keys.hasNext())
        {
            StringBuilder errors = new StringBuilder();
            int size = 0;
            while (keys.hasNext())
            {
                int element = keys.next();

                errors.append(errorReasons.get(element)).append("\n");
                size++;
            }

            alert.setHeaderText("You have " + size + " errors!");
            alert.setContentText(errors.toString());
            alert.show();
            return;
        }

        String[] msg = new String [] { "Limit", "Card type", "" };

        if (update) {
            msg[0] = "New limit";
            msg[1] = "New card type";
            msg[2] = "Refresh this page to see your new card.";
        }

        String limit = (usersCard.getLimit().toBigInteger().intValue() != -1) ? usersCard.getLimit().toPlainString() : "No Limit";
        Alert xd = new Alert(Alert.AlertType.INFORMATION);
        xd.setHeaderText("Credit Card Approved!");
        xd.setContentText("You have been approved!" +
                "\n" + msg[0] + ": " + limit +
                "\n" + msg[1] + ": " + usersCard.getCardType().getCardName() +
                "\n" + msg[2]);
        xd.show();
        stageHandler.close("credit_card_application");
    }

    private Card addCard(String apr, Card.CardType type, String limit) //too many lines to type
    {
        java.util.Random r = new java.util.Random();
        int num = r.nextInt(999) + 1000;
        return new Card(userLoggedIn, apr, String.valueOf(num), type, new BigDecimal(limit), new BigDecimal("0"));

    }

    private Card eval()
    {
        //big assets low income = big spend, bad with money
        //small assets big income = low spend, good with money
        //mid assets mid income = mid with money, decent but not great

        double as = Double.parseDouble(TOTAL_ASSETS.getText());
        double in = Double.parseDouble(ANNUAL_INCOME.getText());


        if (in <= 20000)
        {
            if (as >= in * 2)
            {
                return addCard("18.99", Card.CardType.GOLD, Card.CardType.GOLD.getLowerLimit());
            }
            else if (as < in * 2 && as > in / 2)
            {
                return addCard("20.99", Card.CardType.SILVER, Card.CardType.SILVER.getLowerLimit());
            }
            else
            {
                return addCard("22.99", Card.CardType.BRONZE, Card.CardType.BRONZE.getLowerLimit());
            }
        }
        else if (in > 20000 && in <= 150000)
        {
            if (as >= in * 4)
            {
                return addCard("29.99", Card.CardType.SILVER, Card.CardType.SILVER.getUpperLimit());
            }
            else if (as < in * 3 && as > in / 3)
            {
                return addCard("18.99", Card.CardType.GOLD, Card.CardType.GOLD.getUpperLimit());
            }
            else
            {
                return addCard("15.99", Card.CardType.PLATINUM, Card.CardType.PLATINUM.getLowerLimit());
            }
        }
        else
        {
            if (as >= in * 6)
            {
                return addCard("17.99", Card.CardType.GOLD, Card.CardType.GOLD.getUpperLimit());
            }
            else if (as < in * 4 && as > in / 4)
            {
                return addCard("15.99", Card.CardType.PLATINUM, Card.CardType.PLATINUM.getUpperLimit());
            }
            else
            {
                return addCard("13.99", Card.CardType.GUNGA, Card.CardType.GUNGA.getLowerLimit());
            }
        }
    }


    @Override
    public User getUser()
    {
        return userLoggedIn;
    }
}

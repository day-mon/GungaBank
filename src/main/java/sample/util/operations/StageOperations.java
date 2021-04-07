package sample.util.operations;

import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.GungaBankConstants;
import sample.Main;
import sample.core.objects.User;

import java.util.Optional;

public class StageOperations
{
    private static final Logger STAGE_LOGGER = LoggerFactory.getLogger(StageOperations.class);

    public static void cardApplicationStage()
    {
        try
        {
            Scene scene = new Scene(Main.loadFXML("/creditcardapp"));
            Stage creditAppStage = new Stage();
            creditAppStage.setTitle("Credit Card Application") ;
            creditAppStage.setResizable(false);
            creditAppStage.getIcons().add(new Image(Main.class.getResourceAsStream("/GungaBankLogo.PNG")));
            creditAppStage.initStyle(GungaBankConstants.DEFAULT_STAGE_STYLE);
            creditAppStage.setX(454);
            creditAppStage.setY(700);
            creditAppStage.setScene(scene);
            creditAppStage.show();
        }
        catch (Exception e)
        {
            STAGE_LOGGER.error("Error occurred: " + e.getMessage(), e);
        }
    }

    public static void creditCardStage()
    {
        try
        {
            if (Main.forms.containsKey("/cardpage"))
            {
                Main.forms.remove("/cardpage");
                Main.open("/cardpage",
                        "Dashboard",
                        GungaBankConstants.LENGTH_FOR_MAIN,
                        GungaBankConstants.WIDTH_FOR_MAIN,
                        GungaBankConstants.DEFAULT_STAGE_STYLE);
                return;
            }
            Main.open("/cardpage",
                    "Dashboard",
                    GungaBankConstants.LENGTH_FOR_MAIN,
                    GungaBankConstants.WIDTH_FOR_MAIN,
                    GungaBankConstants.DEFAULT_STAGE_STYLE);

        }
        catch (Exception e)
        {
            STAGE_LOGGER.error("Error occurred: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void switchToProfileScene()
    {
        try
        {
            if (Main.forms.containsKey("/profilepage"))
            {
                Main.forms.remove("/profilepage");
                Main.open("/profilepage",
                        "Dashboard",
                        GungaBankConstants.LENGTH_FOR_MAIN,
                        GungaBankConstants.WIDTH_FOR_MAIN,
                        GungaBankConstants.DEFAULT_STAGE_STYLE);
                return;
            }
            Main.open("/profilepage", "Dashboard", GungaBankConstants.LENGTH_FOR_MAIN, GungaBankConstants.WIDTH_FOR_MAIN, GungaBankConstants.DEFAULT_STAGE_STYLE);

        }
        catch (Exception e)
        {
            STAGE_LOGGER.error("Error occurred: " + e.getMessage(), e);
            e.printStackTrace();
        }
    }

    public static void switchToTransfersScene()
    {
        try
        {
            if (Main.forms.containsKey("/transfers"))
            {
                Main.forms.remove("/transfers");
                Main.open("/transfers", "Transfers",GungaBankConstants.LENGTH_FOR_MAIN, GungaBankConstants.WIDTH_FOR_MAIN, GungaBankConstants.DEFAULT_STAGE_STYLE);
                return;
            }
            Main.open("/transfers", "Transfers", GungaBankConstants.LENGTH_FOR_MAIN, GungaBankConstants.WIDTH_FOR_MAIN, GungaBankConstants.DEFAULT_STAGE_STYLE);

        }
        catch (Exception e)
        {
            System.out.printf("Error occured: %s", e.getMessage());
            e.printStackTrace();

        }
    }

    public static void switchToDashboardScene()
    {
        try
        {
            if (Main.forms.containsKey("/dashboard"))
            {
                Main.forms.remove("/dashboard");
                Main.open("/dashboard", "Dashboard", GungaBankConstants.LENGTH_FOR_MAIN, GungaBankConstants.WIDTH_FOR_MAIN, GungaBankConstants.DEFAULT_STAGE_STYLE);
                return;
            }
            Main.open("/dashboard", "Dashboard", GungaBankConstants.LENGTH_FOR_MAIN, GungaBankConstants.WIDTH_FOR_MAIN, GungaBankConstants.DEFAULT_STAGE_STYLE);

        }
        catch (Exception e)
        {
            STAGE_LOGGER.error("Error occurred: " + e.getMessage(), e);

        }
    }

    public static void initLogoutSequence()
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
                STAGE_LOGGER.error("Error occurred: " + e.getMessage(), e);
            }
        });
        delay.play();
    }

    public static void switchToCardPage(User user)
    {
        if (user.getCards().size() <= 0)
        {

            Alert popUp = new Alert(Alert.AlertType.WARNING,
                    "You do not have any credit cards with us, Would you like to apply?",
                    new ButtonType("Yes"),
                    new ButtonType("No"));
            Optional<ButtonType> result = popUp.showAndWait();

            if (result.get().getText().equals("Yes"))
            {
                StageOperations.cardApplicationStage();
            }
            return;
        }
        StageOperations.creditCardStage();
    }



}

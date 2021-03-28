package sample.util.operations;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Main;

import java.util.Optional;

public class StageOperations
{
    public static void switchToProfileScene()
    {
        try
        {
            if (!Main.forms.containsKey("/profilepage"))
            {
                Main.open("/profilepage", 1200, 800, StageStyle.UTILITY);
                return;
            }
            Main.open("/profilepage");

        }
        catch (Exception e)
        {
            System.out.printf("Error occured: %s", e.getMessage());
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void switchToTransfersScene()
    {
        try
        {
            if (!Main.forms.containsKey("/transfers"))
            {
                Main.open("/transfers", 1200, 800, StageStyle.UTILITY);
                return;
            }
            Main.open("/transfers");

        }
        catch (Exception e)
        {
            System.out.printf("Error occured: %s", e.getMessage());
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static void switchToDashboardScene()
    {
        try
        {
            if (!Main.forms.containsKey("/dashboard"))
            {
                Main.open("/dashboard", 1200, 800, StageStyle.UTILITY);
                return;
            }
            Main.open("/dashboard");

        }
        catch (Exception e)
        {
            System.out.printf("Error occured: %s", e.getMessage());
            e.printStackTrace();
            e.getMessage();
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
                System.out.printf("Error occured: %s", e.getMessage());
            }
        });
        delay.play();
    }

}

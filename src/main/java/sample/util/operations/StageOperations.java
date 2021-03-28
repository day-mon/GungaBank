package sample.util.operations;

import javafx.stage.StageStyle;
import sample.Main;

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

}

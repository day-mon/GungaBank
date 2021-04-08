package sample.handlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GungaBank;
import sample.Main;
import sample.core.objects.StageWrapper;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.IOException;
import java.util.Arrays;

public class StageHandler
{
    private static GungaBank gungaBank;
    private HashDictionary<String, StageWrapper> stages;
    private static Scene scene;


    public StageHandler(GungaBank gungaBank)
    {
         this.gungaBank = gungaBank;
         this.stages = generateStageMap();

    }

    private HashDictionary<String, StageWrapper> generateStageMap()
    {
        ArrayList<StageWrapper> stageList = new ArrayList<>();
        stageList.addAll(
                Arrays.asList(
                    new StageWrapper("Dashboard"),
                    new StageWrapper("Credit Card"),
                    new StageWrapper("Profile"),
                    new StageWrapper("Transfers"),
                    new StageWrapper("Register",  700, 835),
                    new StageWrapper("Login", 700, 500),
                    new StageWrapper("Credit Card Application", 454, 700)
                ));

        HashDictionary<String, StageWrapper> stageDict = new HashDictionary<>();

        for (StageWrapper stages : stageList)
        {
            stageDict.put(stages.getResourceName(), stages);
        }

        gungaBank.getLogger().info("{} has been loaded successfully!", stageList);
        return stageDict;
    }

    public void switchToStage(String stageToSwitchTo)
    {
        try
        {
            this.stages.get("/" + stageToSwitchTo).getStage().show();
        }
        catch (Exception e)
        {

        }


    }



    public void start(Stage primaryStage)
    {
        try
        {
            this.stages.get("/login").getStage().show();
        }
        catch (Exception e)
        {
            gungaBank.getLogger().error("Error occurred: {}", e.getMessage(), e);
        }
    }

    private static Parent loadFXML(String title) throws IOException
    {
        return new FXMLLoader(Main.class.getResource(title + ".fxml")).load();
    }
}

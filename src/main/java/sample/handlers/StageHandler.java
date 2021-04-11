package sample.handlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.GungaBank;
import sample.core.objects.StageWrapper;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

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

        gungaBank.getLogger().info("{} has been loaded successfully!", stageList.getClass().getName());
        return stageDict;
    }

    public void switchToStage(String stageToSwitchTo)
    {
        Iterator<StageWrapper> s = stages.elements();

        while (s.hasNext())
        {
            StageWrapper element = s.next();
            Stage stg = element.getStage();
            if (stg.isShowing())
            {
                System.out.println(stg.getTitle() + " is showing");
                stg.hide();
                stages.get(stageToSwitchTo).getStage().show();
                break;
            }
        }
        /*
        try
        {
            StageWrapper wrap = stages.get("/"+stageToSwitchTo);
            Stage primaryStage = wrap.getStage();
            scene = new Scene(loadFXML(wrap.getResourceName()));
            primaryStage.setScene(scene);
        }
        catch (Exception e)
        {
            gungaBank.getLogger().error("{} cannot be loaded", stageToSwitchTo, e);
        }
        */

    }



    public void start(Stage primaryStage)
    {
        try
        {
            StageWrapper wrap = stages.get("/login");
            primaryStage = stages.get("/login").getStage();
            scene = new Scene(loadFXML("/login"), wrap.getWidth(), wrap.getHeight());
            primaryStage.show();
        }
        catch (Exception e)
        {
            gungaBank.getLogger().error("Error occurred: {}", e.getMessage(), e);
        }
    }

    private Parent loadFXML(String title) throws IOException
    {
        return new FXMLLoader(getClass().getResource(title + ".fxml")).load();
    }
}

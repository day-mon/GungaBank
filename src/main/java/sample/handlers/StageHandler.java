package sample.handlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.GungaBank;
import sample.core.interfaces.Controller;
import sample.core.objects.StageWrapper;
import sample.core.objects.bank.User;
import sample.gui.LoginPageController;
import sample.util.structures.ArrayList;
import sample.util.structures.HashDictionary;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class StageHandler
{
    private final static Logger LOGGER = LoggerFactory.getLogger(StageHandler.class);
    private HashDictionary<String, StageWrapper> stages;
    private GungaBank gungaBank;
    private static Scene scene;

    public StageHandler(GungaBank gungaBank) {
        this.stages = generateStageMap();
        this.gungaBank = gungaBank;
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
                        new StageWrapper("Register", 700, 835),
                        new StageWrapper("Login", 700, 550), // 300 + 409  496+500
                        new StageWrapper("Credit Card Application", 700, 454)
                ));

        HashDictionary<String, StageWrapper> stageDict = new HashDictionary<>();

        for (StageWrapper stages : stageList)
        {
            stageDict.put(stages.getResourceName(), stages);
        }

        LOGGER.info("{} has been loaded successfully!", stageList.getClass().getSimpleName());
        return stageDict;
    }

    public FXMLLoader getLoader(String stage)
    {
        return stages.get(stage).getLoader();
    }

    public void switchToStage(String stageToSwitchTo)
    {
        Iterator<StageWrapper> s = stages.elements();
        String resource_name = "/" + stageToSwitchTo;

        while (s.hasNext())
        {
            StageWrapper element = s.next();
            Stage stg = element.getStage();
            if (stg.isShowing())
            {
                try
                {
                    stg.hide();
                    StageWrapper wrap = stages.get(resource_name);
                    FXMLLoader loader = getLoader(resource_name);
                    loader.setController(null);
                    loader.setRoot(null);
                    Parent root = wrap.getLoader().load();
                    Controller controller = getLoader(resource_name).getController();
                    controller.initData(null, gungaBank.getStageHandler(), gungaBank.getFileHandler());
                    scene = new Scene(root, wrap.getHeight(), wrap.getWidth());
                    Stage stage = wrap.getStage();
                    stage.setScene(scene);
                    stage.show();

                    break;
                }
                catch (Exception e)
                {
                    LOGGER.error("Error occurred: {}", e.getCause(), e);
                }
            }
        }
    }

    public void switchToStage(User user, String stageToSwitchTo)
    {
        Iterator<StageWrapper> s = stages.elements();
        String resource_name = "/" + stageToSwitchTo;

        while (s.hasNext())
        {
            StageWrapper element = s.next();
            Stage stg = element.getStage();
            if (stg.isShowing())
            {
                try
                {
                    stg.hide();
                    StageWrapper wrap = stages.get(resource_name);
                    FXMLLoader loader = getLoader(resource_name);
                    loader.setController(null);
                    loader.setRoot(null);
                    Parent root = wrap.getLoader().load();
                    Controller controller = getLoader(resource_name).getController();
                    controller.initData(user, gungaBank.getStageHandler(), gungaBank.getFileHandler());
                    scene = new Scene(root, wrap.getHeight(), wrap.getWidth());
                    Stage stage = wrap.getStage();
                    stage.setScene(scene);
                    stage.show();

                    break;
                }
                catch (Exception e)
                {
                    LOGGER.error("Error occurred: {}", e.getCause(), e);
                }
            }
        }
    }

    public void openNewScene(String newSceneString, User user)
    {
        try
        {
            String resourceName = "/" + newSceneString;
            FXMLLoader loader = getLoader(resourceName);
            StageWrapper wrap = stages.get(resourceName);


            loader.setRoot(null);
            loader.setController(null);

            Parent root = wrap.getLoader().load();
            Controller controller = getLoader(resourceName).getController();
            controller.initData(user, gungaBank.getStageHandler(), gungaBank.getFileHandler());
            Scene newScene = new Scene(root, wrap.getHeight(), wrap.getWidth());
            Stage stage = wrap.getStage();
            stage.setScene(newScene);
            stage.show();


        }
        catch (Exception e)
        {
            LOGGER.error("Error occurred: {} ", e.getCause(), e);
        }
    }

    public void switchToStage(String stageToSwitchTo, Parent root)
    {

        Iterator<StageWrapper> s = stages.elements();
        String resource_name = "/" + stageToSwitchTo;

        while (s.hasNext())
        {
            StageWrapper element = s.next();
            Stage stg = element.getStage();
            StageWrapper wrap = stages.get(resource_name);
            if (stg.isShowing())
            {
                try
                {
                    if (Objects.equals(stg, wrap.getStage())) return;
                    stg.hide();
                    scene = new Scene(root, wrap.getHeight(), wrap.getWidth());
                    Stage stage = wrap.getStage();
                    stage.setScene(scene);
                    stage.show();

                    break;
                }
                catch (Exception e)
                {
                    LOGGER.error("Error occurred: {}", e.getCause(), e);
                }
            }
        }
    }


    public void close(String name)
    {
        Stage wrap = stages.get("/" + name).getStage();
        wrap.close();
    }


    public void start(Stage primaryStage)
    {
        try
        {
            StageWrapper wrap = stages.get("/login");
            primaryStage = stages.get("/login").getStage();
            scene = new Scene(loadFXML("/login"), wrap.getHeight(), wrap.getWidth());

            LoginPageController controller = wrap.getLoader().getController();
            controller.initData(null, gungaBank.getStageHandler(), gungaBank.getFileHandler());

            primaryStage.setScene(scene);
            primaryStage.show();


            LOGGER.info("Main scene has loaded successfully!");
        }
        catch (Exception e)
        {
            LOGGER.error("Error occurred: {}", e.getCause(), e);
        }
    }

    private Parent loadFXML(String title) throws IOException
    {
        return getLoader(title).load();
    }


}
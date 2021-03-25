package sample;

/**
 * JavaFX Imports
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.core.objects.User;
import sample.util.ArrayList;
import sample.util.HashDictionary;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Self Imports
 * <p>
 * Java Imports
 */

/**
 * Java Imports
 */

public class Main extends Application
{
    public static int ins = 0;
    private static Scene scene;
    public static HashDictionary<String, Stage> stages;
    public static HashDictionary<String, User> users = new HashDictionary<>();
    public static User userLoggedIn;
    public static HashDictionary<String, Stage> forms = new HashDictionary<>();
    public static HashDictionary<String, ArrayList<String>> fuck;


    @Override
    public void start(Stage stage) throws Exception
    {
        FileOperations.loadInformation();

        users.put("fuck", new User("Josh", "Peck", "jpeck@gmail.com", new Date(), "2142323232", "239239232", StringOperations.hashPassword("fuck")));
        scene = new Scene(loadFXML("/loginpage"), 700, 500);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/icons8_department_96px_2.png")));
        stage.setTitle("Gunga Bank");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.show();
        forms.put("/loginpage", stage);
    }

    public static Parent loadFXML(String title) throws IOException
    {

        return new FXMLLoader(Main.class.getResource(title + ".fxml")).load();
    }

    public static void open(String toOpen)
    {
        Iterator<Stage> s = forms.elements();

        while (s.hasNext())
        {
            Stage element = s.next();
            if (element.isShowing())
            {
                element.hide();
                forms.get(toOpen).show();
                break;
            }
        }



    }

    public static void open(String toOpen, int length, int width, StageStyle style) throws IOException
    {
        if (!forms.containsKey(toOpen))
        {
            scene = new Scene(loadFXML(toOpen), length, width);
            Stage stg = new Stage();
            stg.setScene(scene);
            stg.setResizable(false);
            stg.initStyle(style);
            forms.put(toOpen, stg);
        }
        open(toOpen);
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}

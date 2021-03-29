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
import sample.util.structures.HashDictionary;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;

import java.awt.*;
import java.awt.Desktop;
import java.io.IOException;
import java.util.Date;
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
    // static abuse :(
    private static Scene scene;
    public static HashDictionary<String, User> users = new HashDictionary<>();
    public static HashDictionary<String, Stage> forms = new HashDictionary<>();
    public static User userLoggedIn;


    @Override
    public void start(Stage stage) throws Exception
    {
        FileOperations.loadInformation();

        users.put("123", new User("Josh", "Peck", "123", new Date(), "2142323232", "239239232", StringOperations.hashPassword("123")));
        scene = new Scene(loadFXML("/loginpage"), 700, 500);
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/LOGO.PNG")));
        stage.setTitle("Gunga Bank");
        stage.setScene(scene);
        stage.initStyle(StageStyle.DECORATED);
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

    public static void open(String toOpen, String title, int length, int width, StageStyle style) throws IOException
    {
        if (!forms.containsKey(toOpen))
        {
            scene = new Scene(loadFXML(toOpen), length, width);
            Stage stg = new Stage();
            stg.getIcons().add(new Image(Main.class.getResourceAsStream("/LOGO.PNG")));
            stg.initStyle(StageStyle.DECORATED);
            stg.setTitle(title);
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

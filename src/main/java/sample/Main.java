package sample;

/**
 * JavaFX Imports
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.core.objects.User;
import sample.util.operations.FileOperations;
import sample.util.operations.StringOperations;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * Self Imports
 * <p>
 * Java Imports
 */
/**
 * Java Imports
 */

/**
 * TODO: 1. In RegisterPageController, check to see if email exist and if it does direct them to the login page -------------------
 *       *Done* 2. LoginPageController add logic for logging in. With error checking, also check to see if a user has bank accounts before logging them in. If they dont redirect them (I will make the UI for that when I wake up)
 *       3. Check to see if both data structures are <br> FULLY </br> working. If they are replace the them where we are using the Java ones
 *       *Done* 4. Someone learn how to make it so we can effectively hide Scenes.
 *       5. Delete useless files in sample/dependencies
 *       6. Double check all files in sample/core/objects
 *       7. Find a way to clean up the start method @ line 39.
 *       8. Clean up RegisterPageController with AlertShorter and add regex checks to string operations?*
 *       9. Comment code in all files and rename variables if not descriptive enough
 *       10. Create more object files that we will need
 *       11. when u close register page the program should take u back to login
 *
 */

public class Main extends Application {
    private static Scene scene;
    public static HashMap<String, Stage> stages;
    public static HashMap<String, User> users = new HashMap<>();
    public static User userLoggedIn;
    public static HashMap<String, Stage> forms = new HashMap<>();

    /*
    public static void setRoot(String fxmlHide, String fxmlSwitch, int l, int w, boolean resize, StageStyle style) throws IOException {
        scene = new Scene(loadFXML(fxmlSwitch), l, w);
        stages.get(fxmlHide).hide();
        Stage stg = new Stage();
        stg.setScene(scene);
        stg.setResizable(resize);
        stg.initStyle(style);
        stg.show();
        stages.putIfAbsent(fxmlSwitch, stg);

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }



 */
    /*
    @Override
    public void start(Stage primaryStage) throws Exception {


         //On start load all of things we had before.

      FileOperations.loadInformation();

        stages = new HashMap<>();
        scene = new Scene(loadFXML("gui/loginpage"), 700, 500);
        primaryStage.setTitle("Gunga fart");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.show();
        stages.put("gui/loginpage", primaryStage);
    }



    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    */
    @Override
    public void start(Stage stage) throws Exception {
        FileOperations.loadInformation();
        users.put("fuck", new User("fuck", "fuck", "fuck", new Date(), "2142323232", "239239232", StringOperations.hashPassword("fuck")));
        scene = new Scene(loadFXML("/loginpage"), 700, 500);
        stage.setTitle("Gunga Bank");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.show();
        forms.put("/loginpage", stage);
    }

    public static Parent loadFXML(String title) throws IOException {

        return new FXMLLoader(Main.class.getResource(title + ".fxml")).load();
    }

    public static void open(String toOpen) {
        for (Stage s : forms.values()) {
            if (s.isShowing()) {
                s.hide();
                forms.get(toOpen).show();
                break;
            }
        }
    }

    public static void open(String toOpen, int length, int width, StageStyle style) throws IOException {
        if (!forms.containsKey(toOpen)) {
            scene = new Scene(loadFXML(toOpen), length, width);
            Stage stg = new Stage();
            stg.setScene(scene);
            stg.setResizable(false);
            stg.initStyle(style);
            forms.put(toOpen, stg);
        }
        open(toOpen);
    }


    public static void main(String[] args) {



        launch(args);

    }
}

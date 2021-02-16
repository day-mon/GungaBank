package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.core.objects.User;
import sample.util.operations.FileOperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    private static Scene scene;
    public static java.util.ArrayList<Stage> stages;
    public static HashMap<String, User> users = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        /**
         * On start load all of things we had before.
         */
        try {
            ArrayList<File> files = FileOperations.getAllFilesWithExt(new File("src\\sample\\files\\"), "ser");
            int serFiles = 0;
            try {
                serFiles = files.size();
            } catch (NullPointerException npex) {
                System.out.println("No files exist and serializer input failed.");
            }

            for (int i = 0; i < serFiles; i++) {
                FileInputStream fis = new FileInputStream(files.get(i).getAbsolutePath());
                ObjectInputStream ois = new ObjectInputStream(fis);

                String fileName = files.get(i).getName().split("\\.")[0];

                switch (fileName) {
                    case "users":
                        users = (HashMap<String, User>) ois.readObject();
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + fileName);
                }


            }
        } catch (IOException | ClassNotFoundException e) {};


        stages = new ArrayList<>();
        scene = new Scene(loadFXML("gui/loginpage"), 700, 500);
        primaryStage.setTitle("Gunga Niggas");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        primaryStage.show();
        stages.add(primaryStage);

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void setRoot(String fxml, int l, int w, boolean resize, StageStyle style, int index) throws IOException {
        scene = new Scene(loadFXML(fxml), l, w);
        stages.get(index).hide();
        Stage stg = new Stage();
        stg.setScene(scene);
        stg.setResizable(resize);
        stg.initStyle(style);
        stg.show();

    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {



            launch(args);

    }
}

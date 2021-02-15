package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    private static Scene scene;
    public static java.util.ArrayList<Stage> stages;

    @Override
    public void start(Stage primaryStage) throws Exception {

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
        launch(args)    ;
    }
}

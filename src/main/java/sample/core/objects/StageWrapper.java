package sample.core.objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.GungaBankConstants;
import sample.Main;

import java.io.IOException;

public class StageWrapper
{
    private Stage stage;
    private String name;
    private String resourceName;
    private int height;
    private int width;
    private StageStyle style;

    public StageWrapper(String name, int width, int height)
    {
        this.height = height;
        this.width = width;
        this.name = name;
        this.resourceName = new StringBuilder(name)
                .insert(0, "/")
                .toString()
                .toLowerCase()
                .replaceAll("\\s", "_");
        Stage stage = new Stage();
                stage.setTitle(name);
                stage.setResizable(false);
                stage.setWidth(width);
                stage.setHeight(height);
                stage.initStyle(style);
        this.stage = stage;

    }

    public StageWrapper(String name)
    {
        this.name = name;
        this.resourceName = new StringBuilder(name)
                .insert(0, "/")
                .toString()
                .toLowerCase()
                .replaceAll("\\s", "_");
        Stage stage = new Stage();
        Image image = new Image(Main.class.getResourceAsStream("/GungaBankLogo.PNG"));
        stage.setTitle(name);
            stage.setResizable(false);
            stage.getIcons().add(image);
            stage.setWidth(GungaBankConstants.LENGTH_FOR_MAIN);
            stage.setHeight(GungaBankConstants.WIDTH_FOR_MAIN);
            stage.initStyle(style);
        this.stage = stage;
    }

    public Stage getStage()
    {
        return stage;
    }

    public String getName()
    {
        return name;
    }

    public String getResourceName()
    {

       return resourceName;
    }

    private static Parent loadFXML(String title) throws IOException
    {
        return new FXMLLoader(Main.class.getResource(title + ".fxml")).load();
    }
}
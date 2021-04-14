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
    private FXMLLoader loader;
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
        this.stage = new Stage();
        Image image = new Image(Main.class.getResourceAsStream("/GungaBankLogo.PNG"));
        stage.getIcons().addAll(image);
        stage.setTitle(name);
        stage.setResizable(false);
        stage.setWidth(width);
        stage.setHeight(height);
        stage.initStyle(GungaBankConstants.DEFAULT_STAGE_STYLE);
        this.loader = new FXMLLoader(Main.class.getResource(resourceName + ".fxml"));
        this.style = GungaBankConstants.DEFAULT_STAGE_STYLE;


    }

    public StageWrapper(String name)
    {
        this.name = name;
        this.resourceName = new StringBuilder(name)
                .insert(0, "/")
                .toString()
                .toLowerCase()
                .replaceAll("\\s", "_");
        this.stage = new Stage();
        Image image = new Image(Main.class.getResourceAsStream("/GungaBankLogo.PNG"));
        stage.setTitle(name);
        stage.setResizable(false);
        stage.getIcons().add(image);
        stage.setWidth(GungaBankConstants.WIDTH_FOR_MAIN);
        stage.setHeight(GungaBankConstants.HEIGHT_FOR_MAIN);
        stage.initStyle(GungaBankConstants.DEFAULT_STAGE_STYLE);
        this.loader = new FXMLLoader(Main.class.getResource(resourceName + ".fxml"));
        this.width = GungaBankConstants.WIDTH_FOR_MAIN;
        this.height = GungaBankConstants.HEIGHT_FOR_MAIN;
        this.style = GungaBankConstants.DEFAULT_STAGE_STYLE;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public FXMLLoader getLoader()
    {
        return loader;
    }

    public StageStyle getStyle()
    {
        return style;
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
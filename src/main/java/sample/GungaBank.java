package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.handlers.FileHandler;

import java.time.LocalDateTime;

public class GungaBank extends Application
{
    private final Logger logger;
    private final LocalDateTime bankAppStartTime;
    private final FileHandler fileHandler;

    public GungaBank()
    {
        this.logger = LoggerFactory.getLogger(GungaBank.class);
        this.fileHandler = new FileHandler(this);
        this.bankAppStartTime = LocalDateTime.now();

    }

    public void build()
    {

    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {

    }

    public Logger getLogger() {
        return logger;
    }
}

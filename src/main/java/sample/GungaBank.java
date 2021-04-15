package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;

import java.nio.file.Paths;
import java.time.LocalDateTime;


public class GungaBank extends Application
{
    private static StageHandler stageHandler;
    private static FileHandler fileHandler;
    private final Logger GUNGA_LOGGER;
    private final LocalDateTime bankAppStartTime;


    public GungaBank() throws Exception
    {
        GUNGA_LOGGER = LoggerFactory.getLogger(GungaBank.class);
        fileHandler = new FileHandler();
        stageHandler = new StageHandler(this);
        bankAppStartTime = LocalDateTime.now();
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
        GUNGA_LOGGER.info("======================== [ Initializing GungaBank ] ========================");
        GUNGA_LOGGER.info("Start time:           " + bankAppStartTime);
        GUNGA_LOGGER.info("Operating System:     " + System.getProperty("os.name"));
        GUNGA_LOGGER.info("Java Version:         " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
        GUNGA_LOGGER.info("Java VM Version:      " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
        GUNGA_LOGGER.info("Java Home:            " + System.getProperty("java.home"));
        GUNGA_LOGGER.info("Current Directory:    " + Paths.get("").toAbsolutePath());
        GUNGA_LOGGER.info("Memory:               " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "MB");
        stageHandler.start(primaryStage);
    }

    @Override
    public void stop() throws Exception
    {
        GUNGA_LOGGER.warn("======================== [ Stopping GungaBank ] ========================");
        System.exit(1);
    }

    public Logger getLogger()
    {
        return GUNGA_LOGGER;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public StageHandler getStageHandler() {
        return stageHandler;
    }



}

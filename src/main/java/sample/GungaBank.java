package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.core.objects.bank.User;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.operations.StringOperations;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;


public class GungaBank extends Application
{
    /**
     * Using Simpleton pattern
     */
    private static StageHandler stageHandler;
    private static FileHandler fileHandler;
    private final Logger GUNGA_LOGGER;
    private final LocalDateTime bankAppStartTime;


    public GungaBank()
    {
        GUNGA_LOGGER = LoggerFactory.getLogger(GungaBank.class);
        fileHandler = new FileHandler();
        stageHandler = new StageHandler(this);
        bankAppStartTime = LocalDateTime.now();
        loadDemoUser();
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
    public void start(Stage primaryStage)
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
    public void stop()
    {
        GUNGA_LOGGER.warn("======================== [ Stopping GungaBank ] ========================");
        System.exit(1);
    }


    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public StageHandler getStageHandler() {
        return stageHandler;
    }

    private void loadDemoUser()
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Would you like demo users?: ");
        String response = in.next();

        if (response.toUpperCase().startsWith("Y"))
        {
            fileHandler.getUsers().put("1", new User("Ryan", "Ruffing", "123", LocalDateTime.now().minusYears(21), "5079283765", "123456789", StringOperations.hashPassword("123")));
            fileHandler.getUsers().put("2", new User("Gavin", "Heinrichs", "123", LocalDateTime.now().minusYears(17), "2542665103", "123456789", StringOperations.hashPassword("123")));
            fileHandler.getUsers().put("3", new User("Stephen", "Ohl", "123", LocalDateTime.now().minusYears(45), "2158922313", "123456789", StringOperations.hashPassword("123")));
            GUNGA_LOGGER.info("There was 3 demo users loaded with the user-names [{}, {}, {}] with the password of 123", 1, 2, 3);
        }
    }



}

package sample.actions;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.core.interfaces.Controller;
import sample.core.objects.bank.User;
import sample.handlers.FileHandler;
import sample.handlers.StageHandler;
import sample.util.structures.ArrayList;

import java.util.Optional;


public class OnIconClicked implements EventHandler<MouseEvent> {
    private final ArrayList<ImageView> icons;
    private final Logger ICON_HANDLER;

    private StageHandler stageHandler;
    private FileHandler fileHandler;
    private User user;

    public OnIconClicked(ArrayList<ImageView> iconsPassing, User user, StageHandler stageHandler, FileHandler fileHandler) {
        icons = new ArrayList<>();
        for (ImageView icons : iconsPassing) {
            icons.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.icons.add(icons);
        }
        ICON_HANDLER = LoggerFactory.getLogger(OnIconClicked.class);
        this.user = user;
        this.stageHandler = stageHandler;
        this.fileHandler = fileHandler;
    }

    /**
     * Invoked when a specific event of the type for which this handler is
     * registered happens.
     *
     * @param event the event which occurred
     */
    @Override
    public void handle(MouseEvent event)
    {
        ImageView imageClicked = (ImageView)event.getSource();
        String imageClickedName = imageClicked.getId();
        String stageToSwitch = "";
        switch (imageClickedName)
        {

            case "transferIcon":
                stageToSwitch = "transfers";
                Parent transRoot = preLoadData(stageToSwitch);
                stageHandler.switchToStage(stageToSwitch, transRoot);
                break;
            case "homeIcon":
                stageToSwitch = "dashboard";
                Parent homeRoot = preLoadData(stageToSwitch);
                stageHandler.switchToStage(stageToSwitch, homeRoot);
                break;
            case "creditCardIcon":
                stageToSwitch = "credit_card";
                if (user.getCards().size() == 0) {
                    stageHandler.openNewScene("credit_card_application", user);
                } else {
                    Parent cardRoot = preLoadData(stageToSwitch);
                    stageHandler.switchToStage(stageToSwitch, cardRoot);
                }
                break;
            case "profileIcon":
                stageToSwitch = "profile";
                Parent profileRoot = preLoadData(stageToSwitch);
                stageHandler.switchToStage(stageToSwitch, profileRoot);
                break;
            case "logoutIcon":
                Alert alert = new Alert(Alert.AlertType.WARNING,
                        "You have 5 seconds! To respond or you will be logged out!",
                        new ButtonType("Abort!", ButtonBar.ButtonData.CANCEL_CLOSE),
                        new ButtonType("Okay!", ButtonBar.ButtonData.OK_DONE));


                Optional<ButtonType> result = alert.showAndWait();

                if (result.get().getText().equals("Abort!"))
                {
                    break;
                }


                PauseTransition delay = new PauseTransition(Duration.seconds(5.0));
                delay.setOnFinished(event1 ->
                {
                    try {
                        String stage = "login";
                        Parent login = preLoadData(stage);
                        stageHandler.switchToStage("login", login);
                    }
                    catch (Exception e)
                    {
                        ICON_HANDLER.error("Error occurred: {}", e.getCause(), e);
                    }
                });
                delay.play();
                break;
            default:
                ICON_HANDLER.error("Icon not found. You may have to add it to the switch case {@see OnIconClicked.java}");

        }
    }

    private Parent preLoadData(String stage)
    {
        try
        {
            FXMLLoader loader = stageHandler.getLoader("/" + stage);

            loader.setRoot(null);
            loader.setController(null);

            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.initData(user, stageHandler, fileHandler);
            return root;
        }
        catch (Exception e)
        {
            ICON_HANDLER.error("Error occurred: {}", e.getCause(), e);
        }
        return null;
    }
}

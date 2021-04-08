package sample.actions;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.GungaBank;
import sample.util.structures.ArrayList;

import java.util.Optional;


public class OnIconClicked implements EventHandler<MouseEvent>
{
    private final ArrayList<ImageView> icons;
    private final Logger ICON_HANDLER;
    private final GungaBank gungaBank;

    public OnIconClicked(ArrayList<ImageView> iconsPassing, GungaBank gungaBank)
    {
        ICON_HANDLER = LoggerFactory.getLogger(OnIconClicked.class);
        icons = new ArrayList<>();
        this.gungaBank = gungaBank;

        for (ImageView icons : iconsPassing)
        {
            icons.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
            this.icons.add(icons);
        }
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


        switch (imageClickedName)
        {
            case "transferIcon":
                gungaBank.getStageHandler().switchToStage("transfers");
                break;
            case "homeIcon":
                gungaBank.getStageHandler().switchToStage("dashboard");
                break;
            case "creditCardIcon":
                gungaBank.getStageHandler().switchToStage("card_page");
                break;
            case "profileIcon":
                gungaBank.getStageHandler().switchToStage("profile");
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
                    try
                    {
                        gungaBank.getStageHandler().switchToStage("login");
                    }
                    catch (Exception e)
                    {
                        ICON_HANDLER.error("Error occurred: {}", e.getMessage(), e);
                    }
                });
                delay.play();
                break;
            default:
                ICON_HANDLER.error("Icon not found, we will add it to the list. You may have to add it to the switch case {@see OnIconClicked.java}");
                icons.add(imageClicked);

        }
    }
}

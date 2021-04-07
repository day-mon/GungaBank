package actions;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sample.GungaBankConstants;
import sample.util.structures.ArrayList;

public class onButtonHovered implements EventHandler<MouseEvent>
{
    private ArrayList<Button> buttons;
    private Logger BUTTON_HANDLER;

    public onButtonHovered(ArrayList<Button> button)
    {
        BUTTON_HANDLER = LoggerFactory.getLogger(onButtonHovered.class);
        button.forEach(buttons::add);
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
        Button button = (Button)event.getSource();
        if (buttons.contains(button))
        {
            button.setStyle(GungaBankConstants.BUTTON_HOVER_COLOR_STYLE);
        }
        else
        {
            BUTTON_HANDLER.error("Something went wrong! Button doesnt exist, dont worry we are adding it now!");
            buttons.add(button);
        }
    }
}
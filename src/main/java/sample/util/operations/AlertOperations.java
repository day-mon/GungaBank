package sample.util.operations;

import javafx.scene.control.Alert;

public class AlertOperations {


    public static void AlertShortner(String alertType, String title, String headerText, String description)
    {
        Alert alertToSend;

        switch (alertType.toLowerCase()) {
            case "good":
                alertToSend = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case "bad":
                alertToSend = new Alert(Alert.AlertType.ERROR);
                break;
            case "info":
                alertToSend = new Alert(Alert.AlertType.INFORMATION);
                break;
            default:
                alertToSend = new Alert(Alert.AlertType.NONE);
        }

        alertToSend.setTitle(title);
        alertToSend.setHeaderText(headerText);
        alertToSend.setContentText(description);
        alertToSend.show();




    }

    public static void AlertShortner(String alertType,  String headerText, String description)
    {
        Alert alertToSend;

        switch (alertType) {
            case "good":
                alertToSend = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case "bad":
                alertToSend = new Alert(Alert.AlertType.ERROR);
                break;
            case "info":
                alertToSend = new Alert(Alert.AlertType.INFORMATION);
                break;
            default:
                alertToSend = new Alert(Alert.AlertType.NONE);
        }
        alertToSend.setHeaderText(headerText);
        alertToSend.setContentText(description);
        alertToSend.show();

    }

}

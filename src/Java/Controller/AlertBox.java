package Java.Controller;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        Pane pane = new Pane();
        pane.getChildren().add(label);

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.showAndWait();
    }
}

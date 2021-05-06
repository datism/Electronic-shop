package Java.Controller;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Locale;

public class AlertBox {
    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title.toUpperCase(Locale.ROOT));
        window.setMinWidth(250);

        Label label = new Label(message.toUpperCase());
        Button button = new Button("Dong cua so");
        button.setOnAction( e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        scene.getStylesheets().add("/Resource/css/Style.css");
        window.showAndWait();
    }
}

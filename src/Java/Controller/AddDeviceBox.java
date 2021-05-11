package Java.Controller;

import Java.Dao.Database;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class AddDeviceBox {

    private final Database update;

    private Stage window = new Stage();
    private GridPane root = new GridPane();

    private Label nameLabel = new Label("Ten:");
    private Label hsxLabel = new Label("Hang san xuat:");
    private Label modelLabel = new Label("Model:");
    private Label priceLabel = new Label("Gia:");
    private Label quantityLabel = new Label("So luong:");
    private Label p1Label;
    private Label p2Label;
    private Label p3Label;

    private TextField nameTextField = new TextField();
    private TextField hsxTextField = new TextField();
    private TextField modelTextField = new TextField();
    private TextField priceTextField = new TextField();
    private TextField quantityTextField = new TextField();
    private TextField p1TextField = new TextField();
    private TextField p2TextField = new TextField();
    private TextField p3TextField = new TextField();
    private Button addButton = new Button("Them");

    AddDeviceBox() {
        window.initModality(Modality.APPLICATION_MODAL);
        root.add(nameLabel, 0, 0);
        root.add(hsxLabel, 0,1);
        root.add(modelLabel, 0,2);
        root.add(priceLabel, 0,3);
        root.add(quantityLabel, 0 , 4);

        root.add(nameTextField, 1, 0);
        root.add(hsxTextField, 1,1);
        root.add(modelTextField, 1,2);
        root.add(priceTextField, 1,3);
        root.add(quantityTextField, 1 , 4);
        root.add(p1TextField, 1, 5);
        root.add(p2TextField, 1, 6);
        root.add(p3TextField, 1,7);

        root.add(addButton, 1, 8);

        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(50));

        this.update = new Database();
    }

    public void adddPhone() {
        this.p1Label = new Label("Kich thuoc:");
        this.p2Label = new Label("Pin:");
        this.p3Label = new Label("Do phan giai camera:");

        root.add(p1Label, 0, 5);
        root.add(p2Label, 0, 6);
        root.add(p3Label, 0,7);


        addButton.setOnAction(actionEvent -> {
            String name = nameTextField.getText();
            String hsx = hsxTextField.getText();
            String model = modelTextField.getText();
            float size;
            int price;
            int quantity;
            int battery;
            float rs;
            try {
                size = Float.parseFloat(p1TextField.getText());
                price = Integer.parseUnsignedInt(priceTextField.getText());
                quantity = Integer.parseUnsignedInt(quantityTextField.getText());
                battery = Integer.parseInt(p2TextField.getText());
                rs = Float.parseFloat(p3TextField.getText());
            } catch (NumberFormatException ex) {
                AlertBox.display("loi dinh dang", "loi dinh dang");
                return;
            }
            try {
                update.AddPhone(name, hsx, model, size, battery, rs, price, quantity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            window.close();
        });
        Scene scene = new Scene(root);
        window.setScene(scene);
        scene.getStylesheets().add("/Resource/css/Style.css");
        window.showAndWait();
    }

    public void adddLaptop() {
        this.p1Label = new Label("Cpu:");
        this.p2Label = new Label("Ram:");
        this.p3Label = new Label("O cung:");

        root.add(p1Label, 0, 5);
        root.add(p2Label, 0, 6);
        root.add(p3Label, 0,7);


        addButton.setOnAction(actionEvent -> {
            String name = nameTextField.getText();
            String hsx = hsxTextField.getText();
            String model = modelTextField.getText();
            String cpu = p1TextField.getText();
            String oCung = p3TextField.getText();
            int ram;
            int price;
            int quantity;
            try {
                price = Integer.parseUnsignedInt(priceTextField.getText());
                quantity = Integer.parseUnsignedInt(quantityTextField.getText());
                ram = Integer.parseUnsignedInt(p2TextField.getText());
            } catch (NumberFormatException ex) {
                AlertBox.display("loi dinh dang", "loi dinh dang");
                return;
            }
            try {
                update.AddLatop(name, hsx, model, cpu, ram, oCung, price, quantity);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            window.close();
        });
        Scene scene = new Scene(root);
        window.setScene(scene);
        scene.getStylesheets().add("/Resource/css/Style.css");
        window.showAndWait();
    }

}

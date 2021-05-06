package Java.Controller;

import Java.Model.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class UserController extends Controller<Device> {
    // event khi nut them vao gio hang duoc nhan
    @FXML
    private void addToCartButtonPressed(ActionEvent event) {
        Device item = tableDv.getFocusModel().getFocusedItem();
        this.cart.add(new DeviceTf(item, "1"));
        this.deviceList.remove(item);
    }

    // event khi nut gio hang duoc nhan
    @FXML
    private void cartButtonPressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    //lay stage hien tai

        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResourceAsStream("/Resource/View/CartScene.fxml")); //load CartScene

        CartController cartController = loader.getController();
        cartController.setCart(cart);                               // thiet lap danh sach thiet bi cho cartController

        Scene cartScene = new Scene(root);
        stage.setScene(cartScene);
        cartScene.getStylesheets().add("/Resource/css/Style.css");
        stage.show();
    }

    // mang luu cac thiet bi trong gio hang
    private ArrayList<DeviceTf> cart = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            deviceList.addAll(database.getData());
        } catch (SQLException e) {
            AlertBox.display("loi du lieu", "ko the lay du lieu tu database");
        }

        columnInit();   //khoi tao column

        tableDv.setItems(deviceList);   //khoi tao bang

        updateSearchResult();   //cap nhat du lieu tim kiem

       searchText.setOnKeyPressed(keyEvent -> {         // tim kiem khi an dau cach
           if(keyEvent.getCode() == KeyCode.ENTER) {
               try {
                   searchHandle(deviceList);
               } catch (NoSuchMethodException e) {
                   e.printStackTrace();
               }
           }
       });
    }

    void columnInit() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        hangSanXuatColumn.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        kichThuocColumn.setCellValueFactory(new PropertyValueFactory<>("kichThuoc"));
        thoiLuongPinColumn.setCellValueFactory(new PropertyValueFactory<>("thoiLuongPin"));
        doPhanGiaiCameraColumn.setCellValueFactory(new PropertyValueFactory<>("doPhanGiaiCamera"));
        CPUColumn.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        RAMColumn.setCellValueFactory(new PropertyValueFactory<>("RAM"));
        hardDriveColumn.setCellValueFactory(new PropertyValueFactory<>("oCung"));

        kichThuocColumn.setCellFactory(param -> new ColumnCell());
        thoiLuongPinColumn.setCellFactory(param -> new ColumnCell());
        doPhanGiaiCameraColumn.setCellFactory(param -> new ColumnCell());
        CPUColumn.setCellFactory(param -> new ColumnCell());
        RAMColumn.setCellFactory(param -> new ColumnCell());
        hardDriveColumn.setCellFactory(param -> new ColumnCell());
    }


    private static class ColumnCell<T> extends TableCell<Device, T> {
        @Override
        protected void updateItem(T obj, boolean empty) {
            super.updateItem(obj, empty);
            if (!empty) {
                if (obj == null)
                    setText("Null");            // neu thiet bi khong co thuoc tinh thi se hien thi "Null"
                else
                    setText(obj.toString());
            }
            else
                setText(null);                    // neu thiet bi khong ton tai thi ko hien thi
        }
    }
}

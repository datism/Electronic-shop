package Java.Controller;

import Java.Model.*;
import Java.Dao.*;

import Java.Model.User;
import Java.Model.UserHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseUnsignedInt;


public class CartController extends Controller<DeviceTf> {

    public void setCart(ArrayList<DeviceTf> cart) {     //lay danh sach thiet bi tu UserController
        super.deviceList.setAll(cart);
    }

    @FXML
    private void deletePressed(ActionEvent event) {     //event khi nut xoa duoc nhan
        Device item = tableDv.getFocusModel().getFocusedItem();
        super.deviceList.remove(item);
        super.updateSearchResult();                 //cap nha ket qua tim kiem
    }

    @FXML
    private void purchasePressed(ActionEvent event) {   //event khi nut thanh toan duoc nhan
        if (checkSoluong()) {
            User user = UserHolder.getInstance().getUser();     //lay user dang dung

            Purchase purchase = new Purchase(user);
            try {
                purchase.action((new ArrayList<>(new ArrayList<>(super.deviceList))));
            } catch (SQLException a) {
                a.printStackTrace();
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private TableColumn<Device, TextField> soLuongColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.columnInit();

        tableDv.setItems(deviceList);   //khoi tao du lieu cho bang

        super.updateSearchResult();     //cap nhat ket qua tim kiem

        searchText.setOnKeyPressed(keyEvent -> {        //khi an dau cach se tim kiem
            if (keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    searchHandle(deviceList);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    void columnInit() {     // khoi tao du lieu cho cac cot
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        hangSanXuatColumn.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        conLaiColumn.setCellValueFactory(new PropertyValueFactory<>("conLai"));
        soLuongColumn.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
    }

    private boolean checkSoluong() {    //neu so luong khong so nguyen duong se hien thi loi
        int exId = 0;
        String message = null;
        for (DeviceTf deviceTf:
             deviceList) {
            String soluongStr = deviceTf.getSoLuong().getText();
            int soluong;
            try {
                soluong = parseUnsignedInt(soluongStr);
                if (soluong > deviceTf.getConLai()) {
                    message = "so luong phai be hon so san pham con lai";
                    throw new Exception();
                }
            } catch (final NumberFormatException e) {
                exId = deviceTf.getId();
                message = "so luong phai la so nguyen duong";
                break;
            } catch (Exception e) {
                exId = deviceTf.getId();
                break;
            }
        }
        if(exId != 0) {
            AlertBox.display("loi so sluong tai id: " + exId , message);
            return false;
        }
        return true;
    }

}

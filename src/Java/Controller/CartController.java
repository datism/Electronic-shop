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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.lang.Integer.parseUnsignedInt;


public class CartController extends Controller<DeviceTf> {

    public void setCart(ArrayList<DeviceTf> cart) {     //lay danh sach thiet bi tu UserController
        super.deviceList.setAll(cart);
        super.updateSearchResult();     //cap nhat ket qua tim kiem
        updateSum();
    }

    @FXML
    private void deletePressed(ActionEvent event) {     //event khi nut xoa duoc nhan
        Device item = tableDv.getFocusModel().getFocusedItem();

        super.deviceList.remove(item);
        tableDv.setItems(deviceList);

        super.updateSearchResult();                 //cap nha ket qua tim kiem
        this.updateSum();
    }

    @FXML
    private void purchasePressed(ActionEvent event) {   //event khi nut thanh toan duoc nhan
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

    @FXML
    private TableColumn<Device, Integer> soLuongColumn;

    @FXML
    private Text SumText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.columnInit();

        tableDv.setItems(deviceList);   //khoi tao du lieu cho bang
        tableDv.setEditable(true);



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
        soLuongColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        soLuongColumn.setOnEditCommit(event -> {
            int soluong = 0;
            DeviceTf device = (DeviceTf) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            try {
                soluong = event.getNewValue();
                if (soluong < 0)
                    throw new NumberFormatException();
                if (soluong > device.getConLai())
                    throw new Exception();
            } catch (NumberFormatException e) {
                AlertBox.display("sai dinh dang", "so luong phai la so nguyen >= 0");
                return;
            } catch (Exception e) {
                AlertBox.display("sai so luong", "so luong phai be hon so san pham con lai");
                return;
            }
            device.setSoLuong(soluong);
            updateSum();
        });
    }

    void updateSum() {
        int sum = 0;
        for(DeviceTf deviceTf: deviceList) {
            sum += deviceTf.getSoLuong() * deviceTf.getPrice();
        }
        SumText.setText(String.valueOf(sum));
    }
}

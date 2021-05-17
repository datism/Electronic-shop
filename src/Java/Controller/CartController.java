//Thanh vien xay dung: Dat

package Java.Controller;

import Java.Controller.Converter.CustomIntegerStringConverter;
import Java.Dao.*;

import Java.Model.Product.Device;
import Java.Model.Product.DeviceTf;
import Java.Model.user.Customer;
import Java.Model.user.User;
import Java.Model.user.UserHolder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.lang.Integer.parseUnsignedInt;


public class CartController extends Controller<DeviceTf> {

    @FXML
    private void deletePressed(ActionEvent event) {                 //event khi nut xoa duoc nhan
        Device item = tableDv.getFocusModel().getFocusedItem();     //lay item dang dc chon

        this.customer.getCart().remove(item);                       //xoa khoi gio hang

        super.deviceList.remove(item);                              //xoa khoi table
        tableDv.setItems(deviceList);

        super.updateSearchResult();                                 //cap nhat ket qua tim kiem
        this.updateSum();                                           //cap nhat tong tien phai thanh toan
    }

    @FXML
    private void purchasePressed(ActionEvent event) {                               //event khi nut thanh toan duoc nhan         //lay user dang dung
        try {
            customer.purchase();                                                    //thuc hien thanh toan
        } catch (SQLException a) {
           AlertBox.display("loi thanh toan", "khong the ket noi vs datbase");
        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();    //dong cua so
        stage.close();
    }

    @FXML
    private TableColumn<Device, Integer> soLuongColumn;     //cot so luong

    @FXML
    private Text SumText;   //Text Tong

    private Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        customer = (Customer) UserHolder.getInstance().getUser();
        super.deviceList.setAll(customer.getCart());

        super.updateSearchResult();                     //cap nhat ket qua tim kiem
        updateSum();                                    //cap nhat tong tien phai thanh toan

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
        soLuongColumn.setCellFactory(TextFieldTableCell.forTableColumn(new CustomIntegerStringConverter()));          //co the thay doi cot so luong
        soLuongColumn.setOnEditCommit(event -> {
            int soluong;
            DeviceTf device = (DeviceTf) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            try {
                soluong = event.getNewValue();
                if (soluong < 1)
                    throw new NumberFormatException();
                if (soluong > device.getConLai())
                    throw new Exception();
            } catch (NumberFormatException e) {
                AlertBox.display("sai dinh dang", "so luong phai la so nguyen > 0");
                soluong = event.getOldValue();
            } catch (Exception e) {
                AlertBox.display("sai so luong", "so luong phai be hon so san pham con lai");;
                soluong = event.getOldValue();
            }
            device.setSoLuong(soluong);
            event.getTableView().getItems().set(event.getTablePosition().getRow(), device);
            updateSum();
        });
    }

    void updateSum() {      //cap nhat tong so tien phai thanh toan
        int sum = 0;
        for(DeviceTf deviceTf: deviceList) {
            sum += deviceTf.getSoLuong() * deviceTf.getPrice();
        }
        SumText.setText(NumberFormat.getIntegerInstance(Locale.GERMAN).format(sum) + "d");
    }


}

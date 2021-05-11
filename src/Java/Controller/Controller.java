package Java.Controller;

import Java.AutoCompletionTextField;
import Java.Dao.Database;
import Java.Model.Device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Controller<T extends Device> implements Initializable {
    @FXML
    protected TableView<T> tableDv;
    @FXML
    protected TableColumn<T, Integer> idColumn;             //cot id
    @FXML
    protected TableColumn tenColumn;                        //cot ten
    @FXML
    protected TableColumn<T, String> hangSanXuatColumn;     //cot hsx
    @FXML
    protected TableColumn<T, String> modelColumn;           //cot model
    @FXML
    protected TableColumn<T, Integer> priceColumn;          //cot gia
    @FXML
    protected TableColumn<T, Float> kichThuocColumn;        //cot kich thuoc
    @FXML
    protected TableColumn<T, Integer> thoiLuongPinColumn;   //cot thoi luong pin
    @FXML
    protected TableColumn<T, Float> doPhanGiaiCameraColumn; //cot do phan giai
    @FXML
    protected TableColumn<T, String> CPUColumn;             //cot cpu
    @FXML
    protected TableColumn<T, Integer> RAMColumn;            //cot ram
    @FXML
    protected TableColumn<T, String> hardDriveColumn;       //cot o cung
    @FXML
    protected TableColumn<T, Integer> conLaiColumn;         //cot con lai
    @FXML
    protected AutoCompletionTextField searchText;           //thanh tim kiem
    @FXML
    protected void searchButtonPressed(ActionEvent event) throws NoSuchMethodException {
        searchHandle(this.deviceList);
    }

    protected ObservableList<T> deviceList = FXCollections.observableArrayList();   //mang thiet bi hien thi trong scene
    protected Database database = new Database();                                   // lay du lieu hoac thay doi du lieu database

    //cap nhat ket qua tim kiem
    protected void updateSearchResult() {
        if (!deviceList.isEmpty()) {
            Set<String> manuList = deviceList.stream().map(device -> device.getHangSanXuat()).collect(Collectors.toSet());
            Set<String> nameList = deviceList.stream().map(device -> device.getTen()).collect(Collectors.toSet());
            searchText.setManuEntries(manuList);
            searchText.setNameEntries(nameList);
        }
    }

    //xu ly khi mot ket qua dc chon tu thanh tim kiem
    protected void searchHandle(ObservableList<T> deviceList1) throws NoSuchMethodException {
        String sf = searchText.getText();
        ObservableList<T> deviceList2 = FXCollections.observableArrayList();
        if (!sf.equals("")) {
            Method method = Device.class.getMethod(String.valueOf(searchText.getUserData())); // lay phuong thuc de thiet lap filter
                                                                                              // getHsx hoac getTen
            ArrayList<T> deviceL = (ArrayList<T>) deviceList1.stream().filter(device -> {
                try {
                    if (sf.equals(method.invoke(device)))
                        return true;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                return false;
            }).collect(Collectors.toList());

            deviceList2.setAll(deviceL);
        }
        else    // Neu thanh tim kiem ko co gi thi se hien thi toan bo danh sach thiet bi
            deviceList2 = deviceList1;
        tableDv.setItems(deviceList2);
    }

    abstract void columnInit(); //cac lop con deu phai khoi tao cot

}

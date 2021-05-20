//Thanh vien xay dung: Dat

package Java.Controller;

import Java.AutoCompletionTextField;
import Java.Dao.Database;
import Java.Model.Product.CellPhone;
import Java.Model.Product.Device;

import Java.Model.Product.Laptop;
import Java.Model.user.User;
import Java.Model.user.UserHolder;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    protected ObservableList<T> deviceList;   //mang thiet bi hien thi trong scene
    protected User user;              // lay du lieu hoac thay doi du lieu database

    Controller() {
        deviceList = FXCollections.observableArrayList();
        user = UserHolder.getInstance().getUser();
    }



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

    void columnInit() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));                             //khoi tao gia tri cho cot id
        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));                           //khoi tao gia tri cho cot ten
        hangSanXuatColumn.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));           //khoi tao gia tri cho cot hangSangXuat
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));                       //khoi tao gia tri cho cot model
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));                       //khoi tao gia tri cho cot price
        if(conLaiColumn != null)
            conLaiColumn.setCellValueFactory(new PropertyValueFactory<>("conLai"));                 //khoi tao gia tri cho cot conLai

        if(kichThuocColumn != null)
        kichThuocColumn.setCellValueFactory(data -> {                                                  //khoi tao gia tri cho cot kichThuoc
            Device device = data.getValue();
            if (device instanceof CellPhone)
                return ((CellPhone) device).kichThuocProperty().asObject();
            return null;
        });

        if(thoiLuongPinColumn != null)
        thoiLuongPinColumn.setCellValueFactory(data -> {                                                //khoi tao gia tri cho cot thoiLuongPin
            Device device = data.getValue();
            if (device instanceof CellPhone)
                return ((CellPhone) device).thoiLuongPinProperty().asObject();
            return null;
        });

        if(doPhanGiaiCameraColumn != null)
        doPhanGiaiCameraColumn.setCellValueFactory(data -> {                                             //khoi tao gia tri cho cot doPhanGiaiCamera
            Device device = data.getValue();
            if (device instanceof CellPhone)
                return ((CellPhone) device).doPhanGiaiCameraProperty().asObject();
            return null;
        });

        if(CPUColumn != null)
        CPUColumn.setCellValueFactory(data -> {                                                            //khoi tao gia tri cho cot CPU
            Device device = data.getValue();
            if (device instanceof Laptop)
                return new SimpleStringProperty(((Laptop) device).getCPU());
            return null;
        });

        if(RAMColumn != null)
        RAMColumn.setCellValueFactory(data -> {                                                             //khoi tao gia tri cho cot RAM
            Device device = data.getValue();
            if (device instanceof Laptop)
                return ((Laptop) device).RAMProperty().asObject();
            return null;
        });

        if(hardDriveColumn != null)                                                                         //khoi tao gia tri cho cot Ocung
        hardDriveColumn.setCellValueFactory(data -> {
            Device device = data.getValue();
            if (device instanceof Laptop)
                return new SimpleStringProperty(((Laptop) device).getCPU());
            return null;
        });
    };


}

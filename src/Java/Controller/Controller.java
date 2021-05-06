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
    protected TableColumn<T, Integer> idColumn;
    @FXML
    protected TableColumn tenColumn;
    @FXML
    protected TableColumn<T, String> hangSanXuatColumn;
    @FXML
    protected TableColumn<T, String> modelColumn;
    @FXML
    protected TableColumn<T, Integer> priceColumn;
    @FXML
    protected TableColumn<T, Float> kichThuocColumn;
    @FXML
    protected TableColumn<T, Integer> thoiLuongPinColumn;
    @FXML
    protected TableColumn<T, Float> doPhanGiaiCameraColumn;
    @FXML
    protected TableColumn<T, String> CPUColumn;
    @FXML
    protected TableColumn<T, Integer> RAMColumn;
    @FXML
    protected TableColumn<T, String> hardDriveColumn;
    @FXML
    protected TableColumn<T, Integer> conLaiColumn;
    @FXML
    protected AutoCompletionTextField searchText;
    @FXML
    protected void searchButtonPressed(ActionEvent event) throws NoSuchMethodException {
        searchHandle(this.deviceList);
    }

    protected ObservableList<T> deviceList = FXCollections.observableArrayList();
    protected Database database = new Database();

    protected void updateSearchResult() {
        if (!deviceList.isEmpty()) {
            Set<String> manuList = deviceList.stream().map(device -> device.getHangSanXuat()).collect(Collectors.toSet());
            Set<String> nameList = deviceList.stream().map(device -> device.getTen()).collect(Collectors.toSet());
            searchText.setManuEntries(manuList);
            searchText.setNameEntries(nameList);
        }
    }

    protected void searchHandle(ObservableList<T> deviceList1) throws NoSuchMethodException {
        String sf = searchText.getText();
        ObservableList<T> deviceList2 = FXCollections.observableArrayList();
        if (!sf.equals("")) {
            Method method = Device.class.getMethod(String.valueOf(searchText.getUserData()));

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
        else
            deviceList2 = deviceList1;
        tableDv.setItems(deviceList2);
    }

    abstract void columnInit();

}

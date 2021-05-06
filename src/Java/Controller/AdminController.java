package Java.Controller;

import Java.Model.* ;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class AdminController extends Controller<Device> {
    @FXML
    private void addPhonePressed(ActionEvent event) throws SQLException {
        AddDeviceBox addDeviceBox = new AddDeviceBox();
        addDeviceBox.adddPhone();
        super.deviceList.setAll(super.database.getData());
        updateSearchResult();
    }
    @FXML
    private void addLaptopPressed(ActionEvent event) throws SQLException {
        AddDeviceBox addDeviceBox = new AddDeviceBox();
        addDeviceBox.adddLaptop();
        super.deviceList.setAll(super.database.getData());
        updateSearchResult();
    }
    @FXML
    private void deletePressed(ActionEvent event) throws SQLException {
        Device item = super.tableDv.getFocusModel().getFocusedItem();
        super.deviceList.remove(item);
        super.database.Delete(item.getId());
        updateSearchResult();

    }
    @FXML
    private void updatePressed(ActionEvent event) throws SQLException {
        for (Device device :
                this.changedItem) {

            super.database.Modify(device);
        }
        super.updateSearchResult();
    }

    private final Set<Device> changedItem = new LinkedHashSet<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            super.deviceList.addAll(database.getData());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        columnInit();

        super.tableDv.setItems(deviceList);
        super.tableDv.setEditable(true);

        super.updateSearchResult();

        super.searchText.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                try {
                    super.searchHandle(deviceList);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void columnInit() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        tenColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        tenColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) t -> {
            Device device = (Device) t.getTableView().getItems().get(
                    t.getTablePosition().getRow());
            device.setTen((String) t.getNewValue());
            changedItem.add(device);
        });

        hangSanXuatColumn.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));
        hangSanXuatColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hangSanXuatColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setHangSanXuat(event.getNewValue());
            changedItem.add(device);
        });


        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setModel(event.getNewValue());
            changedItem.add(device);
        });

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        priceColumn.setOnEditCommit(event -> {
            int price;
            try {
                price = event.getNewValue();
                if (price < 0)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                AlertBox.display("sai dinh dang", "gia tien phai la so nguyen >= 0");
                return;
            }
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setPrice(price);
            changedItem.add(device);
        });

        StringConverter<String> strConvert = new StrConvert();
        StringConverter<Integer> intConvert = new IntConvert();
        StringConverter<Float> floatConvert = new FloatConvert();

        kichThuocColumn.setCellValueFactory(new PropertyValueFactory<>("kichThuoc"));
        kichThuocColumn.setCellFactory(e -> new EditCustomCell(floatConvert));
        kichThuocColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setKichThuoc(event.getNewValue());
            changedItem.add(phone);
        });

        thoiLuongPinColumn.setCellValueFactory(new PropertyValueFactory<>("thoiLuongPin"));
        thoiLuongPinColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        thoiLuongPinColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setThoiLuongPin(event.getNewValue());
            changedItem.add(phone);
        });

        doPhanGiaiCameraColumn.setCellValueFactory(new PropertyValueFactory<>("doPhanGiaiCamera"));
        doPhanGiaiCameraColumn.setCellFactory(e -> new EditCustomCell<>(floatConvert));
        doPhanGiaiCameraColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setDoPhanGiaiCamera(event.getNewValue());
            changedItem.add(phone);
        });

        CPUColumn.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        CPUColumn.setCellFactory(e -> new EditCustomCell<>(strConvert));
        CPUColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setCPU(event.getNewValue());
            changedItem.add(laptop);
        });

        RAMColumn.setCellValueFactory(new PropertyValueFactory<>("RAM"));
        RAMColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        RAMColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setRAM(event.getNewValue());
            changedItem.add(laptop);
        });

        hardDriveColumn.setCellValueFactory(new PropertyValueFactory<>("oCung"));
        hardDriveColumn.setCellFactory(e -> new EditCustomCell<>(strConvert));
        hardDriveColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setoCung(event.getNewValue());
            changedItem.add(laptop);
        });

        conLaiColumn.setCellValueFactory(new PropertyValueFactory<>("conLai"));
        conLaiColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        conLaiColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setConLai(event.getNewValue());
            changedItem.add(device);
        });


    }

    private static class EditCustomCell<T> extends TextFieldTableCell<Device, T> {
        public EditCustomCell(StringConverter<T> stringConverter) {
            super(stringConverter);
        }
        @Override
        public void updateItem(T obj, boolean empty) {
            super.updateItem(obj, empty);
            if (!empty) {
                if (obj == null) {
                    setText("Null");
                    setEditable(false);
                }
                else
                    setText(obj.toString());
            }
            else
                setText(null);
        }
    }

    private static class StrConvert extends StringConverter<String> {

        @Override
        public String toString(String s) {
            return s;
        }

        @Override
        public String fromString(String s) {
            return s;
        }
    }

    private static class IntConvert extends StringConverter<Integer> {

        @Override
        public String toString(Integer integer) {
            if(integer == null)
                return null;
            return integer.toString();
        }

        @Override
        public Integer fromString(String s) {
            if(s == null)
                return null;
            return Integer.parseInt(s);
        }
    }

    private static class FloatConvert extends StringConverter<Float> {

        @Override
        public String toString(Float afloat) {
            if(afloat == null)
                return null;
            return afloat.toString();
        }

        @Override
        public Float fromString(String s) {
            if(s == null)
                return null;
            return Float.parseFloat(s);
        }
    }

}

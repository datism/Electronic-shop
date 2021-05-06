package Java.Controller;

import Java.Model.* ;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    @FXML   //nut them dien thoai
    private void addPhonePressed(ActionEvent event) throws SQLException {
        AddDeviceBox addDeviceBox = new AddDeviceBox();
        addDeviceBox.adddPhone();
        super.deviceList.setAll(super.database.getData());  //reset lai table
        updateSearchResult();   //cap nhat ket qua tim kiem
    }
    @FXML   //nut them laptop
    private void addLaptopPressed(ActionEvent event) throws SQLException {
        AddDeviceBox addDeviceBox = new AddDeviceBox();
        addDeviceBox.adddLaptop();
        super.deviceList.setAll(super.database.getData());
        updateSearchResult();
    }
    @FXML   //nut xoa
    private void deletePressed(ActionEvent event) throws SQLException {
        Device item = super.tableDv.getFocusModel().getFocusedItem();
        super.database.Delete(item.getId());
        super.deviceList.setAll(super.database.getData());
        updateSearchResult();

    }
    @FXML   //nut cap nhat
    private void updatePressed(ActionEvent event) throws SQLException {
        for (Device device :
                this.changedItem) {

            super.database.Modify(device);
        }
        super.updateSearchResult();
        this.updateButton.setDisable(true);     //tat nu cap nhat cho den khi co thay doi
    }
    @FXML   // nut cap nhat
    private Button updateButton;

    private final Set<Device> changedItem = new LinkedHashSet<>();  //nhung thiet bi dc thay doi


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            super.deviceList.addAll(database.getData());    //lay du lieu tu database
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        columnInit();           //khoi tao cot

        super.tableDv.setItems(deviceList);     //khoi tao du lieu de table hien thi
        super.tableDv.setEditable(true);        //cho phep thay doi gia tri trong bang

        super.updateSearchResult();             //cap nhat ket qua tim kiem

        super.searchText.setOnKeyPressed(keyEvent -> {  //hien thi ket qua tim kiem khi nhan dau cach
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
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));     //khoi tao gia tri cho cot id
                                                                               //cot id khong the thay doi

        tenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));

        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/TextFieldTableCell.html#forTableColumn--
        tenColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        tenColumn.setOnEditCommit((EventHandler<TableColumn.CellEditEvent>) t -> {
            Device device = (Device) t.getTableView().getItems().get(
                    t.getTablePosition().getRow());                                         //lay thiet bi dc thay doi
            device.setTen((String) t.getNewValue());                                        //thay doi gia tri
            this.updateButton.setDisable(false);                                            //enable nut cap nhat
            changedItem.add(device);                                                        //them thiet bi vao danh sach thay doi
        });

        hangSanXuatColumn.setCellValueFactory(new PropertyValueFactory<>("hangSanXuat"));
        hangSanXuatColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        hangSanXuatColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setHangSanXuat(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(device);
        });


        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        modelColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        modelColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setModel(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(device);
        });

        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/TextFieldTableCell.html#forTableColumn-javafx.util.StringConverter-
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
            this.updateButton.setDisable(false);
            changedItem.add(device);
        });

        StringConverter<String> strConvert = new StrConvert();          //doi dinh dang string sang string (vi cai EditCustomCell tu dinh nghia)
        StringConverter<Integer> intConvert = new IntConvert();         //Intteger sang String va ngc lai
        StringConverter<Float> floatConvert = new FloatConvert();       //Float sang String va ngc lai

        kichThuocColumn.setCellValueFactory(new PropertyValueFactory<>("kichThuoc"));
        kichThuocColumn.setCellFactory(e -> new EditCustomCell(floatConvert));
        kichThuocColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setKichThuoc(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(phone);
        });

        thoiLuongPinColumn.setCellValueFactory(new PropertyValueFactory<>("thoiLuongPin"));
        thoiLuongPinColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        thoiLuongPinColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setThoiLuongPin(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(phone);
        });

        doPhanGiaiCameraColumn.setCellValueFactory(new PropertyValueFactory<>("doPhanGiaiCamera"));
        doPhanGiaiCameraColumn.setCellFactory(e -> new EditCustomCell<>(floatConvert));
        doPhanGiaiCameraColumn.setOnEditCommit(event -> {
            CellPhone phone = (CellPhone) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            phone.setDoPhanGiaiCamera(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(phone);
        });

        CPUColumn.setCellValueFactory(new PropertyValueFactory<>("CPU"));
        CPUColumn.setCellFactory(e -> new EditCustomCell<>(strConvert));
        CPUColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setCPU(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(laptop);
        });

        RAMColumn.setCellValueFactory(new PropertyValueFactory<>("RAM"));
        RAMColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        RAMColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setRAM(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(laptop);
        });

        hardDriveColumn.setCellValueFactory(new PropertyValueFactory<>("oCung"));
        hardDriveColumn.setCellFactory(e -> new EditCustomCell<>(strConvert));
        hardDriveColumn.setOnEditCommit(event -> {
            Laptop laptop = (Laptop) event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            laptop.setoCung(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(laptop);
        });

        conLaiColumn.setCellValueFactory(new PropertyValueFactory<>("conLai"));
        conLaiColumn.setCellFactory(e -> new EditCustomCell<>(intConvert));
        conLaiColumn.setOnEditCommit(event -> {
            Device device = event.getTableView().getItems().get(
                    event.getTablePosition().getRow());
            device.setConLai(event.getNewValue());
            this.updateButton.setDisable(false);
            changedItem.add(device);
        });


    }

    //neu thiet bi khong co thuoc tinh thi se hien thi la "Null" va khong cho thay doi
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

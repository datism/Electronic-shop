//Thanh vien xay dung: Dung

package Java.Model.Product;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Device {
    private IntegerProperty id;
    private StringProperty ten;
    private StringProperty hangSanXuat;
    private StringProperty model;
    private IntegerProperty price;
    private IntegerProperty conLai;

    public Device(int id, String ten, String hangSanXuat, String model, int price, int conLai) {
        this.id = new SimpleIntegerProperty(id);
        this.ten = new SimpleStringProperty(ten);
        this.hangSanXuat = new SimpleStringProperty(hangSanXuat);
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleIntegerProperty(price);
        this.conLai = new SimpleIntegerProperty(conLai);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTen() {
        return ten.get();
    }

    public StringProperty tenProperty() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten.set(ten);
    }

    public String getHangSanXuat() {
        return hangSanXuat.get();
    }

    public StringProperty hangSanXuatProperty() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat.set(hangSanXuat);
    }

    public String getModel() {
        return model.get();
    }

    public StringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    public int getConLai() {
        return conLai.get();
    }

    public IntegerProperty conLaiProperty() {
        return conLai;
    }

    public void setConLai(int conLai) {
        this.conLai.set(conLai);
    }
}

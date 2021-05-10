package Java.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;

// du lieu de bieu dien tren cartScene
public class DeviceTf extends Device{
    private IntegerProperty soLuong;

    public DeviceTf(Device device, int soLuong) {
        super(device.getId(), device.getTen(), device.getHangSanXuat(), device.getModel(), device.getPrice(), device.getConLai());
        this.soLuong = new SimpleIntegerProperty(soLuong);
    }

    public int getSoLuong() {
        return soLuong.get();
    }

    public IntegerProperty soLuongProperty() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong.set(soLuong);
    }
}

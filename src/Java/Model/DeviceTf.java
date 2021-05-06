package Java.Model;

import javafx.scene.control.TextField;

import static java.lang.Integer.parseInt;

// du lieu de bieu dien tren cartScene
public class DeviceTf extends Device{
    private TextField soLuong;

    public DeviceTf(Device device, String soLuong) {
        super(device.getId(), device.getTen(), device.getHangSanXuat(), device.getModel(), device.getPrice(), device.getConLai());
        this.soLuong = new TextField(soLuong);
    }

    public TextField getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(TextField soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuongInt() {
        return parseInt(soLuong.getText());
    }
}

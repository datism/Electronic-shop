package Java.Model;

import javafx.beans.property.*;

public class CellPhone extends Device {
    private StringProperty kichThuoc;
    private IntegerProperty thoiLuongPin;
    private DoubleProperty doPhanGiaiCamera;

    public CellPhone(int id,String ten, String hsx, String model, int price, int conlai, String kthuoc, int thoiluong, double dopg)
    {
        super(id,ten, hsx, model,price, conlai);
        this.kichThuoc = new SimpleStringProperty(kthuoc);
        this.thoiLuongPin = new SimpleIntegerProperty(thoiluong);
        this.doPhanGiaiCamera = new SimpleDoubleProperty(dopg);
    }

    public String getKichThuoc() {
        return kichThuoc.get();
    }

    public StringProperty kichThuocProperty() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc.set(kichThuoc);
    }

    public int getThoiLuongPin() {
        return thoiLuongPin.get();
    }

    public IntegerProperty thoiLuongPinProperty() {
        return thoiLuongPin;
    }

    public void setThoiLuongPin(int thoiLuongPin) {
        this.thoiLuongPin.set(thoiLuongPin);
    }

    public double getDoPhanGiaiCamera() {
        return doPhanGiaiCamera.get();
    }

    public DoubleProperty doPhanGiaiCameraProperty() {
        return doPhanGiaiCamera;
    }

    public void setDoPhanGiaiCamera(double doPhanGiaiCamera) {
        this.doPhanGiaiCamera.set(doPhanGiaiCamera);
    }

}

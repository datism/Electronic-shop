package Java.Model;

import javafx.beans.property.*;

public class CellPhone extends Device {
    private FloatProperty kichThuoc;
    private IntegerProperty thoiLuongPin;
    private FloatProperty doPhanGiaiCamera;

    public CellPhone(int id,String ten, String hsx, String model, int price, int conlai, float kthuoc, int thoiluong, float dopg)
    {
        super(id,ten, hsx, model,price, conlai);
        this.kichThuoc = new SimpleFloatProperty(kthuoc);
        this.thoiLuongPin = new SimpleIntegerProperty(thoiluong);
        this.doPhanGiaiCamera = new SimpleFloatProperty(dopg);
    }

    public float getKichThuoc() {
        return kichThuoc.get();
    }

    public FloatProperty kichThuocProperty() {
        return kichThuoc;
    }

    public void setKichThuoc(float kichThuoc) {
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

    public float getDoPhanGiaiCamera() {
        return doPhanGiaiCamera.get();
    }

    public FloatProperty doPhanGiaiCameraProperty() {
        return doPhanGiaiCamera;
    }

    public void setDoPhanGiaiCamera(float doPhanGiaiCamera) {
        this.doPhanGiaiCamera.set(doPhanGiaiCamera);
    }
}

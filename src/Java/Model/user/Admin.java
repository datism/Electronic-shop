//Thanh vien xay dung: Dung, An

package Java.Model.user;

import Java.Model.Product.Device;

import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends User{
    public Admin(int id, String userName, String passWord) {
        super(id, userName, passWord, true);
    }


    public ArrayList<Bill> getBills() throws SQLException {
        return super.getDatabase().getBills();
    }

    public ArrayList<User> getUserInfo() throws SQLException {
        return super.getDatabase().getUsers();
    }

    public void AddPhone(String ten, String hangsx, String model, float kichthuoc, int thoiluongpin, float dpg, int gia, int soluong) throws SQLException {
        super.getDatabase().AddPhone(ten, hangsx, model, kichthuoc, thoiluongpin, dpg, gia, soluong);
    }

    public void AddLaptop(String ten, String hangsx, String model, String cpu, int ram, String ocung,  int gia, int soluong) throws SQLException {
        super.getDatabase().AddLatop(ten, hangsx, model, cpu, ram, ocung, gia, soluong);
    }

    public void Modify(Device device) throws SQLException {
        super.getDatabase().Modify(device);
    }

    public void Delete(Device device) throws SQLException {
        super.getDatabase().Delete(device.getId());
    }
}


//Thanh vien xay dung: Dung, An, Dat

package Java.Model.user;

import Java.Dao.Purchase;
import Java.Model.Product.Device;
import Java.Model.Product.DeviceTf;

import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends User{
    private int revenue;
    private final ArrayList<DeviceTf> cart;
    private final Purchase purchase;

    public Customer(int id, String userName, String passWord, int revenue) {
        super(id, userName, passWord, false);
        this.revenue = revenue;
        this.cart = new ArrayList<>();
        this.purchase = new Purchase(id);
    }

    public void purchase() throws SQLException {
        this.purchase.action(cart);
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public ArrayList<DeviceTf> getCart() {
        return cart;
    }
}

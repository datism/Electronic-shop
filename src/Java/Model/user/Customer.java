//Thanh vien xay dung: Dung, An, Dat

package Java.Model.user;

import Java.Dao.Purchase;
import Java.Model.Product.Device;
import Java.Model.Product.DeviceTf;

import java.sql.SQLException;
import java.util.ArrayList;

public class Customer extends User{
    private int spent;
    private final ArrayList<DeviceTf> cart;
    private final Purchase purchase;

    public Customer(int id, String userName, String passWord, int spent) {
        super(id, userName, passWord, false);
        this.spent = spent;
        this.cart = new ArrayList<>();
        this.purchase = new Purchase(id);
    }

    public void purchase() throws SQLException {
        this.purchase.action(cart);
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public ArrayList<DeviceTf> getCart() {
        return cart;
    }
}

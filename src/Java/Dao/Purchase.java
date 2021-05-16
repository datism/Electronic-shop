//Thanh vien xay dung: An

package Java.Dao;

import Java.Model.user.Bill;
import Java.Model.Product.DeviceTf;
import Java.Model.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Purchase {
    private int userId;
    private Bill bill;
    private Conn conn;

    public Purchase(int userId)
    {
        this.userId = userId;
        bill = new Bill(userId);
        conn = new Conn();
    }

    //day hoa don len database
    public void action(ArrayList<DeviceTf> cart) throws SQLException {
        if(cart.isEmpty())
            return;
        for (DeviceTf device:
             cart) {
            int id = device.getId();
            int soluong = device.getSoLuong();
            bill.action(device.getPrice(), soluong);
            int soluongmoi = device.getConLai() - soluong;
            if (soluongmoi < 1) {
                String q = "Delete from device where id = '" + id + "'";
                conn.s.executeUpdate(q);
            }
            else {
                String q = "update device set CONLAI = '" + soluongmoi + "' where id = '" + id + "'";
                conn.s.executeUpdate(q);
            }
        }
        int gia = bill.getMoney();

        String queryBill = "insert into bill(UserId, Gia, ThoiGian) values ('" + userId + "', '" + gia +"',CURDATE())";
        conn.s.executeUpdate(queryBill);

        String query = "select * from user where Id='" + userId + "'";
        ResultSet rs = conn.s.executeQuery(query);
        if(rs.next()) {
            int daMua = rs.getInt("DaMua");
            daMua += gia;
            String queryUser = "update user set DaMua = '" + daMua + "' where Id = '" + userId + "'";
            conn.s.executeUpdate(queryUser);
        }

    }

}

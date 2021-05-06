package Java.Dao;

import Java.Model.Bill;
import Java.Model.DeviceTf;
import Java.Model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class Purchase {
    private User user;
    private Bill bill;
    private Conn conn;

    public Purchase(User user)
    {
        this.user = user;
        bill = new Bill(user);
        conn = new Conn();
    }

    //day hoa don len database
    public void action(ArrayList<DeviceTf> cart) throws SQLException {
        if(cart.isEmpty())
            return;
        for (DeviceTf device:
             cart) {
            int id = device.getId();
            int soluong = device.getSoLuongInt();
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
        int idUser = user.getId();
        int gia = bill.getMoney();
        String queryBill = "insert into bill(UserId, Gia, ThoiGian) values ('" + idUser + "', '" + gia +"',CURDATE())";
        conn.s.executeUpdate(queryBill);
    }

    public void DoanhThu() throws SQLException {
//        System.out.printf("Doanh thu tu ngay[yyyy-MM-dd]: ");
//        String startDay = scanner.nextLine();
//        System.out.printf("Doanh thu den ngay[yyyy-MM-dd]: ");
//        String endDay = scanner.nextLine();
//        String  query = "select * from bill where ThoiGian between '"+startDay+"' and '"+endDay+"'";
//        ResultSet rs = conn.s.executeQuery(query);
//        int doanhThu = 0;
//        while (rs.next())
//        {
//            doanhThu += rs.getInt("Gia");
//        }
//        System.out.println("Trong khoang thoi gian tren so tien thu duoc la: "+doanhThu);
    }
}

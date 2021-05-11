package Java.Dao;

import Java.Model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Database {
    private final Conn conn;

    public Database()
    {
        conn = new Conn();
    }

    // lay du lieu thiet bi tu database
    public ArrayList<Device> getData() throws SQLException{
        String query = "select * from device";
        ResultSet rs = conn.s.executeQuery(query);
        ArrayList<Device> listDv = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("Id");
            String ten = rs.getString("Ten");
            String hsx = rs.getString("HangSanXuat");
            String model = rs.getString("Model");
            float kichThuoc = rs.getFloat("KichThuoc");
            int thoiLuongPin = rs.getInt("ThoiLuongPin");
            float doPhanGiai = rs.getFloat("DoPhanGiaiCamera");
            String CPU = rs.getString("CPU");
            int RAM = rs.getInt("RAM");
            String oCung = rs.getString("OCUNG");
            int price = rs.getInt("Gia");
            int conlai = rs.getInt("CONLAI");
            Device tempDevice;
            if (thoiLuongPin != 0) {
                tempDevice = new CellPhone(id,ten, hsx, model, price, conlai, kichThuoc, thoiLuongPin, doPhanGiai);
            } else {
                tempDevice = new Laptop(id,ten, hsx, model, price, conlai, CPU, RAM, oCung);
            }
            listDv.add(tempDevice);
        }
        return listDv;
    }

    //lay du lieu hoa don tu database
    public ArrayList<Bill> getBills() throws SQLException {
        String query = "select * from bill";
        ResultSet rs = conn.s.executeQuery(query);
        ArrayList<Bill> listB = new ArrayList<>();
        while (rs.next()) {
            int userId = rs.getInt("UserId");
            int money = rs.getInt("Gia");
            Date date = rs.getDate("ThoiGian");
            listB.add(new Bill(userId, date, money));
        }
        return listB;
    }

    //lay du lieu user tu database
    public ArrayList<User> getUsers() throws SQLException {
        String query = "select * from user";
        ResultSet rs = conn.s.executeQuery(query);

        ArrayList<User> list = new ArrayList<>();
        while (rs.next()) {
            int userId = rs.getInt("Id");
            String name = rs.getString("Ten");
            String pass = rs.getString("MatKhau");
            boolean isAdmin = rs.getBoolean("IsAdmin");
            int daMua = rs.getInt("DaMua");
            list.add(new User(userId, name, pass, isAdmin, daMua));
        }
        return list;
    }

    //them CellPhone moi vao database
    public void AddPhone(String ten, String hangsx, String model, float kichthuoc, int thoiluongpin, float dpg, int gia, int soluong) throws SQLException {
        String q = "insert into device(Ten, HangSanXuat, Model, KichThuoc, ThoiLuongPin, DoPhanGiaiCamera,Gia, CONLAI) " +
                "values('"+ten+"','"+hangsx+"','"+model+"' ,'"+kichthuoc+"','"+thoiluongpin+"','"+dpg+"','"+gia+"','"+soluong+"')";
        conn.s.executeUpdate(q);

    }

    //them Laptop moi vao database
    public void AddLatop(String ten, String hangsx, String model, String cpu, int ram, String ocung,  int gia, int soluong) throws SQLException {
        String q="insert into device(Ten, HangSanXuat, Model, CPU, RAM, OCUNG,Gia, CONLAI) "+
        "values('"+ten+"','"+hangsx+"','"+model+"' ,'"+cpu+"','"+ram+"','"+ocung+"','"+gia+"','"+soluong+"')";
        conn.s.executeUpdate(q);
    }

    //thay doi thiet bi trong database
    public void Modify(Device device) throws SQLException {
            int Id = device.getId();
            String query = "select * from device where Id='"+Id+"'";
            ResultSet rs = conn.s.executeQuery(query);
            if(rs.next())
            {
                if(device instanceof CellPhone)
                {
                    String q = "update device set ";

                    String ten = device.getTen();
                    if(!ten.equals("")) q+="Ten = '"+ten+"',";

                    String hangsx = device.getHangSanXuat();
                    if(!hangsx.equals("")) q+="HangSanXuat = '"+hangsx+"',";

                    String model = device.getModel();
                    if(!model.equals("")) q+="Model = '"+model+"',";

                    float kichthuoc = ((CellPhone) device).getKichThuoc();
                    if(kichthuoc != 0.0f) q+="KichThuoc = '"+kichthuoc+"',";

                    int thoiluongpin = ((CellPhone) device).getThoiLuongPin();
                    if(thoiluongpin != 0) q+="ThoiLuongPin = '"+thoiluongpin+"',";

                    float dpg = ((CellPhone) device).getDoPhanGiaiCamera();
                    if(dpg != 0.0f) q+="DoPhanGiaiCamera = '"+dpg+"',";

                    int gia = device.getPrice();
                    if(gia != 0) q+="Gia = '"+gia+"',";

                    int soluong = device.getConLai();
                    if(soluong != 0) q+="CONLAI = '"+soluong+"',";

                    q = q.substring(0,q.length()-1);
                    q+=" where Id = '"+Id+"'";
                    conn.s.executeUpdate(q);
                }
                else if (device instanceof Laptop)
                {
                    String q = "update device set ";

                    String ten = device.getTen();
                    if(!ten.equals("")) q+="Ten = '"+ten+"',";

                    String hangsx = device.getHangSanXuat();
                    if(!hangsx.equals("")) q+="HangSanXuat = '"+hangsx+"',";

                    String model = device.getModel();
                    if(!model.equals("")) q+="Model = '"+model+"',";

                    String cpu = ((Laptop) device).getCPU();
                    if(!cpu.equals("")) q+="CPU = '"+cpu+"',";

                    int ram = ((Laptop) device).getRAM();
                    if(ram != 0) q+="RAM = '"+ram+"',";

                    String ocung = ((Laptop) device).getoCung();
                    if(!ocung.equals("")) q+="OCUNG = '"+ocung+"',";

                    int gia = device.getPrice();
                    if(gia != 0) q+="Gia = '"+gia+"',";

                    int soluong = device.getConLai();
                    if(soluong != 0) q+="CONLAI = '"+soluong+"',";

                    q = q.substring(0,q.length()-1);
                    q+=" where Id = '"+Id+"'";
                    conn.s.executeUpdate(q);
                }
            }
    }

    //xoa thiet bi khoi database
    public void Delete(int Id) throws SQLException {
        String q = "Delete from device where Id = '"+Id+"'";
        conn.s.executeUpdate(q);

    }
}

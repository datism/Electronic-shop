package Java.Dao;

import Java.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Database {
    private Conn conn;

    public Database()
    {
        conn = new Conn();
    }

    public ArrayList<Device> getData() throws SQLException{
        String query = "select * from device";
        ResultSet rs = conn.s.executeQuery(query);
        ArrayList<Device> listDv = new ArrayList<Device>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String ten = rs.getString("Ten");
            String hsx = rs.getString("HangSanXuat");
            String model = rs.getString("Model");
            Float kichThuoc = rs.getFloat("KichThuoc");
            int thoiLuongPin = rs.getInt("ThoiLuongPin");
            Float doPhanGiai = rs.getFloat("DoPhanGiaiCamera");
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


    public void AddPhone(String ten, String hangsx, String model, float kichthuoc, int thoiluongpin, float dpg, int gia, int soluong) throws SQLException {
        String q = "insert into device(Ten, HangSanXuat, Model, KichThuoc, ThoiLuongPin, DoPhanGiaiCamera,Gia, CONLAI) " +
                "values('"+ten+"','"+hangsx+"','"+model+"' ,'"+kichthuoc+"','"+thoiluongpin+"','"+dpg+"','"+gia+"','"+soluong+"')";
        conn.s.executeUpdate(q);

    }

    public void AddLatop(String ten, String hangsx, String model, String cpu, int ram, String ocung,  int gia, int soluong) throws SQLException {
        String q="insert into device(Ten, HangSanXuat, Model, CPU, RAM, OCUNG,Gia, CONLAI) "+
        "values('"+ten+"','"+hangsx+"','"+model+"' ,'"+cpu+"','"+ram+"','"+ocung+"','"+gia+"','"+soluong+"')";
        conn.s.executeUpdate(q);
    }

    public void Modify(Device device) throws SQLException {
            int id = device.getId();
            String query = "select * from device where id='"+id+"'";
            ResultSet rs = conn.s.executeQuery(query);
            if(rs.next())
            {
                if(device instanceof CellPhone)
                {
                    String q = "update device set ";

                    String ten = device.getTen();
                    if(ten != "") q+="Ten = '"+ten+"',";

                    String hangsx = device.getHangSanXuat();
                    if(hangsx != "") q+="HangSanXuat = '"+hangsx+"',";

                    String model = device.getModel();
                    if(model != "") q+="Model = '"+model+"',";

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
                    q+=" where id = '"+id+"'";
                    conn.s.executeUpdate(q);
                }
                else if (device instanceof Laptop)
                {
                    String q = "update device set ";

                    String ten = device.getTen();
                    if(ten != "") q+="Ten = '"+ten+"',";

                    String hangsx = device.getHangSanXuat();
                    if(hangsx != "") q+="HangSanXuat = '"+hangsx+"',";

                    String model = device.getModel();
                    if(model != "") q+="Model = '"+model+"',";

                    String cpu = ((Laptop) device).getCPU();
                    if(cpu != "") q+="CPU = '"+cpu+"',";

                    int ram = ((Laptop) device).getRAM();
                    if(ram != 0) q+="RAM = '"+ram+"',";

                    String ocung = ((Laptop) device).getoCung();
                    if(ocung != "") q+="OCUNG = '"+ocung+"',";

                    int gia = device.getPrice();
                    if(gia != 0) q+="Gia = '"+gia+"',";

                    int soluong = device.getConLai();
                    if(soluong != 0) q+="CONLAI = '"+soluong+"',";

                    q = q.substring(0,q.length()-1);
                    q+=" where id = '"+id+"'";
                    conn.s.executeUpdate(q);
                }
            }
    }

    public void Delete(int id) throws SQLException {
        String q = "Delete from device where id = '"+id+"'";
        conn.s.executeUpdate(q);

    }
}

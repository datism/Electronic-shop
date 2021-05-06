package Java.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

// tao ket noi den database
public class Conn {
    public Connection c;
    public Statement s;

    public Conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/dataoop","root","");
            s = c.createStatement();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

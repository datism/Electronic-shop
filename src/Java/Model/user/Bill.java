//Thanh vien xay dung: An, Dung

package Java.Model.user;

import java.sql.Date;

// hoa don tren database
public class Bill {
    private int userId;
    private Date date;
    public int money;
    public Bill(int userId)
    {
        this.userId = userId;
        long millis = System.currentTimeMillis();
        date = new Date(millis);
        money = 0;
    }

    public Bill(int userId, Date date, int money) {
        this.userId = userId;
        this.date = date;
        this.money = money;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void action(int gia, int soLuong) {
        this.money += gia * soLuong;
    }

}

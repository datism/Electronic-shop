package Java.Model;

import java.sql.Date;

public class Bill {
    private User user;
    private Date date;
    public int money;
    public Bill(User user)
    {
        this.user=user;
        long millis=System.currentTimeMillis();
        date = new Date(millis);
        money = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return date;
    }

    public void setTime(Date date) {
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

package Java.Model;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private boolean admin;
    private int revenue;
    public User()
    {

    }

    public User(int id, String userName, String passWord, boolean admin, int revenue) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.admin = admin;
        this.revenue = revenue;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}

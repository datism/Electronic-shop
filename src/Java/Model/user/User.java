//Thanh vien xay dung: Dung, Dat

package Java.Model.user;

import Java.Dao.Database;

public abstract class User {
    private int id;
    private String userName;
    private String passWord;
    private boolean isAdmin;
    private final Database database;

    public User(int id, String userName, String passWord, boolean isAdmin) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.isAdmin = isAdmin;
        database = new Database();
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public Database getDatabase() {
        return database;
    }

}

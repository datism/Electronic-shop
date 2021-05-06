package Java.Model;

public class User {
    private int id;
    private String userName;
    private String passWord;
    private boolean admin;
    public User()
    {

    }
    public User(int id, String user, String pass, boolean ad)
    {
        this.id = id;
        this.userName= user;
        this.passWord= pass;
        this.admin = ad;
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

    public boolean isAdmin(){return admin;}
}

//Thanh vien xay dung: Dat

package Java.Model;

//Singleton Class
//luon chi co 1 user xuyen suot chuong trinh
public final class UserHolder {

    private User user;
    private final static UserHolder INSTANCE = new UserHolder();

    //de khong the khoi tao
    private UserHolder() {}

    public static UserHolder getInstance() {
        return INSTANCE;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return this.user;
    }
}
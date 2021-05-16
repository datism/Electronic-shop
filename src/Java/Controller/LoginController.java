//Thanh vien xay dung: Dat

package Java.Controller;

import Java.Dao.Login;
import Java.Model.user.Customer;
import Java.Model.user.User;
import Java.Model.user.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.converter.CurrencyStringConverter;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    private void registerPressed(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("/Resource/View/RegisterScene.fxml"));       //goi scene Register
        Scene registerScene = new Scene(registerParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
    @FXML
    public void loginPressed(ActionEvent actionEvent) throws SQLException, IOException {
        String name = userName.getText();
        String pass = passWord.getText();
        if (name.isEmpty() || pass.isEmpty()) {
            AlertBox.display("Loi dang nhap", "ko the dang nhap");
            return;
        }
        User user = Login.login(name, pass);    //lay du lieu user tu database
        if (user == null) {
            AlertBox.display("loi dang nhap", "dang nhap ko thanh cong");
            userName.clear();
            passWord.clear();
        }
        else {
            UserHolder holder = UserHolder.getInstance();   //class Singleton
            holder.setUser(user);                           //de trong thoi gian chuong trinh chay chi co 1 user duy nhat

            if (user instanceof Customer) {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResourceAsStream("/Resource/View/UserScene.fxml"));     //goi scene User
                Scene userScene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(userScene);
                userScene.getStylesheets().add("/Resource/css/Style.css");
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResourceAsStream("/Resource/View/AdminScene.fxml"));    //goi scene Admin
                Scene adminScene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(adminScene);
                adminScene.getStylesheets().add("/Resource/css/Style.css");
                stage.show();
            }
        }
    }
}

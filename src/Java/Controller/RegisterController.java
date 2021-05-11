package Java.Controller;

import Java.Dao.Login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    public void registerButtonPressed(ActionEvent actionEvent) throws SQLException, IOException {
        String name = userName.getText();
        String pass = passWord.getText();
        if (name.equals("")) {
            AlertBox.display("Loi dang ky", "Ten khong duoc de trong");
            reset();
            return;
        }
        if (pass.equals("")) {
            AlertBox.display("Loi dang ky", "Mat khau khong duoc de trong");
            reset();
            return;
        }

        boolean mode = Login.register(name, pass);      //kiem tra tai khoan da co trong database chua
        if (!mode) {
            AlertBox.display("loi dang ky", "Tai khoan da ton tai");
            reset();
            return;
        }
        else {
            AlertBox.display("", "Dang ky thanh cong");
            Parent loginParent = FXMLLoader.load(getClass().getResource("/Resource/View/LoginScene.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            loginScene.getStylesheets().add("/Resource/css/Style.css");
            stage.show();
        }
    }

    void reset() {          //neu loi thi se xoa het du lieu nhap vao
        userName.clear();
        passWord.clear();
    }
}

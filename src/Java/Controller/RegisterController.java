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
    Text popUp;
    @FXML
    public void registerButtonPressed(ActionEvent actionEvent) throws SQLException, IOException {
        String name = userName.getText();
        String pass = passWord.getText();
        boolean mode = Login.register(name, pass);
        if (!mode) {
            popUp.setText("Tai khoan da co vui long nhap lai.");
            userName.clear();
            passWord.clear();
        }
        else {
            popUp.setText("Dang ky thanh cong.");
            Parent loginParent = FXMLLoader.load(getClass().getResource("java.Controller.LoginScene.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            stage.setScene(loginScene);
            stage.show();
        }
    }
}

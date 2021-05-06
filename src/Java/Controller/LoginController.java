package Java.Controller;

import Java.Dao.Login;
import Java.Model.User;
import Java.Model.UserHolder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    TextField userName;
    @FXML
    TextField passWord;
    @FXML
    private void registerHyperLinkPressed(ActionEvent event) throws IOException {
        Parent registerParent = FXMLLoader.load(getClass().getResource("java.Controller.java.Controller.RegisterScene.fxml"));
        Scene registerScene = new Scene(registerParent);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }
    @FXML
    public void loginButtonPressed(ActionEvent actionEvent) throws SQLException, IOException {
        String name = userName.getText();
        String pass = passWord.getText();
        User user = Login.login(name, pass);
        if (user == null) {
            AlertBox.display("loi dang nhap", "dang nhap ko thanh cong");
            userName.clear();
            passWord.clear();
        }
        else {
            UserHolder holder = UserHolder.getInstance();
            holder.setUser(user);
            if (!user.isAdmin()) {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResourceAsStream("/Resource/View/UserScene.fxml"));
                Scene userScene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(userScene);
                stage.show();
            }
            else {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResourceAsStream("/Resource/View/AdminScene.fxml"));
                Scene adminScene = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(adminScene);
                stage.show();
            }
        }
    }
}

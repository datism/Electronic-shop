//Thanh vien xay dung: Dat

package Java.Controller;

import Java.Dao.Database;
import Java.Model.user.Admin;
import Java.Model.user.User;
import Java.Model.user.UserHolder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserInfoController implements Initializable {
    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, Integer> userIdCol;       //cot User ID

    @FXML
    private TableColumn<User, String> nameCol;          //cot Ten

    @FXML
    private TableColumn<User, Boolean> accessibilityCol;    //cot Quyen han

    @FXML
    private TableColumn<User, Integer> expenseCol;      //cot Da Mua

    private Admin admin;                 //lay du lieu tu database
    private ObservableList<User> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        admin = (Admin) UserHolder.getInstance().getUser();
        try {
            list.setAll(admin.getUserInfo());
        } catch (SQLException throwables) {
            AlertBox.display("loi du lieu", "khong the lay du lieu tu database");
        }

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));

        accessibilityCol.setCellValueFactory(new PropertyValueFactory<>("admin"));
        accessibilityCol.setCellFactory(e -> new accessCell());

        expenseCol.setCellValueFactory(new PropertyValueFactory<>("spent"));
        expenseCol.setCellFactory(e -> new MoneyCell<>());

        userTableView.setItems(list);
    }

    static class accessCell extends TableCell<User, Boolean> {      //cell de hien thi quyen han
        @Override
        protected void updateItem(Boolean aBoolean, boolean b) {
            super.updateItem(aBoolean, b);
            if(!b) {
                if(aBoolean == true)
                    setText("Admin");
                else
                    setText("User");
            }
        }
    }
}

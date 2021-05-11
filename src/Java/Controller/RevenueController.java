//Thanh vien xay dung: Dat

package Java.Controller;

import Java.Dao.Database;
import Java.Model.Bill;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class RevenueController implements Initializable {

    @FXML
    private TableView<Bill> tableB;

    @FXML
    private TableColumn<Bill, Integer> userIdCol;       //cot UserId

    @FXML
    private TableColumn<Bill, Integer> moneyCol;        //cot Tien thanh toan

    @FXML
    private TableColumn<Bill, Date> dateCol;            //cot Ngay(yy/mm/dd)

    @FXML
    private Text revenueText;                           //text tong doanh thu

    Database database = new Database();                 //lay du lieu tu database
    private ObservableList<Bill> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            list.setAll(database.getBills());
        } catch (SQLException throwables) {
            AlertBox.display("loi du lieu", "khong the lay bill tren database");
        }

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        moneyCol.setCellFactory(e -> new MoneyCell<>());

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        tableB.setItems(list);

        int revenue = 0;
        for (Bill b :
                list) {
            revenue += b.getMoney();
        }

        revenueText.setText((NumberFormat.getInstance(Locale.GERMAN).format(revenue)) + "d");

    }
}

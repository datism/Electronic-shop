//Thanh vien xay dung: Dat

package Java.Controller;

import javafx.scene.control.TableCell;

import java.text.NumberFormat;
import java.util.Locale;

public class MoneyCell<T> extends TableCell<T, Integer> {       //cell de the hien tien
    @Override
    protected void updateItem(Integer integer, boolean b) {
        super.updateItem(integer, b);
        if (!b) {
            if(integer != null)
                setText(NumberFormat.getIntegerInstance(Locale.GERMAN).format(integer));    //format integer thanh dang ###.###.###
            else
                setText("NULL");
        }
        else
            setText(null);
    }
}

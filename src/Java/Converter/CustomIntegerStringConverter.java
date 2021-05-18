package Java.Converter;

import Java.Controller.AlertBox;
import javafx.util.converter.IntegerStringConverter;

public class CustomIntegerStringConverter extends IntegerStringConverter {
    private final IntegerStringConverter converter = new IntegerStringConverter();

    @Override
    public String toString(Integer object) {
        return converter.toString(object);
    }

    @Override
    public Integer fromString(String string) {
        try {
            return converter.fromString(string);
        } catch (NumberFormatException e) {
            AlertBox.display("loi dinh dang", "Input yeu cau la so nguyen");
        }
        return 1;
    }
}

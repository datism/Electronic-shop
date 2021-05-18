package Java.Converter;

import Java.Controller.AlertBox;
import javafx.util.converter.FloatStringConverter;

public class CustomFloatStringConverter extends FloatStringConverter {
    private final FloatStringConverter converter = new FloatStringConverter();

    @Override
    public String toString(Float object) {
        return converter.toString(object);
    }

    @Override
    public Float fromString(String string) {
        try {
            return converter.fromString(string);
        } catch (NumberFormatException e) {
            AlertBox.display("loi dinh dang", "Input yeu cau la so thuc");
        }
        return (float) 1;
    }
}

package Java.Controller.Converter;

import javafx.util.StringConverter;

public class CustomStringStringConverter extends StringConverter<String>{
    @Override
    public String toString(String s) {
        return s;
    }

    @Override
    public String fromString(String s) {
        return s;
    }
}

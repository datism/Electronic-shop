package Java.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Laptop extends Device {
    private StringProperty CPU;
    private StringProperty oCung;
    private IntegerProperty RAM;

    public Laptop(int id,String ten, String hsx, String model, int price, int conlai, String cpu, int ram, String ocung)
    {
        super(id,ten, hsx, model,price, conlai);
        this.CPU = new SimpleStringProperty(cpu);
        this.RAM = new SimpleIntegerProperty(ram);
        this.oCung = new SimpleStringProperty(ocung);
    }

    public String getCPU() {
        return CPU.get();
    }

    public StringProperty CPUProperty() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU.set(CPU);
    }

    public String getoCung() {
        return oCung.get();
    }

    public StringProperty oCungProperty() {
        return oCung;
    }

    public void setoCung(String oCung) {
        this.oCung.set(oCung);
    }

    public int getRAM() {
        return RAM.get();
    }

    public IntegerProperty RAMProperty() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM.set(RAM);
    }
}

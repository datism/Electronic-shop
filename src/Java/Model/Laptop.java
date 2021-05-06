package Java.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Laptop extends Device {
    private StringProperty CPU;
    private StringProperty oCung;
    private StringProperty RAM;

    public Laptop(int id,String ten, String hsx, String model, int price, int conlai, String cpu, String ram, String ocung)
    {
        super(id,ten, hsx, model,price, conlai);
        this.CPU = new SimpleStringProperty(cpu);
        this.RAM = new SimpleStringProperty(ram);
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

    public String getRAM() {
        return RAM.get();
    }

    public StringProperty RAMProperty() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM.set(RAM);
    }
}

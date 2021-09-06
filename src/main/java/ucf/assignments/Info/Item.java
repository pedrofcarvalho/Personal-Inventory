package ucf.assignments.Info;

import javafx.beans.property.SimpleStringProperty;
import ucf.assignments.Controllers.InputChecker;

import java.util.Locale;

public class Item
{
    private SimpleStringProperty name;
    private SimpleStringProperty serialNum;
    private SimpleStringProperty moneyValue;

    // default for testing
    public Item()
    {
        this.name = new SimpleStringProperty("mama minha");
        this.serialNum = new SimpleStringProperty("toma louco XXXX");
        this.moneyValue = formatMoneyValueFromInput(60.00);
    }

    public Item(double zero)
    {
        this.name = new SimpleStringProperty("Test1");
        this.serialNum = new SimpleStringProperty("XXXXXXXXXX");
        this.moneyValue = formatMoneyValueFromInput(zero);
    }

    // normal constructor
    public Item(String name, String serialNum, double moneyValue)
    {
        this.name = new SimpleStringProperty(name);
        this.serialNum = new SimpleStringProperty(serialNum.toUpperCase(Locale.ROOT));
        this.moneyValue = formatMoneyValueFromInput(moneyValue);
    }

    //getters and setters
    public String getName() {
        return name.get();
    }
    public SimpleStringProperty getNameProperty() {
        return name;
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public String getSerialNum() {
        return serialNum.get();
    }
    public SimpleStringProperty getSerialNumProperty() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum.set(serialNum.toUpperCase(Locale.ROOT));
    }
    public String getMoneyValue() {
        return moneyValue.get();
    }
    public SimpleStringProperty getMoneyValueProperty() {
        return moneyValue;
    }
    public void setMoneyValue(String moneyValue)
    {
        // checks if input contains '$' in the first element, to either write a '$' or not
        if (InputChecker.checksForDollarSign(moneyValue))
            this.moneyValue.set(moneyValue);

        else
            this.moneyValue.set("$" + moneyValue);
    }
    public void setMoneyValueProperty(SimpleStringProperty moneyValueProperty)
    {
        this.moneyValue = formatMoneyValueFromInput(moneyValueProperty);
    }


    @Override
    public String toString() {
        return "Item @"+  Integer.toHexString(hashCode()) + " {" +
                "name='" + name.get() + '\'' +
                ", serialNum='" + serialNum.get() + '\'' +
                ", moneyValue='" + moneyValue.get() + '\'' +
                '}';
    }

    // methods
    private SimpleStringProperty formatMoneyValueFromInput(double givenVal)
    {
        return new SimpleStringProperty("$" + String.format("%.2f", givenVal));
    }

    private SimpleStringProperty formatMoneyValueFromInput(SimpleStringProperty givenVal)
    {
        return new SimpleStringProperty("$" + givenVal.get());
    }
}

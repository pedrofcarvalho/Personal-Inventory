package ucf.assignments.Info;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

public class InventoryList implements Serializable
{
    // inventory item list
    private ObservableList<Item> itemList;

    // constructors
    public InventoryList()
    {
        this.itemList = FXCollections.observableArrayList(); // initializes observableList as arraylist
    }

    // getter
    public ObservableList<Item> getItemList()
    {
        return itemList;
    }


    // functional methods
    private Item createItem(String name, String serialNum, double moneyVal)
    {
        return new Item(name, serialNum, moneyVal);
    }

    public void addNewItemToList(String name, String serialNum, double moneyVal)
    {
        // for testing
        if (name == null && serialNum == null)
        {
            this.itemList.add(new Item(0));
            return;
        }

       this.itemList.add(createItem(name, serialNum, moneyVal));
   }

    public void deleteItem(Item toBeRemoved)
    {
        this.itemList.remove(toBeRemoved);
    }

    public void clearList()
    {
        this.itemList.clear();
    }

    public void editItem(Item givenItem, String whatToChange, String newValue)
    {
        switch (whatToChange)
        {
            case "name" -> givenItem.setName(newValue);
            case "serial" -> givenItem.setSerialNum(newValue);
            case "money" -> givenItem.setMoneyValue(newValue);
        }
    }

    public void editItem(Item givenItem, int givenIndex) //String newName, String newSerialNum, String newMoneyVal,
    {
        this.itemList.get(givenIndex).setName(givenItem.getName());
        this.itemList.get(givenIndex).setSerialNum(givenItem.getSerialNum());
        this.itemList.get(givenIndex).setMoneyValue(givenItem.getMoneyValue());
    }
}

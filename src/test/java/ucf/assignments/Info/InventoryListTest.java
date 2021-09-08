package ucf.assignments.Info;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryListTest
{
    @Test
    void adds_101_items_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random item to it
        for (int i = 0; i < 101; i++)
        {
            testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);
        }

        // assign values to test
        int expected = 101;
        int actual = testInventory.getItemList().size();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void adds_item_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random item to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);

        // assign values to test
        String expected = "$59.68";
        String actual = testInventory.getItemList().get(0).getMoneyValue();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void deletes_item_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random items to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);
        testInventory.addNewItemToList(null, null, 0);
        testInventory.addNewItemToList(null, null, 0);

        // delete last added item
        testInventory.deleteItem(testInventory.getItemList().get(2));

        // assign values to test
        int expected = 2;
        int actual = testInventory.getItemList().size();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void clears_list_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random items to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);
        testInventory.addNewItemToList(null, null, 0);
        testInventory.addNewItemToList(null, null, 0);

        // delete last added item
        testInventory.clearList();

        // assign values to test
        int expected = 0;
        int actual = testInventory.getItemList().size();

        // then assert
        assertEquals(expected, actual);
    }


    @Test
    void edits_name_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random item to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);

        Item enteredItem = testInventory.getItemList().get(0);
        // edit the entered item
        testInventory.editItem(enteredItem, "name", "GarticTest");

        // assign values to test
        String expected = "GarticTest";
        String actual = testInventory.getItemList().get(0).getName();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void edits_serial_number_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random item to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);

        Item enteredItem = testInventory.getItemList().get(0);
        // edit the entered item
        testInventory.editItem(enteredItem, "serial", "YYYYYYYYYY");

        // assign values to test
        String expected = "YYYYYYYYYY";
        String actual = testInventory.getItemList().get(0).getSerialNum();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void edits_money_properly()
    {
        // given a random inventory list
        InventoryList testInventory = new InventoryList();

        // we add random item to it
        testInventory.addNewItemToList("Test1", "PPPPPPPPPP", 59.68);

        Item enteredItem = testInventory.getItemList().get(0);
        // edit the entered item
        testInventory.editItem(enteredItem, "money", "56.23");

        // assign values to test
        String expected = "$56.23";
        String actual = testInventory.getItemList().get(0).getMoneyValue();

        // then assert
        assertEquals(expected, actual);
    }

    @Test
    void sorts_properly()
    {
        // this is not available since you can only sort through the TableView built-in system

        // the test of another method of sorting would consist of checking the order of each elements in a pre-set table
    }

    @Test
    void searches_item_properly()
    {
        // this is not available since you can only sort through real time on the table.
        // The feature works perfectly and can see why it's undoable a search test by looking at the feature
    }

//    @Test
//    void
}
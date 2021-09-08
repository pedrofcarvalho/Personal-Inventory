package ucf.assignments;

import org.junit.jupiter.api.Test;
import ucf.assignments.Info.InventoryList;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest
{
    String filePath = "ignore_files/output_test";

    // if doing this test multiple times, please delete the created file in the "output_test" directory
    @Test
    void save_as_TSV_properly()
    {
        // given
        InventoryList testInventory = new InventoryList();
        testInventory.addNewItemToList(null, null, 0.0);

        // file path to be used
        String fileName = "hellotest.txt";
        String fullPath = filePath + "\\" + fileName;

        // get file from function call
        File newFile = DatabaseManager.saveInventoryAsTSV(testInventory, fullPath);

        // assign values to test
        String expected = "hellotest.txt";
        String actual = newFile.getName();

        assertEquals(expected, actual);
    }

    // if doing this test multiple times, please delete the created file in the "output_test" directory
    @Test
    void save_as_HTML_properly()
    {
        // given
        InventoryList testInventory = new InventoryList();
        testInventory.addNewItemToList(null, null, 0.0);

        // file path to be used
        String fileName = "thisIsATest.html";
        String fullPath = filePath + "\\" + fileName;

        // get file from function call
        File newFile = DatabaseManager.saveInventoryAsHTML(testInventory, fullPath);

        // assign values to be tested
        String expected = "thisIsATest.html";
        String actual = newFile.getName();

        assertEquals(expected, actual);
    }

/*
    INSTRUCTIONS:
      - please DO NOT run this test before running the "save_as_TSV_properly()" located above (line 17)
        this test was designed to read from the files created above, so it auto-fail if not executed after the above test
*/
    @Test
    void loads_as_TSV_properly()
    {
        // given
        InventoryList testInventory = new InventoryList();

        // create file path variable
        String fileName = "hellotest.txt";
        String fullPath = filePath + "\\" + fileName;

        // create File from filePath
        File toTest = new File(fullPath);
        testInventory = DatabaseManager.parseTSV(toTest);

        // assign values to test
        String expected = "Test1";
        String actual = testInventory.getItemList().get(0).getName();

        // then assert
        assertEquals(expected, actual);
    }


/*
    INSTRUCTIONS:
      - please DO NOT run this test before running the "save_as_HTML_properly()" located above (line 39)
        this test was designed to read from the files created above, so it auto-fail if not executed after the above test
*/
    @Test
    void loads_as_HTML_properly() throws IOException
    {
        // given
        InventoryList testInventory = new InventoryList();

        // create file path variable
        String fileName = "thisIsATest.html";
        String fullPath = filePath + "\\" + fileName;

        // create File from filePath
        File toTest = new File(fullPath);
        testInventory = DatabaseManager.parseHTML(toTest);

        // assign values to test
        String expected = "Test1";
        String actual = testInventory.getItemList().get(0).getName();

        // then assert
        assertEquals(expected, actual);
    }

}
package ucf.assignments;

import com.google.common.io.Files;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ucf.assignments.Info.InventoryList;
import ucf.assignments.Info.Item;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.*;

public class DatabaseManager
{
    public static void saveFile(String usedExtension, InventoryList inventoryList, MenuBar multipleMenuBar)
    {
        // Extension Filters array which will be later used to access which extension to use based on the necessary extension
        FileChooser.ExtensionFilter[] extensionFilters = {new FileChooser.ExtensionFilter("All Files", "*.*"), new FileChooser.ExtensionFilter("TSV File", ".txt"), new FileChooser.ExtensionFilter("HTML File", ".html"), new FileChooser.ExtensionFilter("JSON File", ".json")};

        // create file chooser
        FileChooser fileChooser = new FileChooser();

        // sets basic info about File Chooser
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()));
        fileChooser.setInitialFileName("myInventoryList." + usedExtension);

        // gets extension based on which extension will be used
        int inputEvaluation = switch (usedExtension)
                {
                    case "txt":
                        yield 1;

                    case "html":
                        yield 2;

                    case "json":
                        yield 3;

                    default:
                        throw new IllegalStateException("Unexpected value: " + usedExtension);
                };

        // gets the extensions (all files, and the other from input)
        fileChooser.getExtensionFilters().addAll(extensionFilters[0], extensionFilters[inputEvaluation]);

        // gets the current stage as the menu bar
        Stage currStage = (Stage) multipleMenuBar.getScene().getWindow();

        // Show save file dialog (and set the current scene as it's owner
        File toSaveFile = fileChooser.showSaveDialog(currStage);

        // if file is not null
        if (toSaveFile != null)
        {
            // evaluates txt, html or json and save the inventory in user chosen directory
            switch (inputEvaluation)
            {
                case 1 -> DatabaseManager.saveInventoryAsTSV(inventoryList, toSaveFile.getPath());
                case 2-> DatabaseManager.saveInventoryAsHTML(inventoryList, toSaveFile.getPath());
                case 3 -> DatabaseManager.saveInventoryAsJSON(inventoryList, toSaveFile.getPath());
            }
        }

        // else, throws error
        else
        {
            throw new IllegalStateException();
        }
    }

    public static File saveInventoryAsTSV(InventoryList toSaveFList, String pathToPlace)
    {
        // creates TSV file
        File TSVFile = createFile(pathToPlace);

        // edits created HTML file
        editTSVFile(TSVFile, toSaveFList);

        return TSVFile;
    }

    public static File saveInventoryAsHTML(InventoryList toSaveFList, String pathToPlace)
    {
        // creates HTML file
        File HTMLFile = createFile(pathToPlace);

        // edits created HTML file
        editHTMLFile(HTMLFile, toSaveFList);

        return HTMLFile;
    }

    // TODO - EXTRA CREDIT ONLY
    public static File saveInventoryAsJSON(InventoryList toSaveFList, String pathToPlace)
    {
        // creates JSON file
        File JSONFile = new File(pathToPlace);

        // edits created JSON file

        return JSONFile;
    }

    // create any file
    private static File createFile(String pathToPlace)
    {
        // assign new file to the correct path
        File newFile = new File(pathToPlace);

        try
        {
            newFile.createNewFile();
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return newFile;
    }

    private static void editTSVFile(File createdFile, InventoryList givenList)
    {
        try
        {
            FileWriter toEdit = new FileWriter(createdFile);

            // write appropriate tags into TSV file

            toEdit.write("Item Name" + "\t" + "Serial Number" + "\t" + "Value\n");

            // write each Item from the list to the file
            for (Item eachItem : givenList.getItemList())
            {
                // TODO - CHANGE THE SPACING (try anyways)
                toEdit.write(eachItem.getName() + "\t" + eachItem.getSerialNum() + "\t" + eachItem.getMoneyValue() + "\n");
            }

            // close the file
            toEdit.close();
        }

        // lets us know if the HTML file could not be edited
        catch (IOException e)
        {
            System.err.println("An error has occurred (eF_TSV)");
        }
    }

    // edits the created HTML file
    private static void editHTMLFile(File createdFile, InventoryList givenList)
    {
        try
        {
            FileWriter toEdit = new FileWriter(createdFile);

            // write appropriate tags into HTML file

            toEdit.write(writeHTMLTableHeader());

            // write each Item from the list to the file
            for (Item eachItem : givenList.getItemList())
            {
                toEdit.write("       <tr>\n" +
                        "         <td>" + eachItem.getName() + "</td>\n" +
                        "         <td>" + eachItem.getSerialNum() + "</td>\n" +
                        "         <td>" + eachItem.getMoneyValue() + "</td>\n" +
                        "       </tr>\n");
            }
            toEdit.write("    </table>\n" +
                    "  </body>\n" +
                    "</html>");

            // close the file
            toEdit.close();
        }

        // lets us know if the HTML file could not be edited
        catch (IOException e)
        {
            System.err.println("An error has occurred (eF_HTML)");
        }
    }

    private static String writeHTMLTableHeader()
    {
        return  "<!DOCTYPE html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "      <style>\n" +
                "          table, th, td {\n" +
                "              border: 1px solid black;\n" +
                "              border-collapse: collapse;\n" +
                "          }\n" +
                "          th {\n" +
                "              padding: 5px;\n" +
                "              text-align: center;    \n" +
                "          }\n" +
                "          td {\n" +
                "              padding: 5px;\n" +
                "              text-align: left;    \n" +
                "          }\n" +
                "      </style>\n" +
                "  </head>\n" +
                "  <body>" +
                "\n" +
                "    <table style=\"width:80%\">\n" +
                "       <tr>\n" +
                "         <th>Item Name</th>\n" +
                "         <th>Serial Number</th>\n" +
                "         <th>Value</th>\n" +
                "       </tr>\n";
    }


    public static InventoryList getFileFromUser(MenuBar multipleMenuBar) throws IOException, ParseException
    {
        // create file chooser
        FileChooser fileChooser = new FileChooser();

        // sets basic info about File Chooser
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString()));

        // gets the current stage as the menu bar
        Stage currStage = (Stage) multipleMenuBar.getScene().getWindow();

        // Show save file dialog (and set the current scene as it's owner
        File toLoadFile = fileChooser.showOpenDialog(currStage);

        // if the file is not null, get info from it
        if (toLoadFile != null)
        {
            // gets file extension (using Guava API)
            String fileExtension = Files.getFileExtension(toLoadFile.getName());

            return switch (fileExtension) {
                case "txt" -> parseTSV(toLoadFile);
                case "html" -> parseHTML(toLoadFile);
                case "json" -> parseJSON(toLoadFile);
                default -> throw new FileNotFoundException();
            };
        }

        // if nothing works, return a blank inventory list
        return new InventoryList();
    }

    // (TODO) - make more readable
    public static InventoryList parseTSV(File toLoadFile)
    {
        InventoryList returnInventory = new InventoryList();
        String name = null;
        String serialNum = null;
        String moneyVal = null;

        boolean ignoreFlag = true;
        int itemElement = 1;

        // set TSV Parser (gotten from UniVocity Parsers open-source API)
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser TSVparser = new TsvParser(settings);

        // parses all rows in one go.
        List<String[]> allRows = TSVparser.parseAll(toLoadFile);

        // loops through the parsed list
        for (String[] eachArr : allRows)
        {
            // ignore the header
            if (ignoreFlag)
            {
                ignoreFlag = false;
                continue;
            }

            for (String eachString : eachArr)
            {
                // assigns name
                if (itemElement == 1)
                {
                    name = eachString;
                    itemElement++;
                }

                // assigns serial num
                else if (itemElement == 2)
                {
                    serialNum = eachString;
                    itemElement++;
                }

                // assigns money value
                else if (itemElement == 3)
                {
                    moneyVal = eachString;
                    moneyVal = moneyVal.replaceAll("\\$", "");
                    itemElement = 1;
                }
            }

            // if none are null, create Item and add to list
            if (!(name == null || serialNum == null  || moneyVal == null ))
                returnInventory.addNewItemToList(name, serialNum, Double.parseDouble(moneyVal));
        }

        // return
        return returnInventory;
    }

    // (TODO) - make more readable
    // parses information from HTML file to a InventoryList ready to be loaded
    public static InventoryList parseHTML(File toLoadFile) throws IOException
    {
        // set the document to the file received from user
        Document doc = Jsoup.parse(toLoadFile, "UTF-8");
        int itemElement = 1;
        InventoryList returnInventory = new InventoryList();
        String name = null;
        String serialNum = null;
        String moneyVal = null;

        Element table = doc.select("table").get(0); //select the first table.
        Elements rows = table.select("tr");

        // (skip first row)     //first row is the col names so skip it. (skip first row) <- delete
        for (int i = 1; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");

            for (int j = 0; j < cols.size(); j++) {
                // assigns name
                if (itemElement == 1) {
                    name = cols.get(j).text();
                    System.out.println("name is: " + name);
                    itemElement++;
                }

                // assigns serial num
                else if (itemElement == 2) {
                    serialNum = cols.get(j).text();
                    System.out.println("serialNUm is: " + serialNum);
                    itemElement++;
                }

                // assigns money value
                else if (itemElement == 3) {
                    moneyVal = cols.get(j).text();
                    System.out.println("moneyVal is: " + moneyVal);

                    moneyVal = moneyVal.replaceAll("\\$", "");
                    itemElement = 1;
                }
            }

            // if none are null, create Item and add to list
            if (!(name == null || serialNum == null || moneyVal == null))
                returnInventory.addNewItemToList(name, serialNum, Double.parseDouble(moneyVal));
        }

        // otherwise, return blank list
        return returnInventory;
    }

    // TODO
    private static InventoryList parseJSON(File toLoadFile)
    {
        return new InventoryList();
    }

}


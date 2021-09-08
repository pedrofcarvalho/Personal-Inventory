package ucf.assignments.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ucf.assignments.Info.InventoryList;
import ucf.assignments.InputChecker;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController implements Initializable
{
    private InventoryList inventoryList;
    private SceneManager sceneManager;

    @FXML private Label errorName;
    @FXML private Label errorSerialNum;
    @FXML private Label errorItemValue;
    @FXML private TextField nameField;
    @FXML private TextField serialNumField;
    @FXML private TextField moneyValField;
    @FXML private Button createItemBtn;
    @FXML private Button cancelBtn;
    @FXML private Button editItemBtn;

    @FXML private MenuBar multipleMenuBar;
    @FXML private Menu helpMenu;
    @FXML private MenuItem userManualMenuOpt;

    // constructor used for Scene Manager
    public AddItemController(InventoryList inventoryList, SceneManager sceneManager)
    {
        this.inventoryList = inventoryList;
        this.sceneManager = sceneManager;
    }

    // goes back to menu screen
    public void cancelBtnClicked(ActionEvent event)
    {
        // set error labels to blank
        errorName.setText("");
        errorSerialNum.setText("");
        errorItemValue.setText("");

        changeBackToMenu(event);
    }

    // checks input and create new Item
    public void createItemBtnClicked(ActionEvent event)
    {
        // set error labels to blank
        errorName.setText("");
        errorSerialNum.setText("");
        errorItemValue.setText("");

        // retrieves input from text fields
        String nameInput = nameField.getText();
        String serialNumInput = serialNumField.getText();  // NEEDS TO CHECK IF CORRECT!!!!
        String moneyValInput = moneyValField.getText();

        // checks the input and makes necessary error label changes
        switch (InputChecker.inputIsBad(nameInput, serialNumInput, moneyValInput))
        {
            case -1:
                throw new IllegalStateException();

            case 1:
                errorName.setText("Enter a name");
                return;

            case 2:
                errorName.setText("Enter a valid name\n" +
                        "(From 2 to 256 characters)");
                return;

            case 3:
                errorSerialNum.setText("Enter a serial number");
                return;

            case 4:
                errorSerialNum.setText("Enter a valid serial number\n" +
                        "(Format: XXXXXXXXXX)");
                return;

            case 5:
                errorItemValue.setText("Enter an item value");
                return;

            case 6:
                errorItemValue.setText("Enter a valid serial number\n" +
                        "(Format: XX.XX)");
                return;

            default:
                break;
        }

        // creates new Item and add to list
        inventoryList.addNewItemToList(nameInput, serialNumInput, Double.parseDouble(moneyValInput));

        // set the text to blank (for next use purpose)
        nameField.setText("");
        serialNumField.setText("");
        moneyValField.setText("");

        // change back to menu screen
        changeBackToMenu(event);
    }

    // changes back to the main menu
    private void changeBackToMenu(ActionEvent event)
    {
        sceneManager.switchToMainMenuScene(event);
    }

    // shows the help screen
    public void userManualMenuCLicked(ActionEvent event)
    {
        sceneManager.switchToUserManual(multipleMenuBar);
    }








    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }
}
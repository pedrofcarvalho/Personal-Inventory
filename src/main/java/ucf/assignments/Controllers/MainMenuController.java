package ucf.assignments.Controllers;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import ucf.assignments.DatabaseManager;
import ucf.assignments.Info.InventoryList;
import ucf.assignments.Info.Item;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable
{
    private InventoryList inventoryList;
    private SceneManager sceneManager;

    // main scene ID's - START

    @FXML private Button addItemTestBtn;
    @FXML private Button changeSceneTestBtn;
    @FXML private Button deleteBtn;
    @FXML private Button clearListBtn;
    @FXML private TextField searchBar;

    @FXML private TableView<Item> itemTable;
    @FXML private TableColumn<Item, String> nameCol;
    @FXML private TableColumn<Item, String> serialNumCol;
    @FXML private TableColumn<Item, String> dolValCol;

    @FXML private MenuBar multipleMenuBar;
    @FXML private Menu saveAsMenu;
    @FXML private MenuItem TSVMenuOption;
    @FXML private MenuItem HTMLMenuOption;
    @FXML private MenuItem JSONMenuOption;
    @FXML private MenuItem closeAppOpt;

    @FXML private Menu helpMenu;
    @FXML private MenuItem userManualMenuOpt;

    // main scene ID's - END


    // constructor for scene manager use
    public MainMenuController(InventoryList inventoryList, SceneManager sceneManager)
    {
        this.inventoryList = inventoryList;
        this.sceneManager = sceneManager;
    }

    public void clearListBtnClicked(ActionEvent event)
    {
        inventoryList.clearList();
    }


    public void TSVSaveButtonClicked(ActionEvent event)
    {
        DatabaseManager.saveFile("txt", inventoryList, multipleMenuBar);
    }

    public void HTMLSaveButtonClicked(ActionEvent event)
    {
        DatabaseManager.saveFile("html", inventoryList, multipleMenuBar);
    }

    public void JSONSaveButtonClicked(ActionEvent event)
    {
        DatabaseManager.saveFile("json", inventoryList, multipleMenuBar);
    }

    // deletes selected item
    public void deleteBtnClicked(ActionEvent event)
    {
        // gets the selected item
        Item selectedItem = itemTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null)
        {
            // show alert
            InputChecker.showErrorAlert("Choose an Item from the list");

            throw new IllegalStateException();
        }

        // deletes the selected item
        inventoryList.deleteItem(selectedItem);
    }

    // switch to "Add Item" scene
    public void createItemBtnClicked(ActionEvent event)
    {
        sceneManager.switchToAddItemScene(event);
    }

    // shows the help screen
    public void userManualMenuCLicked(ActionEvent event)
    {
        sceneManager.switchToUserManual(multipleMenuBar);
    }

    public void closeMenuClicked(ActionEvent event)
    {
        Stage currStage = (Stage) multipleMenuBar.getScene().getWindow();

        // close application
        currStage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // table is editable
        itemTable.setEditable(true);
        itemTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // set the cell value for name column on edit attempt
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(event ->
        {
            Item editItem = event.getRowValue();
            editItem.setName(event.getNewValue());
            itemTable.refresh();
        });

        // set the cell value for serial number column on edit attempt
        serialNumCol.setCellValueFactory(new PropertyValueFactory<>("serialNum"));
        serialNumCol.setCellFactory(TextFieldTableCell.forTableColumn());
        serialNumCol.setOnEditCommit(event ->
        {
            Item editItem = event.getRowValue();
            String userNewInput = event.getNewValue();

            if (InputChecker.serialNumIsValid(userNewInput))
                editItem.setSerialNum(event.getNewValue());

            else
                InputChecker.showErrorAlert("Invalid Input");

            itemTable.refresh();
        });

        // set the cell value for money value column on edit attempt
        dolValCol.setCellValueFactory(new PropertyValueFactory<>("moneyValue"));
        dolValCol.setCellFactory(TextFieldTableCell.forTableColumn());
        dolValCol.setOnEditCommit(event ->
        {
            Item editItem = event.getRowValue();
            String userNewInput = event.getNewValue();

            if (InputChecker.moneyValIsValid(userNewInput))
                editItem.setMoneyValue(userNewInput);

            else
                InputChecker.showErrorAlert("Invalid Input");

            itemTable.refresh();
        });

        // creates filtered data, which enables the filters from the table and the search bar to work (dynamic search bar)
        FilteredList<Item> filteredData = new FilteredList<>(inventoryList.getItemList(), b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredData.setPredicate(employee ->
            {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }

                else if (employee.getSerialNum().toLowerCase().contains(lowerCaseFilter))
                {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(employee.getMoneyValue()).contains(lowerCaseFilter))
                    return true;

                else
                    return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Item> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(itemTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        itemTable.setItems(sortedData);
    }
}

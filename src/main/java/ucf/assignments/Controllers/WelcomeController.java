package ucf.assignments.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import ucf.assignments.DatabaseManager;
import ucf.assignments.Info.InventoryList;

import java.io.IOException;
import java.text.ParseException;

public class WelcomeController
{
    private InventoryList inventoryList;
    private SceneManager sceneManager;

    @FXML private Button howToUseBtn;
    @FXML private Button loadListBtn;
    @FXML private Button startNewListBtn;
    @FXML private MenuBar multipleMenuBar;

    public WelcomeController(InventoryList inventoryList, SceneManager sceneManager)
    {
        this.inventoryList = inventoryList;
        this.sceneManager = sceneManager;
    }

    // shows the help screen
    public void howToUseBtnClicked(ActionEvent event)
    {
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // sets new scene title
        String newTitle = "Help - Personal Inventory";

        // add extra screen (the help screen)
        sceneManager.addExtraScreen(currStage, newTitle, "Help");
    }

    // open a loading window - let user choose file and proceed to menu screen with saved info
    public void loadListBtnClicked(ActionEvent event) throws IOException, ParseException
    {
        // gets the loaded list from file
        InventoryList toLoadList = DatabaseManager.getFileFromUser(multipleMenuBar);

        // clear the map from original load and reload with loaded info
        sceneManager.listOfScenes.clear();
        sceneManager.initialLoad(toLoadList);

        // go to the main menu
        goToMenu(event);
    }

    // moves on normally to menu screen
    public void startNewListBtnClicked(ActionEvent event)
    {
        // go to the main menu
        goToMenu(event);
    }

    private void goToMenu(ActionEvent event)
    {
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String newTitle = "Menu - Personal Inventory";

        sceneManager.switchScene(currStage, newTitle, "MainMenu");
    }

}

package ucf.assignments.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ucf.assignments.Info.InventoryList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager
{
    public Map<String, Scene> listOfScenes = new HashMap<>();
    private final String LOGO_PATH = "ucf/assignments/Controllers/application_images/cool_backpack.png";

    // used for loading a new list
    public void initialLoad()
    {
        InventoryList inventoryList = new InventoryList();

        // initializes all the controllers that will be used in the application
        MainMenuController mainMenuController = new MainMenuController(inventoryList, this);
        AddItemController addItemController = new AddItemController(inventoryList, this);
        WelcomeController welcomeController = new WelcomeController(inventoryList, this);
        HelpController helpController = new HelpController();

        // load controllers into the map of scenes
        loadController("MenuScene.fxml", mainMenuController, "MainMenu");
        loadController("AddItemScene.fxml", addItemController, "AddItem");
        loadController("HelpScene.fxml", helpController, "Help");
        loadController("WelcomeScene.fxml", welcomeController, "Welcome");
    }

    // used for loading a pre-loaded list
    public void initialLoad(InventoryList loadedList)
    {
        // initializes all the controllers that will be used in the application
        MainMenuController mainMenuController = new MainMenuController(loadedList, this);
        AddItemController addItemController = new AddItemController(loadedList, this);
        WelcomeController welcomeController = new WelcomeController(loadedList, this);
        HelpController helpController = new HelpController();

        // load controllers into the map of scenes
        loadController("MenuScene.fxml", mainMenuController, "MainMenu");
        loadController("AddItemScene.fxml", addItemController, "AddItem");
        loadController("HelpScene.fxml", helpController, "Help");
        loadController("WelcomeScene.fxml", welcomeController, "Welcome");
    }

    private void loadController(String fileName, Object controller, String sceneName)
    {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        loader.setController(controller);

        try
        {
            root = loader.load();
            listOfScenes.put(sceneName, new Scene(root)); //  what is this?
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void switchScene(Stage currStage, String sceneTitle, String sceneName)
    {
        Stage newStage = new Stage();

        // sets the new stage
        newStage.setTitle(sceneTitle);
        newStage.setScene(this.getScene(sceneName));
        newStage.getIcons().add(new Image(LOGO_PATH));

        // shows new stage and close the old one
        newStage.show();
        currStage.close();
    }

    public void addExtraScreen(Stage currStage, String sceneTitle, String sceneName)
    {
        Stage newStage = new Stage();

        // sets the new stage
        newStage.setTitle(sceneTitle);
        newStage.setScene(this.getScene(sceneName));
        newStage.getIcons().add(new Image(LOGO_PATH));

        // shows new stage and close the old one
        newStage.show();
    }

    public void switchToMainMenuScene(ActionEvent event)
    {
        // gets info for scene change
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String newTitle = "Menu - Personal Inventory";

        // changes to main menu scene
        this.switchScene(currStage, newTitle, "MainMenu");
    }

    public void switchToAddItemScene(ActionEvent event)
    {
        // gets info for scene change
        Stage currStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String newTitle = "Add New Item - Personal Inventory";

        // changes to add item scene
        this.switchScene(currStage, newTitle, "AddItem");
    }

    public void switchToUserManual(MenuBar multipleMenuBar)
    {
        // gets the current stage as the menu bar
        Stage currStage = (Stage) multipleMenuBar.getScene().getWindow();

        // sets new scene title
        String newTitle = "Help - Personal Inventory";

        // add extra screen (the help screen)
        this.addExtraScreen(currStage, newTitle, "Help");
    }

    public Scene getScene(String name)
    {
        return listOfScenes.get(name);
    }
}
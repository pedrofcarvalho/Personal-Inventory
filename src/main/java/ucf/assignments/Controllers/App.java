package ucf.assignments.Controllers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        try
        {
            // creates scene manager and loads scenes info to it
            SceneManager applicationSceneManager = new SceneManager();
            applicationSceneManager.initialLoad();

            // gets welcome scene
            Scene scene = applicationSceneManager.getScene("Welcome");

            // sets scene info
            primaryStage.setTitle("Welcome - Personal Inventory");
            primaryStage.getIcons().add(new Image("ucf/assignments/Controllers/application_images/cool_backpack.png"));

            // show the scene
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        // starts application
        launch(args);
    }
}

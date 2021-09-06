package ucf.assignments.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable
{
    @FXML private TextArea text1;
    @FXML private TextArea text2;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // sets the text areas to non-editable
        text1.setEditable(false);
        text2.setEditable(false);
    }
}
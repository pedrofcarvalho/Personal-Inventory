package ucf.assignments;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputChecker
{
    public static boolean checksForDollarSign(String givenString)
    {
        // transforms the given string into a char array
        char [] stringAsArr = givenString.toCharArray();

        // returns true if there's dollar sign on index 0
        return stringAsArr[0] == '$';
    }

    private static String removesDollarSign(String givenString)
    {
        // converts the String to char array
        char [] giveStrAsArr = givenString.toCharArray();
        int arrLength = giveStrAsArr.length - 1;

        // if there's a '$' in the String, we make a copy of the String without it and return the new String
        if (giveStrAsArr[0] == '$')
        {
            // initializes the new char array and index variable
            char [] newArr = new char[arrLength];
            int index = 0;

            while (index < arrLength)
            {
                newArr[index] = giveStrAsArr[index + 1];
                index++;
            }

            return String.valueOf(newArr);
        }

        return givenString;
    }

    public static void showErrorAlert(String toWriteText)
    {
        // creates the dialog box as an error alert
        Dialog<ButtonType> d = new Alert(Alert.AlertType.ERROR);;

        d.setContentText(toWriteText);
        d.show();
    }

    // checks if input is wrong
    public static int inputIsBad(String nameInput, String serialNumInput, String moneyValInput)
    {
        // checks if moneyVal is empty
        if (nameInput.isEmpty())
        {
            return 1;
        }

        if (!nameIsValid(nameInput))
        {
            return 2;
        }

        // checks if moneyVal is empty
        if (serialNumInput.isEmpty())
        {
            return 3;
        }

        if (!serialNumIsValid(serialNumInput))
        {
            return 4;
        }

        // checks if moneyVal is empty
        try {
            if (moneyValInput.isEmpty())
            {
                return 5;
            }
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
            return -1;
        }

        if (!moneyValIsValid(moneyValInput))
        {
            return 6;
        }

        return 0;
    }

    // returns true if there is a symbol in the string
    private static boolean containSymbolOnName(String str)
    {
        // checks the pattern for only numbers regex
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);

        // matches regex with given string
        Matcher m = p.matcher(str);

        // decides if there's special characters on given string
        if (m.find())
            return true;

        // there's no problems with the string
        return false;
    }

    public static boolean serialNumIsValid(String serialNumInput)
    {
        int CORRECT_LENGTH = 10;

        if (serialNumInput.length() != CORRECT_LENGTH)
        {
            return false;
        }

        if (containSymbolOnName(serialNumInput))
        {
            return false;
        }

        // if everything is good, return true
        return true;
    }

    public static boolean nameIsValid(String nameInput)
    {
        int MIN_LENGTH = 2;
        int MAX_LENGTH = 256;
        int length = nameInput.length();

        // if name is bad, return false
        if (length < MIN_LENGTH || length > MAX_LENGTH)
        {
            return false;
        }

        // else, return true
        return true;
    }

    public static boolean moneyValIsValid(String moneyValInput)
    {
        try
        {
            if (checksForDollarSign(moneyValInput))
            {
                moneyValInput = removesDollarSign(moneyValInput);
            }

            // attempts to convert the string number to a double
            double inputAsDouble = Double.parseDouble(moneyValInput);

            // checks if the item has more than 2 decimals, and if it does, return false
            return !((BigDecimal.valueOf(inputAsDouble).scale() > 2));
        }

        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}

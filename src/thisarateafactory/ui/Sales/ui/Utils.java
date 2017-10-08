/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Mihitha Hansani
 */
public class Utils {
    public static void showValidationAlert( String header, String message)
    {
        showAlert( Alert.AlertType.WARNING, "Invalid Input", header, message );
    }
    
    public static Optional<ButtonType> showAlert( Alert.AlertType alertType, String title, String header, String message)
    {
        Alert alert = new Alert( alertType );
        alert.setTitle( title );
        alert.setHeaderText( header );
        alert.setContentText( message );
 
        return alert.showAndWait();
    }
    
    public static double round( double value, int decimalPoints )
    {
        return Math.round( value * Math.pow(10, decimalPoints) ) / Math.pow(10, decimalPoints);
    }
}

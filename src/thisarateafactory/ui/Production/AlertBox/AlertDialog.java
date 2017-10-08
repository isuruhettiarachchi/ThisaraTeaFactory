/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.AlertBox;

//import com.sun.glass.ui.Window;
//import java.awt.Label;
//import static java.awt.SystemColor.window;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Nipuni
 */
public class AlertDialog {
    public static void display(String title, String message)
    {
    Stage window = new Stage();
    window.setTitle(title);
    window.setMinWidth(250);
    window.setMaxHeight(100);
    
    Label label = new Label();
    label.setText(message);
    Button buttonOK = new Button("OK");
    buttonOK.setOnAction(e -> window.close());
    
    VBox layout = new VBox(5);
    layout.getChildren().addAll(label, buttonOK);
    layout.setAlignment(Pos.CENTER);
    
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();
    
    
    
    
    }
    
    
}

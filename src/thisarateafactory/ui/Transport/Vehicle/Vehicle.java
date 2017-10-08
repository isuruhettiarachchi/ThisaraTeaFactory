package thisarateafactory.ui.Transport.Vehicle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Vehicle extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("vehicle.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}


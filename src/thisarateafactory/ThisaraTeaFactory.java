/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class ThisaraTeaFactory extends Application {
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/thisarateafactory/login/login.fxml"));
        
        scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMaxHeight(400);
        stage.setMaxWidth(600);
        stage.setTitle("Login");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

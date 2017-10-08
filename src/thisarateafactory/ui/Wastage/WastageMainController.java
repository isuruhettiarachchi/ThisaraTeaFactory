/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import thisarateafactory.ui.HRM.EmployeeManagementController;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class WastageMainController implements Initializable {
    private Stage stage;
    
    private Stage stage1;
    private Scene wasteScene;
    private Scene manufactScene;
    private Scene salesScene;
    private Scene stockScene;
    @FXML
    private AnchorPane wastageMain;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        wastageMain.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) wastageMain.getScene().getWindow();
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                try {
                                    System.out.println("exiting");
                                    Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/Main.fxml"));
                                    Stage stage = new Stage(StageStyle.DECORATED);
                                    stage.setResizable(false);
                                    stage.setTitle("Thisara Management System");
                                    
                                    stage.setScene(new Scene(parent));
                                    stage.show();   } catch (IOException ex) {
                                    Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        });
                    }
                });
            }
        });
        
        this.stage1 = new Stage();
    }    

    @FXML
    private void wasteProduction(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Wastage/Waste/Waste.fxml"));
            wasteScene = new Scene(SuppliersParent);
            stage1.setScene(wasteScene);
            stage1.setTitle("Waste Production");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void stockLoader(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Wastage/Stock/Stock.fxml"));
            stockScene = new Scene(SuppliersParent);
            stage1.setScene(stockScene);
            stage1.setTitle("Wastage Stock");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void sales(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Wastage/Sale/Sale.fxml"));
            salesScene = new Scene(SuppliersParent);
            stage1.setScene(salesScene);
            stage1.setTitle("Sales");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void manufacturing(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Wastage/Manufact/Manufact.fxml"));
            manufactScene = new Scene(SuppliersParent);
            stage1.setScene(manufactScene);
            stage1.setTitle("Sales");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
}

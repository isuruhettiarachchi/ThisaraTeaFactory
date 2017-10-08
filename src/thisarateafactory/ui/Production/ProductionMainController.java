package thisarateafactory.ui.Production;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
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
public class ProductionMainController implements Initializable {
    private Stage stage;
    
    private Stage stage1;
    private Scene dailyProductScene;
    private Scene qualityScene;
    private Scene packageScene;
    private Scene groceryScene;

    @FXML
    private JFXButton dailyProductButton;
    @FXML
    private JFXButton qualityButton;
    @FXML
    private JFXButton packageButton;
    @FXML
    private JFXButton groceryButton;
    @FXML
    private AnchorPane productionMainPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        productionMainPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) productionMainPane.getScene().getWindow();
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
    private void loadDailyProduct(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Production/thisara/FXMLDocument.fxml"));
            dailyProductScene = new Scene(SuppliersParent);
            stage1.setScene(dailyProductScene);
            stage1.setTitle("Daily Production");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadQuality(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Production/Quality/qualityProduct.fxml"));
            qualityScene = new Scene(SuppliersParent);
            stage1.setScene(qualityScene);
            stage1.setTitle("Quality Seperation");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadPackage(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Production/Packing/PackingProduct.fxml"));
            packageScene = new Scene(SuppliersParent);
            stage1.setScene(packageScene);
            stage1.setTitle("Packaging");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadGrocery(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Production/Grocery/grocery.fxml"));
            groceryScene = new Scene(SuppliersParent);
            stage1.setScene(groceryScene);
            stage1.setTitle("Grocery");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

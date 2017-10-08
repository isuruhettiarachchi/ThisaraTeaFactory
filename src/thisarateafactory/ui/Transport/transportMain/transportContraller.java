/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Transport.transportMain;

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
 * @author Dexter
 */
public class transportContraller implements Initializable {
    private Stage stage;
    
    private Stage stage1;
    private Scene driverScene;
    private Scene vehicleScene;
    private Scene turnScene;
    private Scene maintainScene;

    @FXML
    private JFXButton drvB;
    @FXML
    private JFXButton vhB;
    @FXML
    private JFXButton turnB;
    @FXML
    private JFXButton maintainB;
    @FXML
    private AnchorPane transportMainPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transportMainPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) transportMainPane.getScene().getWindow();
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
    private void openDriver(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Transport/Driver/driver.fxml"));
            driverScene = new Scene(SuppliersParent);
            stage1.setScene(driverScene);
            stage1.setTitle("Manage Drivers");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void openVehicle(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Transport/Vehicle/vehicle.fxml"));
            vehicleScene = new Scene(SuppliersParent);
            stage1.setScene(vehicleScene);
            stage1.setTitle("Manage Vehicles");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void openTurn(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Transport/Turn/turn.fxml"));
            turnScene = new Scene(SuppliersParent);
            stage1.setScene(turnScene);
            stage1.setTitle("Transport Turns");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void openMaintain(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Transport/Maintain/maintain.fxml"));
            maintainScene = new Scene(SuppliersParent);
            stage1.setScene(maintainScene);
            stage1.setTitle("Vehicle Maintenence");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            //Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

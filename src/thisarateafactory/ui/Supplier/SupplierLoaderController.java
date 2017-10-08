package thisarateafactory.ui.Supplier;

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
public class SupplierLoaderController implements Initializable {
    private Stage stage;
    
    private Stage stage1;
    private Scene supplierScene;
    private Scene teaCardScene;
    private Scene supplierPaymentScene;
    private Scene monthlyBulkScene;
    

    @FXML
    private JFXButton supplierButton;
    @FXML
    private JFXButton teaCardButton;
    @FXML
    private JFXButton paymentButton;
    @FXML
    private JFXButton monthlyButton;
    @FXML
    private AnchorPane supplierMainPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        supplierMainPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) supplierMainPane.getScene().getWindow();
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
    private void loadSuppliers(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Supplier/newthisara/FXMLDocument.fxml"));
            supplierScene = new Scene(SuppliersParent);
            stage1.setScene(supplierScene);
            stage1.setTitle("Supplier Details");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void loadTeaCard(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Supplier/Teacard/Teaturn.fxml"));
            teaCardScene = new Scene(SuppliersParent);
            stage1.setScene(teaCardScene);
            stage1.setTitle("Tea Collection Card");
            
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadPayment(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Supplier/Payment/Suppay.fxml"));
            supplierPaymentScene = new Scene(SuppliersParent);
            stage1.setScene(supplierPaymentScene);
            stage1.setTitle("Supplier Payment");
            
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadMonthly(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Supplier/Monthly/MB.fxml"));
            monthlyBulkScene = new Scene(SuppliersParent);
            stage1.setScene(monthlyBulkScene);
            stage1.setTitle("Monthly Bulk");
            
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

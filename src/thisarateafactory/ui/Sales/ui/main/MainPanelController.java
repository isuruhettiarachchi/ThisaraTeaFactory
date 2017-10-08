/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.main;

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
import thisarateafactory.ui.Supplier.SupplierLoaderController;

/**
 * FXML Controller class
 *
 * @author Mihitha Hansani
 */
public class MainPanelController implements Initializable {

    @FXML
    private JFXButton btnSalesSummary;
    @FXML
    private JFXButton btnReturnSales;
    @FXML
    private JFXButton btnDeductions;
    @FXML
    private JFXButton btnFinalizing;
    @FXML
    private AnchorPane salesMainPane;
    
    private Stage stage;
    
    private Stage stage1;
    private Scene SummaryScene;
    private Scene ReturnScene;
    private Scene DeductionsScene;
    private Scene FinalizeScene;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        salesMainPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) salesMainPane.getScene().getWindow();
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
    private void salesSummaryButtonPressed(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Sales/ui/salessummary/SalesSummary.fxml"));
            SummaryScene = new Scene(SuppliersParent);
            stage1.setScene(SummaryScene);
            stage1.setTitle("Sales Summary");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void returnSalesButtonPressed(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Sales/ui/returnsale/ReturnSales.fxml"));
            ReturnScene = new Scene(SuppliersParent);
            stage1.setScene(ReturnScene);
            stage1.setTitle("Return Sales");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DeductionsButtonPressed(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Sales/ui/deductions/Deductions.fxml"));
            DeductionsScene = new Scene(SuppliersParent);
            stage1.setScene(DeductionsScene);
            stage1.setTitle("Deduction Sales");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void finalizingButtonPressed(ActionEvent event) {
        try {
            Parent SuppliersParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Sales/ui/finalizing/Finalizing.fxml"));
            FinalizeScene = new Scene(SuppliersParent);
            stage1.setScene(FinalizeScene);
            stage1.setTitle("Deduction Sales");
            stage1.show();
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(SupplierLoaderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

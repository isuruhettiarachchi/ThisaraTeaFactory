/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import thisarateafactory.login.LoginController;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class MainController implements Initializable {
    Stage mainStage;
    Stage changeSettingsStage;
    
    @FXML
    private JFXButton employeeButton;
    @FXML
    private JFXButton financeButton;
    @FXML
    private JFXButton assetButton;
    @FXML
    private JFXButton supplierButton;
    @FXML
    private JFXButton productionButton;
    @FXML
    private JFXButton salesButton;
    @FXML
    private JFXButton transportButton;
    @FXML
    private JFXButton wastageButton;
    @FXML
    private AnchorPane MainWindow;
    @FXML
    private JFXButton user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setText(LoginController.returnUser());
        
        /*MainWindow.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        mainStage = (Stage) MainWindow.getScene().getWindow();
                        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                System.out.println("exiting");
                            }
                            
                        });
                    }
                });
            }
        });*/
    }    

    @FXML
    private void loadEmployee(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/EmployeeManagement.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Employee Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadFinance(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Finance/thisara/FXMLDocument.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Finance Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadAsset(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Asset/AssetManagement/FXMLDocument.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Asset Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadSupply(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Supplier/SupplierLoader.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Supply Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadProduction(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Production/ProductionMain.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Production Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadSales(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
            
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Sales/ui/main/MainPanel.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Sales Management");
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadTransport(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("ui/Transport/transportMain/transportM.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Transport Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadWastage(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/Wastage/WastageMain.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Wastage Management");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void changeSettings(ActionEvent event) {
        try {
                Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/accountSettings.fxml"));
                changeSettingsStage = new Stage(StageStyle.DECORATED);
                changeSettingsStage.setResizable(false);
                changeSettingsStage.setTitle("Thisara Management System");
                
                changeSettingsStage.setScene(new Scene(parent));
                changeSettingsStage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}

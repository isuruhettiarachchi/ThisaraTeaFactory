/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.HRM.EmployeeManagementController;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class CreateAccountController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private PasswordField confirmPasswordText;
    @FXML
    private Label warningLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        
        passwordText.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        warningLabel.setText("");
                    }
                }
        });
        
        confirmPasswordText.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        warningLabel.setText("");
                    }
                }
        });
        
        
        
//         usernameText.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
//            if(oldScene == null && newScene != null){
//                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
//                    if(oldWindow == null && newWindow != null){
//                        stage1 = (Stage) usernameText.getScene().getWindow();
//                        stage1.setOnCloseRequest(new EventHandler<WindowEvent>(){
//                            @Override
//                            public void handle(WindowEvent event) {
//                                try {
//                                    Parent root = FXMLLoader.load(getClass().getResource("/thisarateafactory/login/login.fxml"));
//                                    
//                                    Scene scene = new Scene(root);
//                                    
//                                    stage.setScene(scene);
//                                    stage.setResizable(false);
//                                    stage.setMaxHeight(400);
//                                    stage.setMaxWidth(600);
//                                    stage.setTitle("Login");
//                                    stage.show();   } catch (IOException ex) {
//                                    Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            }
//                            
//                        });
//                    }
//                });
//            }
//        });
    }    

    @FXML
    private void createLogin(ActionEvent event) {
        
        String username = usernameText.getText();
        String pass1 = passwordText.getText();
        String pass2 = confirmPasswordText.getText();
        
        if(!pass1.matches(pass2)){
            warningLabel.setText("Password does not match");
            return;
        }
        
        String query = "insert into admin(username, password) values(?, ?)";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, pass2);
            
            int x = pst.executeUpdate();
            
            if(x == 1){
                System.out.println("Login Created");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText(null);
                alert.setContentText("New login created successfully");
                alert.showAndWait();
                
                try {
                    ((Node)event.getSource()).getScene().getWindow().hide();
                
                    Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/login/login.fxml"));
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setResizable(false);
                    stage.setTitle("Thisara Management System");
                
                    stage.setScene(new Scene(parent));
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void cancel(ActionEvent event) {
        try {
            ((Node)event.getSource()).getScene().getWindow().hide();
                
            Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/login/login.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setResizable(false);
            stage.setTitle("Thisara Management System");
                
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

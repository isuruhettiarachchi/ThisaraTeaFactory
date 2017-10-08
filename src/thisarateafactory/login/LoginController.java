/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.login;

import com.jfoenix.controls.JFXButton;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class LoginController implements Initializable {
    private static String user;
    
    Connection connection = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private TextField usernameText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private Label warningLabel;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();//get database connection instance
        
        //warningFieldReset
        usernameText.focusedProperty().addListener(new ChangeListener<Boolean>()
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
        //end
        
    }    

    @FXML
    private void login(ActionEvent event) {
        user = usernameText.getText();
        String pass = passwordText.getText();
        String password = null;
        
        String query = "select password from admin where username = ?";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, user);
            
            rs = pst.executeQuery();
            
            System.out.println("Dataset recieved");
            
            while(rs.next()){
                password = rs.getString("password");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(pass.equals(password)){
            System.out.println("password matched");
            
            try {
                ((Node)event.getSource()).getScene().getWindow().hide();
                
                Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/Main.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("Thisara Management System");
                
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            String warning = "Username and password does not match";
            warningLabel.setText(warning);
            System.out.println("password does not match");
        }
    }
    
    

    @FXML
    private void cancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void createAccount(ActionEvent event) {
        try {
                ((Node)event.getSource()).getScene().getWindow().hide();
                
                Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/login/createAccount.fxml"));
                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setResizable(false);
                stage.setTitle("Create New Login");
                
                stage.setScene(new Scene(parent));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static String returnUser(){
        return user;
    }
    
}

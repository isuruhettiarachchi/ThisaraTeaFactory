/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.main;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class AccountSettingsController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    

    @FXML
    private TextField currentUsername;
    @FXML
    private PasswordField currentPassword;
    @FXML
    private PasswordField chngPassword;
    @FXML
    private PasswordField chngPasswordConfirm;
    @FXML
    private TextField newUsername;
    @FXML
    private PasswordField createPassword;
    @FXML
    private PasswordField createPasswordNew;
    @FXML
    private Label warningLabelNew;
    @FXML
    private Label warningLabelChange;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        
        chngPassword.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        warningLabelChange.setText("");
                    }
                }
        });
        
        chngPasswordConfirm.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        warningLabelChange.setText("");
                    }
                }
        });
        
        currentPassword.focusedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
                {
                    if (newPropertyValue)
                    {
                        warningLabelChange.setText("");
                    }
                }
        });
    }    

    @FXML
    private void changePassword(ActionEvent event) {
        String user = currentUsername.getText();
        String curPass = currentPassword.getText();
        String newPass = chngPassword.getText();
        String newPassConfirm = chngPasswordConfirm.getText();
        String dbPass = null;
        
        if(!newPass.matches(newPassConfirm)){
            warningLabelChange.setText("Password does not match");
            return;
        }
        
        String query = "select password from admin where username = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, user);
            rs = pst.executeQuery();
            
            while(rs.next()){
                dbPass = rs.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(dbPass == null){
            warningLabelChange.setText("Current password does not match");
        } else if(dbPass.matches(curPass)){
            query = "update admin set password = ? where username = ?";
            try {
                pst = connection.prepareStatement(query);
                pst.setString(1, newPassConfirm);
                pst.setString(2, user);
                int x = pst.executeUpdate();
                if(x==1){
                    System.out.println("password updated successfully");
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountSettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @FXML
    private void createAccount(ActionEvent event) {
        String user = newUsername.getText();
        String pass = createPassword.getText();
        String passNew = warningLabelNew.getText();
        
        if(!pass.matches(passNew)){
            warningLabelNew.setText("Password does not match");
            return;
        }
        
        String query = "insert into(username, password) values(?, ?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, user);
            pst.setString(2, passNew);
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("New user added");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

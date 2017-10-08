/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.AddEmployee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;
import static thisarateafactory.validation.validateTextFields.validateNIC;
import static thisarateafactory.validation.validateTextFields.validateName;
import static thisarateafactory.validation.validateTextFields.validateNumbersOnly;
import static thisarateafactory.validation.validateTextFields.validatePhone;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class AddNewEmployeeController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    List designationList = new ArrayList();
    ObservableList<String> designationObsList;
    
    private String gender;
    int eid = 0;

    @FXML
    private JFXTextField eNameText;
    @FXML
    private JFXTextField eNICText;
    @FXML
    private JFXComboBox<String> designationCombo;
    @FXML
    private JFXTextArea eAddrText;
    @FXML
    private JFXRadioButton maleRadio;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private JFXRadioButton femaleRadio;
    @FXML
    private DatePicker birthDate;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXButton finishButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private Label eidLabel;
    @FXML
    private DatePicker assignDate;
    @FXML
    private DatePicker epfDate;
    @FXML
    private JFXTextField nNameText;
    @FXML
    private JFXTextField nAgeText;
    @FXML
    private JFXTextField nShareAmountText;
    @FXML
    private JFXTextField nRelationshipText;
    @FXML
    private JFXTextArea nAddressText;
    @FXML
    private Label personalDataWarning;
    @FXML
    private static AnchorPane addEmployeeMain;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        gender = null;
        
        loadCombo();
        getNextEID();
        
        emptyValidation(eNameText, "Name can not be empty");
        emptyValidation(eNICText, "NIC can not be empty");
        emptyValidation(nNameText, "Name can not be empty");
        emptyValidation(nAgeText, "Age can not be empty");
        emptyValidation(nRelationshipText, "Relationship can not be empty");
        numberValidation(nAgeText, "Invalid age");
        numberValidation(phoneText, "Invalid phone number");
        doubleValidation(nShareAmountText, "Invalid share amount");
    } 
    
    @FXML
    private void add(ActionEvent event) {
        String eName = eNameText.getText();
        String eNIC = eNICText.getText();
        String designation = (String) designationCombo.getValue();
        String eAddr = eAddrText.getText();
        //get global variable gender
        LocalDate bDate = birthDate.getValue();
        String phone = phoneText.getText();
        LocalDate appDate = assignDate.getValue();
        LocalDate etfDate = epfDate.getValue();
        
        String nName = nNameText.getText();
        String nAge = nAgeText.getText();
        String share = nShareAmountText.getText();
        System.out.println("return check");
        String relation = nRelationshipText.getText();
        String nAddr = nAddressText.getText();
        
        if(eName == null || eName.trim().isEmpty() || !validateName(eName)){
            errorDialog("Employee name can not be empty");
            return;
        }
        
        if(eNIC == null || eNIC.trim().isEmpty() || !validateNIC(eNIC)){
            errorDialog("Employee NIC is empty or invalid");
            return;
        }
        
        
        if(designation == null || designation.trim().isEmpty()){
            errorDialog("Employee designation can not be empty");
            return;
        }
        
        if(eAddr == null || eAddr.trim().isEmpty()){
            errorDialog("Employee address can not be empty");
            return;
        }
        
        if(gender == null || gender.trim().isEmpty()){
            errorDialog("Employee gender can not be empty");
            return;
        }
        
        if(bDate == null || bDate.toString().trim().isEmpty()){
            errorDialog("Employee birthdate can not be empty");
            return;
        }
        
        if(phone == null || phone.trim().isEmpty() || !validatePhone(phone)){
            errorDialog("Employee phone number can not be empty");
            return;
        }
        
        if(appDate == null || appDate.toString().trim().isEmpty()){
            errorDialog("Employee appointed date can not be empty");
            return;
        }
        
        if(etfDate == null|| etfDate.toString().trim().isEmpty()){
            errorDialog("Employee EPT/ETF start date can not be empty");
            return;
        }
        
        if(nName == null || nName.trim().isEmpty() || !validateName(nName)){
            errorDialog("Employee nominated name can not be empty");
            return;
        }
        
        if(nAge == null|| nAge.trim().isEmpty() || !validateNumbersOnly(nAge)){
            errorDialog("Nominee age can not be empty");
            return;
        }
        
        if(share == null || share.trim().isEmpty() || !validateCurrency(share)){
            errorDialog("Employee share amount can not be empty");
            return;
        }
        
        if(relation == null || relation.trim().isEmpty()){
            errorDialog("Employee and nominee relationship date can not be empty");
            return;
        }
        
        if(nAddr == null || nAddr.trim().isEmpty()){
            errorDialog("Nominee address can not be empty");
            return;
        }
        
        String query;
        query = "insert into employee(NIC, Name, Designation, Address, Gender, Birthdate, Phone, DateAppointed, EPFETFDate)"+
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, eNIC);
            pst.setString(2, eName);
            pst.setString(3, designation);
            pst.setString(4, eAddr);
            pst.setString(5, gender);
            pst.setDate(6, java.sql.Date.valueOf(bDate));
            pst.setString(7, phone);
            pst.setDate(8, java.sql.Date.valueOf(appDate));
            pst.setDate(9, java.sql.Date.valueOf(etfDate));
            
            int x = pst.executeUpdate();
            
            if(x==1){
                System.out.println("Data insertion successful");
                addBCard();
                //((Node)event.getSource()).getScene().getWindow().hide();
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText(null);
                alert.setContentText("New employee details added successfully");
                alert.showAndWait();
                ((Node)event.getSource()).getScene().getWindow().hide();

            }
        } catch (SQLException ex) {
            errorDialog("Error occrued during data insertion");
        }
        
    }
    
    @FXML
    private void cancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
    private void getNextEID(){
        String query = "select max(EmployeeId) as currentEID from employee";
        try {
            System.out.println("q");
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                eid = rs.getInt("currentEID") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        eidLabel.setText(String.valueOf(eid));
        System.out.println(eid);
    }
    
    private void loadCombo(){
        connection = databaseHandler.getConnection();
        
        String query = "select Designation from Designation";
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String current = rs.getString("Designation");
                designationList.add(current);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        designationObsList = FXCollections.observableArrayList(designationList);
        designationCombo.setItems(designationObsList);
        
    }

    @FXML
    private void setMale(ActionEvent event) {
        gender = "Male";
    }

    @FXML
    private void setFemale(ActionEvent event) {
        gender = "Female";
    }
    
    private void addBCard(){
        String nName = nNameText.getText();
        int nAge = Integer.parseInt(nAgeText.getText());
        Double share = Double.parseDouble(nShareAmountText.getText());
        String relation = nRelationshipText.getText();
        String nAddr = nAddressText.getText();
        
        String query ="insert into Nominee(Name, EmployeeID, Age, ShareAmount, Relationship, Address)"+
                "values(?, ?, ?, ?, ?, ?)";
        
        try {
            pst = connection.prepareStatement(query);
            System.out.println("check");
            pst.setString(1, nName);
            pst.setInt(2, eid);
            pst.setInt(3, nAge);
            pst.setDouble(4, share);
            pst.setString(5, relation);
            pst.setString(6, nAddr);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("BCard data insertion successfull");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(AddNewEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void emptyValidation(JFXTextField input, String warning){
        RequiredFieldValidator validator = new RequiredFieldValidator();
        input.getValidators().add(validator);
        validator.setMessage(warning);
        input.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    input.validate();
                }
            }
        });
    }
    
    private void numberValidation(JFXTextField input, String warning){
        NumberValidator validator = new NumberValidator();
        input.getValidators().add(validator);
        validator.setMessage(warning);
        input.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    input.validate();
                }
            }
        });
    }
    
    private void doubleValidation(JFXTextField input, String warning){
        DoubleValidator validator = new DoubleValidator();
        input.getValidators().add(validator);
        validator.setMessage(warning);
        input.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    input.validate();
                }
            }
        });
    }
    
    private void errorDialog(String erMsg){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
        
    }
    
}

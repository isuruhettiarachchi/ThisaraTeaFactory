/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.ViewEmployee;

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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.HRM.AddEmployee.AddNewEmployeeController;
import thisarateafactory.ui.HRM.EmployeeManagementController;
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
public class ViewSelectedEmployeeController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    private String eid;
    
    List designationList = new ArrayList();
    ObservableList<String> designationObsList;
    
    @FXML
    private JFXTextField eNameText;//
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private Label EIDLabel;
    @FXML
    private JFXTextField eNICText;//
    @FXML
    private JFXComboBox<String> designationText;
    @FXML
    private JFXTextArea eAddrText;//
    @FXML
    private JFXRadioButton maleRadio;
    @FXML
    private JFXRadioButton femaleRadio;
    @FXML
    private DatePicker birthDate;//
    @FXML
    private JFXTextField phoneText;//
    @FXML
    private DatePicker appDate;//
    @FXML
    private DatePicker epfDate;//
    @FXML
    private DatePicker resignedDate;//
    @FXML
    private JFXTextField nNameText;//
    @FXML
    private JFXTextField nAgeText;//
    @FXML
    private JFXTextField shareAmountText;//
    @FXML
    private JFXTextField nRelationshipText;//
    @FXML
    private JFXTextArea nAddrText;//
    @FXML
    private JFXButton updateButton;
    @FXML
    private JFXButton cancelButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        eid = String.valueOf(EmployeeManagementController.getEID());
        EIDLabel.setText(eid);
        updateButton.setDisable(true);
        
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        LocalDate localDate;
        
        loadCombo();
        loadEmployeeData();
        loadNomineeData();
        
        checkTFValueChanges(eNameText);
        checkTFValueChanges(eNICText);
        checkTFValueChanges(phoneText);
        checkTFValueChanges(nAgeText);
        checkTFValueChanges(shareAmountText);
        checkTFValueChanges(nRelationshipText);
        checkTFValueChanges(nNameText);
        
        checkTAValueChanges(eAddrText);
        checkTAValueChanges(nAddrText);
        
        checkCalValueChanges(birthDate);
        checkCalValueChanges(appDate);
        checkCalValueChanges(epfDate);
        checkCalValueAdd(resignedDate);
        
        checkComboValueChanges(designationText);
        
        checkRadioValueChanges(maleRadio);
        checkRadioValueChanges(femaleRadio);
        
        emptyValidation(eNameText, "Name can not be empty");
        emptyValidation(eNICText, "NIC can not be empty");
        emptyValidation(nNameText, "Name can not be empty");
        emptyValidation(nAgeText, "Age can not be empty");
        emptyValidation(nRelationshipText, "Relationship can not be empty");
        numberValidation(nAgeText, "Invalid age");
        numberValidation(phoneText, "Invalid phone number");
        doubleValidation(shareAmountText, "Invalid share amount");
    }    

    @FXML
    private void update(ActionEvent event) {
        updateEmployee();
        updateNominee();
    }

    @FXML
    private void cancel(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    private void loadEmployeeData() {
        System.out.println("check");
        String query = "select * from employee where EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, eid);
            rs = pst.executeQuery();
            
            while(rs.next()){
                EIDLabel.setText(eid);
                eNameText.setText(rs.getString("Name"));
                eNICText.setText(rs.getString("NIC"));
                eAddrText.setText(rs.getString("Address"));
                String gender = rs.getString("Gender");
                    if(gender.matches("male")){
                        maleRadio.setSelected(true);
                    }
                    else if(gender.matches("female")){
                        femaleRadio.setSelected(true);
                    }
                birthDate.setValue(rs.getDate("Birthdate").toLocalDate());
                phoneText.setText(rs.getString("Phone"));
                appDate.setValue(rs.getDate("DateAppointed").toLocalDate());
                String desig = rs.getString("Designation");
                designationText.setValue(desig);
                if(rs.getDate("DateResigned") != null){
                    resignedDate.setValue(rs.getDate("DateResigned").toLocalDate());
                }
                if(rs.getDate("EPFETFDate") != null){
                    epfDate.setValue(rs.getDate("EPFETFDate").toLocalDate());
                }
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewSelectedEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            //errorDialog("Error occrued during data insertion");
        }
    }

    private void loadNomineeData() {
        System.out.println("check2");
        String query = "select * from nominee where employeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, eid);
            rs = pst.executeQuery();
            
            while(rs.next()){
                nNameText.setText(rs.getString("Name"));
                nAgeText.setText(String.valueOf(rs.getInt("Age")));
                shareAmountText.setText(String.valueOf(rs.getDouble("ShareAmount")));
                nRelationshipText.setText(rs.getString("Relationship"));
                nAddrText.setText(rs.getString("Address"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewSelectedEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            //errorDialog();
        }
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
            //errorDialog();
        }
        designationObsList = FXCollections.observableArrayList(designationList);
        designationText.setItems(designationObsList);
    }

    private void updateEmployee() {
        String eName = eNameText.getText();
        String eNIC = eNICText.getText();
        String designation = (String) designationText.getValue();
        String eAddr = eAddrText.getText();
        String gender = null;
        if(maleRadio.isSelected()){
            gender = "male";
        }else if(femaleRadio.isSelected()){
            gender = "female";
        }
        System.out.println(gender);
        //gender = genderGroup.getSelectedToggle().getUserData().toString();
        LocalDate bDate = birthDate.getValue();
        String phone = phoneText.getText();
        LocalDate appointedDate = appDate.getValue();
        LocalDate etfDate = epfDate.getValue();
        LocalDate resignDate;
        Date resDate;
        if(resignedDate.getValue()==null){
            //resignDate = null;
            resDate = null;
        }else{
            resignDate = resignedDate.getValue();
            resDate = java.sql.Date.valueOf(resignDate);
        }
        
        if(eName == null || eName.trim().isEmpty() || !validateName(eName)){
            errorDialog("Employee name can not be empty");
            return;
        }
        
        if(eNIC == null || eNIC.trim().isEmpty() || !validateNIC(eNIC)){
            errorDialog("Employee NIC can not be empty");
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
        
        if(birthDate.getValue().toString() == null || birthDate.getValue().toString().trim().isEmpty()){
            errorDialog("Employee birthdate can not be empty");
            return;
        }
        
        if(phone == null || phone.trim().isEmpty() || !validatePhone(phone)){
            errorDialog("Employee phone number can not be empty");
            return;
        }
        
        if(appDate.getValue().toString() == null || appDate.getValue().toString().trim().isEmpty()){
            errorDialog("Employee appointed date can not be empty");
            return;
        }
        
        if(epfDate.getValue().toString() == null || epfDate.getValue().toString().trim().isEmpty()){
            errorDialog("Employee EPF/ETF start date can not be empty");
            return;
        }
        
        String query = "UPDATE employee SET NIC = ?, Name = ?, Designation = ?, Address = ?, Gender = ?, Birthdate = ?, Phone = ?, DateAppointed = ?, DateResigned = ?, EPFETFDate = ? where EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, eNIC);
            pst.setString(2, eName);
            pst.setString(3, designation);
            pst.setString(4, eAddr);
            pst.setString(5, gender);
            pst.setDate(6, java.sql.Date.valueOf(bDate));
            pst.setString(7, phone);
            pst.setDate(8, java.sql.Date.valueOf(appointedDate));
            pst.setDate(9, resDate);
            System.out.println("upcheck");
            pst.setDate(10, java.sql.Date.valueOf(etfDate));
            pst.setString(11, eid);
            
            int x = pst.executeUpdate();
            
            if(x==1){
                System.out.println("Data insertion successful");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewSelectedEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            errorDialog("Error occrued during data insertion");
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Employee details updated successfully");
        alert.showAndWait();
    }

    private void updateNominee() {
        String nname = nNameText.getText();
        int nage = 0;
        if(!nAgeText.getText().isEmpty()){
            nage = Integer.parseInt(nAgeText.getText());
        }
        String nrelationship = nRelationshipText.getText();
        Double nshareAmount = Double.parseDouble(shareAmountText.getText());
        String naddress = nAddrText.getText();
        
        if(nname == null || nname.trim().isEmpty() || !validateName(nname)){
            errorDialog("Employee nominated person can not be empty");
            return;
        }
        
        if(nAgeText.getText() == null || nAgeText.getText().trim().isEmpty() || !validateNumbersOnly(nAgeText.getText())){
            errorDialog("Employee nominated person's age can not be empty");
            return;
        }
        
        if(nrelationship == null || nrelationship.trim().isEmpty()){
            errorDialog("Employee and nominated person's relationship can not be empty");
            return;
        }
        
        if(shareAmountText.getText() == null || shareAmountText.getText().trim().isEmpty() || !validateCurrency(shareAmountText.getText())){
            errorDialog("Employee nominated person's share amount can not be empty");
            return;
        }
        
        if(naddress == null || naddress.trim().isEmpty()){
            errorDialog("Employee nominated person's address can not be empty");
            return;
        }
        
        //if(nname != null && age != null && )
        
        String query = "UPDATE nominee SET Name = ?, Age = ?, ShareAmount = ?, Relationship = ?, Address = ? WHERE EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, nname);
            pst.setInt(2, nage);
            pst.setDouble(3, nshareAmount);
            pst.setString(4, nrelationship);
            pst.setString(5, naddress);
            pst.setString(6, eid);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("nomdata insert success");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(ViewSelectedEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            errorDialog("Error occrued during data insertion");
        }
        
    }
    
    private void checkTFValueChanges(JFXTextField textInput){
        textInput.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {
                if(!oldValue.equals(newValue) && newValue != null){
                    updateButton.setDisable(false);
                }
            }
        });
    }
    
    private void checkTAValueChanges(JFXTextArea textInput){
        textInput.textProperty().addListener(new ChangeListener<String>() {
        @Override
        public void changed(ObservableValue<? extends String> observable,
            String oldValue, String newValue) {
                if(!oldValue.equals(newValue) && newValue != null){
                    updateButton.setDisable(false);
                }
            }
        });
    }
    
    private void checkCalValueChanges(DatePicker dateInput){
        dateInput.valueProperty().addListener(new ChangeListener<LocalDate>(){
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if(!oldValue.equals(newValue) && newValue != null){
                    updateButton.setDisable(false);
                }
            }
            
        });
    }
    
    private void checkCalValueAdd(DatePicker dateInput){
        dateInput.valueProperty().addListener(new ChangeListener<LocalDate>(){
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if(newValue != null){
                    updateButton.setDisable(false);
                }
            }
            
        });
    }
    
    private void checkComboValueChanges(JFXComboBox comboSelected){
        comboSelected.getEditor().textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!oldValue.equals(newValue) && newValue != null){
                    updateButton.setDisable(false);
                }
            }
            
        }); 
    }
    
    private void checkRadioValueChanges(JFXRadioButton radioButton){
        radioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected == true && wasPreviouslySelected == false) { 
                    updateButton.setDisable(false);
                } 
            }
        });
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
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
        
    }
    
}

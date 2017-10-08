/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.newthisara;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.Supplier.dialog.AlertDialog;

/**
 *
 * @author user pc
 */
public class FXMLDocumentController implements Initializable {
    //Create connection class object.
    Connection conn = null;
    //Create preparedStatement class object.
    PreparedStatement pst = null;
    //Create ResultSet class object.
    ResultSet rs = null;
    private ObservableList<Suplist>data;
    
    private Label label;
    @FXML
    private JFXTextField suppid;
    @FXML
    private JFXTextField supname;
    @FXML
    private JFXTextField supnic;
    @FXML
    private JFXTextArea supadd;
    @FXML
    private JFXTextField supphone;
    @FXML
    private JFXTextField supgnd;
    @FXML
    private JFXTextField suplname;
    @FXML
    private JFXTextField suplarea;
    @FXML
    private TableView<Suplist> TableSupplier;
    @FXML
    private TableColumn<?, ?> SupplierIDcol;
    @FXML
    private TableColumn<?, ?> Namecol;
    @FXML
    private TableColumn<?, ?> Addresscol;
    @FXML
    private TableColumn<?, ?> LandNamecol;
    @FXML
    private TableColumn<?, ?> GramaNiladhariDivisioncol;
    @FXML
    private TableColumn<?, ?> LandAreacol;
    @FXML
    private TableColumn<?, ?> NICcol;
    @FXML
    private TableColumn<?, ?> TelephoneNocol;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton update;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton clear;
    @FXML
    private JFXButton snew;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        
        //emptyValidation method calling.
        emptyValidation( supname,"No Input Given");
        emptyValidation( supnic,"No Input Given insert like XXXXXXXXXv");
        emptyValidation( supphone,"No Input Given");
        emptyValidation( supgnd,"No Input Given");
        emptyValidation( suplarea,"No Input Given");
        emptyValidation( suplname,"No Input Given");
        emptyValidation( supadd,"No Input Given");
        
        //numberValidation method calling.
        numberValidation(supphone, "Phone number must be filled with 10 digits");
        //doubleValidation method calling.
        doubleValidation(suplarea, "Not a Area");
        
        //Setcelltable method calling.
       setcelltable();
       //loadDataFromDatabase method calling.
       loadDataFromDatabase();
       //SetCellValueFromTableToTextField method calling.
        SetCellValueFromTableToTextField();
        
        
        

    }    
//method implimentation for numberValidation(for JFXTextField ).
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

//method implimentation for emptyValidation (for JFXTextField ).    
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
        //method implimentation for numberValidation(for JFXTextArea ).
        private void emptyValidation(JFXTextArea input, String warning){
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
        
         //method implimentation for doubleValidation(for JFXTextArea ).
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

    @FXML
    private void AddRecords(ActionEvent event) {
        
       
     //insert query for add Supplier data.   
        String q = "INSERT INTO supplier(SupplierID,Name,Address,LandName,GramaNiladhariDivision,LandArea,NIC,TelephoneNo)"
                    + "VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
        
        
        System.out.println("ADD");
      
        try{
        
            pst=conn.prepareStatement(q);
          
            pst.setString(1, supname.getText());
            pst.setString(2, supadd.getText());
            pst.setString(3, suplname.getText()); 
            pst.setString(4, supgnd.getText());
            pst.setDouble(5,Double.parseDouble(suplarea.getText()));   
            pst.setString(6, supnic.getText());            
            pst.setString(7, supphone.getText());
            
                       
            
            
            
            
           
         
            int x= pst.executeUpdate();
            
            
            if(x==1)
            {
                
                AlertDialog.didplay("Info", "Data Insert Successfully");
              setcelltable();
              loadDataFromDatabase();
              cleartextfield();
             
            }
            
        }catch (RuntimeException e) {
            AlertDialog.didplay("Info", "Please Enter Value");
            System.out.println(e);
            System.out.println("faile!!!!!");
        }
        catch (SQLException ex) {
            AlertDialog.didplay("Info", "Please Enter Valid Value");
           
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
     
    }

    @FXML
    private void UpdateRecords(ActionEvent event) throws SQLException {
        
        //Update query for update value
         String sql = "UPDATE supplier SET Name=?,Address=?,LandName=?,GramaNiladhariDivision=?,LandArea=?,NIC=?,TelephoneNo=?"+" WHERE SupplierID = ?";
        System.out.println("update");
        try{
         pst=conn.prepareStatement(sql);
           
            pst.setString(1,supname.getText());
            pst.setString(2,supadd.getText());
            pst.setString(3,suplname.getText()); 
            pst.setString(4,supgnd.getText());             
            pst.setDouble(5,Double.parseDouble(suplarea.getText()));            
            pst.setString(6,supnic.getText());            
            pst.setString(7,supphone.getText());
            
            pst.setInt(8, Integer.valueOf(suppid.getText()));
           
            int x= pst.executeUpdate();
            
            if(x==1)
            
                
                 AlertDialog.didplay("Info", "Data Update Successfully");
              setcelltable();
              loadDataFromDatabase();
              cleartextfield();
        }catch (RuntimeException ex) {
           AlertDialog.didplay("Info", "Please enter values");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //method implimentation for clear textfield. 
       private void cleartextfield(){
            
        suppid.clear();
        supname.clear();
        supnic.clear();
        supadd.clear();
        supphone.clear();
        supgnd.clear();
        suplname.clear();
        suplarea.clear();
    }
    @FXML
    private void DeleteRecords(ActionEvent event) {
      //Delete query.  
         String sql ="delete from supplier where SupplierID = ?";
        
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(suppid.getText()));
            
            int x= pst.executeUpdate();
            
            if(x==1)
            
               
                             AlertDialog.didplay("Info", "Data Delete Successfully");

               
               loadDataFromDatabase();
               cleartextfield();
               
        } catch (SQLException ex) {
            AlertDialog.didplay("Info", "You can delete only suppliers who did not supply tea leaves");
        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
    //method for set cell values in table.
     private void setcelltable(){

        SupplierIDcol.setCellValueFactory(new PropertyValueFactory<>("supid"));
        Namecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        NICcol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        Addresscol.setCellValueFactory(new PropertyValueFactory<>("add"));
        TelephoneNocol.setCellValueFactory(new PropertyValueFactory<>("conno"));
        GramaNiladhariDivisioncol.setCellValueFactory(new PropertyValueFactory<>("gnd"));
        LandNamecol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        LandAreacol.setCellValueFactory(new PropertyValueFactory<>("larea"));
        TableSupplier.setItems(data);
        
    }
     
     //method implimentation for load data from database.
     private void loadDataFromDatabase(){
        
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from supplier");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new Suplist(""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),""+rs.getDouble(6),rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableSupplier.setItems(data);
        
    
    }
     
     // method implimentation for set cell values from table to textfield.
       private void SetCellValueFromTableToTextField()
    {
        TableSupplier.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Suplist f1 = TableSupplier.getItems().get(TableSupplier.getSelectionModel().getSelectedIndex());
            
            suppid.setText(f1.getSupid());
            supname.setText(f1.getName());
            supnic.setText(f1.getNic());
            supadd.setText(f1.getAdd());
            supphone.setText(f1.getConno());
            supgnd.setText(f1.getGnd());
            suplname.setText(f1.getLname());
            suplarea.setText(f1.getLarea());
        });
    }

       
    @FXML
    private void NewRecord(ActionEvent event) throws SQLException {
      
        suppid.setText(String.valueOf(generateSupplierID()));
            supname.setText(null);
            supnic.setText(null);
            supadd.setText(null);
            supphone.setText(null);
            supgnd.setText(null);
            suplname.setText(null);
            suplarea.setText(null);
        
    }
    //method implimentation for generate new Supplier ID
    private Integer generateSupplierID() throws SQLException {
        int SupplierID =1;
        String query = "SELECT max(SupplierID) FROM supplier";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()){
            SupplierID = rs.getInt("max(SupplierID)") + 1;
        }
        return SupplierID;
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/suplier.jrxml").getAbsolutePath();
        try{
            String is = path;
            JasperReport jr =JasperCompileManager.compileReport(is);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,conn);
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Grocery;

import thisarateafactory.ui.Production.AlertBox.AlertDialog;
import thisarateafactory.ui.Production.Packing.PackingProductController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.Production.thisara.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author Nipuni
 */
public class GroceryController implements Initializable {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private ObservableList<GroceryList> data;
    
    Date currentDate = new Date();
    
     ObservableList<String> list4 = FXCollections.observableArrayList("Thisara","Thisara A");//set items to combo box
     ObservableList<String> list5 = FXCollections.observableArrayList("Employee","Supplier");//set items to combo box
   
    @FXML
    private JFXComboBox<String> empIdtxt;
    @FXML
    private JFXComboBox<String> supIdtxt;
    @FXML
    private JFXComboBox<String> qualityCmb;
    @FXML
    private JFXTextField qua;
    @FXML
    private JFXTextField uPricetxt;
    @FXML
    private JFXButton totbtn;
    @FXML
    private Label TotAmt;
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXButton upbtn;
    @FXML
    private JFXButton delbtn;
    @FXML
    private TableColumn<?, ?> colDate;
    @FXML
    private TableColumn<?, ?> colEmpId;
    @FXML
    private TableColumn<?, ?> colSupId;
    @FXML
    private TableColumn<?, ?> colBType;
    @FXML
    private TableColumn<?, ?> colUPrice;
    @FXML
    private TableColumn<?, ?> colQuantity;
    @FXML
    private TableView<GroceryList> TableGrocery;
    @FXML
    private TableColumn<?, ?> colGroceryId;
    @FXML
    private TableColumn<?, ?> colQual;
    @FXML
    private TableColumn<?, ?> colTotPr;
    @FXML
    private JFXTextField GrsIDtxt;
    @FXML
    private JFXTextField SoldDTxt1;
    @FXML
    private JFXButton btnGID;
    @FXML
    private JFXComboBox<String> BTypeCmb;
    
    final ObservableList list1 = FXCollections.observableArrayList();
    final ComboBox comboBoxEmp = new ComboBox(list1);
   
    final ObservableList list2 = FXCollections.observableArrayList();
    final ComboBox comboBoxSup = new ComboBox(list2);
    @FXML
    private JFXButton cancelbtn;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set items to the combo box Grade
        qualityCmb.setItems(list4);
        
        //Set items to the combo box BTypeCmb
        BTypeCmb.setItems(list5);
        
        //Set items to the combo box empIdtxt
        empIdtxt.setItems(list1);
        
        //Set items to the combo box supIdtxt
        supIdtxt.setItems(list2);
        
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        
        //setCellTable method calling.
        setCellTable();
        
       //loadDataFromDatabase method calling.
        loadDataFromDatabase();
        
        //setCellValueFromTableToTextField method calling.
        setCellValueFromTableToTextField();
        
        //fillComboBoxSupplier method calling.
        fillComboBoxSupplier();
        
        //fillComboBoxEmployee method calling.
        fillComboBoxEmployee();
        
        //emptyValidation method calling.
        emptyValidation(qua,"No Input Given..!");
        emptyValidation(uPricetxt,"No Input Given..!");
        
       //doubleValidation method calling.
        doubleValidation(qua,"Please enter Quantity..!");
        doubleValidation(uPricetxt,"Please enter Unit Price..!");
        
        //Method Implementation for setVisisbility to BTypeCmb combo box 
     BTypeCmb.focusedProperty().addListener(new ChangeListener<Boolean>(){

        @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        
        String tx1 = BTypeCmb.getValue();
            if(tx1.equals("Employee"))
            {
               supIdtxt.setDisable(true);
               empIdtxt.setDisable(false);
            }
            else if(tx1.toString().equals("Supplier"))
            {
                    empIdtxt.setDisable(true);
                    supIdtxt.setDisable(false);
            }
            }
        
    });
        
    } 
    //Method Implementation for emptyValidation 
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
     
 //Method Implementation for numberValidation 
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

 //Method Implementation for doubleValidation 
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

  //Method Implementation for CalculateTotal  
  @FXML
    private void CalculateTotal(ActionEvent event) {
        
        double quantity = Double.parseDouble(qua.getText());
        double unitPrice = Double.parseDouble(uPricetxt.getText());
                
        double TotalAmount = quantity * unitPrice ;
        TotAmt.setText(String.valueOf(TotalAmount));
    }

    //Method Implementation for addGrocerydetails  
   @FXML
    private void addGrocerydetails(ActionEvent event) throws SQLException {
         try {
            String query = "";
        
            if ( BTypeCmb.getValue() == "Supplier" )
            {
                query = "INSERT INTO grocery (GroceryID, Date, SupplierID, BuyerType, UnitPrice, Quality, Qty, TotalPrice)" 
                + "VALUES (NULL, current_date, ? , ? , ?, ?, ?, ?)";
                
                 pst = conn.prepareStatement(query);
                 
                 pst.setString(1, supIdtxt.getValue());
                 pst.setString(2, BTypeCmb.getValue());
                 pst.setDouble(3,Double.parseDouble(uPricetxt.getText())); 
                 pst.setString(4, qualityCmb.getValue());
                 pst.setDouble(5,Double.parseDouble(qua.getText())); 
                
                 pst.setDouble(6,Double.parseDouble(TotAmt.getText()));
         
            }
            else if ( BTypeCmb.getValue() == "Employee" )
            {
                query = "INSERT INTO grocery (GroceryID, Date, EmployeeID, BuyerType, UnitPrice, Quality, Qty, TotalPrice)" 
                + "VALUES (NULL, current_date, ? , ? , ?, ?, ?, ?)";
                
                 pst = conn.prepareStatement(query);
               
                 pst.setString(1,  empIdtxt.getValue());
                 pst.setString(2, BTypeCmb.getValue());
                  pst.setDouble(3,Double.parseDouble(uPricetxt.getText())); 
                 pst.setString(4, qualityCmb.getValue());
                 pst.setDouble(5,Double.parseDouble(qua.getText())); 
                
                 pst.setDouble(6,Double.parseDouble(TotAmt.getText()));
                 
                 
         
               
         
            }
        
       
            
            int x = pst.executeUpdate();
                       
            if(x==1)
            {  
                clearTextField();
                AlertDialog.display("info", "Data Insert Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            
            }   
        } catch (SQLException ex) {
            AlertDialog.display("info", "Please Fill All Fields..!");
            
           // Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (RuntimeException ex1) {
            AlertDialog.display("info", "Please Fill All Fields..!");
            
            
          //  Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex1);
        }
       
    }
    
  //Method Implementation for UpdategroceryRecords
   @FXML
    private void UpdategroceryRecords(ActionEvent event) {
        
        String query ="UPDATE grocery SET UnitPrice =?, Qty =?, TotalPrice = ? WHERE GroceryID = ?     "; 
        
         try {
            
            pst = conn.prepareStatement(query);
            
                 pst.setDouble(1,Double.parseDouble(uPricetxt.getText())); 
               
                 pst.setDouble(2,Double.parseDouble(qua.getText())); 
                
                 pst.setDouble(3,Double.parseDouble(TotAmt.getText()));
               
                pst.setInt(4, Integer.valueOf(GrsIDtxt.getText()));
            
            int x = pst.executeUpdate();
              
            if(x==1)
            {   
                clearTextField();
                AlertDialog.display("info", "Data Updated Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            }   
            
        } catch (SQLException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (RuntimeException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            System.out.println(ex);
            System.out.println("faile!!!!!");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
 //Method Implemntation for deleteGroceyDetails
   @FXML
    private void deleteGroceyDetails(ActionEvent event) 
    {
       
        String sql = "DELETE from grocery where GroceryID = ?";
        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(GrsIDtxt.getText()));
            
            int x = pst.executeUpdate();
          
          if(x==1)
          
          loadDataFromDatabase();
          clearTextField();
          AlertDialog.display("info", "Data Deleted Successfully..!");
           

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     //Method Implemntation for setCellTable
     private void setCellTable()
    {
         colGroceryId.setCellValueFactory(new PropertyValueFactory<>("GroceryID"));
         colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
         colEmpId.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
         colSupId.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
         colBType.setCellValueFactory(new PropertyValueFactory<>("BuyerType"));
         colUPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
         colQual.setCellValueFactory(new PropertyValueFactory<>("Quality"));
         colQuantity.setCellValueFactory(new PropertyValueFactory<>("Qty"));
         colTotPr.setCellValueFactory(new PropertyValueFactory<>("TotalPrice"));
         
    }
    // method implementation for load data from database and set them into the interface table
   private void loadDataFromDatabase()
    {
        data.clear();
        try {
            
            pst = conn.prepareStatement("Select GroceryID,Date,EmployeeID,SupplierID,BuyerType,UnitPrice,Quality,Qty,TotalPrice from grocery");
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                data.add(new GroceryList(""+rs.getInt(1),rs.getString(2), ""+rs.getInt(3), ""+rs.getInt(4), rs.getString(5), ""+rs.getDouble(6), rs.getString(7), ""+rs.getDouble(8), ""+rs.getDouble(9)));
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableGrocery.setItems(data);
    }
    
   //method implementation for setCellValueFromTableToTextField 
   private void setCellValueFromTableToTextField()
    {
        
        TableGrocery.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        GroceryList d1 = TableGrocery.getItems().get(TableGrocery.getSelectionModel().getSelectedIndex());
        
        GrsIDtxt.setText(d1.getGroceryID());
        SoldDTxt1.setText(d1.getDate());
        String h = d1.getBuyerType();
        BTypeCmb.setValue(h);
        
        String tx1 = BTypeCmb.getValue();
          
        if ( tx1.equals("Employee") )
        {   
                supIdtxt.setDisable(true);
                empIdtxt.setDisable(false);
                
                String v = d1.getEmployeeID();
                empIdtxt.setValue(v);
                supIdtxt.setValue(d1.getSupplierID());
        }
        
        if ( tx1.equals("Supplier") )
        {   
                supIdtxt.setDisable(false);
                empIdtxt.setDisable(true);
                     
                String n = d1.getSupplierID();
                supIdtxt.setValue(n);
                empIdtxt.setValue(d1.getEmployeeID());
        } 
        
        String d = d1.getQuality();
        qualityCmb.setValue(d);
        
        qua.setText(d1.getQty());
        uPricetxt.setText(d1.getUnitPrice());
        TotAmt.setText(d1.getTotalPrice());
        
        });
        
    }

    
 //method implementation for AutoIncrementGID
   @FXML
    private void AutoIncrementGID(ActionEvent event) throws SQLException {
        
        GrsIDtxt.setText(String.valueOf(generateGroceryID()));
        SoldDTxt1.setText(new Date().toString());
        BTypeCmb.setValue("");
        supIdtxt.setValue("");
        empIdtxt.setValue("");
        qualityCmb.setValue("");
        qua.setText(null);  
        uPricetxt.setText(null);
        TotAmt.setText(null);        
         
    }
    
    //method implementation for generateGroceryID
   private Integer generateGroceryID() throws SQLException
    {
        int PID = 1;
        String query = "SELECT max(GroceryID) FROM grocery";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()){
            PID = rs.getInt(1)+1;
        }
        return PID;
    }
    
    //method implementation for fillComboBoxEmployee
  public void fillComboBoxEmployee()
    {
        list1.clear();
        try{
           
            String query = "Select EmployeeID from employee";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next())
            {
                list1.add(rs.getString("EmployeeID"));
            }
            
            pst.close();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
     
     //method implementation for fillComboBoxSupplier
    public void fillComboBoxSupplier()
    {
        list2.clear();
        try
        {
            String query = "Select SupplierID from supplier";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next())
            {
                list2.add(rs.getString("SupplierID"));
            }
            
            pst.close();
            rs.close();
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
  //method implementation for clearTextField
   private void clearTextField()
    {
      
        GrsIDtxt.clear();
        SoldDTxt1.clear();
        BTypeCmb.setValue("");
        supIdtxt.setValue("");
        empIdtxt.setValue("");
        qualityCmb.setValue("");
        qua.setText(null);
        uPricetxt.setText(null);
        TotAmt.setText(null);
      
    }
//method implementation for CancelGroceryDetails
      @FXML
    private void CancelGroceryDetails(ActionEvent event)
    {  
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
     
    }
    
}

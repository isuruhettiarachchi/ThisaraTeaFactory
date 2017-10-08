/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Quality;

import thisarateafactory.ui.Production.AlertBox.AlertDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import thisarateafactory.ui.Production.thisara.FXMLDocumentController;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Nipuni
 */
public class QualityProductController implements Initializable {
    
    //create connection class object.
    Connection conn = null;
    
    //create preparedStatement class object.
    PreparedStatement pst = null;
    
    //create resultSet class object.
    ResultSet rs = null;
    
    private ObservableList<QualityList> data;
    
    ObservableList<String> list = FXCollections.observableArrayList("Thisara A","Thisara");//set items to combo box
    
    private Label indexNo;
    
    private DatePicker sepDate;
    
    @FXML
    private JFXButton btnGross;
    @FXML
    private Label Gweight;
    @FXML
    private JFXTextField sampleSize;
    @FXML
    private JFXButton btnNet;
    @FXML
    private Label weight;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUp;
    @FXML
    private JFXButton btnDel;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private TableView<QualityList> TableQuality;
    @FXML
    private TableColumn<?, ?> colIndex;
    @FXML
    private TableColumn<?, ?> colPId;
    @FXML
    private TableColumn<?, ?> colQua;
    @FXML
    private TableColumn<?, ?> colGweight;
    @FXML
    private TableColumn<?, ?> colSampleSize;
    @FXML
    private TableColumn<?, ?> colNetWeight;
    @FXML
    private JFXComboBox<String> Qua;
    @FXML
    private JFXComboBox<String> cmbPid;
    
    final ObservableList list1 = FXCollections.observableArrayList();
    final ComboBox comboBox = new ComboBox(list1);
    
    @FXML
    private JFXTextField txtIndexNo;
    @FXML
    private JFXButton btnIndex;
    @FXML
    private JFXTextField weightforqua;
    @FXML
    private TableColumn<?, ?> colweightqua;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        
        //Set items to the combo box quality
        Qua.setItems(list);
        
        //Set items to the combo box productID
        cmbPid.setItems(list1);
        
        //setCellTable method calling.
        setCellTable();
        
        //loadDataFromDatabase method calling.
        loadDataFromDatabase();
        
        //setCellValueFromTableToTextField method calling.
        setCellValueFromTableToTextField();
        
        //fillComboBox method calling. 
        fillComboBox();
        
        //emptyValidation method calling.
        emptyValidation(sampleSize,"No Input Given..!");
        
        //doubleValidation method calling.
         doubleValidation(sampleSize,"Please enter Sample Size..!");
       
         //emptyValidation method calling.
        emptyValidation(weightforqua,"No Input Given..!");
        
        //doubleValidation method calling.
         doubleValidation(weightforqua,"Please enter valid weight ..!");
       
        
        // TODO
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


    //Method Implementation for CalculateGrossWeight 
    @FXML
    private void CalculateGrossWeight(ActionEvent event) {
        
        String query = "select MadeTeaWeight from dailyproduct where ProductID = ?";
        
        try{
           pst = conn.prepareStatement(query);
           pst.setString(1,cmbPid.getValue());
           
           rs = pst.executeQuery();
           rs.last();
           int Gnet = rs.getInt(1);
           String netTot = new Integer(Gnet).toString();
           Gweight.setText(netTot);
       
       } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        
    }

    //Method Implementation for doubleValidation 
    @FXML
    private void CalculateNetWeight(ActionEvent event) {
        
        double GrossAmt = Double.parseDouble(weightforqua.getText());
        
        double spSize = Double.parseDouble(sampleSize.getText());
                
        double TotalAmount = GrossAmt - spSize ;
        
        if( TotalAmount >= 0){
               weight.setText(String.valueOf(TotalAmount));
            }
            else{
                AlertDialog.display("Info", "Invalied Entry");
                clearTextField();
        }
                
    }

     //Method Implementation for AddRecord 
    @FXML
    private void AddRecord(ActionEvent event) throws SQLException {
        
        
        if(CountProduct() < 2 && CountQuality()<1){
        
         String query = "INSERT INTO quality (IndexNo,ProductID,Quality,GrossWeight,SampleSize, NetWeight, QualityWeight)"
                + "VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        
         try {
              
            pst = conn.prepareStatement(query);

            pst.setString(1, cmbPid.getValue());
            pst.setString(2, Qua.getValue());
         
             pst.setDouble(3, Double.parseDouble(Gweight.getText()));
             pst.setDouble(4, Double.parseDouble(sampleSize.getText()));
            
            pst.setDouble(5,Double.parseDouble(weight.getText()));
            
            pst.setDouble(6,Double.parseDouble(weightforqua.getText()));
           
            
            
            
             
            
                         
            int x = pst.executeUpdate();
            
            if(x==1){
                
            clearTextField();
            AlertDialog.display("info", "Data Insert Successfully..!");
            setCellTable();
            loadDataFromDatabase();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QualityProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (RuntimeException ex1) {
            AlertDialog.display("info", "Please Fill All Fields..!");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
        }
        else 
        {
            clearTextField();
            AlertDialog.display("info", "You can not add Qualities again..!");
             
        }
        
        
       
    }

     //Method Implementation for updateDalyProduct
    @FXML
    private void UpdateRecord(ActionEvent event) throws SQLException {
        
                     
     String sql = "UPDATE quality SET SampleSize = ?,NetWeight = ?, QualityWeight=? where IndexNo = ? AND Quality = ?";
    
     try {
            pst = conn.prepareStatement(sql);
            
           pst.setDouble(1, Double.parseDouble(sampleSize.getText()));
            pst.setDouble(2,Double.parseDouble(weight.getText()));
            pst.setDouble(3,Double.parseDouble(weightforqua.getText()));
             pst.setInt(4,Integer.parseInt(txtIndexNo.getText()));
            pst.setString(5,Qua.getValue());
            
             int x = pst.executeUpdate();
            
            if(x==1)
            {
                clearTextField();
                AlertDialog.display("info", "Data Updated Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QualityProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
     catch (RuntimeException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            System.out.println(ex);
            System.out.println("faile!!!!!");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
   
//Method Implementation for DeleteRecord
    @FXML
    private void DeleteRecord(ActionEvent event) {
        
        String sql = "DELETE from quality where IndexNo = ? AND Quality = ?";
        
        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(txtIndexNo.getText()));
            pst.setString(2,Qua.getValue());
            
            
            int x = pst.executeUpdate();
          
          if(x==1)
                
               loadDataFromDatabase();
               clearTextField();
               AlertDialog.display("info", "Data Deleted Successfully..!");
               
               
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     //method implementation for CancelRecord
    @FXML
    private void CancelRecord(ActionEvent event)throws IOException {
        
       ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
       
    }
    
     //method implementation for setCellTable
    private void setCellTable()
    {
        
         colIndex.setCellValueFactory(new PropertyValueFactory<>("indNo"));
         colPId.setCellValueFactory(new PropertyValueFactory<>("Pid"));
         colQua.setCellValueFactory(new PropertyValueFactory<>("Qlty"));
         colGweight.setCellValueFactory(new PropertyValueFactory<>("Gweight"));
         colSampleSize.setCellValueFactory(new PropertyValueFactory<>("SSize"));
         colNetWeight.setCellValueFactory(new PropertyValueFactory<>("NetW"));
         //colweightqua.setCellValueFactory(new PropertyValueFactory<>("QualityWeight"));
         colweightqua.setCellValueFactory(new PropertyValueFactory<>("qweight"));
         
    }
    
    // method implementation for load data from database and set them into the interface table
    private void loadDataFromDatabase()
    {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from quality");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new QualityList(""+rs.getInt(1), ""+rs.getInt(2), rs.getString(3), ""+rs.getDouble(4), ""+rs.getDouble(5), ""+rs.getDouble(6), ""+rs.getDouble(7)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableQuality.setItems(data);
    }
    
      //method implementation for setCellValueFromTableToTextField 
     private void setCellValueFromTableToTextField()
     {
         TableQuality.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        QualityList d1 = TableQuality.getItems().get(TableQuality.getSelectionModel().getSelectedIndex());
                
        txtIndexNo.setText(d1.getIndNo());
        
        String h = d1.getQlty();
        Qua.setValue(h);
        
        String v = d1.getPid();
        cmbPid.setValue(v);
        Gweight.setText(d1.getGweight());
        sampleSize.setText(d1.getSSize());
        weight.setText(d1.getNetW());
        weightforqua.setText(d1.getQweight());
        
        });
     }
     
  //method implementation for AutoIncrementIndex 
    @FXML
    private void AutoIncrementIndex(ActionEvent event) throws SQLException 
    {
        
        txtIndexNo.setText(String.valueOf(generateIndexNO()));
        cmbPid.setValue("");
        Qua.setValue("");
        sampleSize.setText(null);
        weight.setText(null);
        weightforqua.setText(null);
        
        
    }
    
    //method implementation for generateIndexNO 
     private Integer generateIndexNO() throws SQLException
    {
        int INO = 1;
        String query = "SELECT max(IndexNo) FROM quality";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()) {
            INO = rs.getInt(1) + 1;
        }
        return INO;
    }
    
   // method implementation for fillComboBox 
     public void fillComboBox()
    {
        list1.clear();
        try{
            String query = "Select ProductID from dailyproduct";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next())
            {
                list1.add(rs.getString("ProductID"));
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
       txtIndexNo.clear();
        cmbPid.setValue("");
        Qua.setValue("");
        sampleSize.clear();
        Gweight.setText(null);
        weight.setText(null);
        weightforqua.clear();
     
    }
     
    //method implementation for CountProduct
     public Integer CountProduct() throws SQLException
    {
        int PNO = 0 ;
        pst = conn.prepareStatement("SELECT count(`ProductID`) from quality where productId = ?");
        pst.setString(1, cmbPid.getValue());
            
        rs = pst.executeQuery();
        rs.last();
        PNO = rs.getInt(1);
        
        return PNO;
         
        
    }
    public Integer CountQuality() throws SQLException
    {
        int PNO1 = 0 ;
        pst = conn.prepareStatement("SELECT count(`Quality`) from quality where Quality = ? and ProductID=?");
        
        pst.setString(1, Qua.getValue());
        pst.setString(2, cmbPid.getValue()) ; 
        rs = pst.executeQuery();
        rs.last();
        PNO1 = rs.getInt(1);
        
        return PNO1;
         
        
    }
    
    
    
}

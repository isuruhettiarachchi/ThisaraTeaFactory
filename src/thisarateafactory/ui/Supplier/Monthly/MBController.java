/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.Monthly;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import thisarateafactory.ui.Supplier.dialog.AlertDialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.Supplier.newthisara.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author user pc
 */
public class MBController implements Initializable {
    
    //Create connection class object.
    Connection conn = null;
    //Create preparedStatement class object.
    PreparedStatement pst = null;
    //Create ResultSet class object.
    ResultSet rs = null;
    private ObservableList<MBlist>data;
    @FXML
    private JFXTextField mbbulk;
    @FXML
    private JFXComboBox<String> mbsid;
    final  ObservableList list1 = FXCollections.observableArrayList();
     final ComboBox comboBox = new ComboBox(list1);
    @FXML
    private JFXButton mbtranusebut;
    @FXML
    private JFXButton mbnotrabut;
    @FXML
    private JFXTextField mbusintlable;
    @FXML
    private JFXTextField mbnotranl;
    @FXML
    private JFXButton mbadd;
    @FXML
    private JFXButton mbdel;
    @FXML
    private JFXButton mbcan;
    @FXML
    private TableView<MBlist> MBTable;
    @FXML
    private TableColumn<?, ?> supidcol;
    @FXML
    private TableColumn<?, ?> nodaycol;
    @FXML
    private TableColumn<?, ?> transusecol;
    @FXML
    private TableColumn<?, ?> nottranscol;
    @FXML
    private JFXButton mbnew;
    @FXML
    private TableColumn<?, ?> bnocol;
    @FXML
    private JFXTextField mbdays;
    @FXML
    private JFXButton mhtotbut;
    @FXML
    private JFXTextField mbtotlab;
    @FXML
    private TableColumn<?, ?> totbulkcol;

    LocalDate l1 = LocalDate.now();
    @FXML
    private JFXButton totdbut;
    @FXML
    private JFXTextField mbmonth;
     //create a object from util data class.
    java.util.Date currentDate = new java.util.Date();
    @FXML
    private TableColumn<?, ?> monthcol;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        mbsid.setItems(list1);
         //Setcelltable method calling.
       setcelltable();
       //loadDataFromDatabase method calling.
       loadDataFromDatabase();
       //SetCellValueFromTableToTextField method calling.
        SetCellValueFromTableToTextField();
        //fillCombobox method calling
        fillCombobox();
       
    }    

    @FXML
    private void CalTransportUsed(ActionEvent event) {
     //get the monthly supplied net weight using factory transport.   
        double cs =0;
        String q1 = "SELECT SUM(NetWeight) FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ? and TransportUsed LIKE 'Used'";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,mbsid.getValue());
            pst.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, Date.valueOf(LocalDate.now()));
            
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("SUM(NetWeight)");
                String cs1 = new Double(cs).toString();
                mbusintlable.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void CalTransportNotUse(ActionEvent event) {
         //get the monthly supplied net weight not using factory transport.   
        double cs =0;
        String q1 = "SELECT SUM(NetWeight) FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ? and TransportUsed LIKE 'Not Used'";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,mbsid.getValue());
            pst.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, Date.valueOf(LocalDate.now()));
            
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("SUM(NetWeight)");
                String cs1 = new Double(cs).toString();
                mbnotranl.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void AddRecord(ActionEvent event) throws SQLException {
        //insert monthly bulk details.
        
        if(CountBulk()<1){
         String q = "INSERT INTO monthlybulk(Bulkno,SupplierID,NoOfDays,TransportUsed,TransportNotUsed,Totalbulk,Month)"
                    + "VALUES(null,?,?, ?, ?, ?,current_date)";
        
        
        System.out.println("ADD");
        
        try{
        
            pst=conn.prepareStatement(q);
          
            pst.setString(1, mbsid.getValue());
             pst.setInt(2,Integer.parseInt(mbdays.getText()));
             pst.setDouble(3,Double.parseDouble(mbusintlable.getText()));              
            pst.setDouble(4,Double.parseDouble(mbnotranl.getText()));            
            pst.setDouble(5,Double.parseDouble(mbtotlab.getText()));   
            
           
         
            int x= pst.executeUpdate();
            
            
            if(x==1)
            {
                
                AlertDialog.didplay("Info", "Data Insert Successfully");
              setcelltable();
              loadDataFromDatabase();
              cleartextfield();
            }
            
        }catch (RuntimeException e) {
            System.out.println(e);
            System.out.println("faile!!!!!");
            AlertDialog.didplay("Info", " Please Enter Values");
        }
     }
      else
        {
            AlertDialog.didplay("Info", " You have already  Insert the given bulk");
             cleartextfield();
        }
        
    }

    //method implimentation for clear textfield. 
 private void cleartextfield(){
    
        mbbulk.clear();
        mbsid.setValue("");
        mbdays.clear();
        mbusintlable.clear();
        mbnotranl.clear();
        mbtotlab.clear();
         mbmonth.clear();
    }
    @FXML
    private void DeleteRecord(ActionEvent event) {
   //delete monthly bulk details.     
         String sql ="delete from monthlybulk where Bulkno = ?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(mbbulk.getText()));
            
            int x= pst.executeUpdate();
            
            if(x==1)
            
               
                             AlertDialog.didplay("Info", "Data Delete Successfully");

               
               loadDataFromDatabase();
               cleartextfield();
               
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void NewRecord(ActionEvent event) throws SQLException {
    
        //get a new bulk number and date.
        mbbulk.setText(String.valueOf(generateSupplierID()));
        
            mbsid.setValue("");
            mbdays.setText(null);
            mbusintlable.setText(null);
            mbnotranl.setText(null);
            mbtotlab.setText(null);
          
            mbmonth.setText(new java.util.Date().toString());
    }
    
    //method implimentation for generate new Bulk number
    private Integer generateSupplierID() throws SQLException {
       
        int Bulkno = 1;
        
        String query = "SELECT max(Bulkno) FROM monthlybulk";
        rs = conn.createStatement().executeQuery(query);
       while(rs.next()){
           Bulkno = rs.getInt("max(Bulkno)") + 1;
       }
        
        return Bulkno;
    }
   
      //method for set cell values in table.
     private void setcelltable(){

        bnocol.setCellValueFactory(new PropertyValueFactory<>("bulkno"));
        supidcol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        nodaycol.setCellValueFactory(new PropertyValueFactory<>("noday"));
        transusecol.setCellValueFactory(new PropertyValueFactory<>("tuse"));
        nottranscol.setCellValueFactory(new PropertyValueFactory<>("tnouse"));
        totbulkcol.setCellValueFactory(new PropertyValueFactory<>("totbulk"));
        monthcol.setCellValueFactory(new PropertyValueFactory<>("Month"));
        MBTable.setItems(data);
        
    }
     //method implimentation for load data from database.
    private void loadDataFromDatabase(){
        
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from monthlybulk");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new MBlist(""+rs.getInt(1),""+rs.getInt(2),""+rs.getInt(3),""+rs.getDouble(4),""+rs.getDouble(5),""+rs.getDouble(6),rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        MBTable.setItems(data);
        
    
    }
    
     // method implimentation for set cell values from table to textfield.
       private void SetCellValueFromTableToTextField()
    {
        MBTable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            MBlist f1 = MBTable.getItems().get(MBTable.getSelectionModel().getSelectedIndex());
            
            mbbulk.setText(f1.getBulkno());
          
            String r = f1.getSid();
            mbsid.setValue(r);
            mbdays.setText(f1.getNoday());
            mbusintlable.setText(f1.getTuse());
            mbnotranl.setText(f1.getTnouse());
            mbtotlab.setText(f1.getTotbulk());
            mbmonth.setText(f1.getMonth());
        });
    }
       
       //Method implimentation for fill Combobox with Supplier ID 
       public void fillCombobox()  {
        
            list1.clear();
            try{
            String query ="select SupplierID from supplier";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()){
             list1.add(rs.getString("SupplierID"));
            
            }
             pst.close();
             rs.close();
            }catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    @FXML
    private void GetTotDays(ActionEvent event)  {
   //Calculate total supplied days.    
        int cs =0;
        String q1 = "SELECT COUNT(Date)  FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ?";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,mbsid.getValue());
            pst.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, Date.valueOf(LocalDate.now()));
             
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("COUNT(Date)");
                String cs1 = new Integer(cs).toString();
                mbdays.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void CalculateTotalBulk(ActionEvent event) {
      //Calculate the monthly total bulk.  
        double cs =0;
        String q1 = "SELECT SUM(NetWeight)  FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ?";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,mbsid.getValue());
            pst.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, Date.valueOf(LocalDate.now()));
             
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("SUM(NetWeight)");
                String cs1 = new Double(cs).toString();
                mbtotlab.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public Integer CountBulk() throws SQLException{
    //Count the bulk in perticular period.
        int sid = 0;
        
       
        pst =conn.prepareStatement("SELECT count( SupplierID ) FROM monthlybulk WHERE SupplierID = ? and Month BETWEEN ? AND ?");
        
        pst.setString(1,mbsid.getValue());
         pst.setDate(2, Date.valueOf(LocalDate.now().withDayOfMonth(1)));
        pst.setDate(3, Date.valueOf(LocalDate.now()));
        
        rs =pst.executeQuery();
        
         while(rs.next())
            {
                sid = rs.getInt("count( SupplierID )");
                
           }
        rs.close();
       
        return sid;
    }
}

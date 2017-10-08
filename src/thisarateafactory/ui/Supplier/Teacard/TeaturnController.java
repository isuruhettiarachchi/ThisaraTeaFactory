/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.Teacard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import thisarateafactory.ui.Supplier.dialog.AlertDialog;
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
import thisarateafactory.ui.Supplier.newthisara.FXMLDocumentController;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author user pc
 */
public class TeaturnController implements Initializable {
    
    //Create connection class object.
    Connection conn = null;
    //Create preparedStatement class object.
    PreparedStatement pst = null;
    //Create ResultSet class object.
    ResultSet rs = null;
    private ObservableList<Tealist>data;
    
    @FXML
    private JFXTextField turnno;
    @FXML
    private JFXTextField tdate;
    @FXML
    private JFXComboBox<String> tsupid;
    final  ObservableList list2 = FXCollections.observableArrayList();
     final ComboBox comboBox1 = new ComboBox(list2);
    @FXML
    private JFXComboBox<String> ttrans;
     ObservableList<String> list1 = FXCollections.observableArrayList("Used","Not Used");
     final ComboBox comboBox = new ComboBox(list1);
    @FXML
    private JFXTextField tnocon;
    @FXML
    private JFXTextField tgross;
    @FXML
    private JFXTextField tconwei;
    @FXML
    private JFXTextField twater;
    @FXML
    private JFXTextField tmatu;
    @FXML
    private JFXButton tnet;
    @FXML
    private Label tnetlb;
    @FXML
    private JFXButton tadd;
    @FXML
    private JFXButton tupd;
    @FXML
    private JFXButton tdele;
    @FXML
    private JFXButton tcan;
    @FXML
    private TableColumn<?, ?> TurnNocol;
    @FXML
    private TableColumn<?, ?> Datecol;
    @FXML
    private TableColumn<?, ?> SupplierIDcol;
    @FXML
    private TableColumn<?, ?> NoOfContainerscol;
    @FXML
    private TableColumn<?, ?> GrossWeightcol;
    @FXML
    private TableColumn<?, ?> WeightOfContainercol;
    @FXML
    private TableColumn<?, ?> WaterWeightcol;
    @FXML
    private TableColumn<?, ?> MatureLeavesWeightcol;
    @FXML
    private TableColumn<?, ?> NetWeightcol;
    @FXML
    private TableColumn<?, ?> TransportUsedcol;
    @FXML
    private JFXButton tnew;
    @FXML
    private TableView<Tealist> Teatable;
    
    //create a object from data class.
     Date currentDate = new Date();
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        ttrans.setItems(list1);
        tsupid.setItems(list2);
   
        //emptyValidation method calling.
        emptyValidation( tnocon,"No Input Given");
        emptyValidation( tgross,"No Input Given");
        emptyValidation( tconwei,"No Input Given");
        emptyValidation( twater,"No Input Given");
        emptyValidation( tmatu,"No Input Given");
        
      //numberValidation method calling.
        numberValidation( tnocon,"Not a number");
      
      //doubleValidation method calling.
        doubleValidation( tgross,"Not a weight");
        doubleValidation( tconwei,"Not a weight");
        doubleValidation( twater,"Not a weight");
        doubleValidation( tmatu,"Not a weight");
        
       //Setcelltable method calling.
       setcelltable();
       //loadDataFromDatabase method calling.
       loadDataFromDatabase();
       //SetCellValueFromTableToTextField method calling.
        SetCellValueFromTableToTextField();
       //fillCombobox method calling
       fillCombobox();
        
       
        
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
    private void CalculateNetWeight(ActionEvent event) {
     
        //calculate the Net Weight of supplied tea leaves.
         double gross = Double.parseDouble(tgross.getText());
        double conweight = Double.parseDouble(tconwei.getText());
        double water = Double.parseDouble(twater.getText());
        double mature = Double.parseDouble(tmatu.getText());
        
            double NetWeight = gross -( conweight + water + mature) ;
            if( NetWeight >= 0){
                tnetlb.setText(String.valueOf(NetWeight));
            }
            else{
                AlertDialog.didplay("Info", "Invalied Entry");
                cleartextfield();
        }
    }

    @FXML
    private void AddRecord(ActionEvent event) {
       
       
      //insert query for add data.  
        
        
         String q = "INSERT INTO collectionturn(TurnNo,Date,	SupplierID,NoOfContainers,GrossWeight,WeightOfContainer,WaterWeight,MatureLeavesWeight,NetWeight,TransportUsed)"
                    + "VALUES(null,current_date,?, ?, ?, ?, ?, ?,?,?)";
        
        
        System.out.println("ADD");
        
        try{
        
            pst=conn.prepareStatement(q);
       
            pst.setString(1, tsupid.getValue().toString());
            pst.setInt(2,Integer.parseInt(tnocon.getText()));
            pst.setDouble(3,Double.parseDouble(tgross.getText()));              
            pst.setDouble(4,Double.parseDouble(tconwei.getText()));            
            pst.setDouble(5,Double.parseDouble(twater.getText()));              
            pst.setDouble(6,Double.parseDouble(tmatu.getText()));  
            pst.setDouble(7,Double.parseDouble(tnetlb.getText()));
            pst.setString(8, ttrans.getValue().toString());
            
        
           
            int x= pst.executeUpdate();
            
            
            if(x==1)
            {
                
                AlertDialog.didplay("Info", "Data Insert Successfully");
              setcelltable();
              loadDataFromDatabase();
              cleartextfield();
            }
            
        }catch (SQLException e) {
            System.out.println(e);
            System.out.println("faile!!!!!");
        }
        
        catch (RuntimeException ex) {
            AlertDialog.didplay("Info", "Please Enter Values");
            System.out.println(ex);
            System.out.println("faile!!!!!");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void UdateRecord(ActionEvent event) {
        //update the teacollection details.
        
        
         String sql = "UPDATE collectionturn SET Date=?,SupplierID=?,NoOfContainers=?,GrossWeight=?,WeightOfContainer=?,WaterWeight=?,MatureLeavesWeight=?,NetWeight=?,TransportUsed=?"+" WHERE TurnNo = ?";
        System.out.println("update");
        try{
         pst=conn.prepareStatement(sql);
           
            pst.setString(1,tdate.getText());
            
             pst.setString(2, (String) tsupid.getValue());
            pst.setInt(3,Integer.parseInt(tnocon.getText()));
            pst.setDouble(4,Double.parseDouble(tgross.getText()));              
            pst.setDouble(5,Double.parseDouble(tconwei.getText()));            
            pst.setDouble(6,Double.parseDouble(twater.getText()));              
            pst.setDouble(7,Double.parseDouble(tmatu.getText()));  
            pst.setDouble(8,Double.parseDouble(tnetlb.getText()));
            pst.setString(9,ttrans.getValue());
           
            pst.setInt(10, Integer.valueOf(turnno.getText()));
           
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
        
        catch (SQLException ex) {
            
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //method implimentation for clear textfield. 
    private void cleartextfield(){
            
        turnno.clear();
        tdate.clear();
        tsupid.setValue("");
        tnocon.clear();
        tgross.clear();
        tconwei.clear();
        twater.clear();
        tmatu.clear();
        tnetlb.setText(null);
        ttrans.setValue("");
    }
    @FXML
    private void DeleteRecord(ActionEvent event) {
     //delete collection details.   
         String sql ="delete from collectionturn where TurnNo = ?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(turnno.getText()));
            
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
    private void CancelRecord(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void NewRecord(ActionEvent event) throws SQLException {
       //Get new turn no and date. 
        tsupid.setValue("");
        ttrans.setValue("");
        tnocon.setText(null);
        tgross.setText(null);
        tconwei.setText(null);
        twater.setText(null);
        tmatu.setText(null);
        tnetlb.setText(null);        
        turnno.setText(String.valueOf(generateTurnNo()));
        tdate.setText(new Date().toString());
    }
    
  //method implimentation for generate new Turn number.
    private Integer generateTurnNo() throws SQLException {
        int TurnNo = 1;
        
        String query = "SELECT max(TurnNo) FROM collectionturn";
        rs = conn.createStatement().executeQuery(query);
       while(rs.next()){
           TurnNo = rs.getInt("max(TurnNo)") + 1;
       }
//        if(rs.wasNull()){
//            System.out.println("null");
//            TurnNo = 1;
//        }else{
//            System.out.println("notnull");
//            rs.last();
//            TurnNo = rs.getInt(1);
//        }
        return TurnNo;
    }
    
    
     //method for set cell values in table.
    private void setcelltable(){

        TurnNocol.setCellValueFactory(new PropertyValueFactory<>("turnno"));
        Datecol.setCellValueFactory(new PropertyValueFactory<>("tdate"));
        SupplierIDcol.setCellValueFactory(new PropertyValueFactory<>("supid"));
        NoOfContainerscol.setCellValueFactory(new PropertyValueFactory<>("nocon"));
        GrossWeightcol.setCellValueFactory(new PropertyValueFactory<>("gross"));
        WeightOfContainercol.setCellValueFactory(new PropertyValueFactory<>("wecon"));
        WaterWeightcol.setCellValueFactory(new PropertyValueFactory<>("water"));
        MatureLeavesWeightcol.setCellValueFactory(new PropertyValueFactory<>("mature"));
        NetWeightcol.setCellValueFactory(new PropertyValueFactory<>("net"));
        TransportUsedcol.setCellValueFactory(new PropertyValueFactory<>("trans"));
        Teatable.setItems(data);
        
    }
    
     //method implimentation for load data from database.
    private void loadDataFromDatabase(){
        
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from collectionturn");
            rs = pst.executeQuery();
            while(rs.next())
            {
               data.add(new Tealist(""+rs.getInt(1),rs.getString(2),""+rs.getInt(3),""+rs.getInt(4),""+rs.getDouble(5),""+rs.getDouble(6),""+rs.getDouble(7),""+rs.getDouble(8),""+rs.getDouble(9),rs.getString(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Teatable.setItems(data);
        
    
    }
    
    // method implimentation for set cell values from table to textfield.
       private void SetCellValueFromTableToTextField()
    {
        Teatable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Tealist f1 = Teatable.getItems().get(Teatable.getSelectionModel().getSelectedIndex());
            
            turnno.setText(f1.getTurnno());
            tdate.setText(f1.getTdate());
            String r = f1.getSupid();
            tsupid.setValue(r);
         
            tnocon.setText(f1.getNocon());
            tgross.setText(f1.getGross());
            tconwei.setText(f1.getWecon());
            twater.setText(f1.getWater());
            tmatu.setText(f1.getMature());
            tnetlb.setText(f1.getNet());
            
            String h = f1.getTrans();
            ttrans.setValue(h);
            comboBox.setItems(list1);
            comboBox1.setItems(list2);
        });
    }
       
      //Method implimentation for fill Combobox with Supplier ID 
      public void fillCombobox()  {
        
            list2.clear();
            try{
            String query ="select SupplierID from supplier";
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while (rs.next()){
             list2.add(rs.getString("SupplierID"));
            
            }
             pst.close();
             rs.close();
            }catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
       
       
}

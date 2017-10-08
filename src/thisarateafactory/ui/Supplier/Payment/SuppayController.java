/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.Payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import thisarateafactory.ui.Supplier.dialog.AlertDialog;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import thisarateafactory.ui.Supplier.newthisara.FXMLDocumentController;

/**
 * FXML Controller class
 *
 * @author user pc
 */
public class SuppayController implements Initializable {
    
    //Create connection class object.
    Connection conn = null;
    //Create preparedStatement class object.
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
     //Create ResultSet class object.
    ResultSet rs = null;
    private ObservableList<paylist>data;
    @FXML
    private JFXTextField PayBill;
    @FXML
    private JFXTextField paydate;
    @FXML
    private JFXComboBox<String> spayid;
    final  ObservableList list1 = FXCollections.observableArrayList();
     final ComboBox comboBox = new ComboBox(list1);
    @FXML
    private JFXTextField prate;
    @FXML
    private JFXButton supleave;
    @FXML
    private JFXButton pamount;
    @FXML
    private JFXButton ptrans;
    @FXML
    private JFXButton pgross;
    @FXML
    private JFXTextField pleavelb;
    @FXML
    private JFXTextField payamulb;
    @FXML
    private JFXTextField ptranlb;
    @FXML
    private JFXTextField pgrosslb;
    @FXML
    private JFXTextField pcash;
    @FXML
    private JFXTextField ptea;
    @FXML
    private JFXTextField pferty;
    @FXML
    private JFXButton pbalance;
    @FXML
    private JFXTextField pbalnlb1;
    @FXML
    private TableColumn<?, ?> BillNocol;
    @FXML
    private TableColumn<?, ?> PaidDatecol;
    @FXML
    private TableColumn<?, ?> SupplierIDcol;
    @FXML
    private TableColumn<?, ?> SuppliedLeavescol;
    @FXML
    private TableColumn<?, ?> Ratecol;
    @FXML
    private TableColumn<?, ?> PayAmountcol;
    @FXML
    private TableColumn<?, ?> Transportcol;
    @FXML
    private TableColumn<?, ?> GrossPaymentcol;
    @FXML
    private TableColumn<?, ?> TeaDeductioncol;
    @FXML
    private TableColumn<?, ?> CashInAdvancecol;
    @FXML
    private TableColumn<?, ?> Fertilizercol;
    @FXML
    private TableColumn<?, ?> NetPaymentcol;
    @FXML
    private JFXButton padd;
    @FXML
    private JFXButton pupd;
    @FXML
    private JFXButton pdel;
    @FXML
    private JFXButton pcan;
    @FXML
    private JFXButton pnew;
    @FXML
    private TableView<paylist> Paytable;
    LocalDate l1 = LocalDate.now();
     //create a object from util data class.
    java.util.Date currentDate = new java.util.Date();
     //create a object from data class.
   // Date currentDate = new Date();
    //private TableView<paylist> PayTable;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        spayid.setItems(list1);
        
        //emptyValidation method calling.
        emptyValidation( prate,"No Input Given");
        emptyValidation( pcash,"No Input Given");
        emptyValidation( ptea,"No Input Given");
        emptyValidation( pferty,"No Input Given");
        
        //doubleValidation method calling.
        doubleValidation( prate,"Not a Cost");
        doubleValidation( pcash,"Not a Cost");
        doubleValidation( ptea,"Not a Cost");
        doubleValidation( pferty,"Not a Cost");
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
    //method implimentation for doubleValidation(for JFXTextField ).
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
    private void GetSupLeaves(ActionEvent event) {
      // Get net weight of monthly supplied tea leaves.  
        double cs =0;
        String q1 = "SELECT SUM(NetWeight)  FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ?";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,spayid.getValue());
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
             
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("SUM(NetWeight)");
                String cs1 = new Double(cs).toString();
                pleavelb.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void CalPayAmount(ActionEvent event) {
       //calculation the payment for supplied tea leaves. 
         double rate = Double.parseDouble(prate.getText());
        double gross = Double.parseDouble(pleavelb.getText());
        
        
        double payamount = gross * rate ;
                payamulb.setText(String.valueOf(payamount));
    }

    @FXML
    private void CalTransport(ActionEvent event) {
        //calculate the payment for transport.
        double cs =0;
        String q1 = "SELECT SUM(NetWeight) FROM collectionturn where SupplierID = ? AND DATE BETWEEN ? AND ? and TransportUsed LIKE 'Not Used'";
        
        try{
            pst = conn.prepareStatement(q1);
             pst.setString(1,spayid.getValue());
            pst.setDate(2, java.sql.Date.valueOf(LocalDate.now().withDayOfMonth(1)));
             pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
             
             rs = pst.executeQuery();
           while(rs.next())
            {
                cs = rs.getInt("SUM(NetWeight)");
                String cs1 = new Double(cs).toString();
                ptranlb.setText(cs1);
           }


        }
        catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }

    @FXML
    private void CalGrossPay(ActionEvent event) {
        //calculate the gross payment for supplied tea leaves.
         double pamount = Double.parseDouble(payamulb.getText());
        double trans = Double.parseDouble(ptranlb.getText());
      
        
        
        double grosspay = pamount + trans ;
                pgrosslb.setText(String.valueOf(grosspay));
    }

    @FXML
    private void CalBalance(ActionEvent event) {
        //Calculate balance payment deducting cash,tea,fertilizer costs.
        double grosspay = Double.parseDouble(pgrosslb.getText());
        double cash = Double.parseDouble(pcash.getText());
        double tead = Double.parseDouble(ptea.getText());
        double ferty = Double.parseDouble(pferty.getText());
        
            double NetWeight = grosspay -( cash + tead + ferty) ;
            
              if( NetWeight >= 0){
                pbalnlb1.setText(String.valueOf(NetWeight));
              }
               else{
                AlertDialog.didplay("Info", "Invalied Entry");
                cleartextfield();
        }
    }

    @FXML
    private void AddRecord(ActionEvent event) throws SQLException {
        // Add payment details.
        
        if(CountPayment()<1){
        String q = "INSERT INTO supplierpayment(BillNo,PaidDate,SupplierID,SuppliedLeaves,Rate,PayAmount,Transport,GrossPayment,TeaDeduction,CashInAdvance,Fertilizer,NetPayment)"
                    + "VALUES(null,current_date,?, ?, ?, ?, ?, ?,?,?,?,?)";
        
        String q1 ="INSERT INTO transaction(TransactionID,Type,Date,Amount)"
                    + "VALUES(null,?,current_date,?)" ;
        
        System.out.println("ADD");
        
        try{
        
            pst=conn.prepareStatement(q);
            pst1 =conn.prepareStatement(q1);
        
            pst.setString(1, spayid.getValue());           
            pst.setDouble(2,Double.parseDouble(pleavelb.getText()));  
            pst.setDouble(3,Double.parseDouble(prate.getText()));            
            pst.setDouble(4,Double.parseDouble(payamulb.getText()));              
            pst.setDouble(5,Double.parseDouble(ptranlb.getText()));  
            pst.setDouble(6,Double.parseDouble(pgrosslb.getText()));
            pst.setDouble(7,Double.parseDouble(ptea.getText()));            
            pst.setDouble(8,Double.parseDouble(pcash.getText()));              
            pst.setDouble(9,Double.parseDouble(pferty.getText()));  
            pst.setDouble(10,Double.parseDouble(pbalnlb1.getText()));
            
                         
            
            
            pst1.setString(1,"Supplier");
             pst1.setDouble(2,Double.parseDouble(pbalnlb1.getText()));
           
         
            int x= pst.executeUpdate();
            //int y= pst1.executeUpdate();
            
            if(x==1)
            {
               
                AlertDialog.didplay("Info", "Data Insert Successfully");
              setcelltable();
              loadDataFromDatabase();
              addToTransaction();
              cleartextfield();
            }
            
        }catch (RuntimeException e) {
            
            AlertDialog.didplay("Info", "Please Enter Values");
            System.out.println(e);
            System.out.println("faile!!!!!");
            
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        catch (SQLException ex) {
           AlertDialog.didplay("Info", "Please Enter Valid Values");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        else
        {
            AlertDialog.didplay("Info", " You have alredy  Paid");
             cleartextfield();
        }
        
        
        
        
    }
    
    private void addToTransaction(){
        System.out.println("transaction");
        String query = "select BillNo, NetPayment from supplierpayment where BillNo = (select max(BillNo) from supplierpayment)";
        int id = 0;
        double payment = 0;
        try {
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("BillNo");
                payment = rs.getDouble("NetPayment");
                System.out.println(id);
                System.out.println(payment);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, current_date, ?)";
        
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Supplier");
            //pst.setString(3, pleavelb.getText());
            pst.setDouble(3, payment);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transactions");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @FXML
    private void UpdateRecord(ActionEvent event) {
        //Update payment details.
       
         String sql = "UPDATE supplierpayment SET PaidDate=?,SupplierID=?,SuppliedLeaves=?,Rate=?,PayAmount=?,Transport=?,GrossPayment=?,TeaDeduction=?,CashInAdvance=?,Fertilizer=?,NetPayment=?"+" WHERE BillNo = ?";
        System.out.println("update");
        try{
         pst=conn.prepareStatement(sql);
          
            pst.setString(1,paydate.getText());
           
            pst.setString(2,spayid.getValue());
            
            pst.setDouble(3,Double.parseDouble(pleavelb.getText()));              
            pst.setDouble(4,Double.parseDouble(prate.getText()));            
            pst.setDouble(5,Double.parseDouble(payamulb.getText()));              
            pst.setDouble(6,Double.parseDouble(ptranlb.getText()));  
            pst.setDouble(7,Double.parseDouble(pgrosslb.getText()));
            pst.setDouble(8,Double.parseDouble(ptea.getText()));            
            pst.setDouble(9,Double.parseDouble(pcash.getText()));              
            pst.setDouble(10,Double.parseDouble(pferty.getText()));  
            pst.setDouble(11,Double.parseDouble(pbalnlb1.getText()));
          
            pst.setInt(12, Integer.valueOf(PayBill.getText()));
           
            int x= pst.executeUpdate();
            
            if(x==1)
            
               
                 AlertDialog.didplay("Info", "Data Updated Successfully");
              setcelltable();
              loadDataFromDatabase();
              cleartextfield();
        }catch (RuntimeException ex) {
           AlertDialog.didplay("Info", "Please enter values");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
             AlertDialog.didplay("Info", "Please enter valid values");
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

      //method implimentation for clear textfield. 
    private void cleartextfield(){
            
        PayBill.clear();
        paydate.clear();
        spayid.setValue("");
        
        pleavelb.clear();
        prate.clear();
        payamulb.clear();
        ptranlb.clear();
        pgrosslb.clear();
        ptea.clear();
        pcash.clear();
        pferty.clear();
        pbalnlb1.clear();
    }
    @FXML
    private void DeleteRecord(ActionEvent event) {
        //Delete paymen details.
            
        int idx = 0;
         String sql ="delete from supplierpayment where BillNo = ?";
        try {
            pst=conn.prepareStatement(sql);
            pst.setInt(1, Integer.valueOf(PayBill.getText()));
            idx = Integer.valueOf(PayBill.getText());
            int x= pst.executeUpdate();
            
            if(x==1)
            
                
                             AlertDialog.didplay("Info", "Data Delete Successfully");

               
               loadDataFromDatabase();
               cleartextfield();
               
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String query = "delete from transactions where RefID = ? and Type = ?";
        try {
            pst = conn.prepareStatement(query);
            pst.setInt(1, idx);
            pst.setString(2, "Supplier");
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully deleted from transactions");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SuppayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void Clear(ActionEvent event) {
        
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void NewRecord(ActionEvent event) throws SQLException {
        
        //Get new bill no and date.
        spayid.setValue("");
        pleavelb.setText(null);
        prate.setText(null);
        payamulb.setText(null);
        ptranlb.setText(null);
        pgrosslb.setText(null);
        ptea.setText(null); 
        pcash.setText(null);
        pferty.setText(null);
        pbalnlb1.setText(null);
        PayBill.setText(String.valueOf(generateBillNo()));
        paydate.setText(new Date().toString());
    }
    //method implimentation for generate new Bill number
    private Integer generateBillNo() throws SQLException {
      int BillNo = 1;
        String query = "SELECT max(BillNo) FROM supplierpayment";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()){
            BillNo = rs.getInt("max(BillNo)")+1;
        }
        return BillNo;
    }
    
     //method for set cell values in table.
     private void setcelltable(){

        BillNocol.setCellValueFactory(new PropertyValueFactory<>("Bill"));
        PaidDatecol.setCellValueFactory(new PropertyValueFactory<>("paydate"));
        SupplierIDcol.setCellValueFactory(new PropertyValueFactory<>("SupplierID"));
        SuppliedLeavescol.setCellValueFactory(new PropertyValueFactory<>("supleave"));
        Ratecol.setCellValueFactory(new PropertyValueFactory<>("rate"));
        PayAmountcol.setCellValueFactory(new PropertyValueFactory<>("payamount"));
        Transportcol.setCellValueFactory(new PropertyValueFactory<>("trans"));
        GrossPaymentcol.setCellValueFactory(new PropertyValueFactory<>("grosspay"));
        TeaDeductioncol.setCellValueFactory(new PropertyValueFactory<>("tead"));
        CashInAdvancecol.setCellValueFactory(new PropertyValueFactory<>("cash"));
        Fertilizercol.setCellValueFactory(new PropertyValueFactory<>("ferty"));
        NetPaymentcol.setCellValueFactory(new PropertyValueFactory<>("balpay"));
     Paytable.setItems(data);
        
    }
     
        //method implimentation for load data from database.
      private void loadDataFromDatabase(){
        
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from supplierpayment");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new paylist(""+rs.getInt(1),rs.getString(2),""+rs.getInt(3),""+rs.getDouble(4),""+rs.getDouble(5),""+rs.getDouble(6),""+rs.getDouble(7),""+rs.getDouble(8),""+rs.getDouble(9),""+rs.getDouble(10),""+rs.getDouble(11),""+rs.getDouble(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
       Paytable.setItems(data);
        
    
    }
      
       // method implimentation for set cell values from table to textfield.
        private void SetCellValueFromTableToTextField()
    {
       Paytable.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            paylist f1 = Paytable.getItems().get(Paytable.getSelectionModel().getSelectedIndex());
            
            PayBill.setText(f1.getBill());
            paydate.setText(f1.getPaydate());
           
            String r = f1.getSupplierID();
            spayid.setValue(r);
            
            pleavelb.setText(f1.getSupleave());
            prate.setText(f1.getRate());
            payamulb.setText(f1.getPayamount());
            ptranlb.setText(f1.getTrans());
            pgrosslb.setText(f1.getGrosspay());
            ptea.setText(f1.getTead());
            pcash.setText(f1.getCash());
            pferty.setText(f1.getFerty());
            pbalnlb1.setText(f1.getBalpay());
            comboBox.setItems(list1);
            
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
      //Count the payment in perticular period.  
         public Integer CountPayment() throws SQLException{
    
        int sid = 0;
        
        pst =conn.prepareStatement("SELECT count( SupplierID ) FROM supplierpayment WHERE SupplierID = ? and PaidDate BETWEEN ? AND ?");
        pst.setString(1,spayid.getValue());
         pst.setDate(2, java.sql.Date.valueOf(LocalDate.now().withDayOfMonth(1)));
        pst.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
        rs =pst.executeQuery();
         while(rs.next())
            {
                sid = rs.getInt("count( SupplierID )");
                
           }
        rs.close();
        
        return sid;
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/supplierPayment.jrxml").getAbsolutePath();
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

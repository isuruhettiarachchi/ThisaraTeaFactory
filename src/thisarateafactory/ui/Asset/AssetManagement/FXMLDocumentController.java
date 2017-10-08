/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Asset.AssetManagement;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateNumbersOnly;
import static thisarateafactory.validation.validateTextFields.validatePhone;

/**
 *
 * @author LUDZ
 */
public class FXMLDocumentController implements Initializable {
    
    DateTimeFormatter dtf;
    LocalDate currentDate;
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<ELEClist> data;
    private ObservableList<FireList> Fire;
    private ObservableList<TPlist> TP;
    private ObservableList<PaymentList> PL;
    private ObservableList<BankList> BL;
    private ObservableList<OtherList> OL;
    
    @FXML
    private JFXTextField elecBillNoText;
    @FXML
    private JFXTextField elecUnitsText;
    @FXML
    private JFXTextField electAmountText;
    @FXML
    private JFXTextField elecSearchDetailstxt;
    @FXML
    private DatePicker elecDate;
    @FXML
    private TableView<ELEClist> TableElectricity;
    @FXML
    private TableColumn<?, ?> ETable_column_Unit;
    @FXML
    private TableColumn<?, ?> ETable_column_Amount;
    @FXML
    private TableColumn<?, ?> ETable_column_Pay;
    @FXML
    private TableColumn<?, ?> ETable_column_BillNo;
    @FXML
    private TableColumn<?, ?> ETable_column_Date; 
   
    @FXML
    private JFXButton Ass_E_BTNPay; 
    @FXML
    private JFXButton elecButtonAdd;
    @FXML
    private JFXButton elecUpdateBtn;
    @FXML
    private JFXButton Ass_E_BTNDelete;
    
    
    @FXML
    private JFXTextField firevoucherNoText;
    @FXML
    private JFXTextArea fireaddresstxt;
    @FXML
    private JFXTextField fireCostTxt;
    @FXML
    private JFXTextField FSearchDetailstxt1;
    @FXML
    private TableView<FireList> FireTable;
    @FXML
    private TableColumn<?, ?> fireVoucherClmn;
    @FXML
    private TableColumn<?, ?> fireAddressClmn;
    @FXML
    private TableColumn<?, ?> fireCostClmn;
    @FXML
    private TableColumn<?, ?> fireDateClmn;
    @FXML
    private JFXButton FAddBtn;
    
    
    
    
    @FXML
    private JFXTextField TbillNoTxt;
    @FXML
    private JFXTextField TphoneNoTxt;
    @FXML
    private JFXTextField TamountTxt;
    @FXML
    private JFXTextField TPSearchDetailstxt1;
    @FXML
    private TableView<TPlist> TableTelephone;
    @FXML
    private TableColumn<?, ?> TBillNOcolumn_;
    @FXML
    private TableColumn<?, ?> TPhoneNumbercolumn;
    @FXML
    private TableColumn<?, ?> TAmountcolumn;
    @FXML
    private JFXButton TAddBtn;
    @FXML
    private JFXButton TUpdateBtn;
    @FXML
    private JFXButton TDeleteBtn;
    @FXML
    private JFXButton TPay;
    @FXML
    private DatePicker TDate;
    @FXML
    private TableColumn<?, ?> TDatecolumn;
    @FXML
    private TableColumn<?, ?> TPaycolumn;
    
   
   
    
    
    
    @FXML
    private TableView<PaymentList> TablePayment;
    @FXML
    private TableColumn<?, ?> PTable_column_Payment;
    @FXML
    private TableColumn<?, ?> PTable_column_Date;
    @FXML
    private TableColumn<?, ?> PTable_column_Amount;
    @FXML
    private TableColumn<?, ?> PTable_column_Catergory;
    @FXML
    private TableColumn<?, ?> PTable_column_TbillNo;
    @FXML
    private TableColumn<?, ?> PTable_column_EbillNo;
    @FXML
    private TableColumn<?, ?> PTable_column_AccNo;
    @FXML
    private TableColumn<?, ?> PTable_column_FNo;
    @FXML
    private TableColumn<?, ?> PTable_column_ONo;
    
    
    
    @FXML
    private Label ElecTAmountLable;
    
    
    
    @FXML
    private JFXTextField OVNuber;
    @FXML
    private JFXTextField OGFCost;
    @FXML
    private JFXTextField ORAPayment;
    @FXML
    private JFXTextField SLoan;
    @FXML
    private JFXTextField OSearchDetailstxt;
    @FXML
    private TableView<OtherList> OtherTable;
    @FXML
    private TableColumn<?, ?> OVNoClmn;
    @FXML
    private TableColumn<?, ?> GFCClmn;
    @FXML
    private TableColumn<?, ?> ORAPaymentClmn;
    @FXML
    private TableColumn<?, ?> SloanClmn;
    @FXML
    private JFXButton OAddBtn;
    @FXML
    private TableColumn<?, ?> ODateClmn;
    @FXML
    private JFXTextField BaccountTXT;
    @FXML
    private JFXTextField BnameTXT;
    @FXML
    private JFXTextArea BdescreptionTXT;
    @FXML
    private TableColumn<?, ?> BAcoountClmn;
    @FXML
    private TableColumn<?, ?> BNameClmn;
    @FXML
    private TableColumn<?, ?> BDescriptionClmn;
    @FXML
    private TableColumn<?, ?> BCostClmn;
    @FXML
    private TableColumn<?, ?> BDateClmn;
    @FXML
    private JFXButton BpayBtn;
    @FXML
    private JFXTextField BCostTxt;
    @FXML
    private TableView<BankList> BankTable;
    @FXML
    private Label TeleLable;
    @FXML
    private JFXTextField BSearchDetailstxt11;
    @FXML
    private JFXTextField Psearchtxt;
   

   


   
   
   

 
 
    

    
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ass_E_BTNPay.setDisable(true);
        TPay.setDisable(true);
        
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentDate = LocalDate.now();
        
         data = FXCollections.observableArrayList();
         Fire = FXCollections.observableArrayList();
          TP = FXCollections.observableArrayList();
          PL=FXCollections.observableArrayList();
           OL=FXCollections.observableArrayList();
           BL=FXCollections.observableArrayList();
        con = databaseHandler.getConnection();
        // TODO
        //SearchDetails();
        // electriciy Table Load
         loadDataFromDatabase();
        setCellTable();
        setCellValueFromTableToTextField();
        SearchDetails() ;
        
        //Firewood Table Load
        setCellFireTable();
        FireTableloadDataFromDatabase();
        FSearchDetails();
//        setCellValueFromFireTableToTextField();
        

        //Telephone bill Table Load
        setTelephoneCellTable();
        loadDataTELEromDatabase();
        setCellValueTeleTableToTextField();
        TPSearchDetails();
        
        //Payment Table Load
        setPaytableCellTable();
        loadDataPayromDatabase();
        
        
        
        //otherEx table Load
        setOthertableCellTable();
        loadDataOtherTfromDatabase();
        OSearchDetails();
        
        //BankEX table Laod
        setBanktableCellTable();
        loadDataBankTfromDatabase();
        BSearchDetails() ;
       getEtotal();
       getTeleTotal();
      
      
      
      
      //validation
      
        RequiredFieldValidator valid = new RequiredFieldValidator();
        NumberValidator numv = new NumberValidator();
        elecBillNoText.getValidators().add(valid);
        elecUnitsText.getValidators().add(valid);
        electAmountText.getValidators().add(valid);
       firevoucherNoText.getValidators().add(valid);
        fireCostTxt.getValidators().add(valid);
        TbillNoTxt.getValidators().add(valid);
        TphoneNoTxt.getValidators().add(numv);
        OVNuber.getValidators().add(valid);
        //BaccountTXT.getValidators().add(valid);
        
        valid.setMessage("Cant be Empty"); 
        numv.setMessage("MobileNo must be a Number");
         elecBillNoText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     elecBillNoText.validate();
                }
            }
        });
           elecUnitsText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     elecUnitsText.validate();
                }
            }
        });
             electAmountText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     electAmountText.validate();
                }
            }
        });
              firevoucherNoText.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     firevoucherNoText.validate();
                }
            }
        });
                fireCostTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     fireCostTxt.validate();
                }
            }
        });
                 TbillNoTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                     TbillNoTxt.validate();
                }
            }
        });
                TphoneNoTxt.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                      TphoneNoTxt.validate();
                }
            }
        });
//          OVNuber.focusedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//                if(!newValue){
//                      OVNuber.validate();
//                }
//            }
//        });
           BaccountTXT.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                      BaccountTXT.validate();
                }
            }
        });
           
           
         
         
           
    }    
String p;
String gf;
String tt;


//TAB Electricity 
    @FXML
    private void SearchDetails() {
        elecSearchDetailstxt.setOnKeyReleased(e->{
            String sql="select * from electricity where BillNo= ?";
            
            if(elecSearchDetailstxt.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                data.clear();
            try {
                int i=Integer.parseInt(elecSearchDetailstxt.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      data.add(new ELEClist("" + rs.getInt(1), "" + rs.getInt(2),"" + rs.getDouble(3),""+ rs.getString(4),""+ rs.getString(5)));
                
                }
                
                TableElectricity.setItems(data);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }

    @FXML
    private void elecDetailsAdd(ActionEvent event) {
        
        if (elecBillNoText.getText() == null ||elecBillNoText.getText().trim().isEmpty()||!validateNumbersOnly(elecBillNoText.getText()) ){
            System.out.println("Bill Number Can't be empty And Bill Nuber must be a number");
            return;
            }
        if (elecUnitsText.getText() == null || elecUnitsText.getText().trim().isEmpty()||! validateNumbersOnly(elecUnitsText.getText()))
        {
            System.out.println("Units Can't be empty and unit must be a number");
            return;
        }  
        if (electAmountText.getText() == null || electAmountText.getText().trim().isEmpty()||! validateNumbersOnly(elecBillNoText.getText())){
            
            System.out.println("Amount  Can't be empty and Amont must be a number");
            return;
        }    
            String query = "insert into electricity (BillNo,Units,Amount,Date)"
                + "Values(?, ?, ?, ?)";

        int elecbillNo = Integer.parseInt(elecBillNoText.getText());
        int elecunits = Integer.parseInt(elecUnitsText.getText());
        double elecamount = Double.parseDouble(electAmountText.getText());
        String Elec_date = elecDate.getValue().toString();

        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, elecbillNo);
            pst.setInt(2, elecunits);
            pst.setDouble(3, elecamount);
            pst.setString(4,Elec_date);

            int x = pst.executeUpdate();
            if (x == 1) {
                System.out.println("OKk");
            }

        } catch (Exception ex) {
            System.out.println("fail");

        }
         loadDataFromDatabase();
        setCellTable();
       setCellValueFromTableToTextField();
        
      getEtotal();
   
    }
    @FXML
    private void elecupdate(ActionEvent event) {
        System.out.println(p);
         if (p.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("Can't Update a made Payment");
             alert.showAndWait();
         }
         else if (p.equalsIgnoreCase("null")){
               if (elecBillNoText.getText() == null ||elecBillNoText.getText().trim().isEmpty()||!validateNumbersOnly(elecBillNoText.getText()) ){
            System.out.println("Bill Number Can't be empty And Bill Nuber must be a number");
            return;
            }
        if (elecUnitsText.getText() == null || elecUnitsText.getText().trim().isEmpty()||! validateNumbersOnly(elecUnitsText.getText()))
        {
            System.out.println("Units Can't be empty and unit must be a number");
            return;
        }  
        if (electAmountText.getText() == null || electAmountText.getText().trim().isEmpty()||! validateNumbersOnly(elecBillNoText.getText())){
            
            System.out.println("Amount  Can't be empty and Amont must be a number");
            return;
        } 
       String sql = "UPDATE electricity SET  Units = ?,Amount = ?, Date = ? where BillNo = ?  ";

        try {
        int elecbillNo = Integer.parseInt(elecBillNoText.getText());
        int elecunits = Integer.parseInt(elecUnitsText.getText());
        double elecamount = Double.parseDouble(electAmountText.getText());
         String Elec_date = elecDate.getValue().toString();
            pst = (PreparedStatement) con.prepareStatement(sql);
           
            pst.setInt(1, elecunits);
            pst.setDouble(2, elecamount);
            pst.setString(3,Elec_date);
            pst.setInt(4, elecbillNo);
            
           // pst.setInt(elecunits, elecunits);
            int x = pst.executeUpdate();
            System.out.println("ASs");

            if (x == 1) {
                System.out.println("Updated Successfully");
                loadDataFromDatabase();
            }

        } catch (Exception ex) {
            System.out.println("fail");
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
          getEtotal();
        
    }

    @FXML
    private void Ass_E_delete(ActionEvent event) {
         if (p.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("Can't Delete a made Payment");
             alert.showAndWait();
         }
         else if(p.equalsIgnoreCase("null")){
        String sql ="DELETE FROM electricity WHERE BillNo= ?";
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, Integer.parseInt(elecBillNoText.getText()));
            System.out.println("kk");
            int x = pst.executeUpdate();
            
            if(x==1)
            {
                System.out.println("Deleted Successfully");
                loadDataFromDatabase();  
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
         }
         else{}
          getEtotal();
    }
     @FXML
    private void Ass_E_Pay(ActionEvent event) {
          Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation Dialog");
            alert1.setHeaderText("Look, a Confirmation Dialog");
            alert1.setContentText("Are You Sure ?, Can't change or delete made payment!");

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK){
                 
         System.out.println(p);
         if (p.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("This Payment has aleady made");
             alert.showAndWait();
         }
         else{
             
               if (elecBillNoText.getText() == null ||elecBillNoText.getText().trim().isEmpty()||!validateNumbersOnly(elecBillNoText.getText()) ){
            System.out.println("Bill Number Can't be empty And Bill Nuber must be a number");
            return;
            }
        if (elecUnitsText.getText() == null || elecUnitsText.getText().trim().isEmpty()||! validateNumbersOnly(elecUnitsText.getText()))
        {
            System.out.println("Units Can't be empty and unit must be a number");
            return;
        }  
        if (electAmountText.getText() == null || electAmountText.getText().trim().isEmpty()||! validateNumbersOnly(elecBillNoText.getText())){
            
            System.out.println("Amount  Can't be empty and Amont must be a number");
            return;
        } 
        
        
    int elecbillNo = Integer.parseInt(elecBillNoText.getText());
       String sq= "select * from electricity where BillNo = "+elecbillNo+" LIKE 'paid'" ;
            try {
            pst = (PreparedStatement) con.prepareStatement(sq);
            
            
            
            int x = pst.executeUpdate();
            
            if(x ==1)
            {
                System.out.println("fffff");
        
            }
            else{
            
            }
            
                }
            catch (Exception e) {
                System.out.println(e);
                
        }
            
                   try {
        //int elecbillNo = Integer.parseInt(elecBillNoText.getText());
         String sql = "UPDATE electricity SET Pay = 'Paid' where BillNo = "+elecbillNo+" ";
       
        
            pst = (PreparedStatement) con.prepareStatement(sql);
           
           
           // pst.setInt(5, elecbillNo);
           
             
            int z = pst.executeUpdate();
            System.out.println("ASs");

            if (z == 1) {
                System.out.println("Updated Successfully");
                loadDataFromDatabase();
       
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           System.out.println("gh");
    //payment
    
        int EbillNo = Integer.parseInt(elecBillNoText.getText());
        double ELECcamount = Double.parseDouble(electAmountText.getText());
        
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        

        //Insert to payment table
        
        String query = "INSERT INTO payment ( Date, Description, Amount ,  EBillNo)"
                +"Values(?,'Electricity',"+ELECcamount+","+EbillNo+")";
              
        

        try {
            pst = (PreparedStatement) con.prepareStatement(query);
             
            pst.setTimestamp(1, date);
            

            int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
            }

        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hjk");
        }
        
        
        
//        
//        //Transaction Table
//        
//        String qu = "INSERT INTO transactions ( Type, Date, Amount)"
//                +"Values('Asset',?,"+ELECcamount+")";
//              
//        
//
//        try {
//            pst = (PreparedStatement) con.prepareStatement(qu);
//             
//            pst.setTimestamp(1, date);
//            
//
//            int y = pst.executeUpdate();
//            if (y == 1) {
//                System.out.println("add");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//        }

query = "select max(PaymentID) as PaymentID, Amount from Payment";
        int id = 0;
        double amnt = 0;
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("PaymentID");
                amnt = Double.parseDouble(electAmountText.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
        
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Asset");
            pst.setDate(3, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transaction");
            }
        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("sss");
        }
        
        getEtotal();
        setPaytableCellTable();
        loadDataPayromDatabase();
         
         }
            
         
            }
    }
    
    private void addToTransaction(){
        
    }
      private void setCellTable() {
        
      ETable_column_BillNo.setCellValueFactory(new PropertyValueFactory<>("Bill"));
      ETable_column_Unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
      ETable_column_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
      ETable_column_Date.setCellValueFactory(new PropertyValueFactory<>("date"));
      ETable_column_Pay.setCellValueFactory(new PropertyValueFactory<>("pay"));
      
      

    }


    private void loadDataFromDatabase() {
        data.clear();
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from electricity");
            rs = pst.executeQuery();
    // String dat=   rs.getDate(4).toString();
    // System.out.println(dat);
            while (rs.next()) {
            String dat=   rs.getDate(4).toString();
            
                data.add(new ELEClist("" + rs.getInt(1), "" + rs.getInt(2),"" + rs.getDouble(3),""+ rs.getString(4),""+ rs.getString(5)));
 //String dat=   rs.getDate(4).toString();
     //System.out.println(dat);
            }

        } catch (SQLException ex) {
            
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        TableElectricity.setItems(data);
       
    }
    
  

    /*set values to text field in Electricity table*/
       
    
    private void setCellValueFromTableToTextField() {
        TableElectricity.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            ELEClist f1 = TableElectricity.getItems().get(TableElectricity.getSelectionModel().getSelectedIndex());
             
            elecBillNoText.setText(f1.getBill());
            elecUnitsText.setText(f1.getUnit());
            electAmountText.setText(f1.getAmount());
             p= f1.getPay();
             String DT = f1.getDate();
             LocalDate dg = LocalDate.parse(DT);
             elecDate.setValue(dg);
            Ass_E_BTNPay.setDisable(false);
        });
        
        
    
    }

    
    
    //Tab FIREWOOD
    
    
    @FXML
    private void FSearchDetails() {
        FSearchDetailstxt1.setOnKeyReleased(e->{
            String sql="select * from firewood where VoucherNo= ?";
            
            if(FSearchDetailstxt1.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                Fire.clear();
            try {
                int i=Integer.parseInt(FSearchDetailstxt1.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                       Fire.add(new FireList(""+ rs.getInt(1), rs.getString(2), ""+ rs.getDate(3), ""+ rs.getDouble(4)));
                
                }
                
                FireTable.setItems(Fire);
                
            } catch (SQLException ex) {
            }
            
            }
        });
       
    }

    @FXML
    private void Fadd(ActionEvent event) {
        
        if (firevoucherNoText.getText() == null || firevoucherNoText.getText().trim().isEmpty() ||! validateNumbersOnly(firevoucherNoText.getText())){
        
            System.out.println("voucher Number Can't be empty And voucher Number must be a number");
            return;
            }    
         if (fireCostTxt.getText() == null || fireCostTxt.getText().trim().isEmpty() ||! validateNumbersOnly(fireCostTxt.getText())){
        
            System.out.println("voucher Number Can't be empty And voucher Number must be a number");
            return;
            } 
          
         
             Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation Dialog");
            alert1.setHeaderText("Look, a Confirmation Dialog");
            alert1.setContentText("Are You Sure ?, Can't change or delete made payment!");

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK)
            {       
                String query = "insert into firewood (VoucherNo, Address, Date, Cost)"
                + "Values(?, ?, ?, ?)";

                int fvoucherNo = Integer.parseInt(firevoucherNoText.getText());
                String faddress = fireaddresstxt.getText();
       // double flength = Double.parseDouble(firelengthtxt.getText());
                double fcost = Double.parseDouble(fireCostTxt.getText());
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                System.out.println(date);
             try {
                    pst = (PreparedStatement) con.prepareStatement(query);
                    pst.setInt(1, fvoucherNo);
                    pst.setString(2, faddress);
                    pst.setTimestamp(3, date);
                    pst.setDouble(4, fcost);
            

                    int x = pst.executeUpdate();
                    if (x == 1) {
                        System.out.println("add");
                    }

                } catch (Exception ex) {
                    System.out.println("fail");
                    //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
             }
                setCellFireTable();
                FireTableloadDataFromDatabase();
//                 setCellValueFromFireTableToTextField();
        
        
                 
                double fcost1 = Double.parseDouble(fireCostTxt.getText());
                java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
        
            String quer= "INSERT INTO payment ( Date, Description, Amount ,  FVoucherNo)"
                +"Values(?,'FireWood',"+fcost1+","+fvoucherNo+")";
              
        //int tamount = Integer.parseInt(TamountTxt.getText());
        

        try {
            pst = (PreparedStatement) con.prepareStatement(quer);
             
            pst.setTimestamp(1, date1);
            

            int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
                 //setPaytableCellTable();
                    loadDataPayromDatabase();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
         //Transaction Table
        
        query = "select max(PaymentID) as PaymentID, Amount from Payment";
        int id = 0;
        double amnt = 0;
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("PaymentID");
                amnt = fcost1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
        
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Asset");
            pst.setDate(3, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transaction");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
            }
            else{
            }
    }

   

   

    
      //Fire Wood Table Load
    
    private void setCellFireTable() {
        
      fireVoucherClmn.setCellValueFactory(new PropertyValueFactory<>("F_voucher"));
     //   System.out.println(new PropertyValueFactory<>("voucherNo"));
      fireAddressClmn.setCellValueFactory(new PropertyValueFactory<>("F_Address"));
      fireDateClmn.setCellValueFactory(new PropertyValueFactory<>("Date"));
      fireCostClmn.setCellValueFactory(new PropertyValueFactory<>("F_Cost"));
   

    }
    private void FireTableloadDataFromDatabase() {
        Fire.clear();
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from firewood");
            rs = pst.executeQuery();
            
            while (rs.next()) {

                Fire.add(new FireList(""+ rs.getInt(1), rs.getString(2), ""+ rs.getDate(3), ""+ rs.getDouble(4)));
                System.out.println(rs.getInt(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    FireTable.setItems(Fire);
    }

    
    
    
    
    
    //TAB TelephoneBill
    
    
    @FXML
    private void TPSearchDetails() {
        TPSearchDetailstxt1.setOnKeyReleased(e->{
            String sql="select * from telephone where BillNo= ?";
            
            if(TPSearchDetailstxt1.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                TP.clear();
            try {
                int i=Integer.parseInt(TPSearchDetailstxt1.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      TP.add(new TPlist(""+ rs.getInt(1), ""+ rs.getInt(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getString(5)));
                
                }
                
                TableTelephone.setItems(TP);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }

    @FXML
    private void TPDetailsAdd(ActionEvent event) {
        if(TbillNoTxt.getText() == null || TbillNoTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TbillNoTxt.getText() )){
            System.out.println(" Bil Number must be a number! ");
            return;
        }
         if(TphoneNoTxt.getText() == null || TphoneNoTxt.getText().trim().isEmpty() ||! validatePhone(TphoneNoTxt.getText())){
             System.out.println("Telephone Number is Incorrect ");
             return;
           }
          if(TamountTxt.getText() == null || TamountTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TamountTxt.getText())){
              System.out.println(" Amount must be a number");
              return;
           }
            if(TDate.getValue().toString() == null || TDate.getValue().toString().trim().isEmpty() ){
              System.out.println(" Date picker can't be Empty");
              return;
           }
         
        String query = "insert into telephone (BillNo, Number, Amount, Date)"
                + "Values(?, ?, ?, ?)";

        int TbillNo = Integer.parseInt(TbillNoTxt.getText());
        String Tnumber = TphoneNoTxt.getText();
        int fcost = Integer.parseInt(TamountTxt.getText());
        String date = TDate.getValue().toString();
        System.out.println(date);
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, TbillNo);
            pst.setString(2,Tnumber);
            pst.setDouble(3, fcost);
            pst.setString(4, date);
            
            

            int x = pst.executeUpdate();
            if (x == 1) {
                System.out.println("add");
                 loadDataTELEromDatabase();
            }

        } catch (Exception ex) {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("fail");       
        
        }
         setTelephoneCellTable();
        loadDataTELEromDatabase();
        setCellValueTeleTableToTextField();
        getTeleTotal();
    }

    @FXML
    private void TPUpdate(ActionEvent event) {
      
            
         if (gf.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("Can't Update a made payment ");
             alert.showAndWait();
         }
         else{
                 if(TbillNoTxt.getText() == null || TbillNoTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TbillNoTxt.getText() )){
            System.out.println(" Bil Number must be a number! ");
            return;
        }
         if(TphoneNoTxt.getText() == null || TphoneNoTxt.getText().trim().isEmpty() ||! validatePhone(TphoneNoTxt.getText())){
             System.out.println("Telephone Number is Incorrect ");
             return;
           }
          if(TamountTxt.getText() == null || TamountTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TamountTxt.getText())){
              System.out.println(" Amount must be a number");
              return;
           }
            if(TDate.getValue().toString() == null || TDate.getValue().toString().trim().isEmpty() ){
              System.out.println(" Date picker can't be Empty");
              return;
           }
        
        String sql = "UPDATE telephone SET  Number = ?,Amount = ? where BillNo = ?  ";

        try {
            int TbillNo = Integer.parseInt(TbillNoTxt.getText());
            
        String Tnumber = TphoneNoTxt.getText();
         
        int fcost = Integer.parseInt(TamountTxt.getText());
        System.out.println("kk");
        //String date = TDate.getValue().toString();
           
            pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,Tnumber);
            //pst.setTimestamp(2, date);
            pst.setInt(2, fcost);
           // pst.setString(3, date);
            pst.setInt(3,TbillNo);
             
            int x = pst.executeUpdate();
            System.out.println("ASs");

            if (x == 1) {
                System.out.println("Updated Successfully");
                loadDataTELEromDatabase();
                getTeleTotal();
                // TphoneNoTxt.getValidators().add(numv);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
         
    }

    @FXML
    private void TPDelete(ActionEvent event) {
        
         if (gf.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("Can't Delete a made payment ");
             alert.showAndWait();
         }
         else{
             //int Tnumber = Integer.parseInt(TphoneNoTxt.getText());
        String sql ="DELETE FROM telephone WHERE BillNo = ?";
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            
            pst.setInt(1, Integer.parseInt(TbillNoTxt.getText()));
            
            int x = pst.executeUpdate();
            
            if(x==1)
            {
                System.out.println("Deleted Successfully");
                 loadDataTELEromDatabase();
                   getTeleTotal();
            }
        } catch (Exception e) {
        }
     
         }
       
    }

   //Telephone Table load
    
         private void setTelephoneCellTable() {
       TBillNOcolumn_.setCellValueFactory(new PropertyValueFactory<>("billNo"));
       TPhoneNumbercolumn.setCellValueFactory(new PropertyValueFactory<>("TpNo"));
       TAmountcolumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
       TDatecolumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
       TPaycolumn.setCellValueFactory(new PropertyValueFactory<>("Pay"));
    }

    private void loadDataTELEromDatabase() {
        TP.clear();
       
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from telephone");
            rs =pst.executeQuery();
            while(rs.next()){
                TP.add(new TPlist(""+ rs.getInt(1), ""+ rs.getString(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getString(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

      TableTelephone.setItems(TP);
    }

    
    
    /*set values to text field Telephone table*/
        
    private void setCellValueTeleTableToTextField() {
        TableTelephone.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            TPlist f1 = TableTelephone.getItems().get(TableTelephone.getSelectionModel().getSelectedIndex());
            
            TbillNoTxt.setText(f1.getBillNo());
            TphoneNoTxt.setText(f1.getTpNo());
          TamountTxt.setText(f1.getAmount());
          gf=f1.getPay();
          tt=f1.getBillNo();
           String AD = f1.getDate();
             LocalDate F = LocalDate.parse(AD);
             TDate.setValue(F);
          //TDate.setDayCellFactory(f1.getDate());
        TPay.setDisable(false);    
        });
    
        
    }

    @FXML
    private void Tpay(ActionEvent event) {
            Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation Dialog");
            alert1.setHeaderText("Look, a Confirmation Dialog");
            alert1.setContentText("Are You Sure ?, Can't change or delete made payment!");

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK){
                 System.out.println(gf);
         if (gf.equalsIgnoreCase("Paid")){
             System.out.println("Aleady Paid");
             Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("Warning");
             alert.setHeaderText("WARNING");
             alert.setContentText("This Payment has aleady made");
             alert.showAndWait();
         }
         else{
                 if(TbillNoTxt.getText() == null || TbillNoTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TbillNoTxt.getText() )){
            System.out.println(" Bil Number must be a number! ");
            return;
                    }
         if(TphoneNoTxt.getText() == null || TphoneNoTxt.getText().trim().isEmpty() ||! validatePhone(TphoneNoTxt.getText())){
             System.out.println("Telephone Number is Incorrect ");
             return;
           }
          if(TamountTxt.getText() == null || TamountTxt.getText().trim().isEmpty() ||! validateNumbersOnly(TamountTxt.getText())){
              System.out.println(" Amount must be a number");
              return;
           }
            if(TDate.getValue().toString() == null || TDate.getValue().toString().trim().isEmpty() ){
              System.out.println(" Date picker can't be Empty");
              return;
           }
            
        int TbillNo = Integer.parseInt(TbillNoTxt.getText());
       String sq= "select * from telephone where BillNo = "+TbillNo+" LIKE 'paid'" ;
            try {
            pst = (PreparedStatement) con.prepareStatement(sq);
            
            
            
            int x = pst.executeUpdate();
            
            if(x ==1)
            {
                System.out.println("fffff");
        
            }
            else{
            
            }
            
                }
            catch (Exception e) {
                System.out.println(e);
        }
            String sql = "UPDATE telephone SET Pay = 'Paid' where BillNo = "+TbillNo+" ";
                   try {
        //int elecbillNo = Integer.parseInt(elecBillNoText.getText());
         
       
        
            pst = (PreparedStatement) con.prepareStatement(sql);
           
           
           // pst.setInt(5, elecbillNo);
           
             
            int z = pst.executeUpdate();
            System.out.println("ASs");

            if (z == 1) {
                System.out.println("Updated Successfully");
                loadDataFromDatabase();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
           System.out.println("gh");
    //payment
    
       
       double fcost = Double.parseDouble(TamountTxt.getText());
        
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        
        
        //Insert to payment Table
        String query = "INSERT INTO payment ( Date, Description, Amount ,  BillNo)"
                +"Values(?,'Telephone',"+fcost+","+TbillNo+")";
       
        

        try {
            pst = (PreparedStatement) con.prepareStatement(query);
             
            pst.setTimestamp(1, date);
            

            int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
                 setPaytableCellTable();
                 loadDataPayromDatabase();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
   loadDataTELEromDatabase();
    
            
            
String query = "select max(PaymentID) as PaymentID, Amount from Payment";
        int id = 0;
        double amnt = 0;
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("PaymentID");
                amnt = Double.parseDouble(TamountTxt.getText());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
        
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Asset");
            pst.setDate(3, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transaction");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }    
            }
else {
    // ... user chose CANCEL or closed the dialog
}
        
getTeleTotal();
   

    }     
    
    //payment Table load
    
    
         private void setPaytableCellTable() {
       PTable_column_Payment.setCellValueFactory(new PropertyValueFactory<>("PId"));
      PTable_column_Date.setCellValueFactory(new PropertyValueFactory<>("Pdate"));
       PTable_column_Catergory.setCellValueFactory(new PropertyValueFactory<>("Pdes"));
       PTable_column_Amount.setCellValueFactory(new PropertyValueFactory<>("Pamount"));
      PTable_column_TbillNo.setCellValueFactory(new PropertyValueFactory<>("TeleBIll"));
      PTable_column_EbillNo.setCellValueFactory(new PropertyValueFactory<>("ElecBill"));
      PTable_column_FNo.setCellValueFactory(new PropertyValueFactory<>("FvouNo"));
      PTable_column_ONo.setCellValueFactory(new PropertyValueFactory<>("OtherVNo"));
      PTable_column_AccNo.setCellValueFactory(new PropertyValueFactory<>("AccNo"));
      
   
    }

    private void loadDataPayromDatabase() {
        PL.clear();
       
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from payment");
            rs =pst.executeQuery();
            while(rs.next()){
                PL.add(new PaymentList(""+ rs.getInt(1), ""+ rs.getDate(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getInt(5), ""+ rs.getInt(6), ""+ rs.getInt(7), ""+ rs.getInt(8), ""+ rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

      TablePayment.setItems(PL);
    }

    
   //get total in non paid telephone bills 
public void getEtotal(){
  double gt =0;
    double total=0;
    
    
    String q ="SELECT Amount from electricity where Pay is null ";
        System.out.println("fgh");
   
        try {
            pst =(PreparedStatement) con.prepareStatement(q);
           
           rs =pst.executeQuery();
           while(rs.next())
           {
              
               gt = rs.getDouble("Amount");
               total=total+gt;
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       System.out.println("Total is:");
       System.out.println(total);
       ElecTAmountLable.setText(String.valueOf(total));
    
}



//Tab OtherEX 

    @FXML
    private void OSearchDetails() {
        OSearchDetailstxt.setOnKeyReleased(e->{
            String sql="select * from otherexpenses where VoucherNo= ?";
            
            if(OSearchDetailstxt.getText().equals(""))
            {
                loadDataOtherTfromDatabase();
            
            }else
            {
                OL.clear();
            try {
                int i=Integer.parseInt(OSearchDetailstxt.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      OL.add(new OtherList(""+ rs.getInt(1), ""+ rs.getDouble(2), ""+ rs.getDouble(3), ""+ rs.getDouble(4), ""+ rs.getDate(5)));
                
                }
                
                OtherTable.setItems(OL);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }
int ovoucherNo;
               double oGF;
               double or;
                double sl;
    @FXML
    private void OAdd(ActionEvent event) {
        
         if (OVNuber.getText() == null || OVNuber.getText().trim().isEmpty() ||! validateNumbersOnly(OVNuber.getText())){
        
            System.out.println("voucher Number Can't be empty And voucher Number must be a number");
            return;
            }    
          
        
           Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation Dialog");
            alert1.setHeaderText("Look, a Confirmation Dialog");
            alert1.setContentText("Are You Sure ?, Can't change or delete made payment!");
             
            

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK)
            {      
                System.out.println(OVNuber.getText());
                 ovoucherNo = Integer.parseInt(OVNuber.getText());
                 //GFCost
                 if(OGFCost.getText().equalsIgnoreCase(""))
                 {
                     OGFCost.setText("0");
                 }
                 else{
                         oGF =Double.parseDouble( OGFCost.getText());
                 }
                 //ORAPayment
                if(ORAPayment.getText().equalsIgnoreCase(""))
                 {
                     ORAPayment.setText("0");
                 }
                 else{
                         or = Double.parseDouble(ORAPayment.getText());
                 }
                //SLoan
                if(SLoan.getText().equalsIgnoreCase(""))
                 {
                     SLoan.setText("0");
                 }
                 else{
                  sl = Double.parseDouble(SLoan.getText());
                 }
                 
                
                String query = "insert into otherexpenses (VoucherNo, Fuel, OverateAdditionalPayment, SupplierLoan,Date)"
                + "Values(?, ?, ?, ?,?)";
              
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                System.out.println(date);
             try {
                    pst = (PreparedStatement) con.prepareStatement(query);
                    pst.setInt(1, ovoucherNo);
                    pst.setDouble(2, oGF);
                    pst.setDouble(3, or);
                    pst.setDouble(4, sl);
                    pst.setTimestamp(5, date);
                   
            

                    int x = pst.executeUpdate();
                    if (x == 1) {
                        System.out.println("add");
                    }
                 

                }
              catch (Exception c){
                  System.out.println(c);
                        System.out.println("can't insert same Voucher number");
                               }
                       
             
             
                double g =0;
                 double h=0;
                 double totOV=0;
                 double totSUP=0;
                 int OvoucherNo = Integer.parseInt(OVNuber.getText());
                 System.out.println(OvoucherNo);
                String que = "SELECT Fuel, OverateAdditionalPayment, SupplierLoan from otherexpenses where VoucherNo= "+OvoucherNo+" ";
        try {
            pst =(PreparedStatement) con.prepareStatement(que);
           // pst.setString(1, "amount");
           rs =pst.executeQuery();
           while(rs.next())
           {
                g = rs.getDouble("Fuel");
               System.out.println(g);
                 totOV= rs.getDouble( "OverateAdditionalPayment");
                 totSUP=rs.getDouble("SupplierLoan");
                h=h+g+totOV+totSUP;
           }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println("this is  a hh"+h);
                 
                //double fcost1 = Double.parseDouble(fireCostTxt.getText());
                java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
        
            String quer= "INSERT INTO payment ( Date, Description, Amount ,  OVoucherNo)"
                +"Values(?,'Other',"+h+","+ovoucherNo+")";
              
        //int tamount = Integer.parseInt(TamountTxt.getText());
        

        try {
            pst = (PreparedStatement) con.prepareStatement(quer);
             
            pst.setTimestamp(1, date1);
            

            int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
                 //setPaytableCellTable();
                    loadDataPayromDatabase();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    //Transaction Table
    query = "select max(PaymentID) as PaymentID, Amount from Payment";
        int id = 0;
        double amnt = 0;
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("PaymentID");
                amnt = h;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
        
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Asset");
            pst.setDate(3, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transaction");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
            else{
           }
            loadDataOtherTfromDatabase();
    }


    private void setOthertableCellTable() {
       OVNoClmn.setCellValueFactory(new PropertyValueFactory<>("Voucher"));
      ODateClmn.setCellValueFactory(new PropertyValueFactory<>("Date"));
     GFCClmn.setCellValueFactory(new PropertyValueFactory<>("fuel"));
       ORAPaymentClmn.setCellValueFactory(new PropertyValueFactory<>("OApayment"));
     SloanClmn.setCellValueFactory(new PropertyValueFactory<>("Sloan"));
     
      
   
    }

    private void loadDataOtherTfromDatabase() {
        OL.clear();
       
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from otherexpenses");
            rs =pst.executeQuery();
            while(rs.next()){
                OL.add(new OtherList(""+ rs.getInt(1), ""+ rs.getDouble(2), ""+ rs.getDouble(3), ""+ rs.getDouble(4), ""+ rs.getDate(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

      OtherTable.setItems(OL);
    }

    
    //Tab BankEX
    
    @FXML
    private void Bpay(ActionEvent event) {
        
         if (BaccountTXT.getText() == null || BaccountTXT.getText().trim().isEmpty() ||! validateNumbersOnly(BaccountTXT.getText())){
        
            System.out.println("voucher Number Can't be empty And voucher Number must be a number");
            return;
            }    
         if (BCostTxt.getText() == null || BCostTxt.getText().trim().isEmpty() ||! validateNumbersOnly(BCostTxt.getText())){
        
            System.out.println(" Can't be empty And Cost  must be a number");
            return;
            } 
        
         Alert alert1 = new Alert(AlertType.CONFIRMATION);
            alert1.setTitle("Confirmation Dialog");
            alert1.setHeaderText("Look, a Confirmation Dialog");
            alert1.setContentText("Do you want to made this payment?, Can't change or delete made payment!");
             
            

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK)
        {
            
         
         String query = "insert into bankexpenses (AccountNo, BankName, Description, Cost,Date)"
                + "Values(?, ?, ?, ?, ?)";
         int Acc = Integer.parseInt(BaccountTXT.getText());
         String Bname = BnameTXT.getText();
         String Des = BdescreptionTXT.getText();
         Double Bcost= Double.parseDouble(BCostTxt.getText());
         java.sql.Timestamp date1 = new java.sql.Timestamp(new java.util.Date().getTime());
         
         try{
             pst =(PreparedStatement) con.prepareStatement(query);
             
             pst.setInt(1, Acc);
             pst.setString(2,Bname);
             pst.setString(3, Des);
             pst.setDouble(4,Bcost);
             pst.setTimestamp(5, date1);
             
             int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
                 //setPaytableCellTable();
                  //  loadDataPayromDatabase();
            }
        
       
         
         }
        
         catch(Exception e){
             System.out.println(e);
         
         }
          String quer= "INSERT INTO payment ( Date, Description, Amount ,  AccountNo)"
                +"Values(?,'BankExpenses',"+Bcost+","+Acc+")";
              
        //int tamount = Integer.parseInt(TamountTxt.getText());
        

        try {
            pst = (PreparedStatement) con.prepareStatement(quer);
             
            pst.setTimestamp(1, date1);
            

            int y = pst.executeUpdate();
            if (y == 1) {
                System.out.println("add");
                 //setPaytableCellTable();
                    loadDataPayromDatabase();
            }

        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
//Insert to the Transaction  

  query = "select max(PaymentID) as PaymentID, Amount from Payment";
        int id = 0;
        double amnt = 0;     
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("PaymentID");
                amnt = Bcost;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
        
        try {
            pst = (PreparedStatement) con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "Asset");
            pst.setDate(3, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transaction");
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
        loadDataBankTfromDatabase();
        
        
        
        }
            else{}
    }
    
     private void setBanktableCellTable() {
       BAcoountClmn.setCellValueFactory(new PropertyValueFactory<>("Acc"));
      BNameClmn.setCellValueFactory(new PropertyValueFactory<>("BName"));
     BDescriptionClmn.setCellValueFactory(new PropertyValueFactory<>("Descript"));
       BCostClmn.setCellValueFactory(new PropertyValueFactory<>("cost"));
     BDateClmn.setCellValueFactory(new PropertyValueFactory<>("date"));
 
    }

    private void loadDataBankTfromDatabase() {
        BL.clear();
       
        try {
            pst = (PreparedStatement) con.prepareStatement("Select * from bankexpenses");
            rs =pst.executeQuery();
            while(rs.next()){
                BL.add(new BankList(""+ rs.getInt(1),""+ rs.getInt(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getDouble(5), ""+ rs.getDate(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
           

      BankTable.setItems(BL);
    }
      
    public void getTeleTotal(){
    double t =0;
    double total2=0;
    
    
    String q ="SELECT Amount from telephone where Pay is null ";
        System.out.println("fgh");
   
        try {
            pst =(PreparedStatement) con.prepareStatement(q);
           
           rs =pst.executeQuery();
           while(rs.next())
           {
              
               t = rs.getDouble("Amount");
               total2=total2+t;
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
       System.out.println("Total is:");
       System.out.println(total2);
      TeleLable.setText(String.valueOf(total2));
    
    
    }

    @FXML
    private void BSearchDetails() {
       BSearchDetailstxt11.setOnKeyReleased(e->{
            String sql="select * from bankexpenses where AccountNo= ?";
            
            if(BSearchDetailstxt11.getText().equals(""))
            {
                loadDataBankTfromDatabase();
            
            }else
            {
                BL.clear();
            try {
                int i=Integer.parseInt(BSearchDetailstxt11.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                       BL.add(new BankList(""+ rs.getInt(1),""+ rs.getInt(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getDouble(5), ""+ rs.getDate(6)));
                
                }
                
                BankTable.setItems(BL);
                
            } catch (SQLException ex) {
            }
            
            }
        });
        
    }

    @FXML
    private void generateReport(ActionEvent event) {
        try{
            String is = "C:\\Users\\Isuru Hettiarachchi\\Documents\\NetBeansProjects\\Thisara Revised\\src\\thisarateafactory\\reports\\asset.jrxml";
            JasperReport jr =JasperCompileManager.compileReport(is);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,con);
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

   
    @FXML
    private void PSearch(ActionEvent event) {
        Psearchtxt.setOnKeyReleased(e->{
            String sql="select * from payment where Date = ?";
            
            if(Psearchtxt.getText().equals(""))
            {
                loadDataBankTfromDatabase();
            
            }else
            {
                PL.clear();
            try {
                int i=Integer.parseInt(Psearchtxt.getText());
                pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                       PL.add(new PaymentList(""+ rs.getInt(1), ""+ rs.getDate(2), ""+ rs.getString(3), ""+ rs.getString(4), ""+ rs.getInt(5), ""+ rs.getInt(6), ""+ rs.getInt(7), ""+ rs.getInt(8), ""+ rs.getInt(9)));
                
                }
                
               TablePayment.setItems(PL);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }

    
   
}


    


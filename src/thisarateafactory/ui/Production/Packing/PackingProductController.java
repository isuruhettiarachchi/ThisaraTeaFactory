/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Packing;

import thisarateafactory.ui.Production.AlertBox.AlertDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import thisarateafactory.ui.Production.thisara.FXMLDocumentController;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Nipuni
 */
public class PackingProductController implements Initializable {
    
    //create connection class object.
    Connection conn = null;
    
    //create preparedStatement class object.
    PreparedStatement pst = null;
    
    //create resultSet class object.
    ResultSet rs = null;
    
    private ObservableList<PackingList> data;
    
    ObservableList<String> list2 = FXCollections.observableArrayList("Thisara A","Thisara");//set items to combo box
    ObservableList<String> list3 = FXCollections.observableArrayList("BOPA","FBOP","FBOP 1","OPNO1","OPA","BOPA","BOPF","OP");//set items to combo box
    ObservableList<String> list4 = FXCollections.observableArrayList("Full","Half");//set items to combo box
   
    //create object from Date class.
    Date currentDate = new Date();
    
    @FXML
    private JFXTextField invTxt;
    @FXML
    private JFXTextField datePack;
    @FXML
    private JFXComboBox<String> Qua;
    @FXML
    private JFXComboBox<String> Grade;
    @FXML
    private JFXTextField TxtchestNo;
    @FXML
    private JFXTextField TxtNoCon;
    @FXML
    private JFXTextField NoWeight;
    @FXML
    private JFXButton btnNetTot;
    @FXML
    private Label netTotQua;
    @FXML
    private JFXTextField TxtSample;
    @FXML
    private JFXButton btntotGross;
    @FXML
    private Label TotGforEach;
    @FXML
    private TableView<PackingList> TablePacking;
    @FXML
    private TableColumn<?, ?> colInvoNo;
    @FXML
    private TableColumn<?, ?> colGrade;
    @FXML
    private TableColumn<?, ?> colQua;
    @FXML
    private TableColumn<?, ?> colcontainersAmount;
    @FXML
    private TableColumn<?, ?> colFH;
    @FXML
    private TableColumn<?, ?> colNetw;
    @FXML
    private TableColumn<?, ?> colnetToEach;
    @FXML
    private TableColumn<?, ?> totG;
    @FXML
    private TableColumn<?, ?> colContainerNo;
    @FXML
    private TableColumn<?, ?> colDateOfPacked;
    @FXML
    private TableColumn<?, ?> colSampleAllowed;
    @FXML
    private JFXButton Addbtn;
    @FXML
    private JFXButton Upbtn;
    @FXML
    private JFXButton delBtn;
    @FXML
    private JFXComboBox<String> CmbFH;
    @FXML
    private JFXTextField Type;
    @FXML
    private TableColumn<?, ?> colTypePacking;
    @FXML
    private JFXButton btnInvoice;
    @FXML
    private JFXButton cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       //Set items to the combo box quality
       Qua.setItems(list2);
       
        //Set items to the combo box Grade
       Grade.setItems(list3);
       
       //Set items to the combo box Grade
       CmbFH.setItems(list4);
       
       conn = databaseHandler.getConnection();
       data = FXCollections.observableArrayList();
       
       //setCellTable method calling.
        setCellTable();
        
        //loadDataFromDatabase method calling.
        loadDataFromDatabase();
        
        //setCellValueFromTableToTextField method calling.
        setCellValueFromTableToTextField();
      
        //emptyValidation method calling.
        emptyValidation(Type,"No Input Given..!");
        emptyValidation(TxtchestNo,"No Input Given..!");
        emptyValidation(TxtNoCon,"No Input Given..!");
        emptyValidation(NoWeight,"No Input Given..!");
        emptyValidation(TxtSample,"No Input Given..!");
        
        //numberValidation method calling.
        numberValidation(TxtchestNo,"Please enter numbers only..!");
        numberValidation(TxtNoCon,"Please enter numbers only..!");
        
        
        //doubleValidation method calling.
        doubleValidation(NoWeight,"Please enter net weight each..!");
        doubleValidation(NoWeight,"Please enter valid weight only..!");
        doubleValidation(TxtSample,"Please enter valid sample weight only..!");
       
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

    @FXML
   
    //Method Implementation for CalculateNetTotalQuantity  
    public double CalculateNetTotalQuantity() 
    {
        int GrossAmt = Integer.parseInt(TxtNoCon.getText());
        double netWeightEach = Double.parseDouble(NoWeight.getText());
        double TotalAmount = GrossAmt * netWeightEach ;
        netTotQua.setText(String.valueOf(TotalAmount));
        
        return TotalAmount; 
        
        
    }

     //Method Implementation for CalculateGrossWeightforEach
   @FXML
    public void CalculateGrossWeightforEach(ActionEvent event) throws SQLException 
    {
        double netTot = CalculateNetTotalQuantity();
        double samplesAllowed = Double.parseDouble(TxtSample.getText());
                
        double TotAmount = netTot + samplesAllowed ;
        
       // if(TotAmount<qualityWeight())
        //{
        TotGforEach.setText(String.valueOf(TotAmount));
    //}
        //else
        //AlertDialog.display("info", "Gross Weight must be lower than total weight");             
    }

    /* public Double qualityWeight() throws SQLException
    {
        double PNO = 0 ;
        pst = conn.prepareStatement("SELECT q.NetWeight from quality q, packingdetails p where q.Quality = p.Quality and q.Quality = ? and p.DateOfPacking = ?");
        pst.setString(1, Qua.getValue());
        pst.setDate(2,java.sql.Date.valueOf(LocalDate.now()));
    
        rs = pst.executeQuery();
        rs.last();
        PNO = rs.getDouble(1);
        
        return PNO;
         
        
    }*/
    
     //Method Implementation for addDailyProduct  
    @FXML
    public void AddRecord(ActionEvent event) {
              
        String query = "INSERT INTO packingdetails(InvoiceNo, Grade, Quality, NoOfContainers, FullHalf, NetTotalQty, NetWeightEach, TotalGrossWeight, ContainerNo, TypeOfPacking,DateOfPacking,SampleAllowed)"
                + "VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_date, ?)";

        try {
           
            pst = conn.prepareStatement(query);
                      
            pst.setString(1,  Grade.getValue());
            pst.setString(2, Qua.getValue());
            pst.setInt(3,Integer.parseInt(TxtNoCon.getText()));
            pst.setString(4, CmbFH.getValue());
            
          
            
           
            pst.setDouble(5,Double.parseDouble(netTotQua.getText())); 
           
            pst.setDouble(6,Double.parseDouble(NoWeight.getText())); 
            
            pst.setDouble(7,Double.parseDouble(TotGforEach.getText())); 
         
            pst.setInt(8,Integer.parseInt(TxtchestNo.getText()));
            pst.setString(9, Type.getText()); 
           
             pst.setDouble(10,Double.parseDouble(TxtSample.getText()));
            
            int x = pst.executeUpdate();
            
            if(x==1)
            {  
                clearTextField();
                AlertDialog.display("info", "Data Insert Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            
            }   
        }
        catch (RuntimeException ex1) {
            
            AlertDialog.display("info", "Please Fill All Fields..!");
           // Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex1);
            
        }
        catch (SQLException ex) {
           
            Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
        
        
    }
    
   //Method Implementation for UpdateRecord
   @FXML
    private void UpdateRecord(ActionEvent event) {
            
        String sql = "Update packingdetails set NoOfContainers = ?, NetTotalQty = ?, NetWeightEach = ?, TotalGrossWeight = ?, ContainerNo = ?, TypeOfPacking = ?,SampleAllowed = ? where InvoiceNo = ? ";
         
        try {
            
            pst = conn.prepareStatement(sql);
            
           pst.setInt(1,Integer.parseInt(TxtNoCon.getText()));
            pst.setDouble(2,Double.parseDouble(netTotQua.getText())); 
           
            pst.setDouble(3,Double.parseDouble(NoWeight.getText())); 
            
            pst.setDouble(4,Double.parseDouble(TotGforEach.getText())); 
         
            pst.setInt(5,Integer.parseInt(TxtchestNo.getText()));
            pst.setString(6, Type.getText()); 
           
             pst.setDouble(7,Double.parseDouble(TxtSample.getText())); 
            pst.setInt(8, Integer.valueOf(invTxt.getText()));
                        
               
            int x = pst.executeUpdate();
              
            if(x==1)
            {   
                clearTextField();
                AlertDialog.display("info", "Data Updated Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            }   
            
        }
        catch (RuntimeException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            System.out.println(ex);
            System.out.println("faile!!!!!");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            
            Logger.getLogger(PackingProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
        
    }
    
 //Method Implementation for DeleteRecord
   @FXML
    private void DeleteRecord(ActionEvent event) {
        String sql = "DELETE from packingdetails where InvoiceNo = ?";
        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(invTxt.getText()));
            
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
         colInvoNo.setCellValueFactory(new PropertyValueFactory<>("InvoiceNo"));
         colGrade.setCellValueFactory(new PropertyValueFactory<>("Grade"));
         colQua.setCellValueFactory(new PropertyValueFactory<>("Quality"));
         colcontainersAmount.setCellValueFactory(new PropertyValueFactory<>("NoOfContainers"));
         colFH.setCellValueFactory(new PropertyValueFactory<>("FullHalf"));
         colNetw.setCellValueFactory(new PropertyValueFactory<>("NetTotalQty"));
         colnetToEach.setCellValueFactory(new PropertyValueFactory<>("NetWeightEach"));
         totG.setCellValueFactory(new PropertyValueFactory<>("TotalGrossWeight"));
         colContainerNo.setCellValueFactory(new PropertyValueFactory<>("ContainerNo"));
         colTypePacking.setCellValueFactory(new PropertyValueFactory<>("TypeOfPacking"));
         colDateOfPacked.setCellValueFactory(new PropertyValueFactory<>("DateOfPacking"));
         colSampleAllowed.setCellValueFactory(new PropertyValueFactory<>("SampleAllowed"));

    }
    
    // method implementation for load data from database and set them into the interface table
   private void loadDataFromDatabase()
    {
        data.clear();
        try {
            pst = conn.prepareStatement("Select InvoiceNo,Grade,Quality,NoOfContainers,FullHalf,NetTotalQty,NetWeightEach,TotalGrossWeight,ContainerNo,TypeOfPacking,DateOfPacking,SampleAllowed from packingdetails ");
          
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new PackingList(""+rs.getInt(1), rs.getString(2), rs.getString(3), ""+rs.getInt(4), rs.getString(5), ""+rs.getDouble(6), ""+rs.getDouble(7), ""+rs.getDouble(8), ""+rs.getInt(9), rs.getString(10),rs.getString(11),""+rs.getDouble(12)));
           
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TablePacking.setItems(data);
    }
   
     //method implementation for setCellValueFromTableToTextField 
    private void setCellValueFromTableToTextField()
    {
        TablePacking.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        PackingList d1 = TablePacking.getItems().get(TablePacking.getSelectionModel().getSelectedIndex());
        
        invTxt.setText(d1.getInvoiceNo());
        datePack.setText(d1.getDateOfPacking());
        String h = d1.getQuality();
        Qua.setValue(h);
        
        String v = d1.getGrade();
        Grade.setValue(v);
        
        String n = d1.getFullHalf();
        CmbFH.setValue(n);
        
        Type.setText(d1.getTypeOfPacking());
        TxtchestNo.setText(d1.getContainerNo());
        TxtNoCon.setText(d1.getNoOfContainers());
        NoWeight.setText(d1.getNetWeightEach());
        netTotQua.setText(d1.getNetTotalQty());
        TxtSample.setText(d1.getSampleAllowed());
        TotGforEach.setText(d1.getTotalGrossWeight());
       
        });
        
    }
    
    //method implementation for AutoIncrementInvoice 
   @FXML
    private void AutoIncrementInvoice(ActionEvent event) throws SQLException {
        
        invTxt.setText(String.valueOf(generateInvoiceNo()));
        datePack.setText(new Date().toString());
        Qua.setValue("");
        Grade.setValue("");
        CmbFH.setValue("");
        Type.setText(null);
        TxtchestNo.setText(null);
        TxtNoCon.setText(null);
        NoWeight.setText(null);
        netTotQua.setText(null);
        TxtSample.setText(null);
        TotGforEach.setText(null);        
       
        
    }
    private Integer generateInvoiceNo() throws SQLException
    {
        int InvoiceNo = 1;
        String query = "SELECT max(InvoiceNo) FROM packingdetails";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()){
            InvoiceNo = rs.getInt(1) + 1;
        }
        return InvoiceNo;
    }
    
    //method implementation for clearTextField
   private void clearTextField()
    {
        invTxt.clear();
        datePack.clear();;
        Qua.setValue("");
        Grade.setValue("");
        CmbFH.setValue("");
        Type.setText(null);
        TxtchestNo.setText(null);
        TxtNoCon.setText(null);
        NoWeight.setText(null);
        netTotQua.setText(null);
        TxtSample.setText(null);
        TotGforEach.setText(null);
       
    }
   
//method implementation for CancelRecord
   @FXML
    private void CancelRecord(ActionEvent event)
    {
   
     ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
     
    }  

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/packaging.jrxml").getAbsolutePath();
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

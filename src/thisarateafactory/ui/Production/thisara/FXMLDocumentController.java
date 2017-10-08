/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.thisara;

import thisarateafactory.ui.Production.AlertBox.AlertDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
//import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
/**

 * @author Nipuni
 */
public class FXMLDocumentController implements Initializable {
    //create connection class object.
    Connection conn = null;
    
    //create preparedStatement class object.
    PreparedStatement pst = null;
    
    //create resultSet class object.
    ResultSet rs = null;
    
    private ObservableList<DailyProductList> data;
  
    
    
    //create object from Date class.
    Date currentDate = new Date();
   
    @FXML
    private JFXTextField pidText;
    @FXML
    private JFXTextField startDate;
    @FXML
    private JFXTextField dh1Text;
    @FXML
    private JFXTextField dh2Text;
    @FXML
    private JFXTextField dh3Text;
    @FXML
    private JFXTextField dh4Text;
    @FXML
    private JFXTextField firedTeaText;
    @FXML
    private JFXButton calcRefusedTeaButton;
    @FXML
    private JFXButton calcMadeTeaButton;
    @FXML
    private JFXButton calcOutTurnButton;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton updateBtn;
    @FXML
    private TableView<DailyProductList> TableDailyProduction;
    @FXML
    private TableColumn<?, ?> ColPID;
    @FXML
    private TableColumn<?, ?> Date;
    @FXML
    private TableColumn<?, ?> ColGWeight;
    @FXML
    private TableColumn<?, ?> MWeight;
    @FXML
    private TableColumn<?, ?> OutTurn;
    @FXML
    private TableColumn<?, ?> FiredTea;
    @FXML
    private TableColumn<?, ?> colRefusedTea;
    @FXML
    private TableColumn<?, ?> colDh1;
    @FXML
    private TableColumn<?, ?> colDh2;
    @FXML
    private TableColumn<?, ?> colDh3;
    @FXML
    private TableColumn<?, ?> colDh;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton TotFirdbtn;
    @FXML
    private JFXTextField txtRefuceWhtToday;
    @FXML
    private JFXTextField txtMadeWhtToday;
    @FXML
    private JFXTextField txtOutTurnToday;
    @FXML
    private JFXTextField txtSupWht;
    @FXML
    private JFXButton btnNewP;
    @FXML
    private JFXButton cancelButton;
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        conn = databaseHandler.getConnection();
        data = FXCollections.observableArrayList();
        
        //setCellTable method calling.
        setCellTable();
        
        //loadDataFromDatabase method calling.
        loadDataFromDatabase();
        
        //setCellValueFromTableToTextField method calling.
        setCellValueFromTableToTextField();
        
        //emptyValidation method calling.
        emptyValidation(dh1Text,"No Input Given");
        emptyValidation(dh2Text,"No Input Given");
        emptyValidation(dh3Text,"No Input Given");
        emptyValidation(dh4Text,"No Input Given");

        //doubleValidation method calling.
        doubleValidation(dh1Text,"Please enter weight for Dhool 1");
        doubleValidation(dh2Text,"Please enter weight for Dhool 2");
        doubleValidation(dh3Text,"Please enter weight for Dhool 3");
        doubleValidation(dh4Text,"Please enter weight for Dhool 4");

        
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

    //Method Implementation for addDailyProduct  
    @FXML
    private void addDailyProduct(ActionEvent event) throws SQLException {
        
       
        if(CountDate() < 1){
       
        String query = "INSERT INTO dailyproduct(ProductID, Date, GreenLeavesWeight, MadeTeaWeight, OutTurn, FiredTea, RefusedTea, Dhool1, Dhool2, Dhool3, Dhool4)"
                + "VALUES(NULL, current_date, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            
            pst = conn.prepareStatement(query);
           
            pst.setString(1, txtSupWht.getText());
             pst.setDouble(2,Double.parseDouble(txtMadeWhtToday.getText()));
            pst.setDouble(3, Double.parseDouble(txtOutTurnToday.getText()));
            pst.setDouble(4, Double.parseDouble(firedTeaText.getText()));
            pst.setDouble(5, Double.parseDouble(txtRefuceWhtToday.getText()));
            pst.setDouble(6, Double.parseDouble(dh1Text.getText()));
            pst.setDouble(7, Double.parseDouble(dh2Text.getText()));
            pst.setDouble(8, Double.parseDouble(dh3Text.getText()));
            pst.setDouble(9, Double.parseDouble(dh4Text.getText()));
            
           
          
            
            int x = pst.executeUpdate();
             
            if(x==1)
               
            clearTextField();
            AlertDialog.display("info", "Data Insert Successfully..!");
            setCellTable();
            loadDataFromDatabase();
            
                
            } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("failed");
            
        }catch (RuntimeException ex1) {
            AlertDialog.display("info", "Please Fill All Fields..!");
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex1);
        }
        
        }
         else 
         {
            clearTextField();
            AlertDialog.display("info", "You have already entered Today's Production Details..!");
         }
       
        
    }
    
    //Method Implementation for updateDalyProduct
    @FXML
    private void updateDalyProduct(ActionEvent event) {
      
        String sql = "Update dailyproduct set MadeTeaWeight = ?, OutTurn = ?, FiredTea = ?,RefusedTea = ?, Dhool1 = ?, Dhool2 = ?, Dhool3 = ?, Dhool4 = ? "+" where ProductID = ? ";
        
        try {
            
            pst = conn.prepareStatement(sql);
          
            pst.setDouble(1,Double.parseDouble(txtMadeWhtToday.getText()));
            pst.setDouble(2, Double.parseDouble(txtOutTurnToday.getText()));
            pst.setDouble(3, Double.parseDouble(firedTeaText.getText()));
            pst.setDouble(4, Double.parseDouble(txtRefuceWhtToday.getText()));
            pst.setDouble(5, Double.parseDouble(dh1Text.getText()));
            pst.setDouble(6, Double.parseDouble(dh2Text.getText()));
            pst.setDouble(7, Double.parseDouble(dh3Text.getText()));
            pst.setDouble(8, Double.parseDouble(dh4Text.getText()));
            
            pst.setInt(9, Integer.valueOf(pidText.getText()));
          
          int x = pst.executeUpdate();
          
          if(x==1)
          {
                clearTextField();
                AlertDialog.display("info", "Data Updated Successfully..!");
                setCellTable();
                loadDataFromDatabase();
            
          }     
          
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }catch (RuntimeException ex) {
            AlertDialog.display("Info", "Please Enter Values");
            System.out.println(ex);
            System.out.println("faile!!!!!");
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    //Method Implemntation for setCellTable
    private void setCellTable()
    {
        //set database column values into the interface table columns
         ColPID.setCellValueFactory(new PropertyValueFactory<>("proID"));
         Date.setCellValueFactory(new PropertyValueFactory<>("date"));
         ColGWeight.setCellValueFactory(new PropertyValueFactory<>("GreenLeavesWeightToday"));
         MWeight.setCellValueFactory(new PropertyValueFactory<>("MadeTeaToday"));
         OutTurn.setCellValueFactory(new PropertyValueFactory<>("OutTurnToday"));
         FiredTea.setCellValueFactory(new PropertyValueFactory<>("FiredTeaWeightToday"));
         colRefusedTea.setCellValueFactory(new PropertyValueFactory<>("RefTeaToday"));
         colDh1.setCellValueFactory(new PropertyValueFactory<>("dhool1"));
         colDh2.setCellValueFactory(new PropertyValueFactory<>("dhool2"));
         colDh3.setCellValueFactory(new PropertyValueFactory<>("dhool3"));
         colDh.setCellValueFactory(new PropertyValueFactory<>("dhool4"));

    }
    
    // method implementation for load data from database and set them into the interface table
    private void loadDataFromDatabase()
    {
        data.clear();
        try {
            pst = conn.prepareStatement("Select * from dailyproduct");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new DailyProductList(""+rs.getInt(1), rs.getString(2), ""+rs.getDouble(3), ""+rs.getDouble(4), ""+rs.getDouble(5), ""+rs.getDouble(6), ""+rs.getDouble(7), ""+rs.getDouble(8), ""+rs.getDouble(9), ""+rs.getDouble(10), ""+rs.getDouble(11)));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TableDailyProduction.setItems(data);
    }
    
    //method implementation for setCellValueFromTableToTextField 
    private void setCellValueFromTableToTextField()
    {
        TableDailyProduction.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        DailyProductList d1 = TableDailyProduction.getItems().get(TableDailyProduction.getSelectionModel().getSelectedIndex());
        
        pidText.setText(d1.getProID());
        startDate.setText(d1.getDate());
        txtSupWht.setText(d1.getGreenLeavesWeightToday());
        dh1Text.setText(d1.getDhool1());
        dh2Text.setText(d1.getDhool2());
        dh3Text.setText(d1.getDhool3());
        dh4Text.setText(d1.getDhool4());
        firedTeaText.setText(d1.getFiredTeaWeightToday());
        
        });
        
    }
    
    //method implementation for clearTextField
    private void clearTextField()
    {
        startDate.clear();
        pidText.clear();
        dh1Text.clear();
        dh2Text.clear();
        dh3Text.clear();
        dh4Text.clear();
        firedTeaText.clear();
        txtSupWht.clear();
        txtMadeWhtToday.clear();
        txtOutTurnToday.clear();
        txtRefuceWhtToday.clear();
     
    }

    
    //method implementation for deleteDailyRecord 
    @FXML
    private void deleteDailyRecord(ActionEvent event) {
        
      String sql = "DELETE from dailyproduct where ProductID = ?";
      
        try {
            pst= conn.prepareStatement(sql);
            pst.setInt(1,Integer.parseInt(pidText.getText()));
            
            int x = pst.executeUpdate();
          
          if(x==1)
          
               loadDataFromDatabase();
               clearTextField();
               AlertDialog.display("info", "Data Deleted Successfully..!");
               

        } catch (SQLException ex) {
           // Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            AlertDialog.display("info", "You can delete only product before seperate qualities ");
        }
        
        
        
    }

    //method implementation for CalculateTotalFiredTea 
    @FXML
    public double CalculateTotalFiredTea() {
        
        double dh1 = Double.parseDouble(dh1Text.getText());
        double dh2 = Double.parseDouble(dh2Text.getText());
        double dh3 = Double.parseDouble(dh3Text.getText());
        double dh4 = Double.parseDouble(dh4Text.getText());
        
        double TotFired = dh1 + dh2 + dh3 + dh4 ;
        if(Double.parseDouble(txtSupWht.getText()) >= TotFired)
        {
                firedTeaText.setText(String.valueOf(TotFired));
        
        return TotFired; 
        }
        else{
                AlertDialog.display("Info", "Invalied Entry");
                clearTextField();
                return 0;
        }
            
        
    }

    //method implementation for CalculateRefucedTea
    @FXML
    public double CalculateRefucedTea() {
        
        double fired = CalculateTotalFiredTea();
        double refTea = fired * 0.06;
        txtRefuceWhtToday.setText(String.valueOf(refTea));
        
        return refTea;
        
    }

    //method implementation for CalculateMadeTea 
    @FXML
    public double CalculateMadeTea() {
        
        double firedAmt = CalculateTotalFiredTea();
        double refuceAmt = CalculateRefucedTea();
        
        double made = firedAmt - refuceAmt; 
        txtMadeWhtToday.setText(String.valueOf(made));

        return made;
    }

    //method implementation for CalculateOutTurn 
    @FXML
    public void CalculateOutTurn(ActionEvent event) {
        
       
        double totMade = CalculateMadeTea();
        double greenLeaves = Double.parseDouble(txtSupWht.getText());
       
        double outTurn = (totMade/greenLeaves)*100;
        txtOutTurnToday.setText(String.valueOf(outTurn));

        
    }

    //method implementation for ProductAutoIncrement 
    @FXML
    private void ProductAutoIncrement(ActionEvent event) throws SQLException {
        
        startDate.setText(new Date().toString());
        txtSupWht.setText(null);
        dh1Text.setText(null);
        dh2Text.setText(null);
        dh3Text.setText(null);
        dh4Text.setText(null);
        firedTeaText.setText(null);
        txtRefuceWhtToday.setText(null);
        txtMadeWhtToday.setText(null);
        txtOutTurnToday.setText(null);       
        pidText.setText(String.valueOf(generateProductID()));
        
           
    }
    
    //method implementation for generateProductID 
    private Integer generateProductID() throws SQLException
    {
        int PID = 1;
        String query = "SELECT max(ProductID) FROM dailyproduct";
        rs = conn.createStatement().executeQuery(query);
        while(rs.next()){
            PID = rs.getInt(1) + 1;
        }
        return PID;
    }

    //method implementation for GetSuppliedWeight 
    @FXML
    private void GetSuppliedWeight(ActionEvent event) {
        
       String query ="SELECT sum(NetWeight) FROM collectionturn WHERE Date = ? ";
        
       try{
           LocalDate today = LocalDate.now();
           LocalDate yesterday = today.minus(Period.ofDays(1));
           
           pst = conn.prepareStatement(query);
           pst.setDate(1,java.sql.Date.valueOf(yesterday));
           
           rs = pst.executeQuery();
           rs.last();
           double net = rs.getDouble(1);
           String netTot = new Double(net).toString();
           txtSupWht.setText(netTot);
       
       } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    //method implementation for CountDate 
    public Integer CountDate() throws SQLException
    {
        int DNO = 0 ;
        pst = conn.prepareStatement("SELECT count(Date) from dailyproduct where Date = ? ");
        pst.setDate(1,java.sql.Date.valueOf(LocalDate.now()));;
            
        rs = pst.executeQuery();
        rs.last();
        DNO = rs.getInt(1);
        
        return DNO;
         
        
    }

     //method implementation for cancelDailyRecord
    @FXML
    private void cancelDailyRecord(ActionEvent event) 
    {
      
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/dailyProduction.jrxml").getAbsolutePath();
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


package thisarateafactory.ui.Finance.thisara;

import thisarateafactory.ui.Finance.DialogBox.AlertBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.ui.Finance.Validation.validateTextFields.validateCurrency;
import static thisarateafactory.ui.Finance.Validation.validateTextFields.validateNumbersOnly;
import thisarateafactory.ui.HRM.EmployeeManagementController;



/**
 *
 * @author Chinthaka
 */
public class FXMLDocumentController implements Initializable {

   
    Stage stage;
    
    private Connection con = null;
    private PreparedStatement pst = null;
    private PreparedStatement pst1 = null;
    private ResultSet rs = null;
    private ObservableList<FinanceList> data;//table load
            
            
    private Label label;
    @FXML
    private Label tid;
    @FXML
    private JFXComboBox<String> tType;
    
    ObservableList<String> list = FXCollections.observableArrayList("Employee","Supplier","Asset","Sales","Wastage","VehicleMaintenance","TransportTurn");//set items to combo box
    @FXML
    private DatePicker date;
    @FXML
    private JFXTextField amnt;
    @FXML
    private JFXButton sumbtn;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<FinanceList> tableFinance;
    @FXML
    private TableColumn<?, ?> columnId;
    @FXML
    private TableColumn<?, ?> columnType;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnAmount;
    @FXML
    private JFXTextField txt_Search;
    @FXML
    private JFXTextField tid1;
    @FXML
    private Label datevalidate;
    @FXML
    private AnchorPane financeMainPane;
    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tType.setItems(list);//comboBox
        data = FXCollections.observableArrayList();//get data to textfield
        setCellTable();
        con = databaseHandler.getConnection();//database
         
        loadDataFromDatabase();
        setCellValueFromTableToTextField();//mouseclick
        SearchDetails();//Search Details method calling
        getandinsert();
        deleteRows();
        
        financeMainPane.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) financeMainPane.getScene().getWindow();
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                try {
                                    System.out.println("exiting");
                                    Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/Main.fxml"));
                                    Stage stage = new Stage(StageStyle.DECORATED);
                                    stage.setResizable(false);
                                    stage.setTitle("Thisara Management System");
                                    
                                    stage.setScene(new Scene(parent));
                                    stage.show();   } catch (IOException ex) {
                                    Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        });
                    }
                });
            }
        });
       
        /*
       RequiredFieldValidator validator = new RequiredFieldValidator();
        NumberValidator num = new NumberValidator();
        
        amnt.getValidators().add(num);
        num.setMessage("This must be a Number");
        amnt.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue)
                {
                amnt.validate();
                }
            }
        });
        
      
        
        tid1.getValidators().add(validator);
        validator.setMessage("Text field is empty");
        tid1.focusedProperty().addListener(new ChangeListener<Boolean>(){ 

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                
                if(!newValue)
                {
                tid1.validate();
                }
            }

        });
        
        
        
        amnt.getValidators().add(validator);
        validator.setMessage("Text field is empty");
        amnt.focusedProperty().addListener(new ChangeListener<Boolean>(){ 

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                
                if(!newValue)
                {
                amnt.validate();
                }
            }
    
    
        });
        
        
       
        */
    } 
    /*table load*/
    
    private void setCellTable() //set table column ids
    {
        columnId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnAmount.setCellValueFactory(new PropertyValueFactory<>("amnt"));
    }
    private void loadDataFromDatabase()
    {
        data.clear();
        try {
            pst =con.prepareStatement("Select * from finance");
            rs =pst.executeQuery();
            
            
            while(rs.next()){
            
                data.add(new FinanceList( ""+rs.getInt(1),rs.getString(2), ""+rs.getString(3), ""+rs.getDouble(4)));
                
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableFinance.setItems(data);
    }
    /*set values to text field*/
    
    private void setCellValueFromTableToTextField() //mouse click 
    {
        tableFinance.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            FinanceList f1 = tableFinance.getItems().get(tableFinance.getSelectionModel().getSelectedIndex());
            
            tid.setText(f1.getId());
            tType.setValue(f1.getType());
            String d1 = f1.getDate();
            LocalDate ld = LocalDate.parse(d1);   
            date.setValue(ld);
            //date.setValue(LocalDate("2016-05-01"));
            amnt.setText(f1.getAmnt());
        });
    }

    @FXML
    private void GoSum(ActionEvent event) throws IOException {
           try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/thisarateafactory/ui/Finance/Summary/FXML.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
          }
        
    }

    @FXML
    private void AddDetails(ActionEvent event) {       
        
        
        //boolean isEmpty = Validation.TextFiledValdation.isTextFieldNotEmpty(amnt,err_amnt,"Field is Empty");
       //boolean isNum = Validation.TextFiledValdation.isTextFieldTypeNum(amnt,err_amnt,"Must be a number");
            if(amnt.getText()==null||amnt.getText().trim().isEmpty()||!validateCurrency(amnt.getText()))
            {
                errorDialog("Amount must be a number!!");
                return;
            }
            
            if(tid1.getText()==null||tid1.getText().trim().isEmpty()||!validateNumbersOnly(tid1.getText()))
            {
                errorDialog("Transaction Id Must be a Number");
                return;
            }
            
            if(date.getValue()== null )
            {
                
                 datevalidate.setText("Date is Empty");
            }else{
                int id = Integer.parseInt(tid1.getText());
                LocalDate ld = date.getValue();
                Date d1 = java.sql.Date.valueOf(ld);
                String type = tType.getValue();
                Double amount = Double.parseDouble(amnt.getText());
              
               
                String db_name = "testdb";
        
                String q = "INSERT INTO "+ db_name +".finance(TransID,Type,Date,Amount)"+
                    "VALUES(?,?,?,?)";
                
                String q1 = "INSERT INTO transactions(TransactionID,Type,Date,Amount)"+
                    "VALUES(?,?,?,?)";
                try {
                    pst=con.prepareStatement(q);
                    pst.setInt(1, id);
                    pst.setString(2, type);
                    pst.setDate(3,d1);
                    pst.setDouble(4, amount);
                    
                    pst1=con.prepareStatement(q1);
                    pst1.setInt(1, id);
                    pst1.setString(2, type);
                    pst1.setDate(3,d1);
                    pst1.setDouble(4, amount);
                    pst1.executeUpdate();
                    int x = pst.executeUpdate();
            
                    if(x==1)
                    {
                
                        loadDataFromDatabase();
                        AlertBox.display("info", "Successfully Added");
                
                    }
            
                }catch (Exception e) 
                {
                    
                }
            }
                            
        
    }

    @FXML
    private void UpdateDetails(ActionEvent event) {
        
         if(amnt.getText()==null||amnt.getText().trim().isEmpty()||!validateCurrency(amnt.getText()))
            {
                errorDialog("Amount must be a number!!");
                return;
            }
            
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Update");
        alert.setHeaderText(null);
        alert.setHeaderText("Do you want to Update?");
        Optional<ButtonType> action = alert.showAndWait();
            
        if(action.get()==ButtonType.OK)
        {
            String sql = "UPDATE finance SET Type = ?, Date = ?,Amount = ? WHERE TransID = ?";
            String sql1 = "UPDATE transactions SET Type = ?, Date = ?,Amount = ? WHERE TransactionID = ?";
            if(date.getValue()==null)
            {
            
                datevalidate.setText("Date is Empty");
                
            }else
            {
                try{
                    int Tid = Integer.parseInt(tid.getText());
                    String type = tType.getValue();
                    LocalDate ld =date.getValue();
                    Date d1 = java.sql.Date.valueOf(ld);
                    Double amount = Double.parseDouble(amnt.getText());
            
                    pst =con.prepareStatement(sql);
                    pst.setString(1, type);
                //pst.setString(2,((TextField)date.getEditor()).getText());
                    pst.setDate(2, d1);
                    pst.setDouble(3, amount);
                    pst.setInt(4, Tid);
                    int x = pst.executeUpdate();
                    
                    pst1 =con.prepareStatement(sql1);
                    pst1.setString(1, type);
                //pst.setString(2,((TextField)date.getEditor()).getText());
                    pst1.setDate(2, d1);
                    pst1.setDouble(3, amount);
                    pst1.setInt(4, Tid);
                    pst1.executeUpdate();
            
              
                    if(x==1)
                    {
               
                        loadDataFromDatabase();
                        AlertBox.display("info", "Successfully Updated");
                
                    }
            
                }catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
        }
        
    }
  

    @FXML
    private void DeleteDetails(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You sure to Delete?");
        Optional<ButtonType> action = alert.showAndWait();
        
        if(action.get()==ButtonType.OK)
        {
            String sql ="DELETE FROM finance WHERE TransID = ?";
            
            try {
                pst = con.prepareStatement(sql);         
                pst.setInt(1, Integer.parseInt(tid.getText()));
            
                int x = pst.executeUpdate();
                
                deleteRowsfromTransaction();
                if(x==1)
                {
                
                    loadDataFromDatabase();
                    AlertBox.display("info", "Successfully Deleted");
                  
                }
            }catch (Exception e)            
            {
            }
        
        }
        
    }

    @FXML
    private void SearchDetails() {
        
         
        txt_Search.setOnKeyReleased(e->{
            
            String sql = "select * from finance where TransID = ? ";
                    
                   
            if(txt_Search.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                data.clear();
            try {
                int i=Integer.parseInt(txt_Search.getText());
                String x = txt_Search.getText();
                pst = con.prepareStatement(sql);
                pst.setInt(1, i);
                //pst.setString(2, x);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                       data.add(new FinanceList( ""+rs.getInt(1),rs.getString(2), ""+rs.getString(3), ""+rs.getDouble(4)));
                
                }
                
                tableFinance.setItems(data);
                
            } catch (SQLException ex) {
            }
            
            }
        });
                
    }
    
    public void getandinsert()
    {
                
                String q ="INSERT INTO finance(TransID,Type,Date,Amount)"+
                            "SELECT f.TransactionID,f.Type,f.Date,f.Amount FROM transactions f "+
                                "LEFT JOIN finance t ON (t.TransID = f.TransactionID )"+
                            "WHERE t.TransID IS NULL";
                
                /*                    
                String q1 ="DELETE FROM finance" +
                            " WHERE TransID NOT IN (SELECT f.tid" +
                            " FROM finance_summary f)";
*/
      
        
                try {
                    pst=con.prepareStatement(q);
                    
                    
                    int x = pst.executeUpdate();
            
                if(x==1)
                {
                
                    loadDataFromDatabase();
                    AlertBox.display("info", "Successfully Added");
                
                }
            
                }catch (Exception e) 
                {
                    
                }
    
    }
    
    public void deleteRows()
    {
                            
                                    
                String q1 ="DELETE FROM finance" +
                            " WHERE TransID NOT IN (SELECT f.TransactionID" +
                            " FROM transactions f)";

      
        
                try {
                    
                    pst=con.prepareStatement(q1);
                    
                    int x = pst.executeUpdate();
            
                if(x==1)
                {
                
                    loadDataFromDatabase();
                    
                
                }
            
                }catch (Exception e) 
                {
                    
                }
    
    }
    
    public void deleteRowsfromTransaction()
    {
    
         String q ="DELETE FROM transactions" +
                            " WHERE TransactionID NOT IN (SELECT a.TransID" +
                            " FROM finance a)";
         
        try {
            pst = con.prepareStatement(q);
            int y = pst.executeUpdate();
            
            if(y==1)
                {
                
                    loadDataFromDatabase();
                    
                
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
    }

    private void errorDialog(String erMsg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
    }
    
   
    
  
}

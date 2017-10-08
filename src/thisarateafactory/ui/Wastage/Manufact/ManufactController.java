/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Manufact;

import thisarateafactory.ui.Wastage.AlertBox.AlertDialog;
//import thisarateafactory.ui.Wastage.Database.Dbconnect;
//import static Validation.validateTextFields.validateName;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateName;

/**
 * FXML Controller class
 *
 * @author ravindu
 */
public class ManufactController implements Initializable {
    
    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    
    private int mid=0;
    
    private ObservableList<MList> data;

    @FXML
    private JFXTextField manufactid;
    @FXML
    private JFXTextField name;
    @FXML
    private Label Manudate;
    @FXML
    private DatePicker Exdate;
    @FXML
    private TableView<MList> ManufactTable;
    @FXML
    private TableColumn<?, ?> Wmanufactid;
    @FXML
    private TableColumn<?, ?> Wwasteid;
    @FXML
    private TableColumn<?, ?> Wproductid;
    @FXML
    private TableColumn<?, ?> Wexpiredate;
    @FXML
    private TableColumn<?, ?> Wmanufactdate;
    @FXML
    private JFXButton Addbutton;
    @FXML
    private JFXButton Updatebutton;
    @FXML
    private JFXButton Deletemanufact;
    @FXML
    private JFXButton cancel;
    @FXML
    private JFXComboBox<String> wcombo;
    @FXML
    private JFXComboBox<String> pcombo;
    
    LocalDate localDate;
    DateTimeFormatter dtf;
    
    ObservableList<String>lll=FXCollections.observableArrayList();
    ObservableList<String>kkk=FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> Wname;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.now();
        Manudate.setText(dtf.format(localDate).toString());
        wcombo.setItems(lll);
        pcombo.setItems(kkk);
        
        data = FXCollections.observableArrayList();
        conn = databaseHandler.getConnection();
        getManufactId();
        fillComboBoxWaste();
        fillComboBoxProduct();
        setCellTable();
        loadDataFromDatabase();
        setCellValueFromTableToTextField();
        Searchmanufact();
        
    }    

    @FXML
    private void Addmanufact(ActionEvent event) {
        
        if(name.getText().trim().isEmpty() || wcombo.getValue() == null || wcombo.getValue().trim().isEmpty() || pcombo.getValue() == null || pcombo.getValue().trim().isEmpty() || Exdate.getValue()==null)
        {
            AlertDialog.display("Alert", "You have to fill every information");
            return;
        }
        if(!validateName(name.getText().trim()))
        {
                AlertDialog.display("Alert", "You can't enter numbers to the name");
                return;
        }
        
        if(!localDate.isBefore(Exdate.getValue())){
            AlertDialog.display("Alert", "Expire Date must be a date after Manufacture Date");
            return;
        }
        
        String manuDate=Manudate.getText();
        String exDate =Exdate.getValue().toString();
        int waste=Integer.parseInt(wcombo.getValue());
        int product=Integer.parseInt(pcombo.getValue());
        String Name=name.getText();
       
            conn = databaseHandler.getConnection();
            try{
                String q="INSERT INTO ManufactureItem (ProductID,ManufactureDate,Name,ExpiartDate,WasteID) values ('"+ product +"','"+ manuDate +"','"+ Name +"','"+ exDate +"','"+ waste +"')";
                System.out.println( q);
                pst=conn.prepareStatement(q);
                pst.execute();
                System.out.println("good");
            
                loadDataFromDatabase();
                AlertDialog.display("Alert", "Added Successfully");
            }
            catch(Exception e){
                System.out.println(e);
            }
        
        clearTextField();
        Manudate.setText(dtf.format(localDate).toString());
        getManufactId();
    }
    
    private void loadDataFromDatabase()
    {
        data.clear();
        try{
            pst = conn.prepareStatement("SELECT * from ManufactureItem");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new MList( ""+rs.getInt(1),""+rs.getInt(2),""+rs.getString(3),rs.getString(4),rs.getString(5), ""+rs.getInt(6)));
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(ManufactController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ManufactTable.setItems(data);
    }

    private void setCellValueFromTableToTextField() //mouse click
    {
        ManufactTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                MList w1 = ManufactTable.getItems().get(ManufactTable.getSelectionModel().getSelectedIndex());
                
                manufactid.setText(w1.getManufactureID());
                Manudate.setText(w1.getManufactureDate());
                pcombo.setValue(w1.getProductID());
                wcombo.setValue(w1.getWasteID());
                name.setText(w1.getName());
                String d1 = w1.getExpiartDate();
                LocalDate ld=LocalDate.parse(d1);
                Exdate.setValue(ld);
            }
            
        });
    }
    
    @FXML
    private void Updatemanufact(ActionEvent event) {
        
        String sql = "UPDATE ManufactureItem SET ProductID = ?,ManufactureDate = ?, Name = ?,ExpiartDate = ?, WasteID=?  WHERE ManufactureID = ?";
        
        try{
            
            if(name.getText().trim().isEmpty() || wcombo.getValue() == null || wcombo.getValue().trim().isEmpty() || pcombo.getValue() == null || pcombo.getValue().trim().isEmpty() || Exdate.getValue()==null)
        {
            AlertDialog.display("Alert", "You have to fill every information");
            return;
        }
        if(!validateName(name.getText().trim()))
        {
                AlertDialog.display("Alert", "You can't enter numbers to the name");
                return;
        }
        
         if(!localDate.isBefore(Exdate.getValue())){
            AlertDialog.display("Alert", "Expire Date must be a date after Manufacture Date");
            return;
        }
            
            int manu = Integer.parseInt(manufactid.getText());
            String manuDate=Manudate.getText();
            String exDate =Exdate.getValue().toString();
            int waste=Integer.parseInt(wcombo.getValue());
            int product=Integer.parseInt(pcombo.getValue());
            String Name=name.getText();
            
            pst =conn.prepareStatement(sql);
            pst.setInt(1,product);
            pst.setString(2,manuDate);
            pst.setString(3,Name);
            pst.setString(4,exDate);
            pst.setInt(5,waste);
            pst.setInt(6,manu);
            
            int x = pst.executeUpdate();
            System.out.println("yky");
            
            if(x==1)
            {
                clearTextField();
                AlertDialog.display("info", "Successfully Updated");
                loadDataFromDatabase();
                setCellTable();
                
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(ManufactController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setCellTable() //set table column ids
    {
        
        Wmanufactid.setCellValueFactory(new PropertyValueFactory<>("ManufactureID"));
        Wproductid.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        Wmanufactdate.setCellValueFactory(new PropertyValueFactory<>("ManufactureDate"));
        Wname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Wexpiredate.setCellValueFactory(new PropertyValueFactory<>("ExpiartDate"));
        Wwasteid.setCellValueFactory(new PropertyValueFactory<>("WasteID"));
        ManufactTable.setItems(data);
       
    }

    @FXML
    private void Searchmanufact() {
        
        search.setOnKeyReleased(e->{
            String sql="select * from ManufactureItem where ManufactureID= ?";
            
            if(search.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                data.clear();
            try {
                int i=Integer.parseInt(search.getText());
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      data.add(new MList( ""+rs.getInt(1),""+rs.getInt(2),""+rs.getString(3),rs.getString(4),rs.getString(5), ""+rs.getInt(6)));
                
                }
                
                ManufactTable.setItems(data);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }

    @FXML
    private void Deletestock(ActionEvent event) {
        
        int manuid = Integer.parseInt(manufactid.getText());
        String sql ="DELETE FROM ManufactureItem WHERE ManufactureID = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, manuid);
            
            int x = pst.executeUpdate();
            
            if(x==1)
            {
                System.out.println("Deleted Successfully");
                loadDataFromDatabase();
                AlertDialog.display("info", "Successfully Deleted");
                  
            }
        } catch (Exception e) {
        }
        clearTextField();
        manufactid.setText(null);
        getManufactId();
        //date.setText(dtf.format(localDate).toString());
    }

    @FXML
    private void Cancelmanufact(ActionEvent event) {
   
        ((Stage) (((Button)event.getSource()).getScene().getWindow())).close();
    
    }
    
    private void getManufactId()
    {
        String query="select max(ManufactureID) as currentid from ManufactureItem";
            try 
            {
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
            while(rs.next()){
                mid = rs.getInt("currentid") + 1;
            }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ManufactController.class.getName()).log(Level.SEVERE, null, ex);
            }
            manufactid.setText(String.valueOf(mid));
            System.out.println(mid);
    }
    
    public void fillComboBoxWaste()
    {
        try{
            
            String query  = " select WasteID from Waste f";
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    //wcombo.add(rs.getString("WasteID"));
                lll.add(rs.getString("WasteID"));
            }
                pst.close();
                rs.close();
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(ManufactController.class.getName()).log(Level.SEVERE, null, ex); 
        }
        wcombo.setItems(lll);
    }
    
    public void fillComboBoxProduct()
    {
        try{
            
            String query  = " select ProductID from Stock f";
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    //wcombo.add(rs.getString("WasteID"));
                kkk.add(rs.getString("ProductID"));
            }
                pst.close();
                rs.close();
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(ManufactController.class.getName()).log(Level.SEVERE, null, ex); 
        }
        pcombo.setItems(kkk);
    }
    
    private void clearTextField()
    {
        
        pcombo.getSelectionModel().clearSelection();
        wcombo.getSelectionModel().clearSelection();
        Exdate.setValue(null);
        name.setText(null);
    }
    
}

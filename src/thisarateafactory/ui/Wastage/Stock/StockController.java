/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Stock;

import thisarateafactory.ui.Wastage.AlertBox.AlertDialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;
import static thisarateafactory.validation.validateTextFields.validateName;
import static thisarateafactory.validation.validateTextFields.validateNumbersOnly;

/**
 * FXML Controller class
 *
 * @author ravindu
 */
public class StockController implements Initializable {

    ObservableList<String>eee=FXCollections.observableArrayList("50","100","200");
    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    
    private ObservableList<SList> data;//table load
    
    private int pid=0;
    private String radio=null;
    @FXML
    private Label Autoproductid;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXComboBox<String> Weight;
    @FXML
    private JFXTextField Quantity;
    @FXML
    private JFXTextField Price;
    @FXML
    private TableColumn<?, ?> Wproductid;
    @FXML
    private TableColumn<?, ?> Wtype;
    @FXML
    private TableColumn<?, ?> Wname;
    @FXML
    private TableColumn<?, ?> Wweight;
    @FXML
    private TableColumn<?, ?> Wquantity;
    @FXML
    private TableColumn<?, ?> Wprice;
    @FXML
    private JFXButton Addbutton;
    @FXML
    private JFXButton Updatebutton;
    @FXML
    private TextField Searchbutton;
    @FXML
    private JFXButton Deletebutton;
    @FXML
    private JFXRadioButton Fertilizer;
    @FXML
    private ToggleGroup Type;
    @FXML
    private JFXRadioButton Wastetea;
    @FXML
    private TableView<SList> StockTable;
    @FXML
    private JFXButton Cancelbutton;

    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Weight.setItems(eee);
        
        setCellTable();
        setCellValueFromTableToTextField() ;
        data = FXCollections.observableArrayList();
        conn = databaseHandler.getConnection();
        loadDataFromDatabase();
        getProductId();
        Searchstock();
    }  
    
      @FXML
    private void getFert(ActionEvent event) {
        radio="Fertilizer";
        System.out.println("X");
    }

    @FXML
    private void getTea(ActionEvent event) {
        radio="Waste Tea";
        System.out.println("Y");
    }

    @FXML
    private void Addstock(ActionEvent event) {
            
            
            String name=Name.getText();
            if(radio==null || Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null || Price.getText().trim().isEmpty() || String.valueOf(Price.getText()) == null || Weight.getValue() == null || Weight.getValue().trim().isEmpty() || Name.getText().trim().isEmpty())
            {
                AlertDialog.display("Alert", "You have to fill every information");
                return;
            }
            else if(!validateName(Name.getText().trim()))
            {
                AlertDialog.display("Alert", "You can't enter numbers to the name");
                return;
            }
            else if(Price.getText().trim().isEmpty() || String.valueOf(Price.getText()) == null || !validateCurrency(Price.getText()))
            {
                AlertDialog.display("Alert", "You can't enter letters to the price");
                return;
            }
            else if(Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null || !validateNumbersOnly(Quantity.getText()))
            {
                AlertDialog.display("Alert", "You can't enter letters to the Quantity");
                return;
            }
            else
            {
            conn = databaseHandler.getConnection();
            int qty=Integer.parseInt(Quantity.getText());
            double price=Double.parseDouble(Price.getText());
            int weight=Integer.parseInt(Weight.getValue());
            try{
                String q="INSERT INTO Stock (Type,Description,Weight,Qty,Price) values ('"+ radio +"','"+ name +"','"+ weight +"','"+ qty +"','"+ price +"')";
                pst=conn.prepareStatement(q);
                pst.execute();
                AlertDialog.display("Alert", "Added Successfully");
            
                loadDataFromDatabase();
            }
            catch(SQLException e){
                System.out.println(e);
            }
            radio=null;
            }
            clearTextField();
            getProductId();
    }

    @FXML
    private void Updatestock(ActionEvent event) {
        String sql = "UPDATE Stock SET Type = ?,Description = ?,Weight = ?, Qty = ?,Price = ?  WHERE ProductID = ?";

        try{
            
          
            if(radio==null || Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null || Price.getText().trim().isEmpty() || String.valueOf(Price.getText()) == null || Weight.getValue() == null || Weight.getValue().trim().isEmpty() || Name.getText().trim().isEmpty())
            {
                AlertDialog.display("Alert", "You have to fill every information");
                return;
            }
            if(!validateName(Name.getText().trim()))
            {
                AlertDialog.display("Alert", "You can't enter numbers to the name");
                return;
            }
            if(Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null || !validateNumbersOnly(Quantity.getText()))
            {
                AlertDialog.display("Alert", "You can't enter letters to the Quantity");
                return;
            }
            if(Price.getText().trim().isEmpty() || String.valueOf(Price.getText()) == null || !validateCurrency(Price.getText()))
            {
                AlertDialog.display("Alert", "You can't enter letters to the price");
                return;
            }
            
            String btn=radio;
            String description=Name.getText();
            String weight=Weight.getValue();
            int qty=Integer.parseInt(Quantity.getText());
            double total=Double.parseDouble(Price.getText());
            int productid = Integer.parseInt(Autoproductid.getText());
            
            pst =conn.prepareStatement(sql);
            pst.setString(1,btn);
            pst.setString(2,description);
            pst.setString(3,weight);
            pst.setInt(4,qty);
            pst.setDouble(5,total);
            pst.setInt(6,productid);
            
            int x = pst.executeUpdate();
            System.out.println("yky");
            
            if(x==1)
            {
                
                AlertDialog.display("info", "Successfully Updated");
                loadDataFromDatabase();
                

            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearTextField();
    }

    
        //Table Load
    private void setCellTable() //set table column ids
    {
        Wproductid.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        Wtype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Wname.setCellValueFactory(new PropertyValueFactory<>("Description"));
        Wweight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        Wquantity.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        Wprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        //Wprice.setCellValueFactory(new PropertyValueFactory<>("0.00"));
        StockTable.setItems(data);
    }

    private void loadDataFromDatabase()
    {
        data.clear();
        try{
            pst = conn.prepareStatement("SELECT * from Stock");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new SList( ""+rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getInt(4),""+rs.getInt(5), ""+rs.getDouble(6)));
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StockTable.setItems(data);
    }
    
    private void setCellValueFromTableToTextField() //mouse click
    {
        StockTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                SList w1 = StockTable.getItems().get(StockTable.getSelectionModel().getSelectedIndex());
                if(w1.getType().equals("Fertilizer"))
                {
                    
                    Fertilizer.setSelected(true);
                    radio = "Fertilzer";
                }
                else if(w1.getType().equals("Waste Tea"))
                {
                    
                    Wastetea.setSelected(true);
                    radio = "Waste Tea";
                }
                Autoproductid.setText(w1.getProductID());
                Weight.setValue(w1.getWeight());
                Price.setText(w1.getPrice());
                Name.setText(w1.getDescription());
                Quantity.setText(w1.getQty());
                Price.setText(w1.getPrice());
                
            }
            
        });
    }
    
    @FXML
    private void Searchstock() {
        
        Searchbutton.setOnKeyReleased(e->{
            String sql="select * from Stock where ProductID= ?";
            
            if(Searchbutton.getText().equals(""))
            {
                loadDataFromDatabase();
            
            }else
            {
                data.clear();
            try {
                int i=Integer.parseInt(Searchbutton.getText());
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      data.add(new SList( ""+rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getInt(4),""+rs.getInt(5), ""+rs.getDouble(6)));
                
                }
                
                StockTable.setItems(data);
                
            } catch (SQLException ex) {
            }
            
            }
        });
        
    }

    @FXML
    private void Deletestock(ActionEvent event) {
        
        int productid = Integer.parseInt(Autoproductid.getText());
        String sql ="DELETE FROM Stock WHERE ProductID = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, productid);
            
            int x = pst.executeUpdate();
            
            if(x==1)
            {
                System.out.println("Deleted Successfully");
                loadDataFromDatabase();
                AlertDialog.display("info", "Successfully Deleted");
                  
            }
        } catch (SQLException e) {
        }
        clearTextField();
    }

    @FXML
    private void Cancelstock(ActionEvent event) {
        ((Stage) (((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    private void getProductId()
    {
        String query="select max(ProductID) as currentid from Stock";
            try 
            {
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
            while(rs.next()){
                pid = rs.getInt("currentid") + 1;
            }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Autoproductid.setText(String.valueOf(pid));
            System.out.println(pid);
    }

    private void clearTextField()
    {
        
        Name.clear();
        Quantity.clear();
        Price.clear();
        Weight.getSelectionModel().clearSelection();
        Autoproductid.setText("");
        Type.selectToggle(null);
    }
    
    //private void loadDataFromDatabase() {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

  
}
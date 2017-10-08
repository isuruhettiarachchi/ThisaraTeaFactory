/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Waste;

import thisarateafactory.ui.Wastage.AlertBox.AlertDialog;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;
import static thisarateafactory.validation.validateTextFields.validateNumbersOnly;

/**
 * FXML Controller class
 *
 * @author ravindu
 */
public class WasteController implements Initializable {
    
    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    
    private String radio=null;
    private int wid = 0;
    private ObservableList<WList> data;//table load
    LocalDate localDate;
    DateTimeFormatter dtf;
    
    @FXML
    private Label Waste_ID;
    @FXML
    private JFXComboBox<String> Scombo;
    @FXML
    private JFXComboBox<String> Wcombo;
    @FXML
    private RadioButton Rorganic;
    @FXML
    private ToggleGroup Rgroup;
    @FXML
    private RadioButton Rinorganic;
    @FXML
    private JFXTextField Amount;
    @FXML
    private TableColumn<?, ?> Wwaste;
    @FXML
    private TableColumn<?, ?> Wdate;
    @FXML
    private TableColumn<?, ?> Wsource;
    @FXML
    private TableColumn<?, ?> Wwastename;
    @FXML
    private TableColumn<?, ?> Wtype;
    @FXML
    private TableColumn<?, ?> Wamount;
    @FXML
    private JFXButton Addbutton;
    @FXML
    private JFXButton Updatebutton;
    @FXML
    private TextField Searchbutton;
    @FXML
    private JFXButton Deletebutton;
    @FXML
    private JFXButton Cancelbutton;

    ObservableList<String>fff=FXCollections.observableArrayList("Leaf Collection","Withering","Maceration","Fermentation","Drying","Sorting");
    ObservableList<String>ggg=FXCollections.observableArrayList("Green Leaf","Gunny Bags","Metal Chips","Pekoe dust","Paper");
    @FXML
    private TableView<WList> WasteTable;
    @FXML
    private Label date;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = databaseHandler.getConnection();
        Scombo.setItems(fff);
        Wcombo.setItems(ggg);
        
        setCellTable();
        setCellValueFromTableToTextField() ;
        data = FXCollections.observableArrayList();
        
        loadDataFromDatabase();
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.now();
        date.setText(dtf.format(localDate).toString());
        getWasteId();
        Searchwaste();

    }    

    @FXML
    private void Organicaction(ActionEvent event) {
        radio="organic";
    }

    @FXML
    private void Inorganicaction(ActionEvent event) {
        radio="inorganic";
    }

    

    @FXML
    private void Updatewaste(ActionEvent event) {
        
        
        String sql = "UPDATE Waste SET RecieveDate = ?,Source = ?,WasteName = ?, Type = ?,Amount = ?  WHERE WasteID = ?";
        
        try{
            int waste = Integer.parseInt(Waste_ID.getText());
            String Date =date.getText();
            String scomb=Scombo.getValue();
            String wcomb=Wcombo.getValue();
            String rdbtn=radio;
            if(Amount.getText().trim().isEmpty() || String.valueOf(Amount.getText()) == null || !validateCurrency(Amount.getText())){
                        AlertDialog.display("Alert","You have to enter a number for amount");
                        return;
            }
            double total=Double.parseDouble(Amount.getText());
            
            pst =conn.prepareStatement(sql);
            pst.setString(1,Date);
            pst.setString(2,scomb);
            pst.setString(3,wcomb);
            pst.setString(4,rdbtn);
            pst.setDouble(5,total);
            pst.setInt(6,waste);
            

            int x = pst.executeUpdate();
            System.out.println("yky");
            
            if(x==1)
            {
                loadDataFromDatabase();
                AlertDialog.display("Alert", "Successfully Updated");
                
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(WasteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clearTextField();
        date.setText(dtf.format(localDate).toString());
        getWasteId();

    }
    
    //Table Load
    private void setCellTable() //set table column ids
    {
        Wwaste.setCellValueFactory(new PropertyValueFactory<>("WasteID"));
        Wdate.setCellValueFactory(new PropertyValueFactory<>("RecieveDate"));
        Wsource.setCellValueFactory(new PropertyValueFactory<>("Source"));
        Wwastename.setCellValueFactory(new PropertyValueFactory<>("WasteName"));
        Wtype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Wamount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        WasteTable.setItems(data);
    }

    private void loadDataFromDatabase()
    {
        data.clear();
        try{
            pst = conn.prepareStatement("SELECT * from Waste");
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new WList( ""+rs.getInt(1),""+rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), ""+rs.getDouble(6)));
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(WasteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        WasteTable.setItems(data);
    }
    
    private void setCellValueFromTableToTextField() //mouse click
    {
        WasteTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                WList w1 = WasteTable.getItems().get(WasteTable.getSelectionModel().getSelectedIndex());
                if(w1.getType().equals("organic"))
                {
                    Rorganic.setSelected(true);
                    radio="organic";
                }
                else if(w1.getType().equals("inorganic"))
                {
                    Rinorganic.setSelected(true);
                    radio="inorganic";
                }
                Waste_ID.setText(w1.getWasteID());
                date.setText(w1.getRecieveDate());
                Scombo.setValue(w1.getSource());
                Wcombo.setValue(w1.getWasteName());
                Amount.setText(w1.getAmount());
            }
            
        });
    }
    
    @FXML
    private void Searchwaste() {
        
        Searchbutton.setOnKeyReleased(e->{
            String sql="select * from Waste where WasteID= ?";
            
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
                      data.add(new WList(""+rs.getInt(1),""+rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5), ""+rs.getDouble(6)));
                
                }
                
                WasteTable.setItems(data);
                
            } catch (SQLException ex) {
            }
            
            }
        });
    }

    @FXML
    private void Deletewaste(ActionEvent event) {
        
        int wasteid = Integer.parseInt(Waste_ID.getText());
        String sql ="DELETE FROM Waste WHERE WasteID = ?";
        try {
            pst = conn.prepareStatement(sql);
            
            pst.setInt(1, wasteid);
            
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
        date.setText(dtf.format(localDate).toString());
        
    }

    @FXML
    private void Cancelwaste(ActionEvent event) {
        ((Stage) (((Button)event.getSource()).getScene().getWindow())).close();
    }
    
    private void getWasteId()
    {
        String query="select max(WasteID) as currentid from Waste";
            try 
            {
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    wid = rs.getInt("currentid") + 1;
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(WasteController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Waste_ID.setText(String.valueOf(wid));
            System.out.println(wid);
    }
    
    private void clearTextField()
    {
        Waste_ID.setText("");
        Scombo.getSelectionModel().clearSelection();
        Wcombo.getSelectionModel().clearSelection();
        Amount.clear();
        Rgroup.selectToggle(null);
    }


    @FXML
    private void Addwaste(ActionEvent event) {
        String Date=date.getText();
        String scomb=Scombo.getValue();
        String wcomb=Wcombo.getValue();
        String rad=radio.toString();
        if(rad==null || scomb==null || wcomb==null)
        {
            AlertDialog.display("Alert","You have to fill every information");
            return;
        }
        
        else if(Amount.getText().trim().isEmpty() || String.valueOf(Amount.getText()) == null || !validateNumbersOnly(Amount.getText())){
            AlertDialog.display("Alert", "Enter a number to Amount");
            return;
        }
        else
        {
        double amnt = Double.parseDouble(Amount.getText());
        try{
            String q="INSERT INTO Waste (RecieveDate,Source,WasteName,Type,Amount) values ('"+ Date +"','"+ scomb +"','"+ wcomb +"','"+ radio +"','"+ amnt +"')";
            System.out.println( q);
            pst=conn.prepareStatement(q);
            int i = pst.executeUpdate();
            AlertDialog.display("Alert", "Added Successfully");
            loadDataFromDatabase();
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        clearTextField();
        getWasteId();   
        
        date.setText(dtf.format(localDate).toString());
        radio=null;
        
    }
   

}


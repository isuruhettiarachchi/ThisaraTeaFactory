package thisarateafactory.ui.Transport.Maintain;

import thisarateafactory.ui.Transport.Driver.DriverController;
import thisarateafactory.ui.Transport.Turn.TurnController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;

public class MaintainController implements Initializable {
    Date dt;
    private Connection con = null;
    private PreparedStatement pst = null;
    private PreparedStatement pst1 = null;
    private ResultSet rs = null;
    private ResultSet rs1 = null;
    private ObservableList datav = FXCollections.observableArrayList();
    private ObservableList <maintainList> ml;
    @FXML
    private Label maintainidL;
    @FXML
    private DatePicker mdate;
    @FXML
    private JFXTextArea mdes;
    @FXML
    private JFXTextField mcost;
    @FXML
    private TableView<maintainList> maintaintable;
    @FXML
    private TableColumn<?, ?> columnmid;
    @FXML
    private TableColumn<?, ?> columnmcost;
    @FXML
    private JFXButton addB;
    @FXML
    private JFXButton updateB;
    @FXML
    private JFXButton deleteB;
    @FXML
    private TableColumn<?, ?> columndate;
    @FXML
    private TableColumn<?, ?> columnmdes;
    @FXML
    private JFXComboBox<String> regnoCombo;
    @FXML
    private TableColumn<?, ?> columnvid;
    @FXML
    private JFXButton schB;
    @FXML
    private JFXButton goB;
    @FXML
    private JFXTextField schID;
    @FXML
    private JFXButton clearB;
    @FXML
    private DatePicker mdateS;
    @FXML
    private JFXButton backB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = databaseHandler.getConnection();
        ml = FXCollections.observableArrayList();
        FillCombo();
        setCellTable();
        loadDatafromDB();
        setCelltoTextFeilds();
        mdate.setValue(LocalDate.now());
        
        RequiredFieldValidator valid = new RequiredFieldValidator();
        DoubleValidator numv = new DoubleValidator();
        
        valid.setMessage("Cant be Empty"); 
        numv.setMessage("Cost Must be a Number");
        
        mdes.getValidators().add(valid);
        mcost.getValidators().add(numv);
        mdes.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    mdes.validate();
                }
            }
        });
                mcost.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    mcost.validate();
                }
            }
        });       
    }
    
    public void FillCombo(){
            try{
            String sql = "SELECT VehicleID, RegNo from vehicle";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                datav.add(rs.getString("RegNo"));
            }
            regnoCombo.setItems(datav);
            pst.close();
            rs.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void addMaintain(ActionEvent event) {           
        if(mcost.getText()==null||mcost.getText().trim().isEmpty()||!validateCurrency(mcost.getText())){
             errorDialog("Cost can't be Empty or must be a number");
             return;
           }
        try {
            String sql = "INSERT INTO vehiclemaintenance(Date, MaintenanceType, Cost, VehicleID) VALUES(?,?,?,?)";
            String sql1 = "INSERT INTO Transaction(RefID, Type, Date, Amount) VALUES (?,?,?,?)";
            
            int vid = getVehicleID();
            LocalDate ld = mdate.getValue();
            dt = java.sql.Date.valueOf(ld);
            //System.out.println(vid);
            pst = con.prepareStatement(sql);
            pst1 = con.prepareStatement(sql1);
            
            pst.setDate(1, dt);
            pst.setString(2, mdes.getText());
            pst.setDouble(3, Double.parseDouble(mcost.getText()));
            pst.setInt(4, vid);
            pst.execute();
            
            pst1.setString(1, "VehicleMaintains");
            pst1.setDate(2, dt);
            pst1.setDouble(3, Double.parseDouble(mcost.getText()));
            //pst1.execute();
            
            setCellTable();
            loadDatafromDB();
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addToTransaction(){
        String query = "select MaintenanceID, cost from vehiclemaintenance where MaintenanceID = (select max(MaintenanceID) from vehiclemaintenance)";
        int id = 0;
        double amnt = 0;
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                id = rs.getInt("MaintenanceID");
                amnt = rs.getDouble("cost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into transactions(RefID, Type, Date, Amount) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, "VehicleMaintenance");
            pst.setDate(3, dt);
            pst.setDouble(4, amnt);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to transactions");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void updateMaintain(ActionEvent event) {
        if(mcost.getText()==null||mcost.getText().trim().isEmpty()||!validateCurrency(mcost.getText())){
             errorDialog("Cost can't be Empty or must be a number");
             return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Update");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Update?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
        try {
            int vid = getVehicleID();
            String sql = "UPDATE vehiclemaintenance SET Date=?, MaintenanceType=?, Cost=?, VehicleID=? WHERE MaintenanceID=?";
            LocalDate ld = mdate.getValue();
            Date dt = java.sql.Date.valueOf(ld);
            pst = con.prepareStatement(sql);
            pst.setDate(1, dt);
            pst.setString(2, mdes.getText());
            pst.setDouble(3, Double.parseDouble(mcost.getText()));
            pst.setInt(4, vid);
            pst.setInt(5, Integer.parseInt(maintainidL.getText()));
            pst.execute();
            setCellTable();
            loadDatafromDB();
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
        
    }

    @FXML
    private void deleteMaintain(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Delete?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){          
        try {            
            String sql = "DELETE FROM vehiclemaintenance WHERE MaintenanceID=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(maintainidL.getText()));
            pst.executeUpdate();
            setCellTable();
            loadDatafromDB();
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query = "delete from transactions where RefID = ? and Type = ?";
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, Integer.parseInt(maintainidL.getText()));
            pst.setString(2, "VehicleMaintenance");
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully delete form transactions");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    private int getVehicleID(){
            int vid=0;
            try {
                String vehicleid = regnoCombo.getSelectionModel().getSelectedItem();
                String sql = "SELECT VehicleID FROM vehicle WHERE RegNo LIKE ?";
                pst = con.prepareStatement(sql);
                pst.setString(1, vehicleid);
                rs = pst.executeQuery();                
                while(rs.next()){
                    vid = rs.getInt(1);        
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return vid;
    }
    
    private void setCellTable(){
        columnmid.setCellValueFactory(new PropertyValueFactory<>("maintainid"));
        columndate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnmdes.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnmcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnvid.setCellValueFactory(new PropertyValueFactory<>("vehicleid"));
    }
    
    private void loadDatafromDB(){
        ml.clear();
        try {            
            String sql = "SELECT * FROM vehiclemaintenance";            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                ml.add(new maintainList(""+rs.getInt(1), ""+rs.getDate(2), rs.getString(3), ""+rs.getDouble(4), ""+rs.getInt(5)));
            }
            pst.close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        maintaintable.setItems(ml);
    }
    
    private void setCelltoTextFeilds(){
        maintaintable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                try {
                    maintainList mll = maintaintable.getItems().get(maintaintable.getSelectionModel().getSelectedIndex());
                    String a = mll.getVehicleid();
                    String sql3 = "SELECT RegNo FROM vehicle WHERE VehicleID = ?";
                    pst = con.prepareStatement(sql3);
                    pst.setInt(1, Integer.parseInt(a));
                    rs = pst.executeQuery();
                    String regno = "";
                    while(rs.next()){
                        regno = rs.getString(1);
                    }
                    String date = mll.getDate();
                    LocalDate ld = LocalDate.parse(date);
                    
                    maintainidL.setText(mll.getMaintainid());
                    regnoCombo.setValue(regno);
                    mdate.setValue(ld);
                    mdes.setText(mll.getDescription());
                    mcost.setText(mll.getCost());
                    pst.close();
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(MaintainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        
        });
    }

    @FXML
    private void schDate(ActionEvent event) {
        if(mdateS.equals("")){
           loadDatafromDB();
        }else{
            ml.clear();           
                try {
                LocalDate ld = mdateS.getValue();
                Date dt = java.sql.Date.valueOf(ld);
                String sql = "SELECT * FROM vehiclemaintenance WHERE Date= ?";           
                pst = con.prepareStatement(sql);
                pst.setDate(1, dt);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    ml.add(new maintainList(""+rs.getInt(1), ""+rs.getDate(2), rs.getString(3), ""+rs.getDouble(4), ""+rs.getInt(5)));
                }
                maintaintable.setItems(ml);               
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void schID(ActionEvent event) {
        String sql = "SELECT * FROM vehiclemaintenance WHERE MaintenanceID =?";
            
            if(schID.getText().equals(""))
            {
                loadDatafromDB();
            }else{
                ml.clear();
                try{
                    int i = Integer.parseInt(schID.getText());
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, i);
                    pst.executeQuery();
                    rs = pst.executeQuery();
                    while(rs.next())
                    {
                        ml.add(new maintainList(""+rs.getInt(1), ""+rs.getDate(2), rs.getString(3), ""+rs.getDouble(4), ""+rs.getInt(5)));
                    }
                    maintaintable.setItems(ml);
                    }catch(NumberFormatException | SQLException ex){                
                    
                }
            }
    }

    @FXML
    private void clear(ActionEvent event) {
        clearAll();
    }
    private void clearAll(){
        maintainidL.setText("");
        regnoCombo.setValue("");
        mdate.setValue(LocalDate.now());
        mdes.setText("");
        mcost.setText("");
        mdateS.getEditor().clear();
        loadDatafromDB();
       
    }

    @FXML
    private void back(ActionEvent event) {
                try {
            Stage stage = (Stage) backB.getScene().getWindow();
            stage.close();
            
            
            FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("../transportMain/transportM.fxml"));
            Parent root1 = (Parent) fxmLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
       catch(Exception e){
       }
    }
    
    private void errorDialog(String erMsg) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
    }
}

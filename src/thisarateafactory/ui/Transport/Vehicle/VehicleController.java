/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Transport.Vehicle;

import thisarateafactory.ui.Transport.Driver.DriverController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;

/**
 * FXML Controller class
 *
 * @author Dexter
 */
public class VehicleController implements Initializable {
    private Connection con = null;
    private PreparedStatement pst= null;
    private ResultSet rs = null;    
    private ObservableList<vehicleList> vlist;

    @FXML
    private Label vidL;
    @FXML
    private JFXComboBox<String> vType;
    private ObservableList list = FXCollections.observableArrayList("Cab","Lorry","Container");
    @FXML
    private JFXTextField vNum;
    @FXML
    private JFXTextField milage;
    @FXML
    private JFXTextField storeC;
    @FXML
    private JFXButton addB;
    @FXML
    private TableView<vehicleList> vehicleTable;
    @FXML
    private TableColumn<?, ?> vidCol;
    @FXML
    private TableColumn<?, ?> vtypeCol;
    @FXML
    private TableColumn<?, ?> vnumCol;
    @FXML
    private TableColumn<?, ?> vmileCol;
    @FXML
    private TableColumn<?, ?> storecapCol;
    @FXML
    private JFXButton updateB;
    @FXML
    private JFXButton delB;
    @FXML
    private JFXButton clearB;
    @FXML
    private JFXButton backB;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = databaseHandler.getConnection();
        vType.setItems(list);             
        vlist = FXCollections.observableArrayList();
        setCellvalues();
        loadDatafromDB();
        setCelltoTextFeilds();
        
        RequiredFieldValidator valid = new RequiredFieldValidator();
        DoubleValidator numv = new DoubleValidator();
        DoubleValidator numv1 = new DoubleValidator();
        
        valid.setMessage("Cant be Empty"); 
        numv.setMessage("Milage Must be a Number");
        numv1.setMessage("Storage Capacity Must be a Number");
        vNum.getValidators().add(valid);
        milage.getValidators().add(numv);
        storeC.getValidators().add(numv1);
        
        vNum.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    vNum.validate();
                }
            }
        });        
        milage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    milage.validate();
                }
            }
        });
        storeC.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    storeC.validate();
                }
            }
        });        
        
        
    }    

    private void setCellvalues(){
        vidCol.setCellValueFactory(new PropertyValueFactory<>("v_id"));
        vtypeCol.setCellValueFactory(new PropertyValueFactory<>("v_type"));
        vnumCol.setCellValueFactory(new PropertyValueFactory<>("v_num"));
        vmileCol.setCellValueFactory(new PropertyValueFactory<>("storeCap"));
        storecapCol.setCellValueFactory(new PropertyValueFactory<>("mile"));
        
    }
    private void loadDatafromDB(){
        try{
            vlist.clear();
            String sql = "SELECT * FROM vehicle";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                vlist.add(new vehicleList(rs.getString(1), rs.getString(2), rs.getString(3), ""+rs.getDouble(4), ""+rs.getDouble(5)));
            }
            vehicleTable.setItems(vlist);
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void adDriver(ActionEvent event) {
        
        try{
            if(milage.getText()==null||milage.getText().trim().isEmpty()||!validateCurrency(milage.getText())){
                errorDialog("Milage can't be Empty or must be a number");
                return;
            }
            if(storeC.getText()==null||storeC.getText().trim().isEmpty()||!validateCurrency(storeC.getText())){
                errorDialog("Storage Capacity cant't be Empty or must be a number");
                return;
            }            
            //String db = "new1";
            //boolean is = Validation.validationTextF.isTextFieldNotEmpty(vNum, vidL, errMsg);
            String sql = "INSERT INTO vehicle (VehicleType, RegNo, StorageCapacity, Milage) values (?,?,?,?)";
            
            pst = con.prepareStatement(sql);
            pst.setString(1, vType.getValue());
            pst.setString(2, vNum.getText());
            pst.setDouble(3, Double.parseDouble(storeC.getText()));
            pst.setDouble(4, Double.parseDouble(milage.getText()));
            pst.execute();
            
            setCellvalues();
            loadDatafromDB();
        }
        catch(SQLException | NumberFormatException e){
           System.out.println(e); 
        }
    }

    @FXML
    private void updateVehicle(ActionEvent event) {
        if(milage.getText()==null||milage.getText().trim().isEmpty()||!validateCurrency(milage.getText())){
                errorDialog("Milage can't be Empty or must be a number");
                return;
            }
        if(storeC.getText()==null||storeC.getText().trim().isEmpty()||!validateCurrency(storeC.getText())){
                errorDialog("Storage Capacity cant't be Empty or must be a number");
                return;
            }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Update");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Update?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
        
        try{
        String sql = "UPDATE vehicle SET VehicleType=?, RegNo=?, StorageCapacity=?, Milage=? where VehicleID=?";
        String id = vidL.getText();
        String vtype = vType.getValue();
        String rNo = vNum.getText();
        String miles = milage.getText();
        String storeCa = storeC.getText();
        pst = con.prepareStatement(sql);
        pst.setString(1, vtype);
        pst.setString(2, rNo);
        pst.setString(3, storeCa);
        pst.setString(4, miles);
        pst.setString(5, id);
        pst.execute();
        loadDatafromDB();
        }
        catch(Exception e){
            System.out.println(e);
        }
        }
        
    }
    private void setCelltoTextFeilds(){
        vehicleTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                vehicleList list = vehicleTable.getItems().get(vehicleTable.getSelectionModel().getSelectedIndex());
                vidL.setText(list.getV_id());
                vType.setValue(list.getV_type());
                vNum.setText(list.getV_num());
                milage.setText(list.getMile());
                storeC.setText(list.getStoreCap());
            }
        });
    }

    @FXML
    private void deleteV(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Delete?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){     
        try{
            String id = vidL.getText();
            String sql = "DELETE from vehicle WHERE VehicleID=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();
            loadDatafromDB();
        }
        catch(SQLException | NumberFormatException e){
            
        }
        }
        
    }
    
    private void clearAll(){
        vType.setValue("");
        vNum.clear();
        milage.clear();
        storeC.clear();
        vidL.setText("");
    }
    @FXML
    private void clear(ActionEvent event) {
        clearAll();
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

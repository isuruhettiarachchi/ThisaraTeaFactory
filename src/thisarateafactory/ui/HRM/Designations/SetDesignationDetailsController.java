/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.Designations;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;
import static thisarateafactory.validation.validateTextFields.validateName;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class SetDesignationDetailsController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    String selectedDesignation = null;
    String selectedRate = null;
    
    DecimalFormat decimalFormat;

    ObservableList<Designation> list = FXCollections.observableArrayList();

    @FXML
    private JFXTextField designationText;
    @FXML
    private JFXTextField rateText;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXButton updateButton;
    @FXML
    private TableView<Designation> designationView;
    @FXML
    private TableColumn<Designation, String> desigColumn;
    @FXML
    private TableColumn<Designation, String> rateColumn;
    @FXML
    private TableColumn<Designation, Double> OTRateColumn;
    @FXML
    private Label warningLabel;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        //errorDialog();
        decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);

        initCol();
        loadData();
        getDesignation();
        
        rateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //OTRateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        designationView.setEditable(true);
        
        rateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Designation, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Designation, String> event) {
                
            }
        });
        
        rateColumn.setOnEditCommit(event ->{
            System.out.println("commit");
            if(String.valueOf(event.getNewValue()) == null || event.getNewValue().trim().isEmpty()){
                errorDialog("Rate value can not be empty");
                loadData();
                return;
            }
        
            if(!validateCurrency(event.getNewValue())){
                errorDialog("Invalid rate value");
                loadData();
                return;
            }
        
            Designation des = event.getRowValue();
            des.setRate(event.getNewValue());
            updateRateValues(Double.parseDouble(event.getNewValue()));
        });
        
//        
//        OTRateColumn.setOnEditCommit(event ->{
//            Designation des = event.getRowValue();
//            des.setOTRate(event.getNewValue());
//            updateOTValues(event.getNewValue());
//        });
        
        DoubleValidator doubleValidator = new DoubleValidator();
        doubleValidator.setMessage("Invalid Rate");
        
        rateText.getValidators().add(doubleValidator);
        
        rateText.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    rateText.validate();
                }
            }
        });
    }

    @FXML
    private void add(ActionEvent event) {
        if(designationText.getText() == null || designationText.getText().trim().isEmpty()){
            errorDialog("Designation can not be null");
            return;
        } else if(!validateName(designationText.getText())){
            errorDialog("Invalid desgination");
            return;
        }
        
        if(rateText.getText() == null || rateText.getText().trim().isEmpty()){
            errorDialog("Rate can not be null");
            return;
        }else if(!validateCurrency(rateText.getText().trim())){
            errorDialog("Invalid rate value");
            return;
        }
        
        
        String designation = designationText.getText();
        Double rate = Double.parseDouble(rateText.getText());
        //Double OTr = Double.parseDouble(OTRateText.getText());
        Double OTr = (rate/8)*1.25;
        String query = "insert into designation(designation, DailyRate, OTRate) values(?, ?, ?)";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, designation);
            pst.setDouble(2, Double.parseDouble(decimalFormat.format(rate)));
            pst.setDouble(3, Double.parseDouble(decimalFormat.format(OTr)));

            int x = pst.executeUpdate();

            if(x==1){
                System.out.println("Designation values insertion completed");
                designationText.clear();
                rateText.clear();
                //OTRateText.clear();
                warningLabel.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetDesignationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Execution Error");
            errorDialog("Unxpected error occured during accessing the database");
        }

        list.clear();
        initCol();
        loadData();
        warningLabel.setText("");
    }

    @FXML
    private void delete(ActionEvent event) {
        System.out.println(selectedDesignation);
        String query = "delete from designation where designation = ?";

        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, selectedDesignation);

            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Value Deleted Successfully");
                designationText.clear();
                rateText.clear();
            }
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Value deletion failed");
            errorDialog("You can not delete designations that already has employees");
        }catch (SQLException ex) {
            System.out.println("Value deletion failed");
            errorDialog("You can not delete designations that already has employees");
        }
        list.clear();
        initCol();
        loadData();
    }

    @FXML
    private void update(ActionEvent event) {
        System.out.println("999");
        //updateValues();
    }

    private void initCol() {
        desigColumn.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        rateColumn.setCellValueFactory(new PropertyValueFactory<>("Rate"));
        OTRateColumn.setCellValueFactory(new PropertyValueFactory<>("OTRate"));
    }

    private void loadData() {
        list.clear();
        String query = "select designation, DailyRate, OTRate from designation";
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();

            while(rs.next()){
                String desig = rs.getString("designation");
                String ra = String.valueOf(rs.getDouble("DailyRate"));
                Double OT = rs.getDouble("OTRate");

                list.add(new Designation(desig, ra, OT));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetDesignationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        designationView.getItems().setAll(list);
    }

    private void getDesignation(){
        designationView.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
        //employeeView eView = employeeTableview.getSelectionModel().getSelectedItem();
        Designation eView = designationView.getItems().get(designationView.getSelectionModel().getSelectedIndex());
        selectedDesignation = eView.getDesignation();
        selectedRate = eView.getRate();
        });
        System.out.println(selectedDesignation);
    }

    private void updateRateValues(Double doub){
        
        String query = "update designation set DailyRate = ?, OTRate = ? where designation = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setDouble(1, Double.parseDouble(decimalFormat.format(doub)));
            pst.setDouble(2, Double.parseDouble(decimalFormat.format((doub/8)*1.25)));
            pst.setString(3, selectedDesignation);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        }  catch (NumberFormatException ex){
            errorDialog("Invalid rate value");
        }catch (SQLException ex) {
            Logger.getLogger(SetDesignationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update failed");
            errorDialog("Unexpected error occured accessing the database");
        }
        
        list.clear();
        initCol();
        loadData();
    }
    
    private void errorDialog(String err){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(err);

        alert.showAndWait();
    }

    private void updateOTValues(Double doub) {
        String query = "update designation set OTRate = ? where designation = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setDouble(1, doub);
            pst.setString(2, selectedDesignation);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SetDesignationDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Update failed");
            errorDialog("Unexpected error occured accessing the database");
        }
        
        list.clear();
        initCol();
        loadData();
    }

}

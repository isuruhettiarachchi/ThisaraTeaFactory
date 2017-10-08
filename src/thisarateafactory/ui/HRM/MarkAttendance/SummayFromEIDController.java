/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.MarkAttendance;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class SummayFromEIDController implements Initializable {
    PreparedStatement pst;
    Connection connection;
    ResultSet rs;
    
    private ObservableList<EIDFilterList> data = FXCollections.observableArrayList();

    @FXML
    private TableView<EIDFilterList> tableView;
    @FXML
    private TableColumn<EIDFilterList, String> dateCol;
    @FXML
    private TableColumn<EIDFilterList, String> inTCol;
    @FXML
    private TableColumn<EIDFilterList, String> outTCol;
    @FXML
    private TableColumn<EIDFilterList, String> workedCol;
    @FXML
    private TableColumn<EIDFilterList, String> OTCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData(MarkDailyAttendanceController.getEID());
    }    

    private void initCol() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        inTCol.setCellValueFactory(new PropertyValueFactory<>("InTime"));
        outTCol.setCellValueFactory(new PropertyValueFactory<>("OutTime"));
        workedCol.setCellValueFactory(new PropertyValueFactory<>("WorkdHours"));
        OTCol.setCellValueFactory(new PropertyValueFactory<>("OTHours"));
    }

    private void loadData(String EID) {
        
        data.clear();
        
        String query = "select Date, InTime, OutTime, WorkedHours, OTHours from salary where EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, EID);
            rs = pst.executeQuery();
            while(rs.next()){
                String ddate = rs.getString("Date");
                String inT = rs.getString("InTime");
                String outT = rs.getString("OutTime");
                String wrk = rs.getString("WorkedHours");
                String otT = rs.getString("OTHours");
                
                data.add(new EIDFilterList(ddate, inT, outT, wrk, otT));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(SummayFromEIDController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tableView.getItems().setAll(data);
        
    }
    
    
    
}

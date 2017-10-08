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
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class AttendanceSummaryController implements Initializable {
    PreparedStatement pst;
    Connection connection;
    ResultSet rs;
    
    private ObservableList<summaryFromDay> data = FXCollections.observableArrayList();

    @FXML
    private TableView<summaryFromDay> attendanceView;
    @FXML
    private TableColumn<summaryFromDay, String> eidCol;
    @FXML
    private TableColumn<summaryFromDay, String> nameCol;
    @FXML
    private TableColumn<summaryFromDay, String> inTimeCol;
    @FXML
    private TableColumn<summaryFromDay, String> outTimeCol;
    @FXML
    private TableColumn<summaryFromDay, String> workedHrsCol;
    @FXML
    private TableColumn<summaryFromDay, String> OTCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        
        initCol();
        loadData(MarkDailyAttendanceController.getDate());
    }    

    private void initCol() {
        eidCol.setCellValueFactory(new PropertyValueFactory<>("eid"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        inTimeCol.setCellValueFactory(new PropertyValueFactory<>("InTime"));
        outTimeCol.setCellValueFactory(new PropertyValueFactory<>("OutTime"));
        workedHrsCol.setCellValueFactory(new PropertyValueFactory<>("WorkdHours"));
        OTCol.setCellValueFactory(new PropertyValueFactory<>("OTHours"));
    }

    private void loadData(String date) {
        data.clear();
        System.out.println(date);
        String query = "select EmployeeID, Name, InTime, OutTime, WorkedHours, OTHours from attendance where Date =?";
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, date);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String eid = rs.getString("EmployeeID");
                String ename = rs.getString("Name");
                String inT = rs.getString("InTime");
                String outT = rs.getString("OutTime");
                String wrk = rs.getString("WorkedHours");
                String ot = rs.getString("OTHours");
                data.add(new summaryFromDay(eid, ename, inT, outT, wrk, ot ));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AttendanceSummaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attendanceView.getItems().setAll(data);
    }
    
}

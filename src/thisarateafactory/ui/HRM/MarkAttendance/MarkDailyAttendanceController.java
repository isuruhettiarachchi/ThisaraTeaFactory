/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.MarkAttendance;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.HRM.EmployeeManagementController;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class MarkDailyAttendanceController implements Initializable {
    private static String filteredDate;
    private static String filteredEID;
    
    Scene DateScene;
    Stage stage1;
    
    DateTimeFormatter dtf;
    DateFormat tf;
    LocalDate currentDate;
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    int selectedEmpID;
    
    private ObservableList<Attendance> data = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Attendance> attendaceView;
    @FXML
    private TableColumn<Attendance, String> dateCol;
    @FXML
    private TableColumn<Attendance, Number> eidCol;
    @FXML
    private TableColumn<Attendance, String> nameCol;
    @FXML
    private TableColumn<Attendance, String> inTCol;
    @FXML
    private TableColumn<Attendance, String> outTCol;
    @FXML
    private TableColumn<Attendance, Number> dayOTCol;
    @FXML
    private TableColumn<Attendance, Number> nightOTCol;
    @FXML
    private Label eidLabel;
    @FXML
    private Label enameLabel;
    @FXML
    private JFXButton markInButton;
    @FXML
    private JFXButton markOutButton;
    @FXML
    private DatePicker dateFilter;
    private TextField FiltEID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        initCol();
        
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentDate = LocalDate.now();
        tf = new SimpleDateFormat("HH:mm");
        System.out.println(currentDate.toString());
        
        createDateInstance();
        loadTable();
        
        getSelectedEID();
        
        inTCol.setCellFactory(TextFieldTableCell.forTableColumn());
        attendaceView.setEditable(true);
        inTCol.setOnEditCommit(event ->{
            Attendance atd = event.getRowValue();
            atd.setInTime(event.getNewValue());
            updateInTime(event.getNewValue());
            System.out.println("commit");
        });
        
        outTCol.setCellFactory(TextFieldTableCell.forTableColumn());
        attendaceView.setEditable(true);
        outTCol.setOnEditCommit(event ->{
            try {
                Attendance atd = event.getRowValue();
                atd.setInTime(event.getNewValue());
                Time currentInTime = getInTime();
                java.sql.Time timeValue = new java.sql.Time(tf.parse(event.getNewValue()).getTime());
                if(currentInTime.before(timeValue)){
                    updateOutTime(event.getNewValue());
                    calcOT();
                }else{
                    errorDialog("Invalid Out Time");
                }
            } catch (ParseException ex) {
                Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadTable();
        });
        
        stage1 = new Stage();
    }    

    private void initCol() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("currentDate"));
        eidCol.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        inTCol.setCellValueFactory(new PropertyValueFactory<>("InTime"));
        outTCol.setCellValueFactory(new PropertyValueFactory<>("OutTime"));
        dayOTCol.setCellValueFactory(new PropertyValueFactory<>("WorkedHours"));
        nightOTCol.setCellValueFactory(new PropertyValueFactory<>("OTHours"));
    }

    private void createDateInstance() {
        System.out.println(dtf.format(currentDate));
        String query = "select EmployeeID, Name from Employee where DateResigned is null";
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                int empID = rs.getInt("EmployeeID");
                String empName = rs.getString("Name");
                
                String nestedQuery = "insert into Attendance(Date, EmployeeID, Name, WorkedHours, OTHours)"+
                        "values(?, ?, ?, ?, ?)";
                
                PreparedStatement npst;
                npst = connection.prepareStatement(nestedQuery);
                npst.setDate(1, java.sql.Date.valueOf(dtf.format(currentDate)));
                npst.setInt(2, empID);
                npst.setString(3, empName);
                npst.setInt(4, 0);
                npst.setInt(5, 0);
                int x = npst.executeUpdate();
            }
        } catch(SQLIntegrityConstraintViolationException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You have already created today's attendance sheet");

            alert.showAndWait();
        }catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void loadTable() {
        data.clear();
        
        String query = "select * from Attendance where Date = ?";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(dtf.format(currentDate)));
            rs = pst.executeQuery();
            while(rs.next()){
                String curDate = String.valueOf(rs.getDate("Date"));
                int empID = rs.getInt("EmployeeID");
                String empName = rs.getString("Name");
                String inTi = "";
                if(rs.getString("InTime") != null){
                    inTi = rs.getString("InTime");
                }
                String outTi = "";
                if(rs.getString("OutTime") != null){
                    outTi = rs.getString("OutTime");
                }
                int dOTime = rs.getInt("WorkedHours");
                int nOTime = rs.getInt("OTHours");
                
                data.add(new Attendance(curDate, empID, empName, inTi, outTi, dOTime, nOTime));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        attendaceView.getItems().setAll(data);
    }
    
    private void updateInTime(String inT){
        String query = "update attendance set InTime = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            java.sql.Time timeValue = new java.sql.Time(tf.parse(inT).getTime());
            pst.setTime(1, timeValue);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(3, selectedEmpID);
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateOutTime(String outT){
        String query = "update attendance set OutTime = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            java.sql.Time timeValue = new java.sql.Time(tf.parse(outT).getTime());
            pst.setTime(1, timeValue);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(3, selectedEmpID);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getSelectedEID(){
        attendaceView.setOnMouseClicked((javafx.scene.input.MouseEvent event)->{
            Attendance atdTemp  = attendaceView.getItems().get(attendaceView.getSelectionModel().getSelectedIndex());
            selectedEmpID = atdTemp.getEmployeeID();  
            enameLabel.setText(atdTemp.getName());
            eidLabel.setText(String.valueOf(selectedEmpID));
        });
        System.out.println(selectedEmpID);
    }
    
    private Time getInTime(){
        String query = "select InTime from Attendance where Date = ? and EmployeeID = ?";
        Time inT = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(2, selectedEmpID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                inT = rs.getTime("InTime");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inT;
    }
    
    private void calcOT(){
        Time curInTime = null;
        Time curOutTime = null;
        
        String query;
        
        //get inTime
        query = "select InTime from attendance where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(2, selectedEmpID);
            rs = pst.executeQuery();
            while(rs.next()){
                curInTime = rs.getTime("InTime");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //get outTime
        query = "select OutTime from attendance where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(2, selectedEmpID);
            rs = pst.executeQuery();
            while(rs.next()){
                curOutTime = rs.getTime("OutTime");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat format = new SimpleDateFormat("HH");
        int difference = (int) (curOutTime.getTime() - curInTime.getTime());
        System.out.println(difference);
        double dif = difference / 1000;
        dif = dif / 60;
        dif = dif / 60;
        
        DecimalFormat decimalFormat = new DecimalFormat("#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        String OThrsString = decimalFormat.format(dif);
        int WorkedHrs = Integer.parseInt(OThrsString);
        
        int OTTime = WorkedHrs - 8;
        if(OTTime > 0)
            WorkedHrs = WorkedHrs - OTTime;
        
        //calculate Worked Hours
        query = "update attendance set WorkedHours = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, WorkedHrs);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(3, selectedEmpID);
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Successfully calc and added OT hrs");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //calc OT
        query = "update attendance set OTHours = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            
            System.out.println(OTTime);
            if(OTTime > 0){
                System.out.println("OT hrs have");
                pst.setInt(1, OTTime);
                pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
                pst.setInt(3, selectedEmpID);
                int x = pst.executeUpdate();
                if(x==1){
                    System.out.println("Successfully inerted OThrs");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void errorDialog(String er){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(er);

        alert.showAndWait();
    }

    @FXML
    private void MarkIn(ActionEvent event) {
        String query = "select InTime from attendance where EmployeeID = ? and Date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, selectedEmpID);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getTime("InTime") != null){
                    System.out.println(String.valueOf(rs.getTime("InTime")));
                    errorDialog("You have already marked in time for this employee");
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "update attendance set InTime = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            Date date = new Date();
            Time time = new Time(date.getTime());
            pst.setTime(1, time);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(3, selectedEmpID);
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        loadTable();
    }

    @FXML
    private void MarkOut(ActionEvent event) {
        String query = "select InTime from attendance where EmployeeID = ? and Date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, selectedEmpID);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getTime("InTime") == null){
                    System.out.println(String.valueOf(rs.getTime("InTime")));
                    errorDialog("You don't have mnark the in time for this employee today");
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "select OutTime from attendance where EmployeeID = ? and Date = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setInt(1, selectedEmpID);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getTime("OutTime") != null){
                    System.out.println(String.valueOf(rs.getTime("InTime")));
                    errorDialog("You have already mark the out time for this employee today");
                    return;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "update attendance set OutTime = ? where Date = ? and EmployeeID = ?";
        try {
            pst = connection.prepareStatement(query);
            Date date = new Date();
            Time time = new Time(date.getTime());
            pst.setTime(1, time);
            pst.setDate(2, java.sql.Date.valueOf(dtf.format(currentDate)));
            pst.setInt(3, selectedEmpID);
            System.out.println("check");
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("Update Successful");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MarkDailyAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        calcOT();
        loadTable();
    }

    @FXML
    private void FilterByDate(ActionEvent event) {
        filteredDate = dateFilter.getValue().toString();
        System.out.println("filt"+filteredDate);
        
        try {
            System.out.println("x1");
            Parent ViewEmployeeParent = FXMLLoader.load(getClass().getResource("attendanceSummary.fxml"));
            DateScene = new Scene(ViewEmployeeParent);
            System.out.println("1");
            stage1.setScene(DateScene);
            System.out.println("2");
            stage1.setTitle("View Attendance");
            System.out.println("3");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static String getDate(){
        return filteredDate;
    }

    private void FilterByEID(ActionEvent event) {
        filteredEID = FiltEID.getText();
        try {
            System.out.println("x1");
            Parent ViewEmployeeParent = FXMLLoader.load(getClass().getResource("summayFromEID.fxml"));
            DateScene = new Scene(ViewEmployeeParent);
            System.out.println("1");
            stage1.setScene(DateScene);
            System.out.println("2");
            stage1.setTitle("View Attendance");
            System.out.println("3");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getEID(){
        return filteredEID;
    }
}

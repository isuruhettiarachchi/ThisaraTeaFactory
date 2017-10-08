/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.CalculateSalary;

import com.jfoenix.controls.JFXButton;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class CalcSalaryController implements Initializable {
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;

    LocalDate sDate;
    LocalDate eDate;
    
    DateTimeFormatter dtf;
    LocalDate currentDate;
    
    private final ObservableList<Salary> data = FXCollections.observableArrayList();
            
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private JFXButton calculateButton;
    @FXML
    private TableView<Salary> salaryView;
    @FXML
    private TableColumn<Salary, Integer> eidColumn;
    @FXML
    private TableColumn<Salary, String> nameColumn;
    @FXML
    private TableColumn<Salary, Double> workedDaysColumn;
    @FXML
    private TableColumn<Salary, Integer> OTColumn;
    @FXML
    private TableColumn<Salary, Double> deductionsColumn;
    @FXML
    private TableColumn<Salary, Double> bonusColumn;
    @FXML
    private TableColumn<Salary, Double> salaryColumn;
    @FXML
    private TableColumn<Salary, String> startDColumn;
    @FXML
    private TableColumn<Salary, String> endDColumn;
    @FXML
    private Label fromDateText;
    @FXML
    private Label toDateText;
    @FXML
    private Label countEmployeeText;
    @FXML
    private Label countSalaryText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        
        initCol();
        loadData();
        
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        currentDate = LocalDate.now();
    }    

    @FXML
    private void calculateSalary(ActionEvent event) {
        calcSal();
        addToTransactions();
        loadData();
    }

    private void insertRowToSalary(int EID, String eName, double WorkedD, int OTHr, double dailyRt, double OTRt) {
        double EPF;
        double ETF;
        double CompETF;
        double Salary;
        double bonus=0;
        if(WorkedD > 18){
            bonus = 175;
        }
        
        double totalSal = (WorkedD * dailyRt) + (OTHr * OTRt);
        EPF = totalSal * 0.08;
        CompETF = totalSal * 0.12;
        ETF = totalSal * 0.03;
        Salary = totalSal - EPF - ETF + bonus;
        
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        
        
        try {
            String insertQuery = "insert into Salary(EmployeeID, Name, StartDate, EndDate, WorkedDays, OTHours, AttendanceBonus, EPFDeduction, ETFDeduction, CompanyETF, Salary) "+
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst3 = connection.prepareStatement(insertQuery);
            pst3.setInt(1, EID);
            pst3.setString(2, eName);
            pst3.setDate(3, java.sql.Date.valueOf(sDate));
            pst3.setDate(4, java.sql.Date.valueOf(eDate));
            pst3.setDouble(5, WorkedD);//convert to int check with attedance table
            pst3.setInt(6, OTHr);
            pst3.setDouble(7, bonus);
            pst3.setDouble(8, Double.parseDouble(decimalFormat.format(EPF)));//calculate EPF using functions
            pst3.setDouble(9, Double.parseDouble(decimalFormat.format(ETF)));//calculate ETF using functions
            pst3.setDouble(10, Double.parseDouble(decimalFormat.format(CompETF)));//calculate CompanyETF using functions
            pst3.setDouble(11, Double.parseDouble(decimalFormat.format(Salary)));//calculate Salary using functions
            
            int x = pst3.executeUpdate();
            if(x==1)
                System.out.println("Successfully added");
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addToTransactions(){
        String query = "select sum(Salary) from Salary where EndDate = ?";
        double salaryTot = 0;
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(eDate));
            rs = pst.executeQuery();
            
            while(rs.next()){
                salaryTot = rs.getDouble("sum(Salary)");
                System.out.println(salaryTot);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "insert into SalaryEvents(Date, amount) values(?, ?)";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1 , java.sql.Date.valueOf(eDate));
            pst.setDouble(2 , salaryTot);
            
            int x = pst.executeUpdate();
            if(x==1){
                System.out.println("successfully added to salaryevents");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "select SalaryID, Date, amount from SalaryEvents where SalaryID = (select max(SalaryID) from SalaryEvents)";
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                int id = rs.getInt("SalaryID");
                Date date = rs.getDate("Date");
                double amnt = rs.getDouble("amount");
                
                String query1 = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
                PreparedStatement pst1 = connection.prepareStatement(query1);
                pst1.setInt(1, id);
                pst1.setString(2, "Employee");
                pst1.setDate(3, (java.sql.Date) date);
                pst1.setDouble(4, amnt);
                
                int y = pst1.executeUpdate();
                if(y==1){
                    System.out.println("successfully added to transaction table");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void calcSal(){
        LocalDate lastPayDate = null;
        //LocalDate lastPayDateStart = null;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            sDate = startDate.getValue();
            eDate = endDate.getValue();
            
            String query = "select max(EndDate) from Salary";
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                if(rs.getDate("max(EndDate)") == null){
                    System.out.println("check");
                    String d = "1000-10-10";
                    lastPayDate = LocalDate.parse(d, dtf);
                    //lastPayDateStart = LocalDate.parse(d, dtf);
                    System.out.println(lastPayDate.toString());
                }else{
                    lastPayDate = rs.getDate("max(EndDate)").toLocalDate();
                    //lastPayDateStart = rs.getDate("max(StartDate)").toLocalDate();
                }
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /*Calendar c = Calendar.getInstance();
        c.setTime(java.sql.Date.valueOf(lastPayDate));
        c.add(Calendar.DATE, 1);
        lastPayDateStart = LocalDate.parse(c.getTime().toString(), dtf);*/
                
        if(sDate.isBefore(lastPayDate) || eDate.isBefore(lastPayDate) || sDate.isEqual(lastPayDate) || eDate.isEqual(lastPayDate)){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Pay Dates");
            alert.setHeaderText(null);
            alert.setContentText("You have already calculated salary for selected duration");

            alert.showAndWait();
        }
        else{
            selectEmployees();
        }
        
    }
    
    private void selectEmployees(){
        sDate = startDate.getValue();
        eDate = endDate.getValue();
        
        String query = "select EmployeeID, Name, Sum(WorkedHours)/8 as Days, Sum(OTHours) as OT from attendance where "+
                "Date >= ? AND Date <= ? AND NOT(WorkedHours = 0) group by EmployeeID";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, java.sql.Date.valueOf(sDate));
            pst.setDate(2, java.sql.Date.valueOf(eDate));
            rs = pst.executeQuery();
            
            while(rs.next()){
                int empID = rs.getInt("EmployeeID");
                System.out.println(empID);
                String empName = rs.getString("Name");
                double workedDays = rs.getInt("Days");
                int OThrs = rs.getInt("OT");
                double dailyR = 0;
                double OTr = 0;
                
                String n1Query = "select Designation from employee where EmployeeID = ?";
                PreparedStatement pst1 = connection.prepareStatement(n1Query);
                pst1.setInt(1, empID);
                ResultSet rs1 = pst1.executeQuery();
                
                while(rs1.next()){
                    String desig = rs1.getString("Designation");
                    System.out.println(desig);
                    
                    String n2Query = "select DailyRate, OTRate from designation where designation = ?";
                    PreparedStatement pst2 = connection.prepareStatement(n2Query);
                    pst2.setString(1, desig);
                    ResultSet rs2 = pst2.executeQuery();
                    while(rs2.next()){
                        System.out.println("check");
                        dailyR = rs2.getDouble("DailyRate");
                        OTr = rs2.getDouble("OTRate");
                    }
                }
                
                insertRowToSalary(empID, empName, workedDays, OThrs, dailyR, OTr);
                
            }
            
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCol() {
        eidColumn.setCellValueFactory(new PropertyValueFactory<>("eid"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        workedDaysColumn.setCellValueFactory(new PropertyValueFactory<>("workDays"));
        OTColumn.setCellValueFactory(new PropertyValueFactory<>("OT"));
        deductionsColumn.setCellValueFactory(new PropertyValueFactory<>("deductions"));
        bonusColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void loadData() {
        data.clear();
        
        String query = "select max(EndDate) from Salary";
        Date maxDate = null;
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                maxDate = rs.getDate("max(EndDate)");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        query = "select EmployeeID, Name, StartDate, EndDate, WorkedDays, OTHours, AttendanceBonus, EPFDeduction, ETFDeduction, Salary from Salary where EndDate = ?";
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, (java.sql.Date) maxDate);
            rs = pst.executeQuery();
            while(rs.next()){
                int eid = rs.getInt("EmployeeID");
                String eName = rs.getString("Name");
                String DateStart = String.valueOf(rs.getDate("StartDate"));
                fromDateText.setText(DateStart);
                String DateEnd = String.valueOf(rs.getDate("EndDate"));
                toDateText.setText(DateEnd);
                double wrkDays = rs.getDouble("WorkedDays");
                int OThrs = rs.getInt("OTHours");
                double bon = rs.getDouble("AttendanceBonus");
                double deductions = rs.getDouble("EPFDeduction") + rs.getDouble("ETFDeduction");
                double sal = rs.getDouble("Salary");
                //countEmployeeText.setText(String.valueOf(rs.getInt("count(EmployeeID)")));
                //countSalaryText.setText(String.valueOf(rs.getDouble("sum(Salary)")));
                
                data.add(new Salary(eid, eName, DateStart, DateEnd, wrkDays, OThrs, bon, deductions, sal));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        salaryView.getItems().setAll(data);
        
        query = "select count(EmployeeID), sum(Salary) from Salary where EndDate = ?";
        
        try {
            pst = connection.prepareStatement(query);
            pst.setDate(1, (java.sql.Date) maxDate);
            rs = pst.executeQuery();
            
            while(rs.next()){
                countEmployeeText.setText(String.valueOf(rs.getInt("count(EmployeeID)")));
                countSalaryText.setText(String.valueOf(rs.getDouble("sum(Salary)")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalcSalaryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

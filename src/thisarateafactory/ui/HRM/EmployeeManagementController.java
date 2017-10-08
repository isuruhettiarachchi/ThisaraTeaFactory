/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class EmployeeManagementController implements Initializable {
    private Stage stage;
    private Parent parent;
    
    private Stage stage1;
    private Scene AddNewEmployeeScene;
    private Scene ViewEmployeeScene;
    private Scene MarkAttendanceScene;
    private Scene CalculateSalaryScene;
    private Scene SetDesignationScene;
    private Scene ViewAllEmployeesScene;
    
    Connection connection;
    PreparedStatement pst;
    ResultSet rs;
    
    private final ObservableList<Employee> data = FXCollections.observableArrayList();
    private static int selectedEID;
    

    @FXML
    private TableView<Employee> employeeView;
    @FXML
    private TableColumn<Employee, Integer> eidColumn;
    @FXML
    private TableColumn<Employee, String> nicColumn;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> desigColumn;
    @FXML
    private TextField searchBar;
    @FXML
    private JFXButton addButton;
    @FXML
    private JFXButton viewButton;
    @FXML
    private JFXButton attendanceButton;
    @FXML
    private JFXButton salaryButton;
    @FXML
    private JFXButton designationButton;
    @FXML
    private JFXButton refreshButton;
    @FXML
    private AnchorPane employeeMain;
    @FXML
    private JFXButton viewAllEmployeesButton;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        loadTalbe();
        setEID();
        viewButton.setDisable(true);
        
        FilteredList<Employee> filteredData = new FilteredList<>(data, e -> true);
        searchBar.setOnKeyReleased(e ->{
            searchBar.textProperty().addListener((ObservableValue, oldValue, newValue) ->{
                filteredData.setPredicate((Predicate<? super Employee>) employee->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    String teid = String.valueOf(employee.getEmployeeID());
                    if(teid.toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    if(employee.getNIC().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    if(employee.getDesignation().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    if(employee.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Employee> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(employeeView.comparatorProperty());
            employeeView.setItems(sortedData);
        });
        
        //closeButtonAction
        employeeMain.sceneProperty().addListener((observableScene, oldScene, newScene) -> {
            if(oldScene == null && newScene != null){
                newScene.windowProperty().addListener((observableWindow, oldWindow, newWindow) ->{
                    if(oldWindow == null && newWindow != null){
                        stage = (Stage) employeeMain.getScene().getWindow();
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                try {
                                    System.out.println("exiting");
                                    Parent parent = FXMLLoader.load(getClass().getResource("/thisarateafactory/Main.fxml"));
                                    Stage stage = new Stage(StageStyle.DECORATED);
                                    stage.setResizable(false);
                                    stage.setTitle("Thisara Management System");
                                    
                                    stage.setScene(new Scene(parent));
                                    stage.show();   } catch (IOException ex) {
                                    Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            
                        });
                    }
                });
            }
        });
        
        this.stage1 = new Stage();
        
    }

    @FXML
    private void addNewEmployee(ActionEvent event) {
        try {
            Parent AddNewEmployeeParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/AddEmployee/AddNewEmployee.fxml"));
            AddNewEmployeeScene = new Scene(AddNewEmployeeParent);
            stage1.setScene(AddNewEmployeeScene);
            stage1.setTitle("Add New Employee");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void viewEmployee(ActionEvent event) {
        try {
            Parent ViewEmployeeParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/ViewEmployee/ViewSelectedEmployee.fxml"));
            ViewEmployeeScene = new Scene(ViewEmployeeParent);
            stage1.setScene(ViewEmployeeScene);
            stage1.setTitle("View and Edit Selected Employee Details");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void markAttendance(ActionEvent event) {
        try {
            Parent MarkAttendanceParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/MarkAttendance/MarkDailyAttendance.fxml"));
            MarkAttendanceScene = new Scene(MarkAttendanceParent);
            stage1.setScene(MarkAttendanceScene);
            stage1.setTitle("Mark Employee Attendance For Today");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void calculateSalary(ActionEvent event) {
        try {
            Parent CalculateSalaryParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/CalculateSalary/CalcSalary.fxml"));
            CalculateSalaryScene = new Scene(CalculateSalaryParent);
            stage1.setScene(CalculateSalaryScene);
            stage1.setTitle("Calculate Salary");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setDesignationDetails(ActionEvent event) {
        try {
            Parent SetDesignationParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/Designations/SetDesignationDetails.fxml"));
            SetDesignationScene = new Scene(SetDesignationParent);
            stage1.setScene(SetDesignationScene);
            stage1.setTitle("Set Designation Details");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initColumns() {
        eidColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        nicColumn.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        desigColumn.setCellValueFactory(new PropertyValueFactory<>("Designation"));
    }
    
    private void loadTalbe() {
        data.clear();
        
        connection = databaseHandler.getConnection();
        String query = "select EmployeeID, NIC, Name, Designation from employee where DateResigned is null";
        
        try {
            pst = (PreparedStatement) connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                int pEID = rs.getInt("EmployeeID");
                String pNIC = rs.getString("NIC");
                String pNAME = rs.getString("Name");
                String pPOS = rs.getString("Designation");
                                
                data.add(new Employee(pEID, pNIC, pNAME, pPOS));
                //System.out.println(pEID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        employeeView.getItems().setAll(data);

    }

    private void setEID() {
        employeeView.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            Employee eView = employeeView.getItems().get(employeeView.getSelectionModel().getSelectedIndex());
            selectedEID = eView.getEmployeeID();
            viewButton.setDisable(false);
        });
        System.out.println(selectedEID);
        
    }
    
    public static int getEID(){
        //employeeView eView = employeeTableview.getSelectionModel().getSelectedItem();
        return selectedEID;
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        loadTalbe();
        viewButton.setDisable(true);
    }

    @FXML
    private void viewAllEmployees(ActionEvent event) {
        try {
            Parent ViewAllEmployeesParent = FXMLLoader.load(getClass().getResource("/thisarateafactory/ui/HRM/ViewEmployee/ViewAll.fxml"));
            ViewAllEmployeesScene = new Scene(ViewAllEmployeesParent);
            stage1.setScene(ViewAllEmployeesScene);
            stage1.setTitle("View All Employees");
            stage1.show();
        } catch (IOException ex) {
            Logger.getLogger(EmployeeManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String basePath = new File("").getAbsolutePath();
        String path = new File("src/thisarateafactory/reports/employee.jrxml").getAbsolutePath();
        try{
            
            String is = path;
            JasperReport jr =JasperCompileManager.compileReport(is);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,connection);
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
    }
    
}

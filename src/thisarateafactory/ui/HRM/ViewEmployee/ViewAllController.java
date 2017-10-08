/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.ViewEmployee;

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
public class ViewAllController implements Initializable {
    PreparedStatement pst;
    Connection connection;
    ResultSet rs;
    
    private static int selectedEID;
    
    private final ObservableList<Employee> data = FXCollections.observableArrayList();

    @FXML
    private TableView<Employee> employeeView;
    @FXML
    private TableColumn<Employee, Integer> eidCol;
    @FXML
    private TableColumn<Employee, String> nicCol;
    @FXML
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> desigCol;
    @FXML
    private TableColumn<Employee, String> addrCol;
    @FXML
    private TableColumn<Employee, String> genderCol;
    @FXML
    private TableColumn<Employee, String> bdayCol;
    @FXML
    private TableColumn<Employee, String> phoneCol;
    @FXML
    private TableColumn<Employee, String> appCol;
    @FXML
    private TableColumn<Employee, String> epfCol;
    @FXML
    private TableColumn<Employee, String> resginCol;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = databaseHandler.getConnection();
        
        initCols();
        loadData();
    }    

    private void initCols() {
        eidCol.setCellValueFactory(new PropertyValueFactory<>("eid"));
        nicCol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        desigCol.setCellValueFactory(new PropertyValueFactory<>("designation"));
        addrCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        bdayCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        appCol.setCellValueFactory(new PropertyValueFactory<>("appDate"));
        epfCol.setCellValueFactory(new PropertyValueFactory<>("etfDate"));
        resginCol.setCellValueFactory(new PropertyValueFactory<>("resginDate"));
    }

    private void loadData() {
        data.clear();
        
        String query = "select * from employee";
        
        try {
            pst = connection.prepareStatement(query);
            rs = pst.executeQuery();
            System.out.println("check");
        
            while(rs.next()){
                int eid = rs.getInt("EmployeeID");
                String nic = rs.getString("NIC");
                String name = rs.getString("Name");
                String desig = rs.getString("Designation");
                String addr = rs.getString("Address");
                String gender = rs.getString("Gender");
                String bday = String.valueOf(rs.getDate("Birthdate"));
                String phone = rs.getString("Phone");
                String appDate = String.valueOf(rs.getDate("DateAppointed"));
                String resDate = String.valueOf(rs.getDate("DateResigned"));
                String etfDate = String.valueOf(rs.getDate("EPFETFDate"));
                
                
                data.add(new Employee(eid, nic, name, desig, addr, gender, bday, phone, appDate, etfDate, resDate));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllController.class.getName()).log(Level.SEVERE, null, ex);
        }
        employeeView.getItems().setAll(data);
    }
    
}

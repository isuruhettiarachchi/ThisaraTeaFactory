package thisarateafactory.ui.Transport.TotalCost;

import thisarateafactory.ui.Transport.Driver.DriverController;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;

public class TotalcostController implements Initializable {

    private Connection con = null;
    private ObservableList<totaltransportcost> tc;
    private ObservableList<maintainCost> mc;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    private PreparedStatement pst1 = null;
    @FXML
    private TableColumn<?, ?> columnYear;
    @FXML
    private TableColumn<?, ?> columnMonth;
    @FXML
    private TableColumn<?, ?> columnFcost;
    @FXML
    private TableColumn<?, ?> columnOe;
    @FXML
    private TableColumn<?, ?> columnTotal;
    @FXML
    private TableView<totaltransportcost> totaltransportTable;
    @FXML
    private TableView<maintainCost> mcostTable;
    @FXML
    private TableColumn<?, ?> columnYearM;
    @FXML
    private TableColumn<?, ?> columnMonthM;
    @FXML
    private TableColumn<?, ?> columnMcost;
    @FXML
    private JFXButton backBS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = databaseHandler.getConnection();
        tc = FXCollections.observableArrayList();
        mc = FXCollections.observableArrayList();
        String sql = "INSERT INTO totaltransportcost select year(date) as y, month(date) as m, sum(fuelcost) as fc, SUM(otherexp) as oe, SUM(fuelcost+otherexp) as tot from turn group by year(date), month(date)";
        String sql1 = "INSERT INTO maintenancecost select year(Date) as y, month(Date) as m, sum(Cost) as mcost from vehiclemaintenance group by year(Date), month(Date)";
        try {
           pst = con.prepareStatement(sql);
           pst1 = con.prepareStatement(sql1);
           pst.execute();
           pst1.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(TotalcostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setCellTableTransport();
        loadDatafromDBTransport();
        setCellTableMaintain();
        loadDatafromDBMaintain();
        
           
    }
    public void setCellTableTransport(){
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        columnFcost.setCellValueFactory(new PropertyValueFactory<>("fcost"));
        columnOe.setCellValueFactory(new PropertyValueFactory<>("otherExp"));
        columnTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    private void loadDatafromDBTransport(){
        try {
            String sql = "SELECT DISTINCT  * FROM totaltransportcost";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                tc.add(new totaltransportcost(""+rs.getInt(1), ""+rs.getInt(2), ""+rs.getDouble(3), ""+rs.getDouble(4), ""+rs.getDouble(5)));
            }
            totaltransportTable.setItems(tc);
            rs.close();
            pst.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TotalcostController.class.getName()).log(Level.SEVERE, null, ex);
        }      
        
    }
    private void setCellTableMaintain(){
        columnYearM.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnMonthM.setCellValueFactory(new PropertyValueFactory<>("month"));
        columnMcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }
    
    private void loadDatafromDBMaintain(){
        try {
            String sql ="SELECT DISTINCT * FROM maintenancecost";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                mc.add(new maintainCost(""+rs.getInt(1), ""+rs.getInt(2), ""+rs.getInt(3)));
            }
            rs.close();
            pst.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(TotalcostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mcostTable.setItems(mc);
    }

    @FXML
    private void back(ActionEvent event) {
                try {
            Stage stage = (Stage) backBS.getScene().getWindow();
            stage.close();
            
            
            FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("../Turn/turn.fxml"));
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
        
}

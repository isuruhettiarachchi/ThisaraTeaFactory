package thisarateafactory.ui.Finance.Summary;

import thisarateafactory.ui.Finance.DialogBox.AlertBox;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import thisarateafactory.ui.Finance.thisara.FinanceList;
import thisarateafactory.database.databaseHandler;

/**
 * FXML Controller class
 *
 * @author Chinthaka
 */
public class FXMLController implements Initializable {

    private Connection con = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ObservableList<FinanceList> data;

       
    @FXML
    private Label in;
    @FXML
    private Label tc;
    @FXML
    private Label np;
    
    @FXML
    private StackedBarChart<?, ?> incomechart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    @FXML
    private JFXButton btnOk;
    @FXML
    private DatePicker sdate;
    @FXML
    private DatePicker edate;
    private JFXRadioButton pid;
    @FXML
    private JFXButton report;

    /**
     * Initializes the controller class.
     */
    
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            // TODO
            con = databaseHandler.getConnection();
            
            
            //getTotalIncome();
            //Month.setItems(mnth);
            // XYChart.Series set1 = new XYChart.Series<>();
            //set1.getData().add(new XYChart.Data("Total Income",100));
            //set1.getData().add(new XYChart.Data("Total Costs",2000));
            //set1.getData().add(new XYChart.Data("Net Profit",100));
            
            //incomechart.getData().addAll(set1);
     
    }    



    @FXML
    private void getNet(ActionEvent event) throws SQLException {
        
        //incomechart.getData().remove(0);
      
            //incomechart.getData().addAll(set1);
       
             if(sdate.getValue()== null || edate.getValue()== null)
            {
            
                AlertBox.display("info", "Please fill the fields");
               
            
            }else
            {
                
                    double cost = getTotalCost();
                double income =  getTotalIncome();
            
                double net = income-cost;
            
            
                String net1 = new Double(net).toString();
                np.setText(net1);
        
                
                XYChart.Series set1 = new XYChart.Series<>();
                set1.getData().add(new XYChart.Data("Total Income",income));
                set1.getData().add(new XYChart.Data("Total Cost",cost));
                set1.getData().add(new XYChart.Data("Net Profit",net));
                incomechart.getData().addAll(set1);   
                
                         
                //incomechart.getData().remove(0);
                //incomechart.getData().clearValues();
            
            }
             
            
            
        
    }
        
    /* get the total costs */
    
    public double getTotalCost()
    {
        double cs = 0;
        
        LocalDate d1 =sdate.getValue();
        LocalDate d2 =edate.getValue();
        
        Date sd = java.sql.Date.valueOf(d1);
        Date ed = java.sql.Date.valueOf(d2);  
        
        String q = "SELECT SUM(Amount) FROM finance WHERE Type IN(?,?,?,?,?) AND Date BETWEEN ? AND ? ";
          
        try{                
            pst = con.prepareStatement(q);
            pst.setString(1, "Employee");
            pst.setString(2, "Supplier");
            pst.setString(3, "TransportTurn");
            pst.setString(4, "Asset");
            pst.setString(5, "VehicleMaintenance");
            pst.setDate(6,sd);
            pst.setDate(7,ed);
            rs =pst.executeQuery();
            
            
            while(rs.next())
                {           
                    cs = rs.getDouble("SUM(Amount)");     
                    String cs1 = new Double(cs).toString();
                    tc.setText(cs1);

                }
        }catch(SQLException ex){
           Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
           
        }
        
               
        return cs;
    }
    
    public double getTotalIncome()
    {
       
        double Income = 0;
        
        LocalDate d1 =sdate.getValue();
        LocalDate d2 =edate.getValue();
        
        Date sd = java.sql.Date.valueOf(d1);
        Date ed = java.sql.Date.valueOf(d2);  
        
        String q = "SELECT SUM(Amount) FROM finance WHERE Type IN(?,?) AND Date BETWEEN ? AND ? ";
          
        try{                
            pst = con.prepareStatement(q);
            pst.setString(1, "Sales");
            pst.setString(2, "Wastage");  
            pst.setDate(3,sd);
            pst.setDate(4,ed);
            rs =pst.executeQuery();
            
            while(rs.next())
                {           
                    Income = rs.getDouble("SUM(Amount)");     
                    String In1 = new Double(Income).toString();
                    in.setText(In1);

                }
        }catch(SQLException ex){
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return Income;
    }

    @FXML
    private void GenerateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/finance.jrxml").getAbsolutePath();
        try{
            String is = path;
            JasperReport jr =JasperCompileManager.compileReport(is);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,con);
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
 
}

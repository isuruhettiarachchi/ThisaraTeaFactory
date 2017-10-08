/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.finalizing;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import thisarateafactory.ui.Sales.dao.FinalizingDAO;
import thisarateafactory.ui.Sales.database.DBHandler;
import java.net.URL;
import java.sql.Connection;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.Sales.ui.Utils;

/**
 * FXML Controller class
 *
 * @author Mihitha Hansani
 */
public class FinalizingController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnCalculate;
    @FXML
    private DatePicker dateFinalization;
    @FXML
    private TextField txtLotNumber;
    @FXML
    private TextField txtInvoiceNumber;
    @FXML
    private TextField txtTotalGrossProceed;
    @FXML
    private JFXComboBox<String> cmbMonth;
    @FXML
    private TextField txtDeduction;
    @FXML
    private TextField txtNetProceed;
    
    @FXML private TableView<FinalizingTableModel> tblFinalizing;
    @FXML private TableColumn<FinalizingTableModel, String> clmDate;
    @FXML private TableColumn<FinalizingTableModel, String> clmTotalNetProceed;
    @FXML private TableColumn<FinalizingTableModel, String> clmTotalDeduction;
    @FXML private TableColumn<FinalizingTableModel, String> clmTotalGrossProceed;
    @FXML private TableColumn<FinalizingTableModel, String> clmMonth;
    @FXML private TableColumn<FinalizingTableModel, String> clmLotNo;

    @FXML
    private JFXButton btnDisplayTotalGrossProceed;
    @FXML
    private JFXButton btnDisplayTotalDeduction;
    @FXML
    private VBox txtDate;
    @FXML
    private JFXTextField txtDate1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onWindowShownEvent();
        loadData();
        
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmTotalNetProceed.setCellValueFactory(new PropertyValueFactory<>("netProceed"));
        clmTotalDeduction.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        clmTotalGrossProceed.setCellValueFactory(new PropertyValueFactory<>("grossProceed"));
        clmMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        clmLotNo.setCellValueFactory(new PropertyValueFactory<>("lotNo"));
        setCellValueFromTableToTextField();
    }    

    private FinalizingDAO generateDAO( boolean isCalculate )
    {
        int lotNumber = 0;
        int invoiceNumber = 0;
        double totalGross = 0;
        double totalDeduction = 0;
        double totalNet = 0;
        Date finalizeDate;
        String month = "";
    
        try
        {
            lotNumber = Integer.parseInt( txtLotNumber.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Lot Number", "Please specify the lot number");
            return null;
        }
        
        try
        {
            invoiceNumber = Integer.parseInt( txtInvoiceNumber.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Invoice Number", "Please specify the invoice number");
            return null;
        }
                
        try
        {
            totalGross = Double.parseDouble( txtTotalGrossProceed.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the total gross");
            return null;
        }
        
        try
        {
            totalDeduction = Double.parseDouble( txtDeduction.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Deduction", "Please specify the total deduction");
            return null;
        }
        
        try
        {
            totalNet = Double.parseDouble( txtNetProceed.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Total Net", "Please calculate the total net before proceed");
                return null;
            }
            else
            {
                totalNet = 0;
            }

        }
       
        month = cmbMonth.getSelectionModel().getSelectedItem();
        
        if (( month == null ) || ( month.isEmpty() ))
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Month", "Please select the month");
                return null;
            }
        }

        LocalDate localDate = dateFinalization.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        finalizeDate = Date.from(instant);
        
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //CurrentDate currentDate = CurrentDate.now();

        FinalizingDAO finalizingDAO = new FinalizingDAO();
        finalizingDAO.setInvoiceNumber(invoiceNumber);
        finalizingDAO.setLotNumber(lotNumber);
        finalizingDAO.setMonth(month);
        finalizingDAO.setFinalizeDate(finalizeDate);
        finalizingDAO.setTotalGross(totalGross);
        finalizingDAO.setTotalDeduction(totalDeduction);
        finalizingDAO.setTotalNet(totalNet);
        
        return finalizingDAO;
    }
    
    private void setCellValueFromTableToTextField() //mouse click 
    {
            tblFinalizing.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            FinalizingTableModel f1 = tblFinalizing.getItems().get(tblFinalizing.getSelectionModel().getSelectedIndex());
            
            txtLotNumber.setText(f1.getLotNo());
            
            LocalDate localDate = LocalDate.parse(f1.getDate());
            dateFinalization.setValue(localDate);
            
            txtNetProceed.setText(f1.getNetProceed());
            txtDeduction.setText(f1.getDeduction());
            cmbMonth.getSelectionModel().select(f1.getMonth());
            txtTotalGrossProceed.setText(f1.getGrossProceed());
            
        });
    }
    

    @FXML
    private void addButtonPressed(ActionEvent event) {
        FinalizingDAO finalizingDAO = generateDAO( false );
        if ( finalizingDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.insert( finalizingDAO );
            //insert into transaction table
            //String result2 = dBHandler.insert( finalizingDAO );
            
            if ( result == null )
            {
                Utils.showAlert(AlertType.INFORMATION, "Success", "Record Added", "Finalizing Data Added");
                loadData();
            }
            else
            {
                Utils.showAlert(AlertType.ERROR, "DB Error", "Fail to add record", result);
            }
        }
    }

    private void updateButtonPressed(ActionEvent event) {
        FinalizingDAO finalizingDAO = generateDAO( false );
        if ( finalizingDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.update(finalizingDAO );
            
            if ( result == null )
            {
                Utils.showAlert(AlertType.INFORMATION, "Success", "Record Updated", "Finalizing Data Updated");
                loadData();
            }
            else
            {
                Utils.showAlert(AlertType.ERROR, "DB Error", "Fail to update record", result);
            }
        }
    }

    @FXML
    private void deleteButtonPressed(ActionEvent event) 
    {
        Optional<ButtonType> confirmResult = Utils.showAlert(AlertType.CONFIRMATION, "Confirmation",
                "Delete Finalizing Record", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            //FinalizingDAO finalizingDAO = generateDAO( false );
              FinalizingDAO finalizingDAO = new FinalizingDAO();
              finalizingDAO.setLotNumber(Integer.parseInt( txtLotNumber.getText()));
              //finalizingDAO.setInvoiceNumber(Integer.parseInt( txtInvoiceNumber.getText()));
            if ( finalizingDAO != null )
            {
                DBHandler dBHandler = DBHandler.getInstance();
                String result = dBHandler.delete( finalizingDAO );

                if ( result == null )
                {
                    Utils.showAlert(AlertType.INFORMATION, "Success", "Record Deleted", "Finalizing Data Deleted");
                    loadData();
                }
                else
                {
                    Utils.showAlert(AlertType.ERROR, "DB Error", "Fail to delete record", result);
                }
            }
        }
    }

    @FXML
    private void clearButtonPressed(ActionEvent event) {
        Optional<ButtonType> confirmResult = Utils.showAlert(AlertType.CONFIRMATION, "Confirmation",
                "Clear Finalizing Details", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            txtLotNumber.clear();
            txtInvoiceNumber.clear();
            txtTotalGrossProceed.clear();
            cmbMonth.getSelectionModel().clearSelection();
            txtDeduction.clear();
            txtNetProceed.clear();
            dateFinalization.setValue( LocalDate.now());
        }
    }
    
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        FinalizingDAO finalizingDAO = generateDAO( true );
        if ( finalizingDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            //change
            double result = dBHandler.getLatestTotalGrossProceed() 
                    - dBHandler.getLatestTotalDeduction();
            finalizingDAO.setTotalNet(Utils.round( result, 2) );
            
            txtNetProceed.setText( finalizingDAO.getTotalNet()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "Net Proceed Calculated", "");
        }
        
    }
    
    public void onWindowShownEvent()
    {
        String[] monthList = {"January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"};
        cmbMonth.getItems().addAll(monthList);
        
        dateFinalization.setValue(LocalDate.now());
        dateFinalization.setDisable(true);
        
        loadData();
    }
    
    private void loadData()
    {
        DBHandler dBHandler = DBHandler.getInstance();
        List<FinalizingDAO> recordList = dBHandler.getAllFinalizingRecords();
        
        if ( recordList != null )
        {
             ObservableList<FinalizingTableModel> data = FXCollections.observableArrayList();
            for ( FinalizingDAO dao : recordList )
            {
                data.add( new FinalizingTableModel(dao) );
            }
        
            tblFinalizing.setItems(data);
        }
        //DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //LocalDate localDate = LocalDate.now();
        
    }

    @FXML//get a lastly added entry of sales table colum Total gross proceed and it display in this interface total gross textfield when this button pressed.
    private void onTotalGrossProceedPressed(ActionEvent event)
    { 
        DBHandler dBHandler = DBHandler.getInstance();
        double result = dBHandler.getLatestTotalGrossProceed();
        txtTotalGrossProceed.setText(result + "");    
    }

    @FXML//same as earlier one. here get total deduction from deduction interfce income table.
    private void onTotalDeductionButtonPressed(ActionEvent event)
    {
        DBHandler dBHandler = DBHandler.getInstance();
        double result = dBHandler.getLatestTotalDeduction();
        txtDeduction.setText(result + ""); 
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String basePath = new File("").getAbsolutePath();
        Connection con = databaseHandler.getConnection();
        String path = new File("src/thisarateafactory/reports/finalizing.jrxml").getAbsolutePath();
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

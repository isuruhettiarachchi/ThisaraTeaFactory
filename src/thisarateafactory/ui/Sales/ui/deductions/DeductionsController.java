/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.deductions;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXComboBox;
import thisarateafactory.ui.Sales.dao.DeductionDAO;
import thisarateafactory.ui.Sales.database.DBHandler;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import thisarateafactory.ui.Sales.ui.Utils;

/**
 * FXML Controller class
 *
 * @author Mihitha Hansani
 */
public class DeductionsController implements Initializable {

    @FXML
    private JFXComboBox<String> txtLotNo;
    @FXML
    private JFXComboBox<String> txtInvoiceNo;
    @FXML
    private JFXTextField txtNoOfLots;
    @FXML
    private JFXTextField txtChamberChg;
    @FXML
    private JFXTextField txtBrokerage;
    @FXML
    private JFXTextField txtPSE;
    @FXML
    private JFXTextField txtVAT;
    @FXML
    private JFXTextField txtHChg;
    @FXML
    private JFXTextField txtFurtherins;
    @FXML
    private JFXButton btnCalculateDeduction;
    @FXML
    private JFXTextField txtTotalDeduction;
    @FXML
    private TableView<DeductionsTableModel> tblDeduction;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmLotNo;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmDate;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmNoOfLots;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmPSE;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmHChg;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmChamberChg;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmVAT;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmBrokerage;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmFurtherIns;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmTotalDeduction;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnCalculate;
    @FXML
    private JFXComboBox<String> cmbMonth;
    @FXML
    private JFXTextField txtYear;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmMonth;
    @FXML
    private TableColumn<DeductionsTableModel,String> clmYear;
    @FXML
    private DatePicker dateDeduction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onWindowShownEvent();//cl when panel is open and it is the one for load data to db from ui
        
        clmLotNo.setCellValueFactory(new PropertyValueFactory<>("lotNo"));
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmNoOfLots.setCellValueFactory(new PropertyValueFactory<>("noOfLots"));
        clmPSE.setCellValueFactory(new PropertyValueFactory<>("PSE"));
        clmHChg.setCellValueFactory(new PropertyValueFactory<>("HChg"));
        clmChamberChg.setCellValueFactory(new PropertyValueFactory<>("chamberChg"));
        clmVAT.setCellValueFactory(new PropertyValueFactory<>("VAT"));
        clmBrokerage.setCellValueFactory(new PropertyValueFactory<>("brokerage"));
        clmFurtherIns.setCellValueFactory(new PropertyValueFactory<>("furtherIns"));
        clmTotalDeduction.setCellValueFactory(new PropertyValueFactory<>("totalDeduction"));
        clmMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        clmYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        setCellValueFromTableToTextField();
    }    

    private DeductionDAO generateDAO( boolean isCalculate )
    {
        int lotNo;
        int invoiceNumber;
        Date date;
        int noOfLots;
        double PSE;
        double HChg;
        double chamberChg;
        double VAT;
        double brokerage;
        double furtherIns;
        double totalDeduction;
        String month;
        int year;
    
        try
        {
            lotNo = Integer.parseInt( txtLotNo.getValue() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Lot Number", "Please specify the lot number");
            return null;
        }
        
        try
        {
            invoiceNumber = Integer.parseInt( txtInvoiceNo.getValue() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid InvoiceNo", "Please specify the InvoiceNo");
            return null;
        }
                
        try
        {
            noOfLots = Integer.parseInt( txtNoOfLots.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid NoOfLots", "Please calculate Number of Lots");
                return null;
            }
            else
            {
                noOfLots = 0;
            }
        }
        
        try
        {
            PSE = Double.parseDouble(txtPSE.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid PSE", "Please calculate PSE");
                return null;
            }
            else
            {
                PSE = 0;
            }
        }
          
        try
        {
            HChg = Double.parseDouble(txtHChg.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid HChg", "Please calculate HChg");
                return null;
            }
            else
            {
                HChg = 0;
            }
        }
        
        try
        {
            chamberChg = Double.parseDouble(txtChamberChg.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid chamberChg", "Please calculate chamberChg");
                return null;
            }
            else
            {
                chamberChg = 0;
            }
        }
                
        try
        {
            VAT = Double.parseDouble(txtVAT.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid VAT", "Please calculate VAT");
                return null;
            }
            else
            {
                VAT = 0;
            }
        }
                        
        try
        {
            brokerage = Double.parseDouble(txtBrokerage.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Brokerage", "Please calculate Brokerage");
                return null;
            }
            else
            {
                brokerage = 0;
            }
        }
                                
        try
        {
            furtherIns = Double.parseDouble(txtFurtherins.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid FurtherIns", "Please calculate FurtherIns");
                return null;
            }
            else
            {
                furtherIns = 0;
            }
        }
                                        
        try
        {
            totalDeduction = Double.parseDouble(txtTotalDeduction.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Total Deduction", "Please calculate Total Deduction");
                return null;
            }
            else
            {
                totalDeduction = 0;
            }
        }
        
        try
        {
            year = Integer.parseInt(txtYear.getText() );
            if (( !isCalculate ) && ((year < 1900 ) || (year > 2100)))
            {
                Utils.showValidationAlert("Invalid Year", "Please enter the year correctly");
                return null;
            }
        }
        catch ( NumberFormatException ex )
        {
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Year", "Please enter the year correctly");
                return null;
            }
            else
            {
               year = 0;
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
        
        //get cuttent date    
        //LocalDate localDate = dateFinalization.getValue();
        //Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        //date = Date.from(instant);
        
        LocalDate localDate = dateDeduction.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);

        DeductionDAO deductionDAO = new DeductionDAO();
        deductionDAO.setLotNo(lotNo);
        deductionDAO.setInvoiceNumber(invoiceNumber);
        deductionDAO.setNoOfLots(noOfLots);
        deductionDAO.setPSE(PSE);
        deductionDAO.setHChg(HChg);
        deductionDAO.setChamberChg(chamberChg);
        deductionDAO.setVAT(VAT);
        deductionDAO.setBrokerage(brokerage);
        deductionDAO.setFurtherIns(furtherIns);
        deductionDAO.setTotalDeduction(totalDeduction);
        deductionDAO.setDate(date);
        
        //deductionDAO.setDate( new Date());
        deductionDAO.setMonth(month);
        deductionDAO.setYear(year);
        return deductionDAO;
    }
    
    @FXML
    private void calculateDeductionButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            double total = deductionDAO.getPSE() + deductionDAO.getVAT() + deductionDAO.getHChg() 
                    + deductionDAO.getBrokerage() + deductionDAO.getChamberChg() + deductionDAO.getFurtherIns();
            deductionDAO.setTotalDeduction( Utils.round( total, 2 ) );
            txtTotalDeduction.setText( deductionDAO.getTotalDeduction()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "Deduction Calculated", "");
        }
    }

    /*private void calculateLotNoButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            int result = dBHandler.getTotalLotNumber();
            deductionDAO.setNoOfLots(result);
            
            txtNoOfLots.setText( deductionDAO.getNoOfLots()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "No of Lots Calculated", "");
        }
    }

    @FXML
    private void calculateChamberChgButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            double result = 36.77 * deductionDAO.getNoOfLots();
            deductionDAO.setChamberChg( Utils.round( result, 2 ) );
            txtChamberChg.setText( deductionDAO.getChamberChg()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "ChamberChg Calculated", "");
        }
    }

    @FXML
    private void calculateBrokerageButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            double result = dBHandler.getTotalGrossProceed( deductionDAO ) * 0.01;
            deductionDAO.setBrokerage( Utils.round( result, 2) );
            
            txtBrokerage.setText( deductionDAO.getBrokerage()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "Brokerage Calculated", "");
        }
    }

    @FXML
    private void calculateCalcpseButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            double result = 73.54 * deductionDAO.getNoOfLots();
            deductionDAO.setPSE( Utils.round( result, 2 ) );
            txtPSE.setText( deductionDAO.getPSE()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "PSE Calculated", "");
        }
    }

    @FXML
    private void calculateVATButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            double vat = ( deductionDAO.getPSE() + deductionDAO.getHChg() 
                    + deductionDAO.getBrokerage() + deductionDAO.getChamberChg() ) * 0.15;
            deductionDAO.setVAT( Utils.round(vat, 2) );
            txtVAT.setText( deductionDAO.getVAT()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "VAT Calculated", "");
        }
    }

    @FXML
    private void calculateFurtherinsButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            double result = 0.06 * dBHandler.getTotalNetWeight( deductionDAO );
            deductionDAO.setFurtherIns(Utils.round( result, 2) );
            
            txtFurtherins.setText( deductionDAO.getFurtherIns()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "FurtherIns Calculated", "");
        }
    }

    @FXML
    private void calculateHChgButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            double result = 2.10 * dBHandler.getTotalNetWeight( deductionDAO );
            deductionDAO.setHChg( Utils.round( result, 2) );
            
            txtHChg.setText( deductionDAO.getHChg()+ "");
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "HChg Calculated", "");
        }
    }*/
    
    
     private void setCellValueFromTableToTextField() //mouse click event
    {
            tblDeduction.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            DeductionsTableModel d1 = tblDeduction.getItems().get(tblDeduction.getSelectionModel().getSelectedIndex());
            
            txtLotNo.setValue(d1.getLotNo());
            txtNoOfLots.setText(d1.getNoOfLots());
            
            LocalDate localDate = LocalDate.parse(d1.getDate());
            dateDeduction.setValue(localDate); 
            
            txtChamberChg.setText(d1.getChamberChg());
            txtBrokerage.setText(d1.getBrokerage());
            txtPSE.setText(d1.getPSE());
            txtVAT.setText(d1.getVAT());
            txtHChg.setText(d1.getHChg());
            txtFurtherins.setText(d1.getFurtherIns());
            txtTotalDeduction.setText(d1.getTotalDeduction());
            cmbMonth.getSelectionModel().select(d1.getMonth());
            txtYear.setText(d1.getYear());
            
        });
    }

    @FXML
    private void addButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( false );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.insert( deductionDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Added", "Deduction Data Added");
                loadData();
            }
            else
            {
                Utils.showAlert(Alert.AlertType.ERROR, "DB Error", "Fail to add record", result);
            }
        }
    }

    private void updateButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( false );
        if ( deductionDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.update(deductionDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Updated", "Deduction Data Updated");
                loadData();
            }
            else
            {
                Utils.showAlert(Alert.AlertType.ERROR, "DB Error", "Fail to update record", result);
            }
        }
    }

    @FXML
    private void deleteButtonPressed(ActionEvent event) 
    {
        Optional<ButtonType> confirmResult = Utils.showAlert(Alert.AlertType.CONFIRMATION, "Confirmation",
                "Delete Deduction Record", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            //DeductionDAO deductionDAO = generateDAO( false );
              DeductionDAO deductionDAO = new DeductionDAO();
              deductionDAO.setLotNo(Integer.parseInt( txtLotNo.getValue()));
            if ( deductionDAO != null )
            {
                DBHandler dBHandler = DBHandler.getInstance();
                String result = dBHandler.delete( deductionDAO );

                if ( result == null )
                {
                    Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Deleted", "Deduction Data Deleted");
                    loadData();
                }
                else
                {
                    Utils.showAlert(Alert.AlertType.ERROR, "DB Error", "Fail to delete record", result);
                }
            }
        }
    }

    @FXML
    private void cancelButtonPressed(ActionEvent event) {
        Optional<ButtonType> confirmResult = Utils.showAlert(Alert.AlertType.CONFIRMATION, "Confirmation",
                "Clear Deduction Details", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            txtLotNo.getSelectionModel().clearSelection();
            txtInvoiceNo.getSelectionModel().clearSelection();
            dateDeduction.setValue(null);
            txtNoOfLots.clear();
            txtChamberChg.clear();
            txtBrokerage.clear();
            txtPSE.clear();
            txtVAT.clear();
            txtHChg.clear();
            txtFurtherins.clear();
            txtTotalDeduction.clear();
            cmbMonth.getSelectionModel().clearSelection();
            txtYear.clear();  
        }
    }
    
    public void onWindowShownEvent()
    {
        String[] monthList = {"January", "February", "March", "April", "May", "June",
                        "July", "August", "September", "October", "November", "December"};
        cmbMonth.getItems().addAll(monthList);
        
        loadData();
        
        DBHandler dBHandler = DBHandler.getInstance();
        List<String> LotNoList = dBHandler.getLotNoList();
        txtLotNo.getItems().addAll(LotNoList);
        
        DBHandler dBHandlerInvoice = DBHandler.getInstance();
        List<String> InvoiceNoList = dBHandler.getInvoiceNoList();
        txtInvoiceNo.getItems().addAll(InvoiceNoList);
    }
    
    private void loadData()//load data fron database to interface
    {
        DBHandler dBHandler = DBHandler.getInstance();
        List<DeductionDAO> recordList = dBHandler.getAllDeductionRecords();
        
        if ( recordList != null )
        {
             ObservableList<DeductionsTableModel> data = FXCollections.observableArrayList();
            for ( DeductionDAO dao : recordList )
            {
                data.add( new DeductionsTableModel(dao) );
            }
        
            tblDeduction.setItems(data);
        }
    }  

    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        DeductionDAO deductionDAO = generateDAO( true );
        if ( deductionDAO != null )
        {
            StringBuilder infoMessage = new StringBuilder();
            
            DBHandler dBHandler = DBHandler.getInstance();
            int resultL = dBHandler.getTotalLotNumber(deductionDAO);
            deductionDAO.setNoOfLots(resultL);
            txtNoOfLots.setText( deductionDAO.getNoOfLots()+ "");
            infoMessage.append("No of Lots").append(", ");

            double chgResult = 36.77 * deductionDAO.getNoOfLots();
            deductionDAO.setChamberChg( Utils.round( chgResult, 2 ) );
            txtChamberChg.setText( deductionDAO.getChamberChg()+ "");
            infoMessage.append("ChamberChg").append(", ");

            double result = dBHandler.getTotalGrossProceed( deductionDAO ) * 0.01;
            deductionDAO.setBrokerage( Utils.round( result, 2) );
            txtBrokerage.setText( deductionDAO.getBrokerage()+ "");
            infoMessage.append("Brokerage").append(", ");

            result = 73.54 * deductionDAO.getNoOfLots();
            deductionDAO.setPSE( Utils.round( result, 2 ) );
            txtPSE.setText( deductionDAO.getPSE()+ "");
            infoMessage.append("PSE").append(", ");

            double vat = ( deductionDAO.getPSE() + deductionDAO.getHChg() 
                    + deductionDAO.getBrokerage() + deductionDAO.getChamberChg() ) * 0.15;
            deductionDAO.setVAT( Utils.round(vat, 2) );
            txtVAT.setText( deductionDAO.getVAT()+ "");
            infoMessage.append("VAT").append(", ");

            result = 0.06 * dBHandler.getTotalNetWeight( deductionDAO );
            deductionDAO.setFurtherIns(Utils.round( result, 2) );
            txtFurtherins.setText( deductionDAO.getFurtherIns()+ "");
            infoMessage.append("FurtherIns").append(", ");

            result = 2.10 * dBHandler.getTotalNetWeight( deductionDAO );
            deductionDAO.setHChg( Utils.round( result, 2) );
            txtHChg.setText( deductionDAO.getHChg()+ "");
            infoMessage.append("HChg").append(" ");
                    
            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculation Complete", 
                    "Following values were calculated succesfully", infoMessage.toString());
        }
    }

}

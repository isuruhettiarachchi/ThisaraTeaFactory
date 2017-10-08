/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.salessummary;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import thisarateafactory.ui.Sales.dao.SalesSummaryDAO;
import thisarateafactory.ui.Sales.database.DBHandler;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
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
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author Isuru Hettiarachchi
 */
public class SalesSummaryController implements Initializable {

    @FXML
    private JFXTextField txtLotNo;
    @FXML
    private JFXComboBox<String> txtInvoiceNo;
    @FXML
    private JFXTextArea txtBuyerDetails;
    @FXML
    private JFXTextField txtNetWeight;
    @FXML
    private JFXTextField txtGrossWeight;
    @FXML
    private JFXComboBox<String> txtGrade;
    @FXML
    private JFXTextField txtChest;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXTextField txtSampleWeight;
    @FXML
    private TableView<SalesSummaryTableModel> tblSalesSummary;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmLotNo;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmInvoiceNo;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmBuyerDetails;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmGrade;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmChest;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmPrice;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmGrossWeight;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmSampleweight;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmNetWeight;
    @FXML
    private TableColumn<SalesSummaryTableModel, String> clmGrossProceed;
    @FXML
    private JFXButton btnCalculate;
    @FXML
    private JFXTextField txtTotGrossWeight;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnCancel;
    @FXML
    private JFXButton btnNew;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onWindowShownEvent();
        
        clmLotNo.setCellValueFactory(new PropertyValueFactory<>("lotNo"));
        clmInvoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        clmBuyerDetails.setCellValueFactory(new PropertyValueFactory<>("buyerDetails"));
        clmGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        clmChest.setCellValueFactory(new PropertyValueFactory<>("chest"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        clmGrossWeight.setCellValueFactory(new PropertyValueFactory<>("grossWeight"));
        clmSampleweight.setCellValueFactory(new PropertyValueFactory<>("sampleWeight"));
        clmNetWeight.setCellValueFactory(new PropertyValueFactory<>("netWeight"));
        clmGrossProceed.setCellValueFactory(new PropertyValueFactory<>("grossProceed"));
        setCellValueFromTableToTextField();
    }    

    private SalesSummaryDAO generateDAO( boolean isCalculate )
    {
        int lotNumber = 0;
        int invoiceNumber = 0;
        String buyerDetails = "";
        double netWeight = 0;
        double grossWeight = 0;
        String grade = "";
        int chest = 0;
        double price = 0;
        double sampleWeight = 0;
        double totGrossWeight = 0;

        try
        {
            lotNumber = Integer.parseInt( txtLotNo.getText() );
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
            Utils.showValidationAlert("Invalid Invoice Number", "Please specify the invoice number");
            return null;
        }
        
        buyerDetails = txtBuyerDetails.getText();   
        grade = txtGrade.getSelectionModel().getSelectedItem();
        if ((grade == null ) || ( grade.trim().isEmpty()))
        {
            Utils.showValidationAlert("Invalid Grade", "Please specify the grade");
            return null;
        }
        
        try
        {
            netWeight = Double.parseDouble( txtNetWeight.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the net weight");
            return null;
        }

        try
        {
            grossWeight = Double.parseDouble( txtGrossWeight.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the gross weight");
            return null;
        }
        
        try
        {
            price = Double.parseDouble( txtPrice.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the price");
            return null;
        }
        
        try
        {
            sampleWeight = Double.parseDouble( txtSampleWeight.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the sample weight");
            return null;
        }
        
        try
        {
            chest = Integer.parseInt(txtChest.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the chest");
            return null;
        }
        
        try
        {
            totGrossWeight = Double.parseDouble( txtTotGrossWeight.getText() );
        }
        catch ( NumberFormatException ex )
        {
            if(grossWeight != netWeight )
        {
             Utils.showValidationAlert("Invalid GrossWeight or NetWeight", "NetWeight and GrossWeight Should be equal");
                return null;
        }
            if ( !isCalculate )
            {
                Utils.showValidationAlert("Invalid Total Net", "Please calculate the total Gross Weight before proceed");
                return null;
                
            }
            else
            {
                totGrossWeight = 0;
            }

        }
                
        SalesSummaryDAO salesSummaryDAO = new SalesSummaryDAO();
        salesSummaryDAO.setInvoiceNumber(invoiceNumber);
        salesSummaryDAO.setLotNumber(lotNumber);
        salesSummaryDAO.setBuyerDetails(buyerDetails);
        salesSummaryDAO.setNetWeight(netWeight);
        salesSummaryDAO.setGrossWeight(grossWeight);
        salesSummaryDAO.setGrade(grade);
        salesSummaryDAO.setChest(chest);
        salesSummaryDAO.setPrice(price);
        salesSummaryDAO.setSampleWeight(sampleWeight);
        salesSummaryDAO.setGrossProceed(totGrossWeight);

       
        return salesSummaryDAO;
    }
    
    
   
     private void setCellValueFromTableToTextField() //mouse click 
    {
            tblSalesSummary.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            SalesSummaryTableModel s1 = tblSalesSummary.getItems().get(tblSalesSummary.getSelectionModel().getSelectedIndex());
            
            txtLotNo.setText(s1.getLotNo());
            txtInvoiceNo.setValue(s1.getInvoiceNo());
            txtBuyerDetails.setText(s1.getBuyerDetails());
            txtNetWeight.setText(s1.getNetWeight());
            txtGrossWeight.setText(s1.getGrossWeight());
            txtGrade.setValue(s1.getGrade());
            txtChest.setText(s1.getChest());
            txtPrice.setText(s1.getPrice());
            txtSampleWeight.setText(s1.getSampleWeight());
            
        });
    }
    
    @FXML
    private void addButtonPressed(ActionEvent event) {
        SalesSummaryDAO salesSummaryDAO = generateDAO( false );
        
        if ( salesSummaryDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.insert( salesSummaryDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Added", "Sales Summary Data Added");
                loadData();
            }
            else
            {
                Utils.showAlert(Alert.AlertType.ERROR, "DB Error", "Fail to add record", result);
            }
        }
    }
    

    @FXML
    private void updateButtonPressed(ActionEvent event) {
        SalesSummaryDAO salesSummaryDAO = generateDAO( false );
        if ( salesSummaryDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.update(salesSummaryDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Updated", "Sales Summary Data Updated");
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
                "Delete Sales Summary Record", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            //SalesSummaryDAO salesSummaryDAO = generateDAO( false );
              SalesSummaryDAO salesSummaryDAO = new SalesSummaryDAO(); 
              salesSummaryDAO.setLotNumber(Integer.parseInt( txtLotNo.getText() ));
            if ( salesSummaryDAO != null )
            {
                DBHandler dBHandler = DBHandler.getInstance();
                String result = dBHandler.delete( salesSummaryDAO );

                if ( result == null )
                {
                    Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Deleted", "Sales Summary Data Deleted");
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
    private void clearButtonPressed(ActionEvent event) {
        Optional<ButtonType> confirmResult = Utils.showAlert(Alert.AlertType.CONFIRMATION, "Confirmation",
                "Clear Sales Summary Details", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            txtLotNo.clear();
            txtInvoiceNo.getSelectionModel().clearSelection();
            txtBuyerDetails.clear();
            txtNetWeight.clear();
            txtGrossWeight.clear();
            txtPrice.clear();
            txtChest.clear();
            txtGrade.getSelectionModel().clearSelection();
            txtSampleWeight.clear();
            txtTotGrossWeight.clear();
        }
    }
    
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        SalesSummaryDAO salesSummaryDAO = generateDAO( true );
        if ( salesSummaryDAO != null )
        {
            calculateNet( salesSummaryDAO );
            txtTotGrossWeight.setText( salesSummaryDAO.getGrossProceed()+ "");

            Utils.showAlert(Alert.AlertType.INFORMATION, "Calculated", "Total Gross Proceed Calculated", "");
        }
    }

    private void calculateNet(SalesSummaryDAO salesSummaryDAO) {
        double totalGrossProceed = salesSummaryDAO.getNetWeight() * salesSummaryDAO.getPrice();
        salesSummaryDAO.setGrossProceed(totalGrossProceed );
    }
    
    public void onWindowShownEvent()
    {
        loadData();
        
        DBHandler dBHandler = DBHandler.getInstance();
        List<String> gradeList = dBHandler.getGradeList();
        txtGrade.getItems().addAll(gradeList);
        
        DBHandler dBHandlerInvoice = DBHandler.getInstance();
        List<String> InvoiceNoList = dBHandler.getInvoiceNoList();
        txtInvoiceNo.getItems().addAll(InvoiceNoList);
    }
    
    
    private void loadData()
    {
        DBHandler dBHandler = DBHandler.getInstance();
        List<SalesSummaryDAO> recordList = dBHandler.getAllSalesSummaryRecords();
        
        if ( recordList != null )
        {
             ObservableList<SalesSummaryTableModel> data = FXCollections.observableArrayList();
            for ( SalesSummaryDAO dao : recordList )
            {
                data.add( new SalesSummaryTableModel(dao) );
            }
        
            tblSalesSummary.setItems(data);
        }
    }
    
    

    @FXML
    private void newButtonPressed(ActionEvent event) {
        // Clear the UI
        txtLotNo.clear();
        //txtInvoiceNo.clear();
        txtBuyerDetails.clear();
        txtNetWeight.clear();
        txtGrossWeight.clear();
        txtPrice.clear();
        txtChest.clear();
        txtGrade.getSelectionModel().clearSelection();
        txtSampleWeight.clear();
        txtTotGrossWeight.clear();
        
        
        DBHandler dBHandler = DBHandler.getInstance();
        int lotNumber = dBHandler.generateNextLotNo();
        txtLotNo.setText(lotNumber + "");
    }   

    @FXML
    private void generateReport(ActionEvent event) {
        String basePath = new File("").getAbsolutePath();
        Connection con = databaseHandler.getConnection();
        String path = new File("src/thisarateafactory/reports/sales.jrxml").getAbsolutePath();
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

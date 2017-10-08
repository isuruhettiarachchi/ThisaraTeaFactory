/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.returnsale;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import thisarateafactory.ui.Sales.dao.ReturnSalesDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import thisarateafactory.ui.Sales.ui.Utils;

/**
 * FXML Controller class
 *
 * @author Isuru Hettiarachchi
 */
public class ReturnSalesController implements Initializable {

    @FXML
    private JFXComboBox<String> txtLotNo;
    @FXML
    private JFXComboBox<String> txtInvoiceNo;
    @FXML
    private DatePicker dateReturn;
    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private JFXTextField txtWeight;
    @FXML
    private TableView<ReturnSalesTableModel> tblRetuenSales;
    @FXML
    private TableColumn<ReturnSalesTableModel,String> clmDate;
    @FXML
    private TableColumn<ReturnSalesTableModel,String> clmLotNo;
    @FXML
    private TableColumn<ReturnSalesTableModel,String> clmInvoiceNo;
    @FXML
    private TableColumn<ReturnSalesTableModel,String> clmDescription;
    @FXML
    private TableColumn<ReturnSalesTableModel,String> clmWeight;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnClear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        onWindowShownEvent();
        
        clmDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        clmLotNo.setCellValueFactory(new PropertyValueFactory<>("lotNo"));
        clmInvoiceNo.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        clmWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        setCellValueFromTableToTextField();
    }    

    private ReturnSalesDAO generateDAO( )
    {
        int lotNumber = 0;
        int invoiceNumber = 0;
        double weight = 0;
        String description = "";
        Date date;
    
        try
        {
            lotNumber = Integer.parseInt( txtLotNo.getValue() );
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
                
        try
        {
            weight = Double.parseDouble( txtWeight.getText() );
        }
        catch ( NumberFormatException ex )
        {
            Utils.showValidationAlert("Invalid Total Gross", "Please specify the weight");
            return null;
        }
        description = txtDescription.getText();
        
        LocalDate localDate = dateReturn.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        date = Date.from(instant);

        ReturnSalesDAO returnSalesDAO = new ReturnSalesDAO();
        returnSalesDAO.setInvoiceNumber(invoiceNumber);
        returnSalesDAO.setLotNumber(lotNumber);
        returnSalesDAO.setDate(date);
        returnSalesDAO.setWeight(weight);
        returnSalesDAO.setDescription(description);
       
        return returnSalesDAO;
    }
    
    
    private void setCellValueFromTableToTextField() //mouse click 
    {
            tblRetuenSales.setOnMouseClicked((javafx.scene.input.MouseEvent event) -> {
            ReturnSalesTableModel r1 = tblRetuenSales.getItems().get(tblRetuenSales.getSelectionModel().getSelectedIndex());
            
            txtLotNo.setValue(r1.getLotNo());
            txtInvoiceNo.setValue(r1.getInvoiceNo());
            //dateReturn.setText(r1.getDate());
            txtDescription.setText(r1.getDescription());
            txtWeight.setText(r1.getWeight());
            
        });
    }
    
    
    
    @FXML
    private void addButtonPressed(ActionEvent event) {
        ReturnSalesDAO returnSalesDAO = generateDAO( );
        if ( returnSalesDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.insert( returnSalesDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Added", "Return Sales Data Added");
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
        ReturnSalesDAO returnSalesDAO = generateDAO( );
        if ( returnSalesDAO != null )
        {
            DBHandler dBHandler = DBHandler.getInstance();
            String result = dBHandler.update(returnSalesDAO );
            
            if ( result == null )
            {
                Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Updated", "Return Sales Data Updated");
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
                "Delete Return Sales Record", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            //ReturnSalesDAO returnSalesDAO = generateDAO( );
              ReturnSalesDAO returnSalesDAO = new ReturnSalesDAO();
              returnSalesDAO.setLotNumber(Integer.parseInt( txtLotNo.getValue()));
            if ( returnSalesDAO != null )
            {
                DBHandler dBHandler = DBHandler.getInstance();
                String result = dBHandler.delete( returnSalesDAO );

                if ( result == null )
                {
                    Utils.showAlert(Alert.AlertType.INFORMATION, "Success", "Record Deleted", "Return Sales Data Deleted");
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
                "Clear Return Sales Details", "Are you sure?");
        if ( confirmResult.get() == ButtonType.OK )
        {
            txtLotNo.getSelectionModel().clearSelection();
            txtInvoiceNo.getSelectionModel().clearSelection();
            dateReturn.setValue(null);
            txtWeight.clear();
            txtDescription.clear();
            dateReturn.setValue( LocalDate.now());
        }
    }
    
    public void onWindowShownEvent()
    {
        loadData();
        
        DBHandler dBHandler = DBHandler.getInstance();
        List<String> LotNoList = dBHandler.getLotNoList();
        txtLotNo.getItems().addAll(LotNoList);
        
        DBHandler dBHandlerInvoice = DBHandler.getInstance();
        List<String> InvoiceNoList = dBHandler.getInvoiceNoList();
        txtInvoiceNo.getItems().addAll(InvoiceNoList);
    }
    
    private void loadData()
    {
        DBHandler dBHandler = DBHandler.getInstance();
        List<ReturnSalesDAO> recordList = dBHandler.getAllReturnSalesRecords();
        
        if ( recordList != null )
        {
            ObservableList<ReturnSalesTableModel> data = FXCollections.observableArrayList();
            for ( ReturnSalesDAO dao : recordList )
            {
                data.add( new ReturnSalesTableModel(dao) );
            }
        
            tblRetuenSales.setItems(data);
        }
    }
    
}

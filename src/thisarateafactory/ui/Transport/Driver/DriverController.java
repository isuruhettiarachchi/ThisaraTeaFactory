package thisarateafactory.ui.Transport.Driver;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.mysql.jdbc.Connection;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateNIC;
import static thisarateafactory.validation.validateTextFields.validateName;
import static thisarateafactory.validation.validateTextFields.validatePhone;


public class DriverController implements Initializable {
    private Connection con = null;
    private PreparedStatement pst= null;
    private ResultSet rs = null;
    private ObservableList op = FXCollections.observableArrayList();
    private String statusX = null;
    private String dName = null;
    private ObservableList<drvList> dlist;
    @FXML
    private Label driveridL;
    @FXML
    private JFXTextField drnameB;
    
    @FXML
    private JFXTextArea drvaddrB;
    @FXML
    private JFXTextField licensenumB;
    @FXML
    private JFXTextField nicB;
    @FXML
    private JFXTextField mobileB;
    @FXML
    private JFXButton addB;
    @FXML
    private JFXButton deleteB;
    @FXML
    private ToggleGroup status;
    @FXML
    private RadioButton mainR;
    @FXML
    private RadioButton asstR;
    @FXML
    private TableColumn<?, ?> didcol;
    @FXML
    private TableColumn<?, ?> dnamecol;
    @FXML
    private TableColumn<?, ?> daddrcol;
    @FXML
    private TableColumn<?, ?> dstatuscol;
    @FXML
    private TableColumn<?, ?> dlnocol;
    @FXML
    private TableColumn<?, ?> niccol;
    @FXML
    private TableColumn<?, ?> mobcol;
    @FXML
    private TableView<drvList> tview;
    @FXML
    private JFXButton updateB;
    @FXML
    private Label error_addr;
    @FXML
    private Label error_license;
    @FXML
    private Label error_nic;
    @FXML
    private Label error_mobile;
    @FXML
    private JFXTextField schT;
    @FXML
    private JFXButton clearB;
    @FXML
    private JFXButton goB;
    @FXML
    private JFXButton backB;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = databaseHandler.getConnection();
        setCellvalue();
        dlist = FXCollections.observableArrayList();
        loadDatafromDB();
        setCelltoTextFeilds();
        RequiredFieldValidator valid = new RequiredFieldValidator();
        NumberValidator numv = new NumberValidator();
        
        drvaddrB.getValidators().add(valid);
        //drnameB.getValidators().add(valid);
        licensenumB.getValidators().add(valid);
        nicB.getValidators().add(valid);
        mobileB.getValidators().add(numv);
        
        valid.setMessage("Cant be Empty"); 
        numv.setMessage("MobileNo must be a Number");
        /*
        drnameB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    drnameB.validate();
                }
            }
        });*/
        drvaddrB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    drvaddrB.validate();
                }
            }
        });
        licensenumB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    licensenumB.validate();
                }
            }
        });
        nicB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    nicB.validate();                    
                }
            }
        });
        mobileB.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    mobileB.validate();                    
                }
            }
        });
        
    }
    @FXML
    //add driver details to db
    private void addDriver(ActionEvent event) {
        if(drnameB.getText()==null||drnameB.getText().trim().isEmpty()||!validateName(drnameB.getText())){
            errorDialog("Driver Name must be a Name");
            return;
        }
        if(nicB.getText()==null|| nicB.getText().trim().isEmpty()||!validateNIC(nicB.getText())){
            errorDialog("NIC can't be Empty or Invalid Format");
            return;
        }
        if(mobileB.getText() == null || mobileB.getText().trim().isEmpty() || !validatePhone(mobileB.getText())){
            errorDialog("Mobile No must be number or Invalid Format");
            return;
        }        
        try{
            String sql  = "INSERT INTO driver(DriverName,Address,Status,LicenseNo,NIC,MobileNo) values (?,?,?,?,?,?)";            
            
            pst = con.prepareStatement(sql);                                   
            pst.setString(1, drnameB.getText());
            pst.setString(2, drvaddrB.getText());
            pst.setString(3, statusX);
            pst.setString(4, licensenumB.getText()); 
            pst.setString(5, nicB.getText());
            pst.setString(6, mobileB.getText());
            pst.execute();            
                       
            op.clear();            
            setCellvalue();
            loadDatafromDB();
            
            
        }
        catch(Exception e){
          System.out.println(e);
        }
    }

    public void setCellvalue(){
        didcol.setCellValueFactory(new PropertyValueFactory<>("did"));
        dnamecol.setCellValueFactory(new PropertyValueFactory<>("dname"));
        daddrcol.setCellValueFactory(new PropertyValueFactory<>("daddr"));
        dstatuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        dlnocol.setCellValueFactory(new PropertyValueFactory<>("licenseno"));
        niccol.setCellValueFactory(new PropertyValueFactory<>("nic"));
        mobcol.setCellValueFactory(new PropertyValueFactory<>("mobileno"));
    }
    public void loadDatafromDB(){
        dlist.clear();
        try {
            String sql = "select * from driver";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next())
            {
                dlist.add(new drvList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
            }
            tview.setItems(dlist);
        } catch (SQLException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void setCelltoTextFeilds(){
        tview.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                drvList dl = tview.getItems().get(tview.getSelectionModel().getSelectedIndex());
                if(dl.getStatus().equals("Main"))
                {
                    mainR.setSelected(true);
                    statusX = "Main";
                }
                else if(dl.getStatus().equals("Assistant"))
                {
                    asstR.setSelected(true);
                    statusX = "Main";
                }
                driveridL.setText(dl.getDid());
                drnameB.setText(dl.getDname());
                drvaddrB.setText(dl.getDaddr());
                licensenumB.setText(dl.getLicenseno());
                nicB.setText(dl.getNic());
                mobileB.setText(dl.getMobileno());
                
            }
        });
        
        
    }
    
    @FXML //set radio btn values
    private void main(ActionEvent event) {
        statusX = "Main";
    }

    @FXML//set radio btn values
    private void asst(ActionEvent event) {
        statusX = "Assistant";
    }

    @FXML
    private void updateDriver(ActionEvent event) {
        if(nicB.getText()==null|| nicB.getText().trim().isEmpty()||!validateNIC(nicB.getText())){
            errorDialog("NIC can't be Empty or Invalid Format");
            return;
        }
        if(mobileB.getText() == null || mobileB.getText().trim().isEmpty() || !validatePhone(mobileB.getText())){
            errorDialog("Mobile No must be number or Invalid Format");
            return;
        }
        if(drnameB.getText()==null||drnameB.getText().trim().isEmpty()||!validateName(drnameB.getText())){
            errorDialog("Driver Name must be a Name");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Update");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Update?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){
        try{
            String sql = "UPDATE driver SET DriverName=?, Address=?, Status=?, LicenseNo=?, NIC=?, MobileNo=? WHERE DriverID=?";
            String id = driveridL.getText();
            String dname = drnameB.getText();
            String daddr = drvaddrB.getText();
            String statusA = statusX;
            String licenseNo = licensenumB.getText();
            String nic = nicB.getText();
            String mobile = mobileB.getText();
      
                
            pst = con.prepareStatement(sql);
            pst.setString(1, dname);
            pst.setString(2, daddr);
            pst.setString(3, statusA);
            pst.setString(4, licenseNo);
            pst.setString(5, nic);
            pst.setInt(6, Integer.parseInt(mobile));
            pst.setInt(7, Integer.parseInt(id));
            
            pst.executeUpdate();
            //dlist.clear();
            loadDatafromDB();            
            
        }
        catch(SQLException | NumberFormatException e)
        {
            System.out.println(e);
        }
        }
    }

    @FXML
    private void deleteDriver(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Delete?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){     
        try{
            String sql = "delete from driver where DriverID=?";
            String id = driveridL.getText();
            pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();
            //dlist.clear();
            loadDatafromDB();
            
        }
        catch(SQLException | NumberFormatException e)
        {
            System.out.println(e);
        }
        }
    }
    private void searchDriver(){
        schT.setOnKeyReleased(e->{
            String sql = "select * from driver where DriverID=?";
            
            if(schT.getText().equals(""))
            {
                loadDatafromDB();
            }else{
                dlist.clear();
                try{
                    int i = Integer.parseInt(schT.getText());
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, i);
                    pst.executeQuery();
                    rs = pst.executeQuery();
                    while(rs.next())
                    {
                        dlist.add(new drvList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
                    }
                    tview.setItems(dlist);
                    }catch(NumberFormatException | SQLException ex){                
                    
                }
            }
        });
    }
    
    private void clearAll(){
        drnameB.clear();
        drvaddrB.clear();
        mainR.setSelected(false);
        asstR.setSelected(false);
        licensenumB.clear();
        nicB.clear();
        mobileB.clear();
        driveridL.setText("");
        loadDatafromDB();
    }

    @FXML
    private void clear(ActionEvent event) {
        clearAll();
    }

    @FXML
    private void schName(ActionEvent event) {
           String sql = "SELECT * FROM driver WHERE DriverName LIKE ?";
            
            if(schT.getText().equals(""))
            {
                loadDatafromDB();
            }else{
                dlist.clear();
                try{
                    String i = schT.getText();
                    pst = con.prepareStatement(sql);
                    pst.setString(1, "%"+i+"%");
                    pst.executeQuery();
                    rs = pst.executeQuery();
                    while(rs.next())
                    {
                        dlist.add(new drvList(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) ));
                    }
                    tview.setItems(dlist);
                    }catch(NumberFormatException | SQLException ex){                
                    
                }
            }        
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Stage stage = (Stage) backB.getScene().getWindow();
            stage.close();
            
            
            FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("../transportMain/transportM.fxml"));
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

    private void errorDialog(String erMsg) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
    }

}
    

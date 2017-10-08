package thisarateafactory.ui.Transport.Turn;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.DoubleValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
import static thisarateafactory.validation.validateTextFields.validateCurrency;
import static thisarateafactory.validation.validateTextFields.validateName;


public class TurnController implements Initializable {
        private Connection con = null;
        private ObservableList data = FXCollections.observableArrayList();
        private ObservableList dataad = FXCollections.observableArrayList();
        private ObservableList datav = FXCollections.observableArrayList();
        private ObservableList dn = FXCollections.observableArrayList();
        private ObservableList<turnList> tl;
        private ResultSet rs = null;
        private ResultSet rs1 = null;
        private ResultSet rs2 = null;
        private ResultSet rs3 = null;
        private PreparedStatement pst = null;
        private PreparedStatement pst1 = null;
        private PreparedStatement pst2 = null;
        private PreparedStatement pst3 = null;
        
        private Date dt;
        
    @FXML
    private JFXComboBox<String> dcomboB = new JFXComboBox (data);
    @FXML
    private JFXComboBox<String> adcomboB= new JFXComboBox (dataad);
    @FXML
    private JFXComboBox<String> vcomboB= new JFXComboBox (datav);
    private  ObservableList list = FXCollections.observableArrayList("Collecting","Supplying","Other");
    @FXML
    private JFXComboBox<String> typecombo;
    @FXML
    private DatePicker tdate = new DatePicker();
    @FXML
    private JFXButton addbtn;
    @FXML
    private JFXTextField fromB;
    @FXML
    private JFXTextField toB;
    @FXML
    private JFXTextField distanceB;
    @FXML
    private JFXTextField fcostB;
    @FXML
    private JFXTextField oexpB;
    @FXML
    private TableColumn<?, ?> columnTurnid;
    @FXML
    private TableColumn<?, ?> columnDriverid;
    @FXML
    private TableColumn<?, ?> columnAsstdrvid;
    @FXML
    private TableColumn<?, ?> columnVehicleid;
    @FXML
    private TableColumn<?, ?> columnDate;
    @FXML
    private TableColumn<?, ?> columnType;
    @FXML
    private TableColumn<?, ?> columnRoute;
    @FXML
    private TableColumn<?, ?> columnDistance;
    @FXML
    private TableColumn<?, ?> columnFuelcost;
    @FXML
    private TableColumn<?, ?> columnOtherexp;
    @FXML
    private Label turnidL;
    @FXML
    private TableView<turnList> turntable;
    @FXML
    private JFXButton updateB;
    @FXML
    private JFXButton deleteB;
    @FXML
    private Label teL;
    @FXML
    private JFXButton clearB;
    @FXML
    private JFXButton showTotalcost;
    @FXML
    private DatePicker searchDate;
    @FXML
    private JFXButton searchB;
    @FXML
    private JFXButton goB;
    @FXML
    private JFXTextField idS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = databaseHandler.getConnection();
        tl = FXCollections.observableArrayList();
        fillcombod();
        fillcomboad();
        fillcombov();
        loadDatafromDB();
        setCellTable();
        setCelltoTextFeilds();
        typecombo.setItems(list);
        tdate.setValue(LocalDate.now());
        
        RequiredFieldValidator valid = new RequiredFieldValidator();
        DoubleValidator numv = new DoubleValidator();
        DoubleValidator numv1 = new DoubleValidator();
        DoubleValidator numv2 = new DoubleValidator();
        
        valid.setMessage("Cant be Empty"); 
        numv.setMessage("Distance Must be a Number");
        numv1.setMessage("Fuel Cost Must be a Number");
        numv2.setMessage("Other Expenses Must be a Number");
        
        fromB.getValidators().add(valid);
        toB.getValidators().add(valid);
        distanceB.getValidators().add(numv);
        fcostB.getValidators().add(numv1);
        oexpB.getValidators().add(numv2);
        
        
        
        fromB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    fromB.validate();
                }
            }
        });          
        toB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    toB.validate();
                }
            }
        });
        distanceB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    distanceB.validate();
                }
            }
        });
        fcostB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    fcostB.validate();
                }
            }
        });
        oexpB.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue){
                    oexpB.validate();
                }
            }
        });
    }
    
    public void fillcombod(){
        try{
            String sql="SELECT * FROM driver WHERE Status LIKE 'Main'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                data.add(rs.getString("DriverName"));                
            }
            pst.close();
            rs.close();
        }
       
        catch(Exception e){
            System.out.println(e);
        }
        dcomboB.setItems(data);
        
    }
    public void fillcomboad(){
            try {
                String sql = "SELECT *FROM driver WHERE Status LIKE 'Assistant'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                    dataad.add(rs.getString("DriverName"));                    
                }
                pst.close();
                rs.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            adcomboB.setItems(dataad); 
    }
    public void fillcombov(){
        try{
            String sql = "SELECT * from vehicle";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                datav.add(rs.getString("RegNo"));
            }
            pst.close();
            rs.close();
            
        }
        catch(Exception e){
            System.out.println(e);
        }
        vcomboB.setItems(datav);
    }

    @FXML
    private void addTurn(ActionEvent event) {
        if(fromB.getText()==null||fromB.getText().trim().isEmpty()||!validateName(fromB.getText())){
            errorDialog("Route(From) Can't be Empty or must be a Name");
            return;
            }
        if(toB.getText()==null||toB.getText().trim().isEmpty()||!validateName(toB.getText())){
            errorDialog("Route(To) Can't be Empty or must be a Name");
            return;
            }
        if(distanceB.getText()==null||distanceB.getText().trim().isEmpty()||!validateCurrency(distanceB.getText())){
             errorDialog("Distance can't be Empty or must be a number");
             return;
           }
        if(fcostB.getText()==null||fcostB.getText().trim().isEmpty()||!validateCurrency(distanceB.getText())){
             errorDialog("Fuel Cost can't be Empty or must be a number");
             return;
           }
        if(oexpB.getText()==null||oexpB.getText().trim().isEmpty()||!validateCurrency(oexpB.getText())){
             errorDialog("Other Expences can't be Empty or must be a number");
             return;
           }
        
        try{                       
            int did = getDriverID();
            int asdid = getAsstDrvID();
            int vid = getVehicleID();            
            
            String from = fromB.getText();
            String to = toB.getText();
            String tog = from + " to " + to;
            System.out.println(tog);
            int i = getVehicleID();
            double mile = getMilage();
            System.out.println(mile);
            double totaldis = Double.parseDouble(distanceB.getText())*2;
            String sql = "INSERT INTO turn(driver_id, asstdrv_id, vehicle_id, date, type, route, distance, fuelcost, otherexp) VALUES (?,?,?,?,?,?,?,?,?)";
            String sql1 = "INSERT INTO Transaction(Type, Date, Amount) VALUES (?,?,?)";
            String sql2 = "UPDATE vehicle SET Milage = ? WHERE vehicleID = ?";           
            
            
            LocalDate ld = tdate.getValue();
            dt = java.sql.Date.valueOf(ld);
            
            pst = con.prepareStatement(sql);
            pst1 = con.prepareStatement(sql1);
            pst2 = con.prepareStatement(sql2);
            
            pst.setInt(1, did);
            pst.setInt(2, asdid);
            pst.setInt(3, vid);
            pst.setDate(4, dt);
            pst.setString(5, typecombo.getValue());
            pst.setString(6, tog);
            pst.setDouble(7, Double.parseDouble(distanceB.getText()));
            pst.setDouble(8, Double.parseDouble(fcostB.getText()));
            pst.setDouble(9, Double.parseDouble(oexpB.getText()));
            pst.execute();            
                       
            pst1.setString(1, "Transport ALl");
            pst1.setDate(2, dt);
            pst1.setDouble(3, Double.parseDouble(fcostB.getText())+Double.parseDouble(oexpB.getText()));
            
            pst2.setDouble(1, mile+totaldis);
            pst2.setInt(2, vid);
            pst2.execute();
            //pst1.execute();
            
            pst.close();
            pst1.close();
            setCellTable();
            loadDatafromDB();
                        
        }
        catch(SQLException | NumberFormatException e){
            System.out.println(e);
        }
       
       addToTransaction();
    }
    
    private void addToTransaction(){
        String query ="select turn_id, fuelcost, otherexp from turn where turn_id = (select max(turn_id) from turn)";
        int id = 0;
        double fuelC = 0;
        double otherex = 0;
            try {
                pst = con.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    id = rs.getInt("turn_id");
                    fuelC = rs.getDouble("fuelcost");
                    otherex = rs.getDouble("otherexp");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        query = "insert into transactions(RefID, Type, Date, Amount) values(?, ?, ?, ?)";
            try {
                pst = con.prepareStatement(query);
                pst.setInt(1, id);
                pst.setString(2, "TransportTurn");
                pst.setDate(3, dt);
                pst.setDouble(4, fuelC+otherex);
                
                int x = pst.executeUpdate();
                if(x == 1){
                    System.out.println("successfully added to transaction table");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            
    }
    
    private void setCellTable(){
        columnTurnid.setCellValueFactory(new PropertyValueFactory<>("turnID"));
        columnDriverid.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        columnAsstdrvid.setCellValueFactory(new PropertyValueFactory<>("asstdrvID"));
        columnVehicleid.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnType.setCellValueFactory(new PropertyValueFactory<>("type"));
        columnRoute.setCellValueFactory(new PropertyValueFactory<>("route"));
        columnDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        columnFuelcost.setCellValueFactory(new PropertyValueFactory<>("fuelcost"));
        columnOtherexp.setCellValueFactory(new PropertyValueFactory<>("otherExp"));        
    }
    
    void loadDatafromDB(){
            tl.clear();
            try {
                String sql = "SELECT * FROM turn";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()){
                    tl.add(new turnList(""+rs.getInt(1), ""+rs.getInt(2), ""+rs.getInt(3), ""+rs.getInt(4), ""+rs.getDate(5), rs.getString(6), rs.getString(7), ""+rs.getDouble(8), ""+rs.getDouble(9), ""+rs.getDouble(10)));
                }
                
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            turntable.setItems(tl);
    }
    private void setCelltoTextFeilds(){
        turntable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    turnList tll = turntable.getItems().get(turntable.getSelectionModel().getSelectedIndex());
                    String a = tll.getDriverID();
                    String sql1= "SELECT DriverName FROM driver WHERE DriverID = ?";
                    pst1 = con.prepareStatement(sql1);
                    pst1.setInt(1, Integer.parseInt(a));
                    rs1 = pst1.executeQuery();
                    String drivername="";
                    while(rs1.next()){
                        drivername =  rs1.getString(1);
                    } 
                    
                    String b = tll.getAsstdrvID();
                    String sql2 = "SELECT DriverName FROM driver WHERE DriverID = ?";
                    pst2 = con.prepareStatement(sql2);
                    pst2.setInt(1, Integer.parseInt(b));
                    rs2 = pst2.executeQuery();
                    String asstdrvname="";
                    while(rs2.next()){
                        asstdrvname = rs2.getString(1);
                    }
                    
                    String c = tll.getVehicleID();
                    String sql3 = "SELECT RegNo FROM vehicle WHERE VehicleID = ?";
                    pst3 = con.prepareStatement(sql3);
                    pst3.setInt(1, Integer.parseInt(c));
                    rs3 = pst3.executeQuery();
                    String regno = "";
                    while(rs3.next()){
                        regno = rs3.getString(1);
                    }
                    String date = tll.getDate();
                    LocalDate ld = LocalDate.parse(date);
                    String rt = tll.getRoute();
                    String [] x = rt.split(" to ");
                    
                    double fc = Double.parseDouble(tll.getFuelcost());
                    double otexp = Double.parseDouble(tll.getOtherExp());
                    double total = fc + otexp;
                    
                    
                    teL.setText(Double.toString(total));
                    turnidL.setText(tll.getTurnID());
                    dcomboB.setValue(drivername);
                    adcomboB.setValue(asstdrvname);
                    vcomboB.setValue(regno);
                    tdate.setValue(ld);
                    typecombo.setValue(tll.getType());
                    fromB.setText(x[0]);
                    toB.setText(x[1]);
                    distanceB.setText(tll.getDistance());
                    fcostB.setText(tll.getFuelcost());
                    oexpB.setText(tll.getOtherExp());
                    pst1.close();
                    pst2.close();
                    pst3.close();
                    rs1.close();
                    rs2.close();
                    rs3.close();
                    pst.close();
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }            
        });
    }

    @FXML
    private void updateTurn(ActionEvent event) {
        if(fromB.getText()==null||fromB.getText().trim().isEmpty()||!validateName(fromB.getText())){
            errorDialog("Route(From) Can't be Empty or must be a Name");
            return;
            }
        if(toB.getText()==null||toB.getText().trim().isEmpty()||!validateName(toB.getText())){
            errorDialog("Route(To) Can't be Empty or must be a Name");
            return;
            }
        if(distanceB.getText()==null||distanceB.getText().trim().isEmpty()||!validateCurrency(distanceB.getText())){
             errorDialog("Distance can't be Empty or must be a number");
             return;
           }
        if(fcostB.getText()==null||fcostB.getText().trim().isEmpty()||!validateCurrency(distanceB.getText())){
             errorDialog("Fuel Cost can't be Empty or must be a number");
             return;
           }
        if(oexpB.getText()==null||oexpB.getText().trim().isEmpty()||!validateCurrency(oexpB.getText())){
             errorDialog("Other Expences can't be Empty or must be a number");
             return;
           }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Update");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Update?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){     
                try {
                String sql = "UPDATE turn SET driver_id=?, asstdrv_id=?, vehicle_id=?, date=?, type=?, route = ?, distance=?, fuelcost=?, otherexp=? WHERE turn_id=?";
                pst = con.prepareStatement(sql);
                
                int did = getDriverID();
                int asdid = getAsstDrvID();
                int vid = getVehicleID();
                String from = fromB.getText();
                String to = toB.getText();                
                String tog = from + " to " + to;
                LocalDate ld = tdate.getValue();
                Date dt = java.sql.Date.valueOf(ld);
                
                pst = con.prepareStatement(sql);
                pst.setInt(1, did);
                pst.setInt(2, asdid);
                pst.setInt(3, vid);
                pst.setDate(4, dt);
                pst.setString(5, typecombo.getValue());
                pst.setString(6, tog);
                pst.setDouble(7, Double.parseDouble(distanceB.getText()));
                pst.setDouble(8, Double.parseDouble(fcostB.getText()));
                pst.setDouble(9, Double.parseDouble(oexpB.getText()));
                pst.setInt(10, Integer.parseInt(turnidL.getText()));
                pst.execute();
                pst.close();
                setCellTable();
                loadDatafromDB();
                
                                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private int getDriverID(){
        int did=0;    
        try {
                String driverid = dcomboB.getSelectionModel().getSelectedItem();
                String sql1 = "SELECT DriverID FROM driver WHERE DriverName LIKE ?";
                pst1 = con.prepareStatement(sql1);
                pst1.setString(1, driverid);
                rs1 = pst1.executeQuery();
                //int did = 0;
                while(rs1.next()){
                    did = rs1.getInt(1);            
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return did;
    }

    private int getAsstDrvID() {
            int asdid=0;
            try {
                String asstdid = adcomboB.getSelectionModel().getSelectedItem();                
                String sql2 = "SELECT DriverID FROM driver WHERE DriverName LIKE ?";
                pst2 = con.prepareStatement(sql2);
                pst2.setString(1, asstdid);
                rs2 = pst2.executeQuery();
                
                while(rs2.next()){
                    asdid = rs2.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return asdid;
        
    }

    private int getVehicleID() {
            int vid=0;
            try {
                String vehicleid = vcomboB.getSelectionModel().getSelectedItem();
                String sql3 = "SELECT VehicleID FROM vehicle WHERE RegNo LIKE ?";
                pst3 = con.prepareStatement(sql3);
                pst3.setString(1, vehicleid);
                rs3 = pst3.executeQuery();                
                while(rs3.next()){
                    vid= rs3.getInt(1);        
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return vid;
    }
    private double getMilage(){
            double milage = 0;
            try {
                String sql = "SELECT Milage FROM vehicle WHERE VehicleID = ?";
                int i = getVehicleID();
                pst = con.prepareStatement(sql);
                pst.setInt(1, i);
                rs = pst.executeQuery();
                while(rs.next()){
                    milage = rs.getDouble(1);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return milage;
    }

    @FXML
    private void deleteTurn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText(null);
        alert.setHeaderText("Are You Sure to Delete?");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get()==ButtonType.OK){     
            try {
                String sql = "DELETE FROM turn where turn_id=?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(turnidL.getText()));
                pst.executeUpdate();
                loadDatafromDB();
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String query ="delete from transactions where RefID = ? and Type = ?";
            try {
                pst = con.prepareStatement(query);
                pst.setInt(1, Integer.parseInt(turnidL.getText()));
                pst.setString(2, "TransportTurn");
                int x =pst.executeUpdate();
                if(x==1){
                    System.out.println("successfully delete from transaction");
                }
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }

    @FXML
    private void clear(ActionEvent event) {
       clearAll();
       loadDatafromDB();
    }
    private void clearAll(){
        turnidL.setText("");
        dcomboB.setValue("");
        adcomboB.setValue("");
        vcomboB.setValue("");
        tdate.setValue(LocalDate.now());
        typecombo.setValue("");
        fromB.clear();
        toB.clear();
        distanceB.clear();
        fcostB.clear();
        oexpB.clear();
        teL.setText("None");
        searchDate.getEditor().clear();
        idS.clear();
    }

    @FXML
    private void showCost(ActionEvent event) {             
           
            try {
                FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("/thisarateafactory/ui/Transport/TotalCost/totalcost.fxml"));
                Parent root1 = (Parent) fxmLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                        
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         
     
    }
    
    private void errorDialog(String erMsg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(erMsg);

        alert.showAndWait();
        
    }

    @FXML
    private void searchTurn(ActionEvent event) {
        if(searchDate.equals("")){
           loadDatafromDB();
        }else{
            tl.clear();           
                try {
                LocalDate ld = searchDate.getValue();
                Date dt = java.sql.Date.valueOf(ld);
                String sql = "SELECT * FROM turn WHERE date= ?";           
                pst = con.prepareStatement(sql);
                pst.setDate(1, dt);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    tl.add(new turnList(""+rs.getInt(1), ""+rs.getInt(2), ""+rs.getInt(3), ""+rs.getInt(4), ""+rs.getDate(5), rs.getString(6), rs.getString(7), ""+rs.getDouble(8), ""+rs.getDouble(9), ""+rs.getDouble(10)));
                }
                turntable.setItems(tl);               
                
            } catch (SQLException ex) {
                Logger.getLogger(TurnController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
            
            
            
    }     

    @FXML
    private void schID(ActionEvent event) {
            String sql = "SELECT * FROM turn WHERE turn_id =?";
            
            if(idS.getText().equals(""))
            {
                loadDatafromDB();
            }else{
                tl.clear();
                try{
                    int i = Integer.parseInt(idS.getText());
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, i);
                    pst.executeQuery();
                    rs = pst.executeQuery();
                    while(rs.next())
                    {
                        tl.add(new turnList(""+rs.getInt(1), ""+rs.getInt(2), ""+rs.getInt(3), ""+rs.getInt(4), ""+rs.getDate(5), rs.getString(6), rs.getString(7), ""+rs.getDouble(8), ""+rs.getDouble(9), ""+rs.getDouble(10)));
                    }
                    turntable.setItems(tl);
                    }catch(NumberFormatException | SQLException ex){                
                    
                }
            }
    }   

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/transport.jrxml").getAbsolutePath();
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

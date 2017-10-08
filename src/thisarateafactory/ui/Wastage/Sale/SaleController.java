/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Sale;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import thisarateafactory.database.databaseHandler;
import thisarateafactory.ui.Wastage.AlertBox.AlertDialog;
import static thisarateafactory.validation.validateTextFields.validateNumbersOnly;

/**
 * FXML Controller class
 *
 * @author ravindu
 */
public class SaleController implements Initializable {
    
    private Connection conn=null;
    private PreparedStatement pst=null;
    private ResultSet rs=null;
    private int bid=0;
    private double cost=0;
    private double dis=0;
    private double disamnt=0;
    private double totalcost=0;
    
    private ObservableList<saleList> data;
    private ObservableList<CartList> data1;
    ObservableList<String>kkk=FXCollections.observableArrayList();
    ObservableList<String>lll=FXCollections.observableArrayList("Customer","Supplier","Employee");
    
    
    LocalDate localDate;
    DateTimeFormatter dtf;
    
    
    @FXML
    private Label Autobillno;
    @FXML
    private JFXComboBox<String> Buyerstype;
    @FXML
    private Label Cost;
    @FXML
    private TableColumn<?, ?> Wbuyertype;
    @FXML
    private TableColumn<?, ?> Wdisamount;
    @FXML
    private TableColumn<?, ?> Wcost;
    @FXML
    private TableColumn<?, ?> Wtotcost;
    @FXML
    private Label discount;
    @FXML
    private Label Disamount;
    @FXML
    private TextField Quantity;
    @FXML
    private JFXButton Addbutton;
    @FXML
    private JFXButton Proceedbutton;
    @FXML
    private JFXButton cancelbutton;
    @FXML
    private JFXTextField search;
    @FXML
    private TableView<saleList> BillTable;
    @FXML
    private TableColumn<?, ?> Wbillno;
    @FXML
    private TableView<CartList> ProductTable;
    @FXML
    private TableColumn<?, ?> wwproductID;
    @FXML
    private TableColumn<?, ?> wwquantity;
    @FXML
    private Label currentdate;
    @FXML
    private TableColumn<?, ?> Wdate;
    @FXML
    private TableColumn<?, ?> WWprice;
    @FXML
    private JFXButton finish;
    @FXML
    private JFXComboBox<String> productid;
    @FXML
    private Label Totalcost;
    @FXML
    private TableColumn<?, ?> Wdiscount;
    @FXML
    private JFXTextField search2;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        conn = databaseHandler.getConnection();
        productid.setItems(kkk);
        Buyerstype.setItems(lll);
        getBillNO();
        fillComboBoxProduct();
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        localDate = LocalDate.now();
        currentdate.setText(dtf.format(localDate).toString());
        
        setCellSaleTable();
        setCellCartTable();
        setCellValueFromsaleTableToTextField();
        data = FXCollections.observableArrayList();
        setCellValueFromCartTableToTextField();
        data1 = FXCollections.observableArrayList();
        loadDataFromSaleDatabase();
        loadDataFromCartDatabase();
        search();
        Search2();
        
    }    


    @FXML
    private void CanselSales(ActionEvent event) {
        ((Stage) (((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void AddProduct(ActionEvent event) {

        if(productid.getValue()==null || Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null)
        {
            AlertDialog.display("Alert","You have to fill every information");
            return;
        }
        if(!validateNumbersOnly(Quantity.getText()) || Quantity.getText().trim().isEmpty() || String.valueOf(Quantity.getText()) == null)
        {
            AlertDialog.display("Alert","You have to enter a number for Quantity");
            return;  
        }
         if(Buyerstype.getValue()==null)
        {
            AlertDialog.display("Alert","You have to enter BuyersType and proceed before Add");
            return;
        }
        int pro=Integer.parseInt(productid.getValue());
        int qty=Integer.parseInt(Quantity.getText());
        double price = 0;
        int npro=0;
        int nqty=0;
        
        try
        {           
            String p="select Price From Stock where ProductID=?";
            pst=conn.prepareStatement(p);
            pst.setInt(1, pro);
            rs = pst.executeQuery();
    
            while(rs.next()){
                price = rs.getDouble("Price");
            }          
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        
        try{
            String a="select ProductID,Quantity from Cart where ProductID=? and Bill_No =?";
            System.out.println(a);
            pst=conn.prepareStatement(a);
            pst.setInt(1, pro);
            pst.setInt(2, bid);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                npro=rs.getInt("ProductID");
                nqty=rs.getInt("Quantity");
            }
            System.out.println(bid);
            if(pro==npro)
            {
                System.out.println("update");
                String n="update Cart set Quantity=?,Price=? where ProductID=? and Bill_No =?";
                pst=conn.prepareStatement(n);
                pst.setInt(1,qty+nqty);
                cost=cost+(price*qty);
                pst.setDouble(2, price*(qty+nqty));
                pst.setInt(3, pro);
                pst.setInt(4, bid);
                int x=pst.executeUpdate();
                disamnt=disamnt+(cost*dis);
                totalcost=totalcost+(cost-disamnt);
                
            }
            else
            {
               try{
                   System.out.println("insert");
                    String q="INSERT INTO Cart (Bill_No,ProductID,Quantity,Price) values ('"+ bid +"','"+ pro +"','"+ qty +"','"+ price*qty +"')";
                    
                    System.out.println(q);
                    pst=conn.prepareStatement(q);
                    pst.execute();
                    System.out.println("q");
                    cost=cost+(price*qty);
                    disamnt=disamnt+(cost*dis);
                    totalcost=totalcost+(cost-disamnt);
                    System.out.println("good");
            
                    
                }
                catch(SQLException e){
                    System.out.println(e);
                } 
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
        conn = databaseHandler.getConnection();
        
        Cost.setText(String.valueOf(cost));
        Disamount.setText(String.valueOf(disamnt));
        Totalcost.setText(String.valueOf(totalcost));
       
        try
        {
            String l="update Sale set Cost=?,DiscountAmount=?,Totalcost=?  where Bill_No=?";
            pst=conn.prepareStatement(l);
            pst.setDouble(1,cost);
            pst.setDouble(2,disamnt);
            pst.setDouble(3,totalcost);
            pst.setInt(4, bid);
            int x=pst.executeUpdate();
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        loadDataFromSaleDatabase();
    }
    @FXML
    private void ProceedSales(ActionEvent event) {
        
        if(Buyerstype.getValue()==null)
        {
            AlertDialog.display("Alert","You have to fill every information");
            return;
        }
        String Date=currentdate.getText();
        String buy=Buyerstype.getValue();
        
        
        if(buy=="Customer")
        {
            dis=0;
            
        }
        else if(buy=="Supplier")
        {
            dis=0.1;
            
        }
        else
        {
            dis=0.15;
            
        }
        discount.setText(String.valueOf(dis));
        
        conn = databaseHandler.getConnection();
        try{
            String q="INSERT INTO Sale (Buyers_Type,Date,Discount,Cost,DiscountAmount,Totalcost) values ('"+ buy +"','"+ Date +"','"+ dis +"','"+ cost +"','"+ disamnt +"','"+ totalcost +"')";

            pst=conn.prepareStatement(q);
            pst.execute();
            System.out.println("good");
            
           loadDataFromSaleDatabase();
           setCellSaleTable();
        }
        catch(SQLException e){
            System.out.println(e);
        }

    }

    private void setCellSaleTable() //set table column ids
    {
        Wbillno.setCellValueFactory(new PropertyValueFactory<>("Bill_No"));
        Wbuyertype.setCellValueFactory(new PropertyValueFactory<>("Buyers_Type"));
        Wdate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Wdiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        Wcost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        Wdisamount.setCellValueFactory(new PropertyValueFactory<>("DiscountAmount"));
        Wtotcost.setCellValueFactory(new PropertyValueFactory<>("Totalcost"));
        BillTable.setItems(data);
    }
    
    private void setCellCartTable() //set table column ids
    {
        wwproductID.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        wwquantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        WWprice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        ProductTable.setItems(data1);
    }
    
    private void loadDataFromSaleDatabase()
    {

        data.clear();
        try{
            pst = conn.prepareStatement("SELECT * from Sale WHERE Bill_No = ?");
            pst.setInt  (1, bid);
            rs = pst.executeQuery();
            while(rs.next())
            {
                data.add(new saleList( ""+rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getDouble(4),""+rs.getDouble(5), ""+rs.getDouble(6),""+rs.getDouble(7)));
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BillTable.setItems(data);
    }
    
    private void loadDataFromCartDatabase()
    {
        data1.clear();
        try{
            pst = conn.prepareStatement("SELECT * from Cart where Bill_No = ?");
            pst.setInt(1, bid);
            rs = pst.executeQuery();
            while(rs.next())
            {
                data1.add(new CartList( ""+rs.getInt(2),""+rs.getInt(3),""+rs.getDouble(4)));
                System.out.println(rs.getDouble(3));
            }
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProductTable.setItems(data1);
    }
    
    private void setCellValueFromsaleTableToTextField() //mouse click
    {
        BillTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                saleList w1 = BillTable.getItems().get(BillTable.getSelectionModel().getSelectedIndex());
             
                Autobillno.setText(w1.getBill_No());
                Buyerstype.setValue(w1.getBuyers_Type());
                currentdate.setText(w1.getDate());
                discount.setText(w1.getDiscount());
                Cost.setText(w1.getCost());
                Disamount.setText(w1.getDiscountAmount());
                Totalcost.setText(w1.getTotalcost());
            }
            
        });
    }
    
    private void setCellValueFromCartTableToTextField() //mouse click
    {
        ProductTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                CartList w1 = ProductTable.getItems().get(ProductTable.getSelectionModel().getSelectedIndex());
             
                productid.setValue(w1.getProductID());
                Quantity.setText(w1.getQuantity());
                
            }
            
        });
    }
    
    @FXML
    private void search() {
        search.setOnKeyReleased(e->{
            String sql="select * from Sale where Bill_No= ?";
            
            if(search.getText().equals(""))
            {
                loadDataFromSaleDatabase();
            
            }else
            {
                data.clear();
            try {
                int i=Integer.parseInt(search.getText());
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      data.add(new saleList(""+rs.getInt(1),rs.getString(2),rs.getString(3),""+rs.getDouble(4),""+rs.getDouble(5), ""+rs.getDouble(6),""+rs.getDouble(7)));
                
                }
                
                BillTable.setItems(data);
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            }
             
        });
        
           
       
    }
    
    public void fillComboBoxProduct()
   {
        try{
            
            String query  = " select ProductID from Stock";
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
                
                while(rs.next()){
                kkk.add(rs.getString("ProductID"));
            }
                pst.close();
                rs.close();
            
        }
        catch(SQLException ex)
        {
           Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex); 
        }
        productid.setItems(kkk);
        
    }
    
    private void getBillNO()
    {
        String query="select max(Bill_No) as currentid from Sale";
        System.out.println(query);
            try 
            {
                pst=conn.prepareStatement(query);
                rs = pst.executeQuery();
                while(rs.next()){
                    bid = rs.getInt("currentid") + 1;
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(SaleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Autobillno.setText(String.valueOf(bid));
            System.out.println(bid);
    }

    @FXML
    private void FinishButton(ActionEvent event) {
        Autobillno.setText("");
        productid.getSelectionModel().clearSelection();
        Quantity.clear();
        Buyerstype.getSelectionModel().clearSelection();
        discount.setText("");
        Cost.setText("");
        Disamount.setText("");
        Totalcost.setText("");
        getBillNO();
        cost = 0;
        disamnt = 0;
        totalcost = 0;
    }

    @FXML
    private void Search2() {
        search2.setOnKeyReleased(e->{
            String sql="select * from Cart where Bill_No= ?";
            
            if(search2.getText().equals(""))
            {
                loadDataFromCartDatabase();
            
            }else
            {
                data1.clear();
            try {
                int i=Integer.parseInt(search2.getText());
                pst = (PreparedStatement) conn.prepareStatement(sql);
                pst.setInt(1, i);
                pst.executeQuery();
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                      data1.add(new CartList(""+rs.getInt(2),""+rs.getInt(3),""+rs.getDouble(4)));
                
                }
                
                ProductTable.setItems(data1);
                
            } 
            catch (SQLException ex) {
                System.out.println(ex);
            }
            
            }
        });
    }

    @FXML
    private void generateReport(ActionEvent event) {
        String path = new File("src/thisarateafactory/reports/waste.jrxml").getAbsolutePath();
        try{
            String is = path;
            JasperReport jr =JasperCompileManager.compileReport(is);
            JasperPrint jp =JasperFillManager.fillReport(jr,null,conn);
            JasperViewer.viewReport(jp,false);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }        
    }
    
}

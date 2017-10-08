/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Grocery;

/**
 *
 * @author Nipuni
 */
public class GroceryList {
    
    
    private String GroceryID;
    private String Date;
    private String EmployeeID;
    private String SupplierID;
    private String BuyerType;
    private String UnitPrice;
    private String Quality;
    private String Qty;
    private String TotalPrice;
    //private String IndexNo;


    public GroceryList(String GroceryID, String Date, String EmployeeID, String SupplierID, String BuyerType, String UnitPrice, String Quality, String Qty, String TotalPrice) {
        this.GroceryID = GroceryID;
        this.Date = Date;
        this.EmployeeID = EmployeeID;
        this.SupplierID = SupplierID;
        this.BuyerType = BuyerType;
        this.UnitPrice = UnitPrice;
        this.Quality = Quality;
        this.Qty = Qty;
        this.TotalPrice = TotalPrice;
    }

    /**
     * @return the GroceryID
     */
    public String getGroceryID() {
        return GroceryID;
    }

    /**
     * @param GroceryID the GroceryID to set
     */
    public void setGroceryID(String GroceryID) {
        this.GroceryID = GroceryID;
    }

    /**
     * @return the Date
     */
    public String getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     * @return the EmployeeID
     */
    public String getEmployeeID() {
        return EmployeeID;
    }

    /**
     * @param EmployeeID the EmployeeID to set
     */
    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    /**
     * @return the SupplierID
     */
    public String getSupplierID() {
        return SupplierID;
    }

    /**
     * @param SupplierID the SupplierID to set
     */
    public void setSupplierID(String SupplierID) {
        this.SupplierID = SupplierID;
    }

    /**
     * @return the BuyerType
     */
    public String getBuyerType() {
        return BuyerType;
    }

    /**
     * @param BuyerType the BuyerType to set
     */
    public void setBuyerType(String BuyerType) {
        this.BuyerType = BuyerType;
    }

    /**
     * @return the UnitPrice
     */
    public String getUnitPrice() {
        return UnitPrice;
    }

    /**
     * @param UnitPrice the UnitPrice to set
     */
    public void setUnitPrice(String UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    /**
     * @return the Quality
     */
    public String getQuality() {
        return Quality;
    }

    /**
     * @param Quality the Quality to set
     */
    public void setQuality(String Quality) {
        this.Quality = Quality;
    }

    /**
     * @return the Qty
     */
    public String getQty() {
        return Qty;
    }

    /**
     * @param Qty the Qty to set
     */
    public void setQty(String Qty) {
        this.Qty = Qty;
    }

    /**
     * @return the TotalPrice
     */
    public String getTotalPrice() {
        return TotalPrice;
    }

    /**
     * @param TotalPrice the TotalPrice to set
     */
    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }
    
    
    
    
    
    
    
    
}

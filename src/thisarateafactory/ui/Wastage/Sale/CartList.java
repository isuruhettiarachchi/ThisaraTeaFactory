/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Sale;

/**
 *
 * @author ravindu
 */
public class CartList {
    
    //private String Bill_No;
    private String ProductID;
    private String Quantity;
    private String Price;
    
    public CartList(/*String Bill_No,*/String ProductID,String Quantity,String Price)
    {
        //this.Bill_No=Bill_No;
        this.ProductID=ProductID;
        this.Quantity=Quantity;
        this.Price=Price;
    }
   /* public String getBill_No()
    {
        return Bill_No;
    }
    public void setBill_No(String Bill_No)
    {
        this.Bill_No=Bill_No;
    }
    */
    public String getProductID()
    {
        return ProductID;
    }
    public void setProductID(String ProductID)
    {
        this.ProductID=ProductID;
    }
    
    public String getQuantity()
    {
        return Quantity;
    }
    public void setQuantity(String Quantity)
    {
        this.Quantity=Quantity;
    }
    
    public String getPrice()
    {
        return Price;
    }
    public void setPrice(String Price)
    {
        this.Price=Price;
    }
    
}


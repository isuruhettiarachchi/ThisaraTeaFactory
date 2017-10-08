/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Stock;

/**
 *
 * @author ravindu
 */
public class SList {
    private String ProductID;
    private String Type;
    private String Description;
    private String Weight;
    private String Qty;
    private String Price;
    
    public SList(String ProductID,String Type,String Description,String Weight,String Qty,String Price)
    {
        this.ProductID=ProductID;
        this.Type=Type;
        this.Description=Description;
        this.Weight=Weight;
        this.Qty=Qty;
        this.Price=Price;
                
    }
    public String getProductID()
    {
        return ProductID;
    }
    public void setProductID(String ProductID)
    {
        this.ProductID=ProductID;
    }
    public String getType()
    {
        return Type;
    }
    public void setType(String Type)
    {
        this.Type=Type;
    }
    public String getDescription()
    {
        return Description;
    }
    public void setDescription(String Description)
    {
        this.Description=Description;
    }
    public String getWeight()
    {
        return Weight;
    }
    public void setWeight(String Weight)
    {
        this.Weight=Weight;
    }
    public String getQty()
    {
        return Qty;
    }
    public void setQty(String Qty)
    {
        this.Qty=Qty;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Manufact;

/**
 *
 * @author ravindu
 */
public class MList {
    private String ManufactureID;
    private String ProductID;
    private String WasteID;
    private String ManufactureDate;
    private String ExpiartDate;
    private String Name;
    
    public MList(String ManufactureID,String ProductID,String ManufactureDate,String Name,String ExpiartDate,String WasteID)
    {
        this.ManufactureID=ManufactureID;
        this.ProductID=ProductID;
        this.WasteID=WasteID;
        this.ManufactureDate=ManufactureDate;
        this.ExpiartDate=ExpiartDate;
        this.Name=Name;
    }

    
    public String getManufactureID()
    {
        return ManufactureID;
    }
    
    public void setManufactureID(String ManufactureID)
    {
        this.ManufactureID=ManufactureID;
    }
    
    public String getManufactureDate()
    {
        return ManufactureDate;
    }
    
    public void setManufactureDate(String ManufactureDate)
    {
        this.ManufactureDate=ManufactureDate;
    }
    
    public String getExpiartDate()
    {
        return ExpiartDate;
    }
    
    public void setExpiartDate(String ExpiartDate)
    {
        this.ExpiartDate=ExpiartDate;
    }
    
    public String getProductID()
    {
        return ProductID;
    }

    public void setProductID(String ProductID)
    {
        this.ProductID=ProductID;
    }
    
    public String getWasteID()
    {
        return WasteID;
    }
    
    public void setWasteID(String WasteID)
    {
        this.WasteID=WasteID;
    }
    
    public String getName()
    {
        return Name;
    }
    
    public void setName(String Name)
    {
        this.Name=Name;
    }
    
}
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
public class saleList {
    private String Bill_No;
    private String Buyers_Type;
    private String Date;
    private String Discount;
    private String Cost;
    private String DiscountAmount;
    private String Totalcost;
    
    public saleList(String Bill_No,String Buyers_Type,String Date,String Discount,String Cost,String DiscountAmount,String Totalcost)
    {
        this.Bill_No=Bill_No;
        this.Buyers_Type=Buyers_Type;
        this.Date=Date;
        this.Discount=Discount;
        this.Cost=Cost;
        this.DiscountAmount=DiscountAmount;
        this.Totalcost=Totalcost;
    }
    
    public String getBill_No()
    {
        return Bill_No;
    }
    public void setBill_No(String Bill_No)
    {
        this.Bill_No=Bill_No;
    }
    
    public String getBuyers_Type()
    {
        return Buyers_Type;
    }
    public void setBuyers_Type(String Buyers_Type)
    {
        this.Buyers_Type=Buyers_Type;
    }
    
    public String getDate()
    {
        return Date;
    }
    public void setDate(String Date)
    {
        this.Date=Date;
    }
    
    public String getDiscount()
    {
        return Discount;
    }
    public void setDiscount(String Discount)
    {
        this.Discount=Discount;
    }
    
    public String getCost()
    {
        return Cost;
    }
    public void setCost(String Cost)
    {
        this.Cost=Cost;
    }
    
    public String getDiscountAmount()
    {
        return DiscountAmount;
    }
    public void setDiscountAmount(String DiscountAmount)
    {
        this.DiscountAmount=DiscountAmount;
    }
    
    public String getTotalcost()
    {
        return Totalcost;
    }
    public void setTotalcost(String Totalcost)
    {
        this.Totalcost=Totalcost;
    }
    
}

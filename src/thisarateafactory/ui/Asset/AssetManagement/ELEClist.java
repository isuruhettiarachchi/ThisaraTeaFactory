/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Asset.AssetManagement;

/**
 *
 * @author LUDZ
 */
public class ELEClist {
        public String Bill;
     public String unit;
     public String amount;
  public String date;
     public String pay;
    public ELEClist(String Bill, String unit, String amount, String date, String pay) {
        this.Bill = Bill;
        this.unit = unit;
        this.amount = amount;
        this.date = date;
        this.pay = pay;
    }

    public String getBill() {
        return Bill;
    }

    public void setBill(String Bill) {
        this.Bill = Bill;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
   
    
}

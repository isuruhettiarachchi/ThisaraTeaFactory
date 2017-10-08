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
public class TPlist {
    
    private String billNo;
    private String TpNo;
    private String amount; 
    private String Date;
    private String Pay;

    public TPlist(String billNo, String TpNo, String amount, String Date, String Pay) {
        this.billNo = billNo;
        this.TpNo = TpNo;
        this.amount = amount;
        this.Date = Date;
        this.Pay = Pay;
    }
    

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTpNo() {
        return TpNo;
    }

    public void setTpNo(String TpNo) {
        this.TpNo = TpNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String Pay) {
        this.Pay = Pay;
    }
  
   
}

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
public class FireList {
    private String F_voucher;
    private String F_Address;
    private String Date;
    private String F_Cost;
    public FireList(String F_voucher, String F_Address, String Date, String F_Cost) {
        this.F_voucher = F_voucher;
        this.F_Address = F_Address;
        this.Date = Date;
        this.F_Cost = F_Cost;
    }
   

    public String getF_voucher() {
        return F_voucher;
    }

    public void setF_voucher(String F_voucher) {
        this.F_voucher = F_voucher;
    }

    public String getF_Address() {
        return F_Address;
    }

    public void setF_Address(String F_Address) {
        this.F_Address = F_Address;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getF_Cost() {
        return F_Cost;
    }

    public void setF_Cost(String F_Cost) {
        this.F_Cost = F_Cost;
    }
    
    
   
    
}

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
public class OtherList {
    private String Voucher;
    private String fuel;
     private String OApayment;
    private String Sloan;
    private String Date;

    public OtherList(String Voucher, String fuel, String OApayment, String Sloan, String Date) {
        this.Voucher = Voucher;
        this.fuel = fuel;
        this.OApayment = OApayment;
        this.Sloan = Sloan;
        this.Date = Date;
    }

    public String getVoucher() {
        return Voucher;
    }

    public void setVoucher(String Voucher) {
        this.Voucher = Voucher;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getOApayment() {
        return OApayment;
    }

    public void setOApayment(String OApayment) {
        this.OApayment = OApayment;
    }

    public String getSloan() {
        return Sloan;
    }

    public void setSloan(String Sloan) {
        this.Sloan = Sloan;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
   
    
    
}

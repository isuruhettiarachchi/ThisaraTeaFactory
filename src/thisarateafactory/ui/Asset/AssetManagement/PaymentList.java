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
public class PaymentList {

    private String PId;
    private String Pdate;
    private String Pdes;
    private String Pamount;
    private String TeleBIll;
    private String ElecBill;
    private String AccNo;
    private String FvouNo;
    private String OtherVNo;

    public PaymentList(String PId, String Pdate, String Pdes, String Pamount, String TeleBIll, String ElecBill, String AccNo, String FvouNo, String OtherVNo) {
        this.PId = PId;
        this.Pdate = Pdate;
        this.Pdes = Pdes;
        this.Pamount = Pamount;
        this.TeleBIll = TeleBIll;
        this.ElecBill = ElecBill;
        this.AccNo = AccNo;
        this.FvouNo = FvouNo;
        this.OtherVNo = OtherVNo;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getPdate() {
        return Pdate;
    }

    public void setPdate(String Pdate) {
        this.Pdate = Pdate;
    }

    public String getPdes() {
        return Pdes;
    }

    public void setPdes(String Pdes) {
        this.Pdes = Pdes;
    }

    public String getPamount() {
        return Pamount;
    }

    public void setPamount(String Pamount) {
        this.Pamount = Pamount;
    }

    public String getTeleBIll() {
        return TeleBIll;
    }

    public void setTeleBIll(String TeleBIll) {
        this.TeleBIll = TeleBIll;
    }

    public String getElecBill() {
        return ElecBill;
    }

    public void setElecBill(String ElecBill) {
        this.ElecBill = ElecBill;
    }

    public String getAccNo() {
        return AccNo;
    }

    public void setAccNo(String AccNo) {
        this.AccNo = AccNo;
    }

    public String getFvouNo() {
        return FvouNo;
    }

    public void setFvouNo(String FvouNo) {
        this.FvouNo = FvouNo;
    }

    public String getOtherVNo() {
        return OtherVNo;
    }

    public void setOtherVNo(String OtherVNo) {
        this.OtherVNo = OtherVNo;
    }
  
    
 

}

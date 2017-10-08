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
public class BankList {
    private String Aid;
    private String Acc;
     private String BName;
    private String Descript;
    private String cost;
    private String date;
    public BankList(String Aid, String Acc, String BName, String Descript, String cost, String date) {
        this.Aid = Aid;
        this.Acc = Acc;
        this.BName = BName;
        this.Descript = Descript;
        this.cost = cost;
        this.date = date;
    }

    public String getAid() {
        return Aid;
    }

    public void setAid(String Aid) {
        this.Aid = Aid;
    }

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String Acc) {
        this.Acc = Acc;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public String getDescript() {
        return Descript;
    }

    public void setDescript(String Descript) {
        this.Descript = Descript;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
  

   

}

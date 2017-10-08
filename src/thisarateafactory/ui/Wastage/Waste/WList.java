/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Wastage.Waste;

/**
 *
 * @author ravindu
 */
public class WList {
    private String WasteID;
    private String RecieveDate;
    private String Source;
    private String WasteName;
    private String Type;
    private String Amount;
    
    public WList(String WasteID,String RecieveDate, String Source, String WasteName, String Type, String Amount) {
        this.WasteID=WasteID;
        this.RecieveDate = RecieveDate;
        this.Source = Source;
        this.WasteName = WasteName;
        this.Type = Type;
        this.Amount = Amount;
    }
    /**
     * @return the Recievedate
     */
    public String getRecieveDate() {
        return RecieveDate;
    }

    /**
     * @param Recievedate the Recievedate to set
     */
    public void setRecieveDate(String RecieveDate) {
        this.RecieveDate = RecieveDate;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return Source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String Source) {
        this.Source = Source;
    }

    /**
     * @return the Wastename
     */
    public String getWasteName() {
        return WasteName;
    }

    /**
     * @param Wastename the Wastename to set
     */
    public void setWasteName(String WasteName) {
        this.WasteName = WasteName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return Type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String Type) {
        this.Type = Type;
    }

    /**
     * @return the amount
     */
    public String getAmount() {
        return Amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return the WasteID
     */
    public String getWasteID() {
        return WasteID;
    }
    //public void setWasteID() {
      //  this.WasteID=WasteID;
    //}
}

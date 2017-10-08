    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Finance.thisara;

/**
 *
 * @author Chinthaka
 */
public class FinanceList {
    private String Id;
    private String type;
    private String date;
    private String amnt;

    public FinanceList(String Id, String type, String date, String amnt) {
        this.Id = Id;
        this.type = type;
        this.date = date;
        this.amnt =amnt;  
    }

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the amnt
     */
    public String getAmnt() {
        return amnt;
    }

    /**
     * @param amnt the amnt to set
     */
    public void setAmnt(String amnt) {
        this.amnt = amnt;
    }
    
    
} 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.Monthly;

/**
 *
 * @author user pc
 */
public class MBlist {
 
     private String bulkno;
     private String sid;
     private String noday;
     private String tuse;
     private String tnouse;
     private String totbulk;
     private String month;

    public MBlist(String bulkno, String sid, String noday, String tuse, String tnouse, String totbulk, String month) {
        this.bulkno = bulkno;
        this.sid = sid;
        this.noday = noday;
        this.tuse = tuse;
        this.tnouse = tnouse;
        this.totbulk = totbulk;
        this.month = month;
    }

    /**
     * @return the bulkno
     */
    public String getBulkno() {
        return bulkno;
    }

    /**
     * @param bulkno the bulkno to set
     */
    public void setBulkno(String bulkno) {
        this.bulkno = bulkno;
    }

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the noday
     */
    public String getNoday() {
        return noday;
    }

    /**
     * @param noday the noday to set
     */
    public void setNoday(String noday) {
        this.noday = noday;
    }

    /**
     * @return the tuse
     */
    public String getTuse() {
        return tuse;
    }

    /**
     * @param tuse the tuse to set
     */
    public void setTuse(String tuse) {
        this.tuse = tuse;
    }

    /**
     * @return the tnouse
     */
    public String getTnouse() {
        return tnouse;
    }

    /**
     * @param tnouse the tnouse to set
     */
    public void setTnouse(String tnouse) {
        this.tnouse = tnouse;
    }

    /**
     * @return the totbulk
     */
    public String getTotbulk() {
        return totbulk;
    }

    /**
     * @param totbulk the totbulk to set
     */
    public void setTotbulk(String totbulk) {
        this.totbulk = totbulk;
    }

    /**
     * @return the month
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(String month) {
        this.month = month;
    }
   
     
}

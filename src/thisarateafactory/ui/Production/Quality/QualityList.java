/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Quality;

/**
 *
 * @author Nipuni
 */
public class QualityList {
    
    private String indNo;
    private String Pid;
    private String Qlty;
    //private String sepDate;
    private String Gweight;
    private String SSize;
    private String NetW;
    private String qweight;

    public QualityList(String indNo, String Pid, String Qlty, String Gweight, String SSize, String NetW, String qweight) {
        this.indNo = indNo;
        this.Pid = Pid;
        this.Qlty = Qlty;
        this.Gweight = Gweight;
        this.SSize = SSize;
        this.NetW = NetW;
        this.qweight = qweight;
    }

    /**
     * @return the indNo
     */
    public String getIndNo() {
        return indNo;
    }

    /**
     * @param indNo the indNo to set
     */
    public void setIndNo(String indNo) {
        this.indNo = indNo;
    }

    /**
     * @return the Pid
     */
    public String getPid() {
        return Pid;
    }

    /**
     * @param Pid the Pid to set
     */
    public void setPid(String Pid) {
        this.Pid = Pid;
    }

    /**
     * @return the Qlty
     */
    public String getQlty() {
        return Qlty;
    }

    /**
     * @param Qlty the Qlty to set
     */
    public void setQlty(String Qlty) {
        this.Qlty = Qlty;
    }

    /**
     * @return the Gweight
     */
    public String getGweight() {
        return Gweight;
    }

    /**
     * @param Gweight the Gweight to set
     */
    public void setGweight(String Gweight) {
        this.Gweight = Gweight;
    }

    /**
     * @return the SSize
     */
    public String getSSize() {
        return SSize;
    }

    /**
     * @param SSize the SSize to set
     */
    public void setSSize(String SSize) {
        this.SSize = SSize;
    }

    /**
     * @return the NetW
     */
    public String getNetW() {
        return NetW;
    }

    /**
     * @param NetW the NetW to set
     */
    public void setNetW(String NetW) {
        this.NetW = NetW;
    }

    /**
     * @return the qweight
     */
    public String getQweight() {
        return qweight;
    }

    /**
     * @param qweight the qweight to set
     */
    public void setQweight(String qweight) {
        this.qweight = qweight;
    }
    
    

    }

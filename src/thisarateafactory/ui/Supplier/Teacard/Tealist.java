/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Supplier.Teacard;

/**
 *
 * @author user pc
 */
public class Tealist {
    
    private String turnno;
    private String tdate;
    private String supid;
    private String nocon;
    private String gross;
    private String wecon;
    private String water;
    private String mature;
    private String net;
    private String trans;
    //private String bulk;
    //private String pid;

    //public Tealist(String turnno, String tdate, String supid, String nocon, String gross, String wecon, String water, String mature, String net, String trans, String bulk, String pid) {
    public Tealist(String turnno, String tdate, String supid, String nocon, String gross, String wecon, String water, String mature, String net, String trans){
        this.turnno = turnno;
        this.tdate = tdate;
        this.supid = supid;
        this.nocon = nocon;
        this.gross = gross;
        this.wecon = wecon;
        this.water = water;
        this.mature = mature;
        this.net = net;
        this.trans = trans;
        //this.bulk = bulk;
        //this.pid = pid;
    }

    /**
     * @return the turnno
     */
    public String getTurnno() {
        return turnno;
    }

    /**
     * @param turnno the turnno to set
     */
    public void setTurnno(String turnno) {
        this.turnno = turnno;
    }

    /**
     * @return the tdate
     */
    public String getTdate() {
        
        return this.tdate;
    }

    /**
     * @param tdate the tdate to set
     */
    public void setTdate(String tdate) {
        this.tdate = tdate;
        
    }

    /**
     * @return the supid
     */
    public String getSupid() {
        return supid;
    }

    /**
     * @param supid the supid to set
     */
    public void setSupid(String supid) {
        this.supid = supid;
    }

    /**
     * @return the nocon
     */
    public String getNocon() {
        return nocon;
    }

    /**
     * @param nocon the nocon to set
     */
    public void setNocon(String nocon) {
        this.nocon = nocon;
    }

    /**
     * @return the gross
     */
    public String getGross() {
        return gross;
    }

    /**
     * @param gross the gross to set
     */
    public void setGross(String gross) {
        this.gross = gross;
    }

    /**
     * @return the wecon
     */
    public String getWecon() {
        return wecon;
    }

    /**
     * @param wecon the wecon to set
     */
    public void setWecon(String wecon) {
        this.wecon = wecon;
    }

    /**
     * @return the water
     */
    public String getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(String water) {
        this.water = water;
    }

    /**
     * @return the mature
     */
    public String getMature() {
        return mature;
    }

    /**
     * @param mature the mature to set
     */
    public void setMature(String mature) {
        this.mature = mature;
    }

    /**
     * @return the net
     */
    public String getNet() {
        return net;
    }

    /**
     * @param net the net to set
     */
    public void setNet(String net) {
        this.net = net;
    }

    /**
     * @return the trans
     */
    public String getTrans() {
        return trans;
    }

    /**
     * @param trans the trans to set
     */
    public void setTrans(String trans) {
        this.trans = trans;
    }

    /**
     * @return the bulk
     */
   // public String getBulk() {
   //     return bulk;
   // }

    /**
     * @param bulk the bulk to set
     */
  //  public void setBulk(String bulk) {
  //      this.bulk = bulk;
  //  }

    /**
     * @return the pid
     */
   // public String getPid() {
   //     return pid;
   // }

    /**
     * @param pid the pid to set
     */
  //  public void setPid(String pid) {
  //      this.pid = pid;
  //  }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.deductions;

import thisarateafactory.ui.Sales.dao.DeductionDAO;

/**
 *
 * @author Mihitha Hansani
 */
public class DeductionsTableModel {
    private String lotNo;
    private String date;
    private String noOfLots;
    private String month;
    private String year;
    private String PSE;
    private String HChg;
    private String chamberChg;
    private String VAT;
    private String brokerage;
    private String furtherIns;
    private String totalDeduction;

    DeductionsTableModel(DeductionDAO dao) {
        this.lotNo = dao.getLotNo() + "";
        this.date = dao.getDate().toString();
        this.noOfLots = dao.getNoOfLots()+ "";
        this.PSE = dao.getPSE() + "";
        this.HChg = dao.getHChg()+ "";
        this.chamberChg = dao.getChamberChg()+ "";
        this.VAT = dao.getVAT()+ "";
        this.brokerage = dao.getBrokerage()+ "";
        this.furtherIns = dao.getFurtherIns()+ "";
        this.totalDeduction = dao.getTotalDeduction()+ "";  
        this.month = dao.getMonth()+ "";
        this.year = dao.getYear()+ "";
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNoOfLots() {
        return noOfLots;
    }

    public void setNoOfLots(String noOfLots) {
        this.noOfLots = noOfLots;
    }

    public String getPSE() {
        return PSE;
    }

    public void setPSE(String PSE) {
        this.PSE = PSE;
    }

    public String getHChg() {
        return HChg;
    }

    public void setHChg(String HChg) {
        this.HChg = HChg;
    }

    public String getChamberChg() {
        return chamberChg;
    }

    public void setChamberChg(String chamberChg) {
        this.chamberChg = chamberChg;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getFurtherIns() {
        return furtherIns;
    }

    public void setFurtherIns(String furtherIns) {
        this.furtherIns = furtherIns;
    }

    public String getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(String totalDeduction) {
        this.totalDeduction = totalDeduction;
    }
    
    
}

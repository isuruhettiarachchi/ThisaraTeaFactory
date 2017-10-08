/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.finalizing;

import thisarateafactory.ui.Sales.dao.FinalizingDAO;

/**
 *
 * @author Keshan De Silva
 */
public class FinalizingTableModel {
    private String date;
    private String netProceed;
    private String deduction;
    private String grossProceed;
    private String month;
    private String lotNo;

    FinalizingTableModel(FinalizingDAO dao) {
        this.date = dao.getFinalizeDate().toString();
        this.netProceed = dao.getTotalNet() + "";
        this.deduction = dao.getTotalDeduction()+ "";
        this.grossProceed = dao.getTotalGross()+ "";
        this.month = dao.getMonth()+ "";
        this.lotNo = dao.getLotNumber()+ "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNetProceed() {
        return netProceed;
    }

    public void setNetProceed(String netProceed) {
        this.netProceed = netProceed;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public String getGrossProceed() {
        return grossProceed;
    }

    public void setGrossProceed(String grossProceed) {
        this.grossProceed = grossProceed;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }
    
    
}

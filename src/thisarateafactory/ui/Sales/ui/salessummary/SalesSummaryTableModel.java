/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.salessummary;

import thisarateafactory.ui.Sales.dao.SalesSummaryDAO;

/**
 *
 * @author Keshan De Silva
 */
public class SalesSummaryTableModel {
    private String lotNo;
    private String invoiceNo;
    private String buyerDetails;
    private String grade;
    private String chest;
    private String price;
    private String grossWeight;
    private String sampleWeight;
    private String netWeight;
    private String grossProceed;

    SalesSummaryTableModel(SalesSummaryDAO dao) {
        this.lotNo = dao.getLotNumber() + "";
        this.invoiceNo = dao.getInvoiceNumber()+ "";
        this.buyerDetails = dao.getBuyerDetails()+ "";
        this.grade = dao.getGrade()+ "";
        this.chest = dao.getChest()+ "";
        this.price = dao.getPrice()+ "";
        this.grossWeight = dao.getGrossWeight()+ "";
        this.sampleWeight = dao.getSampleWeight()+ "";
        this.netWeight = dao.getNetWeight()+ "";
        this.grossProceed = dao.getGrossProceed() + "";
    }

    public String getLotNo() {
        return lotNo;
    }

    public void setLotNo(String lotNo) {
        this.lotNo = lotNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getBuyerDetails() {
        return buyerDetails;
    }

    public void setBuyerDetails(String buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getSampleWeight() {
        return sampleWeight;
    }

    public void setSampleWeight(String sampleWeight) {
        this.sampleWeight = sampleWeight;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getGrossProceed() {
        return grossProceed;
    }

    public void setGrossProceed(String grossProceed) {
        this.grossProceed = grossProceed;
    }
   
    
}

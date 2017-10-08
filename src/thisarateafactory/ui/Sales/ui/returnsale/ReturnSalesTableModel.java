/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.ui.returnsale;

import thisarateafactory.ui.Sales.dao.ReturnSalesDAO;

/**
 *
 * @author Keshan De Silva
 */
public class ReturnSalesTableModel {
    private String date;
    private String lotNo;
    private String invoiceNo;
    private String description;
    private String weight;

    ReturnSalesTableModel(ReturnSalesDAO dao) {
        this.date = dao.getDate().toString();
        this.weight = dao.getWeight()+ "";
        this.description = dao.getDescription()+ "";
        this.invoiceNo = dao.getInvoiceNumber()+ "";
        this.lotNo = dao.getLotNumber()+ "";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    
}

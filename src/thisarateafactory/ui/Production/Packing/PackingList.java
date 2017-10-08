/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Production.Packing;

/**
 *
 * @author Nipuni
 */
public class PackingList {
    
     private String InvoiceNo;
     private String Grade;
     private String Quality;
     private String NoOfContainers;
     private String FullHalf;
     private String NetTotalQty;
     private String NetWeightEach;
     private String TotalGrossWeight;
     private String ContainerNo;
     private String TypeOfPacking;
     private String DateOfPacking;
     private String SampleAllowed;

    public PackingList(String InvoiceNo, String Grade, String Quality, String NoOfContainers, String FullHalf, String NetTotalQty, String NetWeightEach, String TotalGrossWeight, String ContainerNo, String TypeOfPacking, String DateOfPacking, String SampleAllowed) {
        this.InvoiceNo = InvoiceNo;
        this.Grade = Grade;
        this.Quality = Quality;
        this.NoOfContainers = NoOfContainers;
        this.FullHalf = FullHalf;
        this.NetTotalQty = NetTotalQty;
        this.NetWeightEach = NetWeightEach;
        this.TotalGrossWeight = TotalGrossWeight;
        this.ContainerNo = ContainerNo;
        this.TypeOfPacking = TypeOfPacking;
        this.DateOfPacking = DateOfPacking;
        this.SampleAllowed = SampleAllowed;
    }

    /**
     * @return the InvoiceNo
     */
    public String getInvoiceNo() {
        return InvoiceNo;
    }

    /**
     * @param InvoiceNo the InvoiceNo to set
     */
    public void setInvoiceNo(String InvoiceNo) {
        this.InvoiceNo = InvoiceNo;
    }

    /**
     * @return the Grade
     */
    public String getGrade() {
        return Grade;
    }

    /**
     * @param Grade the Grade to set
     */
    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    /**
     * @return the Quality
     */
    public String getQuality() {
        return Quality;
    }

    /**
     * @param Quality the Quality to set
     */
    public void setQuality(String Quality) {
        this.Quality = Quality;
    }

    /**
     * @return the NoOfContainers
     */
    public String getNoOfContainers() {
        return NoOfContainers;
    }

    /**
     * @param NoOfContainers the NoOfContainers to set
     */
    public void setNoOfContainers(String NoOfContainers) {
        this.NoOfContainers = NoOfContainers;
    }

    /**
     * @return the FullHalf
     */
    public String getFullHalf() {
        return FullHalf;
    }

    /**
     * @param FullHalf the FullHalf to set
     */
    public void setFullHalf(String FullHalf) {
        this.FullHalf = FullHalf;
    }

    /**
     * @return the NetTotalQty
     */
    public String getNetTotalQty() {
        return NetTotalQty;
    }

    /**
     * @param NetTotalQty the NetTotalQty to set
     */
    public void setNetTotalQty(String NetTotalQty) {
        this.NetTotalQty = NetTotalQty;
    }

    /**
     * @return the NetWeightEach
     */
    public String getNetWeightEach() {
        return NetWeightEach;
    }

    /**
     * @param NetWeightEach the NetWeightEach to set
     */
    public void setNetWeightEach(String NetWeightEach) {
        this.NetWeightEach = NetWeightEach;
    }

    /**
     * @return the TotalGrossWeight
     */
    public String getTotalGrossWeight() {
        return TotalGrossWeight;
    }

    /**
     * @param TotalGrossWeight the TotalGrossWeight to set
     */
    public void setTotalGrossWeight(String TotalGrossWeight) {
        this.TotalGrossWeight = TotalGrossWeight;
    }

    /**
     * @return the ContainerNo
     */
    public String getContainerNo() {
        return ContainerNo;
    }

    /**
     * @param ContainerNo the ContainerNo to set
     */
    public void setContainerNo(String ContainerNo) {
        this.ContainerNo = ContainerNo;
    }

    /**
     * @return the TypeOfPacking
     */
    public String getTypeOfPacking() {
        return TypeOfPacking;
    }

    /**
     * @param TypeOfPacking the TypeOfPacking to set
     */
    public void setTypeOfPacking(String TypeOfPacking) {
        this.TypeOfPacking = TypeOfPacking;
    }

    /**
     * @return the DateOfPacking
     */
    public String getDateOfPacking() {
        return DateOfPacking;
    }

    /**
     * @param DateOfPacking the DateOfPacking to set
     */
    public void setDateOfPacking(String DateOfPacking) {
        this.DateOfPacking = DateOfPacking;
    }

    /**
     * @return the SampleAllowed
     */
    public String getSampleAllowed() {
        return SampleAllowed;
    }

    /**
     * @param SampleAllowed the SampleAllowed to set
     */
    public void setSampleAllowed(String SampleAllowed) {
        this.SampleAllowed = SampleAllowed;
    }
     
     
     
    
}

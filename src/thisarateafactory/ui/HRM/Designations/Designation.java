/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.Designations;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class Designation {
    private final SimpleStringProperty designation;
    private SimpleStringProperty rate;
    private SimpleDoubleProperty OTRate;
    
    Designation(String designation, String rate, Double OTRate){
        this.designation = new SimpleStringProperty(designation);
        this.rate = new SimpleStringProperty(rate);
        this.OTRate = new SimpleDoubleProperty(OTRate);
    }

    public String getDesignation() {
        return designation.get();
    }

    public String getRate() {
        return rate.get();
    }
    
    public Double getOTRate() {
        return OTRate.get();
    }
    
    public void setRate(String ra){
        this.rate = new SimpleStringProperty(ra);
    }
    
    public void setOTRate(Double ra){
        this.OTRate = new SimpleDoubleProperty(ra);
    }
}

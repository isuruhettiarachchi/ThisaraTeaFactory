/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.ViewEmployee;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class Employee {
    SimpleIntegerProperty eid;
    SimpleStringProperty nic;
    SimpleStringProperty name;
    SimpleStringProperty designation;
    SimpleStringProperty address;
    SimpleStringProperty gender;
    SimpleStringProperty birthdate;
    SimpleStringProperty phone;
    SimpleStringProperty appDate;
    SimpleStringProperty etfDate;
    SimpleStringProperty resginDate;

    public Employee(int eid, String nic, String name, String designation, String address, String gender, String birthdate, String phone, String appDate, String etfDate, String resginDate) {
        this.eid = new SimpleIntegerProperty(eid);
        this.nic = new SimpleStringProperty(nic);
        this.name = new SimpleStringProperty(name);
        this.designation = new SimpleStringProperty(designation);
        this.address = new SimpleStringProperty(address);
        this.gender = new SimpleStringProperty(gender);
        this.birthdate = new SimpleStringProperty(birthdate);
        this.phone = new SimpleStringProperty(phone);
        this.appDate = new SimpleStringProperty(appDate);
        this.etfDate = new SimpleStringProperty(etfDate);
        this.resginDate = new SimpleStringProperty(resginDate);
    }

    public int getEid() {
        return eid.get();
    }

    public String getNic() {
        return nic.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDesignation() {
        return designation.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getGender() {
        return gender.get();
    }

    public String getBirthdate() {
        return birthdate.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getAppDate() {
        return appDate.get();
    }

    public String getEtfDate() {
        return etfDate.get();
    }

    public String getResginDate() {
        return resginDate.get();
    }
    
}

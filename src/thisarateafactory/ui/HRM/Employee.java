/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class Employee {
        private final SimpleIntegerProperty employeeID;
        private final SimpleStringProperty NIC;
        private final SimpleStringProperty name;
        private final SimpleStringProperty designation;
    
        public Employee(int eid, String NIC, String name, String desgination){
            this.employeeID = new SimpleIntegerProperty(eid);
            this.NIC = new SimpleStringProperty(NIC);
            this.name = new SimpleStringProperty(name);
            this.designation = new SimpleStringProperty(desgination);
        }

        public int getEmployeeID() {
            return employeeID.get();
        }

        public String getNIC() {
            return NIC.get();
        }

        public String getName() {
            return name.get();
        }

        public String getDesignation() {
            return designation.get();
        }
}

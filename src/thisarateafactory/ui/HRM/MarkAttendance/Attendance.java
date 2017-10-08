/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.MarkAttendance;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class Attendance {
    private final SimpleStringProperty currentDate;
    private final SimpleIntegerProperty employeeID;
    private final SimpleStringProperty name;
    private SimpleStringProperty inTime;
    private SimpleStringProperty outTime;
    private final SimpleIntegerProperty WorkedHours;
    private final SimpleIntegerProperty OTHours;
    
    public Attendance(String currentDate, int employeeID, String name, String inTime, String outTime, int WorkedHours, int OTHours) {
        this.currentDate = new SimpleStringProperty(currentDate);
        this.employeeID = new SimpleIntegerProperty(employeeID);
        this.name = new SimpleStringProperty(name);
        this.inTime = new SimpleStringProperty(inTime);
        this.outTime = new SimpleStringProperty(outTime);
        this.WorkedHours = new SimpleIntegerProperty(WorkedHours);
        this.OTHours = new SimpleIntegerProperty(OTHours);
    }

    public String getCurrentDate() {
        return currentDate.get();
    }

    public int getEmployeeID() {
        return employeeID.get();
    }

    public String getName() {
        return name.get();
    }

    public String getInTime() {
        return inTime.get();
    }

    public String getOutTime() {
        return outTime.get();
    }

    public int getWorkedHours() {
        return WorkedHours.get();
    }

    public int getOTHours() {
        return OTHours.get();
    }

    public void setInTime(String inT){
        this.inTime = new SimpleStringProperty(inT);
    }
    
    public void setOutTime(String outT){
        this.outTime = new SimpleStringProperty(outT);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.MarkAttendance;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class summaryFromDay {
    private final SimpleStringProperty eid;
    private final SimpleStringProperty name;
    private final SimpleStringProperty inTime;
    private final SimpleStringProperty outTime;
    private final SimpleStringProperty WorkdHours;
    private final SimpleStringProperty OTHours;

    public summaryFromDay(String eid, String name, String inTime, String outTime, String WorkdHours, String OTHours) {
        this.eid = new SimpleStringProperty(eid);
        this.name = new SimpleStringProperty(name);
        this.inTime = new SimpleStringProperty(inTime);
        this.outTime = new SimpleStringProperty(outTime);
        this.WorkdHours = new SimpleStringProperty(WorkdHours);
        this.OTHours = new SimpleStringProperty(OTHours);
    }

    public String getEid() {
        return eid.get();
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

    public String getWorkdHours() {
        return WorkdHours.get();
    }

    public String getOTHours() {
        return OTHours.get();
    }
    
    
    
}

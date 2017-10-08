/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.HRM.CalculateSalary;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class Salary {
    private final SimpleIntegerProperty eid;
    private final SimpleStringProperty name;
    private final SimpleStringProperty startDate;
    private final SimpleStringProperty endDate;
    private final SimpleDoubleProperty workDays;
    private final SimpleIntegerProperty OT;
    private final SimpleDoubleProperty deductions;
    private final SimpleDoubleProperty bonus;
    private final SimpleDoubleProperty salary;

    public Salary(int eid, String name, String stDate, String edDate, double workDays, int OT, double deductions, double bonus, double salary) {
        this.eid = new SimpleIntegerProperty(eid);
        this.name = new SimpleStringProperty(name);
        this.startDate = new SimpleStringProperty(stDate);
        this.endDate = new SimpleStringProperty(edDate);
        this.workDays = new SimpleDoubleProperty(workDays);
        this.OT = new SimpleIntegerProperty(OT);
        this.deductions = new SimpleDoubleProperty(deductions);
        this.bonus = new SimpleDoubleProperty(bonus);
        this.salary = new SimpleDoubleProperty(salary);
    }

    public int getEid() {
        return eid.get();
    }

    public String getName() {
        return name.get();
    }

    public String getStartDate() {
        return startDate.get();
    }

    public String getEndDate() {
        return endDate.get();
    }

    public double getWorkDays() {
        return workDays.get();
    }

    public int getOT() {
        return OT.get();
    }

    public double getDeductions() {
        return deductions.get();
    }

    public double getBonus() {
        return bonus.get();
    }

    public double getSalary() {
        return salary.get();
    }

}

package thisarateafactory.ui.Transport.Maintain;

public class maintainList {
    private String maintainid;
    private String date;
    private String description;
    private String cost;
    private String vehicleid;
    
    public maintainList(String maintainid, String date, String description, String cost, String vehicleid) {
        this.maintainid = maintainid;
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.vehicleid = vehicleid;
    }

    public String getMaintainid() {
        return maintainid;
    }

    public void setMaintainid(String maintainid) {
        this.maintainid = maintainid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getVehicleid() {
        return vehicleid;
    }

    public void setVehicleid(String vehicleid) {
        this.vehicleid = vehicleid;
    }

}

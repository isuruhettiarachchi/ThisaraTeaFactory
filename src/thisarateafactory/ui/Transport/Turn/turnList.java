package thisarateafactory.ui.Transport.Turn;

public class turnList {
    private String turnID;
    private String driverID;
    private String asstdrvID;
    private String vehicleID;
    private String date;
    private String type;
    private String route;
    private String distance;
    private String fuelcost;
    private String otherExp;
    
    public turnList(String turnID, String driverID, String asstdrvID, String vehicleID, String date, String type, String route, String distance, String fuelcost, String otherExp) {
        this.turnID = turnID;
        this.driverID = driverID;
        this.asstdrvID = asstdrvID;
        this.vehicleID = vehicleID;
        this.date = date;
        this.type = type;
        this.route = route;
        this.distance = distance;
        this.fuelcost = fuelcost;
        this.otherExp = otherExp;
    }    

    public String getTurnID() {
        return turnID;
    }

    public void setTurnID(String turnID) {
        this.turnID = turnID;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getAsstdrvID() {
        return asstdrvID;
    }

    public void setAsstdrvID(String asstdrvID) {
        this.asstdrvID = asstdrvID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFuelcost() {
        return fuelcost;
    }

    public void setFuelcost(String fuelcost) {
        this.fuelcost = fuelcost;
    }

    public String getOtherExp() {
        return otherExp;
    }

    public void setOtherExp(String otherExp) {
        this.otherExp = otherExp;
    }
}

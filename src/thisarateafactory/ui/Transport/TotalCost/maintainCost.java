package thisarateafactory.ui.Transport.TotalCost;

public class maintainCost {
    private String year;
    private String month;
    private String cost;

    public maintainCost(String year, String month, String cost) {
        this.year = year;
        this.month = month;
        this.cost = cost;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

}

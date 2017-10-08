package thisarateafactory.ui.Transport.TotalCost;

public class totaltransportcost {
    private String year;
    private String month;
    private String fcost;
    private String otherExp;
    private String total;
    
    public totaltransportcost(String year, String month, String fcost, String otherExp, String total) {
        this.year = year;
        this.month = month;
        this.fcost = fcost;
        this.otherExp = otherExp;
        this.total = total;
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

    public String getFcost() {
        return fcost;
    }

    public void setFcost(String fcost) {
        this.fcost = fcost;
    }

    public String getOtherExp() {
        return otherExp;
    }

    public void setOtherExp(String otherExp) {
        this.otherExp = otherExp;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}

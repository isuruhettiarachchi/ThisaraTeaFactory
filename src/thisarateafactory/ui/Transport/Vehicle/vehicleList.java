package thisarateafactory.ui.Transport.Vehicle;
public class vehicleList {
    private String v_id;
    private String v_type;
    private String v_num;
    private String storeCap;
    private String mile;

    public vehicleList(String v_id, String v_type, String v_num, String storeCap, String mile) {
        this.v_id = v_id;
        this.v_type = v_type;
        this.v_num = v_num;
        this.storeCap = storeCap;
        this.mile = mile;
    }

    /**
     * @return the v_id
     */
    public String getV_id() {
        return v_id;
    }

    /**
     * @param v_id the v_id to set
     */
    public void setV_id(String v_id) {
        this.v_id = v_id;
    }

    /**
     * @return the v_type
     */
    public String getV_type() {
        return v_type;
    }

    /**
     * @param v_type the v_type to set
     */
    public void setV_type(String v_type) {
        this.v_type = v_type;
    }

    /**
     * @return the v_num
     */
    public String getV_num() {
        return v_num;
    }

    /**
     * @param v_num the v_num to set
     */
    public void setV_num(String v_num) {
        this.v_num = v_num;
    }

    /**
     * @return the storeCap
     */
    public String getStoreCap() {
        return storeCap;
    }

    /**
     * @param storeCap the storeCap to set
     */
    public void setStoreCap(String storeCap) {
        this.storeCap = storeCap;
    }

    /**
     * @return the mile
     */
    public String getMile() {
        return mile;
    }

    /**
     * @param mile the mile to set
     */
    public void setMile(String mile) {
        this.mile = mile;
    }
    
}

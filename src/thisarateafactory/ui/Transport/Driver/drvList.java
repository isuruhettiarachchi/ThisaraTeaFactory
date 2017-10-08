package thisarateafactory.ui.Transport.Driver;

public class drvList {
      private String did;
      private String dname;
      private String daddr;
      private String status;
      private String licenseno;
      private String nic;
      private String mobileno;

    public drvList(String did, String dname, String daddr, String status, String licenseno, String nic, String mobileno) {
        this.did = did;
        this.dname = dname;
        this.daddr = daddr;
        this.status = status;
        this.licenseno = licenseno;
        this.nic = nic;
        this.mobileno = mobileno;
    }

    /**
     * @return the did
     */
    public String getDid() {
        return did;
    }

    /**
     * @param did the did to set
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * @return the dname
     */
    public String getDname() {
        return dname;
    }

    /**
     * @param dname the dname to set
     */
    public void setDname(String dname) {
        this.dname = dname;
    }

    /**
     * @return the daddr
     */
    public String getDaddr() {
        return daddr;
    }

    /**
     * @param daddr the daddr to set
     */
    public void setDaddr(String daddr) {
        this.daddr = daddr;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the licenseno
     */
    public String getLicenseno() {
        return licenseno;
    }

    /**
     * @param licenseno the licenseno to set
     */
    public void setLicenseno(String licenseno) {
        this.licenseno = licenseno;
    }

    /**
     * @return the nic
     */
    public String getNic() {
        return nic;
    }

    /**
     * @param nic the nic to set
     */
    public void setNic(String nic) {
        this.nic = nic;
    }

    /**
     * @return the mobileno
     */
    public String getMobileno() {
        return mobileno;
    }

    /**
     * @param mobileno the mobileno to set
     */
    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }
      
}

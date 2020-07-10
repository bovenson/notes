package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizElectricitySubstation {
    private Integer id;

    private String substationname;

    private Integer companyid;

    private String address;

    private String longitude;

    private String latitude;

    private String powertype;

    private String voltageclass;

    private Integer total;

    private String constructionunit;

    private Date constructiontime;

    private String involtage;

    private String outvoltage;

    private String pic;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubstationname() {
        return substationname;
    }

    public void setSubstationname(String substationname) {
        this.substationname = substationname;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPowertype() {
        return powertype;
    }

    public void setPowertype(String powertype) {
        this.powertype = powertype;
    }

    public String getVoltageclass() {
        return voltageclass;
    }

    public void setVoltageclass(String voltageclass) {
        this.voltageclass = voltageclass;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getConstructionunit() {
        return constructionunit;
    }

    public void setConstructionunit(String constructionunit) {
        this.constructionunit = constructionunit;
    }

    public Date getConstructiontime() {
        return constructiontime;
    }

    public void setConstructiontime(Date constructiontime) {
        this.constructiontime = constructiontime;
    }

    public String getInvoltage() {
        return involtage;
    }

    public void setInvoltage(String involtage) {
        this.involtage = involtage;
    }

    public String getOutvoltage() {
        return outvoltage;
    }

    public void setOutvoltage(String outvoltage) {
        this.outvoltage = outvoltage;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
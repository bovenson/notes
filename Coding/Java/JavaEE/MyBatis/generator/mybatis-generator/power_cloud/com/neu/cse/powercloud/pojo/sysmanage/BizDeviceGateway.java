package com.neu.cse.powercloud.pojo.sysmanage;

public class BizDeviceGateway {
    private Integer id;

    private String gatewayname;

    private String manufacturer;

    private String mac;

    private String subjectid;

    private Integer electricitysubstationid;

    private String gatewayusr;

    private String gatewaypsw;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGatewayname() {
        return gatewayname;
    }

    public void setGatewayname(String gatewayname) {
        this.gatewayname = gatewayname;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public Integer getElectricitysubstationid() {
        return electricitysubstationid;
    }

    public void setElectricitysubstationid(Integer electricitysubstationid) {
        this.electricitysubstationid = electricitysubstationid;
    }

    public String getGatewayusr() {
        return gatewayusr;
    }

    public void setGatewayusr(String gatewayusr) {
        this.gatewayusr = gatewayusr;
    }

    public String getGatewaypsw() {
        return gatewaypsw;
    }

    public void setGatewaypsw(String gatewaypsw) {
        this.gatewaypsw = gatewaypsw;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
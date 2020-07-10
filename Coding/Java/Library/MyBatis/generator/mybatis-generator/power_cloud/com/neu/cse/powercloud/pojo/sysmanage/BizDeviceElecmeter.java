package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizDeviceElecmeter {
    private Integer id;

    private String manufacturer;

    private String manufacturercode;

    private Date manufacturdate;

    private Integer gatewaycode;

    private String biztype;

    private String circuitname;

    private Integer gatewayid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturercode() {
        return manufacturercode;
    }

    public void setManufacturercode(String manufacturercode) {
        this.manufacturercode = manufacturercode;
    }

    public Date getManufacturdate() {
        return manufacturdate;
    }

    public void setManufacturdate(Date manufacturdate) {
        this.manufacturdate = manufacturdate;
    }

    public Integer getGatewaycode() {
        return gatewaycode;
    }

    public void setGatewaycode(Integer gatewaycode) {
        this.gatewaycode = gatewaycode;
    }

    public String getBiztype() {
        return biztype;
    }

    public void setBiztype(String biztype) {
        this.biztype = biztype;
    }

    public String getCircuitname() {
        return circuitname;
    }

    public void setCircuitname(String circuitname) {
        this.circuitname = circuitname;
    }

    public Integer getGatewayid() {
        return gatewayid;
    }

    public void setGatewayid(Integer gatewayid) {
        this.gatewayid = gatewayid;
    }
}
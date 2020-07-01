package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizDeviceHighcabinet {
    private Integer id;

    private Integer electricitysubstationid;

    private String lowcabinettype;

    private String manufacturer;

    private String highcabinetmodel;

    private Date manufacturdate;

    private String specifications;

    private String pic;

    private String diagram;

    private String voltageclass;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElectricitysubstationid() {
        return electricitysubstationid;
    }

    public void setElectricitysubstationid(Integer electricitysubstationid) {
        this.electricitysubstationid = electricitysubstationid;
    }

    public String getLowcabinettype() {
        return lowcabinettype;
    }

    public void setLowcabinettype(String lowcabinettype) {
        this.lowcabinettype = lowcabinettype;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHighcabinetmodel() {
        return highcabinetmodel;
    }

    public void setHighcabinetmodel(String highcabinetmodel) {
        this.highcabinetmodel = highcabinetmodel;
    }

    public Date getManufacturdate() {
        return manufacturdate;
    }

    public void setManufacturdate(Date manufacturdate) {
        this.manufacturdate = manufacturdate;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDiagram() {
        return diagram;
    }

    public void setDiagram(String diagram) {
        this.diagram = diagram;
    }

    public String getVoltageclass() {
        return voltageclass;
    }

    public void setVoltageclass(String voltageclass) {
        this.voltageclass = voltageclass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
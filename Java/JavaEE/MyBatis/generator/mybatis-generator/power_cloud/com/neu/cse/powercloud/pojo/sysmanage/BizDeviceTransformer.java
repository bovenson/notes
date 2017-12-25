package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizDeviceTransformer {
    private Integer id;

    private String manufacturer;

    private String manufacturercode;

    private String transformermodel;

    private Integer weight;

    private String workenviorment;

    private String worktype;

    private String ratedvoltage;

    private String ratedfrequency;

    private String connectiongrouplabel;

    private String insulationclass;

    private Date manufacturdate;

    private Integer gatewaycode;

    private String biztype;

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

    public String getTransformermodel() {
        return transformermodel;
    }

    public void setTransformermodel(String transformermodel) {
        this.transformermodel = transformermodel;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getWorkenviorment() {
        return workenviorment;
    }

    public void setWorkenviorment(String workenviorment) {
        this.workenviorment = workenviorment;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getRatedvoltage() {
        return ratedvoltage;
    }

    public void setRatedvoltage(String ratedvoltage) {
        this.ratedvoltage = ratedvoltage;
    }

    public String getRatedfrequency() {
        return ratedfrequency;
    }

    public void setRatedfrequency(String ratedfrequency) {
        this.ratedfrequency = ratedfrequency;
    }

    public String getConnectiongrouplabel() {
        return connectiongrouplabel;
    }

    public void setConnectiongrouplabel(String connectiongrouplabel) {
        this.connectiongrouplabel = connectiongrouplabel;
    }

    public String getInsulationclass() {
        return insulationclass;
    }

    public void setInsulationclass(String insulationclass) {
        this.insulationclass = insulationclass;
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

    public Integer getGatewayid() {
        return gatewayid;
    }

    public void setGatewayid(Integer gatewayid) {
        this.gatewayid = gatewayid;
    }
}
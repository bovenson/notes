package com.neu.cse.powercloud.pojo.sysmanage;

public class BizDeviceException {
    private Integer id;

    private Integer electricitysubstationid;

    private Integer deviceid;

    private Integer num;

    private String condation;

    private String value1;

    private String value2;

    private String exceptioncode;

    private String desc;

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

    public Integer getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(Integer deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getCondation() {
        return condation;
    }

    public void setCondation(String condation) {
        this.condation = condation;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getExceptioncode() {
        return exceptioncode;
    }

    public void setExceptioncode(String exceptioncode) {
        this.exceptioncode = exceptioncode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
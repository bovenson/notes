package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizException {
    private Integer id;

    private String exceptioncode;

    private String desc;

    private Integer electricitysubstationid;

    private Date datetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getElectricitysubstationid() {
        return electricitysubstationid;
    }

    public void setElectricitysubstationid(Integer electricitysubstationid) {
        this.electricitysubstationid = electricitysubstationid;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizElectricianPosition {
    private Integer id;

    private Integer electricianid;

    private String lacation;

    private Date datatime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getElectricianid() {
        return electricianid;
    }

    public void setElectricianid(Integer electricianid) {
        this.electricianid = electricianid;
    }

    public String getLacation() {
        return lacation;
    }

    public void setLacation(String lacation) {
        this.lacation = lacation;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }
}
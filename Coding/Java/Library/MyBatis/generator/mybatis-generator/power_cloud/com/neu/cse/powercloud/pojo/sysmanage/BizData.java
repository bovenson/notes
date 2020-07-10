package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizData {
    private Integer id;

    private Integer gatewayid;

    private String jsondata;

    private Date datatime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGatewayid() {
        return gatewayid;
    }

    public void setGatewayid(Integer gatewayid) {
        this.gatewayid = gatewayid;
    }

    public String getJsondata() {
        return jsondata;
    }

    public void setJsondata(String jsondata) {
        this.jsondata = jsondata;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }
}
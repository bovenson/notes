package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizMagdomain {
    private Integer id;

    private String magdomain;

    private Integer createrid;

    private Date createtime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMagdomain() {
        return magdomain;
    }

    public void setMagdomain(String magdomain) {
        this.magdomain = magdomain;
    }

    public Integer getCreaterid() {
        return createrid;
    }

    public void setCreaterid(Integer createrid) {
        this.createrid = createrid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
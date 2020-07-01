package com.neu.cse.powercloud.pojo.sysmanage;

public class BizMagdomainElectrician {
    private Integer id;

    private Integer magdomainid;

    private Integer electricianid;

    private Integer post;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMagdomainid() {
        return magdomainid;
    }

    public void setMagdomainid(Integer magdomainid) {
        this.magdomainid = magdomainid;
    }

    public Integer getElectricianid() {
        return electricianid;
    }

    public void setElectricianid(Integer electricianid) {
        this.electricianid = electricianid;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
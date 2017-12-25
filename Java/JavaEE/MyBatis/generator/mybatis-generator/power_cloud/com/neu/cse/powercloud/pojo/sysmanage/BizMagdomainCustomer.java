package com.neu.cse.powercloud.pojo.sysmanage;

public class BizMagdomainCustomer {
    private Integer id;

    private Integer magdomainid;

    private Integer companyid;

    private String post;

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

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
package com.neu.cse.powercloud.pojo.sysmanage;

import java.util.Date;

public class BizWorktaskFinish {
    private Integer id;

    private String exceptioncode;

    private String description;

    private String reason;

    private String processaction;

    private String pic;

    private Integer electricianid;

    private Integer companyid;

    private Integer electricitysubstationid;

    private Integer commenter;

    private String score;

    private String comment;

    private Date starttime;

    private Date accepttime;

    private Date finishedtime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProcessaction() {
        return processaction;
    }

    public void setProcessaction(String processaction) {
        this.processaction = processaction;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getElectricianid() {
        return electricianid;
    }

    public void setElectricianid(Integer electricianid) {
        this.electricianid = electricianid;
    }

    public Integer getCompanyid() {
        return companyid;
    }

    public void setCompanyid(Integer companyid) {
        this.companyid = companyid;
    }

    public Integer getElectricitysubstationid() {
        return electricitysubstationid;
    }

    public void setElectricitysubstationid(Integer electricitysubstationid) {
        this.electricitysubstationid = electricitysubstationid;
    }

    public Integer getCommenter() {
        return commenter;
    }

    public void setCommenter(Integer commenter) {
        this.commenter = commenter;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getAccepttime() {
        return accepttime;
    }

    public void setAccepttime(Date accepttime) {
        this.accepttime = accepttime;
    }

    public Date getFinishedtime() {
        return finishedtime;
    }

    public void setFinishedtime(Date finishedtime) {
        this.finishedtime = finishedtime;
    }
}
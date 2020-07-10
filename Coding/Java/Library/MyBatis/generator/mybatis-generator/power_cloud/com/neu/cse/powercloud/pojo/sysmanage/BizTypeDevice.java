package com.neu.cse.powercloud.pojo.sysmanage;

public class BizTypeDevice {
    private Integer id;

    private String typedevicename;

    private String functionname;

    private String gatewaycommand;

    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypedevicename() {
        return typedevicename;
    }

    public void setTypedevicename(String typedevicename) {
        this.typedevicename = typedevicename;
    }

    public String getFunctionname() {
        return functionname;
    }

    public void setFunctionname(String functionname) {
        this.functionname = functionname;
    }

    public String getGatewaycommand() {
        return gatewaycommand;
    }

    public void setGatewaycommand(String gatewaycommand) {
        this.gatewaycommand = gatewaycommand;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
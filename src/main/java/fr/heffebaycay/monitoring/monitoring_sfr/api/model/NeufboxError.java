package fr.heffebaycay.monitoring.monitoring_sfr.api.model;

import org.simpleframework.xml.Attribute;

public class NeufboxError {

    @Attribute(name = "code")
    protected int code;

    @Attribute(name = "msg")
    protected String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}

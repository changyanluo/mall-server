package com.platform.mall.bean;

import java.io.Serializable;
import java.util.Date;

public class SysConfig implements Serializable {
    private String variable;

    private String value;

    private Date setTime;

    private String setBy;

    private static final long serialVersionUID = 1L;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getSetTime() {
        return setTime;
    }

    public void setSetTime(Date setTime) {
        this.setTime = setTime;
    }

    public String getSetBy() {
        return setBy;
    }

    public void setSetBy(String setBy) {
        this.setBy = setBy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", variable=").append(variable);
        sb.append(", value=").append(value);
        sb.append(", setTime=").append(setTime);
        sb.append(", setBy=").append(setBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
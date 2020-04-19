package com.platform.mall.bean;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
    private Long id;

    private String userName;

    private String actionName;

    private String actionUrl;

    private String messageIncoming;

    private String messageReturned;

    private Date createTime;

    private Integer timespan;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionUrl() {
        return actionUrl;
    }

    public void setActionUrl(String actionUrl) {
        this.actionUrl = actionUrl;
    }

    public String getMessageIncoming() {
        return messageIncoming;
    }

    public void setMessageIncoming(String messageIncoming) {
        this.messageIncoming = messageIncoming;
    }

    public String getMessageReturned() {
        return messageReturned;
    }

    public void setMessageReturned(String messageReturned) {
        this.messageReturned = messageReturned;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getTimespan() {
        return timespan;
    }

    public void setTimespan(Integer timespan) {
        this.timespan = timespan;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", actionName=").append(actionName);
        sb.append(", actionUrl=").append(actionUrl);
        sb.append(", messageIncoming=").append(messageIncoming);
        sb.append(", messageReturned=").append(messageReturned);
        sb.append(", createTime=").append(createTime);
        sb.append(", timespan=").append(timespan);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
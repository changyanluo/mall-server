package com.platform.mall.dto;

import java.io.Serializable;
import java.util.List;

//redis中的用户缓存信息
public class UserCache implements Serializable {
    long userId;
    String userName;
    List<String> authorities;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}

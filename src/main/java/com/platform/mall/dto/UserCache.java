package com.platform.mall.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List;

public class UserCache implements Serializable {
    String userName;
    List<String> authorities;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"userName\":\"");
        sb.append(userName);
        sb.append("\",\"authorities\":[");
        for(int i = 0;i < authorities.size();i++){
            sb.append("\"");
            sb.append(authorities.get(i));
            sb.append("\"");
            if(i != authorities.size() - 1)
                sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}

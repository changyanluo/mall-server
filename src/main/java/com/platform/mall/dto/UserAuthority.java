package com.platform.mall.dto;

import com.platform.mall.bean.SysActionAuthority;

import java.util.List;

//用户权限树形数据
public class UserAuthority extends SysActionAuthority {
    private List<UserAuthority> children;

    public List<UserAuthority> getChildren() {
        return children;
    }

    public void setChildren(List<UserAuthority> children) {
        this.children = children;
    }
}

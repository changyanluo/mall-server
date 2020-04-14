package com.platform.mall.dto;

import com.platform.mall.bean.SysMenu;
import java.util.List;

public class UserMenu extends SysMenu {
    private List<UserMenu> children;

    public List<UserMenu> getChildren() {
        return children;
    }

    public void setChildren(List<UserMenu> children) {
        this.children = children;
    }
}

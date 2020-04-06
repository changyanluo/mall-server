package com.platform.mall.service;

import com.platform.mall.bean.SysMenu;
import com.platform.mall.dto.UserMenu;

import java.util.List;

public interface MenuService {

    //获取菜单列表
    List<UserMenu> getList(String name);

    //添加菜单
    int insert(SysMenu menu);

    //更新菜单
    int update(SysMenu menu);

    //删除
    int deletebyId(long id);
}

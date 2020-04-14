package com.platform.mall.service;

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.dto.UserAuthority;

import java.util.List;

public interface AuthorityService {
    //获取权限列表
    List<UserAuthority> getList(String name);

    //添加权限
    int insert(SysActionAuthority authority);

    //更新权限
    int update(SysActionAuthority authority);
}

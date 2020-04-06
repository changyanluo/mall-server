package com.platform.mall.service;

import com.platform.mall.bean.SysRole;
import com.platform.mall.bean.SysUser;
import com.platform.mall.component.PageList;

import java.util.List;

//用户服务接口
public interface UserService {

    //用户登录
    String login(String userName,String password);

    //登出
    int logoutbyUserName(String userName);

    //分页获取用户列表
    PageList<SysUser> getList(String name, int pageIndex, int pageSize);

    //新增用户
    int insert(SysUser user);

    //更新用户信息
    int update(SysUser user);

    //删除
    int deletebyId(long id);

    //获取用户角色
    List<SysRole> getUserRoleList(long userId);

    //更新用户角色
    int updateUserRole(long userId,List<Long> roleIdList);
}

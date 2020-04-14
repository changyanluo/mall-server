package com.platform.mall.service;

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.bean.SysMenu;
import com.platform.mall.bean.SysRole;
import com.platform.mall.component.PageList;
import com.platform.mall.dto.UserMenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//角色服务接口
public interface RoleService {

    //获取角色列表
    List<SysRole> getList(String name);

    //分页获取角色列表
    PageList<SysRole> getList(String name, int pageIndex, int pageSize);

    //新增角色
    int insert(SysRole role);

    //更新角色信息
    int update(SysRole role);

    //删除
    int deletebyId(long id);

    //获取角色权限
    List<SysActionAuthority> getRoleAuthority(long roleId);

    //更新角色权限
    @Transactional
    int updateRoleAuthority(long roleId,List<Long> authorityIds);

    //获取角色菜单
    List<SysMenu> getRoleMenu(long roleId);

    //更新角色菜单
    @Transactional
    int updateRoleMenu(long roleId,List<Long> menuIds);
}

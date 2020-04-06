package com.platform.mall.dao;

//用户自定义dao

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.bean.SysMenu;
import com.platform.mall.bean.SysRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserDao {

    List<SysRole> getRolebyUserId(@Param("userId") long userId);

    List<SysMenu> getMenubyRoleId(@Param("roleId") long roleId);

    List<SysActionAuthority> getAuthoritybyRoleId(@Param("roleId") long roleId);

    int batchInsertUserRoleRelation(@Param("userId") long userId,@Param("list") List<Long> roleIds);

    int batchInsertRoleMenuRelation(@Param("roleId") long roleId,@Param("list")List<Long> menuIds);

    int batchInsertRoleAuthorityRelation(@Param("roleId") long roleId,@Param("list")List<Long> authorityIds);
}

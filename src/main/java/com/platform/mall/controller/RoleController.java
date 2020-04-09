package com.platform.mall.controller;

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.bean.SysMenu;
import com.platform.mall.bean.SysRole;
import com.platform.mall.bean.SysUser;
import com.platform.mall.component.PageList;
import com.platform.mall.component.Result;
import com.platform.mall.dto.UserMenu;
import com.platform.mall.service.RoleService;
import com.platform.mall.service.impl.MenuServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "RoleController",description = "用户角色服务接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuServiceImpl menuService;

    @ApiOperation("分页获取角色列表")
    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST)
    public Result<PageList<SysRole>> getRoleList(@RequestParam(value = "roleName", required = false) String roleName,
                                                 @RequestParam("pageIndex") int pageIndex,
                                                 @RequestParam("pageSize") int pageSize) {
        return Result.success(roleService.getList(roleName, pageIndex, pageSize));
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/getAllRole", method = RequestMethod.POST)
    public Result<List<SysRole>> getAllRole(@RequestParam(value = "roleName", required = false) String roleName) {
        return Result.success(roleService.getList(roleName));
    }

    @ApiOperation("获取角色菜单")
    @RequestMapping(value = "/getRoleMenuList", method = RequestMethod.POST)
    public Result<List<SysMenu>> getRoleMenuList(@RequestParam("roleId") long roleId) {
        List<SysMenu> menus = roleService.getRoleMenu(roleId);
        return Result.success(menus);
    }

    @ApiOperation("获取角色权限按钮")
    @RequestMapping(value = "/getRoleAuthorityList", method = RequestMethod.POST)
    public Result<List<SysActionAuthority>> getRoleAuthorityList(@RequestParam("roleId") long roleId) {
        return Result.success(roleService.getRoleAuthority(roleId));
    }

    @ApiOperation("新增角色")
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public Result addRole(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        return Result.success(roleService.insert(sysRole));
    }

    @ApiOperation("角色编辑")
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public Result updateRole(@RequestBody SysRole sysRole) {
        return Result.success(roleService.update(sysRole));
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/deleteRole", method = RequestMethod.POST)
    public Result deleteRole(@RequestParam("roleId") long roleId) {
        return Result.success(roleService.deletebyId(roleId));
    }

    @ApiOperation("设置角色菜单")
    @RequestMapping(value = "/setRoleMenu",method = RequestMethod.POST)
    public Result setUserRole(@RequestParam("roleId") long roleId,
                              @RequestParam("menuIdList") List<Long> menuIdList){

        return Result.success(roleService.updateRoleMenu(roleId,menuIdList));
    }
}

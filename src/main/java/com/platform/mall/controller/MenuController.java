package com.platform.mall.controller;

import com.platform.mall.bean.SysMenu;
import com.platform.mall.component.Result;
import com.platform.mall.dto.UserMenu;
import com.platform.mall.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "MenuController",description = "菜单服务接口")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取所有菜单")
    @RequestMapping(value = "/getMenuList", method = RequestMethod.POST)
    public Result<List<UserMenu>> getMenuList(@RequestParam(value = "menuName", required = false) String menuName) {
        return Result.success(menuService.getList(menuName));
    }

    @ApiOperation("新增菜单")
    @RequestMapping(value = "/addMenu", method = RequestMethod.POST)
    public Result addMenu(@RequestBody SysMenu sysMenu) {
        sysMenu.setCreateTime(new Date());
        return Result.success(menuService.insert(sysMenu));
    }

    @ApiOperation("菜单编辑")
    @RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
    public Result updateMenu(@RequestBody SysMenu sysMenu) {
        return Result.success(menuService.update(sysMenu));
    }

    @ApiOperation("删除菜单")
    @RequestMapping(value = "/deleteMenu", method = RequestMethod.POST)
    public Result deleteMenu(@RequestParam("menu") long menuId) {
        return Result.success(menuService.deletebyId(menuId));
    }
}

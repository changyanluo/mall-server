package com.platform.mall.controller;

import com.platform.mall.bean.*;
import com.platform.mall.common.PageList;
import com.platform.mall.common.Result;
import com.platform.mall.dto.UserMenu;
import com.platform.mall.service.MessageService;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.UserService;
import com.platform.mall.service.impl.MenuServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Api(tags = "UserController",description = "用户服务接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuServiceImpl menuService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RedisService redisService;

    @ApiOperation("用户登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<List<String>> login(@RequestParam("userName") String userName,@RequestParam("password") String password)
    {
        //登录成功后返回该用户的操作权限
        List<String> authorities = userService.login(userName,password);
        if(authorities == null)
            return Result.failed("用户名或密码不正确");
        else {
            return Result.success(RequestContextHolder.getRequestAttributes().getSessionId(), authorities);
        }
    }

    @ApiOperation("用户登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public Result<String> logout(HttpServletRequest request)
    {
        String userName = (String)request.getAttribute("userName");
        if(!redisService.hasKey(userName)){
            return Result.failed("该用户已登出！");
        }
        //删除redis中的用户信息
        String token = redisService.get(userName).toString();
        redisService.del(token);
        redisService.del(userName);
        return Result.success("登出成功!");
    }

    @ApiOperation("分页获取用户列表")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public Result<PageList<SysUser>> getUserList(@RequestParam(value = "userName",required = false) String userName,
                                                 @RequestParam("pageIndex") int pageIndex,
                                                 @RequestParam("pageSize") int pageSize){
        return Result.success(userService.getList(userName,pageIndex,pageSize));
    }

    @ApiOperation("获取用户角色")
    @RequestMapping(value = "/getUserRoleList",method = RequestMethod.POST)
    public Result<List<SysRole>> getUserRoleList(@RequestParam(value = "userId") long userId){
        return Result.success(userService.getUserRoleList(userId));
    }

    @ApiOperation("获取用户菜单")
    @RequestMapping(value = "/getUserMenuList",method = RequestMethod.POST)
    public Result<List<UserMenu>> getUserMenuList(@RequestParam(value = "userId") long userId){
        List<SysMenu>  menus = userService.getUserMenuList(userId);
        return Result.success(menuService.treeList((menus)));
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Result addUser(@RequestBody SysUser sysUser){
        sysUser.setCreateTime(new Date());
        return Result.success(userService.insert(sysUser));
    }

    @ApiOperation("用户编辑")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Result updateUser(@RequestBody SysUser sysUser){
        return Result.success(userService.update(sysUser));
    }

    @ApiOperation("删除用户")
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Result deleteUser(@RequestParam("userId") long userid){
        return Result.success(userService.deletebyId(userid));
    }

    @ApiOperation("设置用户角色")
    @RequestMapping(value = "/setUserRole",method = RequestMethod.POST)
    public Result setUserRole(@RequestParam("userId") long userId,
                              @RequestParam("roleIdList") List<Long> roleIdList){

        return Result.success(userService.updateUserRole(userId,roleIdList));
    }

    @ApiOperation("获取用户消息列表")
    @RequestMapping(value = "/getUserMessage",method = RequestMethod.POST)
    public Result<List<SysMessage>> getUserMessage(@RequestParam("userName") String userName){
        return Result.success(messageService.getUserMessages(userName));
    }

    @ApiOperation("获取用户操作日志")
    @RequestMapping(value = "/getOperationLog",method = RequestMethod.POST)
    public Result<PageList<SysLog>> getOperationLog(@RequestParam("userName") String userName,
                                                @RequestParam("actionName") String actionName,
                                                @RequestParam("pageIndex") int pageIndex,
                                                @RequestParam("pageSize") int pageSize){
        return Result.success(messageService.getOperationLog(userName,actionName,pageIndex,pageSize));
    }
}

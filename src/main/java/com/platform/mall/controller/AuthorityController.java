package com.platform.mall.controller;

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.common.Result;
import com.platform.mall.dto.UserAuthority;
import com.platform.mall.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Api(tags = "AuthorityController",description = "权限服务接口")
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @ApiOperation("获取所有权限")
    @RequestMapping(value = "/getAuthorityList", method = RequestMethod.POST)
    public Result<List<UserAuthority>> getAuthorityList(@RequestParam(value = "authorityName", required = false) String authorityName) {
        return Result.success(authorityService.getList(authorityName));
    }

    @ApiOperation("新增权限")
    @RequestMapping(value = "/addAuthority", method = RequestMethod.POST)
    public Result addAuthority(@RequestBody SysActionAuthority sysActionAuthority) {
        sysActionAuthority.setCreateTime(new Date());
        return Result.success(authorityService.insert(sysActionAuthority));
    }

    @ApiOperation("权限编辑")
    @RequestMapping(value = "/updateAuthority", method = RequestMethod.POST)
    public Result updateAuthority(@RequestBody SysActionAuthority sysActionAuthority) {
        return Result.success(authorityService.update(sysActionAuthority));
    }
}

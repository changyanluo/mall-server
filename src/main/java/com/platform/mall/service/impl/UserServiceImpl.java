package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.*;
import com.platform.mall.component.PageList;
import com.platform.mall.dao.UserDao;
import com.platform.mall.dto.UserCache;
import com.platform.mall.mapper.SysUserMapper;
import com.platform.mall.mapper.SysUserRoleMapper;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public List<String> login(String userName, String password) {
        //校验账号密码
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        example.createCriteria().andPasswordEqualTo(DigestUtils.md5DigestAsHex(password.getBytes()));
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size() > 0){
            //获取用户权限
            List<SysActionAuthority> authorities = userDao.getAuthoritybyUserId(
                    sysUsers.get(0).getId()
            );
            List<String> authNameList = authorities.stream().map(e->e.getName()).collect(Collectors.toList());
            List<String> authValueList = authorities.stream()
                    .filter(e->!"".equals(e.getValue()))
                    .map(e->e.getValue())
                    .collect(Collectors.toList());
            //将用户名和操作权限存入redis
            UserCache userCache = new UserCache();
            userCache.setUserName(userName);
            userCache.setAuthorities(authValueList);
            userCache.setUserId(sysUsers.get(0).getId());
            String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
            //实现单点登录，查看redis中是否有该用户的信息,
            if(redisService.hasKey(userName)){
                String token = redisService.get(userName).toString();
                redisService.del(token);
            }
            redisService.set(sessionId,userCache);
            redisService.set(userName,sessionId);
            return authNameList;
        }
        else
            return null;
    }

    @Override
    public int logoutbyUserName(String userName) {
        return 0;
    }

    @Override
    public PageList<SysUser> getList(String name, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize,true);
        SysUserExample example = new SysUserExample();
        if(!StringUtils.isEmpty(name)){
            example.createCriteria().andUserNameLike("%" + name + "%");
        }
        List<SysUser> users = sysUserMapper.selectByExample(example);
        return PageList.getPageList(users);
    }

    @Override
    public int insert(SysUser user) {
        return sysUserMapper.insert(user);
    }

    @Override
    public int update(SysUser user) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdEqualTo(user.getId());
        return sysUserMapper.updateByExample(user,example);
    }

    @Override
    public int deletebyId(long id) {
        return sysUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysMenu> getUserMenuList(long userId) {
        return userDao.getMenubyUserId((userId));
    }

    @Override
    public List<SysRole> getUserRoleList(long userId) {
        return userDao.getRolebyUserId(userId);
    }

    @Override
    public int updateUserRole(long userId, List<Long> roleIdList) {
        //先删除该用户的所有角色
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andUserIdEqualTo(userId);
        sysUserRoleMapper.deleteByExample(example);
        //然后插入角色列表
        return userDao.batchInsertUserRoleRelation(userId,roleIdList);
    }
}

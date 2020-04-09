package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.*;
import com.platform.mall.component.PageList;
import com.platform.mall.dao.UserDao;
import com.platform.mall.mapper.SysUserMapper;
import com.platform.mall.mapper.SysUserRoleMapper;
import com.platform.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public String login(String userName, String password) {
        SysUserExample example = new SysUserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        example.createCriteria().andPasswordEqualTo(Base64.getEncoder().encodeToString(password.getBytes()));
        List<SysUser> sysUsers = sysUserMapper.selectByExample(example);
        if(sysUsers.size() > 0) return "";
        else return null;
    }

    @Override
    public int logoutbyUserName(String userName) {
        return 0;
    }

    @Override
    public PageList<SysUser> getList(String name, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
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
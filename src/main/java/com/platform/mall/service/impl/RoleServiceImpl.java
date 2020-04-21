package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.*;
import com.platform.mall.component.PageList;
import com.platform.mall.dao.UserDao;
import com.platform.mall.mapper.SysRoleAuthorityMapper;
import com.platform.mall.mapper.SysRoleMapper;
import com.platform.mall.mapper.SysRoleMenuMapper;
import com.platform.mall.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private SysRoleAuthorityMapper sysRoleAuthorityMapper;

    @Override
    public List<SysRole> getList(String name) {
        SysRoleExample example = new SysRoleExample();
        if(!StringUtils.isEmpty(name)){
            example.createCriteria().andRoleNameLike("%" + name + "%");
        }
        return sysRoleMapper.selectByExample(example);
    }

    @Override
    public PageList<SysRole> getList(String name, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize,true);
        return PageList.getPageList(getList(name));
    }

    @Override
    public int insert(SysRole role) {
        return sysRoleMapper.insertSelective(role);
    }

    @Override
    public int update(SysRole role) {
        return sysRoleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public int deletebyId(long id) {
        return sysRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SysActionAuthority> getRoleAuthority(long roleId) {
        return userDao.getAuthoritybyRoleId(roleId);
    }

    @Override
    public int updateRoleAuthority(long roleId, List<Long> authorityIds) {
        //先删除角色所有权限
        SysRoleAuthorityExample example = new SysRoleAuthorityExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        sysRoleAuthorityMapper.deleteByExample(example);
        //再插入权限列表
        return userDao.batchInsertRoleAuthorityRelation(roleId,authorityIds);
    }

    @Override
    public List<SysMenu> getRoleMenu(long roleId) {
        return userDao.getMenubyRoleId(roleId);
    }

    @Override
    public int updateRoleMenu(long roleId, List<Long> menuIdList) {
        //先删除角色菜单
        SysRoleMenuExample example = new SysRoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        sysRoleMenuMapper.deleteByExample(example);
        //再插入菜单列表
        return userDao.batchInsertRoleMenuRelation(roleId,menuIdList);
    }
}

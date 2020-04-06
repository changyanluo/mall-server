package com.platform.mall.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.platform.mall.bean.SysMenu;
import com.platform.mall.bean.SysMenuExample;
import com.platform.mall.dto.UserMenu;
import com.platform.mall.mapper.SysMenuMapper;
import com.platform.mall.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private SysMenuMapper sysMenuMapper;


    @Override
    public List<UserMenu> getList(String name) {
        SysMenuExample example = new SysMenuExample();
        if(StringUtil.isNotEmpty(name)){
            example.createCriteria().andMenuNameLike("%" + name + "%");
        }
        List<SysMenu> menuList = sysMenuMapper.selectByExample(example);
        return treeList(menuList);
    }

    @Override
    public int insert(SysMenu menu) {
        return sysMenuMapper.insertSelective(menu);
    }

    @Override
    public int update(SysMenu menu) {
        return sysMenuMapper.updateByPrimaryKey(menu);
    }

    @Override
    public int deletebyId(long id) {
        int delSelf =  sysMenuMapper.deleteByPrimaryKey(id);
        //删除子菜单
        SysMenuExample example = new SysMenuExample();
        example.createCriteria().andParentIdEqualTo(id);
        int delChildren = sysMenuMapper.deleteByExample(example);
        return delSelf + delChildren;
    }

    //将菜单转换为树形数据
    public List<UserMenu> treeList(List<SysMenu> menuList){
        return menuList.stream()
                .filter(ele->ele.getParentId() == null)
                .map(ele->treeNode(ele,menuList))
                .collect(Collectors.toList());
    }

    public UserMenu treeNode(SysMenu menu,List<SysMenu> list){
        UserMenu userMenu = new UserMenu();
        BeanUtils.copyProperties(menu,userMenu);
        List<UserMenu> children = list.stream()
                .filter(ele->menu.getId().equals(ele.getParentId()))
                .map(ele->treeNode(ele,list)).collect(Collectors.toList());
        userMenu.setChildren(children);
        return userMenu;
    }
}

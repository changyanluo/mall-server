package com.platform.mall.service.impl;

import com.github.pagehelper.util.StringUtil;
import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.bean.SysActionAuthorityExample;
import com.platform.mall.dto.UserAuthority;
import com.platform.mall.mapper.SysActionAuthorityMapper;
import com.platform.mall.service.AuthorityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    SysActionAuthorityMapper authorityMapper;

    @Override
    public List<UserAuthority> getList(String name) {
        SysActionAuthorityExample example = new SysActionAuthorityExample();
        if(StringUtil.isNotEmpty(name)){
            example.createCriteria().andNameLike("%" + name + "%");
        }
        List<SysActionAuthority> authorityList = authorityMapper.selectByExample(example);
        return treeList(authorityList);
    }

    @Override
    public int insert(SysActionAuthority authority) {
        return authorityMapper.insert(authority);
    }

    @Override
    public int update(SysActionAuthority authority) {
        return authorityMapper.updateByPrimaryKey(authority);
    }

    //将权限转换为树形数据
    public List<UserAuthority> treeList(List<SysActionAuthority> authorityList){
        return authorityList.stream()
                .filter(ele->ele.getParentId() == null)
                .map(ele->treeNode(ele,authorityList))
                .collect(Collectors.toList());
    }

    public UserAuthority treeNode(SysActionAuthority authority,List<SysActionAuthority> list){
        UserAuthority userAuthority = new UserAuthority();
        BeanUtils.copyProperties(authority,userAuthority);
        List<UserAuthority> children = list.stream()
                .filter(ele->authority.getId().equals(ele.getParentId()))
                .map(ele->treeNode(ele,list)).collect(Collectors.toList());
        userAuthority.setChildren(children);
        return userAuthority;
    }
}

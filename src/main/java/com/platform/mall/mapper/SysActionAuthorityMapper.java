package com.platform.mall.mapper;

import com.platform.mall.bean.SysActionAuthority;
import com.platform.mall.bean.SysActionAuthorityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysActionAuthorityMapper {
    long countByExample(SysActionAuthorityExample example);

    int deleteByExample(SysActionAuthorityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysActionAuthority record);

    int insertSelective(SysActionAuthority record);

    List<SysActionAuthority> selectByExample(SysActionAuthorityExample example);

    SysActionAuthority selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysActionAuthority record, @Param("example") SysActionAuthorityExample example);

    int updateByExample(@Param("record") SysActionAuthority record, @Param("example") SysActionAuthorityExample example);

    int updateByPrimaryKeySelective(SysActionAuthority record);

    int updateByPrimaryKey(SysActionAuthority record);
}
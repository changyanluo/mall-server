package com.platform.mall.mapper;

import com.platform.mall.bean.SysMessageUser;
import com.platform.mall.bean.SysMessageUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMessageUserMapper {
    long countByExample(SysMessageUserExample example);

    int deleteByExample(SysMessageUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMessageUser record);

    int insertSelective(SysMessageUser record);

    List<SysMessageUser> selectByExample(SysMessageUserExample example);

    SysMessageUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMessageUser record, @Param("example") SysMessageUserExample example);

    int updateByExample(@Param("record") SysMessageUser record, @Param("example") SysMessageUserExample example);

    int updateByPrimaryKeySelective(SysMessageUser record);

    int updateByPrimaryKey(SysMessageUser record);
}
package com.platform.mall.mapper;

import com.platform.mall.bean.SysMessage;
import com.platform.mall.bean.SysMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMessageMapper {
    long countByExample(SysMessageExample example);

    int deleteByExample(SysMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysMessage record);

    int insertSelective(SysMessage record);

    List<SysMessage> selectByExample(SysMessageExample example);

    SysMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByExample(@Param("record") SysMessage record, @Param("example") SysMessageExample example);

    int updateByPrimaryKeySelective(SysMessage record);

    int updateByPrimaryKey(SysMessage record);
}
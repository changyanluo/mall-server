package com.platform.mall.mapper;

import com.platform.mall.bean.MallFlashSale;
import com.platform.mall.bean.MallFlashSaleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallFlashSaleMapper {
    long countByExample(MallFlashSaleExample example);

    int deleteByExample(MallFlashSaleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallFlashSale record);

    int insertSelective(MallFlashSale record);

    List<MallFlashSale> selectByExample(MallFlashSaleExample example);

    MallFlashSale selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallFlashSale record, @Param("example") MallFlashSaleExample example);

    int updateByExample(@Param("record") MallFlashSale record, @Param("example") MallFlashSaleExample example);

    int updateByPrimaryKeySelective(MallFlashSale record);

    int updateByPrimaryKey(MallFlashSale record);
}
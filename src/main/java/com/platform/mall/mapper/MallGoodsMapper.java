package com.platform.mall.mapper;

import com.platform.mall.bean.MallGoods;
import com.platform.mall.bean.MallGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MallGoodsMapper {
    long countByExample(MallGoodsExample example);

    int deleteByExample(MallGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MallGoods record);

    int insertSelective(MallGoods record);

    List<MallGoods> selectByExampleWithBLOBs(MallGoodsExample example);

    List<MallGoods> selectByExample(MallGoodsExample example);

    MallGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MallGoods record, @Param("example") MallGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") MallGoods record, @Param("example") MallGoodsExample example);

    int updateByExample(@Param("record") MallGoods record, @Param("example") MallGoodsExample example);

    int updateByPrimaryKeySelective(MallGoods record);

    int updateByPrimaryKeyWithBLOBs(MallGoods record);

    int updateByPrimaryKey(MallGoods record);
}
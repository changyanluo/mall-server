package com.platform.mall.dao;

import com.platform.mall.dto.FlashMessage;
import com.platform.mall.dto.FlashOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleDao {

    List<FlashOrder> getFlashGoods(@Param("flashMessage") FlashMessage flashMessage);

    int updateGoodsStateById(@Param("goodsId")long goodsId,@Param("state")int state);

    int decreaseGoodsStock(@Param("goodsId")long goodsId);
}

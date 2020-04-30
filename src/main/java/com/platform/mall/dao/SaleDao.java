package com.platform.mall.dao;

import com.platform.mall.dto.FlashGoods;
import com.platform.mall.dto.FlashMessage;
import com.platform.mall.dto.FlashOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleDao {

    //根据秒杀消息获取商品信息
    List<FlashOrder> getFlashGoods(@Param("flashMessage") FlashMessage flashMessage);

    int updateGoodsStateById(@Param("goodsId")long goodsId,@Param("state")int state);

    //扣减商品库存
    int decreaseGoodsStock(@Param("goodsId")long goodsId);

    //获取包含当前时间的秒杀活动
    List<FlashGoods> getCustomerFlashGoods();
}

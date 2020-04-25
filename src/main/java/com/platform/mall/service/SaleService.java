package com.platform.mall.service;

import com.platform.mall.bean.MallFlashSale;
import com.platform.mall.bean.MallGoods;
import com.platform.mall.bean.MallOrder;
import com.platform.mall.common.PageList;
import org.springframework.transaction.annotation.Transactional;

//商城服务接口
public interface SaleService {

    //分页获取商品列表
    PageList<MallGoods> getGoodsList(String goodsName, int pageIndex, int pageSize);

    //分页获取订单列表
    PageList<MallOrder> getOrderList(String goodsName, int pageIndex, int pageSize);

    //添加商品
    int addGoods(MallGoods goods);

    //编辑商品
    int updateGoods(MallGoods goods);

    //删除商品
    int deleteGoods(long goodsId);

    //添加秒杀商品
    @Transactional
    int addFlashGoods(MallFlashSale mallFlashSale);
}

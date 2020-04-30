package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.*;
import com.platform.mall.common.PageList;
import com.platform.mall.common.Util;
import com.platform.mall.dao.SaleDao;
import com.platform.mall.dto.FlashGoods;
import com.platform.mall.mapper.MallFlashSaleMapper;
import com.platform.mall.mapper.MallGoodsMapper;
import com.platform.mall.mapper.MallOrderMapper;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private MallGoodsMapper mallGoodsMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private MallFlashSaleMapper mallFlashSaleMapper;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private RedisService redisService;

    @Override
    public PageList<MallGoods> getGoodsList(String goodsName, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize,true);
        MallGoodsExample example = new MallGoodsExample();
        if(!StringUtils.isEmpty(goodsName)){
            example.createCriteria().andGoodsNameLike("%" + goodsName + "%");
        }
        List<MallGoods> list = mallGoodsMapper.selectByExampleWithBLOBs(example);
        return PageList.getPageList(list);
    }

    @Override
    public PageList<MallOrder> getOrderList(String goodsName, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize,true);
        MallOrderExample example = new MallOrderExample();
        if(!StringUtils.isEmpty(goodsName)){
            example.createCriteria().andGoodsNameLike("%" + goodsName + "%");
        }
        List<MallOrder> list = mallOrderMapper.selectByExample(example);
        return PageList.getPageList(list);
    }

    @Override
    public int addGoods(MallGoods goods) {
        return mallGoodsMapper.insert(goods);
    }

    @Override
    public int updateGoods(MallGoods goods) {
        return mallGoodsMapper.updateByPrimaryKeyWithBLOBs(goods);
    }

    @Override
    public int deleteGoods(long goodsId) {
        return mallGoodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public int addFlashGoods(MallFlashSale mallFlashSale) {
        mallFlashSaleMapper.insert(mallFlashSale);
        //更新商品状态为'秒杀中'
        saleDao.updateGoodsStateById(mallFlashSale.getGoodsId(),1);
        /*将秒杀商品的信息存入redis,包括库存，开始时间和结束时间,用map存储*/
        try {
            Date now = new Date();
            String key = Util.FLASH_GOODS_PREFIX + mallFlashSale.getGoodsId();
            long duration = (mallFlashSale.getEndDate().getTime() - now.getTime())/1000;
            Map<String,Object> goodsMap = new HashMap<>();
            goodsMap.put("stock",mallFlashSale.getStockCount());
            goodsMap.put("startDate",mallFlashSale.getStartDate());
            goodsMap.put("endDate",mallFlashSale.getEndDate());
            redisService.hSetAll(key,goodsMap,duration);
        }
        catch (Exception ex){
            //秒杀信息写入redis失败，返回0
            return 0;
        }
        return 1;
    }

    @Override
    public List<FlashGoods> getCustomerFlashGoods() {
        //查询包含当前时间的秒杀活动
        return saleDao.getCustomerFlashGoods();
    }
}

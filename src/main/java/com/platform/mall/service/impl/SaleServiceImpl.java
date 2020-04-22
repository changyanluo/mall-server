package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.*;
import com.platform.mall.component.PageList;
import com.platform.mall.component.Util;
import com.platform.mall.dao.UserDao;
import com.platform.mall.mapper.MallFlashSaleMapper;
import com.platform.mall.mapper.MallGoodsMapper;
import com.platform.mall.mapper.MallOrderMapper;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private MallGoodsMapper mallGoodsMapper;
    @Autowired
    private MallOrderMapper mallOrderMapper;
    @Autowired
    private MallFlashSaleMapper mallFlashSaleMapper;
    @Autowired
    private UserDao userDao;
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
        userDao.updateGoodsStateById(mallFlashSale.getGoodsId(),1);
        /*将秒杀商品的信息存入redis,是以flashGoods{商品编号}为key的hash,
        hash中存储秒杀数量，秒杀价格
        */
        try {
            String key = Util.FLASH_GOODS_PREFIX + mallFlashSale.getGoodsId();
            long duration = (mallFlashSale.getEndDate().getTime() - mallFlashSale.getStartDate().getTime())/1000;
            Map<String,Object> map = new HashMap<>();
            map.put("price",mallFlashSale.getFlashPrice());
            map.put("stock",mallFlashSale.getStockCount());
            redisService.hSetAll(key,map,duration);
        }
        catch (Exception ex){
            //秒杀信息写入redis失败，返回0
            return 0;
        }
        return 1;
    }
}

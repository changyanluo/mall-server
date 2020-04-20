package com.platform.mall.service.impl;

import com.platform.mall.bean.*;
import com.platform.mall.component.PageList;
import com.platform.mall.dao.UserDao;
import com.platform.mall.mapper.MallFlashSaleMapper;
import com.platform.mall.mapper.MallGoodsMapper;
import com.platform.mall.mapper.MallOrderMapper;
import com.platform.mall.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public PageList<MallGoods> getGoodsList(String goodsName, int pageIndex, int pageSize) {
        MallGoodsExample example = new MallGoodsExample();
        if(!StringUtils.isEmpty(goodsName)){
            example.createCriteria().andGoodsNameLike("%" + goodsName + "%");
        }
        List<MallGoods> list = mallGoodsMapper.selectByExample(example);
        return PageList.getPageList(list);
    }

    @Override
    public PageList<MallOrder> getOrderList(String goodsName, int pageIndex, int pageSize) {
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
        return mallGoodsMapper.updateByPrimaryKey(goods);
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
        return 1;
    }
}

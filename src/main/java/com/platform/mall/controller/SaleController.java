package com.platform.mall.controller;

import com.platform.mall.bean.MallFlashSale;
import com.platform.mall.bean.MallGoods;
import com.platform.mall.bean.MallOrder;
import com.platform.mall.common.PageList;
import com.platform.mall.common.Result;
import com.platform.mall.common.Util;
import com.platform.mall.dao.SaleDao;
import com.platform.mall.dto.FlashMessage;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "SaleController",description = "商城服务接口")
@RestController
@RequestMapping("/sale")
public class SaleController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SaleService saleService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @ApiOperation("秒杀")
    @RequestMapping(value = "/flash", method = RequestMethod.POST)
    public Result flash(@RequestParam("goodsId") long goodsId,
                        @RequestParam("flashId") long flashId,
                        HttpServletRequest request) throws Exception{
        //获取分布式锁，每个用户每秒钟最多访问2次
        Date now = new Date();
        String userName = (String)request.getAttribute("userName");
        if(!redisService.getDistributedLock(Util.FLASH_USER_PREFIX + userName)){
            return Result.failed("三秒防刷!");
        }
        //读取redis信息，先判断是否在秒杀时间内，再预减库存
        String goodsKey = Util.FLASH_GOODS_PREFIX + goodsId;
        Map<Object,Object> goodsCache = redisService.hGetAll(goodsKey);
        Date startDate = (Date)goodsCache.get("startDate");
        Date endDate = (Date)goodsCache.get("endDate");
        if(startDate.after(now)|| endDate.before(now)){
            return Result.failed("不再秒杀时间范围内!");
        }
        Long stock = redisService.hDecr(goodsKey,"stock",1L);
        if(stock < 0){
            saleDao.updateGoodsStateById(goodsId,0);
            return Result.failed("已被抢光!");
        }
        //redis减库存后发消息到kafka异步生成订单
        FlashMessage message = new FlashMessage();
        message.setFlashId(flashId);
        message.setGoodsId(goodsId);
        message.setUserName(userName);
        String serizeMsg = Util.toJsonString(message);
        //把秒杀的商品信息传到消息队列异步下单
        kafkaTemplate.send("flashSale",serizeMsg);
        return Result.success("");
    }

    @ApiOperation("分页获取商品列表")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Result<PageList<MallGoods>> getGoodsList(@RequestParam(value = "goodsName",required = false) String goodsName,
                                                    @RequestParam("pageIndex") int pageIndex,
                                                    @RequestParam("pageSize") int pageSize) {
        return Result.success(saleService.getGoodsList(goodsName,pageIndex,pageSize));
    }

    @ApiOperation("分页获取订单列表")
    @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
    public Result<PageList<MallOrder>> getOrderList(@RequestParam(value = "goodsName",required = false) String goodsName,
                                                    @RequestParam("pageIndex") int pageIndex,
                                                    @RequestParam("pageSize") int pageSize) {
        return Result.success(saleService.getOrderList(goodsName,pageIndex,pageSize));
    }

    @ApiOperation("添加商品")
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public Result addGoods(@RequestBody MallGoods goods) {
        return Result.success(saleService.addGoods(goods));
    }

    @ApiOperation("编辑商品")
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public Result updateGoods(@RequestBody MallGoods goods) {
        return Result.success(saleService.updateGoods(goods));
    }

    @ApiOperation("删除商品")
    @RequestMapping(value = "/deleteGoods", method = RequestMethod.POST)
    public Result deleteGoods(@RequestParam("goodsId") long goodsId) {
        return Result.success(saleService.deleteGoods(goodsId));
    }

    @ApiOperation("添加秒杀商品")
    @RequestMapping(value = "/addFlashGoods", method = RequestMethod.POST)
    public Result addFlashGoods(@RequestBody MallFlashSale mallFlashSale) {
        int ret = saleService.addFlashGoods(mallFlashSale);
        if(ret == 1){
            return Result.success(ret);
        }
        else{
            return Result.failed("秒杀信息写入redis失败!");
        }
    }

    @ApiOperation("获取用户待秒杀商品")
    @RequestMapping(value = "/getCustomerFlashGoods", method = RequestMethod.POST)
    public Result getCustomerFlashGoods() {
            return Result.success(saleService.getCustomerFlashGoods());
    }
}

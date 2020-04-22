package com.platform.mall.controller;

import com.platform.mall.bean.MallFlashSale;
import com.platform.mall.bean.MallGoods;
import com.platform.mall.bean.MallOrder;
import com.platform.mall.component.PageList;
import com.platform.mall.component.Result;
import com.platform.mall.component.Util;
import com.platform.mall.service.RedisService;
import com.platform.mall.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "SaleController",description = "商城服务接口")
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;
    @Autowired
    private RedisService redisService;

    @ApiOperation("秒杀")
    @RequestMapping(value = "/flash", method = RequestMethod.POST)
    public Result flash(@RequestParam("goodsId") long goodsId, HttpServletRequest request) {
        //获取分布式锁，每个用户每秒钟最多访问2次
        Long userId = (Long)request.getAttribute("userId");
        if(!redisService.getDistributedLock(Util.FLASH_USER_PREFIX + userId)){
            return Result.failed("三秒防刷!");
        }
        String goodsKey = Util.FLASH_GOODS_PREFIX + goodsId;
        //预减库存
        Long stock = redisService.hDecr(goodsKey,"stock", 1L);
        if(stock < 0){
            return Result.failed("已被抢光!");
        }
        return Result.failed("三秒防刷!");
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
}

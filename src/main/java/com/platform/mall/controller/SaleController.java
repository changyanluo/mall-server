package com.platform.mall.controller;


import com.platform.mall.bean.MallGoods;
import com.platform.mall.component.PageList;
import com.platform.mall.component.Result;
import com.platform.mall.service.SaleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "SaleController",description = "商城服务接口")
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @ApiOperation("分页获取商品列表")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.POST)
    public Result<PageList<MallGoods>> getGoodsList(@RequestParam(value = "goodsName",required = false) String goodsName,
                                                    @RequestParam("pageIndex") int pageIndex,
                                                    @RequestParam("pageSize") int pageSize) {
        return Result.success(saleService.getGoodsList(goodsName,pageIndex,pageSize));
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
    public Result deleteGoods(@RequestBody long goodsId) {
        return Result.success(saleService.deleteGoods(goodsId));
    }
}

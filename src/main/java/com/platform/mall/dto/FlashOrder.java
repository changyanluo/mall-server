package com.platform.mall.dto;

import java.math.BigDecimal;

public class FlashOrder {
    private String goodsName;
    private BigDecimal price;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

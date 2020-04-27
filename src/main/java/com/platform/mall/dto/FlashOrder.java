package com.platform.mall.dto;

import java.math.BigDecimal;

//创建订单需要的数据
public class FlashOrder {
    private String goodsName;
    private BigDecimal price;
    private String producer;

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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}

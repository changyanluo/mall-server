package com.platform.mall.dto;

import java.math.BigDecimal;
import java.util.Date;

//前端展示待秒杀商品列表
public class FlashGoods {
    private Long flashId;
    private Long goodsId;
    private String goodsName;
    private String title;
    private String image;
    private BigDecimal flashPrice;
    private Date startDate;
    private Date endDate;

    public Long getFlashId() {
        return flashId;
    }

    public void setFlashId(Long flashId) {
        this.flashId = flashId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getFlashPrice() {
        return flashPrice;
    }

    public void setFlashPrice(BigDecimal flashPrice) {
        this.flashPrice = flashPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

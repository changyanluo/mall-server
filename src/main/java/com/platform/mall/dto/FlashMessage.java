package com.platform.mall.dto;

//商品秒杀消息
public class FlashMessage {
    private String userName;
    private long flashId;
    private long goodsId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getFlashId() {
        return flashId;
    }

    public void setFlashId(long flashId) {
        this.flashId = flashId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}

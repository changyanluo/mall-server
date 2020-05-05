package com.platform.mall.component.kafkaConsumer;

import com.platform.mall.bean.MallOrder;
import com.platform.mall.common.Util;
import com.platform.mall.dao.SaleDao;
import com.platform.mall.dto.FlashMessage;
import com.platform.mall.dto.FlashOrder;
import com.platform.mall.dto.UserMessage;
import com.platform.mall.mapper.MallOrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class FlashSale {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private MallOrderMapper mallOrderMapper;

    //接受秒杀消息异步下单
    @KafkaListener(topics="flashSale")
    public void onFlashSale(String flash){
        logger.info("收到秒杀消息" + flash);
        FlashMessage flashMessage = Util.parseValue(flash,FlashMessage.class);
        List<FlashOrder> flashOrders = saleDao.getFlashGoods(flashMessage);
        if(flashOrders.size() > 0){
            //减库存，生成订单,给用户推送秒杀结果消息
            UserMessage userMessage = new UserMessage();
            try {
                decStockAndCreOrder(flashMessage,flashOrders.get(0));
                userMessage.setTitle("秒杀成功!");
                userMessage.setContent(flashOrders.get(0).getGoodsName() + "已生成订单.");
            }
            catch (Exception ex){
                userMessage.setTitle("秒杀失败!");
                userMessage.setContent(ex.getMessage());
            }
            finally {
                userMessage.setReceivers(Collections.singletonList(flashMessage.getUserName()));
                userMessage.setType(0);
                try {
                    kafkaTemplate.send("usrNotification", Util.toJsonString(userMessage));
                }
                catch (Exception ex){
                    logger.error(ex.getMessage());
                }
            }
        }
    }

    @Transactional
    public void decStockAndCreOrder(FlashMessage flashMessage,FlashOrder flashOrder){
        saleDao.decreaseGoodsStock(flashMessage.getGoodsId());
        MallOrder mallOrder = new MallOrder();
        mallOrder.setUserName(flashMessage.getUserName());
        mallOrder.setGoodsId(flashMessage.getGoodsId());
        mallOrder.setGoodsCount(1);
        mallOrder.setGoodsName(flashOrder.getGoodsName());
        mallOrder.setGoodsPrice(flashOrder.getPrice());
        mallOrder.setStatus(0);
        mallOrder.setCreateDate(new Date());
        mallOrder.setPayDate(null);
        mallOrderMapper.insert(mallOrder);
    }
}

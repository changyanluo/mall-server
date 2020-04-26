package com.platform.mall.component;

import com.platform.mall.bean.MallOrder;
import com.platform.mall.bean.SysLog;
import com.platform.mall.common.Util;
import com.platform.mall.dao.SaleDao;
import com.platform.mall.dto.FlashMessage;
import com.platform.mall.dto.FlashOrder;
import com.platform.mall.mapper.MallOrderMapper;
import com.platform.mall.mapper.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

//kafka消费者
@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private MallOrderMapper mallOrderMapper;

    //接受秒杀消息异步下单
    @Transactional
    @KafkaListener(topics="flashSale")
    public void onFlashSale(String flash){
        logger.info("收到秒杀消息" + flash);
        FlashMessage flashMessage = Util.parseValue(flash,FlashMessage.class);
        List<FlashOrder> flashOrder = saleDao.getFlashGoods(flashMessage);
        if(flashOrder.size() > 0){
            //减库存，生成订单
            try {
                saleDao.decreaseGoodsStock(flashMessage.getGoodsId());
                MallOrder mallOrder = new MallOrder();
                mallOrder.setUserName(flashMessage.getUserName());
                mallOrder.setGoodsId(flashMessage.getGoodsId());
                mallOrder.setGoodsCount(1);
                mallOrder.setGoodsPrice(flashOrder.get(0).getPrice());
                mallOrder.setStatus(0);
                mallOrder.setCreateDate(new Date());
                mallOrderMapper.insert(mallOrder);
            }
            catch (Exception ex){

            }
        }
    }

    //用户前端网页消息通知
    @KafkaListener(topics = "usrNotification")
    public void onUsr(String notification){

    }

    //操作日志
    @KafkaListener(topics = "log")
    public void onLog(String logMessage){
        try {
            SysLog log = Util.parseValue(logMessage, SysLog.class);
            sysLogMapper.insert(log);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}

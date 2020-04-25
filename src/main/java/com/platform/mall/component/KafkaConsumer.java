package com.platform.mall.component;

import com.platform.mall.bean.SysLog;
import com.platform.mall.common.Util;
import com.platform.mall.dto.FlashMessage;
import com.platform.mall.mapper.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//kafka消费者
@Component
public class KafkaConsumer {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @KafkaListener(topics="flashSale")
    public void onFlashSale(String flash){
        FlashMessage flashMessage = Util.parseValue(flash,FlashMessage.class);

    }

    @KafkaListener(topics = "usrNotification")
    public void onUsr(String notification){

    }

    @KafkaListener(topics = "log")
    public void onLog(String logMessage){
        SysLog log = Util.parseValue(logMessage, SysLog.class);
        sysLogMapper.insert(log);
    }
}

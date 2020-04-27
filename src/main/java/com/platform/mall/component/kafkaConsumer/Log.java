package com.platform.mall.component.kafkaConsumer;

import com.platform.mall.bean.SysLog;
import com.platform.mall.common.Util;
import com.platform.mall.mapper.SysLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Log {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysLogMapper sysLogMapper;

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

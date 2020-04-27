package com.platform.mall.component.kafkaConsumer;

import com.platform.mall.bean.SysMessage;
import com.platform.mall.common.Util;
import com.platform.mall.component.WebSocketServer;
import com.platform.mall.dao.MessageDao;
import com.platform.mall.dto.UserMessage;
import com.platform.mall.mapper.SysMessageMapper;
import com.platform.mall.mapper.SysMessageUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class UsrNotification {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysMessageMapper messageMapper;
    @Autowired
    private SysMessageUserMapper messageUserMapper;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private WebSocketServer webSocketServer;

    //用户前端网页消息通知
    @KafkaListener(topics = "usrNotification")
    public void onUsrNotification(String notification){
        UserMessage userMessage = Util.parseValue(notification,UserMessage.class);
        //先插入消息表，再通过websocket推送
        try{
            persistMessage(userMessage);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("{title:");
            stringBuilder.append(userMessage.getTitle());
            stringBuilder.append(",content:");
            stringBuilder.append(userMessage.getContent());
            stringBuilder.append("}");
            webSocketServer.sendInfo(userMessage.getReceivers(),stringBuilder.toString());
        }
        catch (Exception ex){
            logger.error("向用户推送消息出错:"  + ex.toString());
        }
    }

    @Transactional
    public void persistMessage(UserMessage userMessage){
        SysMessage sysMessage = new SysMessage();
        sysMessage.setTitle(userMessage.getTitle());
        sysMessage.setContent(userMessage.getContent());
        sysMessage.setCreateTime(new Date());
        sysMessage.setMessageType(userMessage.getType());
        messageMapper.insert(sysMessage);
        messageDao.batchInsertUserMessage(sysMessage.getId(),userMessage.getReceivers());
    }
}

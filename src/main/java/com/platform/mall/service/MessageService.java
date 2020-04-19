package com.platform.mall.service;

import com.platform.mall.bean.SysMessage;

import java.util.List;

//系统消息服务
public interface MessageService {

    //发送消息
    int sendMessage(SysMessage message, List<Long> userIdList);

    //拉取用户消息
    List<SysMessage> getUserMessages(long userId);
}
package com.platform.mall.service.impl;

import com.platform.mall.bean.SysMessage;
import com.platform.mall.dao.MessageDao;
import com.platform.mall.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public int sendMessage(SysMessage message, List<Long> userIdList) {
        return 0;
    }

    @Override
    public List<SysMessage> getUserMessages(String userName) {
        return  messageDao.getUserMessage(userName);
    }
}

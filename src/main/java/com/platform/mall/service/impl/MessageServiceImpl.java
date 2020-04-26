package com.platform.mall.service.impl;

import com.platform.mall.bean.SysMessage;
import com.platform.mall.service.MessageService;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    @Override
    public int sendMessage(SysMessage message, List<Long> userIdList) {
        return 0;
    }

    @Override
    public List<SysMessage> getUserMessages(long userId) {
        return null;
    }
}

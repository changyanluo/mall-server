package com.platform.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.platform.mall.bean.SysLog;
import com.platform.mall.bean.SysLogExample;
import com.platform.mall.bean.SysMessage;
import com.platform.mall.common.PageList;
import com.platform.mall.dao.MessageDao;
import com.platform.mall.mapper.SysLogMapper;
import com.platform.mall.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public int sendMessage(SysMessage message, List<Long> userIdList) {
        return 0;
    }

    @Override
    public List<SysMessage> getUserMessages(String userName) {
        return  messageDao.getUserMessage(userName);
    }

    @Override
    public PageList<SysLog> getOperationLog(String userName, String actionName, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize,true);
        SysLogExample logExample = new SysLogExample();
        if(!StringUtils.isEmpty(userName)){
            logExample.createCriteria().andUserNameEqualTo(userName);
        }
        if(!StringUtils.isEmpty(actionName)){
            logExample.createCriteria().andActionNameLike(actionName);
        }
        List<SysLog> syslogs = sysLogMapper.selectByExampleWithBLOBs(logExample);
        return PageList.getPageList(syslogs);
    }
}

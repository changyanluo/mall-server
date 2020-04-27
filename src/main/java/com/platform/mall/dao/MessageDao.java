package com.platform.mall.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageDao {
    int batchInsertUserMessage(@Param("messageId") long messageId, @Param("list") List<String> userNameList);
}

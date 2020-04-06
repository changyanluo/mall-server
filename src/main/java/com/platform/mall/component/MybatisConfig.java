package com.platform.mall.component;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.platform.mall.mapper","com.platform.mall.dao"})
public class MybatisConfig {
}

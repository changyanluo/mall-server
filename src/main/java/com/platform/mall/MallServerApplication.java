package com.platform.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class MallServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallServerApplication.class, args);
	}

}

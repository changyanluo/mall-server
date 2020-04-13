package com.platform.mall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platform.mall.bean.SysUser;
import com.platform.mall.dto.UserCache;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.DigestUtils;

class MallServerApplicationTests {

	@Test
	void contextLoads() {
		List<SysUser> list = new ArrayList<>();
		SysUser u1 = new SysUser();
		u1.setUserName("name1");
		u1.setPassword("qqq");
		SysUser u2 = new SysUser();
		u2.setUserName("name2");
		u2.setPassword("www");
		list.add(u1);
		list.add(u2);
		List<String> nameList = list.stream()
				.map(u->u.getUserName())
				.collect(Collectors.toList());
		List<String> pList = list.stream()
				.map(u->u.getPassword())
				.collect(Collectors.toList());
		System.out.println(nameList);
		System.out.println(pList);
	}

}

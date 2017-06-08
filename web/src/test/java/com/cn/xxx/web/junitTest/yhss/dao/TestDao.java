package com.cn.xxx.web.junitTest.yhss.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.cn.xxx.web.junitTest.JunitContext;
import com.cn.xxx.yhsscore.dao.UserDao;
import com.cn.xxx.yhsscore.dao.WechatUserDao;
import com.cn.xxx.yhsscore.model.UserDO;
import com.cn.xxx.yhsscore.model.WechatUserDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDao extends JunitContext {

	private ObjectMapper om = new ObjectMapper();
	private WechatUserDao wechatUserDao ;
	private UserDao userDao;
	
	@Before
	public void prepare() {
		wechatUserDao = (WechatUserDao) ctx.getBean("wechatUserDao");
		userDao = (UserDao) ctx.getBean("userDao");
	}
	
	@Test
	public void testWechatUserDao() throws JsonProcessingException{
		WechatUserDO user = wechatUserDao.queryWechatUserDOByOpenid("123");
		System.out.println(om.writeValueAsString(user));
	}
	@Test
	public void testUserDao() throws JsonProcessingException{
		List<UserDO> user = userDao.queryUserDOByUserNamesOrPhoneNos("calchen","18217296042");
//		System.out.println(om.writeValueAsString(user));
		System.out.println(user.size());
	}
	
}

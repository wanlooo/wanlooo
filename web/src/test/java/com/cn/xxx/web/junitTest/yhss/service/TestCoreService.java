package com.cn.xxx.web.junitTest.yhss.service;

import org.junit.Before;
import org.junit.Test;

import com.cn.xxx.web.junitTest.JunitContext;
import com.cn.xxx.yhsscore.service.CoreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCoreService extends JunitContext {

	private ObjectMapper om = new ObjectMapper();
	private CoreService coreService ;
	
	@Before
	public void prepare() {
		coreService = (CoreService) ctx.getBean("coreService");
	}
	
	@Test
	public void testCoreService() throws JsonProcessingException{
		String requestXml = "<xml>"
							+"<ToUserName><![CDATA[gh_b216fb45b6f1]]></ToUserName>"
							+"<FromUserName><![CDATA[oEybKwtejlI4Ng9fUBc6NrGkO_E8]]></FromUserName>"
							+"<CreateTime>1486623431</CreateTime>"
							+"<MsgType><![CDATA[event]]></MsgType>"
							+"<Event><![CDATA[subscribe]]></Event>"
							+"</xml>";
		String xml = coreService.processRequest(requestXml);
		System.out.println(xml);
	}
	
}

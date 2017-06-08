package com.cn.xxx.wxcore.service;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

@Service
public class ApplicationContextService extends ApplicationObjectSupport {
	
	public  Object getBean(String name){
		return getApplicationContext().getBean(name);
	}
}

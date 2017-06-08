package com.cn.xxx.yhsscore.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.xxx.yhsscore.model.AddressDO;


public class NullValidateUtil<T> {
	private static  Logger log = LoggerFactory.getLogger(NullValidateUtil.class);
	public static <T> void validate(T t,String method) throws Exception{
		log.info("---validate begain---");
		if(t.getClass().equals(AddressDO.class)){
			validateAddress((AddressDO)t,method);
		}
		
		log.info("---validate end---");
	}
	//收货地址非空验证
	private static void validateAddress(AddressDO address,String method) throws Exception{
		
	}
	public static void isNull(String param){
		
	}
	
}

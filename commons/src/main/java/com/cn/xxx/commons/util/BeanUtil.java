package com.cn.xxx.commons.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	public static void copyProperties(Object dest, Object orig){
		
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException e) {
			logger.error(dest.getClass().getName()+" copy "+orig.getClass().getName()+"'s properties fail, "+orig.getClass().getName()+" is illegal");
		} catch (InvocationTargetException e) {
			logger.error(dest.getClass().getName()+" copy "+orig.getClass().getName()+"'s properties fail, "+dest.getClass().getName()+" is illegal");
		}
	}
}

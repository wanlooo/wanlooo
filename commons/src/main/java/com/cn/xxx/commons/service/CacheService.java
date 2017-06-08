package com.cn.xxx.commons.service;

public interface CacheService {
	
	public <T> T getObject(String key);
	
	void setCache(String key,Object o);
	
}

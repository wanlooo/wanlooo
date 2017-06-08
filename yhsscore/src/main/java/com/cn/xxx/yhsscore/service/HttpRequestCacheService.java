package com.cn.xxx.yhsscore.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cn.xxx.commons.service.CacheService;
@Service("request")
public class HttpRequestCacheService implements CacheService {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getObject(String key) {
		T object = (T) getHttpServletRequest().getSession().getAttribute(key);
		return object ;
	}

	@Override
	public void setCache(String key, Object o) {
		getHttpServletRequest().getSession().setAttribute(key, o);
	}
	
	private HttpServletRequest getHttpServletRequest(){
		HttpServletRequest httpServletRequest = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return httpServletRequest ;
	}

}

package com.cn.xxx.commons.memcache;

import com.cn.xxx.commons.core.GlobalConfig;
import com.cn.xxx.commons.util.StringUtil;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * memcache客户端链接的相关配置
 *
 */
public class MemcacheManage {
	
	protected static MemCachedClient client = new MemCachedClient();
	
	static{
		init();
	}
	
	public static MemCachedClient getMemCachedClient(){
		return client;
	}
	
	public static void init(){
		String serverStr = GlobalConfig.getValue("memcache.servers");
		//指定服务器负载权重
		Integer[] weights = {1,1,1};
		//从连接池获取一个连接实例
		SockIOPool pool = SockIOPool.getInstance();
		//设置服务器和服务器负载量
		String[] serverArray = serverStr.trim().split(",");
		pool.setServers(serverArray);
		pool.setWeights(weights);
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.initConn"))){
			pool.setInitConn(Integer.valueOf(GlobalConfig.getValue("memcache.initConn")));
		}
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.minConn"))){
			pool.setMinConn(Integer.valueOf(GlobalConfig.getValue("memcache.minConn")));
		}
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.maxConn"))){
			pool.setMaxConn(Integer.valueOf(GlobalConfig.getValue("memcache.maxConn")));
		}
		
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.maxIdle"))){
			pool.setMaxIdle(Integer.valueOf(GlobalConfig.getValue("memcache.maxIdle")));
		}
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.maintSleep"))){
			pool.setMaintSleep(Integer.valueOf(GlobalConfig.getValue("memcache.maintSleep")));
		}
		
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.nagle"))){
			pool.setNagle(Boolean.valueOf(GlobalConfig.getValue("memcache.nagle")));
		}
		
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.socketTO"))){
			pool.setSocketTO(Integer.valueOf(GlobalConfig.getValue("memcache.socketTO")));
		}
		if(!StringUtil.isBlank(GlobalConfig.getValue("memcache.socketConnectTO"))){
			pool.setSocketConnectTO(Integer.valueOf(GlobalConfig.getValue("memcache.socketConnectTO")));
		}
		// 开始初始化 连接池
		pool.initialize();
		//设置压缩模式
	}
	
	
	
	
}

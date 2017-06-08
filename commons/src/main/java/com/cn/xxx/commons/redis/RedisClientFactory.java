package com.cn.xxx.commons.redis;

import com.cn.xxx.commons.core.GlobalConfig;
import com.cn.xxx.commons.util.StringUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisClientFactory {
	private static JedisPool pool = null;
	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.MaxActive"))){
				config.setMinEvictableIdleTimeMillis(Integer.valueOf(GlobalConfig.getValue("redis.MaxActive")));
			}
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.MaxWait"))){
				config.setMaxWaitMillis(Integer.valueOf(GlobalConfig.getValue("redis.MaxWait")));
			}
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.MaxTotal"))){
				config.setMaxTotal(Integer.valueOf(GlobalConfig.getValue("redis.MaxTotal")));
			}
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.MaxIdle"))){
				config.setMaxIdle(Integer.valueOf(GlobalConfig.getValue("redis.MaxIdle")));
			}
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.TestOnBorrow"))){
				config.setTestOnBorrow(Boolean.valueOf(GlobalConfig.getValue("redis.TestOnBorrow")));
			}
			String host = "127.0.0.1" ;
			Integer port = 6379 ;
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.host"))){
				host = GlobalConfig.getValue("redis.host") ;
			}
			if(!StringUtil.isBlank(GlobalConfig.getValue("redis.port"))){
				port = Integer.valueOf(GlobalConfig.getValue("redis.port"));
			}
			pool = new JedisPool(config, host, port);
		}
		return pool;
	}
	
	public static Jedis getJedis(){
		return RedisClientFactory.getPool().getResource() ;
	}
}

package com.cn.xxx.commons.redis;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.cn.xxx.commons.redis.serialize.ObjectTranscoder;

public class RedisClient {
	/** 
     * 在不同的线程中使用相同的Jedis实例会发生奇怪的错误。但是创建太多的实现也不好因为这意味着会建立很多sokcet连接， 
     * 也会导致奇怪的错误发生。单一Jedis实例不是线程安全的。为了避免这些问题，可以使用JedisPool, 
     * JedisPool是一个线程安全的网络连接池。可以用JedisPool创建一些可靠Jedis实例，可以从池中拿到Jedis的实例。 
     * 这种方式可以解决那些问题并且会实现高效的性能 
     */
	
	public static <T extends Serializable>  String set(String key,T value){
		ObjectTranscoder<T> ot =  new ObjectTranscoder<T>();
		byte[] v = ot.serialize(value);
		return set(key.getBytes(),v);
	}
	private static String set(byte[] key, byte[] value){
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.set(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	
	public static String set(String key, String value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.set(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	public static String set(String key, String value, int seconds) {
		Jedis jedis = null;
		String result;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.setex(key, seconds, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}
	public static Long append(String key, String value) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.append(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	public static String set(String key, Map<String,String> value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.hmset(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	public static String set(String key, List<String> value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			jedis.del(key);  
			for(String v : value){
	            jedis.rpush(key, v);
			}
			result = "success";
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	public static String set(String key, Set<String> value) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			for(String v : value){
	            jedis.sadd(key, v);
			}
			result = "success";
		} finally {
			if (jedis != null) {
				jedis.close();
			}	
		}
		return result;
	}
	
	private static byte[] get(byte[] key) {
		Jedis jedis = null;
		byte[] result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		//pool.destroy();
		return result;
	}
	public static <T extends Serializable>  T getObject(String key){
		if(key == null){
			return null ;
		}
		ObjectTranscoder<T> ot =  new ObjectTranscoder<T>();
		byte[] v = get(key.getBytes());
		return ot.deserialize(v);
	}
	public static String getString(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		//pool.destroy();
		return result;
	}
	public static Map<String,String> getMap(String key) {
		Jedis jedis = null;
		Map<String,String> result = new HashMap<String, String>();
		try {
			jedis = RedisClientFactory.getPool().getResource();
			Iterator<String> iter=jedis.hkeys(key).iterator(); 
			while (iter.hasNext()){ 
				String hkey = iter.next(); 
				List<String> hvalue = jedis.hmget(key,hkey);
				if(hvalue != null && hvalue.size()>0){
					result.put(hkey, hvalue.get(0));
//					System.out.println(hkey+":"+hvalue); 
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}
	public static List<String> getList(String key) {
		Jedis jedis = null;
		List<String> result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.lrange(key, 0, -1); 
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}
	public static Set<String> getSet(String key) {
		Jedis jedis = null;
		Set<String> result = null;
		try {
			jedis = RedisClientFactory.getPool().getResource();
			result = jedis.smembers(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return result;
	}
	
	
	/*
	 * Returns: Integer reply, specifically: an integer greater than 0 if one or
	 * more keys were removed 0 if none of the specified key existed
	 */
	public static Long delete(String... keys) {
		JedisPool pool = RedisClientFactory.getPool();
		Jedis jedis = null;
		Long result  = null;
		try {
			jedis = pool.getResource();
			result = jedis.del(keys);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		//pool.destroy();
		return result;
	}
}

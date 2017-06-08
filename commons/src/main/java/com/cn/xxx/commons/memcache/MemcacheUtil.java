package com.cn.xxx.commons.memcache;

import java.util.Date;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;

/**
 * 
 * memcache客户端工具类
 *
 */
public class MemcacheUtil{
	
	//默认过期时间一小时
	private static final Integer DEFAULT_TIMEOUT = 1000*60*60; 
	private static final MemCachedClient cache = MemcacheManage.getMemCachedClient();
	
	/**
	 * 存key-value对到服务器
	 * @param key
	 * @param value
	 */
	public static void put(String key,Object value){
		try{
			cache.set(key, value, new Date(DEFAULT_TIMEOUT));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void delete(String key){
		try{
			cache.delete(key);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 存key-value对到服务器
	 * @param key
	 * @param value
	 * @param expirTime 过期时长 单位毫秒
	 */
	public static void put(String key,Object value,Integer expirTime){
		cache.set(key, value, new Date(expirTime));
	}
	
	/**
	 * 根据存储的key获取value
	 * @param key
	 * @return
	 */
	public static Object get(String key){
		try{
			return cache.get(key);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public static Map<String, Object> getMulti(String[] keys) {
		try {  
            return cache.getMulti(keys);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}

	public static void remove(String key) {
		try {  
            cache.delete(key);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	public static void removeAll() {
		try {  
            cache.flushAll();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	public static boolean keyExist(String key){
		
		return cache.keyExists(key);
	}
	
}

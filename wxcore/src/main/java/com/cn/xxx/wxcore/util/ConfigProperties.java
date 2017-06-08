package com.cn.xxx.wxcore.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 通过properties文件配置设置常量基类 负责加载和读取properties属性文件并提供访问的静态工具方法
 * 
 * 
 * 
 */
public class ConfigProperties {
	private static Log logger = LogFactory.getLog(ConfigProperties.class);
	private static Properties p = new Properties();

	public static void init(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigProperties.class.getClassLoader().getResourceAsStream(propertyFileName);
			if (in != null){
				p.load(in);
			}
		} catch (IOException e) {
			logger.error("load " + propertyFileName + "  error!");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("close " + propertyFileName + " error!");
				}
			}
		}
	}

	public static String getProperty(String key, String defaultValue) {
		return p.getProperty(key, defaultValue);
	}
	
	public static String getProperty(String key) {
		return p.getProperty(key);
	}
	
	public static boolean getProperty(String key, boolean defaultValue) {
		try {
		return Boolean.parseBoolean(p.getProperty(key, ""));
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			return defaultValue;
		}
	}

	public static int getProperty(String key, int defaultValue) {
		try {
			return Integer.parseInt(getProperty(key, ""));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return defaultValue;
		}

	}
}
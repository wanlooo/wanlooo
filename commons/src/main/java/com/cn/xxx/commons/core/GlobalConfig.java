package com.cn.xxx.commons.core;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.cn.xxx.commons.util.StringUtil;


public class GlobalConfig extends PropertyPlaceholderConfigurer  {
	
	public static final Locale PREFER_LOCALE = Locale.CHINA;
	
	private static Map<String, Object> propertiesMap;
	
	public static String getValue(String key){
		if(StringUtil.isBlank(key)){
			return "";
		}
		return (String)propertiesMap.get(key);
	}
	
	/**
	 * 
	 * @param key
	 * @param defaultValue 如果key对应的值为空 则返回默认值
	 * @return
	 */
	public static String getValue(String key, String defaultValue){
		if(StringUtil.isBlank(getValue(key))){
			return defaultValue;
		}
		return getValue(key);
	}
	
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        propertiesMap = new HashMap<String, Object>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertiesMap.put(keyStr, value);
        }  
    }

    public static Object getContextProperty(String name) {
        return propertiesMap.get(name);
    } 
	
}

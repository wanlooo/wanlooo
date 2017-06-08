package com.cn.xxx.commons.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    
    public static String writeValueAsString(Object o) {
    	return JsonUtil.writeValueAsString(o, false);
    }

    public static String writeValueAsString(Object o, boolean formatted) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
        } catch (IOException e) {
            logger.error("json序列化错误", e);
        }
        return null;
    }

    public static <T> T readObject(String json, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
//        mapper.disable(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("json反序列化错误", e);
        }
        return null;
    }
}

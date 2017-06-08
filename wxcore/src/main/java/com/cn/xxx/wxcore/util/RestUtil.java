package com.cn.xxx.wxcore.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestUtil {

	public static Logger logger = LoggerFactory.getLogger(RestUtil.class);
	
	/**
	 * GET方式执行rest api
	 * 
	 * @param url 带参数访问地址
	 * @return Map success:是否调用成功;responseCode:调用返回code;data:成功时返回结果;
	 * @throws IOException 
	 */
	public static Map<String, Object> doGet(String url) {
		logger.info("[url:"+url+"]");
		return excute("GET", url, null);
	}

	/**
	 * POST方式执行REST API
	 * 
	 * @param url 调用的的url
	 * @param params 传递参数json字符串
	 * @return Map success:是否调用成功;responseCode:调用返回code;data:成功时返回结果;
	 * @throws IOException
	 */
	public static Map<String, Object> doPost(String url, String params) {
		logger.info("[url:"+url+"]");
		logger.info("[params:"+params+"]");
		return excute("POST", url, params);
	}
	
	/**
	 * PUT方式执行REST API
	 * 
	 * @param url 调用的的url
	 * @param params 传递参数
	 * @return Map success:是否调用成功;responseCode:调用返回code;data:成功时返回结果;
	 * @throws IOException
	 */
	public static Map<String, Object> doPut(String url, String params) {
		return excute("PUT", url, params);
	}
	
	private static Map<String, Object> excute(String method, String url, String params) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", false);
		
		HttpURLConnection httpConnection = null;
		BufferedReader responseBuffer = null;
		PrintStream ps = null;
		
		try{
			URL restServiceURL = new URL(url);
			httpConnection = (HttpURLConnection) restServiceURL.openConnection();
			httpConnection.setRequestMethod(method);
			httpConnection.setConnectTimeout(30000);
			if("GET".equals(method)){ // get请求
				httpConnection.setRequestProperty("Accept", "application/json");
			}else if("POST".equals(method) || "PUT".equals(method)){ // post请求 or put请求
				httpConnection.setDoOutput(true);
				httpConnection.setAllowUserInteraction(false);
				httpConnection.setRequestProperty("Content-Type", "application/json");
				
				ps = new PrintStream(httpConnection.getOutputStream());
				ps.print(params);
			}

			result.put("responseCode", httpConnection.getResponseCode());
			
			if (httpConnection.getResponseCode() != 200) {
				return result;
			}

			responseBuffer = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"utf-8"));
			
			String line, output = "";
			while (null != (line = responseBuffer.readLine())) {
				output += line;
			}
			logger.info("[result:"+output+"]");
			result.put("data", output);
			result.put("success", true);
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				if(ps != null){
					ps.close();
				}
				
				if(responseBuffer != null){
					responseBuffer.close();
				}
				
				if(httpConnection != null){
					httpConnection.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
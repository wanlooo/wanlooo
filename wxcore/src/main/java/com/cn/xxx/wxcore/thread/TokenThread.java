package com.cn.xxx.wxcore.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cn.xxx.wxcore.util.AdvancedUtil;
import com.cn.xxx.wxcore.vo.Token;


/**
 * 定时获取微信access_token的线程
 * 
 */
public class TokenThread implements Runnable {
	private static Logger log = LoggerFactory.getLogger(TokenThread.class);
	public static Token token = null;
	public static Long  tokenMillis = 0L;  //获取token时的毫秒数
	
	public void run() {
		while (true) {
			try {
				token = AdvancedUtil.getToken();
				if (null != token) {
					tokenMillis = System.currentTimeMillis() ;
					
					log.info("获取access_token成功，有效时长{}秒 token:{}", token.getExpires_in(), token.getAccess_token());
					// 休眠7000秒
					Thread.sleep((token.getExpires_in() - 200) * 1000);
				} else {
					tokenMillis = 0L ;
					// 如果access_token为null，60秒后再获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
				tokenMillis = 0L ;
				try {
					Thread.sleep(60 * 1000);
				} catch (InterruptedException e1) {
					log.error("{}", e1);
				}
				log.error("{}", e);
			}
		}
	}
	
	public static String initThread(){
		log.info("begin to start TokenThread");
		// 启动定时获取access_token的线程
		new Thread(new TokenThread()).start();
		return "success";
	}
}

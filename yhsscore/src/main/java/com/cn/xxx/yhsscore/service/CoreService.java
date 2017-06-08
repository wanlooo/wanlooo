package com.cn.xxx.yhsscore.service;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.xxx.wxcore.message.resp.RespTextMessage;
import com.cn.xxx.wxcore.util.MessageUtil;

@Service
public class CoreService {

	private Logger log = LoggerFactory.getLogger(CoreService.class);
	
	@Autowired
//	private WechatUserDao wechatUserDao ;
	private WechatUserService wechatUserService ;
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	@SuppressWarnings("rawtypes")
	public String processRequest(String requestXml) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(requestXml);
			System.out.println("-------------requestMap------------");
			for (Entry en : requestMap.entrySet()) {
				System.out.println("key:" + en.getKey() + " value:"
					+new String(en.getValue().toString().getBytes(),"UTF-8"));
			}
			System.out.println("-----------------------------------");

			// 事件推送
			if (requestMap.get("MsgType").equals(
					MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				respXml = event(requestMap);
			}
			// 普通消息
			else {
				respXml = message(requestMap);
			}
			
			if(respXml == null ){
				respXml = "success" ;
			}
			log.info("----answerXml:"+new String(respXml.getBytes(),"UTF-8")+"----");
		} catch (Exception e) {
			e.printStackTrace();
			respXml = "success" ;
		}
		return respXml;
	}

	private String event(Map<String, String> requestMap) {
		String respXml = null;
		String eventType = requestMap.get("Event");// 事件类型
		// 关注事件
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
			// 保存用户信息
			saveWechatUser(requestMap.get("FromUserName"));
		}
		// 扫描事件
		else if (eventType.toLowerCase().equals(MessageUtil.EVENT_TYPE_SCAN)) {
			// 场景值二维码
//			String eventKey = requestMap.get("EventKey").replace("qrscene_", "");// 去掉qrscene_前缀
		}
		// 取消订阅
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
			// 逻辑删除用户信息

		}
		// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			// 其他click事件
		}

		return respXml;
	}

	private void saveWechatUser(String openid) {
		this.wechatUserService.queryOrSaveWechatUserByOpenid(openid);
	}

	private String message(Map<String, String> requestMap) throws IOException {
		String respXml = null;
		// 文本暂不处理,直接返回
		RespTextMessage message = new RespTextMessage();
		message.setCreateTime(new Date().getTime()/1000);
		message.setFromUserName(requestMap.get("ToUserName"));
		message.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		message.setToUserName(requestMap.get("FromUserName"));
		message.setContent(new String(requestMap.get("Content").toString().getBytes(),"UTF-8"));
		
		respXml = MessageUtil.messageToXml(message);
		 
//		AdvancedUtil.sendCustomTextMessage(requestMap.get("FromUserName"), new String(requestMap.get("Content").toString().getBytes(),"GBK"));
		return respXml;
	}

}

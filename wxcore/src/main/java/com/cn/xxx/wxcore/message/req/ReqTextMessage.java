package com.cn.xxx.wxcore.message.req;

/**
 * 文本消息
 * 
 */
public class ReqTextMessage extends ReqBaseMessage {
	// 消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}

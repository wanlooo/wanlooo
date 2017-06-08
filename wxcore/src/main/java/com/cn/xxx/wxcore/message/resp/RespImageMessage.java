package com.cn.xxx.wxcore.message.resp;

/**
 * 图片消息
 * 
 */
public class RespImageMessage extends RespBaseMessage {
	// 图片
	private RespImage Image;

	public RespImage getImage() {
		return Image;
	}

	public void setImage(RespImage image) {
		Image = image;
	}
}

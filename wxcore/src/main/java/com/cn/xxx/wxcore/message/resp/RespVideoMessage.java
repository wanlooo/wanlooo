package com.cn.xxx.wxcore.message.resp;

/**
 * 视频消息
 * 
 */
public class RespVideoMessage extends RespBaseMessage {
	// 视频
	private RespVideo Video;

	public RespVideo getVideo() {
		return Video;
	}

	public void setVideo(RespVideo video) {
		Video = video;
	}
}

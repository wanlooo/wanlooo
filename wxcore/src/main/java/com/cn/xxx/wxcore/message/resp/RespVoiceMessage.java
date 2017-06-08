package com.cn.xxx.wxcore.message.resp;

/**
 * 语音消息
 * 
 */
public class RespVoiceMessage extends RespBaseMessage {
	// 语音
	private RespVoice Voice;

	public RespVoice getVoice() {
		return Voice;
	}

	public void setVoice(RespVoice voice) {
		Voice = voice;
	}
}

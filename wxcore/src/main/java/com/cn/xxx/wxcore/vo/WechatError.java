package com.cn.xxx.wxcore.vo;

public class WechatError {
	private String errcode;
	private String errmsg;
	public WechatError(){}
	public WechatError(String errcode,String errmsg){
		this.errcode = errcode;
		this.errmsg = errmsg ;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String toString(){
		return "errcode:"+errcode+" errmsg:"+errmsg;
	}
}
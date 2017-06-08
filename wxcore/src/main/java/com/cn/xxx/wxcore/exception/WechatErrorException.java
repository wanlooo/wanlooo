package com.cn.xxx.wxcore.exception;

public class WechatErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errcode;
	private String errmsg;
	
	public WechatErrorException(){}
	public WechatErrorException(String errcode,String errmsg){
		super(errmsg);
		this.errcode = errcode ;
		this.errmsg = errmsg ;
	}
	
	public String getErrcode() {
		return errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
}

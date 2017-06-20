package com.cn.xxx.yhsscore.vo.resp;

import java.io.Serializable;

public class SecretSecurityVO implements Serializable {
	
	private static final long serialVersionUID = -4948014633866393118L;
	private String securityTitle;//密保问题标题
	private String securityAnswer;//回答
	public String getSecurityTitle() {
		return securityTitle;
	}
	public void setSecurityTitle(String securityTitle) {
		this.securityTitle = securityTitle;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
}

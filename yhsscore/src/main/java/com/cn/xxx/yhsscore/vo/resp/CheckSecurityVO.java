package com.cn.xxx.yhsscore.vo.resp;

import java.util.List;

import com.cn.xxx.yhsscore.model.SecretSecurityDO;

public class CheckSecurityVO {

	private String userName ;
	private List<SecretSecurityDO> security ;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<SecretSecurityDO> getSecurity() {
		return security;
	}
	public void setSecurity(List<SecretSecurityDO> security) {
		this.security = security;
	}
}

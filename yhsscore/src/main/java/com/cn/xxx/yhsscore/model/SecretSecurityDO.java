package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *密保问题表
 */
@Entity
@Table(name = "YHSS_SECRET_SECURITY")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","user"})
public class SecretSecurityDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String securityTitle;//密保问题标题
	private String securityAnswer;//回答
	
	private UserDO user ;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
	
	@Column(name="Security_Title")
	public String getSecurityTitle() {
		return securityTitle;
	}
	public void setSecurityTitle(String securityTitle) {
		this.securityTitle = securityTitle;
	}
	@Column(name="Security_Answer")
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
}

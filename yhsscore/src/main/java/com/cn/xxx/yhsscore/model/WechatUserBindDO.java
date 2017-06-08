package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;

/**
 */
@Entity
@Table(name = "YHSS_WECHAT_USER_BIND")
public class WechatUserBindDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	
	
	private String status ; //"CHECK"-待审核,"BIND"-已绑定,"UNBIND"-已解绑
	private UserDO user ;
	private WechatUserDO wechatUser ;
	
	@Column(name="STATUS",length=10)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="WECHAT_USER_ID")
	public WechatUserDO getWechatUser() {
		return wechatUser;
	}
	public void setWechatUser(WechatUserDO wechatUser) {
		this.wechatUser = wechatUser;
	}
	
}


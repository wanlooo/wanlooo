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
 * 订单旅客表
 */
@Entity
@Table(name="YHSS_ORDER_CONTACT")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","orderDetail"})
public class OrderContractDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String idType;
	private String idNo;
	private String mobileNo;
	private String email;

	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="ID_TYPE")
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@Column(name="ID_NO")
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	@Column(name="MOBILE_NO")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	

}

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


@Entity
@Table(name="YHSS_TG_BARGAIN_DETAIL")
//团购砍价明细表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","tgActive"})
public class TgActiveBargainDetailDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	private String userid;//参与会员
	private String userName;//参与会员
	private String supperUserid;//上级邀请会员
	private String supperUserName;//上级邀请会员
	private String bargain;//砍掉数值（刀数）
	private String restBargain;//砍掉总数值（刀数）
	
	private TgActiveDO tgActive;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "TG_ACTICE_ID")
	public TgActiveDO getTgActive() {
		return tgActive;
	}
	public void setTgActive(TgActiveDO tgActive) {
		this.tgActive = tgActive;
	}
	
	@Column(name="USER_ID")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Column(name="BARGAIN")
	public String getBargain() {
		return bargain;
	}
	public void setBargain(String bargain) {
		this.bargain = bargain;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="SUPPER_USER_ID")
	public String getSupperUserid() {
		return supperUserid;
	}
	public void setSupperUserid(String supperUserid) {
		this.supperUserid = supperUserid;
	}
	@Column(name="SUPPER_USER_NAME")
	public String getSupperUserName() {
		return supperUserName;
	}
	public void setSupperUserName(String supperUserName) {
		this.supperUserName = supperUserName;
	}
	@Column(name="REST_BARGIAN")
	public String getRestBargain() {
		return restBargain;
	}
	public void setRestBargain(String restBargain) {
		this.restBargain = restBargain;
	}

}

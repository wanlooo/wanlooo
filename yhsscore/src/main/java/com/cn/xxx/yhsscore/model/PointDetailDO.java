package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 会员等级积分规则表  
 */

@Entity
@Table(name = "YHSS_POINT_DETAIL")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","user"})
public class PointDetailDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String point;
	private String orderNo;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	
	private UserDO user ;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user){
		this.user = user ;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="POINT")
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	@Column(name="NOTE1")
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	@Column(name="NOTE2")
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	@Column(name="ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name="NOTE3")
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	@Lob
	@Column(name="NOTE4")
	public String getNote4() {
		return note4;
	}
	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}

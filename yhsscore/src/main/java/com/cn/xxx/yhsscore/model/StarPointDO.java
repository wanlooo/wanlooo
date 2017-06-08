package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 活动星级积分规则表  
 */

@Entity
@Table(name = "YHSS_STAR_POINT")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class StarPointDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String star;
	private String point;
	private String amount ;//价格下限
	private String note1;
	private String note2;
	
	@Column(name="STAR")
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
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
	@Column(name="AMOUNT")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}

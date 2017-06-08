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
 * @author xulong
 *
 */
@Entity
@Table(name="YHSS_TG_ACTIVE_ITEM")
//团购价格明细表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","tgActive"})
public class TgActiveItemDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	
	private Integer stall ;//档次
	private String price ;//A档价格
	private String count;//A档最低数值（刀数）
	
	private TgActiveDO tgActive;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "TG_ACTICE_ID")
	public TgActiveDO getTgActive() {
		return tgActive;
	}
	public void setTgActive(TgActiveDO tgActive) {
		this.tgActive = tgActive;
	}
	@Column(name="PRICE")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name="COUNT")
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Column(name="STALL")
	public Integer getStall() {
		return stall;
	}
	public void setStall(Integer stall) {
		this.stall = stall;
	}
	
}

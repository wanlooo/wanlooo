package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="YHSS_HOT_ACTIVE")
//热门活动表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class HotActiveDO extends AbstractActiveDO {
	
	private static final long serialVersionUID = 1L;
	
	private String type;//类型
	private String venue;//	场馆
	private String address;//地址
	private String detail;//详情描述
	private String joinCount;//参与人数
	private String minPrice;//最低价格（位置不同价格不同的需关联表）
	private String maxPrice;//最高价格（位置不同价格不同的需关联表）
	
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="VENUE")
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	@Column(name="ARRESSS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="DETAIL")
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(name="JOIN_COUNT")
	public String getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(String joinCount) {
		this.joinCount = joinCount;
	}
	@Column(name="MIN_PRICE")
	public String getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}
	@Column(name="MAX_PRICE")
	public String getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}
}

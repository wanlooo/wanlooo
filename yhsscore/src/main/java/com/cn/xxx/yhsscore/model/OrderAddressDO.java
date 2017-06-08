package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 订单快递信息表
 */
@Entity
@Table(name="YHSS_ORDER_ADDRESS")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class OrderAddressDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	private String orderType ; //订单类型
	private String name;
	private String phoneno;
	private String country;
	private String area;
	private String province;
	private String city;
	private String county;
	private String address;
	private String postcode;
	private String expressType ;//配送方式
	private String expressPrice ;//快递费
	private String expressNo ;//快递单号
	private String status ;//快递状态delivered： WD-待发货  DD-已发货  RD-已收货
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="PHONE_NO")
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Column(name="AREA")
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	@Column(name="PROVICE")
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="COUNTY")
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="POST_CODE")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	@Column(name="ORDER_TPYE")
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Column(name="EXPRESS_TYPE")
	public String getExpressType() {
		return expressType;
	}
	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}
	@Column(name="EXPRESS_PRICE")
	public String getExpressPrice() {
		return expressPrice;
	}
	public void setExpressPrice(String expressPrice) {
		this.expressPrice = expressPrice;
	}
	@Column(name="EXPRESS_NO")
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

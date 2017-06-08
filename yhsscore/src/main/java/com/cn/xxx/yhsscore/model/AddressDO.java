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
 *收货地址表
 */
@Entity
@Table(name="YHSS_ADDRESS")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","user"})
public class AddressDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String phoneno;
	private String mobileno;
	private String country;
	private String area;
	private String province;
	private String city;
	private String county;
	private String address;
	private String postcode;
	private Boolean isDefault = false;
	
	private UserDO user ;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
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
	@Column(name="MOBILE_NO")
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
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
	@Column(name = "IS_DEFAULT")
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}

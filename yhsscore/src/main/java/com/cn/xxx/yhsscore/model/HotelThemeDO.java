package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_HOTEL_THEME")
//酒店价格表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class HotelThemeDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String desc;
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="DESCRIPTION")		
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

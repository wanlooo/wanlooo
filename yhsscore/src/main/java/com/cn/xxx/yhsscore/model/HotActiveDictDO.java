package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 热门活动类型字典表
 * 1.添加热门活动用
 */

@Entity
@Table(name="YHSS_HOT_ACTIVE_DICT")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class HotActiveDictDO extends AbstractBaseDO {
	private static final long serialVersionUID = 1L;
	private String activeType;//类型
	private String activeName;//活动标题
	
	@Column(name="ACTIVE_TYPE")
	public String getActiveType() {
		return activeType;
	}
	public void setActiveType(String activeType) {
		this.activeType = activeType;
	}
	@Column(name="ACTIVE_NAME")
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}

}

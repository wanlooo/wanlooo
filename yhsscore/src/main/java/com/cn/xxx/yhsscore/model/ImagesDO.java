package com.cn.xxx.yhsscore.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_IMAGES")
// 图片汇总表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class ImagesDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	private String type;// 类型
	private String desc ;//概述
	private Set<ImageDO> list ;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "YHSS_IMAGES_IMAGE", 
            joinColumns = { @JoinColumn(name = "IMAGES_ID", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "IMAGE_ID", referencedColumnName = "id") })
	public Set<ImageDO> getList() {
		return list;
	}

	public void setList(Set<ImageDO> list) {
		this.list = list;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "DESCRIPTION")
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


}

package com.cn.xxx.yhsscore.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "YHSS_IMAGE")
@JsonIgnoreProperties({"images","created","lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class ImageDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	private String imageName;// 图片名
	private String type;//图片属性"red/12寸"
	private String desc ;//图片描述
	private String imageUrl;// 页面相对链接
	private Set<ImagesDO> images ;
	
	@ManyToMany(mappedBy = "list")
	public Set<ImagesDO> getImages() {
		return images;
	}
	public void setImages(Set<ImagesDO> images) {
		this.images = images;
	}
	@Column(name = "IMAGE_NAME")
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Column(name = "IMAGE_URL")
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	@Column(name = "DESCRIPTION")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}

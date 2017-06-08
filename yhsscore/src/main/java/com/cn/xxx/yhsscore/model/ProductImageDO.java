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

@Entity
@Table(name = "YHSS_PRODUCT_IMAGE")
@JsonIgnoreProperties({"created","lastUpdate","deleted","creator","lastModifier","lastModifierName","product"})
public class ProductImageDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = 1L;
	private String name;// 图片名
	private String type;//图片属性"red/12寸"
	private String desc ;//图片描述
	private String url;// 页面相对链接
	private ProductDO product ;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "URL")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

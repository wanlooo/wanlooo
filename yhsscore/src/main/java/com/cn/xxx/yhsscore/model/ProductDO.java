package com.cn.xxx.yhsscore.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 商品信息表
 */
@Entity
@Table(name="YHSS_PRODUCT")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","handler","hibernateLazyInitializer"})
public class ProductDO extends AbstractBaseDO {
	
	private static final long serialVersionUID = -6436514501676105686L;
	private String name;//商品名称
	private String productNo;//编号/条形码
	private String type ;//商品种类
	private String price;//（原始）价格
	private String capacity;//容量/重量
	private String qrcodeUrl;//二维码照片地址
	private Date produceDate;//生产日期
	private String validity;//有效期：年
	private String factory;//厂家
	private String producedPlace;//出产地
	private String description;//产品描述
	private String remark;//备注
	private String status;//商品状态 SALE-在售，SHELF-下架
	
	private Set<ProductImageDO> images ;
	/*
	private ImagesDO images;//关联图片表
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IMAGES_ID")
	public ImagesDO getImages() {
		return images;
	}
	public void setImages(ImagesDO images) {
		this.images = images;
	}
	*/
	@OneToMany(mappedBy = "product",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Set<ProductImageDO> getImages() {
		return images;
	}
	public void setImages(Set<ProductImageDO> images) {
		this.images = images;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="PRODUCT_NO")
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="PRICE")
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name="CAPACITY")
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	@Column(name="VALIDITY")
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	@Column(name="FACTORY")
	public String getFactory() {
		return factory;
	}
	public void setFactory(String factory) {
		this.factory = factory;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="QRCODE_URL")
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	@Column(name="PRODUCT_DATE")
	public Date getProduceDate() {
		return produceDate;
	}
	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}
	@Column(name="PRODUCED_PLACE")
	public String getProducedPlace() {
		return producedPlace;
	}
	public void setProducedPlace(String producedPlace) {
		this.producedPlace = producedPlace;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}

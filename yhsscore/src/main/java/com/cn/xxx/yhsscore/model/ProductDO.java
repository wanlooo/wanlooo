package com.cn.xxx.yhsscore.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	/**
	 * hzm
	 */
	private Long firstPrice;//第一档价格
	private Long secondPrice;//第一档价格
	private Long thirdPrice;//第一档价格
	private StarLevelRulesDO starLevel;//星级-----商品活动与星级为单向多对一
	
	
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
	@Column(name="FIRST_PRICE")
	public Long getFirstPrice() {
		return firstPrice;
	}
	public void setFirstPrice(Long firstPrice) {
		this.firstPrice = firstPrice;
	}
	@Column(name="SECOND_PRICE")
	public Long getSecondPrice() {
		return secondPrice;
	}
	public void setSecondPrice(Long secondPrice) {
		this.secondPrice = secondPrice;
	}
	@Column(name="THIRD_PRICE")
	public Long getThirdPrice() {
		return thirdPrice;
	}
	public void setThirdPrice(Long thirdPrice) {
		this.thirdPrice = thirdPrice;
	}
	/**
     * cascade：为级联操作，里面有级联保存，级联删除等，all为所有 
     * 说明：在定义关系时经常会涉及是否定义Cascade(级联处理)属性，担心造成负面影响.
     *   ·不定义,则对关系表不会产生任何影响
     *   ·CascadeType.PERSIST （级联新建）
     *   ·CascadeType.REMOVE （级联删除）
     *   ·CascadeType.REFRESH （级联刷新）
     *   ·CascadeType.MERGE （级联更新）中选择一个或多个。
     *   ·还有一个选择是使用CascadeType.ALL ，表示选择全部四项
     * fetch：加载类型，有lazy和eager二种，
     *   eager为急加载，意为立即加载，在类加载时就加载
     *   lazy为慢加载，第一次调用的时候再加载，由于数据量太大，onetomany一般为lazy
     * mappedBy：mappedBy属性用于双向关联实体时，标注在不保存关系的实体中
     * optional:属性表示关联的实体是否能够存在null值。默认为true，表示可以存在null值。如果为false，则要同时配合使用@JoinColumn标记
     */
	@ManyToOne(fetch=FetchType.EAGER,optional=false,cascade={CascadeType.PERSIST})
	/**
	 * @JoinColumn
	 * 默认情况下，关联的实体的主键一般是用来做外键的。但如果此时不想主键作为外键，则需要设置referencedColumnName属性
	 * 通过ProductDO表中的“START_LEVEL”字段关联的是StarLevelRulesDO表中的“START_LEVEL”，而“START_LEVEL”并不是StarLevelRulesDO表中的主键
	 */
	@JoinColumn(name="STAR_LEVEL",referencedColumnName="STAR_LEVEL")
	public StarLevelRulesDO getStarLevel() {
		return starLevel;
	}
	public void setStarLevel(StarLevelRulesDO starLevel) {
		this.starLevel = starLevel;
	}

}

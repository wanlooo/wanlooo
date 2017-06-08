package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *秒杀活动表
 */
@Entity
@Table(name="YHSS_MS_ACTIVE")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName"})
public class MsActiveDO extends AbstractActiveDO {
	private static final long serialVersionUID = 1L;

	private String activePrice;//活动价
	private String originalPrice;//手动设置商品原价
	private String quantity; //活动库存量（总量）
	private String limitCount;//限购数量
	private String joinCount;//已售数量（建议建一视图，以已支付和待支付数量总数计）
	private String limitTime;//秒杀时段（12、14、16、18）
	private String note1;
	private String note2;
	private String note3;
	private String note4;

	private ProductDO product ;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	@Column(name="ACTIVE_PRICE")
	public String getActivePrice() {
		return activePrice;
	}
	public void setActivePrice(String activePrice) {
		this.activePrice = activePrice;
	}
	@Column(name="ORIGINAL_PRICE")
	public String getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}
	@Column(name="QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name="LIMIT_COUNT")
	public String getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}
	@Column(name="JOIN_COUNT")
	public String getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(String joinCount) {
		this.joinCount = joinCount;
	}
	@Column(name="LIMIT_TIME")
	public String getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(String limitTime) {
		this.limitTime = limitTime;
	}
	@Column(name="NOTE1")
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	@Column(name="NOTE2")
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	@Column(name="NOTE3")
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	@Lob
	@Column(name="NOTE4")
	public String getNote4() {
		return note4;
	}
	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}

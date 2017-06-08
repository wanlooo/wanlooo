package com.cn.xxx.yhsscore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 */
@Entity
@Table(name="YHSS_TG_ACTIVE")
//团购活动表
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","bargainDetail"})
public class TgActiveDO extends AbstractActiveDO {
	private static final long serialVersionUID = 1L;
	
	private String activePrice;//活动原价
	private String currentPrice;//当前价格
	private String limitCount;//限购数量
	private String quantity;//商品总数
	private String joinCount;//参与数值量（刀数）
	private String joinQuantity;//已购数量
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	
	private Set<TgActiveItemDO> activeItem ;
	private Set<TgActiveBargainDetailDO> bargainDetail ;
	private ProductDO product ;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "PRODUCT_ID")
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	
	@OneToMany(mappedBy = "tgActive",fetch=FetchType.LAZY)
	public Set<TgActiveItemDO> getActiveItem() {
//		activeItem = CollectionUtil.removeDeletedAndSortById(activeItem, "asc");
//		CollectionUtil.removeDeleted(activeItem);
		return activeItem;
	}
	public void setActiveItem(Set<TgActiveItemDO> activeItem) {
		this.activeItem = activeItem;
	}
	@OneToMany(mappedBy = "tgActive",fetch=FetchType.LAZY)
	public Set<TgActiveBargainDetailDO> getBargainDetail() {
//		CollectionUtil.removeDeletedAndSortById(bargainDetail, "asc");
		return bargainDetail;
	}
	public void setBargainDetail(Set<TgActiveBargainDetailDO> bargainDetail) {
		this.bargainDetail = bargainDetail;
	}
	@Column(name="ACTIVE_PRICE")
	public String getActivePrice() {
		return activePrice;
	}
	public void setActivePrice(String activePrice) {
		this.activePrice = activePrice;
	}
	@Column(name="LIMIT_COUNT")
	public String getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}
	@Column(name="QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	@Column(name="JOIN_COUNT")
	public String getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(String joinCount) {
		this.joinCount = joinCount;
	}
	@Column(name="JOIN_QUANTITY")
	public String getJoinQuantity() {
		return joinQuantity;
	}
	public void setJoinQuantity(String joinQuantity) {
		this.joinQuantity = joinQuantity;
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
	@Column(name="CURRENT_PRICE")
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	
}

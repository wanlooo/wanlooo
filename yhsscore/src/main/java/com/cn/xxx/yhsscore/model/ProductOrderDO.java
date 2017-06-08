package com.cn.xxx.yhsscore.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 商品订单表
 */
@Entity
@Table(name="YHSS_PRODUCT_ORDER")
public class ProductOrderDO extends AbstractSubOrderDO {

	private static final long serialVersionUID = 1L;
	private String properties; //商品属性汇总，用";"隔开
	private Integer quantity ;//商品数量
	
	private ProductDO product ;
	private OrderDO order ;
	private MsActiveDO miaosha ;
	private TgActiveDO tuangou ;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID")
	public OrderDO getOrder() {
		return order;
	}
	public void setOrder(OrderDO order) {
		this.order = order;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="MSACTIVE_ID")
	public MsActiveDO getMiaosha() {
		return miaosha;
	}
	public void setMiaosha(MsActiveDO miaosha) {
		this.miaosha = miaosha;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="TGACTIVE_ID")
	public TgActiveDO getTuangou() {
		return tuangou;
	}
	public void setTuangou(TgActiveDO tuangou) {
		this.tuangou = tuangou;
	}
	
	@Column(name="PROPERTIES")
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	@Column(name="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}

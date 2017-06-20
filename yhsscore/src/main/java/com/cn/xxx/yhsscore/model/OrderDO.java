package com.cn.xxx.yhsscore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cn.xxx.commons.model.AbstractBaseDO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 订单主表
 */
@Entity
@Table(name = "YHSS_ORDER")
@JsonIgnoreProperties({"lastUpdate","deleted","creator","lastModifier","lastModifierName","orderDetails","user"})
public class OrderDO extends AbstractBaseDO {

	private static final long serialVersionUID = -6436514501676105686L;
	private String orderNo;// 大订单号
	private String amount;// 总金额
	private String status ;
	private String type ;//订单包含类型: PRODUCT-商品订单，多个类型用“/”连接
	private String uuid ;//UUID订单时间戳码，用于校验重复提交订单，暂定6位数字
	private String note1;
	private String note2;
	private String note3;
	
	private Set<ProductOrderDO> productOrders ;
	
	private OrderAddressDO orderAddress ;
	private UserDO user ;
	
	@OneToMany(mappedBy = "order",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<ProductOrderDO> getProductOrders() {
		return productOrders;
	}
	public void setProductOrders(Set<ProductOrderDO> productOrders) {
		this.productOrders = productOrders;
	}
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
	public UserDO getUser() {
		return user;
	}
	public void setUser(UserDO user) {
		this.user = user;
	}
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="ORDER_ADDRESS_ID")
	public OrderAddressDO getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddressDO orderAddress) {
		this.orderAddress = orderAddress;
	}
	@Column(name = "ORDER_NO")
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	@Column(name = "AMOUNT")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	@Column(name = "NOTE1")
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	@Column(name = "NOTE2")
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	@Lob
	@Column(name = "NOTE3")
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name = "UUID")
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

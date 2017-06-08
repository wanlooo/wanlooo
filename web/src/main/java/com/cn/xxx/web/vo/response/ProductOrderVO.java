package com.cn.xxx.web.vo.response;

import java.util.List;

import com.cn.xxx.yhsscore.model.ProductDO;

public class ProductOrderVO {
	private String orderno ; //子订单号
	private String type;//订单类型 MS-秒杀订单 TG-团购订单
	private String amount;// 订单金额
	private String orderStatus;// 订单状态：WP-待支付 PF-已支付 XX-已取消
	private String userName ;//联系人
	private String userTel;//联系电话
	private String remark ;//备注信息
	private String properties; //商品属性汇总，用";"隔开
	private Integer quantity ;//商品数量
	private ProductDO product ;
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public ProductDO getProduct() {
		return product;
	}
	public void setProduct(ProductDO product) {
		this.product = product;
	}
	
}

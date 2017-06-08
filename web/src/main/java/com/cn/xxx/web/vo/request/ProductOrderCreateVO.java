package com.cn.xxx.web.vo.request;

import com.cn.xxx.yhsscore.model.OrderAddressDO;

public class ProductOrderCreateVO {
	private String orderType ;//MS-秒杀  TG-团购
	private String userName ;//联系人
	private String userTel ;//联系电话
	private String uuid ;//UUID订单时间戳码，用于校验重复提交订单,默认设为6位
	private String amount ;// 总金额
	private Long activeId ;//活动ID
	private Integer quantity ;//商品数量
	private String properties ; //商品属性汇总
	private String remark ;
	private OrderAddressDO express ;
	
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public OrderAddressDO getExpress() {
		return express;
	}
	public void setExpress(OrderAddressDO express) {
		this.express = express;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getActiveId() {
		return activeId;
	}
	public void setActiveId(Long activeId) {
		this.activeId = activeId;
	}
	
}

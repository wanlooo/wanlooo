package com.cn.xxx.web.vo.response;

import java.util.List;

import com.cn.xxx.yhsscore.model.OrderAddressDO;

public class OrderVO {
	private String orderNo;// 大订单号
	private String amount;// 总金额
	private String status ;
	private String type ;//订单包含类型: PRODUCT-商品订单，多个类型用“/”连接
	private OrderAddressDO orderAddress ;
	private List<ProductOrderVO> porders ;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public OrderAddressDO getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(OrderAddressDO orderAddress) {
		this.orderAddress = orderAddress;
	}
	public List<ProductOrderVO> getPorders() {
		return porders;
	}
	public void setPorders(List<ProductOrderVO> porders) {
		this.porders = porders;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}

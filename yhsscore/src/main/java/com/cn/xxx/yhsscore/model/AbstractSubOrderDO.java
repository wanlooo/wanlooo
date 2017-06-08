package com.cn.xxx.yhsscore.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.cn.xxx.commons.model.AbstractBaseDO;

@MappedSuperclass
public class AbstractSubOrderDO extends AbstractBaseDO {
	private static final long serialVersionUID = 2516071385435721847L;

	private String orderno ; //子订单号
	private String type;//订单类型 MS-秒杀订单 TG-团购订单
	private String amount;// 订单金额
	private String orderStatus;// 订单状态：WP-待支付 PF-已支付 XX-已取消
	private String userName ;//联系人
	private String userTel;//联系电话
	private String remark ;//备注信息
	private String note1;
	private String note2;
	private String note3;
	
	@Column(name="ORDERNO")
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="AMOUNT")
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Column(name="ORDER_STATUS")
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="USER_TEL")
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
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
	@Column(name="REMARK")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
			
}

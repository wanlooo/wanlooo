package com.cn.xxx.yhsscore.vo.resp;

import java.io.Serializable;

public class PointDetailVO implements Serializable {
	
	private static final long serialVersionUID = 66886717218343414L;
	private String type;
	private String point;
	private String orderNo;
	private String note1;
	private String note2;
	private String note3;
	private String note4;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	public String getNote4() {
		return note4;
	}
	public void setNote4(String note4) {
		this.note4 = note4;
	}
	
}

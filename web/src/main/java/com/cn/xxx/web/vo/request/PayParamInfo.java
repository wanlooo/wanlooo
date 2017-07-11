package com.cn.xxx.web.vo.request;

/**
 * @author usky
 * @date 2017年6月26日
 * @descripte 支付信息封装类
 */
public class PayParamInfo {

	private String orderNo;//订单号
    private String payType;//支付方式
    private String amount;//支付金额
    private String paymentNo;//支付流水号
    
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
    
}
